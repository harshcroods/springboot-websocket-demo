<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<meta name="Description" content="Enter your description here"/>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.5.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.0/css/all.min.css">
<title>Socket Demo</title>
</head>
<body>
   <div id="main-content" class="container">
      <div class="row text-center">
        <h2>WebChat WebSocket</h2>
      </div>

      <br/>

      <div class="row text-center">
        <div class="col-md-4">
          <label for="webchat_username">Username:</label>
          <input type="text" id="webchat_username" placeholder="Put your username here..."/>
        </div>
        <div class="col-md-1">
          <input type="button" class="btn" id="webchat_connect" value="Connect"/>
        </div>
        <div class="col-md-1">
          <input type="button" class="btn" id="webchat_disconnect" value="Disconnect" disabled="true"/>
        </div>
      </div>

      <div class="row">
        <div class="row text-center"><h2>Connected Users List</h2></div>
        <div id="chat_user_list" class="row"></div>
      </div>
    
      <div id="chat_list" class="row"></div>
      <div id="alerts"></div>
    </div>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.5.0/sockjs.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.1/umd/popper.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script src="<%=request.getContextPath()%>/script/main.js"></script>
</body>
</html>