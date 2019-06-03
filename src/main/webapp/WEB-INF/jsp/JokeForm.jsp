<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<meta charset="utf-8">
<meta content="width=device-width, initial-scale=1, shrink-to-fit=no"
	name="viewport">
<link crossorigin="anonymous"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	rel="stylesheet">
<title>Add Employee</title>
<script>
	function myFunction() {
		var content = document.getElementById("content").value;
		var category = document.getElementById("category").value;
		if (content.length == 0 && category != "NONE") {
			alert("Content is empty");
		}
		if(content.length > 0 && category == "NONE"){
			alert("Category is not selected");
		}
		if(content.length == 0 && category == "NONE"){
			alert("Content and Category can't be empty");
		}
		
	}
</script>
</head>

<jsp:include page="menupage.jsp" />
<body>
	<h3>Add New Joke</h3>

	<div id="JokeForm">
		<form:form action="/new" method="POST" modelAttribute="JokeForm"
			onsubmit="myFunction()">
			<p>
				<label>Content</label>
				<form:textarea path="content" name="content" id="content" />
			</p>
			<p>
				<label>Enter Category</label>
				<form:select path="category" name="category" id="category">
					<form:option value="NONE">--SELECT--</form:option>
					<form:options items="${listOfCategories}"></form:options>
				</form:select>
			</p>
			<input type="SUBMIT" value="Submit" />

		</form:form>

	</div>

</body>

</html>
<%
	String content = request.getParameter("content");
	String category = request.getParameter("category");
	String host = "jdbc:postgresql://localhost/postgres";
	Connection conn = null;
	PreparedStatement ps = null;
	Class.forName("org.postgresql.Driver").newInstance();

	if (content != null && !content.isEmpty() && category != null && !category.matches("NONE")) {
		try {
			conn = DriverManager.getConnection(host, "postgres", "sammir");
			String query = "Insert into joke(content,category) values(?,?)";
			ps = conn.prepareStatement(query);
			ps.setString(1, content);
			ps.setString(2, category);
			ps.executeUpdate();

		} catch (Exception ex) {
			out.println(ex);
			out.println("Your Connection Failed");
		}
	}
%>