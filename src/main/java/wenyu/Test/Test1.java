package wenyu.Test;

import wenyu.learning.List.DemoUtils;
import wenyu.learning.List.MyListNode;
import wenyu.learning.List.ReverseNode;
import wenyu.sample.Algorithms4th.Stack;

import java.util.*;

/**
 * Created by Wenyu on 12/1/16.
 */
public class Test1 {

    public void func(int a) {

    }

    public void func(double a) {

    }

    public int func(int b, int c) {
        return 1;
    }

    public static void main(String[] args) {
        String aa = "aaabbb";
        String bb = new String("aaabbb");
        String cc = "aaa" + "bbb";

        System.out.println(aa == bb);
        System.out.println(aa == cc);
        System.out.println(aa == bb.intern());


    }
}