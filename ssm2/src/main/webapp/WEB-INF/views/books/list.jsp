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
        <h2>图书列表</h2>
    </div>

    <c:if test="${not empty messgae}">
        <div class="alert alert-success">${message}</div>
    </c:if>

    <a href="/books/new" class="btn bg-success">添加书籍</a>

    <table class="table">
        <thead>
        <tr>
            <th>名称</th>
            <th>作者</th>
            <th>价格</th>
            <th>数量</th>
            <th>出版社</th>
            <th>分类</th>
            <th>操作</th>
        </tr>
        </thead>

        <tbody>

        <c:forEach items="${bookList}" var="book">
            <tr>
                <td>${book.bookname}</td>
                <td>${book.bookauthor}</td>
                <td>${book.bookprice}</td>
                <td>${book.booknum}</td>
                <td>${book.publisher.pubname}</td>
                <td>${book.bookType.booktype}</td>
                <td>
                    <a href="/books/${book.id}">修改</a>
                    <a href="javascript:;" rel="${book.id}" class="delLink">删除</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<script src="/static/js/jquery-2.2.3.min.js"></script>
<script>
    $(function(){

        $(".delLink").click(function(){
            var id = $(this).attr("rel");
            if(confirm("确定删除吗?")){
                window.location.href = "/books/"+id+"/del";
            }
        });
    })
</script>
</body>
</html>
