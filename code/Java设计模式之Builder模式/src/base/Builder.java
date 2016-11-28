package base;

import java.util.ArrayList;

/**
 * @author HaoTianYi  hao.ty@haotianyi.win
 * @version v1.0
 */
public abstract class Builder {
    /**
     * 表示与之对应的英雄
     */
    protected BasePerson mBasePerson;

    /**
     * 设置出装的顺序和展示
     */
    public  abstract void setOrderAndShow();

    /**
     * 获得英雄人物
     *
     * @return
     */
    public abstract Builder setPerson();
}
