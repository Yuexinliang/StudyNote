## JDBC
SQL：结构化数据查询语言
- 1.什么是JDBC:<br>
JDBC（Java DataBase Connectivity,  Java数据库连接） ,是一种用于执行SQL语句的Java API，为
多种关系数据库提供统一访问,它由一组用Java语言编写的类和接口组成.
- 2.在idea中对数据库进行增删改查：
```java
package com.jr.util;

import java.sql.*;
import java.util.Scanner;

public class Test {
    static Connection con;
    /*静态加载数据库驱动类*/
    static{
        /*手动加载数据库驱动类*/
        try {
            /*jar包中已经默认配置了驱动类的加载
             *jar--META-INF--services--java.sql.Driver--com.mysql.jdbc.Driver,
             *在加载jar包时,会自动读取该内容并加载驱动,所以我们不去编写Class.forName("com.mysql.jdbc.Driver"),
             *程序也是可以自动完成加载驱动的。
             */
            
            /*Class.forName("com.mysql.cj.jdbc.Driver");*/
            
        /*获得连接数据库对象*/
        String url = "jdbc:mysql://127.0.0.1:3306/studz10b?useSSL=false&useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai&allowPublicKeyRetrieval=true";
        String user = "root";
        String pwd = "root";
        con = DriverManager.getConnection(url,user,pwd);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    /*主方法*/
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        readAll();
    }

    /*读取*/
    public static void readAll() throws ClassNotFoundException, SQLException {
        System.out.println(con);
        /*con!=null,证明路径存在*/
        /*准备sql语句*/
        /*select后加列名*/
        /*如果要加where，后接 列名 = 值 or/and 列名 = 值*/
        String sql = "select * from db_Student";
        /*获得执行对象*/
        PreparedStatement ps = con.prepareStatement(sql);
        /*执行语句获得结果*/
        ResultSet resultSet = ps.executeQuery();
        /*处理结果集*/
        while (resultSet.next()){
            /*参数为数据表的第几个数据*/
            System.out.print(resultSet.getInt(1)+"\t"+resultSet.getString(2)+"\t");
            System.out.print(resultSet.getInt(3)+"\t");
            System.out.print(resultSet.getString(4)+"\t");
            System.out.println(resultSet.getString(5)+"\t");
        }
        /*关闭连接*/
        resultSet.close();
        ps.close();
        con.close();
    }
    /*
     * ResultSet不方便数据的传输和存储，数据应该放在集合里。
     * */

    /*读取并存储*/
    public static List<Student> readAll() throws SQLException {
        String sql = "select * from db_student";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet resultSet = ps.executeQuery();
        List<Student> list = new ArrayList<>();
        while (resultSet.next()){
          Student student = new Student();
          /*使用列名获取值*/
          student.setAge(resultSet.getInt("age"));
          student.setPhone(resultSet.getString("phone"));
          student.setSex(resultSet.getString("sex"));
          student.setSname(resultSet.getString("sname"));
          student.setStuid(resultSet.getInt("stuid"));
          list.add(student);
        }
        resultSet.close();
        ps.close();
        con.close();
        return list;
    }

    /*删除*/
    public static void deleteByStuid() throws ClassNotFoundException, SQLException {
        /*准备sql语句*/
        String sql = "delete from db_Student where stuid = ?";
        /*获得执行对象*/
        PreparedStatement ps = con.prepareStatement(sql);
            System.out.println("请输入要删除学生编号：");
            int num = new Scanner(System.in).nextInt();
            /*参数为sql语句的？*/
            ps.setInt(1,num);

        /*执行语句获得结果*/
        int i = ps.executeUpdate();//数据表中受影响的行数

        /*处理结果*/
        if(i == 1){
            System.out.println("删除成功");
        }else {
            System.out.println("删除失败");
        }
        /*关闭连接*/
        ps.close();
        con.close();
    }

    /*添加*/
    public static void addStudent() throws ClassNotFoundException, SQLException {
        /*准备sql语句*/
        String sql = "insert into db_student(stuid,sname,age,sex,phone) values (?,?,?,?,?);";
        /*获得sql执行对象*/
        PreparedStatement ps = con.prepareStatement(sql);
            /*参数为第几个？*/
            Scanner input = new Scanner(System.in);
            System.out.println("请输入要添加的学号：");
            ps.setInt(1,input.nextInt());
            System.out.println("请输入要添加的姓名：");
            ps.setString(2,input.next());
            System.out.println("请输入要添加的年龄：");
            ps.setInt(3,input.nextInt());
            System.out.println("请输入要添加的性别：");
            ps.setString(4,input.next());
            System.out.println("请输入要添加的电话：");
            ps.setString(5,input.next());
        /*执行语句获得结果*/
        int i = ps.executeUpdate();
        /*处理结果*/
        if(i == 1){
            System.out.println("添加成功");
        }else {
            System.out.println("添加失败");
        }
        /*关闭连接*/
        ps.close();
        con.close();
    }
    /*修改*/
    public static void updateStudent() throws ClassNotFoundException, SQLException {
        /*准备sql语句*/
        String sql = "update db_student set sname = ? ,age = ?, sex = ? , phone = ? where stuid = ?;";
        /*获得执行对象*/
        PreparedStatement ps = con.prepareStatement(sql);
            /*参数为第几个？*/
            Scanner input = new Scanner(System.in);
            System.out.println("请输入要修改的编号：");
            ps.setInt(5,input.nextInt());
            System.out.println("请输入要修改后的姓名：");
            ps.setString(1,input.next());
            System.out.println("请输入要修改后的年龄：");
            ps.setInt(2,input.nextInt());
            System.out.println("请输入要修改后的性别：");
            ps.setString(3,input.next());
            System.out.println("请输入要修改后的电话：");
            ps.setString(4,input.next());
        /*执行语句获得结果*/
        int i = ps.executeUpdate();
        /*处理结果*/
        if(i == 1){
            System.out.println("修改成功");
        }else {
            System.out.println("修改失败");
        }
        /*关闭连接*/
        ps.close();
        con.close();
    }
}
```
- 3.使用预编译语句对象
  * 1使用PreparedStatement语句对象防止注入攻击
  * 2PreparedStatement 可以使用 ? 作为参数的占位符
  * 3使用?作为占位符,即使是字符串和日期类型,也不使用单独再添加 ''
  * 4connection.createStatement();获得的是普通语句对象 Statement
  * 5connection.prepareStatement(sql);可以获得一个预编译语句对象PreparedStatement
  * 6如果SQL语句中有?作为参数占位符号,那么要在执行CURD之前先设置参数
  * 7通过set***(问号的编号,数据) 方法设置参数
- 4.对实体类的要求：
  * 实体类:
  * 和数据库表格名称和字段是一一对应的类
  * 该类的对象主要用处是存储从数据库中查询出来的数据
  * 除此之外,该类没有任何的其他功能
  * 要求：
  * 1类名和表名保持一致  (见名知意)
  * 2属性个数和数据库的表的列数保持一致
  * 3属性的数据类型和列的数据类型保持一致
  * 4属性名和数据库表格的列名要保持一致
  * 5所有的属性必须都是私有的 (出于安全考虑)
  * 6实体类的属性推荐写成包装类
  * 7日期类型推荐写成java.util.Date
  * 8所有的属性都要有get和set方法
  * 9必须具备空参构造方法
  * 10实体类应当实现序列化接口 (mybatis缓存  分布式需要 )
  * 11实体类中其他构造方法可选
- 5.代码优化----Dao模型
