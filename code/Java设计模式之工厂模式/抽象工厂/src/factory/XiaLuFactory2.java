package factory;

import base.AbstractFactory;
import base.Adc;
import base.FuZhu;
import person.HanBingAdc;
import person.HuoNanFuZhu;

/**
 * @author HaoTianYi  hao.ty@haotianyi.win
 * @version v1.0
 */
public class XiaLuFactory2 extends AbstractFactory{
    @Override
    public Adc createAdc() {
        return new HanBingAdc();
    }

    @Override
    public FuZhu createFuZhu() {
        return new HuoNanFuZhu();
    }
}
