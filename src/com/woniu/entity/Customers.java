package com.woniu.entity;
/**
 * 顾客实体类
 * @author Administrator
 *
 */
public class Customers {
	private int customerId;  
	private String customerName;  
	private String customerPwd;
	private String customerPhone;
	private String customerAddress;
	private String customerStatus;
	public Customers() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Customers(int customerId, String customerName, String customerPwd, String customerPhone,
			String customerAddress, String customerStatus) {
		super();
		this.customerId = customerId;
		this.customerName = customerName;
		this.customerPwd = customerPwd;
		this.customerPhone = customerPhone;
		this.customerAddress = customerAddress;
		this.customerStatus = customerStatus;
	}
	
	
	public Customers(int customerId, String customerName, String customerPhone, String customerAddress) {
		super();
		this.customerId = customerId;
		this.customerName = customerName;
		this.customerPhone = customerPhone;
		this.customerAddress = customerAddress;
	}
	
	public Customers(String customerName, String customerPwd, String customerPhone, String customerAddress) {
		super();
		this.customerName = customerName;
		this.customerPwd = customerPwd;
		this.customerPhone = customerPhone;
		this.customerAddress = customerAddress;
	}
	public Customers(String customerName, String customerPwd) {
		super();
		this.customerName = customerName;
		this.customerPwd = customerPwd;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerPwd() {
		return customerPwd;
	}
	public void setCustomerPwd(String customerPwd) {
		this.customerPwd = customerPwd;
	}
	public String getCustomerPhone() {
		return customerPhone;
	}
	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}
	public String getCustomerAddress() {
		return customerAddress;
	}
	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}
	public String getCustomerStatus() {
		return customerStatus;
	}
	public void setCustomerStatus(String customerStatus) {
		this.customerStatus = customerStatus;
	}
	@Override
	public String toString() {
		return "Customers [customerId=" + customerId + ", customerName=" + customerName + ", customerPwd=" + customerPwd
				+ ", customerPhone=" + customerPhone + ", customerAddress=" + customerAddress + ", customerStatus="
				+ customerStatus + "]";
	}
	
	
}
