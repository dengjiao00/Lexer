<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Lexer</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">
<style>
.register-box {
	position: relative;
	display: -ms-flexbox;
	display: flex;
	-ms-flex-direction: column;
	flex-direction: column;
	width: 100%;
	pointer-events: auto;
	background-color: #fff;
	background-clip: padding-box;
	border: 1px solid rgba(0, 0, 0, .2);
	border-radius: 1.1rem;
	outline: 0;
	max-width: 500px;
	padding: 20px;
	margin-top: 100px;
}

.register-title {
	padding-bottom: 20px;
	font-family: webfont !important;
}

.register-btn {
	border-radius: 50px;
}
</style>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-md-6 col-md-offset-3">
				<div class="register-box">
					<div class="register-title">
						<h1 class="text-center">Lexer</h1>
					</div>
					<form action="AddOutputServlet" method="get" >
						<div class="form-group">
							<span class="help-block has-error"></span>						
							选择输入文件： <select name="name_in" required class="form-control">
								<c:forEach var="ins" items="${sessionScope.inlist}">
									<option value="${ins.name_in}">${ins.name_in}</option>
								</c:forEach>
							</select>
						</div>
						<div class="form-group" id="d1">
							<input type="text" class="form-control" name="name_out"
								id="name_out" placeholder="输出文件名" onblur="checkNum()"
								onfocus="focusNum(this)" required> <span
								class="help-block " id="s1"></span>
						</div>
						<div class="form-group">
							<button type="submit"
								class="btn btn-block btn-primary register-btn">提交</button>
						</div>
					</form>

				</div>
			</div>
		</div>
	</div>

</body>
<!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
<script
	src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
<!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"
	integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
	crossorigin="anonymous">
	
</script>
<script>
	function checkNum() {
		var name = document.getElementById("name_out");
		var n = name.value;
		var regl = /^[\u4E00-\u9FA5a-zA-Z0-9_]{2,10}$/;
		var d0 = document.getElementById("d1");
		var s0 = document.getElementById("s1");
		if (!regl.test(n)) {
			alert("输出文件名应为汉字、英文字母、数字或下划线下划线组成，2-10位");
			flag1 = false;
		} else if (n.length < 2) {
			alert("输出文件名至少为两位！");
			flag1 = false;

		} else {
			flag1 = true;
		}
	}
	function focusNum(obj) {
		obj.select();
		var d1 = document.getElementById("d1");
		var s1 = document.getElementById("s1");
		d1.className = "form-group";
		s1.className = "";
	}
</script>
</html>