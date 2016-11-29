package client;

import base.BasePerson;
import bean.DeMaPerson;
import bean.HouZiPerson;
import factory.DeMaFactory;
import factory.HouZiFactory;
import factory.PersonFactory;

/**
 * @author HaoTianYi  hao.ty@haotianyi.win
 * @version v1.0
 */
public abstract class Client{

    public static void main(String[] args) {
        new DeMaFactory().createPerson().talk().move().hit();
        new HouZiFactory().createPerson().hit().move().talk();
    }
}

/*        BasePerson deMaPerson = PersonFactory.createPerson(DeMaPerson.class);
        deMaPerson.hit().move().talk();

        BasePerson houZiPerson = PersonFactory.createPerson(HouZiPerson.class);
        houZiPerson.hit().move().talk();*/
