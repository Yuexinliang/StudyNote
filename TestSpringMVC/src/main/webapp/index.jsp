<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<h2>Hello World!</h2>
${info}
    <form action="emp/ts1" method="post">
        员工编号：<input type="text" name="empno"><br/>
        员工姓名：<input type="text" name="ename"/><br/>
        <input type="submit" value="提交">
    </form>

<h1>实体类发送信息</h1>

    <form action="emp/ts3" method="post">
        员工编号：<input type="text" name="empno"><br/>
        员工姓名：<input type="text" name="ename"/><br/>
        工资：<input type="text" name="sal"/><br/>
        是否存活：<input type="radio" name="isLive"  value="true"/>存活
            <input type="radio" name="isLive"  value="false"/>死亡
        <input type="submit" value="提交">
    </form>

<h1>存在依赖的实体类发送信息</h1>

<form action="emp/ts4" method="post">
    员工编号：<input type="text" name="empno"><br/>
    员工姓名：<input type="text" name="ename"/><br/>
    工资：<input type="text" name="sal"/><br/>
    是否存活：<input type="radio" name="isLive"  value="true"/>存活
    <input type="radio" name="isLive"  value="false"/>死亡<br/>
    部门名称：<input type="text" name="dept.dname"/><br/>
    部门位置：<input type="text" name="dept.loc"/><br/>
    你最喜欢的爱好是：<input type="checkbox" name="likes" value="book"/>图书<br/>
    <input type="checkbox" name="likes" value="food"/>美食<br/>
    <input type="checkbox" name="likes" value="sport"/>运动<br/>
    <%--时间类数据需要额外配置自定义适配器--%>
    入职时间：<input type="date" name="hiredate"/>
    <input type="submit" value="提交">
</form>

<h1>登录后获取集合进行展示</h1>

<form action="emp/ts5" method="post">
    <input type="submit" value="登录">
</form>
</body>
</html>
