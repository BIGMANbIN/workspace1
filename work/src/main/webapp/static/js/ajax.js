var ajax = (function(){
    var xmlHttp = null;
    function createXmlHttp(){
        if(window.ActiveXObject){
            xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
        }else{
            xmlHttp = new XMLHttpRequest();
        }
        return xmlHttp;
    }

    function bilderQuertParam(ispost,objectParam){
        var queryParam = "";
        if (!ispost){
            queryParam="?";
        }
        for (var index in objectParam){
            var key = index;
            var value = objectParam[index];
            queryParam +=(key + "=" + value + "&");
        }
        if(!ispost){
            queryParam += "_=" + new Date().getTime();
        }else{
            if (queryParam.lastIndexOf("&") == queryParam.length - 1){
                queryParam = queryParam.substring(0,queryParam.length-1);
            }
        }
        return queryParam;
    }

    /**
     * 发出一个get ajax请求
     */
    function sendGetRequest(url,queryParam,fn){
        var args = arguments;
        var xmlHttp = createXmlHttp();

        xmlHttp.open("get",url+bilderQuertParam(false,queryParam),true);
        xmlHttp.onreadystatechange = function(){
            var readyState = xmlHttp.readyState;
            if(readyState == 4){
                var status = xmlHttp.status();
                if(status == 200){
                    var result = xmlHttp.responseText;
                    if (args.length == 2){
                        args[1](result);
                    }else if(args.length == 3){
                        args[2](result);
                    }else{
                        alert("服务器异常："+status);
                    }
                }
            }

        }
        xmlHttp.send();
    }

    /**
     * 发送一个post ajax请求
     */
    function sendPostRequest(url,queryParam,fn){
        var args = arguments;
        var xmlHttp = createXmlHttp();

        xmlHttp.open("post",url,true);
        xmlHttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded")
        xmlHttp.onreadystatechange = function(){
            var readyState = xmlHttp.readyState;
            if(readyState == 4){
                var status = xmlHttp.status();
                if(status == 200){
                    var result = xmlHttp.responseText;
                    if (args.length == 2){
                        args[1](result);
                    }else if(args.length == 3){
                        args[2](result);
                    }else{
                        alert("服务器异常："+status);
                    }
                }
            }

        }
        xmlHttp.send(bilderQuertParam(true,queryParam));
    }
        return{
            getText:sendGetRequest,
            postText:sendPostRequest
        }

})();