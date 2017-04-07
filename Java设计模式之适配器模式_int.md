# Java设计模式之适配器模式

[TOC]

## 说明

笔记本上的那个拖在外面的黑盒子（电源）就是个适配器，一般你在中国能用，在日本也能用，虽然两个国家的的电源电压不同，中国是 220V，日本是 110V，但是这个适配器能够把这些不同的电压转换为你需要的 36V 电压，保证你的笔记本能够正常运行，那我们在设计模式中引入这个适配器模式是不是也是这个意思呢？是的，一样的作用，两个不同接口，有不同的实现，但是某一天突然上帝命令你把 B 接口转换为 A 接口，怎么办？继承，能解决，但是比较傻，而且还违背了 OCP 原则，怎么办？好在我们还有适配器模式。

本质就是把一个接口的实现类包装（wrapper）成另外一个接口的实现类

### 定义

适配器模式就是把一耳光类的接口转换成客户端所期待的另外一种接口，从而使原本接口不匹配的两个类能够在一起工作。

## 实现

有两种实现模式，一种是类适配器模式，另外一种是对象适配器模式，都以220v电压转到5v电压为例

### 类适配器模式

![diagram201704070104](http://oaxelf1sk.bkt.clouddn.com/diagram201704070104.png)

```
/**
 * @author www.haotianyi.win
 * @version V1.0
 * @Description: 原接口
 * @date 2017/4/7 0:59
 */
public interface IVolt220 {
    int getVolt220();
}
```

目标接口：

```
public interface IFiveVolt {
    int getVolt5();
}
```

两者的实现方法：

```
public class FiveVolt implements IFiveVolt {
    @Override
    public int getVolt5() {
        return 5;
    }
}

public class Volt220 implements IVolt220 {
    @Override
    public int getVolt220() {
        return 220;
    }
}
```

适配器类，注意写法：

```
public class Adapter extends Volt220 implements IFiveVolt{
    @Override
    public int getVolt5() {
        return 5;
    }
}
```

就是继承原类（实现了原接口），实现了目标接口的一个Java类，客户端的使用：

```
public class Client {
    public static void main(String[] args) {
        Adapter adapter = new Adapter();
        System.out.println(adapter.getVolt5());
    }
}
```

### 对象适配器模式

![diagram201704070111](http://oaxelf1sk.bkt.clouddn.com/diagram201704070111.png)

更改适配器的代码：

```
public class Adapter2 implements IFiveVolt{

    Volt220 mVolt220;

    public Adapter2(Volt220 volt220) {
        mVolt220 = volt220;
    }

    public int getVolt220() {
        return 220;
    }

    @Override
    public int getVolt5() {
        return 5;
    }
}
```

注意这里把继承变成了包含Volt220的实例对象，但是构造函数有一个参数是Volt220的实例，还是继续继承目标接口，从而实现转化，客户端更新：

```
public class Client {
    public static void main(String[] args) {
        Adapter2 adapter = new Adapter2(new Volt220());
        System.out.println(adapter.getVolt5());
    }
}
```

