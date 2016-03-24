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
		var aNodes = document.getElementsByTagName("a");
		for (var i = 0; i < aNodes.length; i++) {

			aNodes[i].onclick = function() {
				//3. 创建一个XMLHttpRequest 对象
				var request = new XMLHttpRequest();

				//4. 准备发送请求数据
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
							document.getElementById("details").innerHTML = request.responseText;
						}
					}
				}

				//2. 取消a 节点的默认行为
				return false;
			}
		}
	}
</script>
</head>
<body>
	Content:
	<ul>
		<li><a href="AAA.html">AAA</a></li>
		<li><a href="BBB.html">BBB</a></li>
		<li><a href="CCC.html">CCC</a></li>
	</ul>

	<div id="details"></div>
</body>
</html>