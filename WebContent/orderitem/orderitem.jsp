<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="order" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath}/css/bootstrap.min.css" type="text/css" rel="stylesheet"/>
</head>
<body>
<form action="${pageContext.request.contextPath }/orderitem/oi.do" method="post" name="orderForm">
	<div style="width:1000px;margin:0 auto;">
		订单编号：<input type="text" name="orderCode" value="${requestScope.map.orderCode}">
		<input type="submit" value="查询" class="btn btn-primary">
	</div>
	<table class="table table-striped table-striped table-bordered table-hover" id="orderTable" style="width:1100px;margin: 20px auto">
	<caption style="text-align: center;width:1000px;margin:auto;"><h3>订单明细表</h3></caption>
	<thead>
		<tr>
			<th>订单明细id</th>
			<th>订单编号</th>
			<th>订单明细价格</th>
			<th>订单明细数量</th>
			<th>商品名称</th>
		</tr>
	</thead>
	<tbody>
		<order:forEach items="${requestScope.page.li }" var="order" >
			<tr>
				<td>${order.orderItemId }</td>
				<td>${order.orderCode }</td>
				<td>${order.orderItemPrice }</td>
				<td>${order.orderItemCount }</td>
				<td>${order.goods.goodsName }</td>
			</tr>
		</order:forEach>		
	</tbody>
			<tr >
				<td colspan="11">
					<a href="javascript:goPage(1)">首页</a>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<a href="javascript:goPage(${requestScope.page.currentPage-1 })">上一页</a>
					&nbsp; &nbsp;
					<!-- 当前页 -->
					当前页：	
					<order:forEach begin="1" end="${requestScope.page.totalPages}" var="i">
						<order:choose>
							<order:when test="${requestScope.page.currentPage==i}">
							<!-- 如果是当前页码，就显示页码 -->
								${i}
							</order:when>
							<order:otherwise>
							<!-- 如果不是就超链接 -->
							<a href="javascript:goPage(${i})">${i}</a>
							</order:otherwise>
						</order:choose>
					</order:forEach>
					&nbsp; &nbsp;
					<!-- 页面显示条目数 -->
					
					页面显示条目数：
					<select name="pageSize" onchange="goPage(1)">
						<option value="5">5</option>
						<option value="10">10</option>
						<option value="15">15</option>
					</select>
					
					&nbsp;&nbsp;
					<a href="javascript:goPage(${requestScope.page.currentPage+1 })">下一页</a>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<a href="javascript:goPage(${requestScope.page.totalPages })">尾页</a>
					
					&nbsp;&nbsp;&nbsp;&nbsp;
					当前页：${requestScope.page.currentPage }
					&nbsp;&nbsp;&nbsp;&nbsp;
					页面显示条目数：${requestScope.page.pageSize }
					&nbsp;&nbsp;&nbsp;&nbsp;
					总条目数：${requestScope.page.totalCount }
					&nbsp;&nbsp;&nbsp;&nbsp;
					总页码数：${requestScope.page.totalPages }
				</td>			
			</tr>
</table>
	<input type="hidden" name="currentPage" value="1">
</form>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.12.3.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script type="text/javascript">
	function goPage(cutPage){
		orderForm.currentPage.value=cutPage;
		orderForm.submit();
	}
$("[name=pageSize] option[value=${requestScope.page.pageSize}]").attr("selected","selected");
</script>
</body>
</html>
