package bean;

import base.BasePerson;

/**
 * @author HaoTianYi  hao.ty@haotianyi.win
 * @version v1.0
 */
public class HouZiPerson extends BasePerson {
    /**
     * 英雄可以说话
     */
    @Override
    public BasePerson talk() {
        System.out.println("猴子------talk");
        return this;
    }

    /**
     * 英雄可以行走
     */
    @Override
    public BasePerson move() {
        System.out.println("猴子------move");
        return this;
    }

    /**
     * 英雄可以打人
     */
    @Override
    public BasePerson hit() {
        System.out.println("猴子------hit");
        return this;
    }
}
