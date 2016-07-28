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
        <h3>新增书籍</h3>
    </div>

    <div class="row">
        <div class="col-md-4">
            <form method="post">
                <div class="form-group">
                    <label>书籍名称</label>
                    <input type="text" class="form-control" name="bookname">
                </div>
                <div class="form-group">
                    <label>书籍作者</label>
                    <input type="text" class="form-control" name="bookauthor">
                </div>
                <div class="form-group">
                    <label>价格</label>
                    <input type="text" class="form-control" name="bookprice">
                </div>
                <div class="form-group">
                    <label>数量</label>
                    <input type="text" class="form-control" name="booknum">
                </div>
                <div class="form-group">
                    <label>出版社</label>
                    <select class="form-control" name="publisher.id">
                        <c:forEach items="${publisherList}" var="pub">
                            <option value="${pub.id}">${pub.pubname}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <label>分类</label>
                    <select name="bookType.id" class="form-control">
                        <c:forEach items="${bookTypeList}" var="type">
                            <option value="${type.id}">${type.booktype}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <button class="btn btn-primary">保存</button>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
