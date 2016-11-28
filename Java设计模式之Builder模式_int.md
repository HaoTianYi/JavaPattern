# Java设计模式之Builder模式

[TOC]

## 简介

将一个复杂对象的构建与它的表示分离，使得同样的构建过程可以创建不同的表示。

### 适用性

在以下情况使用Build模式：

1 当创建复杂对象的算法应该独立于该对象的组成部分以及它们的装配方式时。

2 当构造过程必须允许被构造的对象有不同的表示时。

3 Builder模式要解决的也正是这样的问题：

　　当我们要创建的对象很复杂的时候（通常是由很多其他的对象组合而成），

　　我们要复杂对象的创建过程和这个对象的表示（展示）分离开来，

　　这样做的好处就是通过一步步的进行复杂对象的构建，

　　由于在每一步的构造过程中可以引入参数，使得经过相同的步骤创建最后得到的对象的展示不一样。

## 用LOL的出装备的顺序为例

 lol的uml如下图，人物的四种装备和一个展示装备顺序的show方法，还有client类：

![diagram](http://oaxelf1sk.bkt.clouddn.com/diagram.png)

### 基本实现代码

#### BasePerson的实现

```
public abstract class BasePerson {

    /**
     * 首先出魔法装备
     */
    protected abstract void magic();

    /**
     * 首先出鞋子
     */
    protected abstract void shoe();

    /**
     * 首先出攻速装备
     */
    protected abstract void velocity();

    /**
     * 首先出物理攻击装备
     */
    protected abstract void strength();

    /**
     * 展示出装的顺序
     */
    public abstract void show();
}
```

#### DeMaXiYa的实现：

```
/**
 * @author HaoTianYi  hao.ty@haotianyi.win
 * @version v1.0
 *  表示德玛西亚
 */
public class DeMaXiYa extends BasePerson {

    /**
     * 首先出魔法装备
     */
    protected void magic() {
        System.out.println("-----德玛西亚-----魔法装备-----------");
    }

    /**
     * 首先出鞋子
     */
    protected void shoe() {
        System.out.println("-----德玛西亚-----鞋子-----------");
    }

    /**
     * 首先出攻速装备
     */
    protected void velocity() {
        System.out.println("------德玛西亚----攻速装备-----------");
    }

    /**
     * 首先出物理攻击装备
     */
    protected void strength() {
        System.out.println("-----德玛西亚-----物理攻击装备-----------");
    }

    /**
     * 展示出装的顺序
     */
    @Override
    public void show() {
        magic();
        shoe();
        velocity();
        strength();
    }
}
```

#### QiTianDaSheng的基本实现

```
/**
 * @author HaoTianYi  hao.ty@haotianyi.win
 * @version v1.0
 * 表示猴子
 */
public class QiTianDaSehng extends BasePerson {

    /**
     * 首先出魔法装备
     */
    protected void magic() {
        System.out.println("----猴子------魔法装备-----------");
    }

    /**
     * 首先出鞋子
     */
    protected void shoe() {
        System.out.println("----猴子------鞋子-----------");
    }

    /**
     * 首先出攻速装备
     */
    protected void velocity() {
        System.out.println("----猴子------攻速装备-----------");
    }

    /**
     * 首先出物理攻击装备
     */
    protected void strength() {
        System.out.println("----猴子------物理攻击装备-----------");
    }

    /**
     * 展示出装的顺序
     */
    @Override
    public void show() {
        magic();
        shoe();
        velocity();
        strength();
    }
}
```

#### client的实现：

```
 public class client {
    public static void main(String[] args) {
        DeMaXiYa deMaXiYa = new DeMaXiYa();
        deMaXiYa.show();

        QiTianDaSehng qiTianDaSehng = new QiTianDaSehng();
        qiTianDaSehng.show();
    }
}
```

最后的出装备的结果：

![sp161128_175127](http://oaxelf1sk.bkt.clouddn.com/sp161128_175127.png)

### 初步使用Buildr模式

上面的代码不复杂，但是“将一个复杂对象的构建与它的表示没有分离”，两个任务由构建方法和表示方法，使用Builder将一个复杂对象的构建与它的表示分离。 

![diagram2](http://oaxelf1sk.bkt.clouddn.com/diagram2.png)

做到了将一个复杂对象的构建与它的表示分离

#### BasePerson的改进

```
public abstract class BasePerson {

    /**
     * 首先出魔法装备
     */
    public abstract void magic();

    /**
     * 首先出鞋子
     */
    public abstract void shoe();

    /**
     * 首先出攻速装备
     */
    public abstract void velocity();

    /**
     * 首先出物理攻击装备
     */
    public abstract void strength();
}
```

#### Builder的实现

```
public abstract class Builder {
    /**
     * 表示与之对应的英雄
     */
    protected BasePerson mBasePerson;

    /**
     * 设置出装的顺序和展示
     */
    public abstract void setOrderAndShow();

    /**
     * 获得英雄人物
     *
     * @return
     */
    public abstract Builder setPerson();
}
```

#### 两个具体Builder的实现

```
public class DeMaBuilder extends Builder {

    /**
     * 设置出装的顺序和展示
     */
    @Override
    public void setOrderAndShow() {
        mBasePerson.magic();
        mBasePerson.shoe();
        mBasePerson.velocity();
        mBasePerson.strength();
    }

    /**
     * 获得英雄人物
     *
     * @return
     */
    @Override
    public Builder setPerson() {
        mBasePerson = new DeMaXiYa();
        return this;
    }
}
```

```
public class HouZiBuilder extends Builder {

    /**
     * 设置出装的顺序和展示
     */
    @Override
    public void setOrderAndShow() {
        mBasePerson.magic();
        mBasePerson.shoe();
        mBasePerson.velocity();
        mBasePerson.strength();
    }

    /**
     * 获得英雄人物
     *
     * @return
     */
    @Override
    public Builder setPerson() {
        mBasePerson = new QiTianDaSehng();
        return this;
    }
}
```

除了类名不同，初始化不同其他完全相同

#### client的改进

```
public class client {
    public static void main(String[] args) {

        DeMaBuilder deMaBuilder = new DeMaBuilder();
        deMaBuilder.setPerson().setOrderAndShow();

        HouZiBuilder houZiBuilder = new HouZiBuilder();
        houZiBuilder.setPerson().setOrderAndShow();
    }
}
```

## 完整的Builder模式

增加导演类，再次包装builder类： 

![diagram3](http://oaxelf1sk.bkt.clouddn.com/diagram3.png)

### Director的实现

```
public abstract class Director {
    protected Builder mBuilder;

    /**
     * 调用builder中的方法
     */
    public abstract void construct();
}
```

```
public class DeMaDirector extends Director {

    public DeMaDirector() {
        mBuilder = new DeMaBuilder();
    }

    /**
     * 调用builder中的方法
     */
    @Override
    public void construct() {
        mBuilder.setPerson().setOrderAndShow();
    }
}
```

```
public class HouZiDirector extends Director {

    public HouZiDirector() {
        mBuilder = new HouZiBuilder();
    }

    /**
     * 调用builder中的方法
     */
    @Override
    public void construct() {
        mBuilder.setPerson().setOrderAndShow();
    }
}
```

### client的改进

```
public class client {
    public static void main(String[] args) {
        new DeMaDirector().construct();
        new HouZiDirector().construct();
    }
}
```



