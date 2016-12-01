# Java设计模式之观察者模式



## 观察者模式简介

当对象间存在一对多关系时，则使用观察者模式（Observer Pattern）。比如，当一个对象被修改时，则会自动通知它的依赖对象。观察者模式属于行为型模式。

就是说当被观察者发生变化的时候，观察者会自动接受到数据，而不用每次数据改变的时候主动通知观察者。

### 观察者模式的由来

现在有这样一个需求，支付宝再一次大改版了，加入附近的有钱人功能（假设的），支付宝要向社会推广这个功能，推广不仅包括用户、竞争对手、政府机构。

![观察者模式12010952](http://oaxelf1sk.bkt.clouddn.com/观察者模式12010952.png)

但是支付宝改版的速度太快了，每次都是调用数据改变的方法太麻烦，所有才有了观察者模式（订阅发布模式）和邮件的订阅很相似。

### 观察者模式的简单实现

![diagram12011019](http://oaxelf1sk.bkt.clouddn.com/diagram12011019.png)

只要设置一个被观察的对象（支付宝）继承Observale类，另外三个观察者实现Observer即可

### 观察者模式的基本代码

#### 支付宝

```
public class ZhiFuBaoObservable extends Observable {
    /**
     * 没错，支付宝社交不死心，又更新了
     * @param string
     */
    public void upgrade(String string){

        //设置数据发生改变
        setChanged();
        //通知所有观察者，宝宝又改版了
        notifyObservers(string);
    }
}
```

#### 观察者们

```
public class QunZhongObserver implements Observer {
    @Override
    public void update(Observable o, Object arg) {
        System.out.println(arg+"----吃瓜群众表示要红包");
    }
}

public class WeiXinObserver implements Observer {

    @Override
    public void update(Observable o, Object arg) {
        System.out.println(arg+"----微信表示我就看着你装B");
    }
}

public class YinLianObserver implements Observer {

    @Override
    public void update(Observable o, Object arg) {
        System.out.println(arg+"----还是央妈好，饿不不死我，嘻嘻嘻");
    }
}
```

#### 客户端

```
public class Client {
    public static void main(String[] args) {
        ZhiFuBaoObservable zhiFuBaoObservable = new ZhiFuBaoObservable();

        QunZhongObserver qunZhongObserver = new QunZhongObserver();
        WeiXinObserver weiXinObserver = new WeiXinObserver();
        YinLianObserver yinLianObserver = new YinLianObserver();

        zhiFuBaoObservable.addObserver(qunZhongObserver);
        zhiFuBaoObservable.addObserver(weiXinObserver);
        zhiFuBaoObservable.addObserver(yinLianObserver);

        zhiFuBaoObservable.upgrade("第100次大改版，加入周围的有钱人功能");
    }
}
```

![sp161201_102254](http://oaxelf1sk.bkt.clouddn.com/sp161201_102254.png)

## 自定义观察者模式

### 源码分析

打开Observable类，添加一些注释：

```
public class Observable {
    //  设置是否改变的钥匙
    private boolean changed = false;
    //  设置存放观察者的队列
    private Vector<Observer> obs;

    /** Construct an Observable with zero Observers. */

    public Observable() {
        obs = new Vector<>();
    }

    /**
     * 添加观察者
     *
     * @param o
     */
    public synchronized void addObserver(Observer o) {
        if (o == null)
            throw new NullPointerException();
        if (!obs.contains(o)) {
            obs.addElement(o);
        }
    }

    /**
     * 删除观察者
     * @param o
     */
    public synchronized void deleteObserver(Observer o) {
        obs.removeElement(o);
    }

    public void notifyObservers() {
        notifyObservers(null);
    }

    /**
     * 通知所有的观察者，宝宝的数据发生了改变
     * @param arg
     */
    public void notifyObservers(Object arg) {

        Object[] arrLocal;

        synchronized (this) {

            if (!changed)
                return;
            arrLocal = obs.toArray();
            clearChanged();
        }

        for (int i = arrLocal.length-1; i>=0; i--)
            ((Observer)arrLocal[i]).update(this, arg);
    }


    public synchronized void deleteObservers() {
        obs.removeAllElements();
    }

    protected synchronized void setChanged() {
        changed = true;
    }


    protected synchronized void clearChanged() {
        changed = false;
    }


    public synchronized boolean hasChanged() {
        return changed;
    }

    public synchronized int countObservers() {
        return obs.size();
    }
}
```

观察者类更是只有一个方法：

```
public interface Observer {
    /**
     * 当数据发生改变的时候调用
     * @param o
     * @param arg
     */
    void update(Observable o, Object arg);
}
```

很简单，完全不用实现JDK中的类，而是可以自己来定制一个观察者模式

### 定制观察者模式

![diagram12011045](http://oaxelf1sk.bkt.clouddn.com/diagram12011045.png)

就是把支付宝和观察者结合起来，在设置一个内部接口来作为观察者们的父接口，相比标准模式各有优缺点。

### 支付宝的实现

```
public class ZhiFuBao {

    //  设置是否改变的钥匙
    private boolean changed = false;
    //  设置存放观察者的队列
    private ArrayList<MyObserver> obs;

    /**
     * Construct an Observable with zero Observers.
     */

    public ZhiFuBao() {
        obs = new ArrayList<>();
    }

    public void publishMessage(String string){
        setChanged();
        notifyObservers(string);
    }

    /**
     * 添加观察者
     *
     * @param o
     */
    public synchronized void addObserver(MyObserver o) {
        if (o == null)
            throw new NullPointerException();
        if (!obs.contains(o)) {
            obs.add(o);
        }
    }

    /**
     * 删除观察者
     *
     * @param o
     */
    public synchronized void deleteObserver(MyObserver o) {
        obs.remove(o);
    }

    public void notifyObservers() {
        notifyObservers(null);
    }

    /**
     * 通知所有的观察者，宝宝的数据发生了改变
     *
     * @param arg
     */
    public void notifyObservers(Object arg) {

        Object[] arrLocal;

        synchronized (this) {

            if (!changed)
                return;
            arrLocal = obs.toArray();
            clearChanged();
        }

        for (int i = arrLocal.length - 1; i >= 0; i--)
            ((MyObserver) arrLocal[i]).publishMessage(this, arg);
    }


    public synchronized void deleteObservers() {
        obs.removeAll(obs);
    }

    protected synchronized void setChanged() {
        changed = true;
    }


    protected synchronized void clearChanged() {
        changed = false;
    }


    public synchronized boolean hasChanged() {
        return changed;
    }

    public synchronized int countObservers() {
        return obs.size();
    }

    public interface MyObserver {
        /**
         * 发布消息
         */
        void publishMessage(ZhiFuBao o, Object arg);
    }

}
```

### 观察者们的实现

```
public class QunZhong implements ZhiFuBao.MyObserver {
    @Override
    public void publishMessage(ZhiFuBao o, Object arg) {
        System.out.println(arg+"----吃瓜群众表示要红包");
    }
}

public class WeiXin implements ZhiFuBao.MyObserver {
    @Override
    public void publishMessage(ZhiFuBao o, Object arg) {
        System.out.println(arg+"----微信表示我就看着你装B");
    }
}

public class YinLian implements ZhiFuBao.MyObserver {
    @Override
    public void publishMessage(ZhiFuBao o, Object arg) {
        System.out.println(arg+"----还是央妈好，饿不不死我，嘻嘻嘻");
    }
}
```

### 客户端实现

```
        ZhiFuBao zhiFuBao = new ZhiFuBao();
        zhiFuBao.addObserver(new QunZhong());
        zhiFuBao.addObserver(new WeiXin());
        zhiFuBao.addObserver(new YinLian());

        zhiFuBao.publishMessage("第100次大改版，加入周围的有钱人功能");
```

自定义的解耦不如标准，但是能够更好的理解每一个方法的作用

## 优缺点

**优点：**

 1、观察者和被观察者是抽象耦合的。

 2、建立一套触发机制。

**缺点：**

 1、如果一个被观察者对象有很多的直接和间接的观察者的话，将所有的观察者都通知到会花费很多时间。 

2、如果在观察者和观察目标之间有循环依赖的话，观察目标会触发它们之间进行循环调用，可能导致系统崩溃。 

3、观察者模式没有相应的机制让观察者知道所观察的目标对象是怎么发生变化的，而仅仅只是知道观察目标发生了变化。



具体代码参见：[GitHub](https://github.com/HaoTianYi/JavaPattern)
