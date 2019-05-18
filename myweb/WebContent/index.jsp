<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>验证码</title>
  </head>
  <body>
  <img id="codeImg" src="/AuthCode" onclick="refreshCode()">
  </body>
<script type="text/javascript">
  function refreshCode() {
    var codeImg = document.getElementById("codeImg");
    var d = new Date();
    codeImg.src = "/AuthCode?s="+d;
  }
</script>
</html>