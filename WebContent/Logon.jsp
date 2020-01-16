<%@page import="java.net.URLDecoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath}/css/bootstrap.min.css" type="text/css" rel="stylesheet"/>

</head>


<body>
   <div class="container-fluid">
		<div class="row" style="background-color: black;color: white;height: 100px">
		 		<div  class="col-md-12">
		 			<h1 align="center">超级市场</h1>
		 		</div> 
				
		</div>

		
		<div class="row" style="height:550px" style="background-image: url(images/2.jpg)">
		 		<div  class="col-md-12">
		 			    <form class="form-horizontal" action="logon.do" method="post" style="width: 400px;margin:100px auto;" id="logon_form"> 
							<div class="form-group">
								<label class="col-md-4 col-sm-4 control-label">用户名：</label>
								<div class="col-md-5 ">
									<input type="text" class="form-control"  id="logon"  name="userName" >
									
								</div>
								
							</div>
							<div class="form-group">
								<label class="col-md-4 col-sm-4 control-label">密码：</label>
								<div class="col-md-5 ">
									<input type="password" class="form-control"   name="userPwd">
								
								</div>
							</div>
							
							<div class="form-group">
								<label class="col-md-4 col-sm-4 control-label">验证码：</label>
								<div class="col-md-4 ">
									<input type="text" class="form-control"  required="required" name="checkCode">
								
								</div>
								<div class="col-md-4 ">
								<!-- 图片路径可以为servlet路径，点击事件继续请求图片路径，路径后拼接随机数是为了每次请求路径都不同 -->
									<img alt="" src="checkCode.do" onclick="this.src='checkCode.do?t='+Math.random()">
								
								</div>
							</div>
							
					
							<div class="form-group">
								<label class="col-md-4 control-label"></label>								
								<div class="col-md-5">
								    
									     	<input type="submit" class="btn btn-info" id="logon_btn" value="登录"/>
									    	<input type="reset" class="btn btn-info" value="取消"/>
								
								</div> 
							</div>
						</form>
		 		</div> 
				 
		</div>
		<div class="row" style="background-color: #eee;height: 100px">
		 		<div  class="col-md-12" style="text-align: center;line-height: 100px">
		 		
		 			&copy;版权信息。。。。。。。
		 		</div>
				
		</div>		

</div>
  


<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.12.3.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>


</body>

</html>
