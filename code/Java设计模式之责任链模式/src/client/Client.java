package client;

import iterator.BaseHandler;
import iterator.OneHandler;
import iterator.ThreeHandler;
import iterator.TwoHandler;
import message.OneMessage;
import message.ThreeMessage;
import message.TwoMessage;

/**
 * @author HaoTianYi  hao.ty@haotianyi.win
 * @version v1.0
 */
public class Client {
    public static void main(String[] args) {
        BaseHandler oneHandler = new OneHandler();
        BaseHandler twoHandler = new TwoHandler();
        BaseHandler threeHandler = new ThreeHandler();

        oneHandler.nextHandler = twoHandler;
        twoHandler.nextHandler = threeHandler;
        threeHandler.nextHandler = oneHandler;

        oneHandler.judge(new OneMessage("第一个消息"));
        oneHandler.judge(new TwoMessage("第二个消息"));
        oneHandler.judge(new ThreeMessage("第三个消息"));
    }
}
