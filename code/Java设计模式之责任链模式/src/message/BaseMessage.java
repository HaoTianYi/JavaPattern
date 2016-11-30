package message;

/**
 * @author HaoTianYi  hao.ty@haotianyi.win
 * @version v1.0
 */
public abstract class BaseMessage {

    /**
     * 表示消息的内容
     */
    public String content;

    /**
     * 表示消息的级别
     * @return
     */
    public abstract int getLevel();
}
