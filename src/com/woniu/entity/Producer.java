package com.woniu.entity;
/**
 * 生产厂商
 * @author Administrator
 *
 */
public class Producer {
	private int proId;
	private String proCode;   
	private String proName;   
	private String proPhone;
	private String proAddress;
	private String proRemarks;
	public Producer() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Producer(int proId, String proCode, String proName, String proPhone, String proRemarks) {
		super();
		this.proId = proId;
		this.proCode = proCode;
		this.proName = proName;
		this.proPhone = proPhone;
		this.proRemarks = proRemarks;
	}
	
	public Producer(int proId, String proCode, String proName, String proPhone, String proAddress, String proRemarks) {
		super();
		this.proId = proId;
		this.proCode = proCode;
		this.proName = proName;
		this.proPhone = proPhone;
		this.proAddress = proAddress;
		this.proRemarks = proRemarks;
	}
	public String getProAddress() {
		return proAddress;
	}
	public void setProAddress(String proAddress) {
		this.proAddress = proAddress;
	}
	public int getProId() {
		return proId;
	}
	public void setProId(int proId) {
		this.proId = proId;
	}
	public String getProCode() {
		return proCode;
	}
	public void setProCode(String proCode) {
		this.proCode = proCode;
	}
	public String getProName() {
		return proName;
	}
	public void setProName(String proName) {
		this.proName = proName;
	}
	public String getProPhone() {
		return proPhone;
	}
	public void setProPhone(String proPhone) {
		this.proPhone = proPhone;
	}
	public String getProRemarks() {
		return proRemarks;
	}
	public void setProRemarks(String proRemarks) {
		this.proRemarks = proRemarks;
	}
	@Override
	public String toString() {
		return "Producer [proId=" + proId + ", proCode=" + proCode + ", proName=" + proName + ", proPhone=" + proPhone
				+ ", proRemarks=" + proRemarks + "]";
	}
	
}
