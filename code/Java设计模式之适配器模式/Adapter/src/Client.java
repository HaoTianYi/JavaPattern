/**
 * @author www.haotianyi.win
 * @version V1.0
 * @Description: TODO
 * @date 2017/4/7 0:48
 */
public class Client {
    public static void main(String[] args) {
        Adapter2 adapter = new Adapter2(new Volt220());
        System.out.println(adapter.getVolt5());
    }
}
