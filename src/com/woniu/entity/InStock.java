package com.woniu.entity;

import java.util.Date;

/***
 * 入库表
 * @author very
 *
 */
public class InStock {
	private int instockId;
	private int goodsId;
	private int staffId;
	private String instockCode;
	private Date instockDate;
	private String instockStatus;
	private int instockCount;
	private String instockRemarks;
	private Goods goods;
	private Staffs staff;
	public InStock() {
		super();
		// TODO Auto-generated constructor stub
	}
	public InStock(int instockId, int goodsId, int staffId, String instockCode, Date instockDate, String instockStatus,
			int instockCount, String instockRemarks) {
		super();
		this.instockId = instockId;
		this.goodsId = goodsId;
		this.staffId = staffId;
		this.instockCode = instockCode;
		this.instockDate = instockDate;
		this.instockStatus = instockStatus;
		this.instockCount = instockCount;
		this.instockRemarks = instockRemarks;
	}
	
	public InStock(int goodsId, int staffId, String instockCode, String instockStatus, int instockCount) {
		super();
		this.goodsId = goodsId;
		this.staffId = staffId;
		this.instockCode = instockCode;
		this.instockStatus = instockStatus;
		this.instockCount = instockCount;
	}
	
	public InStock(int instockId, String instockCode, String instockStatus, int instockCount) {
		super();
		this.instockId = instockId;
		this.instockCode = instockCode;
		this.instockStatus = instockStatus;
		this.instockCount = instockCount;
	}
	
	public InStock(int instockId, int goodsId, String instockCode,int instockCount) {
		super();
		this.instockId = instockId;
		this.goodsId = goodsId;
		this.instockCode = instockCode;
		this.instockStatus = instockStatus;
		this.instockCount = instockCount;
	}
	public InStock(int instockId, int goodsId, int instockCount) {
		super();
		this.instockId = instockId;
		this.goodsId = goodsId;
		this.instockCount = instockCount;
	}
	public int getInstockId() {
		return instockId;
	}
	public void setInstockId(int instockId) {
		this.instockId = instockId;
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
	public String getInstockCode() {
		return instockCode;
	}
	public void setInstockCode(String instockCode) {
		this.instockCode = instockCode;
	}
	public Date getInstockDate() {
		return instockDate;
	}
	public void setInstockDate(Date instockDate) {
		this.instockDate = instockDate;
	}
	public String getInstockStatus() {
		return instockStatus;
	}
	public void setInstockStatus(String instockStatus) {
		this.instockStatus = instockStatus;
	}
	public int getInstockCount() {
		return instockCount;
	}
	public void setInstockCount(int instockCount) {
		this.instockCount = instockCount;
	}
	public String getInstockRemarks() {
		return instockRemarks;
	}
	public void setInstockRemarks(String instockRemarks) {
		this.instockRemarks = instockRemarks;
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
		return "InStock [instockId=" + instockId + ", goodsId=" + goodsId + ", staffId=" + staffId + ", instockCode="
				+ instockCode + ", instockDate=" + instockDate + ", instockStatus=" + instockStatus + ", instockCount="
				+ instockCount + ", instockRemarks=" + instockRemarks + "]";
	}
	
}
