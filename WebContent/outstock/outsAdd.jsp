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
	<form class="form-horizontal" method="post" action="${pageContext.request.contextPath}/outstock/outstockAdd.do" id="form_goods_add" style="width:550px;margin:20px auto">
	<div class="form-group">
	    <label for="goodscode" class="col-sm-3 control-label">出库编号：</label>
	    <div class="col-sm-9">
	     	 <input type="text" class="form-control" id="goodscode" name="outsCode" placeholder="请输入出库编号">
	    </div>
	 </div>
	 <div class="form-group">
	    <label for="goodsname" class="col-sm-3 control-label">出库数量：</label>
	    <div class="col-sm-9">
	     	 <input type="text" class="form-control" id="goodsname" name="outsCount" placeholder="请输入出库数量">
	    </div>
	  </div>
	<div class="form-group">
	    <label for="goodsprice" class="col-sm-3 control-label">出库状态：</label>
	    <div class="col-sm-9">
	     	 <input type="text" class="form-control" id="goodsprice" name="outsStatus" placeholder="请输入出库状态">
	    </div>
	  </div>
	  <div class="form-group">
	    <label for="proname" class="col-sm-3 control-label">商品id：</label>
	    <div class="col-sm-9">
	     	 <input type="text" class="form-control" id="proname" name="goodsId" placeholder="请输入商品Id">
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
