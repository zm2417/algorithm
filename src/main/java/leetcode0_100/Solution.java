package leetcode0_100;

import org.omg.PortableInterceptor.INACTIVE;

import java.util.*;

public class Solution {

    /**
     * 21 merge two sorted lists
     * @param l1 list node
     * @param l2 list node
     * @return ListNode
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if(l1 == null){
            return l2;
        }else if(l2 == null){
            return l1;
        }
        if(l2.val <l1.val){
            ListNode l3 = new ListNode(l2.val);
            l3.next = l1;
            l1 = l3;
            l2 = l2.next;
        }
        ListNode l = l1;
        while (l1.next != null && l2 != null){
            if(l1.val <= l2.val && l1.next.val>=l2.val){
                ListNode l4 = new ListNode(l2.val);
                l4.next = l1.next;
                l1.next = l4;
                l2 = l2.next;
            }
            l1 = l1.next;
        }
        if(l2 != null){
            l1.next = l2;
        }
        return l;
    }

    /**
     * 70 climbing stairs
     * f[n]=f[n-1]+f[n-2]
     * @param n int
     * @return int
     */
    public int climbStairs(int n) {
        if(n<4){
            return n;
        }
        int a = 2,b=3,c=5;
        for(int i =5;i<=n;i++){
            a = c;
            c = b+c;
            b = a;
        }
        return c;
    }

    /**
     * 27 remove element
     * @param nums
     * @param val
     * @return
     */
    public int removeElement(int[] nums, int val) {
        int i = 0;
        int j = nums.length-1;
        for(int len = nums.length;i<len;i++){
            if(nums[i] == val){
                while(j>i){
                    if(nums[j]!=val){
                        nums[i] = nums[i]^nums[j];
                        nums[j] = nums[i]^nums[j];
                        nums[i] = nums[i]^nums[j];
                        break;
                    }
                    j--;
                }
                if(j <= i){
                    return i;
                }
            }
        }
        return i;
    }

    /**
     * 1 two sum
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                result[1] = i + 1;
                result[0] = map.get(target - nums[i]);
                return result;
            }
            map.put(nums[i], i + 1);
        }
        return result;
    }

    /**
     * 53 maximum subarray
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        /**
         * one
         */
//        if(nums.length == 0) return 0;
//        int max = nums[0];
//        int sum = 0;
//        for(int i = 0; i<nums.length;i++){
//            sum = Math.max(nums[i],sum+nums[i]);
//            max = Math.max(sum,max);
//        }
//        return max;
        /**
         * two
         * Kadane's Algorithm
         */
//        int sum = 0;
//        int max = Integer.MIN_VALUE;
//        for(int i : nums){
//            if(sum<0)
//                sum=0;
//            sum +=i;
//            max = Math.max(max,sum);
//        }
//        return max;
        /**
         * three
         * the divide and conquer approach
         */
        if(nums.length == 0) return 0;
        return maximum(nums,0,nums.length-1);
    }

    public int maximum(int[] nums, int start, int end){
        if(start == end)
            return nums[start];
        int middle = (start+end)/2;
        int left = maximum(nums,start,middle);
        int right = maximum(nums,middle+1,end);

        int sum = 0;
        int leftmax = Integer.MIN_VALUE;
        for(int i = middle;i>=start;i--){
            sum += nums[i];
            leftmax = Math.max(sum,leftmax);
        }
        int rightmax =Integer.MIN_VALUE;
        sum = 0;
        for(int i = middle+1;i<=end;i++){
            sum += nums[i];
            rightmax = Math.max(rightmax,sum);
        }
        int tem = Math.max(left,right);
        return Math.max(tem,leftmax+rightmax);
    }

    /**
     * 83 remove duplicate from sorted list
     * @param head
     * @return
     */
    public ListNode deleteDuplicates(ListNode head) {
        Set<Integer> set = new HashSet<>();
        if(head == null){
            return head;
        }
        ListNode first = head;
        ListNode second = head.next;
        set.add(head.val);
        while (second != null){
            if(set.contains(second.val)){
                head.next = second.next;
                second = head.next;
            }else {
                set.add(second.val);
                head = head.next;
                if(head == null){
                    break;
                }
                second = head.next;
            }

        }
        return first;
    }

    /**
     * 53 search insert position
     * @param nums
     * @param target
     * @return
     */
    public int searchInsert(int[] nums, int target) {
        int start = 0;
        int end = nums.length-1;
        while (start<=end){
            int middle = (start+end)/2;
            if(nums[middle] == target){
                return middle;
            }else if(nums[middle]>target){
                end = middle-1;
            }else {
                start = middle+1;
            }
        }
        if(end == nums.length-1 || nums[start]<target){
            return start+1;
        }else {
            return start-1;
        }
    }

    /**
     * 66 plus one
     * @param digits
     * @return
     */
    public int[] plusOne(int[] digits) {
        for(int i = digits.length-1;i>=0;i--){
            if(digits[i] == 9){
                digits[i] = 0;
            }else {
                digits[i]++;
                break;
            }
        }
        if(digits[0] == 0){
            int[] newArr = new int[digits.length+1];
            newArr[0]=1;
            return newArr;
        }
        return digits;
    }

    /**
     * 38 count and say
     * @param n
     * @return
     */
    public static String countAndSay(int n) {
        StringBuilder temp = new StringBuilder("1");
        for(int i = 1;i<n;i++){
                StringBuilder pre = new StringBuilder(temp.substring(0,1));
                int count = 0;
                StringBuilder newTemp = new StringBuilder();
                for(int j = 0;j<temp.length();j++){
                    if(temp.substring(j,j+1).equals(pre.toString())){
                        count++;
                    }else {
                        newTemp = newTemp.append(count).append(pre);
                        count = 1;
                        pre =new StringBuilder(temp.substring(j,j+1));
                    }
                }
                temp = newTemp.append(count).append(pre);
        }
        return temp.toString();
    }

    /**
     * 9 palindrome number
     * @param x
     * @return
     */
    public boolean isPalindrome(int x) {
        if(x<0) return false;
        if(x == 0) return true;
        String s = String.valueOf(x);
        for(int i = 0,j=s.length()-1;i<j;i++,j--){
            if(!s.substring(i,i+1).equals(s.substring(j,j+1))){
                return false;
            }
        }
        return true;
    }

    /**
     * remove duplicates from sorted array
     * @param nums
     * @return
     */
    public int removeDuplicates(int[] nums) {
//        if(nums.length == 0) return 0;
//        int index = 1;
//        for(int i = 1;i<nums.length;i++){
//            if(nums[i] != nums[i-1]){
//                nums[index] = nums[i];
//                index++;
//            }
//        }
//        return index;
        int i = 0;
        for(int n : nums){
            if(i == 0 || n>nums[i-1]){
                nums[i++] = n;
            }
        }
        return i;
    }

    /**
     * 67 add binary
     * @param a
     * @param b
     * @return
     */
    public String addBinary(String a, String b) {
        StringBuilder stringBuilder = new StringBuilder();
        char[] a1 = a.toCharArray();
        char[] b1 = b.toCharArray();
        int len1 = a1.length-1;
        int len2 = b1.length-1;
        int mask = 0;
        while (len1 >= 0 || len2 >= 0){
            int i,j;
            if(len1 < 0){
                i=0;
            }else {
                i = a1[len1]-48;
            }
            if(len2 < 0){
                j = 0;
            }else {
                j = b1[len2]-48;
            }
            if(i+j>1 && mask == 0){
                stringBuilder.append(0);
                mask = 1;
            }else if(i+j>1 && mask == 1){
                stringBuilder.append(1);
                mask = 1;
            }else if(i+j == 1 && mask == 1){
                stringBuilder.append(0);
                mask = 1;
            }else {
                stringBuilder.append(i+j+mask);
                mask = 0;
            }
            len1--;
            len2--;
        }
        if(mask == 1)
            stringBuilder.append(1);
        return stringBuilder.reverse().toString();
    }

    /**
     * 141 linked list cycle
     * @param head
     * @return
     */
    public boolean hasCycle(ListNode head) {
        ListNode p = head;
        if(head == null || head.next == null) return false;
        ListNode q = head.next.next;
        while (p.next != null){
            if(p.equals(q)){
                return true;
            }
            p = p.next;
            if(q == null||q.next == null) return false;
            q = q.next.next;
        }
        return false;
    }

    /**
     * 20 valid
     * @param s
     * @return
     */
    public boolean isValid(String s) {
        char[] chars = s.toCharArray();
        if(chars.length%2 != 0) return false;
        Stack<Character> stack = new Stack<>();
        for(char c : chars){
            if(c == '{' || c == '[' || c == '('){
                stack.push(c);
            }else if(stack.isEmpty())
                return false;
            else if(c == '}' && stack.pop() != '{')
                return false;
            else if(c == ')' && stack.pop() != '(')
                return false;
            else if(c == ']' && stack.pop() != '[')
                return false;
        }
        return stack.isEmpty()?true:false;
    }

    /**
     * 88 merge sorted array
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        List<Integer> list = new ArrayList<>();
        for(int i=0,j=0;i<m||j<n;){
            if(i == m){
                list.add(nums2[j]);
                j++;
                continue;
            }
            if(j == n){
                list.add(nums1[i]);
                i++;
                continue;
            }
            if(nums1[i]>nums2[j]){
                list.add(nums2[j]);
                j++;
            }else {
                list.add(nums1[i]);
                i++;
            }
        }
        for(int i=0;i<list.size();i++){
            nums1[i] = list.get(i);
        }
    }

    /**
     * 58 length of last world
     * @param s
     * @return
     */
    public int lengthOfLastWord(String s) {
        String[] strings = s.split("\\s+");
        return strings.length == 0?0:strings[strings.length-1].length();
    }

    /**
     * 14 longest common prefix
     * @param strs
     * @return
     */
    public String longestCommonPrefix(String[] strs) {
        if(strs.length == 0)
            return "";
        int count = strs[0].length();
        String temp = strs[0];
        for (int i = 1;i<strs.length;i++){
            int mask = 0;
            for(int j = 0;j<count&&j<strs[i].length();j++){
                if(temp.charAt(j) == strs[i].charAt(j)){
                    mask++;
                }else {
                    break;
                }
            }
            if(mask == 0){
                return "";
            }
            count = Integer.min(count,mask);
        }
        return temp.substring(0,count);
    }

    /**
     * 28 implement strStr()
     * @param haystack
     * @param needle
     * @return
     */
    public int strStr(String haystack, String needle) {
        if(needle.isEmpty())
            return 0;
        if(haystack.isEmpty() || haystack.length()<needle.length())
            return -1;
        for(int i =0;i<haystack.length();i++){
            if(i+needle.length()>haystack.length())
                break;
            if(haystack.substring(i,i+needle.length()).equals(needle)){
                return i;
            }
        }
        return -1;
    }

    /**
     * 69 sqrt(x)
     * @param x
     * @return
     */
    public int mySqrt(int x) {
        if( x == 1 || x == 0){
            return x;
        }
        double n = x/2;
        double t = 0;
        do{
            t = n;
            n = (t+(x/t))/2;
        }while ((t-n)!= 0);
        return (int) n;
    }

    /**
     * 7 reverse number
     * @param x
     * @return
     */
    public int reverse(int x) {
        boolean mask = false;
        long temp = x;
        if(x<0){
            mask = true;
            temp = -1*temp;
        }
        String s =new StringBuilder(String.valueOf(temp)).reverse().toString() ;
        long t = Long.parseLong(s);
        if(t>Integer.MAX_VALUE || t<Integer.MIN_VALUE)
            return 0;
        return (int)(mask?-1*t:t);
    }

    /**
     * 13
     * @param s
     * @return
     */
    public int romanToInt(String s) {
        int sum = 0;
        Map<String,Integer> map = new HashMap<>();
        map.put("I",1);map.put("V",5);map.put("X",10);map.put("L",50);
        map.put("C",100);map.put("D",500);map.put("M",1000);
        char[] chars = s.toCharArray();
        for(int i = 0; i<chars.length; i++){
            if(i+1<chars.length){
                if(map.get(String.valueOf(chars[i]))>map.get(String.valueOf(chars[i+1]))){
                    sum = sum + map.get(String.valueOf(chars[i]));
                }else {
                    sum = sum - map.get(String.valueOf(chars[i]));
                }
            }else {
                sum = sum + map.get(String.valueOf(chars[i]));
            }
        }

        return sum;
    }

    /**
     * 22 generate parentheses
     * medium
     * @param n
     * @return
     */
    public List<String> generateParenthesis(int n) {
        List<String> list = new ArrayList<>();
        generateParenthesesHelper(list,0,n,new StringBuilder(),2*n);
        return list;
    }

    public void generateParenthesesHelper(List<String> list,int left,int i,StringBuilder sb,int n){
        if(i == 0){
            StringBuilder stringBuilder = new StringBuilder(sb);
            while (stringBuilder.length() < n){
                stringBuilder.append(")");
            }
            list.add(stringBuilder.toString());
            return;
        }
        StringBuilder stringBuilder = new StringBuilder(sb);
        if(left == 0){
            generateParenthesesHelper(list,1,i-1,stringBuilder.append("("),n);
        }else {
            generateParenthesesHelper(list,left-1,i,stringBuilder.append(")"),n);
            stringBuilder.deleteCharAt(stringBuilder.length()-1);
            generateParenthesesHelper(list,left+1,i-1,stringBuilder.append("("),n);
        }
    }

    /**
     * 46 permutations
     * @param nums
     * @return
     */
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> lists = new ArrayList<>();
        permuteHelper(nums,lists,new boolean[nums.length],new ArrayList<Integer>());
        return lists;
    }

    public void permuteHelper(int[] nums,List<List<Integer>> lists,boolean[] visited,List<Integer> list){
        if(list.size() == nums.length){
            lists.add(list);
            return;
        }
        for (int i = 0;i<nums.length;i++){
            if(!visited[i]){
                visited[i] = true;
                List<Integer> temp = new ArrayList<>(list);
                temp.add(nums[i]);
                permuteHelper(nums,lists,visited,temp);
                visited[i] = false;
            }
        }
    }

    /**
     * 12 integer to roman
     * @param num
     * @return
     */
    public String intToRoman(int num) {
        int[] values = { 1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1 };
        String[] strs = { "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I" };
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < values.length; i++) {
            while (num >= values[i]) {
                num -= values[i];
                sb.append(strs[i]);
            }
        }
        return sb.toString();
    }

    /**
     * 78 subsets
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> lists = new ArrayList<>();
        subsetsHelper(lists,new ArrayList<>(),0,nums);
        return lists;
    }

    public void subsetsHelper(List<List<Integer>> lists,List<Integer> list,int i,int[] nums){
        lists.add(new ArrayList<>(list));
        for (int j = i;j<nums.length;j++){
            list.add(nums[j]);
            subsetsHelper(lists,list,j+1,nums);
            list.remove(list.size()-1);
        }
    }

    /**
     * 48 rotate image
     * @param matrix n*n 2d matrix
     */
    public void rotate(int[][] matrix) {
//        int n = matrix.length-1;
//        for (int i = 0;i<=n/2;i++){
//            for (int j = i;j<n-i;j++){
//                int x = j-i;
//                int temp = matrix[i+x][n-i];
//                matrix[i+x][n-i] = matrix[i][j];
//                int temp1 = matrix[n-i][n-i-x];
//                matrix[n-i][n-i-x] = temp;
//                matrix[i][j] = matrix[n-i-x][i];
//                matrix[n-i-x][i] = temp1;
//            }
//        }
        if(matrix == null) return;
        for (int i = 0;i<matrix.length;i++){
            for (int j = i;j<matrix[0].length;j++){
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
        for(int i=0;i<matrix.length;i++){
            int arr[]=matrix[i];
            reverse(arr);
        }
        int i = 0;
    }
    public void reverse(int arr[]){
        int i=0,j=arr.length-1;
        while(i<j){
            int temp=arr[i];
            arr[i]=arr[j];
            arr[j]=temp;
            i++;
            j--;
        }
    }

    /**
     * 89 gray code
     * @param n non-negative
     * @return the sequence of gray code
     */
    public List<Integer> grayCode(int n) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0;i < Math.pow(2,n);i++){
            list.add((i >> 1) ^ i);
        }
        return list;
    }

    /**
     * 39 combination sum
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> lists = new ArrayList<>();
        combinationSumHelper(lists,new ArrayList<>(),candidates,target,0,0);
        return lists;
    }

    public void combinationSumHelper(List<List<Integer>> lists,List<Integer> list,int[] candidates,int target,int index,int current){
        if(current == target){
            List<Integer> temp = new ArrayList<>(list);
            lists.add(temp);
            return;
        }
        if(current > target) return;
        for (int i = index;i<candidates.length;i++){
            list.add(candidates[i]);
            combinationSumHelper(lists,list,candidates,target,i,current+candidates[i]);
            list.remove(list.size()-1);
        }
    }

    /**
     * 77 combinations
     * @param n
     * @param k
     * @return
     */
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> list = new ArrayList<>();
        if(k > n || k < 0) return list;
        Integer[]pool = new Integer[k];
        helper(list, pool, 0, 1, n, k);
        return list;
    }
    private void helper(List<List<Integer>> list, Integer[] pool, int pIndex, int from, int to, int count){
        if(count == 0){
            Integer[] arr = new Integer[pool.length];
            System.arraycopy(pool, 0, arr, 0, pool.length);
            list.add(Arrays.asList(arr));
        }else{
            int freeCount = (to - from + 1) - (count - 1);
            for(int i =0; i<freeCount; i ++){
                pool[pIndex] = from + i;
                helper(list, pool, pIndex + 1, from + i + 1, to, count - 1);
            }
        }
    }

    /**
     * 59 spiral matrix II
     * @param n integer
     * @return n^2 matrix
     */
    public int[][] generateMatrix(int n) {
        int[][] res = new int[n][n];
        int i = 1;
        int count = 0;
        while (i <= n*n){
            for (int j = count;j<n-count;j++){
                res[count][j] = i++;
            }
            for (int j = count+1;j<n-count;j++){
                res[j][n-count-1] = i++;
            }
            for (int j = n-count-2;j>=count;j--){
                res[n-count-1][j] = i++;
            }
            for (int j = n - count - 2;j>count;j--){
                res[j][count] = i++;
            }
            count++;
        }
        return res;
    }

    /**
     * 24 swap nodes in pairs
     * @param head ListNode head
     * @return new ListNode
     */
    public ListNode swapPairs(ListNode head) {
        if(head == null || head.next == null) return head;
        ListNode pre = head;
        ListNode cur = head.next;
        ListNode res = head.next;
        pre.next = cur.next;
        cur.next = pre;
        if(pre.next == null || pre.next.next == null)
            return res;
        cur = pre.next.next;
        while (pre != null && cur != null){
            ListNode t = pre.next;
            pre.next = cur;
            t.next = cur.next;
            cur.next = t;
            pre = t;
            if(t.next == null || t.next.next == null)
                return res;
            cur = t.next.next;
        }
        return res;
    }

    /**
     * 75 sort color
     * @param nums color array
     */
    public void sortColors(int[] nums) {
        // 0 - left - 1 - i - right - 2
//        int i = 0,left = 0,right = nums.length-1;
//        while (i<=right){
//            if(nums[i] == 0){
//                swap(nums,i,left);
//                i++;
//                left++;
//            }else if(nums[i] == 1){
//                i++;
//            }else {
//                swap(nums,i,right);
//                right--;
//            }
//        }

        int i = -1,j = -1,k = -1;
        for (int p = 0;p<nums.length;p++){
            // 根据第i个数，向后移动
            if(nums[p] == 0){
                nums[++k] = 2;
                nums[++j] = 1;
                nums[++i] = 0;
            }else if(nums[p] == 1){
                nums[++k] = 2;
                nums[++j] = 1;
            }else {
                nums[++k] = 2;
            }
        }
    }
    private void swap(int[] nums,int i,int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    /**
     * 90 subset II
     * @param nums array
     * @return List
     */
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> lists = new ArrayList<>();
        Set<String> set = new HashSet<>();
        Arrays.sort(nums);
        subsetWithDupHelper(nums,lists,new ArrayList<>(),0);
        return lists;
    }
    private void subsetWithDupHelper(int[] nums,List<List<Integer>> lists,List<Integer> list,int index){
        lists.add(new ArrayList<>(list));
        for (int i = index;i<nums.length;i++){
            if( i != index && nums[i] == nums[i-1]) continue;
            list.add(nums[i]);
            subsetWithDupHelper(nums,lists,list,i+1);
            list.remove(list.size()-1);
        }
    }

    /**
     * 79 Word Search
     */
    public boolean exist(char[][] board, String word) {
        if (word == null || word.length() == 0)
            return true;
        char[] temp = word.toCharArray();
        for (int i = 0;i<board.length;i++){
            for (int j = 0;j<board[i].length;j++){
                if (exist(board,temp,0,i,j))
                    return true;
            }
        }
        return false;
    }
    private boolean exist(char[][] board,char[] temp,int index,int i,int j){
         if (index == temp.length) return true;
         if (i < 0 || i >= board.length || j < 0 || j >= board[i].length || temp[index] != board[i][j]) return false;
         board[i][j] = '#';
         boolean res = exist(board,temp,index+1,i,j+1) || exist(board,temp,index+1,i,j-1) ||
                 exist(board,temp,index+1,i+1,j) || exist(board,temp,index+1,i-1,j);
         board[i][j] = temp[index];
         return res;
    }

    /**
     * 54 spiral order
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> list = new ArrayList<>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return list;
        int m = matrix.length,n = matrix[0].length;
        int t = m % 2 == 1 ? 1+m/2 : m/2;
        int x = n % 2 == 1 ? 1+n/2 : n/2;
        for (int k = 0;k<Math.min(t,x);k++){
            for (int i = k;i<n-k;i++){
                list.add(matrix[k][i]);
            }
            if (n-1-k >= k){
                for (int i = k+1;i<m-k;i++){
                    list.add(matrix[i][n-1-k]);
                }
            }
            if (m-1-k != k){
                for (int i = n-2-k;i>=k;i--){
                    list.add(matrix[m-1-k][i]);
                }
            }
            if (k != n-1-k){
                for (int i = m-2-k;i>k;i--){
                    list.add(matrix[i][k]);
                }
            }

        }
        return list;
    }

    /**
     * 31 next permutation
     */
    public void nextPermutation(int[] nums) {
        if (nums == null || nums.length == 0)
            return;
        int len = nums.length;
        int val = nums[len-1],index = -1;
        for (int i = len-2;i>=0;i--) {
            if (nums[i] < val){
                int t = Integer.MAX_VALUE,p = -1;
                for (int j = len-1;j>i;j--){
                     if (nums[j] > nums[i]){
                        if (t > nums[j]) {
                            t = nums[j];
                            p = j;
                        }
                     }
                }
                nums[p] = nums[i];
                nums[i] = t;
                index = i + 1;
                break;
            }else if (nums[i] > val) {
                val = nums[i];
            }
        }
        if (index != -1) {
            Arrays.sort(nums,index,len);
        }else {
            Arrays.sort(nums);
        }
    }

    public static void main(String[] args){
        Solution solution = new Solution();
        solution.nextPermutation(new int[]{2,3,1});
    }
}
