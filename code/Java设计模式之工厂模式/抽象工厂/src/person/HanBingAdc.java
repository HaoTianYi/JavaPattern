package person;

import base.Adc;

/**
 * @author HaoTianYi  hao.ty@haotianyi.win
 * @version v1.0
 * 模拟寒冰的类
 */
public class HanBingAdc extends Adc {
    /**
     * adc输出的方法
     */
    @Override
    public Adc hit() {
        System.out.println("寒冰-----------hit");
        return this;
    }

    /**
     * 每一个英雄有很有趣
     */
    @Override
    public void interesting() {
        System.out.println("寒冰-----------interesting");
    }
}
