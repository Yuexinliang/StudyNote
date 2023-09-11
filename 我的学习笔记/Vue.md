## Vue框架
### 主要组件
```html
  <div id="app">
    <h1>我是APP组件</h1>
    <ul>
        <li><router-link to="/menu">点击显示组件menu</router-link> </li>
        <li><router-link to="/ARK">点击显示组件ARK</router-link> </li>
        <li><router-link to="/Main">点击显示组件Main</router-link> </li>
        <li><router-link to="/login">点击显示组件login</router-link> </li>
    </ul>
    <router-view></router-view>
</div>
</template>

<script>

    export default {
        //当前组件根容器的id值，也就是名字
        name: 'App'
    }
</script>
```
```html
<template>
  <div >
   <!-- 设置自定义可变属性,然后需要在被引用的组件中props里按照同样属性名接收自定义属性   -->
   <!-- props是用于接受其他组件传输的值的容器 -->
   <!-- data是用于存放自己组件创建的值的容器 -->
    <!-- <menu1 :menuval="menu01" ></menu1> -->
    <!-- 使用子窗口$emit(key,value)中的key做事件名，然后选择事件触发的方法，但是方法不能带有括号() -->
    <menu1 :menuval="menu02" @getMenuVal="showVal"></menu1>
    <div>{{ menuVal }}</div>
    <img alt="Vue logo" src="../assets/logo.png"><br>
    <!-- 使用{{}}在标签文本中获取data中的内容 -->
    <a href="">{{ stuname }}</a><br>
    <b>{{ sage }}</b><br>
    <u>{{ sex }}</u><br>
    <!-- 可进行简单计算，三元判断 -->
    <p>{{ 10 < 20 ? '正确':"错误" }}</p>
    <span>{{false ? info : "" }}</span><br>
    <span>{{ 3*8 }}</span>
    <ul>
      <!-- 等号后面是一个表达式，需要在data中进行登记才能使用 -->
      <!-- 判断为真是才进行渲染加载，假就不加载了，也就是不实现代码 -->
      <li v-if="flag01">真</li>
      <li v-else-if="flag02">假</li>
      <li v-else>不真不假</li>
    </ul>
    <!-- 实际上是提前加载，然后因判断结果进行是否隐藏 -->
    <label v-show="flag03" class="acla1">今天九月五号</label><br>
    <select >
      <!-- 循环获取数组中的值，element代表值的代号，index代表下标的代号，
      in后面接要获取的数组，key=...表示动态获取下标，然后通过{{element}}获取值 -->
      <option v-for="(element,index) in names" :key ="index" >{{ element }}</option>
    </select><br>
    <ul>
      <!-- 这样写就会出现数组个数的列表,遍历循环创建数组个数的标签 -->
      <li  v-for="(element,index) in names" :key ="index">{{ element }}</li>
    </ul>
    <table>
      <tr><td>学生编号</td><td>学生姓名</td><td>学生年龄</td></tr>
      <!--                                                带参数方法 -->
      <tr v-for="(element,index) in stus" :key ="index" @click="test02(element)">
        <td>{{ element.stuid }}</td>
        <td>{{ element.stuname }}</td>
        <td>{{ element.sage }}</td>
      </tr>
    </table>
    <!-- v-on:后接事件类型=后接方法名 -->
    <button v-on:click="but1click()">修改属性值</button>
    <!--box为CSSclass类选择器的名-  V-on可以简写成@ -->
    <div class="box" @mousemove="div1move"></div>
  
    <!-- 直接调用存储好的计算结果 -->
    <div>{{ strinfo }}</div>
    <div>{{ strinfo }}</div><br>
    <div>
      <h1>介绍vue的样式绑定</h1>
      <!--原形式为v-bind:,v-bind可省略不写。  :后接的属性都是可动态变化的  可变样式中想加入多个class样式需要使用[变量名，变量名...]
      变量名需要在data中登记，记住不是选择器名，是data中的变量名，否则就需要使用{选择器名:boolean属性变量} -->
      <a :href="hrefurl" :class="[a01,a02,{acla3:flag03}]">百度</a>
      <button @click="test03">更改超链接地址</button>
    </div>
    <!-- 双向拿值 -->
    <div>
      <h1>vue双向拿值:接受用户输入的值,同时重新显示在页面上</h1>
      <form >
        <!-- v-model=""表示可动态将value值存储到data中定义的同名变量中，也可动态获取data中初始赋值 -->
        用户名：<input type="text" name="wd" v-model="username">用户名显示：{{username}}<br>
        密码：<input type="password" name="pwd" v-model="userpwd">密码显示：{{userpwd}}<br>
        <input type="button" value="提交" @click="test04()">

      </form>
    </div>
     <!-- 直接以标签的形式使用其他组件 -->
    <!-- 同个组件之间的多个调用的事件是独立生效的 -->
    <case01></case01>
    <!-- 也可以使用这种单标签形式引用组件 -->
    <case01 @getCase01Val="showCase01Val" :sendCase01Mes="caseMes"/><br>
    <span>case01组件发送来的值:{{ info }}</span>
    <case02></case02>
    <son :sendSonVal="sonval"/>
  </div>
</template>

<script>
// 1.在其他.vue写好组件内容，设置其name属性
// 2.使用import 别名 from ""引用其他组件;
// 3.将引用来的组件放入到组件器中加载报备后才能使用
// 4.在本组件下的根容器中以标签的形式使用。
import case01 from "./case01.vue";
import case02 from "./case02.vue";
import menu1 from "./menu1.vue";
import son from "./son.vue";

export default {
  //当前组件根容器的id值，也就是名字
  name: 'Main',
  //当前页面想操作的文本内容，包含所有标签中应用的变量，可用this.调用这里的变量进行操作
  data(){
    return{
      stuname : "张三",
      sage : 20,
      sex : "男",
      info : "用户名格式不对",
      flag01:false,
      flag02:false,
      flag03:true,
      names : ["墨岚","真白","风守","云霞","千姬"],
      //整型的属性值不能加""，否则会变成字符串
      stus : [{"stuid":"001","stuname":"墨岚","sage":21},
              {"stuid":"002","stuname":"真白","sage":18},
              {"stuid":"003","stuname":"风守","sage":16},
              {"stuid":"004","stuname":"云霞","sage":23},
              {"stuid":"005","stuname":"千姬","sage":25}],
      str:"d51ds51adf45s4f14a5d8d144a5d1f5ad14d",
      hrefurl:"http://www.baidu.com",
      boxinfo:true,   
      a01:"acla1",
      a02:"acla2",  
      username: "admin",
      userpwd: "",
      // 提前准备要传入到其他组件标签中的值
      menu01:["科技新闻","军事新闻","政治新闻"],
      menu02:["娱乐新闻","内陆新闻","国际新闻","电竞新闻"],
      menuVal:"",
      caseMes:"我是主窗口传到子窗口的值",
      sonval:"我是App组件",
    }
  },
  //调用的方法
  methods:{
    but1click(even){
      //this.可以直接调用data中的属性
      alert("修改flage03的值为false");
      this.flag03=!this.flag03;
    },
    div1move(even){
      console.log(even);
      console.log("鼠标移动了！！！");
    },
    test02(asd){
      alert(asd.stuid+";"+asd.stuname+";"+asd.sage)
      asd.sage = asd.sage+1
    },
    test03(){
      //变更链接地址
      this.hrefurl ="http://www.so.com";
    },
    test04(){
      alert("用户输入的信息是"+this.username);
    },
    showVal(data){
      this.menuVal = data;
      alert(data);
    },
    showCase01Val(data){
      this.info = data;
    }
  },
  //将方法使用的结果进行存储，哪里用哪里直接调
  computed:{
    strinfo(){
      return this.str.split("1").reverse();
    },
  },
  //只要监听到监听对象值有变动，就会获取变动的值
  watch:{
    userpwd(newvalue,oldvalue){
      console.log("新值"+newvalue+";旧值"+oldvalue);
    },
  },
  // 需要将引用来的其他组件放入组件器中加载报备
  components:{
    case01,
    case02,
    menu1,
    son,
  }
}
</script>

<style lang="less">
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
  
}
table{
  margin: auto;
}
.box{
  border: red ridge 3px;
  height: 100px;
  width: 100px;
  margin: auto;
}
.acla1{
  font-size: 50px;
  background-color: aqua;
}
.acla2{
  color: wheat;
}
.acla3{
  text-decoration: none;
}

//去除无序列表的点
/* ul{
  list-style: none;
} */
</style>
```
#### 子窗口传出值到主窗口
```html
<template>
  <div>
        <ul>国内新闻

            <li v-for="(el,index ) in menuval" :key="index" >{{ el }}</li>
            <input type="button" value="子-->主窗体传值" @click="test01()"><br>
            <input type="button" value="触发修改声明周期" @click="test02()">
            <span>{{ num }}</span>
        
            <li><router-link to="/menu/tiyu">显示体育新闻</router-link></li>
            <li><router-link to="/menu/yule">显示娱乐新闻</router-link></li>
            <router-view></router-view>
            
        </ul>
  </div>
</template>

<script>
export default {
    name:"menu1",
    // 显示组件生命周期，一共8阶段
    beforeCreate(){
        console.log("menu1开始创建前");
    },
    created(){
        console.log("menu1创建后");
    },
    beforeMount(){
        console.log("menu1开始加载前");
    },
    mounted(){
        console.log("menu1加载后");
    },
    // 页面内容发生变化后触发修改。
    beforeUpdate(){
        console.log("menu1开始修改前");
    },
    updated(){
        console.log("menu1修改后");
    },
    beforeDestroy(){
        console.log("menu1开始销毁前");
    },
    destroyed(){
        console.log("menu1销毁后");
    },
    data(){
        return{
            str:"我是菜单组件",
            num:"123"
        }
    },
    props:{
        menuval:[],

    },
    methods:{
        test01(){
            // 采用$emit(key,value)方法向主窗口发送值，这里的key到了主窗口就会变成事件名
            this.$emit("getMenuVal",this.str);//$emit(key,value)
        },
        test02(){
            this.num="你好！"
        },
    }

}
</script>
```
#### 进行路由配置
```html
import Vue from 'vue'
import App from './App.vue'
import './registerServiceWorker'

// 1.导入路由管理器
import VueRouter from 'vue-router'

// 2.导入需要配置路由的组件
import menu1 from "./components/menu1.vue"
import ARK from "./components/ARK.vue"
import Main from "./components/Main.vue"
import tiyunews from "./components/tiyunews.vue"
import yulenews from "./components/yulenews.vue"
import login from "./components/login.vue"


//3.创建管理器对象进行路由配置
const router=new VueRouter({
  //使用老版本模式访问
  mode:"history",
  // 配置具体路由和组件对应关系,是一个数组，存储多个组件的路由
  routes:[
    {
      path:"/menu",
      component:menu1,
      // 二级路由不能在前面加/
      children:[
        {
          path:"tiyu",
        component:tiyunews,
        },
        {
          path:"yule",
        component:yulenews,
        }
      ]
    },
    {
      path:"/ARK",
      component:ARK
    },
    {
      path:"/Main",
      component:Main
    },
    {
      path:"/login",
      component:login
    }
  ]
});
// 4.将管理器对象加载到Vue对象中
Vue.config.productionTip = false
Vue.use(VueRouter);

// 将完成的路由对象放入Vue对象中
new Vue({
  router,
  render: h => h(App),
}).$mount('#app')
```


