<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- 模拟用户请求此处请求一个id -->
	<form action="${pageContext.request.contextPath }/asyncAnnotationServlet" method="post">
		Id:<input type="text" name="id" />
		<br /><br />
		Protocol:<input type="text" name="protocol" value="SOAP" style="width: 200px;" />&nbsp;&nbsp;REST
		<br /><br />
		URL:<input type="text" name="url" style="width: 400px;" value="http://localhost:8181/spring-ws-web/userService?wsdl" />
		&nbsp;&nbsp;REST:&nbsp;http://localhost:8080/spring-ws-web/userServices
		<br /><br />
		<input type="submit" value="Submit" />
	</form>
</body>
</html>