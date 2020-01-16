package com.woniu.entity;

import java.util.Date;

/**
 * 商品类实体
 * @author Administrator
 *
 */
public class Goods {
	private int goodsId;
	private String goodsCode;
	private String goodsName;
	private Date goodsData;
	private Float goodsPrice;
	private int goodsCount;
	private String goodsImg;
	private String goodsRemarks;
	private int poodsId;    
	private int gsdId;
	private Producer pro;
	private GoodsType gsd;
	public Goods() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Goods(int goodsId, String goodsCode, String goodsName, Date goodsData, Float goodsPrice, int goodsCount,
			String goodsImg, String goodsRemarks, Producer pro, GoodsType gsd) {
		super();
		this.goodsId = goodsId;
		this.goodsCode = goodsCode;
		this.goodsName = goodsName;
		this.goodsData = goodsData;
		this.goodsPrice = goodsPrice;
		this.goodsCount = goodsCount;
		this.goodsImg = goodsImg;
		this.goodsRemarks = goodsRemarks;
		this.pro = pro;
		this.gsd = gsd;
	}
	
	
	public Goods(String goodsCode, String goodsName, Float goodsPrice, int goodsCount, String goodsImg,
			String goodsRemarks, int poodsId, int gsdId) {
		super();
		this.goodsCode = goodsCode;
		this.goodsName = goodsName;
		this.goodsPrice = goodsPrice;
		this.goodsCount = goodsCount;
		this.goodsImg = goodsImg;
		this.goodsRemarks = goodsRemarks;
		this.poodsId = poodsId;
		this.gsdId = gsdId;
	}
	
	
	public Goods(int goodsId, String goodsCode, String goodsName, Float goodsPrice, int goodsCount, String goodsImg,
			int poodsId, int gsdId) {
		super();
		this.goodsId = goodsId;
		this.goodsCode = goodsCode;
		this.goodsName = goodsName;
		this.goodsPrice = goodsPrice;
		this.goodsCount = goodsCount;
		this.goodsImg = goodsImg;
		this.poodsId = poodsId;
		this.gsdId = gsdId;
	}
	public Goods(int goodsId, String goodsCode, String goodsName, Float goodsPrice, int goodsCount, String goodsImg,
			String goodsRemarks, int poodsId, int gsdId) {
		super();
		this.goodsId = goodsId;
		this.goodsCode = goodsCode;
		this.goodsName = goodsName;
		this.goodsPrice = goodsPrice;
		this.goodsCount = goodsCount;
		this.goodsImg = goodsImg;
		this.goodsRemarks = goodsRemarks;
		this.poodsId = poodsId;
		this.gsdId = gsdId;
	}
	public Goods(int goodsId, String goodsName, Float goodsPrice, int goodsCount, String goodsImg) {
		super();
		this.goodsId = goodsId;
		this.goodsName = goodsName;
		this.goodsPrice = goodsPrice;
		this.goodsCount = goodsCount;
		this.goodsImg = goodsImg;
	}
	public Date getGoodsData() {
		return goodsData;
	}
	
	public int getGoodsId() {
		return goodsId;
	}
	
	
	public int getPoodsId() {
		return poodsId;
	}
	public void setPoodsId(int poodsId) {
		this.poodsId = poodsId;
	}
	public int getGsdId() {
		return gsdId;
	}
	public void setGsdId(int gsdId) {
		this.gsdId = gsdId;
	}
	public String getGoodsCode() {
		return goodsCode;
	}
	public void setGoodsCode(String goodsCode) {
		this.goodsCode = goodsCode;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public Float getGoodsPrice() {
		return goodsPrice;
	}
	public void setGoodsPrice(Float goodsPrice) {
		this.goodsPrice = goodsPrice;
	}
	public int getGoodsCount() {
		return goodsCount;
	}
	public void setGoodsCount(int goodsCount) {
		this.goodsCount = goodsCount;
	}
	public String getGoodsImg() {
		return goodsImg;
	}
	public void setGoodsImg(String goodsImg) {
		this.goodsImg = goodsImg;
	}
	public String getGoodsRemarks() {
		return goodsRemarks;
	}
	public void setGoodsRemarks(String goodsRemarks) {
		this.goodsRemarks = goodsRemarks;
	}
	public Producer getPro() {
		return pro;
	}
	public void setPro(Producer pro) {
		this.pro = pro;
	}
	public GoodsType getGsd() {
		return gsd;
	}
	public void setGsd(GoodsType gsd) {
		this.gsd = gsd;
	}
	@Override
	public String toString() {
		return "Goods [goodsId=" + goodsId + ", goodsCode=" + goodsCode + ", goodsName=" + goodsName + ", goodsData="
				+ goodsData + ", goodsPrice=" + goodsPrice + ", goodsCount=" + goodsCount + ", goodsImg=" + goodsImg
				+ ", goodsRemarks=" + goodsRemarks + ", pro=" + pro + ", gsd=" + gsd + "]";
	}
	
	
}
