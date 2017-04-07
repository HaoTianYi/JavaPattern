/**
 * @author www.haotianyi.win
 * @version V1.0
 * @Description: TODO
 * @date 2017/4/7 1:32
 */
public abstract class Coffee {
    protected CoffeeAdd mCoffeeAdd;

    public Coffee(CoffeeAdd coffeeAdd) {
        mCoffeeAdd = coffeeAdd;
    }

    /**
     * 制作咖啡
     */
    abstract void makeCoffee();
}
