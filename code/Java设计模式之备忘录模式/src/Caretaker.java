import java.util.HashMap;

/**
 * @author HaoTianYi  hao.ty@haotianyi.win
 * @version v1.0
 */
public class Caretaker {

    private HashMap<String, Memento> mMap;

    public Caretaker() {
        mMap = new HashMap<>();
    }

    public Memento getMemento(String state) {
        return mMap.get(state);
    }

    public void setMemento(Memento memento) {
        mMap.put(memento.state, memento);
    }
}
