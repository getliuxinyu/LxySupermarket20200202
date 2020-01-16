package com.woniu.entity;
/***
 * 商品类型实体
 * @author Administrator
 *
 */
public class GoodsType {
	  private int gsId;  
	  private String gsCode;  
	  private String gsName;
	  private String gsRemarks;
	public GoodsType() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public GoodsType(int gsId, String gsCode, String gsName, String gsRemarks) {
		super();
		this.gsId = gsId;
		this.gsCode = gsCode;
		this.gsName = gsName;
		this.gsRemarks = gsRemarks;
	}

	public int getGsId() {
		return gsId;
	}
	public void setGsid(int gsId) {
		this.gsId = gsId;
	}
	public String getGsCode() {
		return gsCode;
	}
	public void setGscode(String gsCode) {
		this.gsCode = gsCode;
	}
	public String getGsName() {
		return gsName;
	}
	public void setGsname(String gsName) {
		this.gsName = gsName;
	}
	
	public String getGsRemarks() {
		return gsRemarks;
	}

	public void setGsremarks(String gsRemarks) {
		this.gsRemarks = gsRemarks;
	}

	@Override
	public String toString() {
		return "GoodsType [gsId=" + gsId + ", gsCode=" + gsCode + ", gsName=" + gsName + ", gsRemarks=" + gsRemarks
				+ "]";
	}

	
}
