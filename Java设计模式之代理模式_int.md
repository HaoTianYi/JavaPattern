# Java设计模式之代理模式

[TOC]

## 说明

普通对象可以通过公共接口完成自己的工作，但是，有一些对象无法履行自己日常的职责。例如，对象的加载时间过长，有的对象运行在其他计算机上，对于这样的场景，我们引入代理模式，通过代理人把相应的请求发送到目标对象。

## 静态代理模式

以国内的人购买奥迪汽车为例，奥迪在中国有自己的代理人，当你想购买一辆奥迪车的时候，代理厂家会把请求发送到德国总部，然后再把汽车发送给你。

在静态Proxy（代理）模式中，代理人和原事件爱你哒处理者都会继承同一个接口，客户端向代理人发出请求

![diagram201704061444](http://oaxelf1sk.bkt.clouddn.com/diagram201704061444.png)

### 接口类

很简单就是一个卖车的方法

```java
/**
 * @author www.haotianyi.win
 * @version V1.0
 * @Description: 奥迪汽车
 * @date 2017/4/6 14:33
 */
public interface AudiCar {
    void sellCar();
}
```

### 具体实现

事件的处理者（德国汽车总部）：

```java
public class GermanySeller implements AudiCar{
    @Override
    public void sellCar() {
        System.out.println("----德国总部卖出一辆车----");
    }
}
```

代理人（中国汽车代理）：

```java
/**
 * @author www.haotianyi.win
 * @version V1.0
 * @Description: 奥迪中国汽车总经销商
 * @date 2017/4/6 14:34
 */
public class ChinaProxy implements AudiCar{

    private AudiCar mAudiCar;

    public ChinaProxy() {
        mAudiCar = new GermanySeller();
    }

    public ChinaProxy(AudiCar audiCar) {
        mAudiCar = audiCar;
    }

    @Override
    public void sellCar() {
        System.out.println("----中国代理----");
        mAudiCar.sellCar();
    }
}
```

客户端：

```java
public class Cilent {
    public static void main(String[] args) {
//        要购买车找代理人
        ChinaProxy chinaSeller = new ChinaProxy();
        chinaSeller.sellCar();
    }
}
```

最后：

```java
----中国代理----
----德国总部卖出一辆车----
```

### 代理模式应用

代理者可以在不改变源对象的情况下，做出一些逻辑，更改ChinaProxy，增加一个表示人民币的变量money，并且只有在miney`>`50万的情况下，才能买车，否则只是弹出一句话：

```java
public class ChinaProxy implements AudiCar{

    private AudiCar mAudiCar;
    private int money;

    public ChinaProxy() {
        mAudiCar = new GermanySeller();
    }

    public ChinaProxy(AudiCar audiCar) {
        mAudiCar = audiCar;
    }

    @Override
    public void sellCar() {
        System.out.println("----中国代理----");
        if (money<50){
            System.out.println("少年，钱太少了");
        }else {
            mAudiCar.sellCar();
        }
    }

    public AudiCar setMoney(int money) {
        this.money = money;
        return this;
    }
}
```

对应的客户端：

```java
public class Cilent {
    public static void main(String[] args) {
//        要购买车找代理人
        ChinaProxy chinaSeller = new ChinaProxy();
        chinaSeller.setMoney(45).sellCar();
    }
}
```

运行结果：

```
----中国代理----
少年，钱太少了
```

### 优缺点

优点：可以做到在不修改目标对象的功能前提下,对目标功能扩展

缺点：因为代理对象需要与目标对象实现一样的接口,所以会有很多代理类,类太多.同时,一旦接口增加方法,目标对象与代理对象都要维护

## 动态代理

解决静态代理的缺点可以使用动态代理：

![diagram201704061525](http://oaxelf1sk.bkt.clouddn.com/diagram201704061525.png)

可以看出ChinaProxy和AudiCar没有任何关系，同样有根据钱数判断是否卖车的功能

### ChinaProxy

```java
/**
 * @author www.haotianyi.win
 * @version V1.0
 * @Description: 奥迪中国汽车总经销商
 * @date 2017/4/6 14:34
 */
public class ChinaProxy implements InvocationHandler {

    private Object china;
    private int menoy;

    public ChinaProxy(Object china, int menoy) {
        this.china = china;
        this.menoy = menoy;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("奥迪中国汽车总经销商");
        if (menoy < 50) {
            return null;
        } else {
            return method.invoke(china, args);
        }
    }
}
```

### 具体实现

客户端的更新：

```java
public class Cilent {
    public static void main(String[] args) {
        AudiCar audiCar = (AudiCar) Proxy.newProxyInstance(AudiCar.class.getClassLoader(),
                new Class[]{AudiCar.class},
                new ChinaProxy(new GermanySeller(),46));
//       及时返回为null，也不会报出空指针，在newProxyInstance内部已经判断是否为空了
        audiCar.sellCar();
    }
}
```

## 参考

http://www.cnblogs.com/cenyu/p/6289209.html

http://www.cnblogs.com/flyoung2008/archive/2013/08/11/3251148.html

http://blog.csdn.net/jason0539/article/details/22974405



