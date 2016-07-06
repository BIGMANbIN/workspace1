<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>doucument</title>
    <link rel="stylesheet" href="/static/css/bootstrap.min.css">
    <link rel="stylesheet" href="/static/js/datatables/css/dataTables.bootstrap.min.css">
</head>
<body>
<div class="container">
    <table class="table" id="dataTable">
        <thead>
        <tr>
            <th>ID</th>
            <th>名称</th>
            <th>作者</th>
            <th>价格</th>
            <th>数量</th>
            <th>分类</th>
            <th>出版社</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>


        </tbody>
    </table>

</div>


<script src="/static/js/jquery-2.2.3.min.js"></script>
<script src="/static/js/datatables/js/jquery.dataTables.min.js"></script>
<script src="/static/js/datatables/js/dataTables.bootstrap.min.js"></script>
<script>
    $(function(){
        $('#dataTable').DataTable({
            "lengthMenu":[5,10,25,50,100],//规定每页显示的数量
            "serverSide":true,//规定所有操作都在服务端进行
            "ajax":"/datatable/data.json",//服务端URL地址
            "columns":[//配置返回的json中[data]属性中数据key和表格对应列的关系
                {"data":"id"},
                {"data":"bookname"},
                {"data":"bookauthor"},
                {"data":"bookprice","mame":"bookprice"},
                {"data":"booknum","name":"booknum"},
                {"data":"bookType.booktype","name":"typeid"},
                {"data":"publisher.pubname"},
                {"data":function(row){
                    return "#";
                }}
            ],
            "columnDefs":[//定义列的特征
                {targets:[0],visible:false},
                {targets:[1,2,5,7],orderable:false}

            ],
            "language":{
                //定义中文
                "search":"请输入书籍名称",
                "zeroRecords":      "没有匹配的数据",
                "lengthMenu":       "显示 _MENU_ 条数据",
                "info":             "显示从 _START_ 到 _END_ 条数据 共 _TOTAL_ 条数据",
                "infoFiltered":     "(从 _MAX_ 条数据中过滤得来)",
                "loadingRecords":   "加载...",
                "processing":       "处理中...",
                "paginate":{
                    "first":    "首页",
                    "last":     "末页",
                    "next":     "下一页",
                    "previous": "上一页"
                },
            }
        });
    });

</script>
</body>
</html>
