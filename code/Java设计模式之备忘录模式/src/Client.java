/**
 * @author HaoTianYi  hao.ty@haotianyi.win
 * @version v1.0
 */public class Client {
    public static void main(String[] args) {
        Originator originator = new Originator();

        Caretaker caretaker = new Caretaker();
        caretaker.setMemento(originator.playGame().createMemento("状态一"));
        caretaker.setMemento(originator.playGame().createMemento("状态二"));

        originator.close();

        originator.restore(caretaker.getMemento("状态一"));
    }
}

