# Java设计模式之多例模式

[TOC]

## 说明

所谓的多例模式（Multiton Pattern），实际上就是单例模式的自然推广。作为对象的创建模式，多例模式或多例类有如下的特点：

（1）多例类可有多个实例

（2）多例类必须自己创建、管理自己的实例，并向外界提供自己的实例。

（3）根据是否有实例上限分为：有上限多例类和无上限多例类（就是直接new）

### 特点

根据上面的说明那么多例模式应该有如下的体现：

1. 构造方法私有，防止外部构造对象
2. 内部含有一个对象管理集合
3. 有向外界提供自己实例的方法

## 实现

例如使用人类性别这一样本来构建一个有上限的多例类（上限就是2,男和女）

```
/**
 * @author www.haotianyi.win
 * @version V1.0
 * @Description: 随机产生一个性别的描述
 * @date 2017/4/6 20:06
 */
public class Gender {
    private static ArrayList<String> persons = new ArrayList<>();

    static {
        persons.add("男");
        persons.add("女");
    }

    private Gender() {

    }

    public static String newInstance() {
        int random = (int) Math.random() * 2;
        return persons.get(random);
    }
}
```

对应的客户端类：

```
public class Client {
    public static void main(String[] args) {
        System.out.println(Gender.newInstance());
    }
}
```

