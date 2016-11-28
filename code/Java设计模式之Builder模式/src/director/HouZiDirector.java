package director;

import base.Director;
import builder.HouZiBuilder;

/**
 * @author HaoTianYi  hao.ty@haotianyi.win
 * @version v1.0
 */
public class HouZiDirector extends Director {

    public HouZiDirector() {
        mBuilder = new HouZiBuilder();
    }

    /**
     * 调用builder中的方法
     */
    @Override
    public void construct() {
        mBuilder.setPerson().setOrderAndShow();
    }
}
