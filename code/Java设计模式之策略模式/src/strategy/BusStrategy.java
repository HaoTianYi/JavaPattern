package strategy;

/**
 * @author HaoTianYi  hao.ty@haotianyi.win
 * @version v1.0
 */
public class BusStrategy implements BaseStrategy {

    /**
     * 负责计算价格
     *
     * @param km 行驶的公里数
     * @return 价格的多少
     */
    @Override
    public int execute(int km) {
        if (km<5){
            return 1;
        }
        return 2;
    }
}
