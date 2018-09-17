package dynamicProgramming;

import binaryTree.TreeNode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * dynamic programming
 */
public class Solution {

    /**
     * 货币最小数量兑换 min[i] = 1 + min[i-coins[j]]
     * @param sum 总和
     * @return min数组
     */
    private static final int[] coins = new int[]{1,10,25};
    public static int[] getMin(int sum){
        int[] min = new int[sum+1];
        for(int i = 0; i<=sum; i++){
            min[i] = Integer.MAX_VALUE;
        }
        min[0] = 0;
        min[1] = 1;
        for(int i = 2; i <= sum; i++){
            for(int j = 0,len = coins.length;j<len;j++){
                if(i>=coins[j]){
                    min[i] = Math.min(min[i],min[i-coins[j]]+1);
                }
            }
        }
        return min;
    }

    /**
     * leetcode 712 minimum ASCII delete sum for two strings
     * @param s1
     * @param s2
     * @return
     */
    public int minimumDeleteSum(String s1, String s2) {
        /*
        标准的动态规划题目，难点在于想出将两个字符串删除到相同的过程
        这里从两个字符串的开头字符考虑，一个一个得考虑到最后一个，我们用二维数组dp[i][j]代表字符串1前i个字符和字符串2前j个字符实现相同所需要
       删除的ASCII value,有三种方法可以到达dp[i][j]:
       1.dp[i-1][j] + str1[i]:由于从dp[i-1][j]到dp[i][j]是多考虑了str1的一个字符，但是str2字符数没变，所以要想相同，必须删除str[i],考虑value的话就是加上str[i]
       2.dp[i][j-1] + str1[j]:对应于1，这个是多考虑str2的一个字符，所以要删除str2[j]
       3.dp[i-1][j-1] + a，这里是考虑两个str都加了一个，所以str1[i] =str2[j]时，a=0；str1[i] ！=str2[j]时，两个都要删除，a=str1[i] +str2[j]
       这三种情况每次比较出最小的来，最后返回dp[str1.length][str2.length](这里字符串下标从1开始，因为我们考虑dp数组的第0行代表str1还啥也没有，第0列代表str2啥也没有)
         */
        int m = s1.length();
        int n = s2.length();
        int[][] dp = new int[m+1][n+1];
        //初始化动态数组，就是第0行数据和第1行数据,注意由于下标从1开始，所以charAt的时候要-1
        for (int i = 1;i < m+1;i++)
            dp[i][0] = dp[i-1][0] + s1.charAt(i-1);
        for (int i = 1;i < n+1;i++)
            dp[0][i] = dp[0][i-1] + s2.charAt(i-1);
        for (int i = 1;i < m+1;i++)
        {
            for (int j = 1;j < n+1;j++)
            {
                //先看s1[i]和s2[j]是不是相等，确定a
                int a =(s1.charAt(i-1) == s2.charAt(j-1))? 0 : s1.charAt(i-1)+s2.charAt(j-1);
                //比较三种情况
                dp[i][j] = Math.min(dp[i-1][j-1]+a,Math.min(dp[i-1][j] + s1.charAt(i-1),dp[i][j-1] + s2.charAt(j-1)));
            }
        }
        return dp[m][n];
    }

    /**
     * leetcode 646 maximum length of pair chain
     * dp or greedily
     * @param pairs
     * @return
     */
    public int findLongestChain(int[][] pairs) {
        /**
         * dp
         */
//        Arrays.sort(pairs, (a,b)->a[0]-b[0]);
//        int[] dp = new int[pairs.length];
//        for (int i = 0;i<dp.length;i++){
//            dp[i] = 1;
//        }
//        for (int i = 1;i<pairs.length;i++){
//            for (int j = 0;j<i;j++){
//                if(pairs[j][1] < pairs[i][0] && dp[i] < dp[j]+1)
//                    dp[i] = dp[j]+1;
//            }
//        }
//        int max = 0;
//        for (int i = 0;i<dp.length;i++){
//            max = Integer.max(max,dp[i]);
//        }
//        return max;
        /**
         * greedily
         */
        Arrays.sort(pairs, (a,b)->a[1]-b[1]);
        int cur = Integer.MIN_VALUE,ans = 0;
        for (int[] pair : pairs){
            if(cur < pair[0]){
                cur = pair[1];
                ans++;
            }
        }
        return ans;
    }

    /**
     * 714 beat time to buy and sell stock with transaction free
     * dp or greedily
     * @param prices price array
     * @param fee fee
     * @return max profit
     */
    public int maxProfit(int[] prices, int fee) {
        // dp
//        int cash = 0; //the maxPro you have if you don't have a stock that day
//        int hold = prices[0]; //the maxPro you have if you have a stock that day, if you have a stock the first day,hold=-prices[0]
//        int i;
//        for (i=1;i<prices.length;i++){
//            cash = Integer.max(cash,hold+prices[i]-fee);
//            hold = Integer.max(hold,cash-prices[i]);
//        }
//        return cash;
        /**
         * greedily
         * 贪心选择的关键是找到一个最大后是不是能够卖掉stock，重新开始寻找买入机会。
         * 比如序列1 3 2 8，如果发现2小于3就完成交易买1卖3，此时由于fee=2，（3-1-fee）+(8-2-fee)<(8-1-fee)，所以说明卖早了，
         * 令max是当前最大price，当（max-price[i]>=fee）时可以在max处卖出，且不会存在卖早的情况，再从i开始重新寻找买入机会。
         */
        int profit=0;
        int curProfit=0;
        int minP=prices[0];
        int maxP=prices[0];
        int i;
        for(i=1;i<prices.length;i++){
            minP=Integer.min(minP,prices[i]);
            maxP=Integer.max(maxP,prices[i]);
            curProfit=Integer.max(curProfit,prices[i]-minP-fee);
            if((maxP-prices[i])>=fee){//can just sell the stock at maxP day.
                profit+=curProfit;
                curProfit=0;
                minP=prices[i];
                maxP=prices[i];
            }
        }
        return profit+curProfit;
    }

    /**
     * leetcode 337 house robber III
     * @param root
     * @return
     */
    public int rob(TreeNode root) {
        robHelper(root);
        return root == null?0:root.val;
    }

    public void robHelper(TreeNode node){
        if(node == null) return;
        if(node.left == null && node.right == null) return;
        robHelper(node.left);
        robHelper(node.right);
        int pre1 = 0;
        int pre2 = 0;
        if(node.left != null){
            pre1 += node.left.val;
            if(node.left.left != null){
                pre2 += node.left.left.val;
            }
            if(node.left.right != null){
                pre2 += node.left.right.val;
            }
        }
        if(node.right != null){
            pre1 += node.right.val;
            if(node.right.left != null){
                pre2 += node.right.left.val;
            }
            if(node.right.right != null){
                pre2 += node.right.right.val;
            }
        }
        node.val = Integer.max(pre1,pre2+node.val);
    }

    /**
     * leetcode 486 predict winner
     * play1 '+' ; play2 '-';
     * @param nums
     * @return
     */
    public boolean PredictTheWinner(int[] nums) {
        /**
         * one
         */
//        return PredictTheWinnerHelper1(nums,0,nums.length-1,1) >= 0;
        /**
         * two
         */
//        Integer[][] memo = new Integer[nums.length][nums.length];
//        return PredictTheWinnerHelper2(nums,0,nums.length-1,memo) >=0;
        /**
         * three
         * dp
         * dp[i,j]=nums[i]−dp[i+1][j],nums[j]−dp[i][j−1]
         */
//        int[][] dp = new int[nums.length + 1][nums.length];
//        for (int s = nums.length; s >= 0; s--) {
//            for (int e = s + 1; e < nums.length; e++) {
//                int a = nums[s] - dp[s + 1][e];
//                int b = nums[e] - dp[s][e - 1];
//                dp[s][e] = Math.max(a, b);
//            }
//        }
//        return dp[0][nums.length - 1] >= 0;
        /**
         * four
         * ap
         */
        int[] dp = new int[nums.length];
        for (int s = nums.length; s >= 0; s--) {
            for (int e = s + 1; e < nums.length; e++) {
                int a = nums[s] - dp[e];
                int b = nums[e] - dp[e - 1];
                dp[e] = Math.max(a, b);
            }
        }
        return dp[nums.length - 1] >= 0;
    }

    /**
     * one
     * recursion
     * use Min-Max algorithm
     * O(2^n) O(n)
     * @param nums
     * @param start
     * @param end
     * @param turn
     * @return
     */
    public int PredictTheWinnerHelper1(int[] nums,int start,int end,int turn){
        if(start == end) return turn*nums[start];
        int a = turn*nums[start] + PredictTheWinnerHelper1(nums,start+1,end,-turn);
        int b = turn*nums[end] + PredictTheWinnerHelper1(nums,start,end-1,-turn);
        return turn*Integer.max(turn*a,turn*b);
    }

    /**
     * two
     * use recursion
     * O(n^2) O(n^2)
     * @param nums
     * @param s
     * @param e
     * @param memo
     * @return
     */
    public int PredictTheWinnerHelper2(int[] nums,int s,int e,Integer[][] memo){
        if(s == e) return nums[s];
        if(memo[s][e] != null) return memo[s][e];
        int a = nums[s] - PredictTheWinnerHelper2(nums,s+1, e,memo);
        int b = nums[e] - PredictTheWinnerHelper2(nums,s,e-1,memo);
        memo[s][e] = Integer.max(a,b);
        return memo[s][e];
    }

    /**
     * leetcode 583 delete operation for two strings
     * @param word1
     * @param word2
     * @return
     */
    public int minDistance(String word1, String word2) {
        /**
         * one
         * temp[i][j]: 前i和前j的字符串最长公共子序列
         */
//        int[][] temp = new int[word1.length()+1][word2.length()+1];
//        for (int i = 1;i<=word1.length();i++){
//            for (int j = 1;j<=word2.length();j++){
//                if(word1.charAt(i-1) == word2.charAt(j-1)){
//                    temp[i][j] = temp[i-1][j-1] + 1;
//                }else {
//                    temp[i][j] = Integer.max(temp[i-1][j],temp[i][j-1]);
//                }
//            }
//        }
//        return word1.length()+word2.length() - 2*temp[word1.length()][word2.length()];
        /**
         * two
         * dp[i][j]: 前i和前j的字符串的最小步数
         */
//        int[][] dp = new int[word1.length()+1][word2.length()+1];
//        for (int i = 0;i<word1.length()+1;i++) dp[i][0] = i;
//        for (int j = 0;j<word2.length()+1;j++) dp[0][j] = j;
//        for (int i = 1;i<=word1.length();i++){
//            for (int j = 1;j<word2.length();j++){
//                if(word1.charAt(i) == word2.charAt(j)){
//                    dp[i][j] = dp[i-1][j-1];
//                }else {
//                    dp[i][j] = 1 + Integer.min(dp[i-1][j],dp[i][j-1]);
//                }
//            }
//        }
//        return dp[word1.length()][word2.length()];
        /**
         * three
         * dfs
         */
//        int[][] memo = new int[word1.length()+1][word2.length()+1];
//        return minDistanceHelper(memo,0,0,word1,word2);
        /**
         * four
         * 1-D dp
         */
        int[] dp = new int[word2.length()+1];
        for (int i = 0;i<=word1.length();i++){
            int[] temp = new int[word2.length()+1];
            for (int j = 0;j<=word2.length();j++){
                if(i == 0 || j == 0) temp[j] = i+j;
                else if(word1.charAt(i-1) == word2.charAt(j-1)){
                    temp[j] = dp[j-1];
                }else {
                    temp[j] = 1 + Integer.min(temp[j-1],dp[j]);
                }
            }
            dp = temp;
        }
        return dp[word2.length()];
    }

    public int minDistanceHelper(int[][] memo,int i,int j,String word1,String word2){
        if(memo[i][j] != 0) return memo[i][j];
        int n1 = word1.length(),n2 = word2.length();
        if(i == n1 || j == n2) return n1-i+n2-j;
        if(word1.charAt(i) == word2.charAt(j)) memo[i][j] = minDistanceHelper(memo,i+1,j+1,word1,word2);
        else memo[i][j] = 1 + Integer.min(minDistanceHelper(memo,i+1,j,word1,word2),minDistanceHelper(memo,i,j+1,word1,word2));
        return memo[i][j];
    }

    /**
     * leetcode 740 delete and earn
     * @param nums input array
     * @return maximum earn
     */
    public int deleteAndEarn(int[] nums) {
        if(nums.length == 0) return 0;
        int[] temp = new int[10001];
        int max = Integer.MIN_VALUE;
        for (int i : nums){
            temp[i] += i;
            max = Integer.max(max,i);
        }
        int[] dp = new int[max+1];
        dp[1] = temp[1];
        for (int i = 2;i<max+1;i++){
            dp[i] = Integer.max(dp[i-1],dp[i-2]+temp[i]);
        }
        return dp[max];
    }

    /**
     * leetcode 494 target sum
     * 背包问题
     * @param nums array
     * @param S target sum
     * @return ways
     */
    int findTargetSumWaysCount = 0;
    public int findTargetSumWays(int[] nums, int S) {
        /**
         * brute force
         */
//        findTargetSumWaysHelper1(nums,S,0,0);
//        return findTargetSumWaysCount;
        /**
         * recursion with memoization
         */
//        int[][] memo = new int[nums.length][2001];
//        for (int[] arr : memo) Arrays.fill(arr,Integer.MIN_VALUE);
//        return findTargetSumWaysHelper2(nums,S,0,0,memo);
        /**
         * 2-d dp
         */
//        int[][] dp = new int[nums.length][2001];
//        dp[0][nums[0]+1000] = 1;
//        dp[0][-nums[0]+1000] += 1;
//        for (int i = 1;i<nums.length;i++){
//            for (int sum = -1000;sum < 1000;sum++){
//                if(dp[i-1][sum+1000] > 0){
//                    dp[i][nums[i]+sum+1000] += dp[i-1][sum+1000];
//                    dp[i][sum-nums[i]+1000] += dp[i-1][sum+1000];
//                }
//            }
//        }
//        return S > 1000 ? 0 : dp[nums.length-1][S+1000];
        /**
         * 1-d dp
         */
//        int[] dp = new int[2001];
//        dp[nums[0]+1000] = 1;
//        dp[-nums[0]+1000] += 1;
//        for (int i = 1;i<nums.length;i++){
//            int[] temp = new int[2001];
//            for (int sum = -1000;sum<1000;sum++){
//                if(dp[sum+1000] > 0){
//                    temp[sum+nums[i]+1000] += dp[sum+1000];
//                    temp[sum-nums[i]+1000] += dp[sum+1000];
//                }
//            }
//            dp = temp;
//        }
//        return S > 1000 ? 0 : dp[S + 1000];
        /**
         *  subset sum
         *  sum(P) - sum(N) = target
         *  sum(P) + sum(N) + sum(P) - sum(N) = target + sum(P) + sum(N)
         *  2 * sum(P) = target + sum(nums)
         */
        int sum = 0;
        for (int i : nums) sum += i;
        return sum < S || (S+sum)%2>0 ?0:findTargetSumWaysHelper3(nums,(S+sum)>>>1);
    }

    public void findTargetSumWaysHelper1(int[] nums,int S,int curr,int i){
        if(i == nums.length){
            if(curr == S) findTargetSumWaysCount++;
        }else {
            findTargetSumWaysHelper1(nums,S,curr+nums[i],i+1);
            findTargetSumWaysHelper1(nums,S,curr-nums[i],i+1);
        }
    }
    public int findTargetSumWaysHelper2(int[] nums,int S,int cur,int i,int[][] memo){
        if(i == nums.length){
            if(cur == S) return 1;
            else return 0;
        }else {
            if(memo[i][cur+1000] != Integer.MIN_VALUE){
                return memo[i][cur+1000];
            }
            int add = findTargetSumWaysHelper2(nums,S,cur+nums[i],i+1,memo);
            int subtract = findTargetSumWaysHelper2(nums,S,cur-nums[i],i+1,memo);
            memo[i][cur+1000] = add+subtract;
            return memo[i][cur+1000];
        }
    }
    public int findTargetSumWaysHelper3(int[] nums,int target){
        int[] dp = new int[target+1];
        dp[0] = 1;
        for (int n : nums){
            for (int i = target;i>=n;i--){
                dp[i] += dp[i-n];
            }
//            for (int i = n;i<=target;i++)
//                dp[i] += dp[i-n];
        }
        return dp[target];
    }

    /**
     * leetcode 62 unique path
     * @param m
     * @param n
     * @return
     */
    public int uniquePaths(int m, int n) {
        /**
         * 2-d dp
         */
//        if (m < 1 || n < 1) return 0;
//        if (m == 1 || n == 1) return 1;
//        int[][] dp = new int[n][m];
//        dp[0][0] = 1;
//        for (int i = 0;i<n;i++) dp[i][0] = 1;
//        for (int i = 0;i<m;i++) dp[0][i] = 1;
//        for (int i = 1;i<n;i++){
//            for (int j = 1;j<m;j++){
//                dp[i][j] = dp[i-1][j] + dp[i][j-1];
//            }
//        }
//        return dp[n-1][m-1];
        /**
         * 1-d dp
         */
        if(m == 1 || n == 1) return 1;
        if(m < 1 || n < 1) return 0;
        int[] dp = new int[m];
        Arrays.fill(dp,1);
        for (int i = 1;i<n;i++)
            for (int j = 1;j<m;j++)
                dp[j] += dp[j-1];
        return dp[m-1];
    }

    /**
     * Leetcode 516 longest palindromic subsequence
     * dp[i][j] : the longest palindromic subsequnce'length of subString(i,j)
     * 从尾部到头部 ： the new dp[i][j] use the information from dp[i+1][j-1] which is 0 if the outer loop starts from 0.
     * @param s
     * @return
     */
    public int longestPalindromeSubseq(String s) {
        int[][] dp = new int[s.length()][s.length()];
        for (int i = s.length()-1;i>=0;i--){
            dp[i][i] = 1;
            for (int j = i+1;j<s.length();j++){
                if(s.charAt(i) == s.charAt(j)){
                    dp[i][j] = dp[i+1][j-1] + 2;
                }else {
                    dp[i][j] = Integer.max(dp[i][j-1],dp[i+1][j]);
                }
            }
        }
        return dp[0][s.length()-1];
    }

    /**
     * leetcode 377 combination sum IV
     * dp[n] += dp[n-i]
     * @param nums
     * @param target
     * @return
     */
    public int combinationSum4(int[] nums, int target) {
        Arrays.sort(nums);
        int[] dp = new int[target+1];
        dp[0] = 1;
        for (int i = 1;i < dp.length; i++){
            for (int cur : nums){
                if(cur > i) break;
                dp[i] += dp[i-cur];
            }
        }
        return dp[target];
    }

    /**
     * 96 unique binary search trees
     * @param n
     * @return
     */
    public int numTrees(int n) {
        /**
         * recursion
         * timeout
         */
//        if(n <= 1) return 1;
//        int sum = 0;
//        for (int i = 1;i<=n;i++){
//            sum += numTrees(i-1)*numTrees(n-i);
//        }
//        return sum;
        /**
         * dp
         */
        int[] dp = new int[n+1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2;i<=n;i++){
            for (int j = 0;j<i;++j){
                dp[i] += dp[j]*dp[i-j-1];
            }
        }
        return dp[n];
    }

    /**
     * 309 best time to buy and sell stock with cool down
     * @param prices prices array
     * @return maximum profit
     */
    public int maxProfit(int[] prices) {
        if(prices.length == 0) return 0;
        int len = prices.length;
        int[] cash = new int[len+1];
        int[] hold = new int[len+1];
        hold[1] = -prices[0];
        for (int i = 2;i<=len;i++){
            cash[i] = Math.max(cash[i-1],hold[i-1]+prices[i-1]);
            hold[i] = Math.max(hold[i-1],cash[i-2]-prices[i-1]);
        }
        return cash[len];
    }

    /**
     * 64 minimum path sum
     * @param grid m * n grid
     * @return minimum path sum
     */
    public int minPathSum(int[][] grid) {
//        int[][] dp = new int[grid.length][grid[0].length];
//        dp[0][0] = grid[0][0];
//        for (int i = 1;i<grid[0].length;i++){
//            dp[0][i] = grid[0][i] + dp[0][i-1];
//        }
//        for (int i = 1;i<grid.length;i++){
//            dp[i][0] = dp[i-1][0] + grid[i][0];
//        }
//        for (int i = 1;i<grid.length;i++){
//            for (int j = 1;j<grid[0].length;j++){
//                dp[i][j] = Math.min(dp[i][j-1],dp[i-1][j]) + grid[i][j];
//            }
//        }
//        return dp[grid.length-1][grid[0].length-1];

        // not use extra space
        int m = grid.length,n = grid[0].length;
        for (int i = 0;i<m;i++){
            for (int j = 0;j<n;j++){
                if(i == 0 && j != 0) grid[i][j] += grid[i][j-1];
                if(i != 0 && j == 0) grid[i][j] += grid[i-1][j];
                if(i != 0 && j != 0) grid[i][j] += Math.min(grid[i-1][j],grid[i][j-1]);
            }
        }
        return grid[m-1][n-1];
    }

    /**
     * 718 maximum length of repeated sub array
     * @param A array
     * @param B array
     * @return maximum length
     */
    public int findLength(int[] A, int[] B) {
        // dp[i][j] : longest common prefix of A[i:] and B[j:]
        int max = 0;
        int[][] dp = new int[A.length+1][B.length+1];
        for (int i = A.length-1;i>=0;i--){
            for (int j = B.length-1;j>=0;j--){
                if(A[i] == B[j]){
                    dp[i][j] = dp[i+1][j+1] + 1;
                    max = Math.max(max,dp[i][j]);
                }
            }
        }
        return max;
    }

    /**
     * 813 largest sum of averages
     * @param A array
     * @param K k adjacent
     * @return max sum of averages
     */
    public double largestSumOfAverages(int[] A, int K) {
        int len = A.length;
        double[] pre = new double[len+1];
        for (int i = 1;i<=len;i++)
            pre[i] = pre[i-1] + A[i-1];
        double[][] dp = new double[K][len];
        for (int k = 0;k<K;k++){
            for (int i = 0;i<len;i++){
                dp[k][i] = k == 0 ? pre[i+1]/(i+1) : dp[k-1][i];
                if(k > 0)
                    for (int j =i-1;j>=0;j--)
                        dp[k][i] = Math.max(dp[k][i],dp[k-1][j] + (pre[i+1]-pre[j+1])/(i-j));
            }
        }
        return dp[K-1][len-1];
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
    }

}
