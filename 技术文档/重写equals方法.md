# 重写equals方法

### 1.为什么要重写equals方法？

equals用于比较两个数据，常用的是用来比较String字符串。

```java
public class test {
    public static void main(String[] args) {
        String a = new String("abc");
        String b = new String("abc");
        String c = "abc";
        String d = "abc";
        System.out.println(c == d);//true
        System.out.println(a == b);//false
        System.out.println(a.equals(b));//true
    }
}
```

我们都知道`“==”`比较的是数据的存储地址，c和d被赋值的都是常量池的“abc”，所以地址相同
但是new出来的字符串，每个都是一个新的地址，所以无法用“==”比较，所以就用到了equals
方法，但这里比较用的方法是String类重写过后的方法。<br>
String重写的equals方法

```
public boolean equals(Object anObject) {
        if (this == anObject) {
            return true;
        }
        if (anObject instanceof String) {
            String anotherString = (String)anObject;
            int n = value.length;
            if (n == anotherString.value.length) {
                char v1[] = value;
                char v2[] = anotherString.value;
                int i = 0;
                while (n-- != 0) {
                    if (v1[i] != v2[i])
                        return false;
                    i++;
                }
                return true;
            }
        }
        return false;
    }
```

Object类的equals方法

```
 public boolean equals(Object obj) {
        return (this == obj);
    }
```

比较上面两个方法可以看到，默认继承的Object类的equals方法也只是使用了“==”进行地址
比较，无法实现内容比较，所以String类重写了equals方法，以实现比较内容的需求，当我
们新建自己的类时，也需要重写我们自己的equals方法来实现比较要求。

### 2.如何重写equals方法

重写equals的方法大致可分为四步

- 1.判断数据地址
- 2.判断引用对象是否为空
- 3.判断数据类型
- 4.进行内容判断
  下面来看一段重写完equals的完整类代码：

```java
package com.jiruan;

public class Girl {

    //属性：
    private int age;
    private String name;
    private int phoneNumber;

    //读取年龄：
    public int getAge() {
        return age;
    }

    //设置年龄：
    public void setAge(int age) {
        if (age >= 30) {
            this.age = 18;
        } else {
            this.age = age;
        }
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //空构造器
    public Girl() {

    }

    //构造器重载
    public Girl(int age, String name, int phoneNumber) {
        /*其他类在构造器中的直接赋值不会被private限制
        应避免直接赋值private属性变量
        this.age= age;
        */
        this.setAge(age);
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    /*
    定义了一个toString方法，用于将一个对象转换成字符串形式，
    通常用于输出或日记记录等操作，当调用该方法时，它将返回一个
    字符串代表该对象的值。使用toString方法输出对象信息时使用
    System.out.println(girl);就能直接输出return定义的字
    符串属性，无需使用getage等获取属性
    */
    public String toString() {
        return "年龄：" + age + "姓名：" + name + "电话：" + phoneNumber;
    }

    /*equals方法重写*/
    public boolean equals(Object obj) {
//        判断地址
        if (this == obj) {
            return true;
        }
//        判断取值是否为空
        if (obj == null || this == null) {
            return false;
        }
//        判断是否是同一类型
        if (obj instanceof Girl) {
            Girl girl = (Girl) obj;
//            判断内容
            if (this.age == girl.age && this.phoneNumber == girl.phoneNumber
                    && this.name.equals(girl.name)) {
                return true;
            }
        }
        return false;
    }
}
```

上面编写的是一个Girl类
这是重写的equals方法：

```
    /*equals方法重写*/
    public boolean equals(Object obj) {
//       1. 判断地址
        if (this == obj) {
            return true;
        }
//       2. 判断取值是否为空.
        if (obj == null || this == null){
            return false;
        }
//       3. 判断是否是同一类型的对象
        if (obj instanceof Girl){
            Girl girl = (Girl)obj;
//          4.  判断内容
            if (this.age == girl.age && this.phoneNumber == girl.phoneNumber
                && this.name.equals(girl.name)){
                return true;
            }
        }
        return false;
    }
}
```

- 5.plus 形参向上转型
```
public boolean equals(Object obj) {}
```
向上转型就是将子类的实例对象，引用向父类的对象（或指将父类的对象引用指向子类的实例对象）
这样就可以接受所有类型的对象进行判断，此处采用的形式参数的类型为Object类，为顶级
父类，所有类型都可以向上转型为顶级父类Object。

- 1.首先第一步先判断地址。

```
//   1. 判断地址
    if (this == obj) {
        return true;
    }
```

为什么要首先用`“==”`判断地址？<br>
因为数据内容是存储在内存地址中的，如果两个数据存储的地址相同，那就证明两个地址内
存储的内容相同，就不用进行接下来的判断了

- 2.判断取值是否为空

```
//       2. 判断取值是否为空
if (obj == null ){
return false;
}
```

当取值为null（空）时，可以直接判定两个数据不同，直接返回false<br>
【再多提一下，null是可以用“==”来进行比较的，两个null进行“==”比较时返回的值是
true，但equals这里不会发生这种情况，因为equals属于调用方法，而null是不能调用
任何方法的，否则会产生空指针异常。】

- 3.判断是否为同一类型的对象

```
//  3. 判断是否是同一类型的对象
   if (obj instanceof Girl){
   //向下转型
       Girl girl = (Girl)obj;
       //…………
   return false;  
```

就像手机和手机可以进行比较，但手机和电脑就不太可能进行比较，因为它们是完全不同
的类型，equals方法也是同理，如果数据类型都不同的话就比较不了，直接返回false。
判断的方法使用`“instanceof”,  a instanceof b----->`判断a对象是否是b这个类
的实例对象,如果是则返回true，这样可以`避免发生类型转换异常`，判断为true后则进行
向下转型。<br>

再来说说什么是`向下转型`，就是`将父类对象转成子类对象的过程`，子类继承了父类的内容，
但子类可以额外拓展其他属性变量和方法的，这些父类是无法调用的，所以需要进行向下
转型，将父类强制转为子类，`是为了获取子类内的特殊内容`，用以后面内容判断时进行方
法调用,这里进行向下转型是因为在输入实参时进行了向上转型，现在实参的对象表面是Object类
实际时实参的类型，所以需要将Object类进行向下转型回同一类型【ps.向上转型就不需要强制转型】

- 4.判断内容

```
//   4.  判断内容
     if (this.age == girl.age && this.phoneNumber == girl.phoneNumber
         && this.name.equals(girl.name)){
         return true;
     }
```
最后进行内容判断，判断条件自己来定，引用数据类型就用equals方法来判断，当然是人家重
写后的equals方法，基本数据类型就用“==”来进行判断。

### 3.总结
重写equals方法大致可分为4步，1.判断数据地址 ，2.判断引用对象是否为空， 3.判断数据类型，
4.进行内容判断，值得着重注意的是判断类型里的向下转型和判断内容中的，判断条件设置。

--+



