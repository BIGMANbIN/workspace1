<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Doucuent</title>
    <link rel="stylesheet" href="/static/css/bootstrap.min.css">
    <link href="signin.css" rel="stylesheet">
    <style>
        body {
            padding-top: 40px;
            padding-bottom: 40px;
            background-color: #eee;
            margin: 30px;
        }

        .form-heading {
            max-width: 330px;
            padding: 15px;
            margin: 0 auto;
        }

        .form-heading input[type="Username"] {
            margin-bottom: -3px;
            border-bottom-right-radius: 0;
            border-bottom-left-radius: 0;
        }

        .form-heading input[type="Password"] {
            margin-bottom: 30px;
            border-top-left-radius: 0;
            border-top-right-radius: 0;
        }

        .form-heading .form-control {
            position: relative;
            height: auto;
            -webkit-box-sizing: border-box;
            -moz-box-sizing: border-box;
            box-sizing: border-box;
            padding: 10px;
            font-size: 16px;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-xs-4">
            <form id="regForm">
                <h2><strong>用户注册界面</strong></h2>
                <div class="form-group">
                    <label>账号</label>
                    <input type="text" class="form-control" name="username">
                </div>
                <div class="form-group">
                    <label>密码</label>
                    <input type="text" class="form-control" name="password">
                </div>
                <div class="form-group">
                    <button type="button" id="subBtn" class="btn btn-lg btn-primary">注册</button>
                </div>
            </form>
        </div>
    </div>
</div>
<script src="/static/js/jquery-2.2.3.min.js"></script>
<script src="/static/js/jquery.validate.min.js"></script>
<script>
    $(function () {
        $("#regForm").validate({
            errorElement:"span",
            errorClass:"text-danger",
            rules:{
                username:{

                },
                password:{

                },
                code:{

                }
            },
            message:{
                username:{

                },
                password:{

                },
                code:{

                }
            },
        })
    })

</script>
</body>
</html>
