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
        <h2>添加书籍</h2>
    </div>
    <div class="row">
        <idv class="col-xs-4">
            <form action="" method="post">
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
                    <label>出版社</label>
                    <select class="form-control" name="pubid">
                        <c:forEach items="${pus}" var="publisher">
                            <option value="${publisher.id}">${publisher.pubname}</option>
                        </c:forEach>
                    </select>
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
                    <button class="btn btn-primary">保存</button>
                </div>
            </form>
        </idv>
    </div>
</div>
</body>
</html>