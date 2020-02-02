<%@page import="com.woniu.entity.Customers"%>
<%@page import="com.woniu.entity.PageInfo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="customers" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath}/css/bootstrap.min.css" type="text/css" rel="stylesheet"/>
</head>
<body>
<form action="${pageContext.request.contextPath }/customers/customer.do" method="post" name="cusForm">
<div style="width:1000px;margin:0 auto;">
		员工编号：<input type="text" name="cusName" value="${requestScope.map.cusName}">
		<input type="submit" value="查询" class="btn btn-primary">
</div>
	<table class="table table-striped table-striped table-bordered table-hover" style="width:1000px;margin: 20px auto">
	<caption style="text-align: center;width:1000px;margin: 20px auto;align"><h3>顾客信息表</h3></caption>
	<thead>
		<tr>
			<th>顾客id</th>
			<th>顾客姓名</th>
			<th>顾客密码</th>
			<th>顾客电话</th>
			<th>顾客地址</th>
			<th>顾客状态</th>
		</tr>
	</thead>
	<% PageInfo pageInfo = (PageInfo)request.getAttribute("PageInfo");
		List<Customers> li = pageInfo.getLi();
	
	%>
	<tbody>
		
		<%for(Customers customer:li){ %>
			<tr>
				<td><%=customer.getCustomerId() %></td>
				<td><%=customer.getCustomerName() %></td>
				<td><%=customer.getCustomerPwd() %></td>
				<td><%=customer.getCustomerPhone() %></td>
				<td><%=customer.getCustomerAddress() %></td>
				<td><%=customer.getCustomerStatus() %></td>
				<td style="width:230px">
					<%if(customer.getCustomerStatus().equals("活跃")){ %>
						<a href="${pageContext.request.contextPath}/customers/customersUpdUI.do?cusId=<%=customer.getCustomerId() %>" class="btn btn-warning btn-xs">修改</a>
						<a href="${pageContext.request.contextPath}/customers/customersReset.do?cusId=<%=customer.getCustomerId() %>" class="btn btn-success btn-xs">重置密码</a>
					
						
						<a href="${pageContext.request.contextPath}/customers/customersInactive.do?cusId=<%=customer.getCustomerId() %>" class="btn btn-danger btn-xs">停用</a>
					<%} else{ %>
						<a href="${pageContext.request.contextPath}/customers/customersActive.do?cusId=<%=customer.getCustomerId() %>" class="btn btn-info btn-xs">启用</a>
					<%} %>
				</td>
			</tr>
		<%} %>
		
	</tbody>
		<tr>
			<td colspan="7">
			<a href="javascript:goPage(${1})">首页</a>
			&nbsp; &nbsp; &nbsp; &nbsp;
			<a href="javascript:goPage(${requestScope.PageInfo.currentPage-1})">上一页</a>
			&nbsp; &nbsp;	
			<customers:forEach begin="1" end="${requestScope.PageInfo.totalPages}" var="i">
				<customers:choose>
					<customers:when test="${requestScope.PageInfo.currentPage==i}">
					<!-- 如果是当前页码，就显示页码 -->
						${i}
					</customers:when>
					<customers:otherwise>
					<!-- 如果不是就超链接 -->
					<a href="javascrip:goPage(${i})">${i}</a>
					</customers:otherwise>
				</customers:choose>
			</customers:forEach>
			&nbsp; &nbsp;
			<a href="javascript:goPage(${requestScope.PageInfo.currentPage+1})">下一页</a>
			&nbsp; &nbsp;
			页面显示条数：
			<!-- 页面显示条数通过表单提交，过去。初始值默认为1 -->
			<select name="pageSize" onchange="goPage(1)">
				<option value="3">3</option>
				<option value="4">4</option>
				<option value="5">5</option>
			</select>
			&nbsp; &nbsp; &nbsp; &nbsp;
			<a href="javascript:goPage(${requestScope.PageInfo.totalPages})" >尾页</a>
			&nbsp; &nbsp; &nbsp; &nbsp;
			当前页面：${requestScope.PageInfo.currentPage}
			&nbsp; &nbsp; &nbsp; &nbsp;
			当前页面条数：${requestScope.PageInfo.pageSize}
			&nbsp; &nbsp; &nbsp; &nbsp;
			总页面：${requestScope.PageInfo.totalPages}
			&nbsp; &nbsp; &nbsp; &nbsp;
			总信息条数：${requestScope.PageInfo.totalCount}
			</td>
				
				
		</tr>	
</table>
<input type="hidden" name="currentPage" value="1">
</form>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.12.3.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script type="text/javascript">
//当前页信息
	function goPage(cutPage){
		staffForm.currentPage.value=cutPage;
		staffForm.submit();
	}
//页面显示条数信息	
	//设置option值
$("[name=pageSize] option[value=${requestScope.PageInfo.pageSize}]").attr("selected","selected");
</script>
</body>
</html>