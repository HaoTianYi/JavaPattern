package client;

import factory.XiaLuFactory1;
import factory.XiaLuFactory2;

/**
 * @author HaoTianYi  hao.ty@haotianyi.win
 * @version v1.0
 */
public class Client {
    public static void main(String[] args) {
        XiaLuFactory1 factory1 = new XiaLuFactory1();
        factory1.createAdc().hit().interesting();
        factory1.createFuZhu().protect().interesting();

        XiaLuFactory2 factory2 = new XiaLuFactory2();
        factory2.createAdc().hit().interesting();
        factory2.createFuZhu().protect().interesting();
    }
}
