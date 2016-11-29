package person;

import base.FuZhu;

/**
 * @author HaoTianYi  hao.ty@haotianyi.win
 * @version v1.0
 * 模拟火男辅助
 */
public class HuoNanFuZhu extends FuZhu {
    /**
     * 保护adc的方法
     */
    @Override
    public FuZhu protect() {
        System.out.println("火男-----------protect");
        return this;
    }

    /**
     * 每一个英雄有很有趣
     */
    @Override
    public void interesting() {
        System.out.println("火男-----------interesting");
    }
}
