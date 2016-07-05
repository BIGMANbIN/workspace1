<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>doucument</title>
</head>
<body>

<button id="btn">发出Ajax请求</button>
<script>
    (function(){

        //兼容
        var xmlHttp = null;
        function createXmlHttp(){
            //<IE9
            if(window.ActiveXObject){
                xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
            }else{
                //>=IE9和非IE9浏览器
                xmlHttp = new XMLHttpRequest();
            }
            return xmlHttp;
        }

        document.querySelector("#btn").onclick = function(){
            //1.获取函数对象
            var xmlHttp = createXmlHttp();
            //2.设置请求的URL
            xmlHttp.open("get","/ajax",true);
            //3. 发出ajax请求
            xmlHttp.send();
        }
    })();
</script>
</body>
</html>
