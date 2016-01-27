<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- 是否支持EL表达式，默认不支持 -->
<%@ page isELIgnored="false"%>
<!-- To add jstl lib -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<!-- SpringMvc 处理静态资源
	1. 为什么会有这样的问题?
		优雅的REST风格的资源URL 不希望带.html或.do等后缀
		若将DispatcherServlet 请求映射配置为/，则SpringMVC 将捕获
		WEB容器的所有请求；包括静态资源的请求，SpringMVC会将他们当
		成一切找不到对应处理器将导致错误。
	2. 解决
		在SpringMVC 的配置文件配置<mvc:default servlet-handler />

 -->
<!-- To import jQuery -->
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery.min.js"></script>
<script type="text/javascript">
	$(function() {
		//alert("AAAAAAAAAAAAAAAAAAAAA");
		// the delete href click
		$(".delete").click(function() {
			//To get the class's href value
			var href = $(this).attr("href");

			//To set the action attr's value and submit the form
			$(".delete-form").attr("action", href).submit();
			return false;
		});
	});
</script>
</head>
<body>

	<!-- The DELETE form -->
	<form action="" method="POST" class="delete-form">
		<input type="hidden" name="_method" value="DELETE" />
	</form>

	<!-- The EDIT form -->
	<form action="" method="POST" class="edit-form">
		<input type="hidden" name="_method" value="PUT" />
	</form>
	<c:if test="${empty requestScope.employees }">
		没有任何员工信息.
	</c:if>
	<c:if test="${!empty  requestScope.employees}">
		<table border="1px" cellpadding="10" cellspacing="0">
			<tr>
				<th>ID</th>
				<th>LastName</th>
				<th>Email</th>
				<th>Gender</th>
				<th>DepartmentId</th>
				<th>Edit</th>
				<th>Delete</th>
			</tr>
			<c:forEach items="${requestScope.employees }" var="emp">
				<tr>
					<td>${emp.id }</td>
					<td>${emp.lastName }</td>
					<td>${emp.email }</td>
					<td>${emp.gender }</td>
					<td>${emp['department'].id }</td>

					<!-- class is edit -->
					<td><a href="emp/${emp.id }" class="edit">Edit</a></td>

					<!-- class is delete -->
					<td><a href="emp/${emp.id }" class="delete">Delete</a></td>
				</tr>
			</c:forEach>
		</table>
	</c:if>

	<br />
	<!-- To add emp -->
	<a href="emp">Add Emp</a>
</body>
</html>