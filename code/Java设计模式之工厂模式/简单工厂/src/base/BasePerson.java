package base;

import sun.security.x509.AVA;

/**
 * @author HaoTianYi  hao.ty@haotianyi.win
 * @version v1.0
 * 表示人物的基本类
 */
public abstract class BasePerson {
    /**
     * 英雄可以说话
     */
    public abstract BasePerson talk();

    /**
     * 英雄可以行走
     */
    public abstract BasePerson move();

    /**
     * 英雄可以打人
     */
    public abstract BasePerson hit();
}
