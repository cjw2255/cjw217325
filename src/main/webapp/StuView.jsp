<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
      <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title> 
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css"> 
<style type="text/css">
	th{
		text-align: center;
	}
</style>
</head>
<body>
	<form action="" method="post" name="tj">
		<table class="table table-hover table-striped table-bordered table-condensed" style="width:500px;margin:100px auto">
			<thead>
				<tr class="info">
					<th>学生Id</th>
					<th>学生姓名</th>
					<th>学生年龄</th>
					<th>学生性别</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${requestScope.student.data }" var="student">
					<tr>
						<td>${student.sid }</td>
						<td>${student.sname }</td>
						<td>${student.sage }</td>
						<td>${student.sex }</td>
					</tr>
				</c:forEach>
				<tr>
					<td colspan="4" align="center">
						<a href="javascript:goPage(1)">首页</a>
						<a href="javascript:goPage(${requestScope.student.currentPage-1})">上一页</a>
						<c:forEach begin="1" end="${requestScope.student.pages }" var="i">
							<c:choose>
								<c:when test="${requestScope.student.currentPage==i }">
									${i }
								</c:when>
								<c:otherwise>
									<a href="javascript:goPage(${i})">${i}</a>
								</c:otherwise>
							</c:choose>
						</c:forEach>
						<a href="javascript:goPage(${requestScope.student.currentPage+1})">下一页</a>
						<a href="javascript:goPage(${requestScope.student.pages})">尾页</a>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<select name="pageSize" onchange="goPage(1)">
							<option value="5">5</option>
							<option value="8">8</option>
							<option value="10">10</option>
						</select>
						&nbsp;&nbsp;&nbsp;&nbsp;
						共${requestScope.student.pages }页，共${requestScope.student.totalCount }条
						&nbsp;&nbsp;&nbsp;&nbsp;
						当前页为第${requestScope.student.currentPage }页
					</td>
				</tr>
			</tbody>
		</table>
		<input type="hidden" name="currentPage" value="1"/>
	</form>
<script type="text/javascript" src="js/jquery-1.12.3.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript">
	function goPage(currentParam){
		tj.currentPage.value = currentParam;
		tj.submit();
	}
	$("[name=pageSize] option[value=${requestScope.student.pageSize}]").attr("selected","selected");
</script>
</body>
</html>