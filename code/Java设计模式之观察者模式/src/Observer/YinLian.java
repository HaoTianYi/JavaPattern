package Observer;

import Observable.ZhiFuBao;

/**
 * @author HaoTianYi  hao.ty@haotianyi.win
 * @version v1.0
 */
public class YinLian implements ZhiFuBao.MyObserver {
    @Override
    public void publishMessage(ZhiFuBao o, Object arg) {
        System.out.println(arg+"----还是央妈好，饿不不死我，嘻嘻嘻");
    }
}
