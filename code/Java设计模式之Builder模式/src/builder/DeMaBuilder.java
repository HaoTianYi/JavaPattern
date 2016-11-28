package builder;

import java.util.ArrayList;

import base.BasePerson;
import base.Builder;
import person.DeMaXiYa;

/**
 * @author HaoTianYi  hao.ty@haotianyi.win
 * @version v1.0
 */
public class DeMaBuilder extends Builder {

    /**
     * 设置出装的顺序和展示
     */
    @Override
    public void setOrderAndShow() {
        mBasePerson.magic();
        mBasePerson.shoe();
        mBasePerson.velocity();
        mBasePerson.strength();
    }

    /**
     * 获得英雄人物
     *
     * @return
     */
    @Override
    public Builder setPerson() {
        mBasePerson = new DeMaXiYa();
        return this;
    }
}
