package strategy;

/**
 * @author HaoTianYi  hao.ty@haotianyi.win
 * @version v1.0
 */
public class SubwayStrategy implements BaseStrategy {
    /**
     * 负责计算价格
     *
     * @param km 行驶的公里数
     * @return 价格的多少
     */
    @Override
    public int execute(int km) {
        if (km<5){
            return km;
        }else if (km<10){
            return 2*km;
        }else {
            return 25;
        }
    }
}
