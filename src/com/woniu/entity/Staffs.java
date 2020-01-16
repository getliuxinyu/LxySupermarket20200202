package com.woniu.entity;
/**
 * 员工表
 * @author Administrator
 *
 */
public class Staffs {
	private int staffId;
	private String staffCode;
	private String staffName;
	private String staffPwd;
	private String staffStatus; //状态
	private String staffFunction; //职能
	private String staffRemarks;
	
	public Staffs() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Staffs(int staffId, String staffCode, String staffName, String staffPwd, String staffStatus,
			String staffFunction, String staffRemarks) {
		super();
		this.staffId = staffId;
		this.staffCode = staffCode;
		this.staffName = staffName;
		this.staffPwd = staffPwd;
		this.staffStatus = staffStatus;
		this.staffFunction = staffFunction;
		this.staffRemarks = staffRemarks;
	}
	
	
	public Staffs(String staffCode, String staffName, String staffFunction) {
		super();
		this.staffCode = staffCode;
		this.staffName = staffName;
		this.staffFunction = staffFunction;
	}

	

	public Staffs(int staffId, String staffCode, String staffName, String staffFunction) {
		super();
		this.staffId = staffId;
		this.staffCode = staffCode;
		this.staffName = staffName;
		this.staffFunction = staffFunction;
	}

	public int getStaffId() {
		return staffId;
	}
	
	public String getStaffCode() {
		return staffCode;
	}
	public void setStaffCode(String staffCode) {
		this.staffCode = staffCode;
	}
	public String getStaffName() {
		return staffName;
	}
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}
	public String getStaffPwd() {
		return staffPwd;
	}
	public void setStaffPwd(String staffPwd) {
		this.staffPwd = staffPwd;
	}
	public String getStaffStatus() {
		return staffStatus;
	}
	public void setStaffStatus(String staffStatus) {
		this.staffStatus = staffStatus;
	}
	public String getStaffFunction() {
		return staffFunction;
	}
	public void setStaffFunction(String staffFunction) {
		this.staffFunction = staffFunction;
	}
	public String getStaffRemarks() {
		return staffRemarks;
	}
	public void setStaffRemarks(String staffRemarks) {
		this.staffRemarks = staffRemarks;
	}
	@Override
	public String toString() {
		return "Staffs [staffId=" + staffId + ", staffCode=" + staffCode + ", staffName=" + staffName + ", staffPwd="
				+ staffPwd + ", staffStatus=" + staffStatus + ", staffFunction=" + staffFunction + ", staffRemarks="
				+ staffRemarks + "]";
	}
	
	
}
