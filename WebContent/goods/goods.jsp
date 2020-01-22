<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="goods" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath}/css/bootstrap.min.css" type="text/css" rel="stylesheet"/>
</head>
<body>
<form action="${pageContext.request.contextPath }/goods/showgoods.do" method="post" name="goodsForm">
	<div style="width:1000px;margin:0 auto;">
		商品编号：<input type="text" name="goodsCode" value="${requestScope.map.gscode}">
		商品名称：<input type="text" name="goodsName" value="${requestScope.map.gsname}">
		<input type="submit" value="查询" class="btn btn-primary">
	</div>
	<div style="text-align:right;width:1000px;margin:10px auto">
		<a  class="btn btn-success" href="goodsAdd.jsp">增加</a>
	</div>
	<table class="table table-striped table-striped table-bordered table-hover" id="goodsTable" style="width:1100px;margin: 20px auto">
	<caption style="text-align: center;width:1000px;margin:auto;"><h3>产品信息表</h3></caption>
	<thead>
		<tr>
			<th>产品id</th>
			<th>产品编号</th>
			<th>产品名称</th>
			<th>生产日期</th>
			<th>产品价格</th>
			<th>产品数量</th>
			<th>厂家名称</th>
			<th>类型名称</th>
			<th>商品图片</th>
			<th>备注</th>
		</tr>
	</thead>
	<tbody>
		<goods:forEach items="${requestScope.map.page.li }" var="goods" >
			<tr>
				<td>${goods.goodsId }</td>
				<td>${goods.goodsCode }</td>
				<td>${goods.goodsName }</td>
				<td>${goods.goodsData }</td>
				<td>${goods.goodsPrice }</td>
				<td>${goods.goodsCount }</td>
				<td>${goods.pro.proName }</td>
				<td>${goods.gsd.gsName }</td>
				<td>${goods.goodsImg }</td>
				<td>${goods.goodsRemarks }</td>
				
				<td style="width:140px;">
					<goods:if test="${goods.goodsRemarks=='未确认'}">
						<a href="${pageContext.request.contextPath}/goods/goodsConf.do?goodsId=${goods.goodsId}" class="btn btn-success btn-xs">确认</a>
					</goods:if>
						<a href="${pageContext.request.contextPath}/goods/goodsUpdUI.do?goodsId=${goods.goodsId}" class="btn btn-warning btn-xs">修改</a>
						<a href="${pageContext.request.contextPath}/goods/goodsDel.do?goodsId=${goods.goodsId}" class="btn btn-danger btn-xs">删除</a>
					
				</td>
			</tr>
		</goods:forEach>		
	</tbody>
			<tr >
				<td colspan="11">
					<a href="javascript:goPage(1)">首页</a>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<a href="javascript:goPage(${requestScope.map.page.currentPage-1 })">上一页</a>
					&nbsp; &nbsp;
					<!-- 当前页 -->
					当前页：	
					<goods:forEach begin="1" end="${requestScope.map.page.totalPages}" var="i">
						<goods:choose>
							<goods:when test="${requestScope.map.page.currentPage==i}">
							<!-- 如果是当前页码，就显示页码 -->
								${i}
							</goods:when>
							<goods:otherwise>
							<!-- 如果不是就超链接 -->
							<a href="javascript:goPage(${i})">${i}</a>
							</goods:otherwise>
						</goods:choose>
					</goods:forEach>
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
		goodsForm.currentPage.value=cutPage;
		goodsForm.submit();
	}
$("[name=pageSize] option[value=${requestScope.map.page.pageSize}]").attr("selected","selected");
</script>
</body>
</html>
