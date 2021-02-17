<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title><sitemesh:write property='title'/></title>
<sitemesh:write property='head'/>
</head>
<body>
<%@ include file="/WEB-INF/includes/navigation.jsp"%>
<sitemesh:write property='body'/>
<%@ include file="/WEB-INF/includes/footer.jsp"%>
</body>
</html>