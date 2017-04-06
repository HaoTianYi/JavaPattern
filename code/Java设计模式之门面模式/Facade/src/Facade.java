/**
 * @author www.haotianyi.win
 * @version V1.0
 * @Description: TODO
 * @date 2017/4/6 20:31
 */
public class Facade {

    private PullGift mPullGift;

    public Facade() {
        mPullGift = new PullGift();
        mPullGift.buyGift();
        mPullGift.location();
        mPullGift.go();
        mPullGift.giveGift();
    }
}
