package Observer;

import java.util.Observable;
import java.util.Observer;

/**
 * @author HaoTianYi  hao.ty@haotianyi.win
 * @version v1.0
 */
public class YinLianObserver implements Observer {

    @Override
    public void update(Observable o, Object arg) {
        System.out.println(arg+"----还是央妈好，饿不不死我，嘻嘻嘻");
    }
}
