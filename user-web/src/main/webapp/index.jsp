<head>
<jsp:directive.include
	file="/WEB-INF/jsp/prelude/include-head-meta.jspf" />
<title>homework</title>
</head>
<body>
	<div>
		<form action="/user/register" method="post">
			<p><label>用户名</label><input name="name"></p>
			<p><label>手机号</label><input name="phoneNumber"></p>
			<p><label>邮箱</label><input name="email"></p>
			<p><label>密码</label><input name="password"></p>
			<p><input type="submit" value="注册"></p>
		</form>
	</div>
</body>