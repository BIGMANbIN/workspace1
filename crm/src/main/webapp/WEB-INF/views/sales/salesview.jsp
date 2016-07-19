<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<!--
This is a starter template page. Use this page to start your new project from
scratch. This page gets rid of all links and provides the needed markup only.
-->
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>凯盛CRM | 机会信息 | ${sales.name}</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <!-- Bootstrap 3.3.6 -->
    <link rel="stylesheet" href="/static/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.5.0/css/font-awesome.min.css">
    <!-- Ionicons -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
    <!-- Theme style -->
    <link rel="stylesheet" href="/static/dist/css/AdminLTE.min.css">
    <link rel="stylesheet" href="/static/dist/css/skins/skin-blue.min.css">
    <link rel="stylesheet" href="/static/plugins/simditor/styles/simditor.css">
    <link rel="stylesheet" href="/static/plugins/webuploader/webuploader.css">
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">

    <%@include file="../include/mainHeader.jsp" %>
    <jsp:include page="../include/leftSide.jsp">
        <jsp:param name="menu" value="sales"/>
    </jsp:include>

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <section class="content-header">
            <h1>　</h1>
            <ol class="breadcrumb">
                <li><a href="/sales"><i class="fa fa-dashboard"></i> 机会列表</a></li>
                <li class="active">${sales.name}</li>
            </ol>
        </section>

        <!-- Main content -->
        <section class="content">

            <div class="box box-primary">
                <div class="box-header">
                    <h3 class="box-title">${sales.name}</h3>
                    <shiro:hasRole name="经理">
                        <div class="box-tools">
                            <button id="delBtn" class="btn btn-danger">删除</button>
                        </div>
                    </shiro:hasRole>
                </div>
                <div class="box box-body">
                    <table class="table table-bordered">
                        <tbody>
                        <tr>
                            <td style="width: 100px">关联客户</td>
                            <td style="width: 200px"><a href="/customer/${sales.custid}">${sales.custname}</a></td>
                            <td style="width: 100px">价值</td>
                            <td style="width: 200px">￥${sales.price}</td>
                        </tr>
                        <tr>
                            <td>当前进度</td>
                            <td>${sales.progress}
                                <a href="javascript:;" id="editProgress">修改</a>
                            </td>
                            <td>最后跟进时间</td>
                            <td>${empty sales.lasttime ? '无' : sales.lasttime}</td>
                        </tr>
                        </tbody>
                    </table>
                </div>

            </div>

            <%--customer box end--%>
            <div class="row">
                <div class="col-md-8">
                    <div class="box box-info">
                        <div class="box-header with-border">
                            <h3 class="box-title">跟进记录</h3>
                            <button class="btn btn-success pull-right" id="newLogBtn"><i class="fa fa-plus"></i>新增
                            </button>
                        </div>
                    </div>


                    <div class="box-body">
                        <ul class="timeline">
                            <c:forEach items="${salesLogList}" var="log">
                                <li>
                                    <c:choose>
                                        <c:when test="${log.type == 'auto'}">
                                            <i class="fa fa-history bg-yellow"></i>
                                        </c:when>
                                        <c:otherwise>
                                            <i class="fa fa-commenting bg-aqua"></i>
                                        </c:otherwise>
                                    </c:choose>

                                    <div class="timeline-item">
                                        <span class="time"><i class="fa fa-clock-o"></i> <span class="timeago"
                                                                                               title="${log.time}"></span></span>
                                        <h3 class="timeline-header no-border">
                                                ${log.context}
                                        </h3>
                                    </div>
                                </li>
                            </c:forEach>
                            <li>
                                <i class="fa fa-clock-o bg-gray"></i>
                            </li>
                        </ul>
                    </div>
                </div>
                <%--col-md-8 end--%>
                <div class="col-md-4">
                    <div class="box box-default">
                        <div class="box-header with-border">
                            <h3 class="box-title"><i class="fa fa-file-o"></i> 相关资料</h3>
                            <div class="box-tools">
                                <div class="box-tools">
                                    <div id="uploadBtn"><span class="text"><i class="fa fa-upload"></i></span></div>
                                </div>
                            </div>
                        </div>
                        <div class="box-body">
                            <ul class="list-unstyled files">
                                <c:if test="${empty salesFileList}">
                                    <li>暂无资料</li>
                                </c:if>
                                <c:forEach items="${salesFileList}" var="file">
                                    <li><a href="/sales/file/${file.id}/download">${file.name}</a></li>
                                </c:forEach>
                            </ul>
                        </div>
                    </div>

                    <div class="box box-default">
                        <div class="box-header with-border">
                            <h3 class="box-title"><i class="fa fa-calendar-check-o"></i> 代办事项</h3>
                        </div>
                        <div class="box-body">
                            <h5>暂无代办事项</h5>
                        </div>
                    </div>
                </div>
                <%--col-md-4 end--%>
            </div>

        </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->
</div>

<div class="modal fade" id="newLogModal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">新增跟进记录</h4>
            </div>
            <div class="modal-body">
                <form id="newLogForm" action="/sales/log/new" method="post">
                    <input type="hidden" name="logsalesid" value="${sales.id}">
                    <div class="form-group">
                        <textarea name="context" id="context"></textarea>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" id="saveLogBtn">保存</button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="progressModal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">修改进度</h4>
            </div>
            <div class="modal-body">
                <form action="/sales/progress/edit" method="post" id="progressForm">
                    <input type="hidden" name="id" value="${sales.id}">
                    <fiv class="form-group">
                        <label>当前进度</label>
                        <select name="progress" class="form-control" id="progressID">
                            <option value="初次接触">初次接触</option>
                            <option value="确认意向">确认意向</option>
                            <option value="提供合同">提供合同</option>
                            <option value="交易失败">交易失败</option>
                            <option value="交易完成">交易完成</option>
                        </select>
                    </fiv>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" id="saveProgressBtn">保存</button>
            </div>
        </div>
    </div>
</div>
<!-- jQuery 2.2.3 -->
<script src="/static/plugins/jQuery/jquery-2.2.3.min.js"></script>
<!-- Bootstrap 3.3.6 -->
<script src="/static/bootstrap/js/bootstrap.min.js"></script>
<!-- AdminLTE App -->
<script src="/static/dist/js/app.min.js"></script>
<script src="/static/plugins/timeago/timeago.js"></script>
<script src="/static/plugins/simditor/scripts/module.min.js"></script>
<script src="/static/plugins/simditor/scripts/hotkeys.min.js"></script>
<script src="/static/plugins/simditor/scripts/uploader.min.js"></script>
<script src="/static/plugins/simditor/scripts/simditor.min.js"></script>
<script src="/static/plugins/webuploader/webuploader.min.js"></script>
<script>
    $(function () {
        //相对时间
        $(".timeago").timeago();

        //在线编辑器
        var edit = new Simditor({
            textarea: $("#context"),
            placeholder: '请输入跟进内容',
            toolbar: false
        });
        //新增跟进记录
        $("#newLogBtn").click(function () {
            $("#newLogModal").modal({
                show: true,
                backdrop: 'static'
            })
        });

        $("#saveLogBtn").click(function () {
            if (edit.getValue()) {
                $("#newLogForm").submit();
            } else {
                edit.focus();
            }
        });

        //修改当前进度
        $("#editProgress").click(function () {
            $("#progressModal").modal({
                show: true,
                backdrop: 'static'
            });
        });
        $("#saveProgressBtn").click(function () {
            $("#progressForm").submit();
        });

        //上传文件
        var uploader = WebUploader.create({
            swf: "/static/plugins/webuploader/Uploader.swf",
            pick: "#uploadBtn",
            server: "/sales/file/upload",
            fileValL: "file",
            formData: {"salesid": "${sales.id}"},
            auto: true //选择文件后直接上传
        });
        //上传成功
        uploader.on("startUpload", function () {
            $("#uploadBtn.text").html('<i class="fa fa-spinner fa-spin"></i>');
        });
        uploader.on('uploadSuccess', function (file, data) {
            if (data._raw == "success") {
                window.history.go(0);
            }
        });
        //上传失败
        uploader.on("uploadError", function (file) {
            alert("文件上传失败")
        });
        //无论成功或失败
        uploader.on("uploadComplete", function (file) {
            $("#uploadBtn.text").html('<i class="fa fa-upload"></i>').removeAttr("disabled");
            ;
        });

        <shiro:hasRole name="经理">
        $("#delBtn").click(function () {
            if (confirm("确定要删除吗？")) {
                window.location.href = "/sales/del/${sales.id}"
            }
        });
        </shiro:hasRole>


    });
</script>
</body>
</html>

