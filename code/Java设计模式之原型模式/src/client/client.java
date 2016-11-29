package client;

import java.util.ArrayList;
import java.util.Arrays;

import bean.Adress;
import bean.Student;

/**
 * @author HaoTianYi  hao.ty@haotianyi.win
 * @version v1.0
 */
public class client {
    public static void main(String[] args) {
        Student student = new Student();
        System.out.println(student);

        Student clone = student.clone();
        clone.addString("测试");
        clone.mString = "你好";
        clone.mInt = 20;
        clone.mAdress.name= "adress";
        clone.mAdress.age = 88888;

        System.out.println(clone);
        System.out.println(student);

    }
}
