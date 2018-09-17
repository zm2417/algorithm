package greedy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Solution {
    /**
     * 881 boats to save people
     * two points greedy
     * @param people array of people weight
     * @param limit boat limit
     * @return minimum number of boats
     */
    public int numRescueBoats(int[] people, int limit) {
        Arrays.sort(people);
        int i = 0,j = people.length-1,sum = 0;
        while (i<=j){
            if(people[i]+people[j] <= limit){
                sum++;
                i++;
                j--;
            }else {
                sum++;
                j--;
            }
        }
        return sum;
    }
}
