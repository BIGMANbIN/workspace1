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
    <title>凯盛CRM |销售机会</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <!-- Bootstrap 3.3.6 -->
    <link rel="stylesheet" href="/static/bootstrap/css/bootstrap.min.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.5.0/css/font-awesome.min.css">
    <!-- Ionicons -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
    <!-- Theme style -->
    <link rel="stylesheet" href="/static/dist/css/AdminLTE.min.css">

    <link rel="stylesheet" href="/static/dist/css/skins/skin-blue.min.css">
    <link rel="stylesheet" href="/static/plugins/datatables/css/dataTables.bootstrap.css">

</head>
<!--
BODY TAG OPTIONS:
=================
Apply one or more of the following classes to get the
desired effect
|---------------------------------------------------------|
| SKINS         | skin-blue                               |
|               | skin-black                              |
|               | skin-purple                             |
|               | skin-yellow                             |
|               | skin-red                                |
|               | skin-green                              |
|---------------------------------------------------------|
|LAYOUT OPTIONS | fixed                                   |
|               | layout-boxed                            |
|               | layout-top-nav                          |
|               | sidebar-collapse                        |
|               | sidebar-mini                            |
|---------------------------------------------------------|
-->
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">

    <%@ include file="../include/mainHeader.jsp" %>
    <jsp:include page="../include/leftSide.jsp">
        <jsp:param name="menu" value="sales"/>
    </jsp:include>


    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">

        <section class="content">
            <div class="box box-primary">
                <div class="box-header with-border">
                    <shiro:hasRole name="经理">
                        <h2 class="box-title">机会列表</h2>
                        <div class="box-tools pull-right">
                            <a href="/sales/salesnew" class="btn btn-xs btn-success"><i class="fa fa-plus"></i> 新增机会</a>
                        </div>
                    </shiro:hasRole>
                </div>
                <div class="box-body">
                    <table class="table" id="salesTable">
                        <thead>
                        <tr>
                            <th>机会名称</th>
                            <th>关联客户</th>
                            <th>价值</th>
                            <th>当前进度</th>
                            <th>最后跟进时间</th>
                            <th>所属员工</th>
                        </tr>
                        </thead>
                    </table>
                </div>
            </div>
        </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->

</div>
<!-- ./wrapper -->

<div class="modal fade" id="newModal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="text-align: center">新增机会</h4>
            </div>
            <div class="modal-body">
                <form id="newForm">
                    <div class="form-group">
                        <label>机会名称</label>
                        <input type="text" name="name" class="form-control">
                        <div class="form-group">
                            <label>价值</label>
                            <input type="text" name="price" class="form-control">
                        </div>
                        <div class="form-group">
                            <label>关联客户</label>
                            <select name="custname">

                            </select>
                        </div>
                        <div class="form-group">
                            <label>电子邮件</label>
                            <input type="text" name="email" class="form-control">
                        </div>
                        <div class="form-group">
                            <label>客户等级</label>
                            <select name="level" class="form-control">
                                <option value=""></option>
                                <option value="★">★</option>
                                <option value="★★">★★</option>
                                <option value="★★★">★★★</option>
                                <option value="★★★★">★★★★</option>
                                <option value="★★★★★">★★★★★</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <label>地址</label>
                            <input type="text" name="address" class="form-control">
                        </div>
                        <div class="form-group" id="companyList">
                            <label>所属公司</label>
                            <select name="companyid" class="form-control"></select>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" id="addBtn">保存</button>
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
<script src="/static/plugins/moment/moment.min.js"></script>
<script src="/static/plugins/datatables/js/jquery.dataTables.min.js"></script>
<script src="/static/plugins/datatables/js/dataTables.bootstrap.min.js"></script>
<script>

</script>
</body>
</html>
