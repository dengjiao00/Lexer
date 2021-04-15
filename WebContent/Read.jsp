<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>OUTPUT-SHOW</title>
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
	max-width: 800px;
	padding: 20px;
	margin-top: 100px;
}

.register-title {
	padding-bottom: 20px;
	font-family: webfont !important;
}

.register-btn {
	border-radius: 100%;
}
</style>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-md-6 col-md-offset-3">
				<div class="register-box">
					<div class="register-title">
						<h1 class="text-center">OUTPUT-SHOW</h1>
					</div>
					<form action="" method="">
					<div class="form-group" id="d0">
							<textarea  style="width: 100%; height: 500px;"
							class="form-control" name="name_out"
								id="intext"  onblur="checkCon()"
								onfocus="focusCon(this)" readonly="readonly"><%
							String url = (String) request.getAttribute("url_out");
							java.io.BufferedReader reader = new java.io.BufferedReader(new java.io.FileReader(url));
							String s = reader.readLine();
							StringBuffer sb = new StringBuffer();
							while (s != null) {
								sb.append(s);
								s = reader.readLine();
								sb.append("\n");
							}
							out.println(sb.toString());

							reader.close();
							%></textarea>
					</form>

				</div>
			</div>
		</div>
	</div>
</body>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"
	integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
	crossorigin="anonymous"></script>
<!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
<script
	src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
<!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"></script>

</html>
