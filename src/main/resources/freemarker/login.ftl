<html>
<head>
    <title>Welcome</title>

    <link href="css/bootstrap.css" rel="stylesheet" />
  </head>
<body>
<form class="form-inline" method="POST" action="/welcome">
    <div class="form-group">
        <label for="email">Email</label>
        <input type="text"
               class="form-control"
               id="email"
               name="email">
    </div>
    <div class="form-group">
        <label for="password">Password</label>
        <input type="password"
               class="form-control"
               id="password"
               name="password">
    </div>
    <button type="submit" class="btn btn-default">Submit</button>
</form>
<br>
<div>"Don't have an account click here"</div>
<a href="http://localhost:8080/signup"> <button  class="btn btn-default">Signup</button></a>
</body>
</html>