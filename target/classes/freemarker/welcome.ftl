<html>
<head>
    <title>Welcome</title>

    <link href="css/bootstrap.css" rel="stylesheet" />
</head>
<body>
<form class="form-inline" method="POST" action="/confirmation">
    <div class="form-group">
        <label for="date">Date</label>
        <input type="date"
               class="form-control"
               id="date"
               name="date">
    </div>
    <div class="form-group">
        <label for="source">Source</label>
        <input type="text"
               class="form-control"
               id="source"
               name="source">
    </div>
    <div class="form-group">
        <label for="destination">Destination</label>
        <input type="text"
               class="form-control"
               id="destination"
               name="destination">
    </div>
    <div class="form-group">
        <label for="trainno">Train No.</label>
        <input type="text"
               class="form-control"
               id="trainno"
               name="trainno">
    </div>
    <button type="submit" class="btn btn-default">Book</button>
</form>
</body>
</html>