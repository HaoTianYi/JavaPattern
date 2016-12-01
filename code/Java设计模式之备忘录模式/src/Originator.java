import java.util.Timer;

/**
 * @author HaoTianYi  hao.ty@haotianyi.win
 * @version v1.0
 *          表示文明6游戏类
 */
public class Originator {
    /**
     * 表示金钱
     */
    private int money;
    /**
     * 表示人数
     */
    private int people;

    public Originator playGame() {
        money += 2;
        people += 10;
        System.out.println("游戏开始：" + "金钱是" + money + "人数是" + people);
        return this;
    }

    public Memento createMemento(String state) {
        return new Memento(money, people,state);
    }

    public void restore(Memento memento) {
        this.money = memento.money;
        this.people = memento.people;
        System.out.println("游戏恢复：" + memento);
    }

    public void close(){
        this.money = 0;
        this.people = 0;
        System.out.println("游戏结束：" + "金钱是" + money + "人数是" + people);
    }
}
