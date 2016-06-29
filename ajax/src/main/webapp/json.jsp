<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>doucument</title>
</head>
<body>
<button id="btn">Get Json</button>

<script>
    (function () {

        function createXmlHttp() {
            var xmlHttp = null;
            if (window.ActiveXObject) {
                xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
            } else {
                xmlHttp = new XMLHttpRequest();
            }
            return xmlHttp;
        }

        document.querySelector("#btn").onclick = function(){
            var xmlHttp = createXmlHttp();
            xmlHttp.open("get","/user.json",true);
            xmlHttp.onreadystatechange = function(){
                if(xmlHttp.readyState == 4){
                    if(xmlHttp.status == 200){
                        var result = xmlHttp.responseText;//获取服务器返回的Json字符串
                        //把字符串转换为json
                        var json = JSON.parse(result);
                        console.log(json.length);

                    }
                }
            };
            xmlHttp.send();
        }


        /*var array = [];
         //属性
         var obj = {
         "name":"tom",
         "age":"23"
         };
         alert(obj.name);
         alert(obj["age"]);

         var array = [
         {
         "name":"tom",
         "age":23
         },
         {
         "name":"jim",
         "age":"24"
         }
         ];

         for (var i = 0;i < array.length; i++){
         var a = array[i];
         console.log(a.name + "->" + a.age);
         }*/


    })()
</script>
</body>
</html>
