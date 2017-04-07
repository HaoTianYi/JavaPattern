# Java设计模式之桥接模式

[TOC]

## 定义

将抽象部分和实现部分分离，使得他们可以独立的进行变化，也叫做桥梁模式。

如果一个系统在构建的抽象化角色和具体角色之间增加更多的灵活性，避免在两个层次之间建立静态联系，可以使用桥接模式。

## 简单实现

比如喝咖啡，可以有大杯小杯的区别也有口味的区别，使用桥接模式：

![diagram201704070143](http://oaxelf1sk.bkt.clouddn.com/diagram201704070143.png)

本质就是在一个类中**含有**另外一个类的引用，从而使两者的子类可以自由的变化

coffe类：

```
public abstract class Coffee {
    protected CoffeeAdd mCoffeeAdd;

    public Coffee(CoffeeAdd coffeeAdd) {
        mCoffeeAdd = coffeeAdd;
    }

    /**
     * 制作咖啡
     */
    abstract void makeCoffee();
}
```

coddeeadd类：

```
public abstract class CoffeeAdd {
    /**
     * 向咖啡中添加东西
     */
    abstract String addSomething();
}
```

两者的实现类：

```
public class AddNull extends CoffeeAdd {
    @Override
    String addSomething() {
        return null;
    }
}


public class AddSugar extends CoffeeAdd {
    @Override
    String addSomething() {
        return "糖";
    }
}



public class LargeCoffee extends Coffee {

    public LargeCoffee(CoffeeAdd coffeeAdd) {
        super(coffeeAdd);
    }

    @Override
    void makeCoffee() {
        System.out.println("大杯的"+mCoffeeAdd.addSomething());
    }
}

public class SmallCoffee extends Coffee {

    public SmallCoffee(CoffeeAdd coffeeAdd) {
        super(coffeeAdd);
    }

    @Override
    void makeCoffee() {
        System.out.println("小杯的"+mCoffeeAdd.addSomething());
    }
}
```

客户端的调用：

```
public class Client {
    public static void main(String[] args) {
        new LargeCoffee(new AddNull()).makeCoffee();
        new SmallCoffee(new AddSugar()).makeCoffee();
    }
}
```

完整的结构图：

![diagram201704070148](http://oaxelf1sk.bkt.clouddn.com/diagram201704070148.png)

最重要的就是两个顶层类（或者接口）的包含关系
