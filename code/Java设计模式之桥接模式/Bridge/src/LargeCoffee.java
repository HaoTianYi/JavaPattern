/**
 * @author www.haotianyi.win
 * @version V1.0
 * @Description: TODO
 * @date 2017/4/7 1:35
 */
public class LargeCoffee extends Coffee {

    public LargeCoffee(CoffeeAdd coffeeAdd) {
        super(coffeeAdd);
    }

    @Override
    void makeCoffee() {
        System.out.println("大杯的"+mCoffeeAdd.addSomething());
    }
}
