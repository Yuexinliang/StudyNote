# JavaScript
## 1.引入JavaScript
一切数据都由客户端浏览器，将请求发送到服务器端去做校验。

### 特点：
- 1.JavaScript是运行在浏览器端的。`阻断式解释执行的前端脚本语言`
- 2.弱类型语言：声明变量的时候不需要指定变量类型。
- 3.主要作用是做前端的数据校验，用户交互。
- 4.基于对象：
- 5.事件驱动：在网页中执行了某种操作的动作，被称为"事件"(Event)，比如按下鼠标、
移动窗口、选择菜单等都可以视为事件。当事件发生后，可能会引起相应的事件响应。
- 6.安全性：JavaScript不能访问本地的硬盘，不能将数据存入到服务器上，不能对
网络文档进行修改和删除，只能通过浏览器实现信息浏览或动态交互。
- 7.跨平台性：JavaScript依赖于浏览器本身，与操作平台无关， 只要计算机安装了支持
JavaScript的浏览器（装有JavaScript解释器），JavaScript程序就可以正确执行。
缺点 ：各种浏览器支持JavaScript的程度是不一样的，支持和不完全支持JavaScript的 
浏览器在浏览同一个带有JavaScript脚本的网页时，效果会有一定的差距，有时甚至会显示
不出来。
- 8.动态语言：程序在运行期间仍然可以动态改变程序结构。
### JavaScript三部分

![img.png](img/img130.png)

## 2.JavaScript入门
- 1.Script的位置：
学习期间写在head中，开发过程放在body下方。
- 2.想要script代码受控，需要把script代码放在方法中，在body中相应事件调用script中的
方法。
- 3.一个事件可以调用多个方法，一个方法可以被多个事件调用。
- 4.script标签一但用于引入外部JS文件,就不能在中间定义内嵌式代码，一个页面上可以用
多个script标签，一个标签只能应用引入和定义其中一种方法。

## 3.ECMAScript语法
### 1.输出
`弹框输出alert`：alert("我来阻断");

`页面打印document.write`：document.write(i+"*"+j+"="+i*j+"&nbsp;&nbsp");

`控制台打印console：`
console.log();
console.warn();
console.error();
console.info(); 

### 2.`弹框输入prompt`：let name = prompt("你的名字是：");用一个变量来接收。

### 3.变量：声明，赋值，使用。
  - 1.常量：const age = 20;声明同时赋值。
  - 2.方法中局部变量：let age;age = 20;
  - 3.成员变量：var age = 25; 
  【声明变量时，关键字不能省略，如果一个变量什么关键字也没写，使用一次后，自动变为成员变量】
  - 4.变量类型：
    - 1.`数值型`：number整数和浮点数统称为数值。例如85或3.1415926等。
    - 2.`字符串型`：String由0个,1个或多个字符组成的序列。在JavaScript中，用双引
    号或单引号括起来表示，如"您好"、'学习JavaScript' 等。
    - 3.`逻辑（布尔）型`：boolean用true或false来表示。
    - 4.`空（null）值`：表示没有值，用于定义空的或不存在的引用。要注意，空值不等同
    于空字符串""或0。
    - 5.`未定义（undefined）值`：它也是一个保留字。表示变量虽然已经声明，但却没有
    赋值。
    - 6.除了以上五种普通的数据类型（包括三个基本：number，string，boolean，
    两个特殊：null，undefined）之外，JavaScript还支持复合数据类型Object，
    复合数据类型包括`对象和数组`两种。 
    ![img_1.png](img/img_131.png)
    【JS中如果出现除零,那么结果是 infinity,而不是报错】
    
    ![img_2.png](img/img_132.png)

    【JS取余数运算对于浮点数仍然有效,如果和0取余数,结果是NaN(not a number)】

    ![img_3.png](img/img_133.png)
### 4.流程控制：
基本与java相同。

















