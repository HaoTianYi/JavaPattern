package message;

/**
 * @author HaoTianYi  hao.ty@haotianyi.win
 * @version v1.0
 */
public class TwoMessage extends BaseMessage {

    public TwoMessage(String content) {
        super.content = content;
    }

    /**
     * 表示消息的级别
     *
     * @return
     */
    @Override
    public int getLevel() {
        return 2;
    }
}
