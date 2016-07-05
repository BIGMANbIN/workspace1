<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>doucument</title>
</head>
<body>

<input type="text" id="username"><span id="help_text"></span>

<script>


            function createXmlHttp() {

                //兼容
                var xmlHttp = null;
                if (window.ActiveXObject) {
                    xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
                } else {
                    xmlHttp = new XMLHttpRequest();
                }
                return xmlHttp
            }

            document.querySelector("#username").onchange = function () {
                var username = this.value;
                //创建Ajax引擎
                var xmlHttp = createXmlHttp();
                //设置请求方式和URL
                //xmlHttp.open("get","/check?username="+encodeURIComponent(username)+"&_="+new Date().getTime());
                xmlHttp.open("get", "/check?username=" + encodeURIComponent(username));
                //回调函数
                console.log(username);
                xmlHttp.onreadystatechange = function () {
                    //获取请求状态码[1,2,3,4]
                    var readyState = xmlHttp.readyState;
                    if (readyState == 4) {
                        //获取HTTP状态码
                        var httpState = xmlHttp.status;
                        if (httpState == 200) {
                            //处理服务器响应的数据
                            var result = xmlHttp.responseText;
                            if (result == "yes") {
                                document.querySelector("#help_text").innerText = "√";
                            } else {
                                document.querySelector("#help_text").innerText = "账号已被占用";
                            }
                        } else {
                            alert("服务器请求异常:" + httpState);
                        }
                    }
                }
                //发出请求
                xmlHttp.send();
            }
</script>

</body>
</html>
