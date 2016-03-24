<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	var jsonObject = {
			"name":"zhangsan",
			"age":12,
			"adress":{"city":"beijing", "school":"ECJTU"},
			"teaching":function() {
				alert("JavaEE Andriod");
			}
	}
	
	alert(jsonObject.name);
	alert(jsonObject.adress.city);
	jsonObject.teaching();
	
	var jsonStr = "{'name': 'lisi'}";
	var testStr = "alert('hello val')";
	
	//eval()方法可以把字符串转化为本地json代码来执行
	eval(testStr);
	
	//把json字符串转为json对象,前加"(" 和 后加")""
	var testObj = eval("(" + jsonStr + ")");
	alert(testObj.name);
</script>
</head>
<body>

</body>
</html>