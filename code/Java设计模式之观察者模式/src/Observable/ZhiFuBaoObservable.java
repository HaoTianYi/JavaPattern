package Observable;

import java.util.Observable;

/**
 * @author HaoTianYi  hao.ty@haotianyi.win
 * @version v1.0
 */
public class ZhiFuBaoObservable extends Observable {
    /**
     * 没错，支付宝社交不死心，又更新了
     * @param string
     */
    public void upgrade(String string){

        //设置数据发生改变
        setChanged();
        //通知所有观察者，宝宝又改版了
        notifyObservers(string);
    }
}
