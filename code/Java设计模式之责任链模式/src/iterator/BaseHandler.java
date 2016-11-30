package iterator;

import message.BaseMessage;

/**
 * @author HaoTianYi  hao.ty@haotianyi.win
 * @version v1.0
 */
public abstract class BaseHandler {
    /**
     * 处理信息的下一个节点
     */
    public BaseHandler nextHandler;

    /**
     * 根据消息的级别和消息处理类的级别来匹配相应的消息处理
     *
     * @param baseMessage
     */
    public void judge(BaseMessage baseMessage) {
        if (baseMessage.getLevel() == this.getLevel()) {
            execute(baseMessage);
        } else {
            nextHandler.execute(baseMessage);
        }
    }

    /**
     * 具体的执行方法
     */
    public abstract void execute(BaseMessage baseMessage);

    /**
     * 获得处理者能够处理消息的级别
     *
     * @return
     */
    public abstract int getLevel();
}
