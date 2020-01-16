<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="goods" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath}/css/bootstrap.min.css" type="text/css" rel="stylesheet"/>
</head>
<body>
<form class="form-horizontal" method="post" action="goodsUpd.do" id="form_goods_add" enctype="multipart/form-data" style="width:550px;margin:20px auto">
	<div class="form-group">
	    <label for="goodscode" class="col-sm-3 control-label">商品编号：</label>
	    <div class="col-sm-9">
	     	 <input type="text" class="form-control" id="goodscode" name="goodsCode" placeholder="请输入修改的商品编号" value="${requestScope.goodsInfo.goods.goodsCode }">
	    </div>
	 </div>
	 
	 <div class="form-group">
	    <label for="goodsname" class="col-sm-3 control-label">商品名称：</label>
	    <div class="col-sm-9">
	     	 <input type="text" class="form-control" id="goodsname" name="goodsName" placeholder="请输入修改的商品名称" value="${requestScope.goodsInfo.goods.goodsName }">
	    </div>
	 </div>
	 
	<div class="form-group">
	    <label for="goodsprice" class="col-sm-3 control-label">商品价格：</label>
	    <div class="col-sm-9">
	     	 <input type="text" class="form-control" id="goodsprice" name="goodsPrice" placeholder="请输入修改的商品价格" value="${requestScope.goodsInfo.goods.goodsPrice }">
	    </div>
	 </div>
	 
	 <div class="form-group">
	    <label for="goodscount" class="col-sm-3 control-label">商品数量：</label>
	    <div class="col-sm-9">
	     	 <input type="text" class="form-control" id="goodscount" name="goodsCount" placeholder="请输入修改的商品数量" value="${requestScope.goodsInfo.goods.goodsCount }">
	    </div>
	 </div>
	 
	<div class="form-group">
	    <label for="goodsImg" class="col-sm-3 control-label">商品图片：</label>
	    <div class="col-sm-9">
	     	 <input type="file" class="form-control" name="goodsNewImg">
	    </div>
	 </div>
	 
	 <div class="form-group">
	    <label for="goodstypeid" class="col-sm-3 control-label">商品类型id：</label>
	    <div class="col-sm-9">
	     	<select name="goodstypeId">
	     		<goods:forEach items="${requestScope.goodsInfo.goodstypeArr }" var="goodstype">
	     			<option value="${goodstype.gsId }">${goodstype.gsName }</option>
	     		</goods:forEach>
	     	</select>
	    </div>
	 </div>
	 
	 <div class="form-group">
	    <label for="producerid" class="col-sm-3 control-label">生产商名称：</label>
	    <div class="col-sm-9">
	     	<select name="producerId">
	     		<goods:forEach items="${requestScope.goodsInfo.producerArr }" var="producer">
	     			<option value="${producer.proId }">${producer.proName }</option>
	     		</goods:forEach>
	     	</select>
	    </div>
	 </div>
	 
	  <div class="form-group">
	    <label for="goodssave" class="col-sm-3 control-label"></label>
	    <div class="col-sm-9">
	    	<input type="hidden" name="goodsId" value="${requestScope.goodsInfo.goods.goodsId }">
	       <input type="hidden"  name="goodsOldImg"  value="${requestScope.goodsInfo.goods.goodsImg }">
	       <input type="submit" id="goodsAdd" class="btn btn-info col-sm-12" value="保存" />
	    </div>
	  </div>
</form>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.12.3.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
</body>
</html>