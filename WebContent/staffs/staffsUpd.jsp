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
<form class="form-horizontal" method="post" action="staffsUpd.do" id="form_goods_add"  style="width:550px;margin:20px auto">
	<div class="form-group">
	    <label for="goodscode" class="col-sm-3 control-label">员工编号：</label>
	    <div class="col-sm-9">
	     	 <input type="text" class="form-control" id="goodscode" name="staffCode" placeholder="请输入修改的员工编号" value="${requestScope.staff.staffCode }">
	    </div>
	 </div>
	 
	 <div class="form-group">
	    <label for="goodscode" class="col-sm-3 control-label">员工姓名：</label>
	    <div class="col-sm-9">
	     	 <input type="text" class="form-control" id="goodscode" name="staffName" placeholder="请输入修改的员工姓名" value="${requestScope.staff.staffName }">
	    </div>
	 </div>
	 
	<div class="form-group">
	    <label for="goodscode" class="col-sm-3 control-label">员工职能：</label>
	    <div class="col-sm-9">
	     	 <input type="text" class="form-control" id="goodscode" name="staffFunction" placeholder="请输入修改的员工职能" value="${requestScope.staff.staffFunction }">
	    </div>
	 </div>
	 
	  <div class="form-group">
	    <label for="goodssave" class="col-sm-3 control-label"></label>
	    <div class="col-sm-9">
	    	<input type="hidden" name="staffId" value="${requestScope.staff.staffId }">
	       <input type="submit" id="goodsAdd" class="btn btn-info col-sm-12" value="保存" />
	       
	    </div>
	  </div>
</form>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.12.3.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
</body>
</html>