<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="${pageContext.request.contextPath }/scripts/jquery.min.js"></script>
<script type="text/javascript">
	$(function(){
		$("#loadTxt").click(function(){
			//使用load方法处理Ajax
			var url = this.href;
			//通过传不同参数，用时间参数来禁止页面缓存
			var args = {"time": new Date()};
			//最简单常用的方法
			$("#content").load(url, args);
			return false;
		});
		
		$("#loadHTML").click(function(){
			//使用load方法处理Ajax
			var url = this.href;
//			alert("url = " + url);
			//通过传不同参数，用时间参数来禁止页面缓存
			var args = {"time": new Date()};
			//最简单常用的方法
			$("#detail").load(url, args);
			return false;
		});
		
		$("#loadHTML2").click(function(){
			//使用load方法处理Ajax
//			var url = "${pageContext.request.contextPath }/jQuery/AAA.html";
			//空格选择器，选择部分内容，任何一个html节点都可以，用load方法加载Ajax，结果可插入html节点中
			var url = "${pageContext.request.contextPath }/jQuery/AAA.html h4";
//			alert("url = " + url);
			//通过传不同参数，用时间参数来禁止页面缓存
			var args = {"time": new Date()};
			//最简单常用的方法
			$("#detail2").load(url, args);
			return false;
		});
		
		/**
			url:
			args: JSON格式
			function: 回调函数，响应结果在data中
		*/
		$("#loadXML").click(function(){
			var url = this.href;
			var args = {"time": new Date()};
			//get 方法
			$.get(url, args, function(data){
				//从data中查找信息，并拼接出字符串
				var name = $(data).find("name").text();
				var email = $(data).find("email").text();
				var website = $(data).find("website").text();
				$("#detail3").empty()
					.append("<h2><a href='mailto:" + email + "'>" + name + "</a></h2>")
					.append("<a href='" +website +  "'>'" + website + "</a>");
			});
			return false;
		});
		$("#loadXML2").click(function(){
			var url = this.href;
			var args = {"time": new Date()};
			//post 方法
			$.post(url, args, function(data){
				//从data中查找信息，并拼接出字符串
				var name = $(data).find("name").text();
				var email = $(data).find("email").text();
				var website = $(data).find("website").text();
				$("#detail3").empty()
					.append("<h2><a href='mailto:" + email + "'>" + name + "</a></h2>")
					.append("<a href='" +website +  "'>'" + website + "</a>");
			});
			return false;
		});
		
		$("#loadXML3").click(function(){
			var url = this.href;
			var args = {"time": new Date()};
			//getJSON 方法
			$.getJSON(url, args, function(data){
				//从data中查找信息，并拼接出字符串
				var name = data.person.name;
				var email = data.person.email;
				var website = data.person.website;
				$("#detail4").empty()
					.append("<h2><a href='mailto:" + email + "'>" + name + "</a></h2>")
					.append("<a href='" +website +  "'>'" + website + "</a>");
			});
			return false;
		});
	});
	
	
</script>
</head>
<body>
	<a id="loadTxt" href="${pageContext.request.contextPath }/jQuery/helloAjax.txt">HelloAjax</a>
	<div id="content"></div>
	
	<a id="loadHTML" href="${pageContext.request.contextPath }/jQuery/AAA.html">Load HTML</a>
	<div id="detail"></div>
	
	<div id="loadHTML2">Load HTML By Div Click</div>
	<div id="detail2"></div>
	
	<a id="loadXML3" href="${pageContext.request.contextPath }/jQuery/users.xml">Load XML</a>
	<div id="detail3"></div>
	
	<a id="loadXML4" href="${pageContext.request.contextPath }/jQuery/users.xml">Load XML</a>
	<div id="detail4"></div>
</body>
</html>