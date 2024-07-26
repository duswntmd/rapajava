package com.test.sku.doc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class FileService {
	private static final String FILE_PATH = "C:/test/list_fileinfo.ser";
	private static final String DOWNLOAD_DIR = "C:/test/download/";
    private static final String DATE_FORMAT = "yyyy-MM-dd";

    public boolean saveFile(Msg clientMsg) {
        List<FileInfo> list = loadFileList();

        // 빈 번호 찾기
        int newNumber = getNextAvailableNumber(list);

        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        String date = sdf.format(new Date());

        // 새로운 FileInfo 객체 추가
        list.add(new FileInfo(newNumber, clientMsg.fileName, clientMsg.fileData, clientMsg.who, clientMsg.content, date));

        // 리스트를 번호순으로 정렬
        list.sort(Comparator.comparingInt(FileInfo::getNumber));

        // 리스트 직렬화하여 파일에 저장
        return saveFileList(list);
    }
    
    public String showFiles() {
        List<FileInfo> list = loadFileList();

        // 결과를 문자열로 변환
        StringBuilder result = new StringBuilder("서버에서 받은 파일 목록:\n");
        for (FileInfo fileInfo : list) {
            result.append(fileInfo).append("\n");
        }

        return result.toString();
    }
    
    public String findFiles(int number) {
        List<FileInfo> foundFiles = new ArrayList<>();
        List<FileInfo> list = loadFileList();

        // 파일 번호로 검색
        for (FileInfo fileInfo : list) {
            if (fileInfo.getNumber() == number) {
                foundFiles.add(fileInfo);
            }
        }

        // 검색 결과를 문자열로 변환
        if (foundFiles.isEmpty()) {
            return "해당 번호의 파일을 찾을 수 없습니다.";
        } else {
            StringBuilder result = new StringBuilder("검색된 파일 목록:\n");
            for (FileInfo fileInfo : foundFiles) {
                result.append(fileInfo).append("\n");
            }
            return result.toString();
        }
    }
    
    public String updateFile(Msg clientMsg) {
        List<FileInfo> list = loadFileList();
        boolean found = false;
        String oldFileName = null;
        String newFileName = clientMsg.fileName;
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        String date = sdf.format(new Date());
        StringBuilder responseBuilder = new StringBuilder();

        // 번호로 파일 정보 업데이트
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getNumber() == clientMsg.number) {
                oldFileName = list.get(i).getFileName(); // 이전 파일 이름 저장
                FileInfo updatedFileInfo = new FileInfo(
                    clientMsg.number,
                    newFileName,
                    clientMsg.fileData,
                    clientMsg.who,
                    clientMsg.content,
                    date
                );
                list.set(i, updatedFileInfo);
                found = true;
                break;
            }
        }

        // 수정된 리스트를 파일에 저장
        if (saveFileList(list)) {
            System.out.println("리스트에서 파일 업데이트 성공");

            // 실제 파일 시스템에서 파일 이름 업데이트
            if (found && oldFileName != null) {
                File oldFile = new File(DOWNLOAD_DIR + oldFileName);
                File newFile = new File(DOWNLOAD_DIR + newFileName);

                if (oldFile.exists()) {
                    if (oldFile.renameTo(newFile)) {
                        String successMessage = "파일 이름 변경 성공: " + oldFile.getPath() + " -> " + newFile.getPath();
                        responseBuilder.append(successMessage).append("\n");
                        System.out.println(successMessage);
                    } else {
                        String failMessage = "파일 이름 변경 실패: " + oldFile.getPath();
                        responseBuilder.append(failMessage).append("\n");
                        System.err.println(failMessage);
                    }
                } else {
                    String notExistMessage = "파일이 존재하지 않습니다: " + oldFile.getPath();
                    responseBuilder.append(notExistMessage).append("\n");
                    System.err.println(notExistMessage);
                }
            }

            if (found) {
                responseBuilder.append("파일 업데이트 성공");
            } else {
                responseBuilder.append("파일 번호를 찾을 수 없습니다");
            }
        } else {
            System.err.println("리스트 저장 실패");
            responseBuilder.append("파일 업데이트 실패");
        }

        return responseBuilder.toString();
    }
    
    public String deleteFile(Msg clientMsg, ObjectOutputStream oos) {
        File file = new File(FILE_PATH);
        List<FileInfo> list = new ArrayList<>();

        if (file.exists()) {
            try (ObjectInputStream fileIn = new ObjectInputStream(new FileInputStream(file))) {
                list = (List<FileInfo>) fileIn.readObject();
            } catch (Exception e) {
                e.printStackTrace();
                return "파일 읽기 실패";
            }
        }

        boolean found = false;
        String fileNameToDelete = null;

        // 번호로 파일 정보 삭제
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getNumber() == clientMsg.number) {
                fileNameToDelete = list.get(i).getFileName(); // 파일 이름 저장
                list.remove(i);
                found = true;
                break;
            }
        }

        // 수정된 리스트를 파일에 저장
        try (ObjectOutputStream fileOut = new ObjectOutputStream(new FileOutputStream(file))) {
            fileOut.writeObject(list);
            fileOut.flush();
            System.out.println("리스트에서 파일 삭제 성공");

            // 실제 파일 시스템에서 파일 삭제
            if (fileNameToDelete != null) {
                File fileToDelete = new File(DOWNLOAD_DIR + fileNameToDelete);
                if (fileToDelete.exists() && fileToDelete.delete()) {
                    System.out.println(fileToDelete.getPath() + " 파일 삭제 성공");
                } else {
                    System.err.println(fileToDelete.getPath() + " 파일 삭제 실패");
                }
            }

            // 클라이언트에게 응답 전송
            Msg responseMsg = new Msg();
            if (found) {
                responseMsg.response = "파일 삭제 성공";
            } else {
                responseMsg.response = "파일 번호를 찾을 수 없습니다";
            }
            oos.writeObject(responseMsg);
            oos.flush();
            return responseMsg.response;
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("리스트 저장 실패");

            Msg responseMsg = new Msg();
            responseMsg.response = "파일 삭제 실패";
            try {
                oos.writeObject(responseMsg);
                oos.flush();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            return responseMsg.response;
        }
    }

    private List<FileInfo> loadFileList() {
        List<FileInfo> list;
        File file = new File(FILE_PATH);
        if (file.exists()) {
            try (ObjectInputStream fileIn = new ObjectInputStream(new FileInputStream(file))) {
                list = (List<FileInfo>) fileIn.readObject();
            } catch (Exception e) {
                e.printStackTrace();
                list = new ArrayList<>();
            }
        } else {
            list = new ArrayList<>();
        }
        return list;
    }

    private int getNextAvailableNumber(List<FileInfo> list) {
        int newNumber = 1;
        Set<Integer> usedNumbers = list.stream()
                                       .map(FileInfo::getNumber)
                                       .collect(Collectors.toSet());
        while (usedNumbers.contains(newNumber)) {
            newNumber++;
        }
        return newNumber;
    }

    private boolean saveFileList(List<FileInfo> list) {
        try (ObjectOutputStream fileOut = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            fileOut.writeObject(list);
            fileOut.flush();
            System.out.println("리스트 직렬화하여 파일에 저장 성공");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("리스트 직렬화하여 파일에 저장 실패");
            return false;
        }
    }
}
