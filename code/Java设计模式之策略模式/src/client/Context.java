package client;

import strategy.BaseStrategy;

/**
 * @author HaoTianYi  hao.ty@haotianyi.win
 * @version v1.0
 *          负责选择策略
 */
public class Context {
    private BaseStrategy mBaseStrategy = null;

    public Context(BaseStrategy baseStrategy) {
        mBaseStrategy = baseStrategy;
    }

    public int execute(int km) {
        return this.mBaseStrategy.execute(km);
    }
}
