# Java设计模式之状态模式



## 状态模式简介

在状态模式（State Pattern）中，类的行为是基于它的状态改变的。这种类型的设计模式属于行为型模式。在状态模式中，我们创建表示各种状态的对象和一个行为随着状态对象改变而改变的 context 对象。

**意图：**允许对象在内部状态发生改变时改变它的行为，对象看起来好像修改了它的类。

**主要解决：**对象的行为依赖于它的状态（属性），并且可以根据它的状态改变而改变它的相关行为。

**何时使用：**代码中包含大量与对象状态有关的条件语句。

**如何解决：**将各种具体的状态类抽象出来。

## 基本类图

![diagram11300943](http://oaxelf1sk.bkt.clouddn.com/diagram11300943.png)

我们可以看到使用一个中间类Context负责状态的更改和调用不同状态的方法，而使用者不知道具体实现的细节，context类可以省略。

Context 是一个环境角色，它的作用是串联各个状态的过渡，在BaseSate 抽象类中我们定义了并把这个环境角色聚合进来，并传递到了子类，也就是五个具体的实现类中自己根据环境来决定如何进行状态的过渡。 

## 不使用状态模式以电梯为例

电梯自定义有五种状态：开、关、异常、走、停止，每个状态执行不同的方法能够改变状态，并且不同的状态只能能执行特定的方法。

```
public class NOState {

    public static final int CLOSING_STATE = 0;
    public static final int FAULT_STATE = 1;
    public static final int OPENING_STATE = 2;
    public static final int RUNNING_STATE = 3;
    public static final int STOPPING_STATE = 4;

    private static int state;


    /**
     * 模拟电梯的运行方法
     */
    public void run() {
        switch (state) {
            case CLOSING_STATE:
//              执行具体的方法
//              state = XXXX;
                break;
            case FAULT_STATE:
//              执行具体的方法
//              state = XXXX;
                break;
            case OPENING_STATE:
//              执行具体的方法
//              state = XXXX;
                break;
            case RUNNING_STATE:
//              执行具体的方法
//              state = XXXX;
                break;
            case STOPPING_STATE:
//              执行具体的方法
//              state = XXXX;
                break;
            default:
                break;
        }
    }

    /**
     * 模拟电梯的停止方法
     */
    public void stop() {
        switch (state) {
            case CLOSING_STATE:
//              执行具体的方法
//              state = XXXX;
                break;
            case FAULT_STATE:
//              执行具体的方法
//              state = XXXX;
                break;
            case OPENING_STATE:
//              执行具体的方法
//              state = XXXX;
                break;
            case RUNNING_STATE:
//              执行具体的方法
//              state = XXXX;
                break;
            case STOPPING_STATE:
//              执行具体的方法
//              state = XXXX;
                break;
            default:
                break;
        }
    }

    /**
     * 模拟电梯的开门方法
     */
    public void open() {
        switch (state) {
            case CLOSING_STATE:
//              执行具体的方法
//              state = XXXX;
                break;
            case FAULT_STATE:
//              执行具体的方法
//              state = XXXX;
                break;
            case OPENING_STATE:
//              执行具体的方法
//              state = XXXX;
                break;
            case RUNNING_STATE:
//              执行具体的方法
//              state = XXXX;
                break;
            case STOPPING_STATE:
//              执行具体的方法
//              state = XXXX;
                break;
            default:
                break;
        }
    }

    /**
     * 模拟电梯的关门方法
     */
    public void close() {
        switch (state) {
            case CLOSING_STATE:
//              执行具体的方法
//              state = XXXX;
                break;
            case FAULT_STATE:
//              执行具体的方法
//              state = XXXX;
                break;
            case OPENING_STATE:
//              执行具体的方法
//              state = XXXX;
                break;
            case RUNNING_STATE:
//              执行具体的方法
//              state = XXXX;
                break;
            case STOPPING_STATE:
//              执行具体的方法
//              state = XXXX;
                break;
            default:
                break;
        }
    }
}

```

代码高度重复，使用状态模式来重构代码。

## 使用状态模式重构代码

新建一个所有状态共有的基类BaseState，并且抽取公共方法，然后新建一个Context类来负责管理状态，使客户端能够在不知道状态的情况下实现逻辑。

![diagram11300938](http://oaxelf1sk.bkt.clouddn.com/diagram11300938.png)

简单而言类图如上，客户端调用Context，Context负责状态的改变，而且客户端不知道实现的细节，高度解耦。

### 使用状态模式的基本逻辑

![状态模式11300949](http://oaxelf1sk.bkt.clouddn.com/状态模式11300949.png)

方法的调用伴随的是状态的改变，在调用方法前首先判断状态，比如电梯开时不能调用运行方法。

### 不同状态的实现

```
/**
 * @author HaoTianYi  hao.ty@haotianyi.win
 * @version v1.0
 *          模拟电梯状态中不同的方法
 */
public abstract class BaseState {

    protected Context mContext;

    public void setContext(Context context) {
        mContext = context;
    }

    /**
     * 模拟电梯的运行方法
     */
    public abstract void run();
    /**
     * 模拟电梯的停止方法
     */
    public abstract void stop();
    /**
     * 模拟电梯的开门方法
     */
    public abstract void open();
    /**
     * 模拟电梯的关门方法
     */
    public abstract void close();
}

```

![diagram11300937](http://oaxelf1sk.bkt.clouddn.com/diagram11300937.png)

五种状态都有共有的方法

####ClosingState

```
/**
 * @author HaoTianYi  hao.ty@haotianyi.win
 * @version v1.0
 * 模拟电梯关闭状态
 */
public class ClosingState extends BaseState {
    /**
     * 模拟电梯的运行方法
     */
    @Override
    public void run() {
        super.mContext.setBaseState(Context.RUNNING_STATE);
        System.out.println("电梯开始跑起来-----------");
    }

    /**
     * 模拟电梯的停止方法
     */
    @Override
    public void stop() {
        super.mContext.setBaseState(Context.STOPPING_STATE);
        System.out.println("电梯关门-----------");
    }

    /**
     * 模拟电梯的开门方法
     */
    @Override
    public void open() {
        super.mContext.setBaseState(Context.CLOSING_STATE);
        System.out.println("电梯开门-----------");
    }

    /**
     * 模拟电梯的关门方法
     */
    @Override
    public void close() {
        super.mContext.setBaseState(Context.STOPPING_STATE);
        System.out.println("电梯关门-----------");
    }
}
```

#### FaultState

```
/**
 * @author HaoTianYi  hao.ty@haotianyi.win
 * @version v1.0
 *          模拟故障状态,四个方法都不可用
 */
public class FaultState extends BaseState {
    /**
     * 模拟电梯的运行方法
     */
    @Override
    public void run() {
        System.out.println("电梯发生故障，不能正常工作");
    }

    /**
     * 模拟电梯的停止方法
     */
    @Override
    public void stop() {
        System.out.println("电梯发生故障，不能正常工作");
    }

    /**
     * 模拟电梯的开门方法
     */
    @Override
    public void open() {
        System.out.println("电梯发生故障，不能正常工作");
    }

    /**
     * 模拟电梯的关门方法
     */
    @Override
    public void close() {
        System.out.println("电梯发生故障，不能正常工作");
    }
}
```

#### OpeningState

```
/**
 * @author HaoTianYi  hao.ty@haotianyi.win
 * @version v1.0
 *          模拟电梯打开状态
 */
public class OpeningState extends BaseState {
    /**
     * 模拟电梯的运行方法
     */
    @Override
    public void run() {
        super.mContext.setBaseState(Context.FAULT_STATE);
    }

    /**
     * 模拟电梯的停止方法
     */
    @Override
    public void stop() {

    }

    /**
     * 模拟电梯的开门方法
     */
    @Override
    public void open() {
        System.out.println("电梯开门-----------");
    }

    /**
     * 模拟电梯的关门方法
     */
    @Override
    public void close() {
        super.mContext.setBaseState(Context.CLOSING_STATE);
        System.out.println("电梯关门-----------");
    }
}
```

#### RunningState

```
/**
 * @author HaoTianYi  hao.ty@haotianyi.win
 * @version v1.0
 *          模拟电梯运行状态
 */
public class RunningState extends BaseState {
    /**
     * 模拟电梯的运行方法
     */
    @Override
    public void run() {
        System.out.println("电梯开始跑起来-----------");
    }

    /**
     * 模拟电梯的停止方法
     */
    @Override
    public void stop() {
        super.mContext.setBaseState(Context.STOPPING_STATE);
        System.out.println("电梯停止-----------");
    }

    /**
     * 模拟电梯的开门方法
     */
    @Override
    public void open() {
        super.mContext.setBaseState(Context.FAULT_STATE);
        System.out.println("电梯发生故障");
    }

    /**
     * 模拟电梯的关门方法
     */
    @Override
    public void close() {
        System.out.println("电梯关门-----------");
    }
}
```

#### StoppingState

```
/**
 * @author HaoTianYi  hao.ty@haotianyi.win
 * @version v1.0
 *          模拟电梯停止状态
 */
public class StoppingState extends BaseState {
    /**
     * 模拟电梯的运行方法
     */
    @Override
    public void run() {
        super.mContext.setBaseState(Context.RUNNING_STATE);
        System.out.println("电梯开始跑起来-----------");
    }

    /**
     * 模拟电梯的停止方法
     */
    @Override
    public void stop() {
        System.out.println("电梯停止-----------");
    }

    /**
     * 模拟电梯的开门方法,中途停止状态省略
     */
    @Override
    public void open() {
        super.mContext.setBaseState(Context.OPENING_STATE);
        System.out.println("电梯开门-----------");
    }

    /**
     * 模拟电梯的关门方法
     */
    @Override
    public void close() {
        System.out.println("电梯关门-----------");
    }
}
```

### Context方法

```
public class Context {

    public static final ClosingState CLOSING_STATE = new ClosingState();
    public static final FaultState FAULT_STATE = new FaultState();
    public static final OpeningState OPENING_STATE = new OpeningState();
    public static final RunningState RUNNING_STATE = new RunningState();
    public static final StoppingState STOPPING_STATE = new StoppingState();

    private BaseState mBaseState;

    public BaseState getBaseState() {
        return mBaseState;
    }

    public void setBaseState(BaseState baseState) {
        this.mBaseState = baseState;
        this.mBaseState.setContext(this);
    }

    /**
     * 模拟电梯的运行方法
     */
    public Context run() {
        this.mBaseState.run();
        return this;
    }

    /**
     * 模拟电梯的停止方法
     */
    public Context stop() {
        this.mBaseState.stop();
        return this;
    }

    /**
     * 模拟电梯的开门方法
     */
    public Context open() {
        this.mBaseState.open();
        return this;
    }

    /**
     * 模拟电梯的关门方法
     */
    public Context close() {
        this.mBaseState.close();
        return this;
    }
}
```
注意：

```
    public void setBaseState(BaseState baseState) {
        this.mBaseState = baseState;
        this.mBaseState.setContext(this);
    }
```

中：`this.mBaseState.setContext(this);`的作用：绑定相应的上下文

### 客户端的实现

```
public class Client {
    public static void main(String[] args) {
        Context context = new Context();
        context.setBaseState(new ClosingState());
        context.run().open().close();

        Context context1 = new Context();
        context1.setBaseState(new RunningState());
        context1.stop().open().run().open();
    }
}
```
![sp161130_101455](http://oaxelf1sk.bkt.clouddn.com/sp161130_101455.png)

##优缺点

优点： 1、封装了转换规则。 2、枚举可能的状态，在枚举状态之前需要确定状态种类。 3、将所有与某个状态有关的行为放到一个类中，并且可以方便地增加新的状态，只需要改变对象状态即可改变对象的行为。 4、允许状态转换逻辑与状态对象合成一体，而不是某一个巨大的条件语句块。 5、可以让多个环境对象共享一个状态对象，从而减少系统中对象的个数。

缺点： 1、状态模式的使用必然会增加系统类和对象的个数。 2、状态模式的结构与实现都较为复杂，如果使用不当将导致程序结构和代码的混乱。 3、状态模式对"开闭原则"的支持并不太好，对于可以切换状态的状态模式，增加新的状态类需要修改那些负责状态转换的源代码，否则无法切换到新增状态，而且修改某个状态类的行为也需修改对应类的源代码。

###和策略模式的对比

状态模式和策略模式几乎完全一样，但是他们的本质不同。状态模式的状态是不可替换的而且是兄弟关系，但是策略模式是可以替换的彼此独立的。而且他们执行完之后的行为也不同状态模式会更改状态但是策略模式执行完就是执行完。

具体代码参见：[GitHub](https://github.com/HaoTianYi/JavaPattern)

