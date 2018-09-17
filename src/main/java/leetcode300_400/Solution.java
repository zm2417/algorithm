package leetcode300_400;

import leetcode0_100.ListNode;

import java.util.*;

public class Solution {

    /**
     * leetcode 387
     * @param s
     * @return
     */
    public static int firstUniqChar(String s) {
        int p = -1;
        Map<Character,Integer> map = new HashMap<>();
        char[] chars = s.toCharArray();
        for(char c : chars){
            if(map.containsKey(c)){
                map.put(c,map.get(c)+1);
            }else {
                map.put(c,1);
            }
        }
        char k = ' ';
        Iterator it = map.entrySet().iterator();
        while (it.hasNext()){
            Map.Entry entry = (Map.Entry) it.next();
            System.out.println(entry.getKey()+"   "+entry.getValue());
            if((int)entry.getValue() == 1){
                k = (char)entry.getKey();
                break;
            }
        }
        System.out.println("k = "+k);
        for (int i = 0;i<chars.length;i++){
            if(chars[i] == k){
                return i;
            }
        }
        return p;
    }

    /**
     * leetcode 326 power of three
     * @param n
     * @return
     */
    public boolean isPowerOfThree(int n) {
        return (Math.log10(n)/Math.log10(3))%1 == 0;
    }

    /**
     * leetcode 342 power four
     * @param num
     * @return
     */
    public boolean isPowerOfFour(int num) {
        return (Math.log10(num)/Math.log10(4))%1 == 0;
    }

    /**
     * leetcode 345 reverse vowels of string
     * @param s
     * @return
     */
    public String reverseVowels(String s) {
        Set<Character> set = new HashSet<>();
        set.add('a');set.add('e');set.add('i');set.add('o');set.add('u');
        set.add('A');set.add('E');set.add('I');set.add('O');set.add('U');
        int i = 0;
        int j = s.length()-1;
        char[] chars = s.toCharArray();
        while (i<j){
            if(!set.contains(chars[i])){
                i++;
                continue;
            }
            if(!set.contains(chars[j])){
                j--;
                continue;
            }
            char temp = chars[i];
            chars[i] = chars[j];
            chars[j] = temp;
            i++;
            j--;
        }
        return String.valueOf(chars);
    }

    /**
     * leetcode 367 valid prefect square
     * @param num
     * @return
     */
    public boolean isPerfectSquare(int num) {
//        int i = 1;
//        int n = 0;
//        while (n<num){
//            n +=i;
//            i = i+2;
//        }
//        return n == num;
        long x = num;
        while (x*x >num){
            x = (x+num/x)/2;
        }
        return x*x == num;
    }

    /**
     * leetcode 303 range sum query
     */
    int[] arr;
    void NumArray(int[] nums) {
        int len = nums.length;
        arr = new int[len];
        for(int i = 0;i<len;i++){
            if(i == 0){
                arr[0] = nums[0];
            }else {
                arr[i] = nums[i]+arr[i-1];
            }
        }
    }

    public int sumRange(int i, int j) {
        if(arr.length == 0)
            return 0;
        if(j>=arr.length)
            j = arr.length-1;
        if(i<0)
            return arr[j];
        return arr[j]-arr[i-1];
    }

    /**
     * leetcode 347 top k frequent elements
     * medium
     * time complexity must be better than O(n log n)
     * @param nums
     * @param k
     * @return
     */
    public List<Integer> topKFrequent(int[] nums, int k) {
        /**
         * one
         */
//        Arrays.sort(nums);
//        Map<Integer,List<Integer>> map = new TreeMap<>(new Comparator<Integer>() {
//            @Override
//            public int compare(Integer o1, Integer o2) {
//                return o2-o1;
//            }
//        });
//        for (int i=0;i<nums.length;){
//            int temp = 1;
//            int j = i+1;
//            for (;j<nums.length;j++){
//                if(nums[i] == nums[j]){
//                    temp++;
//                }else {
//                    break;
//                }
//            }
//            if(map.containsKey(temp)){
//                List<Integer> list = map.get(temp);
//                list.add(nums[i]);
//            }else {
//                List<Integer> list = new ArrayList<>();
//                list.add(nums[i]);
//                map.put(temp,list);
//            }
//            i = j;
//        }
//        List<Integer> res = new LinkedList<>();
//        for (Map.Entry entry : map.entrySet()){
//            for (int i : (List<Integer>)entry.getValue()){
//                if(res.size() < k){
//                    res.add(i);
//                }
//            }
//        }
//        return res;
        /**
         * two
         * bucket sort(桶排序)
         */
        List<Integer>[] bucket = new List[nums.length + 1];
        Map<Integer, Integer> frequencyMap = new HashMap<Integer, Integer>();

        for (int n : nums) {
            frequencyMap.put(n, frequencyMap.getOrDefault(n, 0) + 1);
        }

        for (int key : frequencyMap.keySet()) {
            int frequency = frequencyMap.get(key);
            if (bucket[frequency] == null) {
                bucket[frequency] = new ArrayList<>();
            }
            bucket[frequency].add(key);
        }

        List<Integer> res = new ArrayList<>();

        for (int pos = bucket.length - 1; pos >= 0 && res.size() < k; pos--) {
            if (bucket[pos] != null) {
                res.addAll(bucket[pos]);
            }
        }
        return res;
    }

    /**
     * leetcode 343 integer break
     * @param n
     * @return
     */
    public int integerBreak(int n) {
        if (n == 2 || n == 3) return n - 1;
        if (n == 4) return 4;
        n -= 5;
        return (int)Math.pow(3, (n / 3 + 1)) * (n % 3 + 2);
    }

    /**
     * leetcode 318 maximum product of word length
     * medium
     * 一个整型数int有32位，我们可以用后26位来对应26个字母，
     * 若为1，说明该对应位置的字母出现过，那么每个单词的都可由一个int数字表示，两个单词没有共同字母的条件是这两个int数想与为0
     * @param words
     * @return
     */
    public int maxProduct(String[] words) {
        int[] mask = new int[words.length];
        int max = 0;
        for (int i =0;i<words.length;i++){
            for (int j = 0;j<words[i].length();j++){
                mask[i] |= (1 << (words[i].charAt(j)-'a'));
            }
            for (int j = 0;j<i;j++){
                if((mask[i] & mask[j]) == 0){
                    max = Integer.max(max,words[i].length()*words[j].length());
                }
            }
        }
        return max;
    }

    /**
     * leetcode 375 count numbers with unique digits
     * @param n
     * @return
     */
    public int countNumbersWithUniqueDigits(int n) {
        if(n == 0) return 1;
        int[] temp = new int[11];
        temp[0] = 10;temp[1] = 81;
        for (int i =2;i<11;i++){
            int t = temp[i-1]*(10-i);
            temp[i] = t;
        }
        int count = 0;
        for (int i = 0;i<n&&i<11;i++){
            count += temp[i];
        }
        return count;
    }

    /**
     * leetcode 378 kth smallest element in a sorted matrix
     * medium
     * @param matrix
     * @param k
     * @return
     */
    public int kthSmallest(int[][] matrix, int k) {
//        int[] temp = new int[k];
//        int[] curr = new int[matrix.length];
//        for (int i = 0;i<k;i++){
//            int min = Integer.MAX_VALUE;
//            int mask = 0;
//            for (int j = 0;j<curr.length;j++){
//                if(curr[j] >= matrix[j].length){
//                    continue;
//                }
//                if(min > matrix[j][curr[j]]){
//                    min = matrix[j][curr[j]];
//                    mask = j;
//                }
//            }
//            temp[i] = min;
//            curr[mask]++;
//        }
//        return temp[k-1];
        /**
         * binary search
         * O(nlgn*lgX)
         * 查找小于中间值（left + right)/2 的个数，进行二分查找
         */
//        int n = matrix.length, left = matrix[0][0], right = matrix[n-1][matrix[0].length-1];
//        while (left < right){
//            int mid = (left+right)/2,num = 0,j = matrix[0].length-1;
//            for (int i = 0;i<n;i++){
//                while (j>=0 && matrix[i][j] > mid) j--;
//                num += (j+1);
//            }
//            if(num < k) left = mid+1;
//            else right = mid;
//        }
//        return left;
        /**
         * refine
         * O(nlgX)
         * 从左下角开始，向右或上遍历，找到小于mid的个数
         */
        int left = matrix[0][0], right = matrix[matrix.length-1][matrix[0].length-1];
        while (left < right){
            int mid = (left+right)/2;
            int count = kthSmallestHelper(matrix,mid);
            if(count < k) left = mid+1;
            else right = mid;
        }
        return left;
    }

    public int kthSmallestHelper(int[][] matrix, int val){
        int res = 0;
        int n = matrix.length,i = n-1,j  = 0;
        while (i >= 0 && j < n){
            if(matrix[i][j] > val) i--;
            else {
                res += i+1;
                j++;
            }
        }
        return res;
    }

    /**
     * leetcode 328 odd even linked list
     * @param head
     * @return
     */
    public ListNode oddEvenList(ListNode head) {
        if(head == null || head.next == null || head.next.next == null){
            return head;
        }
        ListNode a = head.next;
        ListNode pre = head;
        ListNode next = head.next;
        while (next != null && next.next != null){
            pre.next = next.next;
            next.next = next.next.next;
            pre = pre.next;
            next = next.next;
        }
        pre.next = a;
        return head;
    }

    /**
     * leetcode 392 is subsequence
     * @param s
     * @param t
     * @return
     */
    public boolean isSubsequence(String s, String t) {
//        int i = 0,j =0;
//        while (i<s.length()){
//            char c = s.charAt(i);
//            boolean mask = false;
//            while (j<t.length()){
//                if(t.charAt(j) == c){
//                    mask = true;
//                    j++;
//                    break;
//                }
//                j++;
//            }
//            if(!mask){
//                return false;
//            }
//            i++;
//        }
//        return true;
        /*
        use indexOf
         */
        int index = -1;
        for (char c : s.toCharArray()){
            index = t.indexOf(c,index+1);
            if(index == -1){
                return false;
            }
        }
        return true;
    }

    /**
     * leetcode 319 bulb switcher
     * @param n
     * @return
     */
    public int bulbSwitch(int n) {
        return (int) Math.sqrt(n);
    }

    /**
     * leetcode 386 lexicographical numbers
     * @param n
     * @return
     */
    public List<Integer> lexicalOrder(int n) {
        List<Integer> list = new ArrayList<>();
        int cur = 1;
        for (int i = 0;i<n;i++){
            list.add(cur);
            if(cur*10 <= n)
                cur = cur * 10;
            else {
                if(cur >= n) cur /= 10;
                cur += 1;
                while (cur % 10 == 0) cur /= 10;
            }
        }
        return list;
    }

    /**
     * 399 evaluate division
     * medium
     * floyd algorithm
     * @param equations A / B
     * @param values k
     * @param queries C / D
     * @return value = C / D
     */
    public double[] calcEquation(String[][] equations, double[] values, String[][] queries) {
        Map<String,Integer> map = new HashMap<>();
        for (String[] s : equations){
            if(!map.containsKey(s[0])) map.put(s[0],map.size());
            if(!map.containsKey(s[1])) map.put(s[1],map.size());
        }
        double[][] matrix = new double[map.size()][map.size()];
        for (double[] i : matrix)
            Arrays.fill(i,Double.MAX_VALUE);
        for (int i = 0;i<map.size();i++)
            matrix[i][i] = 1;
        for (int i = 0;i<equations.length;i++){
            matrix[map.get(equations[i][0])][map.get(equations[i][1])] = values[i];
            matrix[map.get(equations[i][1])][map.get(equations[i][0])] = 1 / values[i];
        }

        // floyd algorithm
        for (int k = 0;k<map.size();k++){
            for (int i = 0;i<map.size();i++){
                for (int j = 0;j<map.size();j++){
                    if(matrix[i][k] < Double.MAX_VALUE && matrix[k][j] < Double.MAX_VALUE)
                        matrix[i][j] = Double.min(matrix[i][j],matrix[i][k]*matrix[k][j]);
                }
            }
        }

        double[] result = new double[queries.length];
        for (int i = 0;i<queries.length;i++){
            if(map.containsKey(queries[i][0]) && map.containsKey(queries[i][1])){
                double tmp = matrix[map.get(queries[i][0])][map.get(queries[i][1])];
                result[i] = tmp == Double.MAX_VALUE ? -1:tmp;
            }else
                result[i] = -1;
        }
        return result;
    }

    /**
     * 390 elimination game
     * medium
     * @param n integer from 1 to n
     * @return remain number
     */
    public int lastRemaining(int n) {
        /**
         * recursion
         */
//        return lastRemainingHelper(n,true);
        /**
         * two
         */
//        return n == 1 ? 1 : 2 * (1 + n / 2 - lastRemaining(n / 2));
        /**
         * three
         */
//        int base = 1, res = 1;
//        while (base * 2 <= n){
//            res += base;
//            base *= 2;
//            if(base * 2 > n) break;
//            if((n / base) % 2 == 1) res += base;
//            base *= 2;
//        }
//        return res;
        /**
         * four
         *
         * when we head be updated
         * 1.if we move from left
         *   head += step ; step *= 2
         * 2.if we remove from right and total remaining number % 2 == 1
         *  number % 2 == 1 same with move from left
         *  number % 2 != 1 head not change, step *= 2
         */
        boolean left = true; int remaining = n; int step = 1; int head = 1;
        while (remaining > 1){
            if(left || remaining %2 == 1)
                head += step;
            remaining %= 2;
            step *= 2;
            left = !left;
        }
        return head;
    }

    private int lastRemainingHelper(int n,boolean leftToRight){
        if(n == 1) return 1;
        if(leftToRight){
            return 2 * lastRemainingHelper(n / 2,false);
        }else {
            return 2 * lastRemainingHelper(n / 2,true) - 1 + n % 2;
        }
    }

    /**
     * 394 decode string
     * refine string.match ==> Character.isDigit() 判断是否为数字
     * @param s encode string
     * @return decode string
     */
    public String decodeString(String s) {
        StringBuilder sb = new StringBuilder();
        Stack<String> stack = new Stack<>();
        int i = 0;
        while (i<s.length()){
            String s1 = s.substring(i,i+1);
            if(s1.equals("["))
                stack.push(s1);
            else if(s1.equals("]")){
                StringBuilder stringBuilder = new StringBuilder();
                while (!stack.peek().equals("[")){
                    stringBuilder.append(stack.pop());
                }
                stack.pop();
                while (!stack.empty() && !stack.peek().equals("[")){
                    String s2 = stack.pop();
                    if(s2.matches("^\\d+$")){
                        int j = Integer.parseInt(s2);
                        StringBuilder temp = new StringBuilder();
                        for (;j>0;j--){
                            temp.append(stringBuilder);
                        }
                        stringBuilder = temp;
                    }else
                        stringBuilder = new StringBuilder(s2).append(stringBuilder);
                }
                if(!stack.empty())
                    stack.push(stringBuilder.toString());
                else
                    sb.append(stringBuilder);
            }else if(s1.matches("^\\d+$")){
                if (!stack.empty() && stack.peek().matches("^\\d+$")){
                    String temp = stack.pop()+s1;
                    stack.push(temp);
                }else
                    stack.push(s1);
            }else {
                if(stack.empty()){
                    stack.push(s1);
                }else {
                    String temp = stack.peek();
                    if(!temp.equals("[") && !temp.equals("]") && !temp.matches("^\\d+$")){
                        stack.pop();
                        stack.push(temp+s1);
                    }else
                        stack.push(s1);
                }
            }
            i++;
        }
        while (!stack.empty()){
            sb.append(stack.pop());
        }
        return sb.toString();
    }

    /**
     * 382 linked list random node
     */
    class Solution1 {

        private Map<Integer,ListNode> map = new HashMap<>();
        private int sum = 0;
        private Random random = new Random();

        /** @param head The linked list's head.
        Note that the head is guaranteed to be not null, so it contains at least one node. */
        public Solution1(ListNode head) {
            while (head != null){
                map.put(sum++,head);
                head = head.next;
            }
        }

        /** Returns a random node's value. */
        public int getRandom() {
            int i = random.nextInt(sum);
            return map.get(i).val;
        }
    }

    /**
     * 334 increasing triplet sub_sequence
     * @param nums array
     * @return boolean
     */
    public boolean increasingTriplet(int[] nums) {
        if (nums.length < 3) return false;
        int min = Integer.MAX_VALUE;
        int secondMin = min;
        for (int i : nums){
            if(i <= min){
                min = i;
            }else if(i<secondMin){
                secondMin = i;
            }else if(i>secondMin){
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args){
        Solution solution = new Solution();
        solution.increasingTriplet(new int[]{9,10,1,11});
    }
}

/**
 * 384 shuffle an array
 */
class Solution2 {

    private int[] array;
    private int[] original;

    Random rand = new Random();

    private int randRange(int min, int max) {
        return rand.nextInt(max - min) + min;
    }

    private void swapAt(int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public Solution2(int[] nums) {
        array = nums;
        original = nums.clone();
    }

    public int[] reset() {
        array = original;
        original = original.clone();
        return original;
    }

    public int[] shuffle() {
        for (int i = 0; i < array.length; i++) {
            swapAt(i, randRange(i, array.length));
        }
        return array;
    }
}

/**
 * 398 random pick index
 */
class Solution3 {

    private Random rand;
    private int[] nums;

    public Solution3(int[] nums) {
        this.nums = nums;
        rand = new Random();
    }

    public int pick(int target) {
        int count = 0;
        int res = -1;

        for(int i = 0; i < nums.length; i++) {
            if(nums[i] != target) {
                continue;
            } else {
                if(rand.nextInt(++count) == 0) {
                    res = i;
                }
            }
        }

        return res;
    }
}

/**
 * 341 flatten nested list iterator
 */
class NestedIterator implements Iterator<Integer> {

    List<Integer> list = new ArrayList<>();
    int mask = 0;
    public NestedIterator(List<NestedInteger> nestedList) {
        helper(nestedList);
    }

    public void helper(List<NestedInteger> nestedList){
        for (NestedInteger nestedInteger : nestedList){
            if(nestedInteger.isInteger()){
                list.add(nestedInteger.getInteger());
            }else {
                helper(nestedInteger.getList());
            }
        }
    }

    @Override
    public Integer next() {
        return list.get(mask++);
    }

    @Override
    public boolean hasNext() {
        return mask < list.size();
    }
}

/**
 * 380 insert delete getRandom O(1)
 */
class RandomizedSet {

    private List<Integer> list = new ArrayList<>();
    private Map<Integer,Integer> map = new HashMap<>();

    /** Initialize your data structure here. */
    public RandomizedSet() {

    }

    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if(map.containsKey(val))
            return false;
        list.add(val);
        map.put(val,list.size()-1);
        return true;
    }

    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if(!map.containsKey(val))
            return false;
        int index = map.get(val);
        list.set(index,list.get(list.size()-1));
        map.put(list.get(list.size()-1),index);
        map.remove(val);
        list.remove(list.size()-1);
        return true;
    }

    /** Get a random element from the set. */
    public int getRandom() {
        Random random = new Random();
        return list.get(random.nextInt(list.size()));
    }
}