package client;

import Observable.ZhiFuBao;
import Observable.ZhiFuBaoObservable;
import Observer.QunZhong;
import Observer.QunZhongObserver;
import Observer.WeiXin;
import Observer.WeiXinObserver;
import Observer.YinLian;
import Observer.YinLianObserver;

/**
 * @author HaoTianYi  hao.ty@haotianyi.win
 * @version v1.0
 */
public class Client {
    public static void main(String[] args) {
/*        ZhiFuBaoObservable zhiFuBaoObservable = new ZhiFuBaoObservable();

        QunZhongObserver qunZhongObserver = new QunZhongObserver();
        WeiXinObserver weiXinObserver = new WeiXinObserver();
        YinLianObserver yinLianObserver = new YinLianObserver();

        zhiFuBaoObservable.addObserver(qunZhongObserver);
        zhiFuBaoObservable.addObserver(weiXinObserver);
        zhiFuBaoObservable.addObserver(yinLianObserver);

        zhiFuBaoObservable.upgrade("第100次大改版，加入周围的有钱人功能");*/
        ZhiFuBao zhiFuBao = new ZhiFuBao();
        zhiFuBao.addObserver(new QunZhong());
        zhiFuBao.addObserver(new WeiXin());
        zhiFuBao.addObserver(new YinLian());

        zhiFuBao.publishMessage("第100次大改版，加入周围的有钱人功能");
    }
}
