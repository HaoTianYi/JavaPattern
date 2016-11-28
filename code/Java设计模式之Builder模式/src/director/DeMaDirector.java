package director;

import base.Director;
import builder.DeMaBuilder;

/**
 * @author HaoTianYi  hao.ty@haotianyi.win
 * @version v1.0
 */
public class DeMaDirector extends Director {

    public DeMaDirector() {
        mBuilder = new DeMaBuilder();
    }

    /**
     * 调用builder中的方法
     */
    @Override
    public void construct() {
        mBuilder.setPerson().setOrderAndShow();
    }
}
