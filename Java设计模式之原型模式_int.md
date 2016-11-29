# Java设计模式之原型模式

[TOC]

## 简介

原型模式是一种创建型设计模式,它通过复制一个已经存在的实例来返回新的实例,而不是新建实例.被复制的实例就是我们所称的原型,这个原型是可定制的.

原型模式适合在场景：

一是类初始化需要消化非常多的资源，这个资源包括数据、硬件资源等；

二是通过 new 产生一个对象需要非常繁琐的数据准备或访问权限，则可以使用原型模式；

三是一个对象需要提供给其他对象访问，而且各个调用者可能都需要修改其值时，可以考虑使用原型模式拷贝多个对象供调用者使用。

在实际项目中，原型模式很少单独出现，一般是和工厂方法模式一起出现，通过 clone的方法创建一个对象，然后由工厂方法提供给调用者。

## 以拷贝学生为例

### 浅拷贝

Java做了一个偷懒的拷贝动作， Object类提供的方法clone只是拷贝本对象，其对象内部的数组、引用对象等都不拷贝，还是指向原生对象的内部元素地址，这种拷贝就叫做浅拷贝，确实是非常浅，两个对象共享了一个私有变量，你改我改大家都能改，是一个种非常不安全的方式

### 实例

![diagram11291543](http://oaxelf1sk.bkt.clouddn.com/diagram11291543.png)

####地址类实现

```
public class Adress implements Cloneable {
    public String name;
    public int age;

    public Adress(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Adress() {
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return "Adress{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
```
####学生类实现
```
public class Student implements Cloneable {
    public ArrayList<String> mList = new ArrayList<>();
    public String mString;
    public int mInt;
    public Adress mAdress = new Adress();

    public void addString(String string) {
        mList.add(string);
    }

    @Override
    public Student clone(){
        Student studentClone = null;
        try {
            studentClone = (Student) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return studentClone;
    }

    @Override
    public String toString() {
        return "Student{" +
                "mList=" + mList +
                ", mString='" + mString + '\'' +
                ", mInt=" + mInt +
                ", mAdress=" + mAdress +
                '}';
    }
}
```
####客户端类实现
```
public class client {
    public static void main(String[] args) {
        Student student = new Student();
        System.out.println(student);

        Student clone = student.clone();
        clone.addString("测试");
        clone.mString = "你好";
        clone.mInt = 20;
        clone.mAdress.name= "adress";
        clone.mAdress.age = 88888;

        System.out.println(clone);
        System.out.println(student);

    }
}
```

![sp161129_153401](http://oaxelf1sk.bkt.clouddn.com/sp161129_153401.png)

Java做了一个偷懒的拷贝动作， Object类提供的方法clone只是拷贝本对象，其对象**内部的数组**、**引用对象**等都不拷贝，还是指向原生对象的内部元素地址，这种拷贝就叫做浅拷贝。

注意**string是拷贝的值**（很多资料是拷贝地址，作者环境jdk1.8）

## 使用深拷贝

更改Student类的实现：

```
    @Override
    public Student clone(){
        Student studentClone = null;
        try {
            studentClone = (Student) super.clone();
            studentClone.mList = (ArrayList<String>) this.mList.clone();
            studentClone.mAdress = (Adress) this.mAdress.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return studentClone;
    }
```

![sp161129_153719](http://oaxelf1sk.bkt.clouddn.com/sp161129_153719.png)

拷贝的对象与源对象没有关系，注意：**对象拷贝时，类的构造函数是不会被执行的**

