# Java设计模式之单例模式

[TOC]

## 简介

单例模式确保某个类只有一个实例，而且自行实例化并向整个系统提供这个实例。在计算机系统中，线程池、缓存、日志对象、对话框、打印机、显卡的驱动程序对象常被设计成单例。这些应用都或多或少具有资源管理器的功能。每台计算机可以有若干个打印机，但只能有一个Printer Spooler，以避免两个打印作业同时输出到打印机中。每台计算机可以有若干通信端口，系统应当集中管理这些通信端口，以避免一个通信端口同时被两个请求同时调用。总之，选择单例模式就是为了避免不一致状态，避免政出多头。

## 基本格式

包含三个部分：

```
//一个私有静态的对象
    private static Emperor singleton;
//  私有的构造方法
    private Emperor() {
    }
//  公共的返回对象的方法
    public static Emperor getSingleton() {
        return singleton = new Emperor();
    }
```

## 饿汉单例模式

```
    private static final Emperor singleton = new Emperor();

    //  私有的构造方法
    private Emperor() {
    }

    //  公共的返回对象的方法
    public static Emperor getSingleton() {
        return singleton;
    } 
```

饿汉式天生就是线程安全的，可以直接用于多线程而不会出现问题

## 懒汉单例模式

### 基本的懒汉单例模式

```
public class Emperor {
    private static Emperor singleton;

    //  私有的构造方法
    private Emperor() {
    }

    //  公共的返回对象的方法
    public static Emperor getSingleton() {
        if (singleton == null) {
            singleton = new Emperor();
        }
        return singleton;
    }
}
```

不适合多线程的环境，改进，加入锁的机制

### 适合多线程的基本懒汉单例模式

```
public class Emperor {
    private static Emperor singleton;

    //  私有的构造方法
    private Emperor() {
    }

    //  公共的返回对象的方法
    public synchronized static Emperor getSingleton() {
        if (singleton == null) {
            singleton = new Emperor();
        }
        return singleton;
    }
}
```

缺点：第一次加载反应慢（初始化变量），每次调用getSingleton都会同步，浪费资源

### Double CheckLock（DCL）实现机制

```
public class Emperor {
    private static Emperor singleton;

    //  私有的构造方法
    private Emperor() {
    }

    //  公共的返回对象的方法
    public static Emperor getSingleton() {
        if (singleton == null) {
            synchronized (Emperor.class) {
                if (singleton == null) {
                    singleton = new Emperor();
                }
            }
        }
        return singleton;
    }
}
```

解决了每次调用getSingleton都会同步的问题，但是第一次加载反应慢（初始化变量）是懒汉单例模式的通病

但是假设线程A执行到singleton = new Emperor()这句话都三个基本操作：
1、给singleton 分配内存；

2、调用Emperor()构造函数，初始化成员变量；

3、把singleton 对象指向指定的内存空间；

但是2和3是没有先后顺序的，假设A执行132，那么现在是2然后线程B执行完1，不会初始化，B的值就是null，造成DCL失效（双重检查锁定失效），在JDK1.5之后引入关键字volatile

```
public class Emperor {
    private volatile static Emperor singleton;

    //  私有的构造方法
    private Emperor() {
    }

    //  公共的返回对象的方法
    public static Emperor getSingleton() {
        if (singleton == null) {
            synchronized (Emperor.class) {
                if (singleton == null) {
                    singleton = new Emperor();
                }
            }
        }
        return singleton;
    }
}
```

## 更加优雅的写法

使用静态内部类的单例模式

```
//  内部类中初始化
    private static class SingletonHolder{
        private static final Emperor singleton = new Emperor();
    }

    //  私有的构造方法
    private Emperor() {
    }

    //  公共的返回对象的方法
    public static Emperor getSingleton() {
       return SingletonHolder.singleton;
    }
```

评价上述的单例模式有三个角度：线程安全？第一次调用实例方法延迟加载？每次调用方法耗费大量资源？

## 对比

| 评价角度    | 懒汉   | 饿汉   | 内部类  |
| ------- | ---- | ---- | ---- |
| 线程安全    | 可以后加 | 天生   | 天生   |
| 第一次加载延迟 | 是    | 否    | 是    |
| 耗费资源    | 具体而定 | 少    | 少    |

Android在图片加载库经常使用单例模式

参考：[CSDN](http://blog.csdn.net/jason0539/article/details/23297037/)





