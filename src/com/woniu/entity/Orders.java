package com.woniu.entity;

import java.util.Date;

/**
 * 订单实体类
 * @author Administrator
 *
 */
public class Orders {
	private String orderCode;
	private int customerId;
	private Date orderDate;
	private float orderPrice;
	private String orderRemarks;
	public Orders() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Orders(String orderCode, int customerId, Date orderDate, String orderRemarks) {
		super();
		this.orderCode = orderCode;
		this.customerId = customerId;
		this.orderDate = orderDate;
		this.orderRemarks = orderRemarks;
	}
	
	
	
	public Orders(int customerId, float orderPrice, String orderRemarks) {
		super();
		this.customerId = customerId;
		this.orderPrice = orderPrice;
		this.orderRemarks = orderRemarks;
	}
	
	public float getOrderPrice() {
		return orderPrice;
	}
	public void setOrderPrice(float orderPrice) {
		this.orderPrice = orderPrice;
	}
	
	public String getOrderCode() {
		return orderCode;
	}
	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public String getOrderRemarks() {
		return orderRemarks;
	}
	public void setOrderRemarks(String orderRemarks) {
		this.orderRemarks = orderRemarks;
	}
	@Override
	public String toString() {
		return "Orders [orderCode=" + orderCode + ", customerId=" + customerId + ", orderDate=" + orderDate
				+ ", orderRemarks=" + orderRemarks + "]";
	}
	
	
}
