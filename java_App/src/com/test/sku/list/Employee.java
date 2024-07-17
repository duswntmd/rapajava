package com.test.sku.list;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Employee 
{
	private int empno;
	private String ename;
	private int sal;
	private int deptno;
	private java.util.Date hiredate;
	
	public Employee() {}
	public Employee(int empno)
	{
		setEmpno(empno);
	}
	public Employee(int empno, String ename, int sal, int deptno, String sHiredate)
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		setEmpno(empno);
		setEname(ename);
		setSal(sal);
		setDeptno(deptno);
		try {
			setHiredate(sdf.parse(sHiredate));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	public Employee(String[] info) 
	{
		int empno = Integer.parseInt(info[0]);
		String ename = info[1];
		int sal = Integer.parseInt(info[2]);
		int deptno = Integer.parseInt(info[3]);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date hiredate = null;
		try {
			hiredate = sdf.parse(info[4]);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		setEmpno(empno);
		setEname(ename);
		setSal(sal);
		setDeptno(deptno);
		setHiredate(hiredate);
	}
	
	@Override
	public boolean equals(Object obj) {
		Employee other = (Employee) obj;
		return this.getEmpno()==other.getEmpno();
	}
	@Override
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String sHiredate = sdf.format(hiredate);
		return String.format("%d\t%s\t%d\t%d\t%s", empno,ename,sal,deptno,sHiredate);
	}
	public int getEmpno() {
		return empno;
	}
	public void setEmpno(int empno) {
		this.empno = empno;
	}
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public int getSal() {
		return sal;
	}
	public void setSal(int sal) {
		this.sal = sal;
	}
	public int getDeptno() {
		return deptno;
	}
	public void setDeptno(int deptno) {
		this.deptno = deptno;
	}
	public java.util.Date getHiredate() {
		return hiredate;
	}
	public void setHiredate(java.util.Date hiredate) {
		this.hiredate = hiredate;
	}
}
