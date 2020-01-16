package com.woniu.entity;

import java.util.Date;

/**
 * 购物车
 * @author Administrator
 *
 */
public class Shoppings {
	private int shoppingId;  
	private int goodsId;
	private int customerId;
	private int shoppingCount;
	private Date shoppingDate;
	private String shoppingStatus;
	private Goods goods;
	public Shoppings() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Shoppings(int shoppingId, int goodsId, int customerId, int shoppingCount, Date shoppingDate,
			String shoppingStatus) {
		super();
		this.shoppingId = shoppingId;
		this.goodsId = goodsId;
		this.customerId = customerId;
		this.shoppingCount = shoppingCount;
		this.shoppingDate = shoppingDate;
		this.shoppingStatus = shoppingStatus;
	}
	public Shoppings(int goodsId, int customerId) {
		super();
		this.goodsId = goodsId;
		this.customerId = customerId;
	}
	
	public Shoppings(int shoppingId, int shoppingCount, Date shoppingDate) {
		super();
		this.shoppingId = shoppingId;
		this.shoppingCount = shoppingCount;
		this.shoppingDate = shoppingDate;
		
	}
	
	public Shoppings(int shoppingId, int shoppingCount, Date shoppingDate, Goods goods) {
		super();
		this.shoppingId = shoppingId;
		this.shoppingCount = shoppingCount;
		this.shoppingDate = shoppingDate;
		this.goods = goods;
	}
	public Goods getGoods() {
		return goods;
	}
	public void setGoods(Goods goods) {
		this.goods = goods;
	}
	public int getShoppingId() {
		return shoppingId;
	}
	public void setShoppingId(int shoppingId) {
		this.shoppingId = shoppingId;
	}
	public int getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(int goodsId) {
		this.goodsId = goodsId;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public int getShoppingCount() {
		return shoppingCount;
	}
	public void setShoppingCount(int shoppingCount) {
		this.shoppingCount = shoppingCount;
	}
	public Date getShoppingDate() {
		return shoppingDate;
	}
	public void setShoppingDate(Date shoppingDate) {
		this.shoppingDate = shoppingDate;
	}
	public String getShoppingStatus() {
		return shoppingStatus;
	}
	public void setShoppingStatus(String shoppingStatus) {
		this.shoppingStatus = shoppingStatus;
	}
	@Override
	public String toString() {
		return "Shoppings [shoppingId=" + shoppingId + ", goodsId=" + goodsId + ", customerId=" + customerId
				+ ", shoppingCount=" + shoppingCount + ", shoppingDate=" + shoppingDate + ", shoppingStatus="
				+ shoppingStatus + "]";
	}
	
	
}
