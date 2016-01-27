<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- 是否支持EL表达式，默认不支持 -->
<%@ page isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery.min.js"></script>
<script type="text/javascript">
	$(function() {
		$("#testJson").click(function() {
			var url = this.href;
			var args = {};
			//To send the data and return the json object
			$.post(url, args, function(data) {
				for (var i = 0; i < data.length; i++) {
					var id = data[i].id;
					var lastName = data[i].lastName;

					alert(id + " : " + lastName);
				}
			});

			return false;
		});
	});
</script>
</head>
<body>
	<a href="emps">List All Employees</a>
	<br />
	<br />
	<a href="hello">To Hello</a>
	<br />
	<br />
	<a id="testJson" href="testJson">Test Json</a>
	<br />
	<br />
	<a href="toFileUploadPage">To File Upload Page</a>
	<br />
	<br />
</body>
</html>
