package leetcode900_1000;

import java.util.Arrays;

public class Solution {

    /**
     * 925 3sum with multiplicity
     */
    public int threeSumMulti(int[] A, int target) {
        int max = 1000000007;
        Arrays.sort(A);
        int res = 0;
        for (int i = 0;i<A.length;i++){
            int left = i+1,right = A.length-1;
            while (left < right){
                int t = A[left] + A[right] + A[i];
                if (t == target){
                    if (A[left] == A[right]){
                        int k = right - left + 1;
                        for (int j = 1;j<k;j++){
                            res += j;
                        }
                        res %= max;
                        break;
                    }else {
                        int x = 1,k = 1;
                        while (left+1 < right && A[left] == A[left+1]){
                            x++;
                            left++;
                        }
                        while (right - 1 > left && A[right] == A[right-1]){
                            k++;
                            right--;
                        }
                        res += (x*k) % max;
                        left++;
                        right--;
                    }
                }else if (t > target){
                    right--;
                }else {
                    left++;
                }
            }
        }
        return res % max;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.threeSumMulti(new int[]{1,1,2,2,2,2},5);
    }

}
