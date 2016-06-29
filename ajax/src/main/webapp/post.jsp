<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>doucument</title>
</head>
<body>
<button id="getbtn">发出get Ajax请求</button>
<button id="postbtn">发出post Ajax请求</button>
<script src="/static/js/ajax.js"></script>
<script>
    (function(){
        function sayHello(arg1,arg2){
            alert(arguments.length);
        }

        document.querySelector("#getbtn").onclick = function(){
            Ajax.getText("/ajax",function(reqeult){
                alert("Hi:"+ result)
            })
        }

        document.querySelector("#postbtn").onclick = function(){
            Ajax.postText("/ajax",{name:"Tom",adress:"USA"},function(result){
                alert("Post:"+result)
            })
        }
    })();
</script>
</body>
</html>
