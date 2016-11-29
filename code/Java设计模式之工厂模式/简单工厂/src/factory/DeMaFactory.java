package factory;

import base.BaseFactory;
import base.BasePerson;
import bean.DeMaPerson;

/**
 * @author HaoTianYi  hao.ty@haotianyi.win
 * @version v1.0
 */
public class DeMaFactory extends BaseFactory {
    @Override
    public  BasePerson createPerson() {
        return new DeMaPerson();
    }
}
