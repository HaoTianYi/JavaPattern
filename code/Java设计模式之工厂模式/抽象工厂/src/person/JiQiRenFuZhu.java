package person;

import base.FuZhu;

/**
 * @author HaoTianYi  hao.ty@haotianyi.win
 * @version v1.0
 * 模拟机器人辅助
 */
public class JiQiRenFuZhu extends FuZhu {
    /**
     * 保护adc的方法
     */
    @Override
    public FuZhu protect() {
        System.out.println("机器人-----------protect");
        return this;
    }

    /**
     * 每一个英雄有很有趣
     */
    @Override
    public void interesting() {
        System.out.println("机器人-----------interesting");
    }
}
