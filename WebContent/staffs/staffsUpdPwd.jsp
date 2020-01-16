<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="staffs" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath}/css/bootstrap.min.css" type="text/css" rel="stylesheet"/>
</head>
<body>
	<form class="form-horizontal" method="post" action="changePwd.do"  style="width:600px;margin:20px auto">
	  <div class="form-group" >
	    <label for="firstname" class="col-md-3 control-label">旧密码</label>
	    <div class="col-md-6">
	      <input type="password" class="form-control" id="firstname" name="oldPwd" placeholder="请输入原密码">
	    </div>
	    <div class="clo-md-3" id="showPwd" style="color:red;" ></div>
	  </div>
	  
	  <div class="form-group" >
	    <label for="secondname" class="col-md-3 control-label">新密码</label>
	    <div class="col-md-6">
	      <input type="text" class="form-control" id="secondname" name="newPwd" placeholder="请输入新密码">
	    </div>
	  </div>
	  
	  
	  <div class="form-group" >
	    <label for="thirdname" class="col-md-3 control-label">确认密码</label>
	    <div class="col-md-6">
	      <input type="text" class="form-control" id="thirdname" name="repeatPwd" placeholder="请确认新密码">
	    </div>
		  <div class="clo-md-3" id="changePwd" style="color:red;" ></div>	    
	  </div>
	  
	  
	  <div class="form-group">
		<label class="col-md-4 control-label"></label>								
		<div class="col-md-5">
	     	<input type="submit" class="btn btn-info" id="upd_pwd_btn" value="保存"/>
	    	
		</div> 
	 </div>
	</form>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.12.3.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script type="text/javascript">
//添加change事件，原密码改变时比对密码是否正确
//全局变量tag原密码不正确时组织表单提交
var tag=false;
	$("[name=oldPwd]").change(function(){
		$.ajax({
			cache:false,
			url:"checkPwd.do",
			type:"post",
			data:{"oldPwd":$("[name=oldPwd]").val()},
			aysnc:true,
			error:function(){
				alert("旧密码显示有误");
			},
			
			success:function(data){
				if(data=="true"){
					tag=true;
					$("#showPwd").html("");
				}else{
					tag=false;
					$("#showPwd").html("原密码不正确");
				}
			}
		});
	});
	
	
	$(":submit").click(function(){
		if(tag==false){
			return false;
		}
		
		if($("[name=newPwd]").val()==""||$("[name=repeatPwd]").val()==""){
			$("#changePwd").html("新密码不能为空");
			return false;
		}
		
		if($("[name=newPwd]").val()!=$("[name=repeatPwd]").val()){
			$("#changePwd").html("确认密码不一致");
			return false;
		}
		
		
	});
	
	
	
</script>
</body>
</html>
