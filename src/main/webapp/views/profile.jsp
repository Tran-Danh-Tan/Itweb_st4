<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Profile</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"
	integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js"
	integrity="sha384-0pUGZvbkm6XF6gxjEnlmuGrJXVbNuzT9qBBavbLwCsOGabYfZo0T0to5eqruptLy"
	crossorigin="anonymous"></script>
</head>
<body>
<div id="wrapper">
	<div class="container">
		<div class="row justify-content-around">
			<form action="${pageContext.request.contextPath}/profile" method="POST" class="col-md-6 bg-light p-3" enctype="multipart/form-data">
				<c:if test="${not empty message}">
                      <div class="alert alert-success">${message}</div>
               	</c:if>
                <c:if test="${not empty error}">
                      <div class="alert alert-danger">${error}</div>
                </c:if>
				<c:choose>
					<c:when test="${empty sessionScope.username}">
						<h3>Click <a href="${pageContext.request.contextPath}/login"><b>here</b></a> to login</h3>
					</c:when>
					<c:otherwise>
						<section>
						<div class="form-group">
                               <label for="images">Profile Image</label>
                               <input type="file" name="images" id="images" class="form-control">
                         <c:if test="${not empty requestScope.user.images}">
							<div style="display: flex; justify-content: center;">
								<img src="${pageContext.request.contextPath}/${requestScope.user.images}"alt="Profile Image" style="max-width: 200px; margin-top: 10px;">
							</div>
						</c:if>
                               
                         </div>
						</section>
						<section>
							<div class="form-group">
								<label for="fullname">Họ tên</label> 
								<input type="text" name="fullname" id="fullname" class="form-control" value = "${requestScope.user.fullName}">
							</div>
						</section>
						<section>
							<div class="form-group">
								<label for="email">Email</label> 
								<input type="email" name="email" id="email" class="form-control" placeholder="email@gmail.com" value = "${requestScope.user.email}">
							</div>
						</section>
						<section>
							<div class="form-group">
								<label for="phone">Số điện thoại</label> 
								<input type="text" name="phone" id="phone" class="form-control" value = "${requestScope.user.phone}">
							</div>
						</section>		
						<br>
						<input type="submit" value = "Save" class="btn-primary btn btn-block" style = "width: 100%;">
					</c:otherwise>
				</c:choose>
				</form>
			</div>
		</div>
	</div>
</body>
</html>