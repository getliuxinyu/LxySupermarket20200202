<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath}/css/bootstrap.min.css" type="text/css" rel="stylesheet"/>
</head>
<body>
	<form class="form-horizontal" method="post" action="goodsAdd.do" id="form_goods_add" enctype="multipart/form-data" style="width:550px;margin:20px auto">
	<div class="form-group">
	    <label for="goodscode" class="col-sm-3 control-label">商品编号：</label>
	    <div class="col-sm-9">
	     	 <input type="text" class="form-control" id="goodscode" name="goodsCode" placeholder="请输入商品编号">
	    </div>
	 </div>
	 <div class="form-group">
	    <label for="goodsname" class="col-sm-3 control-label">商品名称：</label>
	    <div class="col-sm-9">
	     	 <input type="text" class="form-control" id="goodsname" name="goodsName" placeholder="请输入商品名称">
	    </div>
	  </div>
	<div class="form-group">
	    <label for="goodsprice" class="col-sm-3 control-label">商品价格：</label>
	    <div class="col-sm-9">
	     	 <input type="text" class="form-control" id="goodsprice" name="goodsPrice" placeholder="请输入商品价格">
	    </div>
	  </div>
	  <div class="form-group">
	    <label for="goodscount" class="col-sm-3 control-label">商品数量：</label>
	    <div class="col-sm-9">
	     	 <input type="text" class="form-control" id="goodscount" name="goodsCount" placeholder="请输入商品数量">
	    </div>
	  </div>
	  <div class="form-group">
	    <label for="proname" class="col-sm-3 control-label">生产厂家id：</label>
	    <div class="col-sm-9">
	     	 <input type="text" class="form-control" id="proname" name="proName" placeholder="请输入生产厂家id">
	    </div>
	  </div>
	  <div class="form-group">
	    <label for="gstname" class="col-sm-3 control-label">类型id：</label>
	    <div class="col-sm-9">
	     	 <input type="text" class="form-control" id="gstname" name="gstId" placeholder="请输入类型id">
	    </div>
	  </div>
	  <div class="form-group">
	    <label for="goodsremarks" class="col-sm-3 control-label">备注：</label>
	    <div class="col-sm-9" >
			<select name="goodsRemarks">
				<option value="未确认">未确认</option>
			</select>
		</div>
	  </div>
	  
	   <div class="form-group">
	    <label for="gstname" class="col-sm-3 control-label">商品图片：</label>
	    <div class="col-sm-9">
	     	 <input type="file" name="goodsImg">
	    </div>
	  </div>
	  
	  
	  
	  <div class="form-group">
	    <label for="goodssave" class="col-sm-3 control-label"></label>
	    <div class="col-sm-9">
	       <input type="submit" id="goodsAdd" class="btn btn-info col-sm-12" value="保存" />
	       
	    </div>
	  </div>
</form>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.12.3.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
</body>

</html>
