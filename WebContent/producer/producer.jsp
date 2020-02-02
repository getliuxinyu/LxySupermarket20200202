<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="producer" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath}/css/bootstrap.min.css" type="text/css" rel="stylesheet"/>
</head>
<body>
<form action="${pageContext.request.contextPath }/producer/pro.do" method="post" name="producerForm">
	<div style="width:1000px;margin:0 auto;">
		商品编号：<input type="text" name="proCode" value="${requestScope.map.proCode}">
		商品名称：<input type="text" name="proName" value="${requestScope.map.proName}">
		<input type="submit" value="查询" class="btn btn-primary">
	</div>
	<div style="text-align:right;width:1000px;margin:10px auto">
		<a  class="btn btn-success" href="producerAdd.jsp">增加</a>
	</div>
	<table class="table table-striped table-striped table-bordered table-hover" id="goodsTable" style="width:1100px;margin: 20px auto">
	<caption style="text-align: center;width:1000px;margin:auto;"><h3>生厂商</h3></caption>
	<thead>
		<tr>
			<th>生厂商id</th>
			<th>生厂商编号</th>
			<th>生厂商名称</th>
			<th>生厂商地址</th>
			<th>生厂商电话</th>
		</tr>
	</thead>
	<tbody>
		<producer:forEach items="${requestScope.map.page.li }" var="producer" >
			<tr>
				<td>${producer.proId }</td>
				<td>${producer.proCode }</td>
				<td>${producer.proName }</td>
				<td>${producer.proAddress }</td>
				<td>${producer.proPhone }</td>
				
				<td style="width:140px;">
						<a href="${pageContext.request.contextPath}/producer/producerUpdUI.do?producerId=${producer.proId}" class="btn btn-warning btn-xs">修改</a>
						<a href="${pageContext.request.contextPath}/producer/producerDel.do?producerId=${producer.proId}" class="btn btn-danger btn-xs">删除</a>
					
				</td>
			</tr>
		</producer:forEach>		
	</tbody>
			<tr >
				<td colspan="5">
					<a href="javascript:goPage(1)">首页</a>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<a href="javascript:goPage(${requestScope.map.page.currentPage-1 })">上一页</a>
					&nbsp; &nbsp;
					<!-- 当前页 -->
					当前页：	
					<producer:forEach begin="1" end="${requestScope.map.page.totalPages}" var="i">
						<producer:choose>
							<producer:when test="${requestScope.map.page.currentPage==i}">
							<!-- 如果是当前页码，就显示页码 -->
								${i}
							</producer:when>
							<producer:otherwise>
							<!-- 如果不是就超链接 -->
							<a href="javascript:goPage(${i})">${i}</a>
							</producer:otherwise>
						</producer:choose>
					</producer:forEach>
					&nbsp; &nbsp;
					<!-- 页面显示条目数 -->
					
					页面显示条目数：
					<select name="pageSize" onchange="goPage(1)">
						<option value="3">3</option>
						<option value="4">4</option>
						<option value="5">5</option>
					</select>
					
					&nbsp;&nbsp;
					<a href="javascript:goPage(${requestScope.map.page.currentPage+1 })">下一页</a>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<a href="javascript:goPage(${requestScope.map.page.totalPages })">尾页</a>
					
					&nbsp;&nbsp;&nbsp;&nbsp;
					当前页：${requestScope.map.page.currentPage }
					&nbsp;&nbsp;&nbsp;&nbsp;
					页面显示条目数：${requestScope.map.page.pageSize }
					&nbsp;&nbsp;&nbsp;&nbsp;
					总条目数：${requestScope.map.page.totalCount }
					&nbsp;&nbsp;&nbsp;&nbsp;
					总页码数：${requestScope.map.page.totalPages }
				</td>			
			</tr>
</table>
	<input type="hidden" name="currentPage" value="1">
</form>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.12.3.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script type="text/javascript">
	function goPage(cutPage){
		producerForm.currentPage.value=cutPage;
		producerForm.submit();
	}
$("[name=pageSize] option[value=${requestScope.map.page.pageSize}]").attr("selected","selected");
</script>
</body>
</html>
