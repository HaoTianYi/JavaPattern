package bean;

/**
 * @author HaoTianYi  hao.ty@haotianyi.win
 * @version v1.0
 */
public class Adress implements Cloneable {
    public String name;
    public int age;

    public Adress(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Adress() {
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return "Adress{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
