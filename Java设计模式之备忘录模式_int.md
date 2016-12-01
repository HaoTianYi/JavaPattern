# Java设计模式之备忘录模式



## 备忘录模式简介

备忘录模式（Memento Pattern）保存一个对象的某个状态，以便在适当的时候恢复对象。备忘录模式属于行为型模式。

所谓备忘录模式就是在**不破坏封装**的前提下，捕获一个对象的内部状态，并在该对象之外保存这个状态，这样可以在以后将对象恢复到原先保存的状态。

## 备忘录模式简单实现

![diagram12011445](http://oaxelf1sk.bkt.clouddn.com/diagram12011445.png)

**发起人Originator：**记录当前时刻的内部状态，负责定义哪些属于备份范围的状态，负责创建和恢复备忘录数据。

**备忘录Memento：**负责存储发起人对象的内部状态，在需要的时候提供发起人需要的内部状态。

**管理角色Caretaker：**对备忘录进行管理，保存和提供备忘录。

### 备忘录的实现

通过刚刚的介绍，其实备忘录模式就是游戏的存储存档和恢复存档。这里以文明6这个游戏为例，假设这个游戏有两个属相：金钱和人数；

```
/**
 * @author HaoTianYi  hao.ty@haotianyi.win
 * @version v1.0
 *          表示文明6游戏类
 */
public class Originator {
    /**
     * 表示金钱
     */
    private int money;
    /**
     * 表示人数
     */
    private int people;

    public void playGame() {
        money += 2;
        people += 10;
        System.out.println("游戏开始：" + "金钱是" + money + "人数是" + people);
    }

    public Memento createMemento() {
        return new Memento(money, people);
    }

    public void restore(Memento memento) {
        this.money = memento.money;
        this.people = memento.people;
        System.out.println("游戏恢复：" + memento);
    }

    public void close(){
        this.money = 0;
        this.people = 0;
        System.out.println("游戏结束：" + "金钱是" + money + "人数是" + people);
    }
}
```

### 备忘录的实现

```
public class Memento {
    /**
     * 表示金钱
     */
    public int money;
    /**
     * 表示人数
     */
    public int people;

    public Memento(int money, int people) {
        this.money = money;
        this.people = people;
    }

    @Override
    public String toString() {
        return "Memento{" +
                "money=" + money +
                ", people=" + people +
                '}';
    }
}
```

就是一个简单的javaBean

### 客户端和Caretaker

```
public class Caretaker {
    Memento mMemento;


    public Memento getMemento() {
        return mMemento;
    }

    public void setMemento(Memento memento) {
        mMemento = memento;
    }
}


public class Client {
    public static void main(String[] args) {
        Originator originator = new Originator();
        originator.playGame();

        Caretaker caretaker = new Caretaker();
        caretaker.setMemento(originator.createMemento());

        originator.close();

        originator.restore(caretaker.getMemento());
    }
}
```

代码演示了一个单状态单备份的例子，逻辑非常简单：Originator类中的变量需要备份，以便在需要的时候恢复；Memento类中，也有两个变量，用来存储Originator类中金钱和人数变量的临时状态；而Caretaker类就是用来管理备忘录类的，用来向备忘录对象中写入状态或者取回状态。

![sp161201_145101](http://oaxelf1sk.bkt.clouddn.com/sp161201_145101.png)

## 多状态的备忘录模式

 通用代码演示的例子中，Originator类只有一个状态需要备份，而通常情况下，发起人角色通常是一个javaBean，对象中需要备份的状态也不止一个，这就是多状态多备份备忘录。实现备忘录的方法很多，备忘录模式有很多变形和处理方式，像通用代码那样的方式一般不会用到，多数情况下的备忘录模式，是多状态多备份的。其实实现多状态多备份也很简单，最常用的方法是，在Caretaker类中同样使用一个Map容器才存储所有的备份。

![diagram120115111](http://oaxelf1sk.bkt.clouddn.com/diagram120115111.png)

更改上面的代码：

### Memento

```
//增加一个Map
public class Caretaker {

    private HashMap<String, Memento> mMap;

    public Caretaker() {
        mMap = new HashMap<>();
    }

    public Memento getMemento(String state) {
        return mMap.get(state);
    }

    public void setMemento(Memento memento) {
        mMap.put(memento.state, memento);
    }
}
```

### Caretaker和客户端的实现

```
//利用关键字来存储状态
public class Caretaker {

    private HashMap<String, Memento> mMap;

    public Caretaker() {
        mMap = new HashMap<>();
    }

    public Memento getMemento(String state) {
        return mMap.get(state);
    }

    public void setMemento(Memento memento) {
        mMap.put(memento.state, memento);
    }
}

public class Client {
    public static void main(String[] args) {
        Originator originator = new Originator();

        Caretaker caretaker = new Caretaker();
        caretaker.setMemento(originator.playGame().createMemento("状态一"));
        caretaker.setMemento(originator.playGame().createMemento("状态二"));

        originator.close();
        //只是恢复状态一的情况
        originator.restore(caretaker.getMemento("状态一"));
    }
}
```

![sp161201_150823](http://oaxelf1sk.bkt.clouddn.com/sp161201_150823.png)

## 优缺点

**优点：**

1、给用户提供了一种可以恢复状态的机制，可以使用户能够比较方便地回到某个历史的状态。 2、实现了信息的封装，使得用户不需要关心状态的保存细节。

**缺点：**消耗资源。如果类的成员变量过多，势必会占用比较大的资源，而且每一次保存都会消耗一定的内存。

### 使用实例

1、后悔药。 2、打游戏时的存档。 3、Windows 里的 ctri + z。 4、IE 中的后退。 4、数据库的事务管理。



具体代码参见：[GitHub](https://github.com/HaoTianYi/JavaPattern)





