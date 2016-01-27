<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- 是否支持EL表达式，默认不支持 -->
<%@ page isELIgnored="false"%>
<!-- To add jstl lib -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- To add springframework's tag -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<!-- To test the convert -->
	<form action="${pageContext.request.contextPath }/testConverter"
		method="POST">
		<!-- Test Case is: GG-gg@qq.com-boy-105 -->
		style: lastName-email-gender-department.id <br /> <input type="text"
			name="employee" /><input type="submit" value="Submit" />
	</form>
	<br />
	<br />
	<!-- 
		1. 为什么使用form标签？
			可以更快速的开发出表单页面，而且可以更方便的进行表单值的回显。
			
		2. 可以通过modelAttribute属性指定绑定的模型属性，若没有指定该属性，则
			默认从request对象中读取command的表单bean，如果该属性值不存在，则
			会发生错误。
	
	 -->
	<form:form action="${pageContext.request.contextPath }/emp"
		method="POST" modelAttribute="employee">
		
		所有错误消息：<br />
		<form:errors path="*"></form:errors>
		<br />

		<!-- path is the object's name attribute -->
		<c:if test="${employee.id == null }">
			LastName:<form:input path="lastName" />
			<form:errors path="lastName"></form:errors>
		</c:if>
		<c:if test="${employee.id != null }">
			<form:hidden path="id" />
			<!-- Change POST to PUT request -->
			<input type="hidden" name="_method" value="PUT" />
		</c:if>
		<br />
		Email:<form:input path="email" />
		<form:errors path="email"></form:errors>
		<br />
		<%
			Map<String, String> genders = new HashMap<String, String>();
				genders.put("boy", "boy");
				genders.put("girl", "girl");

				request.setAttribute("genders", genders);
		%>

		<!-- delimiter: the split -->
		Gender:<form:radiobuttons path="gender" items="${genders }" />
		<br />

		<!-- 
			itemLabel and itemValue is the department's attribute. 
			Items is the list var
		-->
		Department:<form:select path="department.id" items="${depts }"
			itemLabel="deptName" itemValue="id"></form:select>
		<br />

		birth:<form:input path="birth" />
		<form:errors path="birth"></form:errors>
		<br />

		salary:<form:input path="salary" />
		<form:errors path="salary"></form:errors>
		<br />

		<input type="submit" value="Submit" />
	</form:form>
</body>
</html>