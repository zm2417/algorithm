package leetcode700_800;

import leetcode0_100.ListNode;
import org.junit.Test;
import org.omg.PortableInterceptor.INACTIVE;

import java.util.*;

public class Solution {
    /**
     * leetcode 702 longest world in dictionary
     * @param words
     * @return
     */
    public String longestWord(String[] words) {
        Arrays.sort(words);
        Set<String> set = new HashSet<>();
        String res = "";
        for(String s : words){
            if(s.length() == 1 || set.contains(s.substring(0,s.length()-1))){
                res = s.length()>res.length()?s:res;
                set.add(s);
            }
        }
        return res;
    }

    /**
     * leetcode 747 largest number at least twice of others
     * @param nums
     * @return
     */
    public int dominantIndex(int[] nums) {
        int index = -1;
        int max = -1;
        int max2 = -2;
        for(int i = 0; i<nums.length; i++){
            if(nums[i]>max){
                max2 = max;
                max = nums[i];
                index = i;
            }else if(nums[i]>max2 && nums[i]<max){
                max2 = nums[i];
            }
        }
        if(max>=max2*2){
            return index;
        }
        return -1;
    }

    /**
     * 724 find pivot index
     * @param nums
     * @return
     */
    public int pivotIndex(int[] nums) {
        if(nums.length ==0 || nums.length == 2){
            return -1;
        }else if(nums.length ==1){
            return 0;
        }
        int sum = 0;
        for(int i : nums){
            sum += i;
        }
        int left = 0;
        int right = 0;
        for(int i =0;i<nums.length;i++){
            left = i==0?left:left+nums[i-1];
            right = sum-left-nums[i];
            if(left == right){
                return i;
            }
        }
        return -1;
    }

    /**
     * leetcode 704 binary search
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length-1;
        while (left<=right){
            int middle = (left+right)/2;
            if(nums[middle]>target){
                right = middle-1;
            }else if(nums[middle]<target){
                left = middle+1;
            }else
                return middle;
        }
        return -1;
    }

    /**
     * leetcode 797 all paths from source to target
     * dfs
     * @param graph
     * @return
     */
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List<List<Integer>> lists = new LinkedList<>();
        List<Integer> list = new LinkedList<>();
        allPathSourceTargetHelper(lists,list,0,graph.length-1,graph);
        return lists;
    }

    public void allPathSourceTargetHelper(List<List<Integer>> lists, List<Integer> list,int start,int end,int[][] graph){
        if(start == end){
            list.add(start);
            List<Integer> list1 = new LinkedList<>(list);
            lists.add(list1);
            list.remove(list.size()-1);
            return;
        }
        for(int i : graph[start]){
            list.add(start);
            allPathSourceTargetHelper(lists,list,i,end,graph);
            list.remove(list.size()-1);
        }
    }

    /**
     * leetcode 763 partition labels
     * 贪心算法 中等偏难
     * @param S
     * @return
     */
    public List<Integer> partitionLabels(String S) {
        List<Integer> list = new ArrayList<>();
        if(S.length() == 0){
            return list;
        }
        Map<Character,Integer> map = new HashMap<>();
        char[] chars = S.toCharArray();
        for (int i = 0;i<chars.length;i++){
            map.put(chars[i],i);
        }
        list.add(map.get(chars[0])+1);
        int count = map.get(chars[0])+1;
        char temp = chars[0];
        int pre = 0;
        for (int i = 1;i<chars.length;i++){
            if(i<count){
                if(map.get(chars[i]) > map.get(temp)){
                    list.set(list.size()-1,map.get(chars[i])+1-pre);
                    count = map.get(chars[i])+1;
                    temp = chars[i];
                }
            }else {
                pre = count;
                list.add(map.get(chars[i])+1-pre);
                count = map.get(chars[i])+1;
                temp = chars[i];
            }
        }
        return list;
    }

    /**
     * leetcode 791 custom sort string
     * @param S
     * @param T
     * @return
     */
    public String customSortString(String S, String T) {
        if (S.isEmpty() || T.isEmpty())
            return T;
        char[] chars = S.toCharArray();
        Map<Character,Integer> map = new HashMap<>();
        int[] temp = new int[26];
        char[] chars1 = T.toCharArray();
        for(char c : chars1){
            if(map.containsKey(c)){
                map.put(c,map.get(c)+1);
                temp[c-'a'] = map.get(c);
                continue;
            }
            map.put(c,1);
            temp[c-'a'] = map.get(c);
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0;i<chars.length;i++){
            if(map.containsKey(chars[i])){
                temp[chars[i]-'a'] = 0;
                for(int j = 0;j<map.get(chars[i]);j++){
                    sb.append(chars[i]);
                }
            }
        }
        for(int i=0;i<26;i++){
            if(temp[i] != 0){
                for(int j = 0;j<temp[i];j++){
                    sb.append((char)('a'+i));
                }
            }
        }
        return sb.toString();
    }

    /**
     * leetcode 739 daily temperatures
     * @param temperatures
     * @return
     */
    public int[] dailyTemperatures(int[] temperatures) {
        /**
         * one
         */
//        int[] res = new int[temperatures.length];
//        for (int i = 0;i<temperatures.length;i++){
//            for (int j = i+1;j<temperatures.length;j++){
//                if(temperatures[j]>temperatures[i]){
//                    res[i] = j-i;
//                    break;
//                }
//            }
//        }
//        return res;
        int[] res = new int[temperatures.length];
        if(temperatures.length == 0){
            return res;
        }
        Stack<int[]> stack = new Stack<>();
        for (int i = 0;i<temperatures.length;i++){
            while (!stack.isEmpty()){
                if(stack.peek()[0] < temperatures[i]){
                    res[stack.peek()[1]] = i-stack.pop()[1];
                }else {
                    break;
                }
            }
            stack.push(new int[]{temperatures[i],i});
        }
        return res;
    }

    /**
     * leetcode 748 shortest completing world
     * @param licensePlate
     * @param words
     * @return
     */
    public String shortestCompletingWord(String licensePlate, String[] words) {
        Map<Character,Integer> map = new HashMap<>();
        for (char c : licensePlate.toCharArray()){
            if((c = Character.toLowerCase(c)) > 'z' || c < 'a') continue;
            if(map.containsKey(c)){
                map.put(c,map.get(c)+1);
            }else {
                map.put(c,1);
            }
        }
        int len = Integer.MAX_VALUE;
        String temp = "";
        for (String s : words){
            if(len <= s.length()){
                continue;
            }
            Map<Character,Integer> map1 = new HashMap<>();
            for (char i : s.toCharArray()){
                if(map.containsKey(i)){
                    if(map1.containsKey(i)){
                        map1.put(i,map1.get(i)+1);
                    }else {
                        map1.put(i,1);
                    }
                }
            }
            boolean t = true;
            for (Map.Entry entry : map.entrySet()){
                if(map1.containsKey(entry.getKey()) && (int)entry.getValue() <= map1.get(entry.getKey())){
                    continue;
                }else {
                    t = false;
                    break;
                }
            }
            if(t){
                len = s.length();
                temp = s;
            }
        }
        return temp;
    }

    /**
     * leetcode 789 escape the ghosts
     * @param ghosts
     * @param target
     * @return
     */
    public boolean escapeGhosts(int[][] ghosts, int[] target) {
        int min = Math.abs(target[0])+Math.abs(target[1]);
        for (int[] arr : ghosts){
            int t = Math.abs(arr[0]-target[0])+Math.abs(arr[1]-target[1]);
            if(t <= min){
                return false;
            }
        }
        return true;
    }

    /**
     * leetcode 733
     * @param image
     * @param sr
     * @param sc
     * @param newColor
     * @return
     */
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        if(image == null || image.length == 0){
            return image;
        }
        int row = image.length;
        int cloum = image[0].length;
        if(sc<0 || sc>=cloum || sr<0 || sr>=row){
            return image;
        }
        image[sr][sc] = newColor;
        if(image[sr][sc] == image[sr+1][sc]){
            floodFill(image,sr+1,sc,newColor);
        }
        if(image[sr][sc] == image[sr-1][sc]){
            floodFill(image,sr-1,sc,newColor);
        }
        if(image[sr][sc] == image[sr][sc+1]){
            floodFill(image,sr,sc+1,newColor);
        }
        if(image[sr][sc] == image[sr][sc-1]){
            floodFill(image,sr,sc-1,newColor);
        }
        return image;
    }

    public boolean isAnagram(String s, String t) {
        if(s.length() != t.length()) return false;
        Map<Character,Integer> maps = new HashMap<>();
        Map<Character,Integer> mapt = new HashMap<>();
        char[] chars = s.toCharArray();
        char[] chart = t.toCharArray();
        for(char c : chars){
            if(maps.containsKey(c)){
                maps.put(c,maps.get(c)+1);
            }else {
                maps.put(c,1);
            }
        }
        for(char c : chart){
            if(maps.containsKey(c)){
                maps.put(c,maps.get(c)-1);
                if(maps.get(c) <= 0){
                    return false;
                }
            }else {
                return false;
            }
        }
        return true;

    }

    /**
     * leetcode 784
     * @param S
     * @return
     */
    public List<String> letterCasePermutation(String S) {
        if(S == "" || S == null){
            return null;
        }
        List<String> list = new ArrayList<>();
        char[] c = S.toCharArray();
        String s = "";
        letterCasePermutationHelper(list,c,0,s);
        return list;
    }

    public static List<String> letterCasePermutationHelper(List<String> list,char[] c,int op,String s){
        if(op == c.length){
            list.add(s);
            return list;
        }

        if(Character.isDigit(c[op])){
            letterCasePermutationHelper(list,c,op+1,s+c[op]);
        }else{
            letterCasePermutationHelper(list,c,op+1,s+Character.toUpperCase(c[op]));
            letterCasePermutationHelper(list,c,op+1,s+Character.toLowerCase(c[op]));
        }
        return list;
    }

    /**
     * leetcode 781 rabbits in forest
     * @param answers
     * @return
     */
    public int numRabbits(int[] answers) {
        int res = 0;
        int len = answers.length;
        if (len == 0)
            return 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int answer : answers) {
            map.put(answer, map.getOrDefault(answer, 0) + 1);
        }
        for (Integer n : map.keySet()) {
            int group = map.get(n) / (n + 1);
            if (map.get(n) % (n + 1) == 0) {
                res += group * (n + 1);
            } else {
                res += (group + 1) * (n + 1);
            }
        }
        return res;
    }

    /**
     * leetcode 769 max chunks to make sorted
     * medium
     * @param arr array that waits to doing
     * @return max chunks
     */
    public int maxChunksToSorted(int[] arr) {
        /**
         * one
         */
//        int sum = 0;
//        for (int i = 0;i<arr.length;i++){
//            if(arr[i] != i){
//                int right = arr[i];
//                while (i != right){
//                    i++;
//                    right = Integer.max(right,arr[i]);
//                }
//                sum++;
//            }else {
//                sum++;
//            }
//        }
//        return sum;
        /**
         * two
         */
        int res = 0;
        int ending = -1;
        for (int i = 0;i<arr.length;i++){
            ending = Math.max(ending,arr[i]);
            if(ending == i)
                res++;
        }
        return res;
    }

    /**
     * leetcode 756 pyramid transition matrix
     * @param bottom
     * @param allowed
     * @return
     */
    public boolean pyramidTransition(String bottom,List<String> allowed){
        Map<String, List<String>> mem = new HashMap<>();
        boolean[][] dp = new boolean[20][7];
        int n = bottom.length();

        for (String allow : allowed) {
            mem.computeIfAbsent(allow.substring(0, 2), k -> new ArrayList<>()).add(allow.substring(2));
        }

        for (int i = 0; i < n; ++i) {
            dp[i][bottom.charAt(i) - 'A'] = true;
        }

        for (int i = n - 1; i >= 1; --i) {
            boolean[][] ndp = new boolean[20][7];
            for (int j = 0; j < i; ++j) {
                for (int l = 0; l < 7; ++l) {
                    for (int r = 0; r < 7; ++r) {
                        if (dp[j][l] && dp[j + 1][r]) {
                            if (mem.containsKey((char)(l + 'A') + "" + (char)(r + 'A'))) {
                                for (String s : mem.get((char)(l + 'A') + "" + (char)(r + 'A'))) {
                                    ndp[j][s.charAt(0) - 'A'] = true;
                                }
                            }
                        }
                    }
                }
            }
            dp = ndp;
        }

        for (int i = 0; i < 7; ++i) {
            if (dp[0][i]) return true;
        }
        return false;
    }

    /**
     * leetcode 725 split linked list in parts
     * @param root
     * @param k
     * @return
     */
    public ListNode[] splitListToParts(ListNode root, int k) {
        ListNode[] listNodes = new ListNode[k];
        int len = 0;
        ListNode node = root;
        while (node != null){
            len++;
            node = node.next;
        }
        int small = len/k;
        int bigCount = len-small*k;
        int big = bigCount == 0?small:small+1;
        node = root;
        ListNode pre = root;
        int i = 0;
        int j = 0;
        while (node != null){
            i++;
            if(i == big){
                bigCount--;
                if(bigCount == 0){
                    big = big-1;
                }
                i=0;
                ListNode temp = pre;
                listNodes[j++] = temp;
                pre = node.next;
                ListNode t = node;
                node = pre;
                t.next = null;
            }else {
                node = node.next;
            }
        }
        return listNodes;
    }

    /**
     * 795 number of sub_arrays with bounded maximum
     * @param A array
     * @param L at least L
     * @param R at most R
     * @return the numbers
     */
    public int numSubarrayBoundedMax(int[] A, int L, int R) {
        int len = A.length;
        int res = 0,count = 0,left = 0;
        for (int i = 0;i<len;i++){
            if(A[i] <= R && A[i] >= L){
                res += (i-left+1);
                count = (i-left+1);
            }else if(A[i] < L){
                res += count;
            }else {
                count = 0;
                left = i+1;
            }
        }
        return res;
    }

    /**
     * 738 monotone increasing digits
     * @param N the number of N
     * @return the largest number
     */
    public int monotoneIncreasingDigits(int N) {
//        char[] chars = String.valueOf(N).toCharArray();
//        int[] temp = new int[chars.length];
//        for (int i = 0;i<chars.length;i++){
//            temp[i] = Integer.parseInt(String.valueOf(chars[i]));
//        }
//        for (int i = temp.length-1;i>0;i--){
//            if(temp[i] < temp[i-1]){
//                temp[i-1] -= 1;
//                int j = i;
//                while (j< temp.length){
//                    temp[j++] = 9;
//                }
//            }
//        }
//        int res = temp[0];
//        for (int i = 1;i<temp.length;i++){
//            res  = res*10+temp[i];
//        }
//        return res;

        // refine
        char[] chars = String.valueOf(N).toCharArray();
        int j = -1;
        for (int i = chars.length-1;i>0;i--){
            if(chars[i] >= chars[i-1]) continue;
            j = i;
            chars[i-1] -= 1;
        }
        for (int i = j;i<chars.length;i++){
            chars[i] = '9';
        }
        return Integer.parseInt(String.valueOf(chars));
    }

    /**
     * 752 open the lock
     * deadend可能看做一个used数组，表示使用过就不能在使用。
     * 就是一个BFS，对于每一位都求出他可能走出的下一步，把所有的可能列举出来存入queue（把deadend的存入set，检查是否存在如deadend，
     * 存在pass，不存在添加到queue和deadend）。对于queue的每一个，先poll，在求其的每一种可能，直到求到target，或者不能求出结果。
     * @param deadends dead ends
     * @param target target string
     * @return minimum total number of turns required to open the lock or -1 if it is impossible
     */
    public int openLock(String[] deadends, String target) {
        if(target.equals("0000")) return 0;
        int min = 0;
        Set<String> set = new HashSet<>();
        Set<String> used = new HashSet<>();
        for (String s : deadends)
            set.add(s);
        Queue<String> queue = new LinkedList<>();
        queue.offer("0000");
        while (!queue.isEmpty()){
            int size = queue.size();
            while (size-->0){
                String temp = queue.poll();
                if(set.contains(temp))
                    continue;
                for (int i = 0;i<4;i++){
                    String pre = temp.substring(0,i);
                    String last = temp.substring(i+1,4);
                    String s1 = pre + (temp.charAt(i)-'0'+1)%10 + last;
                    String s2 = pre + (temp.charAt(i)-'0'+9)%10 + last;
                    if(s1.equals(target) || s2.equals(target))
                        return min+1;
                    if(!set.contains(s1) && !used.contains(s1)){
                        queue.offer(s1);
                        used.add(s1);
                    }
                    if(!set.contains(s2) && !used.contains(s2)){
                        queue.offer(s2);
                        used.add(s2);
                    }
                }
            }
            min++;
        }
        return -1;
    }

    /**
     * 777 Swap Adjacent in LR String
     */
    public boolean canTransform(String start, String end) {
        int len = start.length(),i = 0,j = 0;
        while (i < len && j < len) {
            while (i < len && start.charAt(i) == 'X') i++;
            while (j < len && end.charAt(j) == 'X') j++;
            if (i == len && j == len)
                return true;
            else if (i == len || j == len)
                return false;
            if (start.charAt(i) != end.charAt(j)) return false;
            if ((start.charAt(i) == 'L' && i < j) || (start.charAt(i) == 'R' && j < i)) return false;
            i++;
            j++;
        }
        return true;
    }

    public static void main(String[] args){
        Solution solution = new Solution();
        ListNode listNode = new ListNode(0);ListNode listNode1 = new ListNode(2);ListNode listNode2 = new ListNode(3);
        ListNode listNode3 = new ListNode(4);
//        listNode.next = listNode1;listNode1.next = listNode2;listNode2.next = listNode3;
        solution.canTransform("XLXRRXXRXX", "LXXXXXXRRR");
    }
}