/**
 * @author www.haotianyi.win
 * @version V1.0
 * @Description: TODO
 * @date 2017/4/7 1:36
 */
public class SmallCoffee extends Coffee {

    public SmallCoffee(CoffeeAdd coffeeAdd) {
        super(coffeeAdd);
    }

    @Override
    void makeCoffee() {
        System.out.println("小杯的"+mCoffeeAdd.addSomething());
    }
}
