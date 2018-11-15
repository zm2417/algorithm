package leetcode100_200;

import leetcode0_100.ListNode;
import org.omg.PortableInterceptor.INACTIVE;

import java.util.*;

public class Solution {

    /**
     * number of 1 bits
     * @param n
     * @return
     */
    public int hammingWeight(int n) {
        return Integer.bitCount(n);
    }

    /**
     * leetcode 198 house robber
     * DP algorithm
     * @param nums
     * @return
     */
    public int rob(int[] nums) {
        /**
         * O(n)
         */
//        int[] arr = new int[nums.length];
//        if(nums.length == 0)
//            return 0;
//        if(nums.length>0){
//            arr[0] = nums[0];
//        }
//        if(nums.length>1){
//            arr[1] = Math.max(nums[0],nums[1]);
//        }
//        for(int i = 2;i<nums.length;i++){
//            arr[i] = Math.max(arr[i-2]+nums[i],arr[i-1]);
//        }
//        return arr[nums.length-1];
        /**
         * O(1)
         */
        int preNo = 0;
        int preYes = 0;
        for(int i : nums){
            int tem = preNo;
            preNo = Math.max(preNo,preYes);
            preYes =i+tem;
        }
        return Math.max(preNo,preYes);
    }

    /**
     * leetcode 119 pascal's triangle II
     * @param rowIndex start from 0
     * @return
     */
    public List<Integer> getRow(int rowIndex) {
//        List<List<Integer>> lists = new ArrayList<>();
////        for(int i = 0;i<=rowIndex;i++){
////            List<Integer> list = new ArrayList<>();
////            list.add(1);
////            if(i == 0){
////                lists.add(list);
////                continue;
////            }
////            List<Integer> pre = lists.get(i-1);
////            for(int j = 1;j<i;j++){
////                list.add(pre.get(j)+pre.get(j-1));
////            }
////            list.add(1);
////            lists.add(list);
////        }
////        return lists.get(rowIndex);
        List<Integer> list = new ArrayList<>();
        list.add(1);
        for(int i =0;i<=rowIndex;i++){
            if(i==0){
                continue;
            }
            int k = list.get(0);
            for(int j =1;j<i;j++){
                int t = k+list.get(j);
                k = list.get(j);
                list.set(j,t);
            }
            list.add(1);
        }
        return list;
    }

    /**
     * leetcode 172 factorial trailing zeroes
     * @param n
     * @return
     */
    public int trailingZeroes(int n) {
        int count=0;
        while (n>1){
            count += n/5;
            n /=5;
        }
        return count;
    }

    /**
     * leetcode min stack
     */
    class MinStack {

        private Stack<Integer> stack;
        private int min;

        /** initialize your data structure here. */
        public MinStack() {
            stack = new Stack<>();
            min = Integer.MAX_VALUE;
        }

        public void push(int x) {
            if(x<= min){
                stack.push(min);
                min = x;
            }
            stack.push(x);
        }

        public void pop() {
            if(stack.pop() == min)
                min = stack.pop();
        }

        public int top() {
            return stack.peek();
        }

        public int getMin() {
            return min;
        }
    }

    /**
     * leetcode 160 intersection of two linked lists
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if(headA == null || headB == null)
            return null;
        int i=1,j =1;
        ListNode temp = headA;
        while (temp != null){
            i++;
            temp =temp.next;
        }
        temp = headB;
        while (temp != null){
            j++;
            temp = temp.next;
        }
        if(i > j){
            int mask = i-j;
            while (mask > 0){
                headA = headA.next;
                mask--;
            }
        }else if(i < j){
            int mask = j-i;
            while (mask>0){
                headB = headB.next;
                mask--;
            }
        }
        while (headA != null){
            if(headA == headB){
                return headA;
            }else {
                headA = headA.next;
                headB = headB.next;
            }
        }
        return null;
    }

    /**
     * leetcode 190 reverse bits
     * @param n
     * @return
     */
    public int reverseBits(int n) {
//        char[] temp = Integer.toBinaryString(n).toCharArray();
//        int i = 0;
//        int j = temp.length;
//        for(;i<j;i++,j--){
//            char t = temp[i];
//            temp[i] = temp[j];
//            temp[j] = t;
//        }
        int res = 0;
        for(int i = 0; i < 32; i++, n >>= 1){
            res = res << 1 | (n & 1);
        }
        return res;
    }

    /**
     * leetcode 125 valid palindrome
     * improve : 1.toLowerCase 2.isLetterOrDigit
     * @param s
     * @return
     */
    public boolean isPalindrome(String s) {
        char[] temp = s.toCharArray();
        for (int i =0,j=temp.length-1;i<j;){
            if(!((temp[i] >= '0'&& temp[i] <= '9')||(temp[i] >= 'a' && temp[i] <= 'z')||(temp[i] >= 'A'&& temp[i]<= 'Z'))){
                i++;
                continue;
            }
            if(!((temp[j] >= '0'&& temp[j] <= '9')||(temp[j] >= 'a' && temp[j] <= 'z')||(temp[j] >= 'A'&& temp[j]<= 'Z'))){
                j--;
                continue;
            }
            if(temp[i] >= '0'&& temp[i] <= '9'){
                if(temp[i] == temp[j]){
                    i++;
                    j--;
                }else
                    return false;
            }else {
                if(temp[i] == temp[j] || temp[i] - temp[j] == 32 || temp[j] - temp[i] == 32){
                    i++;
                    j--;
                }else {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * leetcode 168 Excel Sheet Column Title
     * @param n
     * @return
     */
    public String convertToTitle(int n) {
        String ret = "";
        while(n>0)
        {
            ret = (char)((n-1)%26+'A') + ret;
            n = (n-1)/26;
        }
        return ret;
    }

    /**
     * leetcode 189 rotate array
     * @param nums
     * @param k
     */
    public void rotate(int[] nums, int k) {
        /**
         * one : O(n*k)
         */
//        if (nums == null || k < 0) {
//            throw new IllegalArgumentException("Illegal argument!");
//        }
//
//        for (int i = 0; i < k; i++) {
//            for (int j = nums.length - 1; j > 0; j--) {
//                int temp = nums[j];
//                nums[j] = nums[j - 1];
//                nums[j - 1] = temp;
//            }
//        }
        /**
         * two : O(n)
         * 1. Divide the array two parts: 1,2,3,4 and 5, 6
         * 2. Rotate first part: 4,3,2,1,5,6
         * 3. Rotate second part: 4,3,2,1,6,5
         * 4. Rotate the whole array: 5,6,1,2,3,4
         */
//        k = k%nums.length;
//        if(nums == null || k<0)
//            return;
//        int left = nums.length-k;
//        reverse(nums,0,left-1);
//        reverse(nums,left,nums.length-1);
//        reverse(nums,0,nums.length-1);
        /**
         * three : O(n)
         */
        if(nums.length == 0 || (k%=nums.length) == 0) return;
        k = k%nums.length;
        int mask = 0;
        int i = 0;
        int temp = nums[0];
        int state = 0;
        while (mask<nums.length){
            i = (i+k)%nums.length;
            int t = nums[i];
            nums[i] = temp;
            mask++;
            if(i == state){
                state++;
                i++;
                temp = nums[i];
            }else
                temp = t;
        }
    }

    public void reverse(int[] num,int left,int right){
        while (left<right){
            int temp = num[left];
            num[left] = num[right];
            num[right] = temp;
            left++;
            right--;
        }
    }

    /**
     * leetcode 122
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        int sum = 0;
        for(int i = 0; i<prices.length-1; i++){
            if(prices[i] < prices[i+1]){
                sum = sum + prices[i+1] - prices[i];
            }
        }
        return sum;
    }

    /**
     * leetcode 169
     * @param nums
     * @return
     */
    public int majorityElement(int[] nums) {
        //int max = 0;
        int length = nums.length;
        Map<Integer,Integer> map = new HashMap<>();
        for(int i : nums){
            if(map.containsKey(i)){
                map.put(i,map.get(i)+1);
            }else {
                map.put(i,1);
            }
        }
        Iterator it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            int key = (Integer) entry.getKey();
            int value = (Integer) entry.getValue();
            if(value > length){
                return key;
            }
        }
        return 0;
    }

    /**
     * leetcode 137 single number II
     * medium
     * @param nums array
     * @return single number
     */
    public int singleNumber(int[] nums) {
//        if(nums.length == 1) return nums[0];
//        Arrays.sort(nums);
//        for (int i = 0;i<nums.length;i++){
//            if(i == 0 && nums[i] != nums[i+1]) return nums[i];
//            else if(i == nums.length-1 && nums[i] != nums[i-1]) return nums[i];
//            else if(i > 0 && i < nums.length-1 && nums[i] != nums[i-1] && nums[i] != nums[i+1]) return nums[i];
//        }
//        return 0;
        /**
         * bit operation
         */
//        int res = 0;
//        for (int i = 0;i<32;i++){
//            int sum = 0;
//            for (int n : nums)
//                sum += (n>>i) & 1;
//            res |= (sum % 3) << i;
//        }
//        return res;
        /**
         * one-two-three
         */
//        int one = 0,two = 0,three = 0;
//        for (int i : nums){
//            two |= one & i; // twos holds the num that appears twice
//            one ^= i; // ones holds the num that appears once
//            three = one & two; // threes holds the num that appears three times
//            // if num[i] appears three times
//            // doing this will clear ones and twos
//            one &= ~three;
//            two &= ~three;
//        }
//        return one;
        /**
         * one-two
         * 00 -> 01 -> 10 -> 00
         * 0     1     2     3
         *
         * b = b xor r & ~a;
         * a = a xor r & ~b;
         */
        int one = 0,two = 0;
        for (int i : nums){
            two = (two ^ i) & ~ one;
            one = (one ^ i) & ~ two;
        }
        return two;
    }

    /**
     * 153 find minimum in rotated sorted array
     * @param nums array
     * @return minimum element
     */
    public int findMin(int[] nums) {
        int left = 0;
        int right = nums.length-1;
        if(nums.length == 1)
            return nums[0];
        if(nums[left] < nums[right])
            return nums[0];
        while (left<=right){
            int mid = (left+right)/2;
            if(nums[mid] > nums[mid+1]) return nums[mid+1];
            if(nums[mid-1] > nums[mid]) return nums[mid];
            if(nums[mid] > nums[0])
                left = mid + 1;
            else
                right = mid - 1;
        }
        return -1;
    }

    /**
     * 162 find peak element
     * O(logN)
     * @param nums array
     * @return any one of the peak
     */
    public int findPeakElement(int[] nums) {
        int l = 0,r = nums.length-1;
        while (l<r){
            int mid = (l+r)/2;
            if(nums[mid] > nums[mid+1])
                r = mid;
            else
                l = mid + 1;
        }
        return l;
    }

    /**
     * 142 linked list cycle
     */
    public ListNode detectCycle(ListNode head) {
        if (head == null)
            return null;
        ListNode slow = head,fast = head;
        while (true) {
            if (fast.next == null || fast.next.next == null)
                return null;
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast)
                break;
        }
        slow = head;
        while (true) {
            if (slow == fast) {
                return slow;
            }
            slow = slow.next;
            fast = fast.next;
        }
    }

    public static void main(String[] args){
        Solution solution = new Solution();
        solution.findMin(new int[]{2,1});
    }

}
