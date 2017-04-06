import java.util.ArrayList;

/**
 * @author www.haotianyi.win
 * @version V1.0
 * @Description: 随机产生一个性别的描述
 * @date 2017/4/6 20:06
 */
public class Gender {
    private static ArrayList<String> persons = new ArrayList<>();

    static {
        persons.add("男");
        persons.add("女");
    }

    private Gender() {

    }

    public static String newInstance() {
        int random = (int) Math.random() * 2;
        return persons.get(random);
    }
}
