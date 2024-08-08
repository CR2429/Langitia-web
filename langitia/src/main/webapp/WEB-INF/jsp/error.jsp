<%@ page contentType="text/html"  pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Page d'erreur</title>
</head>
<body>
<h1>Attention, une erreur s’est produite dans la ressource demandée. Veuillez contacter le support technique. </h1>
<h2>Message : ${message}</h2>
<h2>Cause : ${error}</h2>
<h2>Date :${timestamp}</h2>
<h2>Code : ${status}</h2>
<h2>URL : ${path}</h2>


<hr/> <a href="/"> allez à l'acceuil</a>

</body>
</html>
