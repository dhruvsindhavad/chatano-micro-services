<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Contact us</title>
<link rel="stylesheet" type="text/css" href="/resources/css/mystyle.css">
<!--link rel="stylesheet" type="text/css" href="style1.css"-->
<link rel="stylesheet" href="/resources/css/bootstrap_min_v3_3_7.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script
	src="/resources/js/bootstrap_min_v3_3_7.js"></script>
	<meta name="keywords" content="chat, stranger, anonymous, fun, with,contact, contact us">
<meta name="description" content="Anonymous stranger chat">
<meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>

	<div class="container-fluid">
		<div class="row">
			<div class="col-xs-12" style="background-color: lightblue;">ad</div>
			<div class="col-sm-9 col-xs-12">
				<div class="well well-sm">
					<form:form method="post" modelAttribute="contactus">
						<fieldset>
							<legend class="text-center">Contact us</legend>
							<c:if test="${contactus.isSubmited == true}">
								<div class="col-md-12">
									<p class="text-info">Thank you for contacting us. We will
										get back to you shortly.</p>
								</div>
							</c:if>
							<div class="form-group">
								<form:label class="col-md-3 control-label" for="name"
									path="name">Your Name</form:label>
								<div class="col-md-9">
									<form:input path="name" type="text" placeholder="Your name"
										data-error="Please enter name field." class="form-control"
										required="true"></form:input>
									<div class="help-block with-errors"></div>
								</div>
							</div>

							<!-- Email input-->
							<div class="form-group">
								<div class="col-md-3"></div>
								<div class="col-md-9">
									<small id="emailHelp" class="form-text text-muted">We'll
										never share your email with anyone else.</small>
								</div>
								<div class="col-md-3">
									<form:label class="control-label" for="email" path="email">Your
									E-mail</form:label>
								</div>
								<div class="col-md-9">
									<form:input path="email" type="email" placeholder="Your email"
										class="form-control" required="true"></form:input>
									<div class="help-block with-errors"></div>
								</div>
							</div>

							<!-- Message body -->
							<div class="form-group">
								<form:label class="col-md-3 control-label" for="description"
									path="description">Your
									message</form:label>
								<div class="col-md-9">
									<form:textarea path="description" class="form-control"
										id="message" name="message"
										placeholder="Please enter your message here..."
										data-error="Please enter your message here." rows="5"
										required="true" />
									<div class="help-block with-errors"></div>
								</div>
							</div>

							<!-- Form actions -->
							<div class="form-group">
								<div class="col-md-12 text-right">
									<form:button type="submit" class="btn btn-primary btn-lg">Submit</form:button>
									-
								</div>
							</div>
						</fieldset>
					</form:form>
				</div>
			</div>
			<div class="col-sm-3 col-xs-12 ad"
				style="background-color: lightblue;">ad</div>
		</div>
	</div>

</body>
</html>