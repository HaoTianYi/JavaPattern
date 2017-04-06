import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author www.haotianyi.win
 * @version V1.0
 * @Description: 奥迪中国汽车总经销商
 * @date 2017/4/6 14:34
 */
public class ChinaProxy implements InvocationHandler {

    private Object china;
    private int menoy;

    public ChinaProxy(Object china, int menoy) {
        this.china = china;
        this.menoy = menoy;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("奥迪中国汽车总经销商");
        if (menoy < 50) {
            return null;
        } else {
            return method.invoke(china, args);
        }
    }
}
