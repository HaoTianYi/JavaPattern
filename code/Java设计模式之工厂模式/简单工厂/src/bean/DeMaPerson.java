package bean;

import base.BasePerson;

/**
 * @author HaoTianYi  hao.ty@haotianyi.win
 * @version v1.0
 */
public class DeMaPerson extends BasePerson {
    /**
     * 英雄可以说话
     */
    @Override
    public BasePerson talk() {
        System.out.println("德玛西亚------talk");
        return this;
    }

    /**
     * 英雄可以行走
     */
    @Override
    public BasePerson move() {
        System.out.println("德玛西亚------move");
        return this;
    }

    /**
     * 英雄可以打人
     */
    @Override
    public BasePerson hit() {
        System.out.println("德玛西亚------hit");
        return this;
    }
}
