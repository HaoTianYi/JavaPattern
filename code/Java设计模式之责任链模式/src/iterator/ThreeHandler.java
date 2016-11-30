package iterator;

import message.BaseMessage;

/**
 * @author HaoTianYi  hao.ty@haotianyi.win
 * @version v1.0
 */
public class ThreeHandler extends BaseHandler {

    /**
     * 具体的执行方法
     *
     */
    @Override
    public void execute(BaseMessage baseMessage) {
            System.out.println(baseMessage.content+"----------"+"ThreeHandler---------正在执行");
    }

    /**
     * 获得处理者能够处理消息的级别
     *
     * @return
     */
    @Override
    public int getLevel() {
        return 3;
    }
}
