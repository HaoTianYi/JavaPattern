package base;

/**
 * @author HaoTianYi  hao.ty@haotianyi.win
 * @version v1.0
 */
public abstract class Director {
    protected Builder mBuilder;

    /**
     * 调用builder中的方法
     */
    public abstract void construct();
}
