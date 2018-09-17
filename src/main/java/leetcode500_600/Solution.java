package leetcode500_600;

import java.util.*;

public class Solution {

    /**
     * leetcode 594 longest Harmonious Subsequence
     * @param nums
     * @return
     */
    public int findLHS(int[] nums) {
        int max = 0;
        Map<Integer,Integer> map = new HashMap<>();
        for (int i : nums){
            if(map.containsKey(i)){
                map.put(i,map.get(i)+1);
            }else {
                map.put(i,1);
            }
        }
        Iterator it = map.entrySet().iterator();
        while (it.hasNext()){
            Map.Entry entry = (Map.Entry)it.next();
            int key = (Integer) entry.getKey();
            int value = (Integer) entry.getValue();
            if(map.containsKey(key+1)){
                max = max>(value+map.get(key+1))?max:(value+map.get(key+1));
            }
        }
        return max;
    }

    /**
     * leetcode 507 perfect number
     * @param num
     * @return
     */
    public boolean checkPerfectNumber(int num) {
        if(num<=1){
            return false;
        }
        int i = 2;
        int sum = 1;
        while (i*i<num){
            if(num%i == 0)
                sum += i+num/i;
            if(i*i == num)
                sum -= i;
            if(sum> num)
                return false;
            i++;
        }
        return sum == num;
    }

    /**
     * leetcode 581 shortest unsorted continuous subarray
     * @param nums
     * @return
     */
    public int findUnsortedSubarray(int[] nums) {
        int[] temp = new int[nums.length];
        temp = Arrays.copyOf(nums,nums.length);
        Arrays.sort(temp);
        int start = -1;
        int end = -1;
        for(int i = 0;i<nums.length;i++){
            if(nums[i] != temp[i] && start == -1){
                start = i;
            }else if(start != -1 && nums[i] != temp[i]){
                end = i;
            }
        }
        if(start == -1)
            return 0;
        return end-start+1;
    }

    /**
     * leetcode 532 k-diff pairs in an array
     * @param nums
     * @param k
     * @return
     */
    public int findPairs(int[] nums, int k) {
        if(k<0) return 0;
        int count = 0;
        if(k == 0){
            Map<Integer,Integer> map = new HashMap<>();
            for(int i : nums){
                if(map.containsKey(i)){
                    map.put(i,map.get(i)+1);
                }else {
                    map.put(i,1);
                }
            }
            for(Map.Entry entry : map.entrySet()){
                if((int)entry.getValue()>1){
                    count++;
                }
            }
            return count;
        }
        Set<Integer> set = new HashSet<>();
        for(int i : nums){
            set.add(i);
        }
        for(int i : set){
            if(set.contains(i-k)){
                count++;
            }
        }
        return count;
    }

    /**
     * leetcode 537 complex number multiplication
     * @param a
     * @param b
     * @return
     */
    public String complexNumberMultiply(String a, String b) {
        String[] strings = a.split("\\+");
        String[] strings1 = b.split("\\+");
        int a1 = Integer.parseInt(strings[0]);
        int b1 = Integer.parseInt(strings[1].split("i")[0]);
        int a2 = Integer.parseInt(strings1[0]);
        int b2 = Integer.parseInt(strings1[1].split("i")[0]);
        int A = a1*a2-b1*b2;
        int B = a1*b2+a2*b1;
        return A+"+"+B+"i";
    }

    /**
     * leetcode 540 single element in a sorted array
     * time: O(long N) space: O(1)
     * medium
     * @param nums
     * @return
     */
    public int singleNonDuplicate(int[] nums) {
        /**
         * one
         */
//        if(nums.length == 1){
//            return nums[0];
//        }
//        if(nums[0] != nums[1]){
//            return nums[0];
//        }
//        if(nums[nums.length-1] != nums[nums.length-2]){
//            return nums[nums.length-1];
//        }
//        int start = 0;
//        int end = nums.length-1;
//        int mid = 0;
//        while (start < end){
//            mid = (start+end)/2;
//            if(nums[mid] != nums[mid+1] && nums[mid] != nums[mid-1])
//                return nums[mid];
//            if(mid%2 == 0){
//                if(nums[mid] == nums[mid+1]){
//                    start = mid+1;
//                }else {
//                    end = mid-1;
//                }
//            }else {
//                if(nums[mid] == nums[mid+1]){
//                    end = mid-1;
//                }else {
//                    start = mid+1;
//                }
//            }
//        }
//        return nums[start];
        /**
         * two
         */
        int start = 0;
        int end = nums.length-1;
        while (start < end){
            int mid = (start+end)/2;
            if(mid % 2 == 1) mid--;
            if (nums[mid] != nums[mid + 1]) end = mid;
            else start = mid + 2;
        }
        return nums[start];
    }

    /**
     * leetcode 553 optimal division
     * @param nums
     * @return
     */
    public String optimalDivision(int[] nums) {
        StringBuilder stringBuilder = new StringBuilder();
        if(nums.length == 1) return ""+nums[0];
        if(nums.length == 2) return nums[0]+"/"+nums[1];
        for (int i = 0;i<nums.length;i++){
            if(i == 0){
                stringBuilder.append(nums[i]+"/(");
            }else if(i == nums.length-1){
                stringBuilder.append(nums[i]+")");
            }else {
                stringBuilder.append(nums[i]+"/");
            }
        }
        return stringBuilder.toString();
    }

    /**
     * leetcode 526 beautiful arrangement
     * 回溯问题
     * medium
     * @param N
     * @return
     */
    int countArrangements = 0;
    public int countArrangement(int N) {
        int[] temp = new int[N];
        for (int i = 0;i<N;i++){
            temp[i] = i+1;
        }
        countArrangementHelper(temp,1,N);
        return countArrangements;
    }

    public void countArrangementHelper(int[] temp,int i,int N){
        if(i > N){
            countArrangements++;
            return;
        }
        for (int j=0;j<temp.length;j++){
            if(temp[j] != -1 && (temp[j]%i == 0 || i%temp[j] == 0)){
                temp[j] = -1;
                countArrangementHelper(temp,i+1,N);
                temp[j] = j+1;
            }
        }
    }

    /**
     * 599
     * @param list1
     * @param list2
     * @return
     */
    public static String[] findRestaurant(String[] list1, String[] list2) {
        Map<String,Integer> map = new HashMap<>();
        int i = 0;
        for(String s : list1){
            map.put(s,i);
            i++;
        }
        List<String> list = new ArrayList<>();
        int min = 200000;
        for(int j = 0;j<list2.length;j++){
            if(map.containsKey(list2[j])){
                if(min == (map.get(list2[j])+j)) {
                    list.add(list2[j]);
                }else if(min>(map.get(list2[j])+j)) {
                    list.clear();
                    list.add(list2[j]);
                    min = map.get(list2[j])+j;
                }
            }
        }
        String[] strings = new String[list.size()];
        int k = 0;
        for(String s:list){
            strings[k] = s;
            k++;
        }
        return strings;
    }

    /**
     * leetcode 565 array nesting
     * @param nums
     * @return
     */
    public int arrayNesting(int[] nums) {
        int max = 0;
        int[] temp = new int[nums.length];
        for (int i = 0;i<nums.length;i++){
            temp[i] = 1;
            int j = nums[i];
            int maxTemp = 1;
            while (temp[j] != 1){
                temp[j] = 1;
                j = nums[j];
                maxTemp++;
            }
            max = maxTemp>max?maxTemp:max;
        }
        return max;
    }

    /**
     * leetcode 529 minesweeper
     * @param board
     * @param click
     * @return
     */
    public char[][] updateBoard(char[][] board, int[] click) {
        if(board[click[0]][click[1]] == 'M'){
            board[click[0]][click[1]] = 'X';
            return board;
        }else if(board[click[0]][click[1]] == 'E') updateBoardHelper(board,click[0],click[1]);
        return board;
    }

    public void updateBoardHelper(char[][] board,int row,int colum){
        if(row <0 || row>board.length-1 || colum <0 || colum>board[0].length-1 || board[row][colum] == 'M')
            return;
        if(board[row][colum] == 'E'){
            int mineSum = 0;
            for (int i = -1;i<2;i++){
                for (int j = -1;j<2;j++){
                    if(i==0&&j==0) continue;
                    if(row+i>=0 &&row+i <= board.length-1 && colum+j>=0 && colum+j<=board[0].length-1 && board[row+i][colum+j] == 'M') mineSum++;
                }
            }
            board[row][colum] = mineSum == 0?'B':(char)('0'+mineSum);
            if(mineSum == 0){
                for (int i = -1;i<2;i++){
                    for (int j = -1;j<2;j++){
                        if(i == 0 && j == 0) continue;
                        updateBoardHelper(board,row+i,colum+j);
                    }
                }
            }
        }
    }

    /**
     * leetcode 547 friends circles
     * dfs
     * @param M
     * @return
     */
    public int findCircleNum(int[][] M) {
        if(M == null &&  M.length == 0){
            return 0;
        }
        int len = M.length;
        boolean[] visited = new boolean[M.length];
        int count = 0;
        for (int i = 0;i<len;i++){
            if(findCircleNumHelper(M,i,visited) > 0){
                count++;
            }
        }
        return count;
    }

    public int findCircleNumHelper(int[][] M,int i,boolean[] visited){
        if(visited[i]){
            return 0;
        }
        visited[i] = true;
        int count = 1;
        for (int j = 0;j<M.length;j++){
            if(M[i][j] == 1 && i != j){
                count += findCircleNumHelper(M,j,visited);
            }
        }
        return count;
    }

    /**
     * leetcode 503 next greater element II
     * @param nums
     * @return
     */
    public int[] nextGreaterElements(int[] nums) {
        /**
         * one
         */
//        int[] res = new int[nums.length];
//        for (int i = 0;i<nums.length;i++){
//            int j = i+1 >= nums.length?0:i+1;
//            boolean mask = true;
//            while (nums[j] <= nums[i]){
//                j++;
//                if(j >= nums.length){
//                    j = 0;
//                }
//                if(j == i){
//                    res[i] = -1;
//                    mask = false;
//                    break;
//                }
//            }
//            if(mask) res[i] = nums[j];
//        }
//        return res;
        /**
         * two
         * use stack
         */
//        int n = nums.length;
//        int[] temp = new int[n];
//        for (int i = 0;i<n;i++){
//            temp[i] = -1;
//        }
//        Stack<Integer> stack = new Stack<>();
//        for (int i = 0;i<2*n;i++){
//            int num = nums[i%n];
//            while (!stack.empty() && nums[stack.peek()] < num){
//                temp[stack.peek()] = num;
//                stack.pop();
//            }
//            if(i<n) stack.push(i);
//        }
//        return temp;
        /**
         * three
         * refine
         */
//        Stack<Integer> stack = new Stack<>();
//        int[] res = new int[nums.length];
//        for(int i = 0; i < nums.length; i++){
//            stack.push(i);
//            res[i] = -1;
//        }
//        // 从最后一个开始，stack.pop()从大到小
//        for(int i = 0; i < nums.length; i++){
//            while(!stack.isEmpty() && nums[stack.peek()] < nums[i]){
//                res[stack.pop()] = nums[i];
//            }
//            stack.push(i);
//        }
//        return res;
        /**
         * four
         * refine
         */
//        for(int i = 0; i < nums.length; i++){
//            res[i] = -1;                        ===>   Arrays.fill(results, -1);
//        }
        /**
         * five
         * refine
         */
        if (nums.length == 0) return nums;
        int top = -1;
        int[] stack = new int[nums.length];
        int[] indexes = new int[nums.length];
        int[] elements = new int[nums.length];
        stack[++top] = nums[0];
        indexes[top] = 0;
        for (int i = 1; i < nums.length; i++) {
            while (top >= 0 && nums[i] > stack[top]) {
                elements[indexes[top]] = nums[i];
                top--;
            }
            stack[++top] = nums[i];
            indexes[top] = i;
        }
        for (int num : nums) {
            while (top >= 0 && num > stack[top]) {
                elements[indexes[top]] = num;
                top--;
            }
            if (stack[0] == stack[top]) {
                while (top >= 0) elements[indexes[top--]] = -1;
                break;
            }
        }
        return elements;
    }

    /**
     * leetcode 554 brick wall
     * medium
     * We want to cut from the edge of the most common location among all the levels, hence using a map to record the locations and their corresponding occurrence.
     * @param wall brick wall
     * @return min
     */
    public int leastBricks(List<List<Integer>> wall) {
        int min = 0;
        Map<Integer,Integer> map = new HashMap<>();
        for (List<Integer> list: wall){
            int sum = 0;
            for (int i = 0;i<list.size()-1;i++){
                sum += list.get(i);
                map.put(sum,map.getOrDefault(sum,0)+1);
                min = Integer.max(min,map.get(sum));
            }
        }
        return wall.size()-min;
    }

    /**
     * leetcode 539 minimum time different
     * @param timePoints
     * @return
     */
    public int findMinDifference(List<String> timePoints) {
        /**
         * use array.sort() (quick sort)
         */
//        int[] times = new int[timePoints.size()];
//        for (int i = 0;i<timePoints.size();i++){
//            String[] t = timePoints.get(i).split(":");
//            times[i] = Integer.parseInt(t[0])*60+Integer.parseInt(t[1]);
//        }
//        Arrays.sort(times);
//        int min = Integer.MAX_VALUE;
//        for (int i = 0;i<times.length;i++){
//            if(i == times.length-1){
//                min = Integer.min(24*60+times[0]-times[i],min);
//            }else {
//                min = Integer.min(min,times[i+1]-times[i]);
//            }
//        }
//        return min;
        /**
         * use bucket sort
         */
        boolean[] mark = new boolean[24 * 60];
        for (String time : timePoints) {
            String[] t = time.split(":");
            int h = Integer.parseInt(t[0]);
            int m = Integer.parseInt(t[1]);
            if (mark[h * 60 + m]) return 0;
            mark[h * 60 + m] = true;
        }

        int prev = 0, min = Integer.MAX_VALUE;
        int first = Integer.MAX_VALUE, last = Integer.MIN_VALUE;
        for (int i = 0; i < 24 * 60; i++) {
            if (mark[i]) {
                if (first != Integer.MAX_VALUE) {
                    min = Math.min(min, i - prev);
                }
                first = Math.min(first, i);
                last = Math.max(last, i);
                prev = i;
            }
        }

        min = Math.min(min, (24 * 60 - last + first));

        return min;
    }

    /**
     * 525 contiguous array
     * @param nums a binary array
     * @return the maximum length of a contiguous sub_array with equal number if 0 and 1
     */
    public int findMaxLength(int[] nums) {
        // arr[] => 第一次达到count值得index 范围为 all 0's or 1's (2n+1)
//        int[] arr = new int[2*nums.length + 1];
//        Arrays.fill(arr,-2);
//        arr[nums.length] = -1;
//        int maxlen = 0,count = 0;
//        for (int i = 0;i<nums.length;i++){
//            count = count + (nums[i] == 0 ? -1 : 1);
//            if(arr[count+nums.length] >= -1)
//                maxlen = Math.max(maxlen,i-arr[count + nums.length]);
//            else
//                arr[count+nums.length] = i;
//        }
//        return maxlen;

        // use map
        Map<Integer,Integer> map = new HashMap<>();
        map.put(0,-1);
        int maxlen = 0,count = 0;
        for (int i = 0;i<nums.length;i++){
            count = count + (nums[i] == 0 ? -1 : 1);
            if(map.containsKey(count)){
                maxlen = Math.max(maxlen,i-map.get(count));
            }else
                map.put(count,i);
        }
        return maxlen;
    }

    /**
     * 560 sub_array sum equals K
     * @param nums array
     * @param k sum K
     * @return the total number of sum equal K
     */
    public int subarraySum(int[] nums, int k) {
        // use hash map
        int sum = 0,count = 0;
        Map<Integer,Integer> map = new HashMap<>();
        map.put(0,1);
        for (int i = 0;i<nums.length;i++){
            sum += nums[i];
            if(map.containsKey(sum-k))
                count += map.get(sum-k);
            map.put(sum,map.getOrDefault(sum,0)+1);
        }
        return count;
    }

    /**
     * 593 valid square
     * @param p1 point
     * @param p2 point
     * @param p3 point
     * @param p4 point
     * @return whether the four points could construct a square
     */
    public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
        int[][] p={p1,p2,p3,p4};
        Arrays.sort(p, (l1, l2) -> l2[0] == l1[0] ? l1[1] - l2[1] : l1[0] - l2[0]);
        return dist(p[0], p[1]) != 0 && dist(p[0], p[1]) == dist(p[1], p[3]) && dist(p[1], p[3]) == dist(p[3], p[2]) && dist(p[3], p[2]) == dist(p[2], p[0])   && dist(p[0],p[3])==dist(p[1],p[2]);
    }
    private double dist(int[] p1, int[] p2) {
        return (p2[1] - p1[1]) * (p2[1] - p1[1]) + (p2[0] - p1[0]) * (p2[0] - p1[0]);
    }

    public static void main(String[] args){
        Solution solution = new Solution();
        solution.subarraySum(new int[]{1,1,1},2);
    }
}

/**
 * 528 random pick with weight(权重)
 *
 * 把概率分布函数转化为累计概率分布函数。然后通过随机数，进行二分查找。
 */
class Solution1 {

    int[] temp;

    public Solution1(int[] w) {
        temp = new int[w.length];
        if(w.length == 0) return;
        temp[0] = w[0];
        for (int i = 1;i<w.length;i++){
            temp[i] = w[i] + temp[i-1];
        }
    }

    public int pickIndex() {
        int t = new Random().nextInt(temp[temp.length-1])+1;
        int left = 0,right = temp.length-1;
        while (left < right){
            int mid = (left+right)/2;
            if(temp[mid] == t) return mid;
            else if(temp[mid] > t) right = mid;
            else left = mid + 1;
        }
        return left;
    }
}