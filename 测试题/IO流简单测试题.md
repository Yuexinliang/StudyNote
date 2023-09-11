### 1. 什么是序列化流, 什么是反序列化流
```
#在此区域进行答题
序列化流就是在使用对象流时，需要对传输的对象进行可序列化操作，这样才能使用对象流将
内存对象转化为序列化流的形式输出到物理文件中去，当使用输入对象流进行序列化的对象的
还原时，就需要进行一个反序列化操作，这样做的目的是为了在本地对象文件进行更改后还可以
根据UID进行判断序列化的对象和本地对象文件是不是同一版本，判断后将序列化对象反序列回
内存中保存。
```
### 2. 用IO流读取城市id.txt文件的内容, 将内容存入map集合中, 城市名称作为key, id作为值
```java
package GroupWorkIO;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class IOMap {
    public static void main(String[] args) throws IOException {
        HashMap<String,String> citys = new HashMap<>();
        BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\16608\\Desktop\\城市id.txt"));
        String str = br.readLine();
        System.out.println(str);
        String[] strs = str.split(",");
        /*循环存入Map*/
        for (String s:strs) {
            citys.put(s.split("=>")[0],s.split("=>")[1]);
        }
        System.out.println(citys);
        br.close();
    }
}
```
### 3. TCP和UDP的区别, 请分点回答
```
#在此区域进行答题
TCP协议时面向连接的协议，可靠性高，发送信息回进行确认，并有超时重发机制，适合传输
大量数据。
UDP协议是无连接协议，不可靠，只负责发送信息，不回确认信息是否到达，适合发送小量数据。
```

### 4. 编写一段客户端登录注册程序, 注册程序利用用户类, 将用户的密码, 账户名封装并且序列化进本地的User.txt文件里, 登录程序将Usr.txt文件进行反序列, 进行登录的密码用户名验证
```java
/**
 * 用户类
 */
public class User implements Serializable {
    private static final long serialVersionUID = 5815801903108783216L;
    private String username;
    private String password;

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * 获取
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    public String toString() {
        return "User{username = " + username + ", password = " + password + "}";
    }
}
```

```java
/**
 * 注册程序
 */
package GroupWorkIO;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class sign {
    /*注册*/
    public static void signIn(String name, String pwd) throws IOException {
        User user = new User(name,pwd);
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("C:\\Users\\16608\\Desktop\\User.txt"));
        oos.writeObject(user);
        System.out.println("注册成功！");
        oos.close();
    }
}

```
```java
/**
 * 登录程序
 */
package GroupWorkIO;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class log {
    /*登录*/
    public static void login(String name, String pwd) throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("C:\\Users\\16608\\Desktop\\User.txt"));
        User user = (User)ois.readObject();
        if (user.getUsername().equals(name) && user.getPassword().equals(pwd) ){
            System.out.println("登录成功");
        }else if (user.getUsername().equals(name) && !user.getPassword().equals(pwd) ){
            System.out.println("密码不正确");
        }else {
            System.out.println("此用户不存在");
        }
        ois.close();
    }
}

```
### 5. TCP三次握手, 四次挥手的过程
```
#在此区域进行答题
TCP的三次握手是指，客户端先向服务器发送连接请求，服务端回复确认收到连接请求，客户端连接后
再向服务端发送已确认连接成功。
四次挥手的过程是，客户端发送给服务端一个关闭连接的信息，服务端回复已收到关闭连接信息，
再发送给客户端一个服务端关闭连接的信息，之后客户端再向服务端回复一个确认关闭连接的信息。

```

### 6. 利用IO流和File类实现文本文件的剪切, 讲一个文件夹里的至少两个文件剪切到另外一个文件夹里
```java
/**
 * 这里写上你的java代码
 */
package GroupWorkIO;

import java.io.*;

public class FileCut {
    public static void main(String[] args) throws IOException {
        File sourceFile = new File("C:\\Users\\16608\\Desktop\\新建文件夹");
        File[] files = sourceFile.listFiles();
        for (int i = 0; i <files.length ; i++) {
            if (files[i].isFile()){
                if (files[i].getName().endsWith(".txt")){
                    BufferedReader br = new BufferedReader(new FileReader(files[i]));
                    String targetFile = new String("D:\\Program Files (x86)\\develop"+File.separator+files[i].getName());
                    BufferedWriter bw = new BufferedWriter(new FileWriter(targetFile));
                    String str = null;
                    while ((str = br.readLine()) != null){
                        bw.write(str);
                        bw.newLine();
                    }
                    bw.close();
                    br.close();
                    files[i].delete();
                }
            }
        }
    }
}
```
