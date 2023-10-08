<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<form action="emp/update" method="get">
    <c:forEach items="${list}" var="dept" varStatus="status">
        <table>
            <tr>
                <td>
                    <%--通过name寻找集合中的每个元素并赋值--%>
                    部门编号：<input type="text" name="depts[${status.index}].deptno" value="${dept.deptno}"><br/>
                </td>
                <td>
                    部门名称：<input type="text" name="depts[${status.index}].dname" value="${dept.dname}"><br/>
                </td>
                <td>
                    部门地址<input type="text" name="depts[${status.index}].loc" value="${dept.loc}"><br/>
                </td>
            </tr>
        </table>
    </c:forEach>
    <input type="submit" value="批量修改">
</form>

<form action="emp/update" method="get">
    <c:forEach items="${maps}" var="map" varStatus="status">
        <table>
            <tr>
                <td>
                        <%--deptMaps[${map.value.deptno}]中${map.value.deptno}为key值，这应该是由于springMVC的映射特性，
                        使得map集合可以用[key值]来找寻对应数据--%>
                    部门编号：<input type="text" name="deptMaps[${map.key}].deptno" value="${map.value.deptno}"><br/>
                </td>
                <td>
                    部门名称：<input type="text" name="deptMaps[${map.key}].dname" value="${map.value.dname}"><br/>
                </td>
                <td>
                    部门地址<input type="text" name="deptMaps[${map.value.deptno}].loc" value="${map.value.loc}"><br/>
                </td>
            </tr>
        </table>
    </c:forEach>
    <input type="submit" value="map批量修改">
</form>
</body>
<script src="js/jquery.min.js"></script>
</html>
