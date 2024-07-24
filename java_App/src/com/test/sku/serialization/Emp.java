package com.test.sku.serialization;

import java.io.Serializable;

import javax.print.attribute.standard.MediaSize.Other;

public class Emp implements Serializable{
	private int empno;
	private String ename;
	private int deptno;
	private int sal;
	
	
	
	@Override
	public String toString() {
		return "Emp [empno=" + empno + ", ename=" + ename + ", deptno=" + deptno + ", sal=" + sal + "]";
	}

	@Override
	public boolean equals(Object obj) {
		Emp other = (Emp) obj;
		return this.getEmpno() == other.getEmpno();
	}

	public Emp() {
		super();
	}

	public Emp(int empno) {
		super();
		this.empno = empno;
	}
	
	public Emp(int empno, String ename, int deptno, int sal) {
		super();
		this.empno = empno;
		this.ename = ename;
		this.deptno = deptno;
		this.sal = sal;
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
	public int getDeptno() {
		return deptno;
	}
	public void setDeptno(int deptno) {
		this.deptno = deptno;
	}
	public int getSal() {
		return sal;
	}
	public void setSal(int sal) {
		this.sal = sal;
	}
	
	
}
