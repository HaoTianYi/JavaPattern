package factory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import base.BasePerson;

/**
 * @author HaoTianYi  hao.ty@haotianyi.win
 * @version v1.0
 */
public class PersonFactory {

    private static Map<String,BasePerson> mBasePersons = new HashMap<>();

    public static BasePerson createPerson(Class c) {
        if (mBasePersons.containsKey(c.getSimpleName())){
            return mBasePersons.get(c.getSimpleName());
        }
        BasePerson basePerson = null;
        try {
            basePerson = (BasePerson) Class.forName(c.getName()).newInstance();
            mBasePersons.put(c.getSimpleName(),basePerson);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return basePerson;
    }
}
