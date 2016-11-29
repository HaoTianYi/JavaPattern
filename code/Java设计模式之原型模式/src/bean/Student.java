package bean;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author HaoTianYi  hao.ty@haotianyi.win
 * @version v1.0
 * 模拟一个
 */
public class Student implements Cloneable {
    public ArrayList<String> mList = new ArrayList<>();
    public String mString;
    public int mInt;
    public Adress mAdress = new Adress();

    public void addString(String string) {
        mList.add(string);
    }

    @Override
    public Student clone(){
        Student studentClone = null;
        try {
            studentClone = (Student) super.clone();
            studentClone.mList = (ArrayList<String>) this.mList.clone();
            studentClone.mAdress = (Adress) this.mAdress.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return studentClone;
    }

    @Override
    public String toString() {
        return "Student{" +
                "mList=" + mList +
                ", mString='" + mString + '\'' +
                ", mInt=" + mInt +
                ", mAdress=" + mAdress +
                '}';
    }
}
