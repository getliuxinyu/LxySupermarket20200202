<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath }/css/bootstrap.min.css" type="text/css" rel="stylesheet"/>
</head>
<body>

<div class="container-fluid">
	<div class="row" style="background-color:green">
			<div class="col-md-12" style="height: 100px">
			  <h1 align="center">仓储管理系统</h1>
			
			</div>
		
			<div style="color:white">
			 ${sessionScope.uname}已经登录&nbsp;&nbsp;<a href="exit.do" style="color:yellow">安全退出</a>
			</div>
	</div>
	<div class="row" style="height:600px">
		<div class="col-md-3">
				<div class="panel-group" id="accordion">
							  <div class="panel panel-default">
							    <div class="panel-heading">
							      <h4 class="panel-title">
							        <a data-toggle="collapse" data-toggle="collapse" data-parent="#accordion" href="#collapseOne">
							          	系统管理
							        </a>
							      </h4>
							    </div>
							    <div id="collapseOne" class="panel-collapse collapse in">
							      <div class="panel-body">
							      		<ul>
							      			<li>
							      				<a href="${pageContext.request.contextPath }/staffs/staff.do" target="goodsFrame">员工管理</a>
							      			</li>
							      			<li>
							      				<a href="${pageContext.request.contextPath }/staffs/staffsUpdPwd.jsp" target="goodsFrame">修改密码</a>
							      			</li>
							      			
							      			<li>
							      				<a href="#">权限管理</a>
							      			</li>
							      		</ul>
							      </div>
							    </div>
							  </div>
							  
							  
							  <div class="panel panel-default">
							    <div class="panel-heading">
							      <h4 class="panel-title">
							        <a data-toggle="collapse" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo">
							          	基础数据管理
							        </a>
							      </h4>
							    </div>
							    <div id="collapseTwo" class="panel-collapse collapse in">
							      <div class="panel-body">
							      		<ul>
							      			<li>
							      				<a href="${pageContext.request.contextPath }/goods/showgoods.do" target="goodsFrame">商品管理</a>
							      			</li>
							      			<li>
							      				<a href="${pageContext.request.contextPath }/goodstype/gt.do" target="goodsFrame">商品类型管理</a>
							      			</li>
							      			
							      			<li>
							      				<a href="${pageContext.request.contextPath }/producer/pro.do" target="goodsFrame">供应商管理</a>
							      			</li>
							      		</ul>
							      </div>
							    </div>
							  </div>
							  
							  <div class="panel panel-default">
							    <div class="panel-heading">
							      <h4 class="panel-title">
							        <a data-toggle="collapse" data-toggle="collapse" data-parent="#accordion" href="#collapseThree">
							          	出入库数据管理
							        </a>
							      </h4>
							    </div>
							    <div id="collapseTwo" class="panel-collapse collapse in">
							      <div class="panel-body">
							      		<ul>
							      			<li>
							      				<a href="#" target="goodsFrame">入库管理</a>
							      			</li>
							      			<li>
							      				<a href="${pageContext.request.contextPath }/outstock/out.do" target="goodsFrame">出库管理</a>
							      			</li>
							      		</ul>
							      </div>
							    </div>
							  </div>
					</div>
		
		</div>
		
			<div class="col-md-9" style="height: 600px">
			<iframe name="goodsFrame" width="100%" height="100%"></iframe>
		
			</div>
		
		</div>
	
	</div>
	
	<div class="row">
		<div class="col-md-12" style="text-align: center;height: 50px;line-height: 50px;background-color:ghostwhite">
		
				&copy;版权信息。。。。。。。在线人数：<%=application.getAttribute("online")%>
		
		</div>
	</div>

</div>



<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.12.3.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }js/bootstrap.min.js"></script>

</script>
</body>
</html>