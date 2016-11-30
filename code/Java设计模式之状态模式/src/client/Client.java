package client;

import context.Context;
import state.ClosingState;
import state.RunningState;

/**
 * @author HaoTianYi  hao.ty@haotianyi.win
 * @version v1.0
 */
public class Client {
    public static void main(String[] args) {
        Context context = new Context();
        context.setBaseState(new ClosingState());
        context.run().open().close();

        Context context1 = new Context();
        context1.setBaseState(new RunningState());
        context1.stop().open().run().open();
    }
}
