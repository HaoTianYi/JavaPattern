package Observer;

import Observable.ZhiFuBao;

/**
 * @author HaoTianYi  hao.ty@haotianyi.win
 * @version v1.0
 */
public class WeiXin implements ZhiFuBao.MyObserver {
    @Override
    public void publishMessage(ZhiFuBao o, Object arg) {
        System.out.println(arg+"----微信表示我就看着你装B");
    }
}
