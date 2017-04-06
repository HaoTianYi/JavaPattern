import java.lang.reflect.Proxy;

/**
 * @author www.haotianyi.win
 * @version V1.0
 * @Description: TODO
 * @date 2017/4/6 14:35
 */
public class Cilent {
    public static void main(String[] args) {
        AudiCar audiCar = (AudiCar) Proxy.newProxyInstance(AudiCar.class.getClassLoader(),
                new Class[]{AudiCar.class},
                new ChinaProxy(new GermanySeller(),46));
//       及时返回为null，也不会报出空指针，在newProxyInstance内部已经判断是否为空了
        audiCar.sellCar();
    }
}
