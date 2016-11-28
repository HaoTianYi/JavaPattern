package lol;

import director.DeMaDirector;
import director.HouZiDirector;

/**
 * @author HaoTianYi  hao.ty@haotianyi.win
 * @version v1.0
 */
public class client {
    public static void main(String[] args) {
        new DeMaDirector().construct();
        new HouZiDirector().construct();
    }
}
