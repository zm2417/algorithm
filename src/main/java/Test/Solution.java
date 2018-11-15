package Test;

import java.util.ArrayList;

public class Solution {
    class A {
        public void t(){
            System.out.println("a");
        }

    }

    class B extends A {
        @Override
        public void t() {
            System.out.println("b");
        }

        public void t1() {
            System.out.println("c");
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        Solution.A a = solution.new B();
    }
}
