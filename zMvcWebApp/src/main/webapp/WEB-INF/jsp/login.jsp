<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c' %>
    
<c:url value="/login/signin" var="loginUrl"/>
<c:url value="/login/menuLinks" var="menuLinks"/>
<c:url value="/login/authenticated" var="authenticatedLink"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title></title>
	<link rel='stylesheet' type='text/css' media='screen' href='<c:url value="./resources/css/style.css"/>'/>
	<script type='text/javascript' src='<c:url value="./resources/js/custom.js"/>'></script>
	<script type='text/javascript' src='<c:url value="./resources/js/jquery.js"/>'></script>
	

	<title>User Records</title>
	
	<script type='text/javascript'>
	$(function() {
		// init
		urlHolder.login = '${loginUrl}';
		urlHolder.menuLinks = '${menuLinks}';
		urlHolder.authenticatedLink = '${authenticatedLink}';
		
		toggleLogin('show');
		
		$('#loginBtn').click(function() { 
			submitLogin();
		});
	});
	</script>

</head>


<body onload="javascript:document.forms[0].username.focus()">
<div id='loginForm'>
<form>
	<fieldset>
		<label for='username'>User Name</label><input type='text' id='username'/></br>
		<label for='newPassword'>Password</label><input type='password' id='password'/></br>
		
	</fieldset>
	<input type='button' value='Login' id='loginBtn' />
</form>
</div>

<table id='tableLinks'>
	<caption></caption>
	<thead>
		<tr>
			<th>Links</th>
		</tr>
	</thead>
</table>

</body>
</html>