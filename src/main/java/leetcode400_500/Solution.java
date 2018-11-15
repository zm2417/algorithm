package leetcode400_500;

import leetcode0_100.ListNode;
import org.omg.PortableInterceptor.INACTIVE;

import java.util.*;

public class Solution {

    /**
     * leetcode 415 add strings
     * @param num1
     * @param num2
     * @return
     */
    public String addStrings(String num1, String num2) {
        StringBuilder sb = new StringBuilder();
        int carry = 0;
        for(int i = num1.length()-1,j = num2.length()-1;i>=0 || j>=0 || carry ==1;i--,j--){
            int x = i<0?0:num1.charAt(i)-'0';
            int y = j<0?0:num2.charAt(j)-'0';
            sb.append((x+y+carry)%10);
            carry = (x+y+carry)/10;
        }
        return sb.reverse().toString();
    }

    /**
     * leetcode 405 convert a number to Hexadecimal
     * @param num
     * @return
     */
    public String toHex(int num) {
//        int a = 32-Integer.numberOfLeadingZeros(num);
//        int count =Math.max( (int)Math.ceil((double)a/4),1);
//        char[] chars = new char[count];
//        do{
//            chars[--count] = Solution.digits[num&15];
//            num >>>= 4;
//        }while (num != 0 && count >= 0);
//        return String.valueOf(chars);
        String res = "";
        char[] map = new char[]{'0' , '1' , '2' , '3' , '4' , '5' ,
                '6' , '7' , '8' , '9' , 'a' , 'b' ,
                'c' , 'd' , 'e' , 'f' , 'g' , 'h' ,
                'i' , 'j' , 'k' , 'l' , 'm' , 'n' ,
                'o' , 'p' , 'q' , 'r' , 's' , 't' ,
                'u' , 'v' , 'w' , 'x' , 'y' , 'z'};
        do{
            res = map[num&15]+res;
            num >>>= 4;
        }while (num != 0);
        return res;
    }

    final static char[] digits = {
            '0' , '1' , '2' , '3' , '4' , '5' ,
            '6' , '7' , '8' , '9' , 'a' , 'b' ,
            'c' , 'd' , 'e' , 'f' , 'g' , 'h' ,
            'i' , 'j' , 'k' , 'l' , 'm' , 'n' ,
            'o' , 'p' , 'q' , 'r' , 's' , 't' ,
            'u' , 'v' , 'w' , 'x' , 'y' , 'z'
    };

    /**
     * leetcode 482 License Key Formatting
     * @param S
     * @param K
     * @return
     */
    public String licenseKeyFormatting(String S, int K) {
        char[] chars = S.toCharArray();
        int mask = 0;
        int diff = 'a'-'A';
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = S.length()-1;i>=0;i--){
            if(chars[i] != '-'){
                if(mask == K){
                    stringBuilder.insert(0,'-');
                    mask = 0;
                }
                if(chars[i]>='a'&&chars[i]<='z'){

                    stringBuilder.insert(0,(char)(chars[i]-diff));
                    mask++;
                }else {
                    stringBuilder.insert(0,chars[i]);
                    mask++;
                }
            }
        }
        return stringBuilder.toString();
    }

    /**
     * leetcode 459 repeated substring pattern
     * @param s
     * @return
     */
    public boolean repeatedSubstringPattern(String s) {
        for(int i = 0; i<s.length(); i++){
            String tem = s.substring(0,i+1);
            int j = i+1;
            int mask = 0;
            if(j+i+1 > s.length()){
                break;
            }
            while (j+i+1 <= s.length()){
                if(!tem.equals(s.substring(j,j+i+1))){
                    mask =1;
                    break;
                }
                j = j+i+1;
            }
            if(mask == 0 && j == s.length()){
                return true;
            }
        }
        return false;
    }

    /**
     * leetcode 441 arranging coins
     * @param n
     * @return
     */
    public int arrangeCoins(int n) {
//        int i = 1;
//        while (true){
//            if(n-i>0)
//                n -= i;
//            else if(n-i == 0)
//                return i;
//            else
//                return i-1;
//            i++;
//        }
        /**
         * 等差数列
         */
        return (int)((Math.sqrt(8*(long)n + 1) - 1)/2);
    }

    /**
     * leetcode 434 number of segments
     * @param s
     * @return
     */
    public int countSegments(String s) {
        s = s.trim();
        if(s.equals("")){
            return 0;
        }
        return s.split(" ").length;
    }

    /**
     * leetcode 443 string compression
     * @param chars
     * @return
     */
    public int compress(char[] chars) {
        if(chars.length == 0) return 0;
        char pre = chars[0];
        int index = 0;
        int count = 1;
        for(int i = 1,len = chars.length;i<len;i++){
            if(pre != chars[i] && count != 0){
               chars[index] = pre;
               index++;
               if(count != 1){
                   char[] temp = String.valueOf(count).toCharArray();
                   for(char c : temp){
                       chars[index++] = c;
                   }
               }
               pre = chars[i];
               count = 1;
           }else {
               count++;
           }
        }
        chars[index++] = pre;
        if(count >1) {
            char[] temp = String.valueOf(count).toCharArray();
            for (char c : temp) {
                chars[index++] = c;
            }
        }
        return index;
    }

    /**
     * leetcode 438 find all anagrams in a string
     * @param s
     * @param p
     * @return
     */
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> list = new ArrayList<>();
        if(s.length()<p.length()) return list;
        int[] temp = new int[26];
        char[] p1 = p.toCharArray();
        for(char c : p1){
            temp[c-'a']++;
        }
        int len = p.length();
        for(int i = 0;i<=s.length()-len;i++){
            String s1 = s.substring(i,i+len);
            int[] arr = new int[26];
            char[] t = s1.toCharArray();
            for(char c : t){
                arr[c-'a']++;
            }
            int mask = 0;
            for(int j = 0;j<26;j++){
                if(temp[j] != arr[j]){
                    mask =1;
                }
            }
            if(mask == 0)
                list.add(i);
        }
        return list;
    }

    /**
     * leetcode 400 nth digit
     * @param n
     * @return
     */
    public int findNthDigit(int n) {
        if(n <=0) return 0;
        long count = 9;
        int start = 1;
        int len = 1;
        while(n > len*count){
            n -= len*count;
            len++;
            start *= 10;
            count *= 10;
        }
        start += (n-1)/len;
        return String.valueOf(start).charAt((n-1)%len)-'0';
    }

    /**
     * leetcode 475 heaters
     * @param houses
     * @param heaters
     * @return
     */
    public int findRadius(int[] houses, int[] heaters) {
        Arrays.sort(heaters);
        int result = Integer.MIN_VALUE;

        for (int house : houses) {
            int index = Arrays.binarySearch(heaters, house);
            if (index < 0) {
                index = -(index + 1);
            }
            int dist1 = index - 1 >= 0 ? house - heaters[index - 1] : Integer.MAX_VALUE;
            int dist2 = index < heaters.length ? heaters[index] - house : Integer.MAX_VALUE;

            result = Math.max(result, Math.min(dist1, dist2));
        }

        return result;
    }

    /**
     * 414 third maximum number
     * @param nums array
     * @return int
     */
    public int thirdMax(int[] nums) {
        int[] arr = new int[3];
        int mask = 0;
        for(int i : nums){
            if(mask == 0){
                mask++;
                arr[0] = i;
            }else if(mask == 1){
                if(arr[0]>i){
                    arr[1] = i;
                    mask++;
                }else if(arr[0]<i){
                    arr[1] = arr[0];
                    arr[0] = i;
                    mask++;
                }
            }else if(mask == 2){
                if(i<arr[1]){
                    arr[2] = i;
                    mask++;
                }else if(i>arr[1]&&i<arr[0]){
                    arr[2] = arr[1];
                    arr[1] = i;
                    mask++;
                }else if(i>arr[0]){
                    arr[2] = arr[1];
                    arr[1] = arr[0];
                    arr[0] = i;
                    mask++;
                }
            }else if(mask == 3){
                if(i>arr[0]){
                    arr[2] = arr[1];
                    arr[1] =arr[0];
                    arr[0]= i;
                }else if(i<arr[0]&&i>arr[1]){
                    arr[2] = arr[1];
                    arr[1] = i;
                }else if(i>arr[2]&&i<arr[1]){
                    arr[2] = i;
                }
            }
        }
        if(mask==3)
            return arr[2];
        else
            return arr[0];
    }

    /**
     * 419 battleships in a board
     * @param board matrix
     * @return int
     */
    public int countBattleships(char[][] board) {
        int res = 0;
        for(int i = 0;i<board.length;i++){
            for (int j = 0;j<board[i].length;j++){
                if(board[i][j] == '.' || (i>0 && board[i-1][j]=='X') || (j>0 && board[i][j-1]=='X')){
                    continue;
                }
                res++;
            }
        }
        return res;
    }

    /**
     * 406 queue reconstruction by height
     * medium
     * @param people matrix
     * @return
     */
    public int[][] reconstructQueue(int[][] people) {
        Arrays.sort(people, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] == o2[0]) return o1[1]-o2[1];
                return o2[0]-o1[0];
            }
        });
        List<int[]> list = new LinkedList<>();
        for (int i = 0;i<people.length;i++){
            list.add(people[i][1],new int[]{people[i][0],people[i][1]});
        }
        int[][] res = new int[people.length][2];
        int i = 0;
        for(int[] k : list){
            res[i][0] = k[0];
            res[i++][1] = k[1];
        }
        return res;
    }

    /**
     * 413 arithmetic slice
     * @param A array
     * @return
     */
    public int numberOfArithmeticSlices(int[] A) {
        int len;
        if((len = A.length) < 3)
            return 0;
        int diff = A[1]-A[0];
        int count = 2;
        List<Integer> list = new ArrayList<>();
        for (int i = 2;i<len;i++){
            int t = A[i]-A[i-1];
            if(t == diff){
                count++;
            }else {
                if(count>2){
                    list.add(count);
                }
                diff = A[i]-A[i-1];
                count = 2;
            }
        }
        if(count >2){
            list.add(count);
        }
        int sum = 0;
        for (int i : list){
            sum += (i-2)*(i-1)/2;
        }
        return sum;
    }

    /**
     * 451 sort characters by frequent
     * medium
     * @param s string
     * @return frequency sort string
     */
    public String frequencySort(String s) {
        if(s.length() < 3)
            return s;
        int max = 0;
        int[] map = new int[256];
        for(char ch : s.toCharArray()) {
            map[ch]++;
            max = Math.max(max,map[ch]);
        }
        String[] buckets = new String[max + 1]; // create max buckets
        for(int i = 0 ; i < 256; i++) { // join chars in the same bucket
            String str = buckets[map[i]];
            if(map[i] > 0)
                buckets[map[i]] = (str == null) ? "" + (char)i : (str + (char) i);
        }
        StringBuilder strb = new StringBuilder();
        for(int i = max; i >= 0; i--) { // create string for each bucket.
            if(buckets[i] != null)
                for(char ch : buckets[i].toCharArray())
                    for(int j = 0; j < i; j++)
                        strb.append(ch);
        }
        return strb.toString();
    }

    /**
     * 462 minimum moves to equal array element II
     * medium 中位数
     * @param nums arrays
     * @return int
     */
    public int minMoves2(int[] nums) {
        Arrays.sort(nums);
        int i = 0;
        int j = nums.length-1;
        int res = 0;
        while (i<j){
            res += nums[j--] - nums[i++];
        }
        return res;
    }

    /**
     * 401
     * 方法1 循环呢遍历所有时间，找到符合的时间
     * @param num
     * @return
     */
    public List<String> readBinaryWatch(int num) {
        List<String> list = new ArrayList<>();
        for(int i = 0 ;i<12;i++){
            for(int j = 0;j<60;j++){
                if( Integer.bitCount(i*64+j) == num){
                    list.add(i+":"+j);
                }
            }
        }
        return list;
    }

    public List<String> readBinaryWatch2(int num){
        int hourCnt = 4;
        int minCnt = 6;
        List<String> res = new ArrayList<String>();

        // 小时和分钟能亮灯个数的所有可能性
        for (int i = num > minCnt? (num - minCnt) : 0; i <= Math.min(hourCnt, num); i++) {
            List<Integer> hourList = getCombination(hourCnt, i);
            List<Integer> minList = getCombination(minCnt, num - i);
            for (Integer hour : hourList) {
                if (hour >= 12) {
                    continue;
                }
                for (Integer min : minList) {
                    if (min <= 59) {
                        res.add(String.format("%d:%02d", hour, min));
                    }
                }
            }
        }
        return res;
    }

    private List<Integer> getCombination(int ledCnt, int num) {
        // 特殊处理，当没有led亮时
        if(num <= 0) {
            List<Integer> l = new ArrayList<>();
            l.add(0);
            return l;
        }
        if (ledCnt == num) {
            List<Integer> l = new ArrayList<>();
            int data = 0;
            for (int i = 0; i < num; i++) {
                data += 1 << i;
            }
            l.add(data);
            return l;
        } else {
            List<Integer> l = new ArrayList<>();

            if (num > 1) {
                List<Integer> subList = getCombination(ledCnt - 1, num - 1);
                Integer []a = new Integer[subList.size()];
                subList.toArray(a);
                int high = 1 << (ledCnt - 1);
                for (int i = 0; i < a.length; i++) {
                    l.add(a[i] + high);
                }
            }else {
                l.add(1 << (ledCnt - 1));
            }

            l.addAll(getCombination(ledCnt - 1, num));
            return l;
        }
    }

    // 数组的全排序
    public void getList(List<Integer> arr, List<Integer> list){
        if(arr.isEmpty()){

            for(int j : list){
                System.out.print(j);
            }
            System.out.print("\n");
            return;
        }
        for(int i = 0; i<arr.size(); i++){
            List<Integer> list1 = new ArrayList<>(arr);
            List<Integer> list2 = new ArrayList<>(list);
            list2.add(arr.get(i));
            list1.remove(arr.get(i));
            getList(list1,list2);
        }
    }

    /**
     * m选n
     * @param n 选择数量
     * @param a 原数组
     * @param b 输出数组
     */
    public void getSelect(int n, List<Integer> a, List<Integer> b){
        if(n==0){
            for(int j : b){
                System.out.print(j);
            }
            System.out.print("\n");
            return;
        }
        for(int i =0;i<a.size();i++){
            List<Integer> a1 = new ArrayList<>(a.subList(i+1,a.size()));
            List<Integer> b1 = new ArrayList<>(b);
            b1.add(a.get(i));
            getSelect(n-1,a1,b1);
        }
    }

    /**
     * 495 Teemo attacking
     * @param timeSeries
     * @param duration
     * @return
     */
    public int findPoisonedDuration(int[] timeSeries, int duration) {
        int sum = 0;
        for (int i = 0,len = timeSeries.length;i<len;i++){
            sum += duration;
            if(i >0 && timeSeries[i]-timeSeries[i-1] < duration){
                sum -= duration-timeSeries[i]+timeSeries[i-1];
            }
        }
        return sum;
    }

    /**
     * leetcode maximum XOR of two numbers in an array
     * medium
     * @param nums array
     * @return maximum XOR
     */
    public int findMaximumXOR(int[] nums) {
        int max = 0, mask = 0;
        // test each bit pose, 判断能不能构成所需要的值；
        for(int i = 31; i >= 0; i --) {
            // 每次都在之前的基础上更新mask
            mask = mask | (1 << i);
            Set<Integer> set = new HashSet<>();
            for(int num : nums) {
                // add the number which has the mask as its prefix;
                set.add(num & mask);
            }
            // 假设当前所能达到的最大值是这个temp值；
            int tmp = max | (1 << i);
            for(Integer prefix : set) {
                if(set.contains(prefix ^ tmp)) {
                    // 如果能组成就直接break
                    max = tmp;
                    break;
                }
            }
        }
        return max;
    }

    /**
     * leetcode 454 4sum II
     * @param A
     * @param B
     * @param C
     * @param D
     * @return
     */
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        int res = 0;
        Map<Integer,Integer> map1 = new HashMap<>();
        for (int i : A){
            for (int j : B){
                map1.put(i+j,map1.getOrDefault(i+j,0)+1);
            }
        }
        for (int i : C){
            for (int j : D){
                res += map1.getOrDefault((-i-j),0);
            }
        }
        return res;
    }

    /**
     * leetcode 477 total hamming distance
     * @param nums
     * @return
     */
    public int totalHammingDistance(int[] nums) {
        int res = 0;
        for (int i = 0;i<32;i++){
            res += totalHammingDistanceHelper(nums,i);
        }
        return res;
    }

    public int totalHammingDistanceHelper(int[] i,int j){
        int count = 0;
        for (int a : i){
            count += (a>>j)&(1);
        }
        return count * (i.length-count);
    }

    /**
     * leetcode 445 add two number II
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Stack<Integer> stack1 = new Stack<>();
        Stack<Integer> stack2 = new Stack<>();
        ListNode node = l1;
        while (node != null){
            stack1.push(node.val);
            node = node.next;
        }
        node = l2;
        while (node != null){
            stack2.push(node.val);
            node = node.next;
        }
        Stack<Integer> stack = new Stack<>();
        addTwoNumberHelper(stack1,stack2,false,stack);
        ListNode head = new ListNode(stack.pop());
        ListNode top = head;
        while (!stack.isEmpty()){
            ListNode node1 = new ListNode(stack.pop());
            head.next = node1;
            head = node1;
        }
        return top;
    }

    public void addTwoNumberHelper(Stack<Integer> stack1, Stack<Integer> stack2, boolean mask,Stack<Integer> stack){
        if(stack1.isEmpty() && stack2.isEmpty()){
            if (mask){
                stack.push(1);
            }
            return;
        }
        int num = 0;
        if(!stack1.isEmpty()){
            num += stack1.pop();
        }
        if(!stack2.isEmpty()){
            num += stack2.pop();
        }
        if(mask){
            num++;
        }
        stack.push(num%10);
        if(num > 9){
            addTwoNumberHelper(stack1,stack2,true,stack);
        }else
            addTwoNumberHelper(stack1,stack2,false,stack);
    }

    /**
     * leetcode 423 reconstruct original digits from english
     * @param s
     * @return
     */
    public String originalDigits(String s) {
        // zero one two three four five six seven eight nine
        // z        w         u     v   x     s   g
        //      o        t
        //                                              n
        if(s == null || s.length() == 0)
            return null;
        int[] count = new int[10];//存放0-9出现的次数
        for(char c : s.toCharArray())
        {
            if(c == 'z') count[0]++;//'z'只有0才有
            if(c == 'w') count[2]++;//'w'只有2才有
            if(c == 'x') count[6]++;//'x'只有6才有
            if(c == 's') count[7]++;//6,7都有，最后只要count[7]-count[6]即可
            if(c == 'g') count[8]++;
            if(c == 'u') count[4]++;
            if(c == 'f') count[5]++;//5-4
            if(c == 'h') count[3]++;//3-8
            if(c == 'i') count[9]++;//9-5-6-8
            if(c == 'o') count[1]++;//1-2-0-4
        }
        //可以得到各自唯一的字母
        count[7] -= count[6];
        count[5] -= count[4];
        count[3] -= count[8];
        count[9] = count[9] - count[5] - count[6] - count[8];
        count[1] = count[1] - count[2] - count[0] - count[4];
        //开始进行统计
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i<10; ++i)
        {
            for(int j = 1; j<=count[i]; ++j)
            {
                sb.append(i);
            }
        }
        return sb.toString();
    }

    /**
     * leetcode 452 minimum number of arrows to burst balloons
     * @param points
     * @return
     */
    public int findMinArrowShots(int[][] points) {
        /**
         * one
         */
//        Arrays.sort(points, new Comparator<int[]>() {
//            @Override
//            public int compare(int[] o1, int[] o2) {
//                return o1[0]-o2[0];
//            }
//        });
//        int res = 0;
//        for (int i = 0;i<points.length;i++){
//            if(i == 0) res++;
//            else {
//                if(points[i][0] > points[i-1][1]){
//                    res++;
//                }else if(points[i][0] == points[i-1][1]){
//                    points[i][1] = points[i][0];
//                }else {
//                    points[i][0] = Integer.min(points[i][0],points[i-1][0]);
//                    points[i][1] = Integer.min(points[i][1],points[i-1][1]);
//                }
//            }
//        }
//        return res;
        /**
         * two
         */
        if (points.length == 0) return 0;
        Arrays.sort(points, (int[] a,int[] b)->a[1]-b[1]);
        int res = 1;
        int end = points[0][1];
        for (int i = 1;i<points.length;i++){
            if(points[i][0] > end){
                res++;
                end = points[i][1];
            }
        }
        return res;
    }

    /**
     * leetcode 498 diagonal traverse
     * @param matrix matrix
     * @return diagonal traverse array
     */
    public int[] findDiagonalOrder(int[][] matrix) {
        boolean up = true;
        if (matrix.length == 0) return new int[0];
        int[] res = new int[matrix.length * matrix[0].length];
        int i = 0;
        int j = 0;
        for (int k = 0; k < matrix.length * matrix[0].length; k++) {
            res[k] = matrix[i][j];
            if (up) {// 往右上走
                if ((i-1) >= 0 && (j+1) < matrix[0].length) {
                    i--;
                    j++;
                } else if ((j+1) < matrix[0].length) {
                    j++;
                    up = false;
                } else if ((i+1) < matrix.length) {
                    i++;
                    up = false;
                } else break;
            } else {// 往左下走
                if ((i+1) < matrix.length && (j-1) >= 0) {
                    i++;
                    j--;
                } else if ((i+1) < matrix.length) {
                    i++;
                    up = true;
                } else if ((j+1) < matrix[0].length) {
                    j++;
                    up = true;
                } else break;
            }
        }
        return res;
    }

    /**
     * 470 implement rand10() using rand7()
     * @return rand10()
     */
    public int rand10() {
        int a = rand7(), b = rand7();

        while (true) {
            if (b <= 4)
                return a;
            else if (a <= 4)
                return b + 3;
            a = rand7();
            b = rand7();
        }
    }
    private int rand7(){
        Random random = new Random();
        return random.nextInt(7);
    }

    /**
     * 424 longest repeating character replacement
     * 滑动窗口
     * @param s string
     * @param k most replace time
     * @return the length of a longest substring
     */
    public int characterReplacement(String s, int k) {
        if(s.length() == 0) return 0;
        int[] map = new int[26];
        int most = ++map[s.charAt(0)-'A'];
        int res = 1;
        int left = 0;
        for (int i = 1;i<s.length();i++){
            most = Math.max(most,++map[s.charAt(i)-'A']);
            if(most + k  < i - left +1){
                map[s.charAt(left++)-'A']--;
            }else {
                res = Math.max(res,i-left+1);
            }
        }
        return res;
    }

    /**
     * 436 find right interval
     * @param intervals interval[]
     * @return right interval
     */
    public int[] findRightInterval(Interval[] intervals) {
//        Map<Interval,Integer> map = new HashMap<>();
//        for (int i = 0;i<intervals.length;i++){
//            map.put(intervals[i],i);
//        }
//        Arrays.sort(intervals,(o1,o2) -> o1.start-o2.start);
//        int[] res = new int[intervals.length];
//        for (int i = 0;i<intervals.length;i++){
//            int left = i+1,right = intervals.length-1;
//            while (left <= right){
//                int mid = (left + right)/2;
//                if(intervals[mid].start < intervals[i].end)
//                    left = mid +1;
//                else if(intervals[mid].start > intervals[i].end)
//                    right = mid - 1;
//                else{
//                    left = mid;
//                    break;
//                }
//            }
//            res[map.get(intervals[i])] = left == intervals.length ? -1 : map.get(intervals[left]);
//        }
//        return res;

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = 0;i<intervals.length;i++){
            max = Math.max(max,intervals[i].start);
            min = Math.min(min,intervals[i].start);
        }
        int[] bucket = new int[max-min+1];
        Arrays.fill(bucket,-1);
        for (int i=0;i<intervals.length;i++){
            bucket[intervals[i].start-min] = i;
        }
        int[] ans = new int[intervals.length];
        for (int i=0;i<intervals.length;i++){
            int target = intervals[i].end - min;
            if(target >= bucket.length){
                ans[i] = -1;
                continue;
            }
            while (bucket[target] == -1)
                target++;
            ans[i] = target;
        }
        return ans;
    }

    /**
     * 435 non-overlapping intervals
     * @param intervals Interval[]
     * @return minimum number of interval you need to remove to make the rest of intervals non-overlapping
     */
    public int eraseOverlapIntervals(Interval[] intervals) {
        if(intervals.length < 2) return 0;
        Arrays.sort(intervals, new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                if(o1.start == o2.start)
                    return o1.end - o2.end;
                return o1.start-o2.start;
            }
        });
        int min = 0;
        Interval pre = intervals[0];
        for (int i = 1;i<intervals.length;i++){
            if(pre.end <= intervals[i].start)
                pre = intervals[i];
            else {
                if(pre.end > intervals[i].end){
                    pre = intervals[i];
                }
                min++;
            }
        }
        return min;
    }

    private static class Interval {
        int start;
        int end;
        Interval(){ start = 0; end = 0; }
        Interval(int s, int e) { start = s; end = e; }
    }

    /**
     * 491 increasing sub sequences
     * @param nums array
     * @return List
     */
    public List<List<Integer>> findSubsequences(int[] nums) {
        Set<List<Integer>> set = new HashSet<>();
        findSubsequencesHelper(new ArrayList<>(),0,nums,set);
        return new ArrayList<>(set);
    }
    private void findSubsequencesHelper(List<Integer> list,int index,int[] num,Set<List<Integer>> set){
        if(list.size() > 1) {
            set.add(new ArrayList<>(list));
        }
        for (int i = index;i<num.length;i++){
            if(list.size() > 0 && num[i] < list.get(list.size()-1)) continue;
            list.add(num[i]);
            findSubsequencesHelper(list,i+1,num,set);
            list.remove(list.size()-1);
        }
    }

    /**
     * 452 132 pattern
     */
    public boolean find132pattern(int[] nums) {
        if (nums == null || nums.length < 3) return false;
        int third = Integer.MIN_VALUE,len = nums.length;
        Stack<Integer> stack = new Stack<>();
        for (int i = len-1;i>=0;i--) {
            if (nums[i] < third) return true;
            while (!stack.isEmpty() && stack.peek() < nums[i]) {
                third = stack.pop();
            }
            stack.push(nums[i]);
        }
        return false;
    }

    public static void main(String[] args){
        Solution solution = new Solution();
    }

}