/**
 * @author HaoTianYi  hao.ty@haotianyi.win
 * @version v1.0
 */
public class Memento {
    /**
     * 表示金钱
     */
    public int money;
    /**
     * 表示人数
     */
    public int people;

    public String state;

    public Memento(int money, int people,String state) {
        this.money = money;
        this.people = people;
        this.state = state;
    }

    @Override
    public String toString() {
        return "Memento{" +
                "money=" + money +
                ", people=" + people +
                ", state='" + state + '\'' +
                '}';
    }
}
