#### 1. 请统计出订单表中每一位顾客的积分, 积分为消费总金额的2倍, 如会使用视图, 请使用视图更新顾客表的积分字段, 不会则手动更新
```sql
-- 请在此处编写sql语句
CREATE OR REPLACE VIEW point
AS
SELECT o.customer_id,c.customer_name, c.customer_tel, (SUM(o.order_total*2)) AS point
FROM tb_order o
         JOIN tb_customer c
              USING(customer_id)
GROUP BY o.customer_id

UPDATE tb_customer c
SET customer_point = (
    SELECT point
    FROM point p
    WHERE c.customer_id = p.customer_id
)




```
### 2. having子句和where子句的区别
```
# 请在此处答题
where字句是进行sql查询语句时的查询条件，通常在from或join之后进行条件筛选。
having子句是在分组查询后进行的二次筛选语句，通常位于group by之后。




```
### 3. 顾客表里有顾客姓名, id, 电话和积分,请使用where子查询统计订单总表(有顾客id和他们的消费金额)中单笔消费金额大于600的顾客
```sql
-- 请在此处编写sql语句
SELECT customer_id,customer_name,customer_tel,customer_point
FROM tb_customer
WHERE customer_id IN(
    SELECT customer_id
    FROM tb_order
    WHERE order_total > 600
)


```
### 4. 请回答为什么一线互联网公司严禁使用存储过程
```
# 请在此处答题
首先解释以下存储过程：
存储过程就像是java中的函数一样，是将多条sql语句包装成一个方法的过程，之后调用此存
储过程就会执行其中的所有语句。这样就大大减少了sql语句的重复操作。
存储过程的缺点：
第一：存储过程没有版本控制，更新迭代麻烦，可扩展性低，如果是正在使用中的数据库存储
过程出现的问题更是很难去更新，甚至需要停服。现在公司的项目都是分布式进行的，大多都要
求可扩展性要高，存储过程在其高便利性的基础上限制的扩展性。
第二：数据迁移问题，进行数据库数据迁移时，需要重写大量的存储过程，不仅效率低，而且
无法保证重写的存储过程都能在新数据库上正常执行。一旦出现错误，数据库就会面临大量的
数据丢失和错误。
第三：存储过程在数据库执行，一线互联网公司通常使用分布式和微服务架构，更倾向于将业
务逻辑放在应用程序中，以减少业务逻辑和数据库的耦合度，以便更好的控制和优化性能。
第四：处理异常麻烦，存储过程一旦出现失败，外围系统收到的异常信息很少，需要从大量的
语句中分析寻找异常原因，再进行sql语句错误地点的查找，这一套流程下来费时费力。开发
效率很低。
```
### 5. 请说出至少4点sql优化规则
```
# 请在此处答题
1.不要在查询中使用*
2.查询过程要尽量使用索引
3.避免在索引上使用函数
4.where中like的%尽量放在右边。



```
### 6. 请根据一张顾客表查询出顾客的姓名, id, 电话, 积分级别(查询自设字段),但是要根据顾客的积分赋予不同的积分级别, 大于500白银会员, 大于800黄金会员, 大于1000白金会员
```sql
-- 请在此处编写sql语句
SELECT *,
       CASE
           WHEN customer_point > 1000 THEN
               "白金会员"
           WHEN customer_point > 800 THEN
               "黄金会员"
           WHEN customer_point > 500 THEN
               "白银会员"
           END AS level
FROM tb_customer




```
### 7. 请回答sql语句中all关键字和any关键字的区别
```
# 请在此处答题
一般在进行范围比对时会用到all和any，例如比较大于或小于
all语句在进行范围比对时，条件是all（）内的所有数据。
any语句在进行范围比对时，条件是any（）内的任意一个数据。



```
### 8.(选做题) 顾客表里有顾客姓名, id, 电话和积分,请使用where子查询统计订单总表(有顾客id和他们的消费金额)中总消费金额大于600的顾客
```sql
-- 请在此处编写sql语句
第一种方法：优点：具有总价钱total，缺点：没有完全符合题中要求在where后使用子查询的要求
SELECT customer_id,customer_name,customer_tel,customer_point,total
FROM tb_customer c
         JOIN (
    SELECT customer_id ,SUM(order_total) AS total
    FROM tb_order
    GROUP BY customer_id
) o
              USING(customer_id)
WHERE total > 600

第二种方法：优点：使用where子查询，缺点：没有总价钱total
SELECT customer_id,customer_name,customer_tel,customer_point
FROM tb_customer c
WHERE 600 < (
    SELECT SUM(order_total) AS total
    FROM tb_order
    WHERE customer_id = c.customer_id
)






```




















































```
select *
from tb_customer
join (select t.customer_id, sum(order_total) as total
from tb_order t group by customer_id) as tb_total
using (customer_id)
where tb_total.total > 1000
```