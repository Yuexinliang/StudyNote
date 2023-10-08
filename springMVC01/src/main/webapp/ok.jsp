<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2023/10/07
  Time: 14:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
${info}
<form action="zcb" method="post">
    <select name="choose">
        <option value="1">1</option>
        <option value="2">2</option>
        <option value="3">3</option>
    </select>
    <input type="submit" id="find" value="提交">
</form>
</body>
<script src="js/jquery.min.js"></script>
<%--<script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.min.js"></script>--%>
<%--<script>--%>
<%--    $(document).ready(function(){--%>
<%--        $('#find').click(function (){--%>
<%--            $.ajax({--%>
<%--                url: "zcb",--%>
<%--                type:"post",--%>
<%--                data: {--%>
<%--                    choose: $("[name='choose']").val(),--%>
<%--                },--%>
<%--                success(data){--%>
<%--                    alert(data.flag)--%>
<%--                }--%>
<%--            });--%>
<%--        })--%>
<%--    })--%>
<%--</script>--%>
</html>
