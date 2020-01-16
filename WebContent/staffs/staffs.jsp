<%@page import="com.woniu.entity.PageInfo"%>
<%@page import="com.woniu.entity.Staffs"%>
<%@page import="java.util.List"%>
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
<form action="${pageContext.request.contextPath }/staffs/staff.do" method="post" name="staffForm">
<div style="width:1000px;margin:0 auto;">
		员工编号：<input type="text" name="staffCode" value="${requestScope.map.staffCode}">
		员工名称：<input type="text" name="staffName" value="${requestScope.map.staffName}">
		<input type="submit" value="查询" class="btn btn-primary">
</div>
	<div style="text-align:right;width:1000px;margin:10px auto">
		<a  class="btn btn-success" href="staffsAdd.jsp">增加</a>
	</div>
	<table class="table table-striped table-striped table-bordered table-hover" style="width:1000px;margin: 20px auto">
	<caption style="text-align: center;width:1000px;margin: 20px auto;align"><h3>员工信息表</h3></caption>
	<thead>
		<tr>
			<th>员工id</th>
			<th>员工编号</th>
			<th>员工姓名</th>
			<th>员工状态</th>
			<th>员工职能</th>
			<th>备注</th>
		</tr>
	</thead>
	<% PageInfo pageInfo = (PageInfo)request.getAttribute("PageInfo");
		List<Staffs> li = pageInfo.getLi();
	
	%>
	<tbody>
		
		<%for(Staffs staff:li){ %>
			<tr>
				<td><%=staff.getStaffId() %></td>
				<td><%=staff.getStaffCode() %></td>
				<td><%=staff.getStaffName() %></td>
				<td><%=staff.getStaffStatus() %></td>
				<td><%=staff.getStaffFunction() %></td>
				<td><%=staff.getStaffRemarks() %></td>
				<td style="width:230px">
					<%if(staff.getStaffStatus().equals("启用")){ %>
						<a href="${pageContext.request.contextPath}/staffs/staffsConf.do?staffId=<%=staff.getStaffId() %>" class="btn btn-primary btn-xs">确认</a>
	
					
					
						<a href="${pageContext.request.contextPath}/staffs/staffsUpdUI.do?staffId=<%=staff.getStaffId() %>" class="btn btn-warning btn-xs">修改</a>
						<a href="${pageContext.request.contextPath}/staffs/staffsReset.do?staffId=<%=staff.getStaffId() %>" class="btn btn-success btn-xs">重置密码</a>
					
						
						<a href="${pageContext.request.contextPath}/staffs/staffsInactive.do?staffId=<%=staff.getStaffId() %>&staffSta=<%=staff.getStaffStatus() %>" class="btn btn-danger btn-xs">停用</a>
					<%} else{ %>
						<a href="${pageContext.request.contextPath}/staffs/staffsActive.do?staffId=<%=staff.getStaffId() %>&staffSta=<%=staff.getStaffStatus() %>" class="btn btn-info btn-xs">启用</a>
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
			<staffs:forEach begin="1" end="${requestScope.PageInfo.totalPages}" var="i">
				<staffs:choose>
					<staffs:when test="${requestScope.PageInfo.currentPage==i}">
					<!-- 如果是当前页码，就显示页码 -->
						${i}
					</staffs:when>
					<staffs:otherwise>
					<!-- 如果不是就超链接 -->
					<a href="javascrip:goPage(${i})">${i}</a>
					</staffs:otherwise>
				</staffs:choose>
			</staffs:forEach>
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