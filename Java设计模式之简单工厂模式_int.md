# Java设计模式之三种工厂模式

[TOC]



## 工厂模式简介

工厂模式在《Java与模式》中分为三类：
1）简单工厂模式（Simple Factory）：不利于产生系列产品；

2）工厂方法模式（Factory Method）：又称为多形性工厂；

3）抽象工厂模式（Abstract Factory）：又称为工具箱，产生产品族，但不利于产生新的产品；
             这三种模式从上到下逐步抽象，并且更具一般性。
             GOF在《设计模式》一书中将工厂模式分为两类：工厂方法模式（Factory Method）与抽象工厂模式（Abstract Factory）。将简单工厂模式（Simple Factory）看为工厂方法模式的一种特例，两者归为一类。

-----------------------

简单工厂模式（Simple Factory Pattern）属于类的创新型模式，又叫静态工厂方法模式（Static FactoryMethod Pattern）,是通过专门定义一个类来负责创建其他类的实例，被创建的实例通常都具有共同的父类。

简单工厂模式解决的问题是如何去实例化一个合适的对象。

       简单工厂模式的核心思想就是：有一个专门的类来负责创建实例的过程。

       具体来说，把产品看着是一系列的类的集合，这些类是由某个抽象类或者接口派生出来的一个对象树。而工厂类用来产生一个合适的对象来满足客户的要求。

       如果简单工厂模式所涉及到的具体产品之间没有共同的逻辑，那么我们就可以使用接口来扮演抽象产品的角色；如果具体产品之间有功能的逻辑或，我们就必须把这些共同的东西提取出来，放在一个抽象类中，然后让具体产品继承抽象类。为实现更好复用的目的，共同的东西总是应该抽象出来的。

## 简单工厂模式实现

### BasePerson的实现

```
/**
 * @author HaoTianYi  hao.ty@haotianyi.win
 * @version v1.0
 * 表示人物的基本类
 */
public abstract class BasePerson {
    /**
     * 英雄可以说话
     */
    public abstract BasePerson talk();

    /**
     * 英雄可以行走
     */
    public abstract BasePerson move();

    /**
     * 英雄可以打人
     */
    public abstract BasePerson hit();
}
```

### 两个人物类

```
public class DeMaPerson extends BasePerson {
    /**
     * 英雄可以说话
     */
    @Override
    public BasePerson talk() {
        System.out.println("德玛西亚------talk");
        return this;
    }

    /**
     * 英雄可以行走
     */
    @Override
    public BasePerson move() {
        System.out.println("德玛西亚------move");
        return this;
    }

    /**
     * 英雄可以打人
     */
    @Override
    public BasePerson hit() {
        System.out.println("德玛西亚------hit");
        return this;
    }
}
```

```
public class HouZiPerson extends BasePerson {
    /**
     * 英雄可以说话
     */
    @Override
    public BasePerson talk() {
        System.out.println("猴子------talk");
        return this;
    }

    /**
     * 英雄可以行走
     */
    @Override
    public BasePerson move() {
        System.out.println("猴子------move");
        return this;
    }

    /**
     * 英雄可以打人
     */
    @Override
    public BasePerson hit() {
        System.out.println("猴子------hit");
        return this;
    }
}
```

### 客户端类

```
public abstract class Client{

    public static void main(String[] args) {
        BasePerson deMaPerson = PersonFactory.createPerson(DeMaPerson.class);
        deMaPerson.hit().move().talk();

        BasePerson houZiPerson = PersonFactory.createPerson(HouZiPerson.class);
        houZiPerson.hit().move().talk();
    }
}
```

### 工厂类

```
public class PersonFactory {
    public static BasePerson createPerson(Class c){
        BasePerson basePerson = null;
        try {
            basePerson = (BasePerson) Class.forName(c.getName()).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return basePerson;
    }
}
```

![sp161129_163240](http://oaxelf1sk.bkt.clouddn.com/sp161129_163240.png)

## 简单工厂模式优化

工厂方法模式有一个非常重要的应用，就是延迟始化(Lazy initialization)，什么是延迟始化呢？
一个对象初始化完毕后就不释放，等到再次用到得就不用再次初始化了，直接从内存过中拿到就可以了，就是在Factory中添加一个Map

```
public class PersonFactory {
    private static Map<String,BasePerson> mBasePersons = new HashMap<>();

    public static BasePerson createPerson(Class c) {
        if (mBasePersons.containsKey(c.getSimpleName())){
            return mBasePersons.get(c.getSimpleName());
        }
        BasePerson basePerson = null;
        try {
            basePerson = (BasePerson) Class.forName(c.getName()).newInstance();
            mBasePersons.put(c.getSimpleName(),basePerson);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return basePerson;
    }
}
```

## 工厂模式的简介

是定义一个创建产品对象的工厂接口，让子类决定实例化哪一个类，将实际创建工作推迟到子类当中。

**主要解决：**主要解决接口选择的问题。

**何时使用：**我们明确地计划不同条件下创建不同实例时。

**如何解决：**让其子类实现工厂接口，返回的也是一个抽象的产品。

**关键代码：**创建过程在其子类执行。

## 工厂模式的基本实现

![diagram11291640](http://oaxelf1sk.bkt.clouddn.com/diagram11291640.png)

在上面的代码中增加三个类，更改客户端的实现代码

### 工厂类

```
public abstract class BaseFactory {
    public abstract BasePerson createPerson();
}
```

```
public class DeMaFactory extends BaseFactory {
    @Override
    public  BasePerson createPerson() {
        return new DeMaPerson();
    }
}
```

### 客户端的实现

```
public abstract class Client{

    public static void main(String[] args) {
        new DeMaFactory().createPerson().talk().move().hit();
        new HouZiFactory().createPerson().hit().move().talk();
    }
}
```

![sp161129_164349](http://oaxelf1sk.bkt.clouddn.com/sp161129_164349.png)

## 抽象工厂简介

提供一个创建一系列相关或相互依赖对象的接口，而无需指定它们具体的类；

抽象工厂模式（Abstract Factory Pattern）是围绕一个超级工厂创建其他工厂。该超级工厂又称为其他工厂的工厂。这种类型的设计模式属于创建型模式，它提供了一种创建对象的最佳方式。

**何时使用：**系统的产品有多于一个的产品族，而系统只消费其中某一族的产品。

**优点：**当一个产品族中的多个对象被设计成一起工作时，它能保证客户端始终只使用同一个产品族中的对象。

简单来说就是创建一套相关的对象

## 以LOL下路英雄为例

LOL中下路永远是两个人（对象），所以当下路有人的时候应该是两个一起创建，就是相关的类

![diagram11291807](http://oaxelf1sk.bkt.clouddn.com/diagram11291807.png)

一组产品是寒冰和火男；一组是男枪和机器人。

### 工厂类

```
public abstract class AbstractFactory {
    public abstract Adc createAdc();
    public abstract FuZhu createFuZhu();
}
```

```
public class XiaLuFactory1 extends AbstractFactory {
    @Override
    public Adc createAdc() {
        return new NanQiangAdc();
    }

    @Override
    public FuZhu createFuZhu() {
        return new JiQiRenFuZhu();
    }
}
```

### 人物类

```
public abstract class Adc {
    /**
     * adc输出的方法
     */
    public abstract void hit();
}
```

```
public abstract class FuZhu {
    /**
     * 保护adc的方法
     */
    public abstract void protect();
}
```

```
public class HanBingAdc extends Adc {
    /**
     * adc输出的方法
     */
    @Override
    public void hit() {
        System.out.println("寒冰-----------hit");
    }
}
```

```
public class HuoNanFuZhu extends FuZhu {
    /**
     * 保护adc的方法
     */
    @Override
    public void protect() {
        System.out.println("火男-----------protect");
    }
}
```

其他类似

### 客户端的实现

```
public class Client {
    public static void main(String[] args) {
        XiaLuFactory1 factory1 = new XiaLuFactory1();
        factory1.createAdc().hit();
        factory1.createFuZhu().protect();

        XiaLuFactory2 factory2 = new XiaLuFactory2();
        factory2.createAdc().hit();
        factory2.createFuZhu().protect();
    }
}
```

![sp161129_180315](http://oaxelf1sk.bkt.clouddn.com/sp161129_180315.png)

## 改进

把人物再次提取出一个共有的方法

![diagram11291820](http://oaxelf1sk.bkt.clouddn.com/diagram11291820.png)

然后让两个抽象类分别实现Person接口，最后实现interseting方法

### 抽象类的实现

```
public abstract class Adc implements Person{
    /**
     * adc输出的方法
     */
    public abstract Person hit();
}
```

### 具体类的实现

```
/**
 * @author HaoTianYi  hao.ty@haotianyi.win
 * @version v1.0
 * 模拟寒冰的类
 */
public class HanBingAdc extends Adc {
    /**
     * adc输出的方法
     */
    @Override
    public Adc hit() {
        System.out.println("寒冰-----------hit");
        return this;
    }

    /**
     * 每一个英雄有很有趣
     */
    @Override
    public void interesting() {
        System.out.println("寒冰-----------interesting");
    }
}
```

### 客户端的更改

```
public class Client {
    public static void main(String[] args) {
        XiaLuFactory1 factory1 = new XiaLuFactory1();
        factory1.createAdc().hit().interesting();
        factory1.createFuZhu().protect().interesting();

        XiaLuFactory2 factory2 = new XiaLuFactory2();
        factory2.createAdc().hit().interesting();
        factory2.createFuZhu().protect().interesting();
    }
}
```

