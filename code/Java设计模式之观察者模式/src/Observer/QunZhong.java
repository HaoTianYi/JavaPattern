package Observer;

import Observable.ZhiFuBao;

/**
 * @author HaoTianYi  hao.ty@haotianyi.win
 * @version v1.0
 */
public class QunZhong implements ZhiFuBao.MyObserver {
    @Override
    public void publishMessage(ZhiFuBao o, Object arg) {
        System.out.println(arg+"----吃瓜群众表示要红包");
    }
}
