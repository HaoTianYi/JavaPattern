package factory;

import base.BaseFactory;
import base.BasePerson;
import bean.HouZiPerson;

/**
 * @author HaoTianYi  hao.ty@haotianyi.win
 * @version v1.0
 */
public class HouZiFactory extends BaseFactory {
    @Override
    public BasePerson createPerson() {
        return new HouZiPerson();
    }
}
