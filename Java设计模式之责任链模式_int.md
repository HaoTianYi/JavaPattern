# Java设计模式之责任链模式

[TOC]

## 责任链模式简介

责任链模式（Chain of Responsibility Pattern）为请求创建了一个接收者对象的链。这种模式给予请求的类型，对请求的发送者和接收者进行解耦。这种类型的设计模式属于行为型模式。

避免请求发送者与接收者耦合在一起，让多个对象都有可能接收请求，将这些对象连接成一条链，并且沿着这条链传递请求，直到有对象处理它为止。

职责链上的处理者负责处理请求，客户只需要将请求发送到职责链上即可，无须关心请求的处理细节和请求的传递，所以职责链将请求的发送者和请求的处理者解耦了。

## 责任链模式简单实现

![diagram11301836](http://oaxelf1sk.bkt.clouddn.com/diagram11301836.png)

一个表示负责事件处理基类BaseHandler，它有三个子类表示具体的事件处理类，而Client产生事件并且把这个事件传递给每一个具体的事件处理类。

#### Handler的简单实现

```
public abstract class BaseHandler {
    /**
     * 处理信息的下一个节点
     */
    public BaseHandler nextHandler;

    /**
     * 具体的执行方法
     */
    public abstract void execute(int level);
}
```

三个具体的实现子类：

```
public class OneHandler extends BaseHandler {

    /**
     * 具体的执行方法
     */
    @Override
    public void execute(int level) {
        if (level < 10) {
            System.out.println("OneHandler---------正在执行");
        }else {
            nextHandler.execute(level);
        }
    }
}

public class TwoHandler extends BaseHandler {

    /**
     * 具体的执行方法
     */
    @Override
    public void execute(int level) {
        if (level<20){
            System.out.println("TwoHandler---------正在执行");
        }else {
            nextHandler.execute(level);
        }
    }
}

public class ThreeHandler extends BaseHandler {

    /**
     * 具体的执行方法
     *
     * @param level
     */
    @Override
    public void execute(int level) {
        if (level>=20){
            System.out.println("ThreeHandler---------正在执行");
        }else {
            nextHandler.execute(level);
        }
    }
}
```

#### 客户端的实现

```
public class Client {
    public static void main(String[] args) {
        OneHandler oneHandler = new OneHandler();
        TwoHandler twoHandler = new TwoHandler();
        ThreeHandler threeHandler = new ThreeHandler();

        oneHandler.nextHandler = twoHandler;
        twoHandler.nextHandler = threeHandler;
        threeHandler.nextHandler = oneHandler;

        oneHandler.execute(35);
    }
}
```

使用一个level类设置消息处理的级别，从而让具体的事件处理这能够处理对应的事件。

## 责任链模式的完整实现

在实际应用中不单单应该有不同种类的事件处理，也应该有不同种类的消息（事件） 

![diagram11301919](http://oaxelf1sk.bkt.clouddn.com/diagram11301919.png)

三个消息类，三个消息处理的类

### handler的实现

```
public abstract class BaseHandler {
    /**
     * 处理信息的下一个节点
     */
    public BaseHandler nextHandler;

    /**
     * 根据消息的级别和消息处理类的级别来匹配相应的消息处理
     *
     * @param baseMessage
     */
    public void judge(BaseMessage baseMessage) {
        if (baseMessage.getLevel() == this.getLevel()) {
            execute(baseMessage);
        } else {
            nextHandler.execute(baseMessage);
        }
    }

    /**
     * 具体的执行方法
     */
    public abstract void execute(BaseMessage baseMessage);

    /**
     * 获得处理者能够处理消息的级别
     *
     * @return
     */
    public abstract int getLevel();
}

public class OneHandler extends BaseHandler {

    /**
     * 具体的执行方法
     */
    @Override
    public void execute(BaseMessage baseMessage) {
        System.out.println(baseMessage.content+"----------"+"OneHandler---------正在执行");
    }

    /**
     * 获得处理者能够处理消息的级别
     *
     * @return
     */
    @Override
    public int getLevel() {
        return 1;
    }
}
//其他俩个消息处理类完全相同
```

### 消息Message的实现

```
public abstract class BaseMessage {

    /**
     * 表示消息的内容
     */
    public String content;

    /**
     * 表示消息的级别
     * @return
     */
    public abstract int getLevel();
}

public class OneMessage extends BaseMessage {

    public OneMessage(String content) {
        super.content = content;
    }

    /**
     * 表示消息的级别
     *
     * @return
     */
    @Override
    public int getLevel() {
        return 1;
    }
}
//其他俩个消息类完全相同
```

### 客户端的实现

```
public class Client {
    public static void main(String[] args) {
        BaseHandler oneHandler = new OneHandler();
        BaseHandler twoHandler = new TwoHandler();
        BaseHandler threeHandler = new ThreeHandler();

        oneHandler.nextHandler = twoHandler;
        twoHandler.nextHandler = threeHandler;
        threeHandler.nextHandler = oneHandler;

        oneHandler.judge(new OneMessage("第一个消息"));
        oneHandler.judge(new TwoMessage("第二个消息"));
        oneHandler.judge(new ThreeMessage("第三个消息"));
    }
}
```

最后发现即使调用的是oneHandler但是还是相应的消息类来处理对应的消息

![sp161130_192518](http://oaxelf1sk.bkt.clouddn.com/sp161130_192518.png)

## 优缺点

**优点：** 

1、降低耦合度。它将请求的发送者和接收者解耦。

 2、简化了对象。使得对象不需要知道链的结构。 

3、增强给对象指派职责的灵活性。通过改变链内的成员或者调动它们的次序，允许动态地新增或者删除责任。

 4、增加新的请求处理类很方便。

**缺点：**

 1、不能保证请求一定被接收。 

2、系统性能将受到一定影响，而且在进行代码调试时不太方便，可能会造成循环调用。 

3、可能不容易观察运行时的特征，有碍于除错。



具体代码参见：[GitHub](https://github.com/HaoTianYi/JavaPattern)
