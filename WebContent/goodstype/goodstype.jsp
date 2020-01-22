<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="goodstype" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath}/css/bootstrap.min.css" type="text/css" rel="stylesheet"/>
</head>
<body>
<form action="${pageContext.request.contextPath }/goodstype/gt.do" method="post" name="goodsForm">
	<div style="width:1000px;margin:0 auto;">
		商品类型编号：<input type="text" name="gsCode" value="${requestScope.map.gscode}">
		商品类型名称：<input type="text" name="gsName" value="${requestScope.map.gsname}">
		<input type="submit" value="查询" class="btn btn-primary">
	</div>
	<div style="text-align:right;width:1000px;margin:10px auto">
		<a  class="btn btn-success" href="goodsAdd.jsp">增加</a>
	</div>
	<table class="table table-striped table-striped table-bordered table-hover" id="goodsTable" style="width:1100px;margin: 20px auto">
	<caption style="text-align: center;width:1000px;margin:auto;"><h3>产品类型表</h3></caption>
	<thead>
		<tr>
			<th>产品类型id</th>
			<th>产品类型编号</th>
			<th>产品类型名称</th>
			<th>备注</th>
		</tr>
	</thead>
	<tbody>
		<goodstype:forEach items="${requestScope.map.page.li }" var="goodstype" >
			<tr>
				<td>${goodstype.gsId }</td>
				<td>${goodstype.gsCode }</td>
				<td>${goodstype.gsName }</td>
				<td>${goodstype.gsRemarks }</td>
				
				<td style="width:140px;">
					<goodstype:choose>
						<goodstype:when test="${goodstype.gsRemarks=='未确认'}">
							<a href="${pageContext.request.contextPath}/goodstype/goodstypeConf.do?goodstypeId=${goodstype.gsId}" class="btn btn-success btn-xs">确认</a>
						</goodstype:when>
						<goodstype:otherwise>
							<a href="${pageContext.request.contextPath}/goodstype/goodstypeUpdUI.do?goodstypeId=${goodstype.gsId}" class="btn btn-warning btn-xs">修改</a>
							<a href="${pageContext.request.contextPath}/goodstype/goodstypeDel.do?goodstypeId=${goodstype.gsId}" class="btn btn-danger btn-xs">删除</a>
						</goodstype:otherwise>
					</goodstype:choose>
				</td>
			</tr>
		</goodstype:forEach>		
	</tbody>
			<tr >
				<td colspan="5">
					<a href="javascript:goPage(1)">首页</a>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<a href="javascript:goPage(${requestScope.map.page.currentPage-1 })">上一页</a>
					&nbsp; &nbsp;
					<!-- 当前页 -->
					当前页：	
					<goodstype:forEach begin="1" end="${requestScope.map.page.totalPages}" var="i">
						<goodstype:choose>
							<goodstype:when test="${requestScope.map.page.currentPage==i}">
							<!-- 如果是当前页码，就显示页码 -->
								${i}
							</goodstype:when>
							<goodstype:otherwise>
							<!-- 如果不是就超链接 -->
							<a href="javascript:goPage(${i})">${i}</a>
							</goodstype:otherwise>
						</goodstype:choose>
					</goodstype:forEach>
					&nbsp; &nbsp;
					<!-- 页面显示条目数 -->
					
					页面显示条目数：
					<select name="pageSize" onchange="goPage(1)">
						<option value="2">2</option>
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
