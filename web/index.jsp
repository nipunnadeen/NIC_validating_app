<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 7/21/2021
  Time: 1:18 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootswatch/4.5.2/cerulean/bootstrap.min.css"
          integrity="sha384-3fdgwJw17Bi87e1QQ4fsLn4rUFqWw//KU0g8TvV6quvahISRewev6/EocKNuJmEw"
          crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
            integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx"
            crossorigin="anonymous"></script>
    <title>NIC Application</title>
</head>
<%--<script>--%>
<%--jQuery("#submit-btn").click(function(e)){--%>
<%--e.preventDefault();--%>
<%--jQuery('#first').hide();--%>
<%--jQuery('#show').show();--%>
<%--}--%>
<%--</script>--%>
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
      <a class="navbar-brand" style="margin-left: 40%">NIC Validating Web Application</a>
</nav><br>
<div align="center">
    <div class="card" style="width: 80%;padding: 10px;height: 40%;">
        <div class="card-body">
            <h4 class="card-title"><strong>Enter User Details</strong></h4>
            <form action="userDetails" method="post">
                <p>
                    <label>
                        <input type="text" name="id" hidden value=${id}>
                    </label>
                    <label>Full Name
                        <input type="text" name="fName" required value=${fullname}>
                    </label>
                    <label>Address
                        <input type="text" name="address" required value=${address}>
                    </label>
                    <label>Nationality
                        <input type="text" name="nationality" required value=${nationality}>
                    </label>
                    <label>NIC Number
                        <input type="text" name="nic" required value=${nicnumber}>
                    </label>
                </p>
                <p>
                    <button type="submit" class="btn btn-primary">Send</button>
                </p>
                <h5 style="color: red">${userFailResponse}</h5>
                <h5 style="color: yellowgreen">${userSuccessResponse}</h5>
            </form>
            <h4 class="card-title"><strong>Enter Your NIC number to update your personal details</strong></h4>
            <form action="userDetails" method="get">
                <p>
                    <label>NIC Number
                        <input type="text" name="nic" required/>
                    </label>
                </p>
                <p>
                    <button type="submit" class="btn btn-primary">Find</button>
                </p>
                <h5 style="color: red">${NICFailResponse}</h5>
            </form>
        </div>
    </div>
    <div class="card" style="width: 80%;padding: 10px;height: 40%;">
        <div class="card-body">
            <h4 class="card-title"><strong>Enter Your NIC number to find birthday, age and gender</strong></h4>
            <form action="nicDetails" method="post">
                <p>
                    <label>NIC Number
                        <input type="text" name="nic" required/>
                    </label>
                </p>
                <p>
                    <button type="submit" class="btn btn-primary">Find Results</button>
                </p>
                <h5 style="color: red">${NICFindFailResponse}</h5>
            </form>
            <h5>Birthday:- ${birthDay}</h5>
            <h5>Age:- ${age}</h5>
            <h5>Gender:- ${gender}</h5>
        </div>
    </div>
</div>
</body>
</html>
