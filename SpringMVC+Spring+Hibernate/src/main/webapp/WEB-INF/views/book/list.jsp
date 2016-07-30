<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>doucument</title>
    <link rel="stylesheet" href="/static/css/bootstrap.min.css">
</head>
<body>
<div class="container">

    <div class="page-header">
        <h3>SpringMVC+Hibernate+Spring</h3>
    </div>

    <div class="well well-sm">
        <form method="get" class="form-inline">
            <div class="form-group">
                <input type="text" placeholder="书籍名称" name="q_like_bookname" value="${q_like_bookname}" class="form-control">
            </div>
            <div class="form-group">
                <input type="text" placeholder="最低价格" name="q_ge_bookprice" class="form-control"> - <input type="text" name="q_le_bookprice" placeholder="最高价格" class="form-control">
            </div>

            <button class="btn btn-default">搜索</button>
        </form>
    </div>


    <a href="/book/new" class="btn btn-success">新增书籍</a>
    <table class="table table-bordered">
        <thead>
        <tr>
            <th>名称</th>
            <th>作者</th>
            <th>价格</th>
            <th>数量</th>
            <th>出版社</th>
            <th>类型</th>
            <th>操作</th>
        </tr>
        </thead>

        <tbody>
        <c:forEach items="${page.items}" var="book">
            <tr>
                <td>${book.bookname}</td>
                <td>${book.bookauthor}</td>
                <td>${book.bookprice}</td>
                <td>${book.booknum}</td>
                <td>${book.publisher.pubname}</td>
                <td>${book.bookType.booktype}</td>
                <td>
                    <a href="/book/${book.id}/edit">修改</a>
                    <a href="/book/${book.id}/del">删除</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <ul id="page" class="pagination pull-right"></ul>
</div>


<script src="/static/js/jquery-2.2.3.min.js"></script>
<script src="/static/js/jquery.twbsPagination.min.js"></script>

<script>
    $(function(){
        $("#page").twbsPagination({
            totalPages:${page.totalPages},
            visiblePages: 5,
            first: '首页',
            prev: '上一页',
            next: '下一页',
            last: '末页',
            href: '?p={{number}}'
        });

    })

</script>


</body>
</html>
