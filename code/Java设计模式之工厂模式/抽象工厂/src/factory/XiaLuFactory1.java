package factory;

import base.AbstractFactory;
import base.Adc;
import base.FuZhu;
import person.JiQiRenFuZhu;
import person.NanQiangAdc;

/**
 * @author HaoTianYi  hao.ty@haotianyi.win
 * @version v1.0
 */
public class XiaLuFactory1 extends AbstractFactory {
    @Override
    public Adc createAdc() {
        return new NanQiangAdc();
    }

    @Override
    public FuZhu createFuZhu() {
        return new JiQiRenFuZhu();
    }
}
