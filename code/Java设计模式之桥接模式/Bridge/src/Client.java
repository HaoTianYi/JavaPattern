/**
 * @author www.haotianyi.win
 * @version V1.0
 * @Description: TODO
 * @date 2017/4/7 1:38
 */
public class Client {
    public static void main(String[] args) {
        new LargeCoffee(new AddNull()).makeCoffee();
        new SmallCoffee(new AddSugar()).makeCoffee();
    }
}
