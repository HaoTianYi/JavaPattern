package client;

import strategy.BusStrategy;
import strategy.SubwayStrategy;

/**
 * @author HaoTianYi  hao.ty@haotianyi.win
 * @version v1.0
 */
public class Client {
    public static void main(String[] args) {
        Context context = new Context(new BusStrategy());
        System.out.println(context.execute(10));

        Context context2 = new Context(new SubwayStrategy());
        System.out.println(context2.execute(10));
    }
}
