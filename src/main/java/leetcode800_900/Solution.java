package leetcode800_900;

import leetcode0_100.ListNode;
import org.junit.Test;

import javax.sound.midi.Soundbank;
import java.util.*;

public class Solution {

    /**
     * 836 rectangle overlap
     * @param rec1
     * @param rec2
     * @return
     */
    public boolean isRectangleOverlap(int[] rec1, int[] rec2) {
        return !(rec1[2] <= rec2[0] ||   // left
                rec1[3] <= rec2[1] ||   // bottom
                rec1[0] >= rec2[2] ||   // right
                rec1[1] >= rec2[3]);    // top
    }

    /**
     * 849 maximize distance to closest person
     * @param seats
     * @return
     */
    public int maxDistToClosest(int[] seats) {
        int pre = -1;
        int max = -1;
        if(seats[0] == 0){
            pre = -2;
        }
        for(int i  = 0,len = seats.length; i<len; i++){
            if(seats[i] == 1 && pre != -1){
                if(pre == -2){
                    max = i;
                }else {
                    max = (i-1-pre)/2+1 >max? (i-1-pre)/2+1: max;
                }
                pre = -1;
            }
            if(seats[i] == 0 && pre == -1){
                pre = i;
            }
        }
        if(seats[seats.length-1] == 0){
            max = seats.length-pre > max? seats.length-pre:max;
        }
        return max;
    }

    /**
     * 840 magic squares in grid
     * @param grid matrix
     * @return
     */
    public int numMagicSquaresInside(int[][] grid) {
        int row = grid.length;
        int colum = grid[0].length;
        int count = 0;
        for(int i =0;i<row-2;i++){
            for(int j =0;j<colum-2;j++){
                int a1 = grid[i][j],a2=grid[i][j+1],a3=grid[i][j+2],
                        b1=grid[i+1][j],b2=grid[i+1][j+1],b3=grid[i+1][j+2],
                        c1=grid[i+2][j],c2=grid[i+2][j+1],c3=grid[i+2][j+2];
                if(a1>9 || a2>9 || a3>9 || b1>9 || b2>9 || b3>9 || c1>9 || c2>9 || c3>9){
                    continue;
                }
                if(a1+a2+a3 == 15 && a1+a2+a3 == b1+b2+b3 && b1+b2+b3 == c1+c2+c3 && c1+c2+c3 == a1+b1+c1
                        && a1+b1+c1 == a2+b2+c2 && a2+b2+c2 == a3+b3+c3
                        && a3+b3+c3 == a1+b2+c3 && a1+b2+c3 == a3+b2+c1){
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * 859 buddy strings
     * @param A
     * @param B
     * @return
     */
    public boolean buddyStrings(String A, String B) {
        if(A.length()!=B.length()) return false;
        if(A.equals(B)){
            if(A.length()>26)
                return true;
            Set<Character> set = new HashSet<>();
            for(char c : A.toCharArray()){
                if(set.contains(c))
                    return true;
                else set.add(c);
            }
            return false;
        }
        char a1='1';char a2='2';char b1='3';char b2='4';int mask = 0;
        for(int i = 0;i<A.length();i++){
            if(A.charAt(i) != B.charAt(i)&&mask==0){
                a1 = A.charAt(i);
                b1 = B.charAt(i);
                mask++;
            }else if(mask == 1 && A.charAt(i) != B.charAt(i)){
                a2 = A.charAt(i);
                b2 = B.charAt(i);
                mask++;
            }else if(mask ==2 && A.charAt(i) != B.charAt(i)){
                return false;
            }
        }
        if(a1 == b2 && a2 == b1 && mask == 2)
            return true;
        return false;
    }

    /**
     * 807 max increase to keep skyline
     * medium
     * @param grid
     * @return
     */
    public int maxIncreaseKeepingSkyline(int[][] grid) {
        int len = grid.length;
        int[] top = new int[len];
        int[] left = new int[len];
        for(int i = 0;i<len;i++){
            int maxTop = grid[0][i];
            int maxLeft = grid[i][0];
            for(int j = 1;j<len;j++){
                if(grid[i][j] > maxLeft){
                    maxLeft = grid[i][j];
                }
                if(grid[j][i] > maxTop){
                    maxTop = grid[j][i];
                }
            }
            top[i] = maxTop;
            left[i] = maxLeft;
        }
        int count = 0;
        for(int i = 0;i<len;i++){
            for(int j = 0;j<len;j++){
                count += Integer.min(top[i],left[j])-grid[i][j];
            }
        }
        return count;
    }

    /**
     * 861 score after flipping matrix
     * @param A
     * @return
     */
    public int matrixScore(int[][] A) {

//        for(int i = 0;i<A.length;i++){
//            if(A[i][0] == 0){
//                for(int j = 0;j<A[i].length;j++){
//                    A[i][j] = A[i][j] == 0?1:0;
//                }
//            }
//        }
//        int max = A.length;
//        for(int i = 1;i<A[0].length;i++){
//            int count = 0;
//            for(int j = 0;j<max;j++){
//                count += A[j][i];
//            }
//            if(count < (double)max/2){
//                for(int j = 0;j<max;j++){
//                    A[j][i] = A[j][i] == 0?1:0;
//                }
//            }
//        }
//        int res = 0;
//        for(int i = 0;i<max;i++){
//            int temp = A[i][0];
//            for(int j = 1;j<A[0].length;j++){
//                temp = A[i][j]|(temp<<1);
//            }
//            res += temp;
//        }
//        return res;

        int res = 0;
        for(int i = 0;i<A[0].length;i++){
            int count = 0;
            for(int j = 0;j<A.length;j++){
                count += A[j][0]^A[j][i];
            }
            res += Integer.max(count,A.length-count)*(1<<(A[0].length-i-1));
        }
        return res;
    }

    /**
     * 841 keys and rooms
     * @param rooms
     * @return
     */
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        int[] temp = new int[rooms.size()];
        visitAllRoomsHelper(temp,0,rooms);
        for (int i : temp){
            if(i == 0)
                return false;
        }
        return true;
    }

    public void visitAllRoomsHelper(int[] temp,int target,List<List<Integer>> rooms){
        if(temp[target] == 1){
            return;
        }
        temp[target] = 1;
        for (int i : rooms.get(target)){
            visitAllRoomsHelper(temp,i,rooms);
        }
    }

    /**
     * 856 score of parentheses
     * 不仅将parentheses压入stack中，还将当前数子压stack中
     * medium
     * @param S
     * @return
     */
    public int scoreOfParentheses(String S) {
        int sum = 0;
        Stack<String> stack = new Stack<>();
        for (int i = 0,len = S.length();i<len;i++){
            if(S.charAt(i) == '('){
                stack.push("(");
            }else if(S.charAt(i) == ')'){
                String c = stack.peek();
                if(c.equals("(")){
                    stack.pop();
                    stack.push("1");
                }else {
                    int temp = 0;
                    while (!stack.peek().equals("(")){
                        temp += Integer.parseInt(stack.pop());
                    }
                    stack.pop();
                    stack.push(String.valueOf(2*temp));
                }
            }
        }
        while (!stack.empty()){
            sum += Integer.parseInt(stack.pop());
        }
        return sum;
    }

    /**
     * 811
     * @param cpdomains
     * @return
     */
    public static List<String> subdomainVisits(String[] cpdomains) {
        List<String> list = new ArrayList();
        Map<String,String> map = new HashMap<>();
        for(String str : cpdomains){
            String[] strs = str.split(" ");

            if(strs.length>1){
                String[] domains = strs[1].split("\\.");
                String d = strs[1];
                if(!map.containsKey(d)){
                    map.put(d,strs[0]);
                }else {
                    int i = Integer.parseInt(map.get(d));
                    int j = Integer.parseInt(strs[0]);
                    map.put(d,String.valueOf(i+j));
                }
                for(int k = 0 ;k<domains.length-1;k++){
                    d = d.substring(domains[k].length()+1,d.length());
                    if(!map.containsKey(d)){
                        map.put(d,strs[0]);
                    }else {
                        int i = Integer.parseInt(map.get(d));
                        int j = Integer.parseInt(strs[0]);
                        map.put(d,String.valueOf(i+j));
                    }
                }
            }
        }
        Iterator<Map.Entry<String,String>> entries = map.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry<String,String> entry = entries.next();
            String s = entry.getValue()+" "+entry.getKey();
            System.out.println(s);
            list.add(s);
        }
        return list;
    }

    /**
     * 819
     * @param p
     * @param banned
     * @return
     */
    public static String mostCommonWord(String p, String[] banned) {
        Map<String,Integer> map = new HashMap<>();
        String s = "";
        for(int i = 0;i<p.length();i++){
            if((p.charAt(i)>='a'&&p.charAt(i)<='z')||(p.charAt(i)>='A'&&p.charAt(i)<='Z')){
                s=s+p.charAt(i);
            }else{
                if(s != ""){
                    s=s.toLowerCase();
                    if(map.containsKey(s)){
                        map.put(s,map.get(s)+1);
                    }else{
                        map.put(s,1);
                    }
                }
                s = "";
            }
        }
        if(s != ""){
            s=s.toLowerCase();
            if(map.containsKey(s)){
                map.put(s,map.get(s)+1);
            }else{
                map.put(s,1);
            }
        }
        for(int i = 0;i<banned.length;i++){
            if(map.containsKey(banned[i])){
                map.remove(banned[i]);
            }
        }
        Iterator iterator = map.keySet().iterator();
        String r ="";
        int max =0;
        while (iterator.hasNext()) {
            String key = (String) iterator.next();
            if (map.get(key)>max) {
                max = map.get(key);
                r = key;
            }
        }
        return r;
    }

    /**
     * 817 linked list components
     * @param head
     * @param G
     * @return
     */
    public int numComponents(ListNode head, int[] G) {
        Set<Integer> set = new HashSet<>();
        for (int i: G){
            set.add(i);
        }
        ListNode node = head;
        int sum = 0;
        while (node != null){
            if(set.contains(node.val)){
                node = node.next;
                while (node != null && set.contains(node.val)){
                    node = node.next;
                }
                sum++;
            }else {
                node = node.next;
            }
        }
        return sum;
    }

    /**
     * 858 mirror reflection
     * medium
     * @param p
     * @param q
     * @return
     */
    public int mirrorReflection(int p, int q) {
        int dist = 0;
        int d = 0;
        int remain;
        while(true)
        {
            d++;
            dist += q;
            remain = dist%(2*p);

            if(remain == p)
            {
                if(d%2 == 1)
                    return 1;
                else
                    return 2;
            }
            if(remain == 0)
                return 0;
        }
    }

    /**
     * 869 reorder power of 2
     * because of 1 < N < 10^9 ,the number that power of 2 is 32. so judging the N whether if can be changed to that in the int[]
     * @param N
     * @return
     */
    public boolean reorderedPowerOf2(int N) {
        int[] A = count(N);
        for (int i = 0; i < 31; ++i)
            if (Arrays.equals(A, count(1 << i)))
                return true;
        return false;
    }

    // Returns the count of digits of N
    // Eg. N = 112223334, returns [0,2,3,3,1,0,0,0,0,0]
    public int[] count(int N) {
        int[] ans = new int[10];
        while (N > 0) {
            ans[N % 10]++;
            N /= 10;
        }
        return ans;
    }

    /**
     * 846 hand of straights
     * @param hand
     * @param W
     * @return
     */
    public boolean isNStraightHand(int[] hand, int W) {
        TreeMap<Integer, Integer> count = new TreeMap();
        for (int card: hand) {
            if (!count.containsKey(card))
                count.put(card, 1);
            else
                count.replace(card, count.get(card) + 1);
        }

        while (count.size() > 0) {
            int first = count.firstKey();
            for (int card = first; card < first + W; ++card) {
                if (!count.containsKey(card)) return false;
                int c = count.get(card);
                if (c == 1) count.remove(card);
                else count.replace(card, c - 1);
            }
        }

        return true;
    }

    /**
     * short encode of words
     * @param words string[]
     * @return shortest length
     */
    public int minimumLengthEncoding(String[] words) {
        int sum = 0;
        Tire tire = new Tire();
        boolean hasnext = false;
        for (String word : words){
            hasnext = false;
            Tire cur = tire;
            for (int i = word.length()-1;i>=0;i--){
                if(cur.next[word.charAt(i)-'a'] == null){
                    cur.next[word.charAt(i)-'a'] = new Tire();
                }
                cur = cur.next[word.charAt(i)-'a'];
                if(cur.word != null){
                    sum -= (cur.word.length()+1);
                    cur.word = null;
                }
            }
            for (int i = 0;i<26;i++){
                if(cur.next[i] != null){
                    hasnext = true;
                    break;
                }
            }
            if(!hasnext){
                cur.word = word;
                sum += (cur.word.length()+1);
            }
        }
        return sum;
    }

    class Tire{
        String word;
        Tire[] next;
        public Tire(){
            word=null;
            next=new Tire[26];
        }
    }

    /**
     * 816 ambiguous coordinates
     * @param S original string
     * @return List
     */
    public List<String> ambiguousCoordinates(String S) {
        List<String> list = new ArrayList<>();
        S = S.substring(1,S.length()-1);
        for (int i = 1;i<S.length();i++){
            String s1 = S.substring(0,i);
            String s2 = S.substring(i,S.length());
            List<String> list1 = ambiguousCoordinatesHelper(s1);
            List<String> list2 = ambiguousCoordinatesHelper(s2);
            for (String s : list1){
                for (String s3 : list2){
                    list.add("("+s+", "+s3+")");
                }
            }
        }
        return list;
    }

    private List<String> ambiguousCoordinatesHelper(String s1){
        List<String> list = new ArrayList<>();
        if(s1.length() == 1){
            list.add(s1);
            return list;
        }
        for (int i = 1;i<=s1.length();i++){
            String pre = s1.substring(0,i);
            String last = s1.substring(i,s1.length());
            if(pre.length() != 1 && (pre.matches("^0+$") || pre.substring(0,1).equals("0"))){
                continue;
            }
            if(last.matches("^0+$") || last.matches("^\\d+0$"))
                continue;
            if(last.equals(""))
                list.add(pre);
            else
                list.add(pre+"."+last);
        }
        return list;
    }

    /**
     * 835 image overlap
     * @param A image a
     * @param B image b
     * @return maximum overlap
     */
    public int largestOverlap(int[][] A, int[][] B) {
        int N = A.length;
        int[][] count = new int[2*N+1][2*N+1];
        for (int i = 0; i < N; ++i)
            for (int j = 0; j < N; ++j)
                if (A[i][j] == 1)
                    for (int i2 = 0; i2 < N; ++i2)
                        for (int j2 = 0; j2 < N; ++j2)
                            if (B[i2][j2] == 1)
                                count[i-i2 +N][j-j2 +N] += 1;
        int ans = 0;
        for (int[] row: count)
            for (int v: row)
                ans = Math.max(ans, v);
        return ans;
    }

    /**
     * 896 monotonic array
     * @param A array
     * @return true if and only if the given array A is a monotonic
     */
    public boolean isMonotonic(int[] A) {
        int type = 0;
        for (int i = 1;i<A.length;i++){
            if(type == 0){
                if(A[i] > A[i-1]) type = 1;
                else if(A[i] < A[i-1]) type = -1;
            }else {
                if(A[i] > A[i-1] && type == -1) return false;
                else if(A[i] < A[i-1] && type == 1) return false;
            }
        }
        return true;
    }

    /**
     * 838 push dominoes
     * @param dominoes a vertical dominoes
     * @return final state
     */
    public String pushDominoes(String dominoes) {
        char[] chars = dominoes.toCharArray();
        int count = 0,left = 0;
        for (int i = 0;i<chars.length;i++){
            char c = chars[i];
            if(c == '.'){
                if(count == 0){
                    left = i;
                }
                count++;
            }else {
                if(count != 0){
                    char c1 = left-1>=0 ? chars[left-1] : 'E';
                    if((c1 == 'E' && c == 'L') || (c1 == c)){
                        for (int j = left;j<i;j++){
                            chars[j] = c;
                        }
                    }else if(c1 == 'R' && c == 'L'){
                        int j = i-1;
                        while (left < j){
                            chars[left++] = c1;
                            chars[j--] = c;
                        }
                    }
                }
                count = 0;
            }
        }
        if(count != 0 && left-1 >=0 && chars[left-1] == 'R'){
            while (left < chars.length){
                chars[left++] = 'R';
            }
        }
        return new String(chars);
    }

    /**
     * 875 koko eating bananas
     * binary search
     * @param piles N piles of bananas
     * @param H H hours
     * @return minimum integer K
     */
    public int minEatingSpeed(int[] piles, int H) {
        Arrays.sort(piles);
        int left = 1;
        int right = piles[piles.length - 1];
        while (left < right){
            int mid = (left+right)/2;
            if(minEatingSpeed(piles,mid,H)){
                right = mid;
            }else {
                left = mid + 1;
            }
        }
        return left;
    }
    private boolean minEatingSpeed(int[] piles,int mid,int h){
        int time = 0;
        for (int i : piles){
            time += (i-1)/mid + 1;
        }
        return time <= h;
    }

    /**
     * 873 length of longest fibonacci sub_sequence
     * @param A array
     * @return length of longest
     */
    public int lenLongestFibSubseq(int[] A) {
//        int max = 0;
//        for (int i = 0;i<A.length;i++){
//            for (int j = i+1;j<A.length;j++){
//                int target = A[i] + A[j],left = j+1,right =A.length-1,count = 2,pre = j;
//                while (left <= right){
//                    int mid = (left+right)/2;
//                    if(A[mid] > target) right = mid - 1;
//                    else if(A[mid] < target) left = mid + 1;
//                    else {
//                        count++;
//                        target += A[pre];
//                        pre = mid;
//                        left = mid + 1;
//                        right = A.length-1;
//                    }
//                }
//                max = Math.max(max,count);
//            }
//        }
//        return max >= 3 ? max : 0;

        int n =A.length;
        Map<Integer,Integer> map = new HashMap<>();
        for (int i = 0;i<n;i++){
            map.put(A[i],i);
        }
        Map<Integer,Integer> longest = new HashMap<>();
        int ans = 0;
        for (int i = 0;i<n;i++){
            for (int j = 0;j<i;j++){
                int index = map.getOrDefault(A[i]-A[j],-1);
                if(index >=0 && index <n){
                    // encoding tuple (i,j) as integer (i*n+j)
                    int temp = longest.getOrDefault(index * n + j,2)+1;
                    longest.put(j*n+i,temp);
                    ans = Math.max(temp,ans);
                }
            }
        }
        return ans;
    }

    /**
     * 802 find eventual safe states
     * @param graph a directed graph
     * @return all eventual safe nodes
     */
    public List<Integer> eventualSafeNodes(int[][] graph) {
        List<Integer> res = new ArrayList<>();
        if(graph == null || graph.length == 0) return res;
        int[] temp = new int[graph.length];
        for (int i = 0;i<graph.length;i++){
            if(eventualSafeNodesHelper(graph,temp,i)) res.add(i);
        }
        return res;
    }
    private boolean eventualSafeNodesHelper(int[][] graph,int[] temp,int start){
        if(temp[start] != 0) return temp[start] == 1;
        temp[start] = 2;
        for (int i : graph[start]){
            if(!eventualSafeNodesHelper(graph,temp,i)) return false;
        }
        temp[start] = 1;
        return true;
    }

    /**
     * 870 advantage shuffle
     * @param A Array A
     * @param B Array B
     * @return any permutation of A that maximizes its advantage with respect to B.
     */
    public int[] advantageCount(int[] A, int[] B) {
         int len = A.length;
         int[] result = new int[len];
         Arrays.sort(A);
         PriorityQueue<int[]> queue = new PriorityQueue<>(new Comparator<int[]>() {
             @Override
             public int compare(int[] o1, int[] o2) {
                 return o2[0]-o1[0];
             }
         });
         for (int i = 0;i<len;i++){
             queue.offer(new int[]{B[i],i});
         }
         int lo = 0,hi = len-1;
         while (!queue.isEmpty()){
             int[] t = queue.poll();
             if(A[hi] > t[0]) result[t[1]] = A[hi--];
             else result[t[1]] = A[lo++];
         }
         return result;
    }

    /**
     * 900 RLE iterator
     */
    class RLEIterator {
        Queue<int[]> queue;
        public RLEIterator(int[] A) {
            queue = new LinkedList<>();
            for (int i = 0;i<A.length;i+=2){
                queue.offer(new int[]{A[i],A[i+1]});
            }
        }

        public int next(int n) {
            while (!queue.isEmpty()){
                int[] temp = queue.peek();
                if(temp[0] < n){
                    queue.poll();
                    n -= temp[0];
                }else if(temp[0] == n){
                    queue.poll();
                    return temp[1];
                }else {
                    temp[0] -= n;
                    return temp[1];
                }
            }
            return -1;
        }
    }



    public static void main(String[] args){
        Solution solution = new Solution();
        solution.advantageCount(new int[]{12,24,8,32},new int[]{13,25,32,11});
    }
}
