import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Geater500 {
    public boolean checkRecord(String s) {
        int length = s.length();
        int a = 0;
        int l = 0;
        for(int i = 0;i<length; i++){
            char c = s.charAt(i);
            if(c == 'A'){
                a++;
                l = 0;
            }else if(c == 'L'){
                l++;
            }else {
                l = 0;
            }
            if(a>1 || l>2){
                return false;
            }
        }
        return true;
    }

    /**
     * leetcode 350
     */
    public int[] intersect(int[] nums1, int[] nums2) {
        List<Integer> list = new ArrayList<>();
        Arrays.sort(nums2);
        Arrays.sort(nums1);
        for(int i = 0,j =0;i<nums1.length&&j<nums2.length;){
            if(nums1[i] == nums2[j]){
                list.add(nums1[i]);
                i++;j++;
            }else if(nums1[i]>nums2[j]){
                j++;
            }else {
                i++;
            }
        }
        int[] arr = new int[list.size()];
        for(int k = 0; k<list.size(); k++){
            arr[k] = list.get(k);
        }
        return arr;

    }

    /**
     * leetcode 541
     * @param s 字符串
     * @param k k值
     * @return reverse string
     */
    public String reverseStr(String s, int k) {
        char[] chars = s.toCharArray();
        for(int i = 0;i<s.length();i = i+2*k){
            int a = i;
            int b = Math.min(s.length(),i+k)-1;
            while(a<b){
                char temp = chars[a];
                chars[a] = chars[b];
                chars[b] = temp;
                a++;
                b--;
            }
        }
        String res = String.valueOf(chars);
        return res;
    }

    /**
     * leetcode 504 base 7
     * @param num integer base 10
     * @return base 7 string representation
     */
    public String convertToBase7(int num) {
        String c = "";
        if(num<0){
            c = "-";
        }else {
            c = "";
        }
        String s = "";
        List<Integer> remainders = new ArrayList<>();
        while(Math.abs(num) >= 7){
            remainders.add(num%7);
            num = num/7;
        }
        remainders.add(num);
        for(int i : remainders){
            s = Math.abs(i) + s;
        }
        s = c + s;
        return s;
    }

    /**
     * leetcode 741 min cost climbing stairs
     * @param cost stairs step cost
     * @return min cost
     */
    public int minCostClimbingStairs(int[] cost) {
        int[] dp = new int[cost.length+1];
        dp[0] = 0; dp[1] = 0;
        for(int i = 2, len = cost.length;i <= len;i++){
            dp[i] = Math.min(dp[i-1]+cost[i-1],dp[i-2]+cost[i-2]);
        }
        return dp[cost.length];
    }

    /**
     * leetcode 121 best time buy and sell stock
     * @param prices prices array
     * @return best profit
     */
    public int maxProfit(int[] prices) {
        int min = Integer.MAX_VALUE;
        int maxProfit = 0;
        for(int i = 0,len = prices.length;i<len;i++){
            if(min > prices[i]){
                min = prices[i];
            }
            if(maxProfit<prices[i]-min){
                maxProfit = prices[i]-min;
            }
        }
        return maxProfit;
    }

    /**
     * leetcode 744 find smallest letter greater than target
     * @param letters letters array
     * @param target target letter
     * @return smallest letter
     */
    public char nextGreatestLetter(char[] letters, char target) {
        for (int i = 0,len = letters.length; i<len; i++){
            if(letters[i]>target){
                return letters[i];
            }
        }
        return letters[0];
    }

    /**
     * leetcode 674 longest continuous increasing subsequence
     * @param nums 数组
     * @return
     */
    public int findLengthOfLCIS(int[] nums) {
        int max = 0;
        int tem = 0;
        for(int i = 0,len = nums.length; i<len; i++){
            if(i == 0 || nums[i-1]<nums[i]){
                tem++;
                max = Math.max(max,tem);
            }else {
                tem = 1;
            }
        }
        return max;
    }

    public static void main(String[] args){
        int[] num1 = {1,2,2,1};
        int[] num2 = {2,2};
        Geater500 geater500 = new Geater500();
//        int[] arr = geater500.intersect(num1,num2);
//        for(int i : arr){
//            System.out.println(i);
//        }
//        String s = geater500.reverseStr("abcdefg",2);
//        System.out.println(s);
//        System.out.println(geater500.convertToBase7(-10));
//        geater500.minCostClimbingStairs(new int[]{0,0,0,1});
//        geater500.maxProfit(new int[]{7,1});
//        geater500.findLengthOfLCIS(new int[]{1,2,3,4});
        System.out.println(-2>>>1);
        System.out.println(Integer.toBinaryString(-2));
        System.out.println(Integer.toBinaryString(2147483647));
        System.out.println(Integer.bitCount(-2));
    }
}
