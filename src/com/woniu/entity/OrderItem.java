package com.woniu.entity;
/**
 * 订单明细
 * @author very
 *
 */
public class OrderItem {
	private int orderItemId;
	private int goodsId;
	private String orderCode;
	private float orderItemPrice;
	private int orderItemCount;
	private Goods goods;
	public OrderItem() {
		super();
		// TODO Auto-generated constructor stub
	}
	public OrderItem(int orderItemId, int goodsId, String orderCode, float orderItemPrice, int orderItemCount,
			Goods goods) {
		super();
		this.orderItemId = orderItemId;
		this.goodsId = goodsId;
		this.orderCode = orderCode;
		this.orderItemPrice = orderItemPrice;
		this.orderItemCount = orderItemCount;
		this.goods = goods;
	}
	public int getOrderItemId() {
		return orderItemId;
	}
	public void setOrderItemId(int orderItemId) {
		this.orderItemId = orderItemId;
	}
	public int getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(int goodsId) {
		this.goodsId = goodsId;
	}
	public String getOrderCode() {
		return orderCode;
	}
	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}
	public float getOrderItemPrice() {
		return orderItemPrice;
	}
	public void setOrderItemPrice(float orderItemPrice) {
		this.orderItemPrice = orderItemPrice;
	}
	public int getOrderItemCount() {
		return orderItemCount;
	}
	public void setOrderItemCount(int orderItemCount) {
		this.orderItemCount = orderItemCount;
	}
	public Goods getGoods() {
		return goods;
	}
	public void setGoods(Goods goods) {
		this.goods = goods;
	}
	@Override
	public String toString() {
		return "OrderItem [orderItemId=" + orderItemId + ", goodsId=" + goodsId + ", orderCode=" + orderCode
				+ ", orderItemPrice=" + orderItemPrice + ", orderItemCount=" + orderItemCount + ", goods=" + goods
				+ "]";
	}
	
}
