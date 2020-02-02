package com.woniu.entity;

import java.util.Date;

/**
 * 出库表
 * */
public class OutStock {
	private int outstockId;
	private int goodsId;
	private int staffId;
	private String outstockCode;
	private Date outstockDate;
	private int outstockCount;
	private String outstockStatus;
	private String outstockRemarks;
	private Goods goods;
	private Staffs staff;
	
	public OutStock() {
		super();
		// TODO Auto-generated constructor stub
	}
	public OutStock(int outstockId, int goodsId, int staffId, String outstockCode, Date outstockDate, int outstockCount,
			String outstockStatus, String outstockRemarks) {
		super();
		this.outstockId = outstockId;
		this.goodsId = goodsId;
		this.staffId = staffId;
		this.outstockCode = outstockCode;
		this.outstockDate = outstockDate;
		this.outstockCount = outstockCount;
		this.outstockStatus = outstockStatus;
		this.outstockRemarks = outstockRemarks;
	}
	
	public OutStock(int goodsId, int staffId, String outstockCode, int outstockCount, String outstockStatus) {
		super();
		this.goodsId = goodsId;
		this.staffId = staffId;
		this.outstockCode = outstockCode;
		this.outstockCount = outstockCount;
		this.outstockStatus = outstockStatus;
	}
	
	
	public OutStock( int goodsId, String outstockCode, int outstockCount, String outstockStatus) {
		super();
		this.goodsId = goodsId;
		this.outstockCode = outstockCode;
		this.outstockCount = outstockCount;
		this.outstockStatus = outstockStatus;
	}
	public OutStock(int outstockId, int goodsId, int outstockCount) {
		super();
		this.outstockId = outstockId;
		this.goodsId = goodsId;
		this.outstockCount = outstockCount;
	}
	public int getOutstockId() {
		return outstockId;
	}
	public void setOutstockId(int outstockId) {
		this.outstockId = outstockId;
	}
	public int getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(int goodsId) {
		this.goodsId = goodsId;
	}
	public int getStaffId() {
		return staffId;
	}
	public void setStaffId(int staffId) {
		this.staffId = staffId;
	}
	public String getOutstockCode() {
		return outstockCode;
	}
	public void setOutstockCode(String outstockCode) {
		this.outstockCode = outstockCode;
	}
	public Date getOutstockDate() {
		return outstockDate;
	}
	public void setOutstockDate(Date outstockDate) {
		this.outstockDate = outstockDate;
	}
	public int getOutstockCount() {
		return outstockCount;
	}
	public void setOutstockCount(int outstockCount) {
		this.outstockCount = outstockCount;
	}
	public String getOutstockStatus() {
		return outstockStatus;
	}
	public void setOutstockStatus(String outstockStatus) {
		this.outstockStatus = outstockStatus;
	}
	public String getOutstockRemarks() {
		return outstockRemarks;
	}
	public void setOutstockRemarks(String outstockRemarks) {
		this.outstockRemarks = outstockRemarks;
	}
	
	public Goods getGoods() {
		return goods;
	}
	public void setGoods(Goods goods) {
		this.goods = goods;
	}
	public Staffs getStaff() {
		return staff;
	}
	public void setStaff(Staffs staff) {
		this.staff = staff;
	}
	@Override
	public String toString() {
		return "OutStock [outstockId=" + outstockId + ", goodsId=" + goodsId + ", staffId=" + staffId
				+ ", outstockCode=" + outstockCode + ", outstockDate=" + outstockDate + ", outstockCount="
				+ outstockCount + ", outstockStatus=" + outstockStatus + ", outstockRemarks=" + outstockRemarks + "]";
	}
	
}
