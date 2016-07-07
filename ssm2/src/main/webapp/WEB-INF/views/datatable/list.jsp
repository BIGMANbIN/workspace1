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
    <div class="page-header" style="text-align: center;color: red">
        <h2><strong>图书列表</strong></h2>
    </div>
    <a href="javascript:;" style="margin-bottom: 20px" class="btn btn-primary" id="newBookBtn">添加新书籍</a>
    <table class="table table-bordered" id="dataTable">
        <thead style="color: brown">
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

<!-- Modal -->
<div class="modal fade" id="newBookModal">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">添加新书籍</h4>
            </div>
            <div class="modal-body">
                <form id="saveForm">
                    <div class="form-group">
                        <label>书籍名称</label>
                        <input type="text" name="bookname" class="form-control">
                    </div>
                    <div class="form-group">
                        <label>书籍作者</label>
                        <input type="text" name="bookauthor" class="form-control">
                    </div>
                    <div class="form-group">
                        <label>书籍价格</label>
                        <input type="text" name="bookprice" class="form-control">
                    </div>
                    <div class="form-group">
                        <label>书籍数量</label>
                        <input type="text" name="booknum" class="form-control">
                    </div>
                    <div class="form-group">
                        <label>分类</label>
                        <select class="form-control" name="typeid">
                            <c:forEach items="${types}" var="type">
                                <option value="${type.id}">${type.booktype}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label>出版社</label>
                        <select class="form-control" name="pubid">
                            <c:forEach items="${pubs}" var="publisher">
                                <option value="${publisher.id}">${publisher.pubname}</option>
                            </c:forEach>
                        </select>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" id="saveBtn">保存</button>
            </div>
        </div>
    </div>
</div>


<script src="/static/js/jquery-2.2.3.min.js"></script>
<script src="/static/js/datatables/js/jquery.dataTables.min.js"></script>
<script src="/static/js/datatables/js/dataTables.bootstrap.min.js"></script>
<script src="/static/js/bootstrap.min.js"></script>
<script src="/static/js/jquery.validate.min.js"></script>
<script>
    $(function () {

        $('#dataTable').DataTable({
            "lengthMenu": [5, 10, 25, 50, 100],//规定每页显示的数量
            "serverSide": true,//规定所有操作都在服务端进行
            "ajax": "/datatable/data.json",//服务端URL地址
            "order":[0,'desc'],//指定默认排序方式
            "searching": false,//禁止使用自带的搜索
            "columns": [//配置返回的json中[data]属性中数据key和表格对应列的关系
                {"data": "id","name":"id"},
                {"data": "bookname"},
                {"data": "bookauthor"},
                {"data": "bookprice", "name": "bookprice"},
                {"data": "booknum", "name": "booknum"},
                {"data": "bookType.booktype", "name": "typeid"},
                {"data": "publisher.pubname"},
                {
                    "data": function (row) {
                        return "<a id='' href='javascript:;' class='btn btn-primary' rel='" + row.id + "'>修改</a> " +
                                "<a href='javascript:;' class='btn btn-danger' rel='" + row.id + "'>删除</a>"

                    }
                }
            ],
            "columnDefs": [//定义列的特征
                {targets: [0], visible: true},
                {targets: [1, 2, 6, 7], orderable: false}

            ],
            "language": {
                //定义中文
                "search": "请输入书籍名称",
                "zeroRecords": "没有匹配的数据",
                "lengthMenu": "显示 _MENU_ 条数据",
                "info": "显示从 _START_ 到 _END_ 条数据 共 _TOTAL_ 条数据",
                "infoFiltered": "(从 _MAX_ 条数据中过滤得来)",
                "loadingRecords": "加载...",
                "processing": "处理中...",
                "paginate": {
                    "first": "首页",
                    "last": "末页",
                    "next": "下一页",
                    "previous": "上一页"
                },
            }
        });

        //添加新书籍
        $(function () {
            $("#saveForm").validate({
                errorElement: "span",
                errorClass: "text-danger",
                rules: {
                    bookname: {
                        required: true
                    },
                    bookauthor: {
                        required: true
                    },
                    bookprice: {
                        required: true,
                        number: true
                    },
                    booknum: {
                        required: true,
                        digits: true
                    }
                },
                messages: {
                    bookname: {
                        required: "请输入书籍名称"
                    },
                    bookauthor: {
                        required: "请输入作者"
                    },
                    bookprice: {
                        required: "请输入价格",
                        number: "请输入正确的价格"
                    },
                    booknum: {
                        required: "请输入数量",
                        digits: "请输入正确的数量"
                    }
                },

                submitHandler: function (form) {
                    $.post("/datatable/new", $(form).serialize()).done(function (data) {
                        if (data == "success") {
                            $("#newBookModal").modal('hide');
                            dataTable.ajax.reload();//自动刷新
                        }
                    })
                    .fail(function () {
                        alert("请求服务器异常")
                    });
                }
            });
        });

        //点击添加按钮
        $("#newBookBtn").click(function () {

            $("#saveForm")[0].reset();//让表单进行重置
            $("#newBookModal").modal({
                show: true,
                backdrop: 'static',
                keyboard: false
            });
        });

        //点击保存按钮
        $("#saveBtn").click(function () {
            $("#saveForm").submit();//表单提交
        });

        //删除书籍(时间委托)可以给当前或未来出现的符合选择器的元素绑定事件
        /*$(document)/delegate(".delLink")*/

    });

</script>
</body>
</html>
