package leetcode600_700;

import org.omg.PortableInterceptor.INACTIVE;

import java.util.*;

public class Solution {

    /**
     * leetcode 645 set mismatch
     * @param nums
     * @return
     */
    public int[] findErrorNums(int[] nums) {
        int[] arr = new int[2];
        for(int i : nums){
            if(nums[Math.abs(i)-1] <0)
                arr[0] = Math.abs(i);
            else
                nums[Math.abs(i)-1] *= -1;
        }
        for(int i = 0;i<nums.length;i++){
            if(nums[i]>0)
                arr[1] = Math.abs(i);
        }
        return arr;
    }

    /**
     * leetcode 643 maximum average subarray I
     * @param nums
     * @param k
     * @return
     */
    public double findMaxAverage(int[] nums, int k) {
        int max = 0;
        int temp = 0;
        for(int i = 0;i<k;i++){
            max += nums[i];
            temp = max;
        }
        for(int i = 0; i+k<nums.length;i++){
            temp = temp-nums[i]+nums[i+k];
            max = Math.max(temp,max);
        }
        return (double) max/k;
    }

    /**
     * leetcode 633 sum of square numbers
     * @param c
     * @return
     */
    public boolean judgeSquareSum(int c) {
        int a = 0;
        int b = (int)Math.sqrt(c);
        while ( a<=b ){
            if(a*a+b*b == c)
                return true;
            else if(a*a+b*b < c)
                a++;
            else
                b--;
        }
        return false;
    }

    /**
     * leetcode 680 valid palindrome II
     * @param s
     * @return
     */
    public boolean validPalindrome(String s) {
        for(int i=0,j=s.length()-1;i<j;i++,j--){
            if(s.charAt(i) != s.charAt(j)){
                return isValid(s,i+1,j) || isValid(s,i,j-1);
            }
        }
        return true;
    }

    public boolean isValid(String s,int i, int j){
        while (i<j){
            if(s.charAt(i++) != s.charAt(j--)){
                return false;
            }
        }
        return true;
    }

    /**
     * leetcode 686 repeated string match
     * @param A
     * @param B
     * @return
     */
    public int repeatedStringMatch(String A, String B) {
        int times = B.length()/A.length()+2;
        StringBuilder stringBuilder = new StringBuilder(A);
        for(int i = 1;i<=times;i++){
            if(stringBuilder.indexOf(B) != -1) return i;
            else stringBuilder.append(A);
        }
        return -1;
    }

    /**
     * leetcode 605 can place flowers
     * @param flowerbed
     * @param n
     * @return
     */
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        int mask = 0;
        int l = flowerbed.length;
        for (int i =0;i<l;i++){
            if(flowerbed[i] == 0){
                if(i==0 && (l==1 || flowerbed[1] ==0) ){
                    flowerbed[0]=1;
                    n--;
                }else if(i == l-1&&flowerbed[i-2] == 0){
                    flowerbed[i]=1;
                    n--;
                }else if(l>2&&i!=0&&i!=l-1&&flowerbed[i-1]==0&&flowerbed[i+1]==0){
                    flowerbed[i]=1;
                    n--;
                }
            }
        }
        return n == 0;
    }

    /**
     * leetcode 665 non-decreasing array
     * @param nums
     * @return
     */
    public boolean checkPossibility(int[] nums) {
        int mask = 0;
        for (int i = 0;i < nums.length-1;i++){
            if(nums[i]>nums[i+1]){
                mask++;
                if(mask > 1){
                    return false;
                }
                if(i>0 && nums[i-1] == nums[i]){
                    nums[i+1] = nums[i];
                }
                if(i>0 && nums[i-1]> nums[i+1]){
                    nums[i+1] = nums[i];
                }
            }
        }
        return mask<2;
    }

    /**
     * leetcode 647 palindromic substring
     * 从回文的中间开始向两边遍历，判断是否为回文
     * medium
     * @param s
     * @return
     */
    int palindromicSum = 0;
    public int countSubstrings(String s) {
        char[] chars = s.toCharArray();
        for (int i = 0;i<chars.length;i++){
            countSubstringsHelper(chars,i,i); // 奇数回文
            countSubstringsHelper(chars,i,i+1); // 偶数回文
        }
        return palindromicSum;
    }

    public void countSubstringsHelper(char[] chars,int left,int right){
        while (left>=0 && right<chars.length && chars[left] == chars[right]){
            palindromicSum++;
            left--;
            right++;
        }
    }

    /**
     * leetcode 609 find duplicate file in system
     * @param paths
     * @return
     */
    public List<List<String>> findDuplicate(String[] paths) {
        Map<String,ArrayList<String>> map=new HashMap<>();
        for(String path:paths){
            String []file=path.split(" ");
            String root = file[0];
            for(int i=1;i<file.length;i++){
                String []content=file[i].split("[(]");
                ArrayList<String> nowContent = map.getOrDefault(content[1], new ArrayList<>());
                nowContent.add(root + "/" + content[0]);
                map.put(content[1], nowContent);
            }
        }
        List<List<String>> lls= new ArrayList<>();
        for (ArrayList<String> value : map.values()) {
            if(value.size()>1)
                lls.add(value);
        }
        return lls;
    }

    /**
     * leetcode 628
     * @param nums
     * @return
     */
    public int maximumProduct(int[] nums) {
        Arrays.sort(nums);
        int length = nums.length;
        return Math.max(nums[0]*nums[1]*nums[length-1],nums[length-1]*nums[length-2]*nums[length-3]);
    }

    /**
     * leetcode 682
     * @param ops
     * @return
     */
    public static int calPoints(String[] ops) {
        int sum = 0;
        List<Integer> list = new LinkedList<>();
        for(int j = 0;j<ops.length;j++){
            if(ops[j].equals("+")){
                if(list.isEmpty()){
                    break;
                }else if(list.size() == 1){
                    sum = sum+((LinkedList<Integer>) list).getLast();
                    list.add(((LinkedList<Integer>) list).getLast());
                }else {
                    sum = sum+((LinkedList<Integer>) list).getLast()+list.get(list.size()-2);
                    list.add(((LinkedList<Integer>) list).getLast()+list.get(list.size()-2));
                }
            }else if(ops[j].equals("D")){
                if(list.isEmpty()){
                    break;
                }
                sum = sum+((LinkedList<Integer>) list).getLast()*2;
                list.add(((LinkedList<Integer>) list).getLast()*2);
            }else if(ops[j].equals("C")){
                sum = sum - list.get(list.size()-1);
                list.remove(list.size()-1);
            }else {
                list.add(Integer.parseInt(ops[j]));
                sum = sum+Integer.parseInt(ops[j]);
            }
        }
        return sum;
    }

    /**
     * leetcode 696
     * @param s
     * @return
     */
    public static int countBinarySubstrings(String s) {
        int pre = 0;int cur = 0;int res = 0;
        for(int i = 1;i<s.length();i++){
            if(s.charAt(i) == s.charAt(i-1)){
                cur++;
            }else {
                pre = cur;
                cur = 1;
            }
            if(pre>=cur){
                res++;
            }
        }
        return res;
    }

    /**
     * leetcode 667 beautiful arrangement II
     * @param n
     * @param k
     * @return
     */
    public int[] constructArray(int n, int k) {
        int[] res = new int[n];
        int max = n, min = 1;
        int i;
        for (i = 0; i < k; i ++) {
            if (i % 2 != 0) res[i] = max --;
            else res[i] = min ++;
        }
        if (i % 2 == 0) {
            for (int j = k; j < n; j ++)
                res[j] = max --;
        } else {
            for (int j = k; j < n; j ++)
                res[j] = min ++;
        }
        return res;
    }

    /**
     * leetcode 672 bulb switcher II
     * medium ???
     * @param n
     * @param m
     * @return
     */
    public int flipLights(int n, int m) {
        if(m == 0) return 1;
        if(n <= 0 || m < 0) return 0;
        if(n == 1) return 2;
        else if(n == 2) return (m == 1)?3:4;
        else return (m == 1)?4:((m == 2)?7:8);
    }

    /**
     * leetcode 638 shopping offers
     * medium
     * @param price
     * @param special
     * @param needs
     * @return
     */
    public int shoppingOffers(List < Integer > price, List < List < Integer >> special, List < Integer > needs) {
        return shopping(price, special, needs);
    }
    public int shopping(List < Integer > price, List < List < Integer >> special, List < Integer > needs) {
        int j = 0, res = dot(needs, price);
        for (List < Integer > s: special) {
            ArrayList < Integer > clone = new ArrayList < > (needs);
            for (j = 0; j < needs.size(); j++) {
                int diff = clone.get(j) - s.get(j);
                if (diff < 0)
                    break;
                clone.set(j, diff);
            }
            if (j == needs.size())
                res = Math.min(res, s.get(j) + shopping(price, special, clone));
        }
        return res;
    }
    public int dot(List < Integer > a, List < Integer > b) {
        int sum = 0;
        for (int i = 0; i < a.size(); i++) {
            sum += a.get(i) * b.get(i);
        }
        return sum;
    }

    /**
     * leetcode 650 2 keys keyboard
     * @param n
     * @return
     */
    public int minSteps(int n) {
        int ans = 0, d = 2;
        while (n > 1) {
            while (n % d == 0) {
                ans += d;
                n /= d;
            }
            d++;
        }
        return ans;
    }

    /**
     * leetcode 684 redundant connection
     * @param edges
     * @return
     */
    public int[] findRedundantConnection(int[][] edges) {
        int[] temp = new int[2000];
        for (int i = 0;i<2000;i++){
            temp[i] = -1;
        }
        for (int[] arr : edges){
            int x = findRedundantConnectionHelper(temp,arr[0]),y = findRedundantConnectionHelper(temp,arr[1]);
            if(x == y) return arr;
            temp[x] = y;
        }
        return new int[2];
    }

    public int findRedundantConnectionHelper(int[] temp,int i){
        while (temp[i] != -1){
            i = temp[i];
        }
        return i;
    }

    /**
     * 636 exclusive time of function
     * @param n
     * @param logs
     * @return
     */
    public int[] exclusiveTime(int n, List<String> logs) {
        Stack<String> stack = new Stack<>();
        int[] res = new int[n];
        for (String s : logs){
            String[] strings = s.split(":");
            if(strings[1].equals("start")){
                stack.push(s);
            }else {
                int mid = 0;
                while (stack.peek().split(":").length == 1){
                    mid += Integer.parseInt(stack.pop());
                }
                String[] s1 = stack.pop().split(":");
                int k = Integer.parseInt(strings[2]) - mid - Integer.parseInt(s1[2]) + 1;
                res[Integer.parseInt(strings[0])] += k;
                if(!stack.isEmpty()){
                    stack.push(String.valueOf(k+mid));
                }
            }
        }
        return res;
    }

    /**
     * 621 task scheduler
     * consider the frequent characters
     * @param tasks task array
     * @param n interval n that means between two same task
     * @return minimum time
     */
    public int leastInterval(char[] tasks, int n) {

        // recursion
//        if(n == 0) return tasks.length;
//        int[] position = new int[26];
//        Arrays.fill(position,-n-1);
//        int[] chars = new int[26];
//        for (char c : tasks){
//            chars[c-'A']++;
//        }
//        return leastIntervalHelper(chars,position,tasks.length,n,0);

        // first consider the frequent characters,we can determine their relative position first and use them as a frame to insert the remaining
        // less frequent characters.
//        int[] c = new int[26];
//        for (char t : tasks){
//            c[t-'A']++;
//        }
//        Arrays.sort(c);
//        int i = 25;
//        while (i >=0 && c[i] == c[25]) i--;
//        return Math.max(tasks.length,(c[25]-1)*(n+1) + 25 - i);

        int[] counter = new int[26];
        int max = 0;
        int maxCount = 0;
        for(char task : tasks) {
            counter[task - 'A']++;
            if(max == counter[task - 'A']) {
                maxCount++;
            }
            else if(max < counter[task - 'A']) {
                max = counter[task - 'A'];
                maxCount = 1;
            }
        }
        int partCount = max - 1;
        int partLength = n - (maxCount - 1);
        int emptySlots = partCount * partLength;
        int availableTasks = tasks.length - max * maxCount;
        int idles = Math.max(0, emptySlots - availableTasks);

        return tasks.length + idles;
    }
    private int leastIntervalHelper(int[] chars,int[] position,int count,int n,int length){
        if(count == 0)  return length;
        int min = Integer.MAX_VALUE;
        boolean idle = true;
        for (int i = 0;i<chars.length;i++){
            if(chars[i] != 0 && position[i] + n < length){
                idle = false;
                chars[i]--;
                int temp = position[i];
                position[i] = length;
                min = Integer.min(min,leastIntervalHelper(chars,position,count-1,n,length+1));
                chars[i]++;
                position[i] = temp;
            }
        }
        if(idle){
            min = Integer.min(min,leastIntervalHelper(chars,position,count,n,length+1));
        }
        return min;
    }

    /**
     * 611 valid triangle number
     * @param nums array
     * @return the number of triangle number
     */
    public int triangleNumber(int[] nums) {
//        Arrays.sort(nums);
//        int res = 0;
//        for (int i = 0;i<nums.length;i++){
//            for (int j = i+1;j<nums.length;j++){
//                int sum = nums[i] + nums[j],left = j+1,right = nums.length;
//                while (left < right){
//                    int mid = (left+right)/2;
//                    if(nums[mid] < sum) left = mid + 1;
//                    else right = mid;
//                }
//                res += right - 1 - j;
//            }
//        }
//        return res;

        int res = 0;
        Arrays.sort(nums);
        for (int i = nums.length-1;i>=0;i--){
            int left = 0,right = i-1;
            while (left < right){
                if(nums[left] + nums[right] > nums[i]){
                    res += right - left;
                    right--;
                }else
                    left++;
            }
        }
        return res;
    }

    /**
     * 692 top k frequent words
     * @param words a non-empty list of words
     * @param k the k frequent
     * @return the k frequent words
     */
    public List<String> topKFrequent(String[] words, int k) {

        // O(N logN) O(N)
//        Map<String,Integer> map = new HashMap<>();
//        for (String s : words){
//            map.put(s,map.getOrDefault(s,0)+1);
//        }
//        List<String> temp = new ArrayList<>(map.keySet());
//        Collections.sort(temp,(w1,w2) -> map.get(w1).equals(map.get(w2)) ? w1.compareTo(w2) : map.get(w2) - map.get(w1) );
//        return temp.subList(0,k);

        // O(N logK) O(N) use heap
        Map<String,Integer> map = new HashMap<>();
        for (String s : words)
            map.put(s,map.getOrDefault(s,0)+1);
        PriorityQueue<String> heap = new PriorityQueue<>((w1,w2) -> map.get(w1).equals(map.get(w2)) ? w2.compareTo(w1) : map.get(w2) - map.get(w1));
        for (String s : map.keySet()){
            heap.offer(s);
            if(heap.size() > k) heap.poll();
        }
        List<String> list = new ArrayList<>();
        while (!heap.isEmpty())
            list.add(heap.poll());
        Collections.reverse(list);
        return list;
    }

    /**
     * 688 knight probability in chessboard
     * @param N matrix N*N
     * @param K k moves
     * @param r at the r-th row
     * @param c at the c-th column
     * @return the probability that the knight remains on the chessboard
     */
    public double knightProbability(int N, int K, int sr, int sc) {
        double[][] dp = new double[N][N];
        int[] dr = new int[]{2, 2, 1, 1, -1, -1, -2, -2};
        int[] dc = new int[]{1, -1, 2, -2, 2, -2, 1, -1};

        dp[sr][sc] = 1;
        for (; K > 0; K--) {
            double[][] dp2 = new double[N][N];
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    for (int k = 0; k < 8; k++) {
                        int cr = r + dr[k];
                        int cc = c + dc[k];
                        if (0 <= cr && cr < N && 0 <= cc && cc < N) {
                            dp2[cr][cc] += dp[r][c] / 8.0;
                        }
                    }
                }
            }
            dp = dp2;
        }
        double ans = 0.0;
        for (double[] row: dp) {
            for (double x: row) ans += x;
        }
        return ans;
    }

    public static void main(String[] args){
        Solution solution = new Solution();
        solution.knightProbability(3,2,0,0);
    }
}

/**
 * 677 map sum pairs
 * medium
 */
class MapSum {
    Map<String, Integer> map;
    Map<String, Integer> score;
    public MapSum() {
        map = new HashMap();
        score = new HashMap();
    }
    public void insert(String key, int val) {
        int delta = val - map.getOrDefault(key, 0);
        map.put(key, val);
        String prefix = "";
        for (char c: key.toCharArray()) {
            prefix += c;
            score.put(prefix, score.getOrDefault(prefix, 0) + delta);
        }
    }
    public int sum(String prefix) {
        return score.getOrDefault(prefix, 0);
    }
}

/**
 * 676 implement magic dictionary
 */
class MagicDictionary {

    Set<String> set;

    /** Initialize your data structure here. */
    public MagicDictionary() {
        set = new HashSet<>();
    }

    /** Build a dictionary through a list of words */
    public void buildDict(String[] dict) {
        if(dict == null || dict.length == 0)
            return;
        for (String s : dict){
            set.add(s);
        }
    }

    /** Returns if there is any word in the trie that equals to the given word after modifying exactly one character */
    public boolean search(String word) {
        for (String s : set){
            if(s.length() == word.length()){
                int count = 0;
                for (int i = 0;i<s.length();i++){
                    if(s.charAt(i) != word.charAt(i)){
                        count++;
                        if(count > 1){
                            break;
                        }
                    }
                }
                if(count == 1){
                    return true;
                }
            }
        }
        return false;
    }
}