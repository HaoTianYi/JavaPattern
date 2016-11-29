package strategy;

/**
 * @author HaoTianYi  hao.ty@haotianyi.win
 * @version v1.0
 */
public interface BaseStrategy {
    /**
     * 负责计算价格
     * @param km  行驶的公里数
     * @return  价格的多少
     */
    int execute(int km);
}
