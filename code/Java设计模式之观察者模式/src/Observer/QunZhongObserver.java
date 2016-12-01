package Observer;

import java.util.Observable;
import java.util.Observer;

/**
 * @author HaoTianYi  hao.ty@haotianyi.win
 * @version v1.0
 */
public class QunZhongObserver implements Observer {
    @Override
    public void update(Observable o, Object arg) {
        System.out.println(arg+"----吃瓜群众表示要红包");
    }
}
