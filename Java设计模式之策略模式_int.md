# Java设计模式之策略模式

[TOC]

## 策略模式简介

在策略模式（Strategy Pattern）中定义一系列的算法,把它们一个个封装起来, 并且使它们可相互替换。这种类型的设计模式属于行为型模式。

**主要解决：**在有多种算法相似的情况下，使用 if...else 所带来的复杂和难以维护，换句话说就是一个问题有不同的解决方法，可以考虑策略模式

**使用场景：** 1、如果在一个系统里面有许多类，它们之间的区别仅在于它们的行为，那么使用策略模式可以动态地让一个对象在许多行为中选择一种行为。 2、一个系统需要动态地在几种算法中选择一种。 3、如果一个对象有很多的行为，如果不用恰当的模式，这些行为就只好使用多重的条件选择语句来实现。

## 以选择公共交通方法为例

![diagram11291854](http://oaxelf1sk.bkt.clouddn.com/diagram11291854.png)

BaseStrategy表示策略的基类，两个实现子类是具体的策略也就是两个公共交通的计价策略，创建表示各种策略的对象和一个行为随着策略对象改变而改变的 context 对象。策略对象改变 context 对象的执行算法。

### 实现代码

```
public interface BaseStrategy {
    /**
     * 负责计算价格
     * @param km  行驶的公里数
     * @return  价格的多少
     */
    int execute(int km);
}
```

#### 俩中公共交通方式

```
public class BusStrategy implements BaseStrategy {

    /**
     * 负责计算价格
     *
     * @param km 行驶的公里数
     * @return 价格的多少
     */
    @Override
    public int execute(int km) {
        if (km<5){
            return 1;
        }
        return 2;
    }
}
```

```
public class SubwayStrategy implements BaseStrategy {
    /**
     * 负责计算价格
     *
     * @param km 行驶的公里数
     * @return 价格的多少
     */
    @Override
    public int execute(int km) {
        if (km<5){
            return km;
        }else if (km<10){
            return 2*km;
        }else {
            return 25;
        }
    }
}
```

#### context的实现

```
/**
 * @author HaoTianYi  hao.ty@haotianyi.win
 * @version v1.0
 *          负责选择策略
 */
public class Context {
    private BaseStrategy mBaseStrategy = null;

    public Context(BaseStrategy baseStrategy) {
        mBaseStrategy = baseStrategy;
    }

    public int execute(int km) {
        return this.mBaseStrategy.execute(km);
    }
}
```

#### Client的实现

```
public class Client {
    public static void main(String[] args) {
        Context context = new Context(new BusStrategy());
        System.out.println(context.execute(10));

        Context context2 = new Context(new SubwayStrategy());
        System.out.println(context2.execute(10));
    }
}
```

## 使用策略模式重构代码

在我自己的一个APP中，在开始进入APP有两种策略，Android版本大于4.4和小于4.4，使用策略模式重构代码

GitHub地址：[短信作弊器](https://github.com/HaoTianYi/FakeSms)

### 重构之前的逻辑

这个主要是负责在每次初始化的时候检查是否有权限人后更新状态，但是在4.4之后的策略不应该放在MainActivity中

```
        if (Build.VERSION.SDK_INT >= 20) {

            systemSms = getSystemDefaultSms();
            mIndex = getSharedPreferences("index", MODE_PRIVATE);
            int count = mIndex.getInt("count", 0);

            if (count == 0) {
                onclickSetSms(null);
                mIndex.edit().putString("sms", systemSms);
            } else {
                if (getPackageName().equals(Telephony.Sms.getDefaultSmsPackage(this))) {
                    mBtn.setBackgroundResource(R.drawable.btn_bg);
                    mBtn.setText("还原默认短信程序");
                    System.out.println(Telephony.Sms.getDefaultSmsPackage(this));
                }
            }

            mIndex.edit().putInt("count", ++count).commit();
            System.out.println(mIndex.getInt("count", 0));
        } else {
            mBtn.setVisibility(View.GONE);
            mTvHint.setText("请点击插入短信");
        }
```

下面负责4.4之后的方法逻辑，开始都在MainActivity中，代码臃肿

```
    /**
     * 设置默认的短信
     *
     * @param packageName
     */
    public void setDefaultSms(String packageName) {
        Intent intent = new Intent(Telephony.Sms.Intents.ACTION_CHANGE_DEFAULT);
        intent.putExtra(Telephony.Sms.Intents.EXTRA_PACKAGE_NAME, packageName);
        startActivity(intent);
    }


    public void onclickSetSms(View view) {
        if (!getSystemDefaultSms().equals(getPackageName())) {
            setDefaultSms(getPackageName());
            mBtn.setBackgroundResource(R.drawable.btn_bg);
            mBtn.setText("还原默认短信程序");
        } else {
            setDefaultSms(mIndex.getString("sms", "com.android.messaging"));
            mBtn.setBackgroundResource(R.drawable.btn_error);
            mBtn.setText("设置成默认短信程序");
        }
    }
```

### 重构之后的逻辑

```
//      添加策略模式
        if (Build.VERSION.SDK_INT >= 20) {
            new HighStrategy(MainActivity.this, mBtn).editSharedPreference(getSharedPreferences("index", MODE_PRIVATE));
        } else {
            new LowStrategy().setButtonStatus(mBtn).setHintStatus(mTvHint);
        }
```

然后上面的方法变成：

```
    /**
     * 设置默认的短信
     *
     * @param packageName
     */
    public void setDefaultSms(String packageName) {
        mHighStrategy.setDefaultSms(packageName);
    }

    /**
     * 按钮的变化
     * @param view
     */
    public void onclickSetSms(View view) {
        mHighStrategy.onclickSetSms(mIndex);
    }
```

把所有的都防到策略类中
