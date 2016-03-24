<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	window.onload = function() {
		//1. 获取a节点，并为其添加onclick 响应函数
		document.getElementsByTagName("a")[0].onclick = function() {
			//3. 创建一个XMLHttpRequest 对象
			var request = new XMLHttpRequest();

			//4. 准备发送请求数据
			//var url = this.href + "?time=" + new Date();//达到禁用缓存的目的，每次都是新的url(与时间有关)
			var url = this.href;
			var method = "GET";

			//5. 调用XMLHttpRequest 对象的open方法
			request.open(method, url);

			//6. 调用XMLHttpRequest 对象的send方法
			request.send(null);

			//7. 为XMLHttpRequest 对象添加onreadystatechange 响应函数
			request.onreadystatechange = function() {
				//8. 判断响应是否完成： XMLHttpRequest 对象的readyState 属性值为4
				if (request.readyState == 4) {
					//9. 再判断响应是否可用： XMLHttpRequest 对象的status属性值为200 或 304
					if (request.status == 200 || request.status == 304) {
						//10. 打印响应结果 responseText
						alert(request.responseText);
					}
				}
			}

			//2. 取消a 节点的默认行为
			return false;
		}
	}
</script>
</head>
<body>
	<a href="node.txt">Ajax</a>
	<br />
	<br />

	<a href="${pageContext.request.contextPath }/send-html/send-html.jsp">Send
		HTML</a>
	<br />
	<br />

	<a href="${pageContext.request.contextPath }/send-xml/send-xml.jsp">Send
		XML</a>
	<br />
	<br />

	<a href="${pageContext.request.contextPath }/json/json.jsp">Json</a>
	<br />
	<br />

	<a href="${pageContext.request.contextPath }/json/send-json.jsp">Send
		Json</a>
	<br />
	<br />

	<a href="${pageContext.request.contextPath }/jQuery/jQuery-test.jsp">JQuery</a>
	<br />
	<br />

</body>
</html>