<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>doucument</title>
    <link rel="stylesheet" href="/static/css/bootstrap.min.css">
    <link rel="stylesheet" href="/static/js/webupload/webuploader.css">
</head>
<body>
<div class="container">
    <div class="page-header">
        <h3>Ajax上传文件</h3>
    </div>

    <div id="picker">选择文件</div>
    <h3>文件上传列表</h3>
    <ul id="fileList"></ul>
    <button id="btn" class="btn btn-success">开始上传</button>
</div>
<script src="/static/js/jquery-2.2.3.min.js"></script>
<script src="/static/js/webupload/webuploader.js"></script>
<script type="myTemplate" >
    <div class="progress" id="progressTemplate">
        <div class="progress-bar progress-bar-success"></div>
    </div>
</script>

<script>
    $(function(){

        var uploader = WebUploader.create({
            swf:"/static/js/webupload/Uploader.swf",
            server:"/upload",
            pick:"#picker",
            fileVal:"file"
        });

        //将选择的大文件放到文件清单中

        uploader.on("fileQueued",function(file){
            console.log(file.id + "->" + file.name);

            var html = "<li id='"+file.id+"'>"+file.name+"</li>";
            $("#fileList").append(html);
        });

        //文件开始上传时调用的事件

        uploader.on("uploadProgress",function(file,percentage){
            percentage = parseInt(percentage * 100);

            var $li = $("#" + file.id);

            if($li.find(".progress").length){
                $li.find(".progress.progress-bar").css("width",percentage + "%");
            }else {
                var template = $("#progressTemplate").html();
                $li.append(template);
            }

        });

        //文件上传成功
        uploader.on("uploadSuccess",function(file){

            $("#"+file.id).css("color","#ccc");
        });
        //文件上传失败
        uploader.on("uploadError", function(file){
            $("#"+file.id).css("color","darkred");
        });

        uploader.on("uploadComplete",function(file){

        });

        //手动开启上传

        $("#btn").click(function () {
            console.log("开机")
            uploader.upload();

        })
    });

</script>
</body>
</html>
