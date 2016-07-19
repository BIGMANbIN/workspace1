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
    <link rel="stylesheet" href="/static/plugins/daterangepicker/daterangepicker-bs3.css">
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
        <section class="content-header">
            <h1>　</h1>
            <ol class="breadcrumb">
                <li><a href="/sales"><i class="fa fa-dashboard"></i> 机会列表</a></li>
                <li class="active">${sales.name}</li>
            </ol>
        </section>

        <section class="content">
            <!-- SELECT2 EXAMPLE -->
            <div class="box box-default collapsed-box">
                <div class="box-header with-border">
                    <h3 class="box-title">搜索</h3>
                    <div class="box-tools">
                        <button class="btn btn-box-tool" data-widget="collapse" data-toggle="tooltip"><i
                                class="fa fa-plus"></i></button>
                    </div>
                </div>

                <div class="box-body">
                    <form class="form-inline">
                        <input type="hidden" id="search_start_time">
                        <input type="hidden" id="search_end_time">
                        <input type="text" class="form-control" id="search_name" placeholder="机会名称">
                        <select class="form-control" class="form-control" id="search_progress">
                            <option value="">当前进度</option>
                            <option value="初次接触">初次接触</option>
                            <option value="确认意向">确认意向</option>
                            <option value="提供合同">提供合同</option>
                            <option value="完成交易">完成交易</option>
                            <option value="交易搁置">交易搁置</option>
                        </select>
                        <input type="text" id="rangepicker" class="form-control" placeholder="跟进时间">
                        <button type="button" id="search_Btn" class="btn btn-default"><i class="fa fa-search"></i> 搜索
                        </button>
                    </form>
                </div>
            </div>
            <div class="box box-primary">

                <div class="box-header with-border">
                    <h2 class="box-title">机会列表</h2>
                    <div class="box-tools pull-right">
                        <a class="btn btn-xs btn-success" id="newBtn"><i class="fa fa-plus"></i>
                            新增机会</a>
                    </div>
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
    </div>
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
                    </div>
                    <div class="form-group">
                        <label>价值</label>
                        <input type="text" name="price" class="form-control">
                    </div>
                    <div class="form-group">
                        <label>最后跟进时间</label>
                        <input type="text" name="lasttime" class="form-control">
                    </div>
                    <div class="form-group">
                        <label>当前进度</label>
                        <select name="progress" class="form-control">
                            <option>初次接触</option>
                            <option>确认意向</option>
                            <option>提供合同</option>
                            <option>交易失败</option>
                            <option>交易完成</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label>关联客户</label>
                        <select name="custid" class="form-control">
                            <c:forEach items="${customerList}" var="cust">
                                <option value="${cust.id}">${cust.name}</option>
                            </c:forEach>
                        </select>
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
<script src="/static/plugins/daterangepicker/daterangepicker.js"></script>
<script src="/static/plugins/datatables/js/jquery.dataTables.min.js"></script>
<script src="/static/plugins/datatables/js/dataTables.bootstrap.min.js"></script>
<script src="/static/plugins/validate/jquery.validate.min.js"></script>
<script>

    $(function () {

        var dataTable = $("#salesTable").DataTable({
            serverSide: true,
            ajax: {
                url:"/sales/load",
                data:function(dataSouce){
                    dataSouce.name = $("#search_name").val();
                    dataSouce.progress = $("#search_progress").val();
                    dataSouce.startdate = $("#search_start_time").val();
                    dataSouce.enddate = $("#search_end_time").val();
                }
            },
            ordering: false,
            "autoWidth": true,
            searching: false,
            columns: [
                {
                    "data": function (row) {
                        return '<a href="/sales/' + row.id + '">' + row.name + '</a>';
                    }
                },
                {
                    "data": function (row) {
                        return "<a href='/customer/" + row.custid + "'>" + row.custname + "</a>";
                    }
                },
                {
                    "data": function (row) {
                        return '￥' + row.price;
                    }
                },
                {
                    "data": function (row) {
                        if (row.progress == "交易完成") {
                            return '<span class="label label-success">' + row.progress + '</span>'
                        } else if (row.progress == "交易失败") {
                            return '<span class="label label-danger">' + row.progress + '</span>'
                        } else {
                            return row.progress;
                        }

                    }
                },
                {"data": "lasttime"},
                {"data": "realname"}
            ],
            "language": { //定义中文
                "search": "客户名称或电话:",
                "zeroRecords": "没有匹配的数据",
                "lengthMenu": "显示 _MENU_ 条数据",
                "info": "显示从 _START_ 到 _END_ 条数据 共 _TOTAL_ 条数据",
                "infoFiltered": "(从 _MAX_ 条数据中过滤得来)",
                "loadingRecords": "加载中...",
                "processing": "处理中...",
                "paginate": {
                    "first": "首页",
                    "last": "末页",
                    "next": "下一页",
                    "previous": "上一页"
                }
            }
        });

        //搜索
        $("#search_Btn").click(function(){
            dataTable.ajax.reload();
        });

        //时间戳
        $("#rangepicker").daterangepicker({
            format:"YYYY-MM-DD",
            separator:"~",
            locale:{
                "applyLabel": "选择",
                "cancelLabel": "取消",
                "fromLabel": "从",
                "toLabel": "到",
                "customRangeLabel": "自定义",
                "weekLabel": "周",
                "daysOfWeek": [
                    "一",
                    "二",
                    "三",
                    "四",
                    "五",
                    "六",
                    "日"
                ],
                "monthNames": [
                    "一月",
                    "二月",
                    "三月",
                    "四月",
                    "五月",
                    "六月",
                    "七月",
                    "八月",
                    "九月",
                    "十月",
                    "十一月",
                    "十二月"
                ],
                "firstDay": 1
            },
            ranges: {
                '今天': [moment(), moment()],
                '昨天': [moment().subtract(1, 'days'), moment().subtract(1, 'days')],
                '最近7天': [moment().subtract(6, 'days'), moment()],
                '最近30天': [moment().subtract(29, 'days'), moment()],
                '本月': [moment().startOf('month'), moment().endOf('month')],
                '上个月': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')]
            }
        });
        $('#rangepicker').on('apply.daterangepicker', function(ev, picker) {
            $("#search_start_time").val(picker.startDate.format('YYYY-MM-DD'));
            $("#search_end_time").val(picker.endDate.format('YYYY-MM-DD'));
            //console.log(picker.startDate.format('YYYY-MM-DD'));
            //console.log(picker.endDate.format('YYYY-MM-DD'));
        });

        //新增机会
        $("#newForm").validate({
            errorClass: "text-danger",
            errorElement: "span",
            rules: {
                name: {
                    required: true
                },

                price: {
                    required: true,
                    number:true
                }
            },
            messages: {
                name: {
                    required: "请输入机会名称"
                },
                price: {
                    required: "请输入价值",
                    number:"数字格式错误"
                },
            },
            submitHandler: function (form) {
                $.post("/sales/new", $(form).serialize()).done(function (data) {
                    if ("success" == data) {
                        $("#newModal").modal('hide');
                        dataTable.ajax.reload();
                    }
                }).fail(function () {
                    alert("服务器异常");
                });
            }

        });

        //新增客户
        $("#newBtn").click(function () {
            $("#newForm")[0].reset();
            $("#newModal").modal({
                show: true,
                dropback: 'static',
                keyboard: false
            });
        });
        $("#addBtn").click(function () {
            $("#newForm").submit();
        });


    });
</script>
</body>
</html>
