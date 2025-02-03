<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <style>
        .window {
            position: fixed;
            width: 300px;
            height: 200px;
            background: black;
            border: 2px solid white;
            box-shadow: 5px 5px 15px rgba(0, 0, 0, 0.3);
            top: 50px;
            left: 50px;
            padding: 10px;
        }
        .window-header {
            background: white;
            padding: 5px;
            color: black;
            text-align: right;
            cursor: move;
        }
        .window-body {
            padding: 10px;
            color:white;
        }
        .close-btn {
            cursor: pointer;
            font-weight: bold;
        }
    </style>
</head>
<body>
    <div id="txtWindow" class="window">
        <div class="window-header">
            <title style="color: black;">Fichier txt</title>
            <span class="close-btn" onclick="closeWindow()" style='cursor:pointer;float:right;'>X</span>
        </div>
        <div class="window-body">
            <p>${content}</p>
        </div>
        <div id="windowContainer">
        </div>
    </div>
</body>
</html>
