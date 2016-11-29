package person;

import base.Adc;

/**
 * @author HaoTianYi  hao.ty@haotianyi.win
 * @version v1.0
 * 模拟男枪的类
 */
public class NanQiangAdc extends Adc {
    /**
     * adc输出的方法
     */
    @Override
    public Adc hit() {
        System.out.println("男枪-----------hit");
        return this;
    }

    /**
     * 每一个英雄有很有趣
     */
    @Override
    public void interesting() {
        System.out.println("男枪-----------interesting");
    }
}
