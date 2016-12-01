package Observer;

import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

/**
 * @author HaoTianYi  hao.ty@haotianyi.win
 * @version v1.0
 */
public class WeiXinObserver implements Observer {

    @Override
    public void update(Observable o, Object arg) {
        System.out.println(arg+"----微信表示我就看着你装B");
    }
}

