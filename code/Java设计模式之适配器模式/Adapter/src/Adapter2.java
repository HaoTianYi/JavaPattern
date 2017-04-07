/**
 * @author www.haotianyi.win
 * @version V1.0
 * @Description: TODO
 * @date 2017/4/7 1:08
 */
public class Adapter2 implements IFiveVolt{

    Volt220 mVolt220;

    public Adapter2(Volt220 volt220) {
        mVolt220 = volt220;
    }

    public int getVolt220() {
        return 220;
    }

    @Override
    public int getVolt5() {
        return 5;
    }
}
