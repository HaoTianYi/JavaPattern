package Observable;

import java.util.ArrayList;

/**
 * @author HaoTianYi  hao.ty@haotianyi.win
 * @version v1.0
 */
public class ZhiFuBao {

    //  设置是否改变的钥匙
    private boolean changed = false;
    //  设置存放观察者的队列
    private ArrayList<MyObserver> obs;

    /**
     * Construct an Observable with zero Observers.
     */

    public ZhiFuBao() {
        obs = new ArrayList<>();
    }

    public void publishMessage(String string){
        setChanged();
        notifyObservers(string);
    }

    /**
     * 添加观察者
     *
     * @param o
     */
    public synchronized void addObserver(MyObserver o) {
        if (o == null)
            throw new NullPointerException();
        if (!obs.contains(o)) {
            obs.add(o);
        }
    }

    /**
     * 删除观察者
     *
     * @param o
     */
    public synchronized void deleteObserver(MyObserver o) {
        obs.remove(o);
    }

    public void notifyObservers() {
        notifyObservers(null);
    }

    /**
     * 通知所有的观察者，宝宝的数据发生了改变
     *
     * @param arg
     */
    public void notifyObservers(Object arg) {

        Object[] arrLocal;

        synchronized (this) {

            if (!changed)
                return;
            arrLocal = obs.toArray();
            clearChanged();
        }

        for (int i = arrLocal.length - 1; i >= 0; i--)
            ((MyObserver) arrLocal[i]).publishMessage(this, arg);
    }


    public synchronized void deleteObservers() {
        obs.removeAll(obs);
    }

    protected synchronized void setChanged() {
        changed = true;
    }


    protected synchronized void clearChanged() {
        changed = false;
    }


    public synchronized boolean hasChanged() {
        return changed;
    }

    public synchronized int countObservers() {
        return obs.size();
    }

    public interface MyObserver {
        /**
         * 发布消息
         */
        void publishMessage(ZhiFuBao o, Object arg);
    }

}
