<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="outs" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath}/css/bootstrap.min.css" type="text/css" rel="stylesheet"/>
</head>
<body>
<form action="${pageContext.request.contextPath }/outstock/out.do" method="post" name="outsForm">
	<div style="width:1000px;margin:0 auto;">
		出库单编号：<input type="text" name="outsCode" value="${requestScope.map.outsCode}">
		<input type="submit" value="查询" class="btn btn-primary">
	</div>
	<div style="text-align:right;width:1000px;margin:10px auto">
		<a  class="btn btn-success" href="outsAdd.jsp">增加</a>
	</div>
	<table class="table table-striped table-striped table-bordered table-hover" id="outsTable" style="width:1100px;margin: 20px auto">
	<caption style="text-align: center;width:1000px;margin:auto;"><h3>出库表</h3></caption>
	<thead>
		<tr>
			<th>出库id</th>
			<th>出库编号</th>
			<th>出库日期</th>
			<th>出库数量</th>
			<th>出库状态</th>
			<th>出库商品</th>
			<th>办理员工</th>
			<th>备注</th>
		</tr>
	</thead>
	<tbody>
		<outs:forEach items="${requestScope.page.li }" var="outs" >
			<tr>
				<td>${outs.outstockId }</td>
				<td>${outs.outstockCode }</td>
				<td>${outs.outstockDate }</td>
				<td>${outs.outstockCount }</td>
				<td>${outs.outstockStatus }</td>
				<td>${outs.goods.goodsName }</td>
				<td>${outs.staff.staffName }</td>
				<td>${outs.outstockRemarks }</td>
				
				<td style="width:140px;">
					<outs:if test="${outs.outstockRemarks=='未确认'}">
						<a href="${pageContext.request.contextPath}/outstock/outsConf.do?outsId=${outs.outstockId}&outsCount=${outs.outstockCount}&goodsId=${outs.goodsId}" class="btn btn-success btn-xs">确认</a>
						<a href="${pageContext.request.contextPath}/outstock/outsDel.do?outsId=${outs.outstockId}" class="btn btn-danger btn-xs">删除</a>
						<a href="${pageContext.request.contextPath}/outstock/outsUpdUI.do?outsId=${outs.outstockId}" class="btn btn-warning btn-xs">修改</a>
					</outs:if>
					
				</td>
			</tr>
		</outs:forEach>		
	</tbody>
			<tr >
				<td colspan="11">
					<a href="javascript:goPage(1)">首页</a>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<a href="javascript:goPage(${requestScope.page.currentPage-1 })">上一页</a>
					&nbsp; &nbsp;
					<!-- 当前页 -->
					当前页：	
					<outs:forEach begin="1" end="${requestScope.page.totalPages}" var="i">
						<outs:choose>
							<outs:when test="${requestScope.page.currentPage==i}">
							<!-- 如果是当前页码，就显示页码 -->
								${i}
							</outs:when>
							<outs:otherwise>
							<!-- 如果不是就超链接 -->
							<a href="javascript:goPage(${i})">${i}</a>
							</outs:otherwise>
						</outs:choose>
					</outs:forEach>
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
		outsForm.currentPage.value=cutPage;
		outsForm.submit();
	}
$("[name=pageSize] option[value=${requestScope.page.pageSize}]").attr("selected","selected");
</script>
</body>
</html>
