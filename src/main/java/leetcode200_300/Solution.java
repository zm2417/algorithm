package leetcode200_300;

import leetcode0_100.ListNode;
import org.w3c.dom.ls.LSInput;

import java.util.*;

public class Solution {

    /**
     * leetcode 202 happy number
     * @param n number
     * @return whether is happy number
     */
    public boolean isHappy(int n) {
        Set<Integer> set = new HashSet<>();
        int num = 0;
        while (true){
            List<Integer> list = new ArrayList<>();
            while (n != 0){
                list.add(n%10);
                n=n/10;
            }
            num = 0;
            for (int i : list){
                num = i*i+num;
            }
            if(num == 1){
                return true;
            }
            if(set.contains(num)){
                return false;
            }else {
                set.add(num);
                n = num;
            }
        }
    }

    /**
     * leetcode 182 Pascal's Triangle
     * @param numRows
     * @return
     */
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> list = new ArrayList<>();
        for(int i = 1;i<=numRows;i++){
            List<Integer> a = new ArrayList<>();
            List<Integer> b = new ArrayList<>();
            if(i != 1){
                b = list.get(i-2);
            }
            a.add(1);
            for(int j = 2;j<i;j++){
                a.add(b.get(j-2)+b.get(j-1));
            }
            if(i != 1){
                a.add(1);
            }
            list.add(a);
        }
        return list;
    }

    /**
     * leetcode 231 power of two
     * @param n
     * @return
     */
    public boolean isPowerOfTwo(int n) {
        if(n<0)
            return false;
        return Integer.bitCount(n)==1?true:false;
    }

    /**
     * leetcode 263 ugly number
     * @param num
     * @return
     */
    public boolean isUgly(int num) {
        if(num<=0){
            return false;
        }
        while (true){
            if(num==1){
                return true;
            }else if(num%2==0){
                num = num/2;
            }else if(num%3==0){
                num = num/3;
            }else if(num%5==0){
                num = num/5;
            }else {
                return false;
            }
        }
    }

    /**
     * leetcode 232 implement queue using stacks
     */
    class MyQueue {

        Stack<Integer> input = new Stack();
        Stack<Integer> output = new Stack();

        public void push(int x) {
            input.push(x);
        }

        public void pop() {
            peek();
            output.pop();
        }

        public int peek() {
            if (output.empty())
                while (!input.isEmpty())
                    output.push(input.pop());
            return output.peek();
        }

        public boolean empty() {
            return input.empty() && output.empty();
        }
    }

    /**
     * leetcode 205 Isomorphic string
     * @param s
     * @param t
     * @return
     */
    public boolean isIsomorphic(String s, String t) {
//        Map<Character,Character> maps = new HashMap<>();
////        Map<Character,Character> mapt = new HashMap<>();
////        for(int i = 0,len = s.length();i<len;i++){
////            if(maps.containsKey(s.charAt(i)) ){
////                if(maps.get(s.charAt(i))!=t.charAt(i)){
////                    return false;
////                }
////            }else if(mapt.containsKey(t.charAt(i))){
////                if(mapt.get(t.charAt(i)) != s.charAt(i))
////                    return false;
////            }
////            else {
////                maps.put(s.charAt(i),t.charAt(i));
////                mapt.put(t.charAt(i),s.charAt(i));
////            }
////        }
////        return true;
        int[] m1 = new int[256];
        int[] m2 = new int[256];
        for(int i =0,len = s.length();i<len;i++){
            char a = s.charAt(i);
            char b = t.charAt(i);
            if(m1[a] != m2[b]){
                return false;
            }
            m1[a] = i+1;
            m2[b] = i+1;
        }
        return true;
    }

    /**
     * leetcode 203 remove linked list elements
     * @param head
     * @param val
     * @return
     */
    public ListNode removeElements(ListNode head, int val) {
        ListNode trav = head;
        ListNode pre = null;
        while (trav != null){
            if(trav.val == val){
                if(pre == null){
                    head = head.next;
                    trav = head;
                }else {
                    pre.next = trav.next;
                    trav = trav.next;
                }
            }else {
                pre = trav;
                trav = trav.next;
            }
        }
        return head;
    }

    /**
     * leetcode 234 palindrome linked list
     * @param head
     * @return
     */
    public boolean isPalindrome(ListNode head) {
        ListNode slow = head;
        if(head == null || head.next == null)
            return true;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }
        ListNode mid = slow;
        ListNode fir = slow.next;
        ListNode curr = fir.next;
        fir.next = null;
        while (curr != null){
            ListNode next = curr.next;
            curr.next = mid.next;
            mid.next = curr;
            curr = next;
        }
        slow = head;
        fast = mid.next;
        while (fast != null){
            if(fast.val != slow.val){
                return false;
            }
            slow = slow.next;
            fast = fast.next;
        }
        return true;
    }

    /**
     * leetcode 290 world pattern
     * @param pattern
     * @param str
     * @return
     */
    public boolean wordPattern(String pattern, String str) {
        Map<Character,String> mapPattern = new HashMap<>();
        Map<String,Character> mapStr = new HashMap<>();
        char[] chars = pattern.toCharArray();
        String[] strings = str.split("\\s+");
        if(chars.length != strings.length)
            return false;
        for(int i=0,len = chars.length;i<len;i++){
            if(mapPattern.containsKey(chars[i])&&!mapPattern.get(chars[i]).equals(strings[i]))
                return false;
            if(mapStr.containsKey(strings[i])&&mapStr.get(strings[i])!=chars[i])
                return false;
            mapPattern.put(chars[i],strings[i]);
            mapStr.put(strings[i],chars[i]);
        }
        return true;
    }

    /**
     * leetcode 219 contains duplicate II
     * @param nums
     * @param k
     * @return
     */
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        int len = nums.length;
        if(len <= 1) return false;
        Set<Integer> set = new HashSet<>();
        for(int i = 0;i<len;i++){
            if(i>k)
                set.remove(nums[i-k-1]);
            if(!set.add(nums[i]))
                return true;
        }
        return false;
    }

    /**
     * leetcode 204 count primes
     * @param n
     * @return
     */
    public int countPrimes(int n) {
        if(n < 1 ){
            return 0;
        }
        int[] temp = new int[n];
        for(int i = 2;i<n;i++){
            if(temp[i] == 1){
                continue;
            }
            for(int j = 2;i*j<n;j++){
                temp[i*j] = 1;
            }
        }
        int count = 0;
        for(int i = 2;i<n;i++){
            if(temp[i] != 1){
                count++;
            }
        }
        return count;
    }

    /**
     * leetcode 278 first bad version
     * binary search
     * @param n
     * @return
     */
    public int firstBadVersion(int n) {
        int left = 1;
        int right = n;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (isBadVersion(mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    public boolean isBadVersion(int n){
        return true;
    }

    /**
     * leetcode 260 single number III
     * medium
     * @param nums
     * @return
     */
    public int[] singleNumber(int[] nums) {
        /**
         * one
         */
//        int[] r = new int[2];
//        Map<Integer,Integer> map = new HashMap<>();
//        for (int i : nums){
//            if (map.containsKey(i)){
//                map.put(i,2);
//            }else
//                map.put(i,1);
//        }
//        int i = 0;
//        for (Map.Entry entry : map.entrySet()){
//            if((int)entry.getValue() == 1){
//                r[i++] =(int) entry.getKey();
//            }
//        }
//        return r;
        /**
         * two
         * 通过遍历整个数组并求整个数组所有数字之间的 XOR，根据 XOR 的特性可以得到最终的结果为 AXORB = A XOR B；
         * 通过某种特定的方式，我们可以通过 AXORB 得到在数字 A 和数字 B 的二进制下某一位不相同的位；因为A 和 B 是不相同的，所以他们的二进制数字有且至少有一位是不相同的。我们将这一位设置为 1，并将所有的其他位设置为 0，我们假设我们得到的这个数字为 setBit；
         * 只需要在循环一次数组，将与上 bitFlag 为 0 的数字进行 XOR 运算，与上 bitFlag 不为 0 的数组进行独立的 XOR 运算。那么最后我们得到的这两个数字就是 A 和 B。
         */
        if (nums == null || nums.length == 0)
            return new int[0];
        int xor = 0;
        for (int num : nums){
            xor ^= num;
        }
        int setBit = 1;
        while ((xor & setBit) == 0){
            setBit <<= 1;
        }
        int ans[] = new int[2];
        // 将数组分为两组，两个single number分别在不同的数组中
        for (int num : nums){
            if ((num & setBit) == 0){
                ans[0] ^= num;
            }else{
                ans[1] ^= num;
            }
        }
        return ans;
    }

    /**
     * leetcode 238 product of array except self
     * @param nums
     * @return
     */
    public int[] productExceptSelf(int[] nums) {
        int sum = 1;
        int numberOfZero = 0;
        for (int i : nums){
            if(i == 0){
                numberOfZero++;
                continue;
            }
            sum *= i;
        }
        int[] res = new int[nums.length];
        if(numberOfZero > 1){
            return res;
        }
        for (int i = 0;i<nums.length;i++){
            if(numberOfZero == 1){
                if(nums[i] == 0){
                    res[i] = sum;
                }else {
                    res[i] = 0;
                }
            }else {
                res[i] = sum/nums[i];
            }
        }
        return res;
    }

    /**
     * leetcode 216 combination sum III
     * @param k
     * @param n
     * @return
     */
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> lists = new ArrayList<>();
        combinationSum3Helper(k,n,lists,new ArrayList<>(),1,0);
        return lists;
    }

    private void combinationSum3Helper(int k,int n,List<List<Integer>> lists,List<Integer> list,int left,int currentSum){
        if(k == 0){
            if(currentSum != n){
                return;
            }else {
                List<Integer> list1 = new ArrayList<>(list);
                lists.add(list1);
            }
        }
        for (int i = left;i<=9;i++){
            if(currentSum + i < n){
                list.add(i);
                combinationSum3Helper(k-1,n,lists,list,i+1,currentSum+i);
                list.remove(list.size()-1);
            }else if(currentSum + i == n && k == 1){
                List<Integer> list1 = new ArrayList<>(list);
                list1.add(i);
                lists.add(list1);
                return;
            }else if(currentSum + i > n || (currentSum+i==n && k>1)){
                return;
            }
        }
    }

    /**
     * leetcode 241 different ways to add parentheses
     * 分治算法
     * medium
     * @param input
     * @return
     */
    Map<String,List<Integer>> diffWayToComputeMap = new HashMap<>();
    public List<Integer> diffWaysToCompute(String input) {
//        List<Integer> list = new ArrayList<>();
//        for (int i = 0,len = input.length();i<len;i++){
//            char ch = input.charAt(i);
//            if(ch == '+' || ch == '-' || ch == '*'){
//                List<Integer> left = diffWaysToCompute(input.substring(0,i));
//                List<Integer> right = diffWaysToCompute(input.substring(i+1,len));
//                for (int l : left){
//                    for (int r : right){
//                        switch (ch){
//                            case '+':
//                                list.add(l+r);
//                                break;
//                            case '-':
//                                list.add(l-r);
//                                break;
//                            case '*':
//                                list.add(l*r);
//                                break;
//                        }
//                    }
//                }
//            }
//        }
//        if(list.size() == 0) list.add(Integer.parseInt(input));
//        return list;
        if(diffWayToComputeMap.containsKey(input)) return diffWayToComputeMap.get(input);
        List<Integer> list = new ArrayList<>();
        for (int i = 0;i<input.length();i++){
            char ch = input.charAt(i);
            if(ch == '+' || ch == '-' || ch == '*'){
                for (int l : diffWaysToCompute(input.substring(0,i))){
                    for (int r : diffWaysToCompute(input.substring(i+1,input.length()))){
                        if(ch == '+') list.add(l+r);
                        else if(ch == '-') list.add(l-r);
                        else list.add(l*r);
                    }
                }
            }
        }
        if(list.size() == 0) list.add(Integer.parseInt(input));
        diffWayToComputeMap.put(input,list);
        return list;
    }

    /**
     * leetcode 287 find the duplicate number
     * Floyd's Tortoise and Hare
     * If we interpret nums such that for each pair of index ii and value v_iv
     * ​i
     * ​​ , the "next" value v_jv
     * ​j
     * ​​  is at index v_iv
     * ​i
     * ​​ , we can reduce this problem to cycle detection.
     * @param nums
     * @return
     */
    public int findDuplicate(int[] nums) {
        // Find the intersection point of the two runners.
        // [2,5,9,6,9,3,8,9,7,1] output 7 expected 9
        int tortoise = nums[0];
        int hare = nums[0];
        do {
            tortoise = nums[tortoise];
            hare = nums[nums[hare]];
        } while (tortoise != hare);

//        return tortoise;
//         Find the "entrance" to the cycle.
        int ptr1 = nums[0];
        int ptr2 = tortoise;
        while (ptr1 != ptr2) {
            ptr1 = nums[ptr1];
            ptr2 = nums[ptr2];
        }

        return ptr1;
    }

    /**
     * 215 kth largest element in an array
     * use quick sort
     * @param nums array
     * @param k kth
     * @return kth element
     */
    public int findKthLargest(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k < 1 || k > nums.length) return -1;
        return findKthLargestQuickSort(nums,0,nums.length-1,nums.length-k);
    }

    private int findKthLargestQuickSort(int[] nums,int left,int right,int k){
        if(left >= right){
            return nums[k];
        }
        int i = left,j = right;
        int pivot = nums[(i+j)/2];
        while (i<=j){
            while (i <= j && nums[i] < pivot){
                i++;
            }
            while (i <= j && nums[j] > pivot){
                j--;
            }
            if(i <= j){
                swap(nums,i,j);
                i++;
                j--;
            }
        }
        if(k <= j)
            return findKthLargestQuickSort(nums,left,j,k);
        if(k >= i)
            return findKthLargestQuickSort(nums,i,right,k);
        return nums[k];
    }

    private void swap(int[] nums,int i,int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    /**
     * 240 search a 2-d matrix II
     * from top-right start compare with target
     * @param matrix matrix
     * @param target search value
     * @return boolean
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix == null || matrix.length == 0) return false;
        int i = 0,j = matrix[0].length-1;
        while (i<matrix.length && j>=0){
            if(matrix[i][j] == target) return true;
            else if(matrix[i][j] < target) i++;
            else j--;
        }
        return false;
    }

    /**
     * 289 game of life
     * @param board matrix
     */
    public void gameOfLife(int[][] board) {

    }

    public static void main(String[] args){
        Solution solution = new Solution();
        solution.findKthLargest(new int[]{3,2,1,5,6,4},2);
//        ListNode listNode1 = new ListNode(1);ListNode listNode2 = new ListNode(3);ListNode listNode3 = new ListNode(2);
//        ListNode listNode4 = new ListNode(4);ListNode listNode5 = new ListNode(3);ListNode listNode6 = new ListNode(2);
//        ListNode listNode7 = new ListNode(1);
//        listNode1.next=listNode2;listNode2.next=listNode3;listNode3.next=listNode4;listNode4.next=listNode5;listNode5.next=listNode6;
//        listNode6.next=listNode7;
    }

}
