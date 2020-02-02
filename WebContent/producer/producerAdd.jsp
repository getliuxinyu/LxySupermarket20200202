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
	<form class="form-horizontal" method="post" action="${pageContext.request.contextPath}/producer/producerAdd.do" id="form_goods_add"  style="width:550px;margin:20px auto">
	<div class="form-group">
	    <label for="proCode" class="col-sm-3 control-label">生产商编号：</label>
	    <div class="col-sm-9">
	     	 <input type="text" class="form-control" id="proCode" name="proCode" placeholder="请输入生产商编号">
	    </div>
	 </div>
	 <div class="form-group">
	    <label for="proName" class="col-sm-3 control-label">生产商名称：</label>
	    <div class="col-sm-9">
	     	 <input type="text" class="form-control" id="proName" name="proName" placeholder="请输入生产商名称">
	    </div>
	  </div>
	<div class="form-group">
	    <label for="proAddress" class="col-sm-3 control-label">生产商地址：</label>
	    <div class="col-sm-9">
	     	 <input type="text" class="form-control" id="proAddress" name="proAddress" placeholder="请输入生产商地址">
	    </div>
	  </div>
	  <div class="form-group">
	    <label for="proPhone" class="col-sm-3 control-label">生产商电话：</label>
	    <div class="col-sm-9">
	     	 <input type="text" class="form-control" id="proPhone" name="proPhone" placeholder="请输入生产商电话">
	    </div>
	  </div>
	 
	  
	  <div class="form-group">
	    <label for="goodssave" class="col-sm-3 control-label"></label>
	    <div class="col-sm-9">
	       <input type="submit" id="producerAdd" class="btn btn-info col-sm-12" value="保存" />
	       
	    </div>
	  </div>
</form>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.12.3.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
</body>

</html>
