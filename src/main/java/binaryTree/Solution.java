package binaryTree;

import org.omg.PortableInterceptor.INACTIVE;

import java.util.*;

public class Solution {

    /**
     * leetcode 669
     * @param root
     * @param L
     * @param R
     * @return
     */
    public static TreeNode trimBST(TreeNode root, int L, int R) {
        if(root == null){
            return null;
        }
        if(root.val>R){
            root = trimBST(root.left,L,R);
        }else if(root.val<L){
            root = trimBST(root.right,L,R);
        }else if(root.left != null){

            root.left = trimBST(root.left,L,R);
        }else if(root.right != null){
            root.right = trimBST(root.right,L,R);
        }
        return root;
    }

    /**
     * leetcode 606
     * @param t
     * @return
     */
    public static String tree2str(TreeNode t) {
        String result = "";
        result = str(t,result);
        return result.substring(0,result.length()-1);
    }

    public static String str(TreeNode t,String s){
        if(t == null){
            s = s+")";
            return s;
        }
        if(t.left == null && t.right == null){
            return s + t.val+")";
        }
        if(t.right == null){
            s = str(t.left,s);
            return s + t.val+")";
        }else {
            s = s + t.val + "(";
        }
        s = str(t.left,s);
        s = s + "(";
        s = str(t.right,s);
        s = s + ")";
        return s;
    }

    /**
     * leetcode 538
     * @param root
     * @return
     */
    public TreeNode convertBST(TreeNode root) {
        int sum = 0;
        Stack<TreeNode> stack = new Stack<>();
        stack = putStack(root,stack);
        while (!stack.empty()){
            sum = sum + stack.peek().val;
            stack.peek().val = sum;
            stack.pop();
        }
        return root;
    }


    public Stack putStack(TreeNode root,Stack stack){
        if(root == null){
            return stack;
        }
        stack = putStack(root.left,stack);
        stack.push(root);
        stack = putStack(root.right,stack);
        return stack;
    }

    /**
     * leetcode 107 binary tree level order traversal II
     * @param root treenode
     * @return bottom-up level order
     */
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> lists = new ArrayList<>();
        if(root == null){
            return lists;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int i = 1;
        int j = 0;
        List<Integer> list = new ArrayList<>();
        while (!queue.isEmpty()){
            TreeNode treeNode = queue.poll();
            i--;
            list.add(treeNode.val);
            if(treeNode.left != null){
                queue.add(treeNode.left);
                j++;
            }
            if(treeNode.right != null){
                queue.add(treeNode.right);
                j++;
            }
            if(i <= 0){
                List<Integer> arr = new ArrayList<>(list);
                lists.add(arr);
                i = j;
                j = 0;
                list.removeAll(list);
            }
        }
        Collections.reverse(lists);
        return lists;
    }

    /**
     * leetcode 257 binary tree paths
     * @param root treenode
     * @return
     */
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> list = new ArrayList<>();
        if(root == null){
            return list;
        }
        String str = "";
        paths(root,list,str);
        return list;
    }

    public void paths(TreeNode root,List<String> list,String str){
        if(root == null){
            return;
        }
        if(root.left == null&&root.right == null){
            str = str+root.val;
            list.add(str);
            return;
        }
        paths(root.left,list,str+root.val+"->");
        paths(root.right,list,str+root.val+"->");
    }

    /**
     * leetcode 671 second minimum node in binary tree
     * @param root
     * @return
     */
    public int findSecondMinimumValue(TreeNode root) {
        if (root == null) {
            return -1;
        }
        if (root.left == null && root.right == null) {
            return -1;
        }

        int left = root.left.val;
        int right = root.right.val;

        // if value same as root value, need to find the next candidate
        if (root.left.val == root.val) {
            left = findSecondMinimumValue(root.left);
        }
        if (root.right.val == root.val) {
            right = findSecondMinimumValue(root.right);
        }

        if (left != -1 && right != -1) {
            return Math.min(left, right);
        } else if (left != -1) {
            return left;
        } else {
            return right;
        }
    }

    /**
     * leetcode 101 symmetric tree
     * @param root
     * @return
     */
    public boolean isSymmetric(TreeNode root) {
//        if(root == null) return true;
//        return symmetric(root.left,root.right);
        if(root == null) return true;
        List<TreeNode> q = new LinkedList<>();
        q.add(root.left);

        List<TreeNode> p = new LinkedList<>();
        p.add(root.right);
        while (!q.isEmpty()&&!p.isEmpty()){
            TreeNode treeNode1 = ((LinkedList<TreeNode>) q).poll();
            TreeNode treeNode2 = ((LinkedList<TreeNode>) p).poll();
            if(treeNode1 == null && treeNode2 == null)
                continue;
            if(treeNode1 == null || treeNode2 == null)
                return false;
            if(treeNode1.val != treeNode2.val)
                return false;
            q.add(treeNode1.left);
            q.add(treeNode1.right);
            p.add(treeNode2.right);
            p.add(treeNode2.left);
        }
        return true;
    }

    public boolean symmetric(TreeNode left, TreeNode right){
        if(left == null && right == null) return true;
        if(left == null || right == null) return false;
        if(left.val != right.val) return false;
        return symmetric(left.left,right.right)&&symmetric(left.right,right.left);
    }

    /**
     * leetcode 253 lowest common ancestor of a binary search tree
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(p.val>q.val){
            TreeNode node = p;
            p = q;
            q = node;
        }
        if(root.val>q.val){
            return lowestCommonAncestor(root.left,p,q);
        }else if(root.val<p.val){
            return lowestCommonAncestor(root.right,p,q);
        }else if(root.val == p.val || root.val == q.val){
            return root;
        }else{
            return root;
        }
    }

    /**
     * leetcode 572 subtree of another tree
     * @param s
     * @param t
     * @return
     */
    public boolean isSubtree(TreeNode s, TreeNode t) {

        if(s == null){
            return false;
        }
        if(s.val == t.val){
            if(subTree(s,t)){
                return true;
            }
        }
        if(isSubtree(s.left,t) || isSubtree(s.right,t)){
            return true;
        }else {
            return false;
        }
    }

    public boolean subTree(TreeNode s, TreeNode t){
        if(s == null && t == null){
            return true;
        }else if(s == null || t == null){
            return false;
        }
        if(s.val != t.val){
            return false;
        }
        return subTree(s.left,t.left)&&subTree(s.right,t.right);
    }

    /**
     * leetcode 437 path sum III
     * @param root
     * @param sum
     * @return
     */
    public int pathSum(TreeNode root, int sum) {
        if(root == null) return 0;
        return pathSum3(root,sum)+pathSum(root.left,sum)+pathSum(root.right,sum);
    }

    public int pathSum3(TreeNode root, int sum){
        int res = 0;
        if(root == null)
            return res;
        if(root.val == sum){
            res++;
        }
        res += pathSum3(root.left,sum-root.val);
        res += pathSum3(root.right,sum-root.val);
        return res;
    }

    /**
     * leetcode 101 balanced binary tree
     * @param root
     * @return
     */
    public boolean isBalanced(TreeNode root) {
        if(root == null) return true;
        return balanced(root) != -1;
    }

    public int balanced(TreeNode root){
        if(root == null)
            return 1;
        int left = balanced(root.left);
        int right = balanced(root.right);
        if(left == -1 || right == -1){
            return -1;
        }
        if(Math.abs(left-right) < 2){
            return Math.max(left,right)+1;
        }
        return -1;
    }

    /**
     * leetcode 501 find mode in binary search tree
     * @param root
     * @return
     */
    Integer prev = null;
    int count = 1;
    int max = 0;
    public int[] findMode(TreeNode root) {
        if (root == null) return new int[0];

        List<Integer> list = new ArrayList<>();
        traverse(root, list);

        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); ++i) res[i] = list.get(i);
        return res;
    }

    private void traverse(TreeNode root, List<Integer> list) {
        if (root == null) return;
        traverse(root.left, list);
        if (prev != null) {
            if (root.val == prev)
                count++;
            else
                count = 1;
        }
        if (count > max) {
            max = count;
            list.clear();
            list.add(root.val);
        } else if (count == max) {
            list.add(root.val);
        }
        prev = root.val;
        traverse(root.right, list);
    }

    /**
     * leetcode 112 path sum
     * @param root
     * @param sum
     * @return
     */
    public boolean hasPathSum(TreeNode root, int sum) {
        return pathSum(root,sum,0);
    }

    public boolean pathSum(TreeNode root, int sum, int current){
        if(root == null){
            return false;
        }
        current += root.val;
        if(root.left == null &&root.right == null && current == sum){
            return true;
        }
        return pathSum(root.left,sum,current)||pathSum(root.right,sum,current);
    }

    /**
     * leetcode 111 minimum depth of binary tree
     * @param root
     * @return
     */
    int min = Integer.MAX_VALUE;
    public int minDepth(TreeNode root) {
        if(root == null){
            return 0;
        }
        return minDepthHelper(root,0);
    }

    public int minDepthHelper(TreeNode treeNode,int curr){
        if(treeNode.left == null && treeNode.right == null){
            return Integer.min(min,curr+1);
        }
        int min1 = Integer.MAX_VALUE;
        if(treeNode.left != null)
            min1 = minDepthHelper(treeNode.left,curr+1);
        int min2 = Integer.MAX_VALUE;
        if(treeNode.right != null)
            min2 = minDepthHelper(treeNode.right,curr+1);
        return Integer.min(min1,min2);
    }

    /**
     * leetcode 687 longest univalue path
     * @param root
     * @return
     */
    int longestUnivaluePath = 0;
    public int longestUnivaluePath(TreeNode root) {
        if(root == null)
            return 0;
        longestUnivaluePathHepler(root);
        return longestUnivaluePath;
    }

    public int longestUnivaluePathHepler(TreeNode root){
        if(root == null)
            return 0;
        int left = longestUnivaluePathHepler(root.left);
        int right = longestUnivaluePathHepler(root.right);
        int arrowleft = 0; int arrowright = 0;
        if(root.left != null && root.left.val == root.val){
            arrowleft += left+1;
        }
        if(root.right != null && root.right.val == root.val){
            arrowright += right+1;
        }
        longestUnivaluePath = Integer.max(longestUnivaluePath,arrowleft+arrowright);
        return Integer.max(arrowleft,arrowright);
    }

    /**
     * leetcode 589 n-ary tree preorder traversal
     * @param root
     * @return
     */
    public List<Integer> preorder(Node root) {
        List<Integer> list = new ArrayList<>();
        preoderHepler(list,root);
        return list;
    }

    public void  preoderHepler(List<Integer> list, Node node){
        if(node == null){
            return;
        }
        list.add(node.val);
        for(Node node1 : node.children){
            preoderHepler(list,node1);
        }
    }

    /**
     * leetcode 429 n-ary tree level order traversal
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder(Node root) {
//        List<List<Integer>> lists = new ArrayList<>();
//        Queue<Node> queue = new ArrayDeque<>();
//        if(root == null)
//            return lists;
//        queue.add(root);
//        while (!queue.isEmpty()){
//            List<Integer> list = new ArrayList<>();
//            int len = queue.size();
//            for(int i = 0;i<len;i++){
//                Node node = queue.poll();
//                list.add(node.val);
//                for(Node n : node.children){
//                    queue.offer(n);
//                }
//            }
//            lists.add(list);
//        }
//        return lists;
        List<List<Integer>> lists = new ArrayList<>();
        traverse2(root,lists,0);
        return lists;
    }

    public void traverse2(Node node,List<List<Integer>> lists, int level){
        if(node == null){
            return;
        }
        while (lists.size() <= level) lists.add(new ArrayList<>());
        lists.get(level).add(node.val);
        for(Node node1 : node.children){
            traverse2(node1,lists,level+1);
        }
    }

    /**
     * leetcode 654 maximum binary tree
     * @param nums
     * @return
     */
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        /**
         * one
         */
//        TreeNode node = helper(0,nums.length-1,nums);
//        return node;
        /**
         * two
         */
        Queue<TreeNode> stack = new LinkedList<>();
        for(int i = 0;i<nums.length;i++){
            TreeNode node = new TreeNode(nums[i]);
            while (!stack.isEmpty() && stack.peek().val < nums[i]){
                node.left = ((LinkedList<TreeNode>) stack).pop();
            }
            if(!stack.isEmpty()){
                stack.peek().right = node;
            }
            ((LinkedList<TreeNode>) stack).push(node);
        }
        return ((LinkedList<TreeNode>) stack).removeLast();
    }

    public TreeNode helper(int left,int right,int[] nums){
        if(left>right){
            return null;
        }
        int max = left;
        for(int i = left+1;i<=right;i++){
            if(nums[i]>nums[max]){
                max = i;
            }
        }
        TreeNode node = new TreeNode(nums[max]);
        node.left =  helper(left,max-1,nums);
        node.right = helper(max+1,right,nums);
        return node;
    }

    /**
     * leetcode 814 binary tree pruning
     * @param root
     * @return
     */
    public TreeNode pruneTree(TreeNode root) {
        return pruneTreeHelper(root);
    }

    public TreeNode pruneTreeHelper(TreeNode node){
        if(node == null){
            return null;
        }
        if(node.left != null)
            node.left = pruneTreeHelper(node.left);
        if(node.right != null)
            node.right = pruneTreeHelper(node.right);
        if(node.val == 0 && node.left == null && node.right == null){
            return null;
        }
        return node;
    }

    /**
     * leetcode 513 find bottom left tree value
     * @param root
     * @return
     */
    public int findBottomLeftValue(TreeNode root) {
        /**
         * use queue
         */
//        Queue<TreeNode> queue = new ArrayDeque<>();
//        queue.add(root);
//        int count = 1;
//        TreeNode res = null;
//        while (!queue.isEmpty()){
//            int temp = 0;
//            for (int i = 0;i<count;i++){
//                TreeNode node = ((ArrayDeque<TreeNode>) queue).pop();
//                if(i == 0){
//                    res = node;
//                }
//                if(node.left != null){
//                    queue.add(node.left);
//                    temp++;
//                }
//                if(node.right != null){
//                    queue.add(node.right);
//                    temp++;
//                }
//            }
//            count = temp;
//        }
//        return res.val;
        /**
         * use recursion
         */
        findBottomLeftHelper(root,1);
        return res;
    }

    int res = 0;
    int depth = 0;
    public void findBottomLeftHelper(TreeNode root,int h){
        if(h>depth){
            res = root.val;
            depth = h;
        }
        if(root == null)
            return;
        if(root.left != null)
            findBottomLeftHelper(root.left,h+1);
        if(root.right != null)
            findBottomLeftHelper(root.right,h+1);
    }

    /**
     * leetcode 515 find largest value in each tree row
     * @param root
     * @return
     */
    public List<Integer> largestValues(TreeNode root) {
        /**
         * one
         */
//        Queue<TreeNode> queue = new ArrayDeque<>();
//        List<Integer> list = new ArrayList<>();
//        if(root == null)
//            return list;
//        queue.add(root);
//        int count = 1;
//        while (!queue.isEmpty()){
//            int temp = 0;
//            int max = Integer.MIN_VALUE;
//            for (int i = 0;i<count;i++){
//                TreeNode node = ((ArrayDeque<TreeNode>) queue).pop();
//                max = Integer.max(max,node.val);
//                if(node.left != null){
//                    queue.add(node.left);
//                    temp++;
//                }
//                if(node.right != null){
//                    queue.add(node.right);
//                    temp++;
//                }
//            }
//            count = temp;
//            list.add(max);
//        }
//        return list;
        /**
         * two
         */
        List<Integer> list = new LinkedList<>();
        largestHelper(list,0,root);
        return list;
    }

    public void largestHelper(List<Integer> list, int level,TreeNode node){
        if(node == null){
            return;
        }

        if(list.size() == level){
            list.add(node.val);
        }

        if(list.get(level)<node.val){
            list.set(level,node.val);
        }

        largestHelper(list,level+1,node.left);
        largestHelper(list,level+1,node.right);
    }

    /**
     * leetcode 508 most frequent subtree sum
     * @param root
     * @return
     */
    public int[] findFrequentTreeSum(TreeNode root) {
        Map<Integer,Integer> map = new HashMap<>();
        findFrequentSumHelper(root,map);
        int max = 0;
        int count = 0;
        for (Map.Entry entry : map.entrySet()){
            if(max == (int)entry.getValue()){
                count++;
            }else if(max < (int)entry.getValue()) {
                count = 1;
                max = (int)entry.getValue();
            }
        }
        int[] res = new int[count];
        int i = 0;
        for (Map.Entry entry : map.entrySet()){
            if((int)entry.getValue() == max){
                res[i++] = (int)entry.getKey();
            }
        }
        return res;
    }

    public int findFrequentSumHelper(TreeNode node,Map<Integer,Integer> map){
        if(node == null){
            return 0;
        }
        int sum = findFrequentSumHelper(node.left,map)+findFrequentSumHelper(node.right,map)+node.val;
        if(map.containsKey(sum)){
            map.put(sum,map.get(sum)+1);
        }else
            map.put(sum,1);
        return sum;
    }

    /**
     * leetcode 404
     * @param root
     * @return
     */
    public static int sumOfLeftLeaves(TreeNode root) {
        if(root == null){
            return 0;
        }
        return sum(root.left,0)+sum(root.right,1);
    }

    public static int sum(TreeNode root, int p){
        if(root == null){
            return 0;
        }
        if(root.left == null && root.right == null && p ==0){
            return root.val;
        }
        return sum(root.left,0) + sum(root.right,1);
    }

    /**
     * leetcode 94 binary tree inorder traversal
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        /**
         * recursion
         */
//        List<Integer> list = new LinkedList<>();
//        inorderTraversalHelper(root,list);
//        return list;
        /**
         * iterating
         */
//        List<Integer> list = new ArrayList<>();
//        Stack<TreeNode> stack = new Stack<>();
//        TreeNode node = root;
//        while (node != null || !stack.isEmpty()){
//            while (node != null){
//                stack.push(node.left);
//                node = node.left;
//            }
//            node = stack.pop();
//            list.add(node.val);
//            node = node.right;
//        }
//        return list;
        /**
         * morris traversal
         * space : O(1)
         * If current does not have left child
         *
         *     a. Add current’s value
         *
         *     b. Go to the right, i.e., current = current.right
         *
         * Else
         *
         *     a. In current's left subtree, make current the right child of the rightmost node
         *
         *     b. Go to this left child, i.e., current = current.left
         */
        List<Integer> list = new ArrayList<>();
        TreeNode node = root;
        TreeNode pre;
        while (node != null){
            if(node.left == null){
                list.add(node.val);
                node = node.right;
            }else {
                pre = node.left;
                while (pre.right != null){
                    pre = pre.right;
                }
                pre.right = node;
                TreeNode temp = node;
                node = node.left;
                temp.left = null;
            }
        }
        return list;
    }

    public void inorderTraversalHelper(TreeNode node,List<Integer> list){
        if(node == null)
            return;
        if(node.left != null){
            inorderTraversalHelper(node.left,list);
        }
        list.add(node.val);
        if(node.right != null){
            inorderTraversalHelper(node.right,list);
        }
    }

    /**
     * leetcode 865 smallest subtree with all deepest nodes
     * medium
     * 如果 root 为 nullptr，则直接返回 nullptr;
     * 如果 root 的左子树的深度等于 root 的右子树的深度，则返回 root;
     * 如果 root 的左子树的深度大于 root 的右子树的深度，则返回 root 的左子树的 mallest Subtree with all the Deepest Nodes;
     * 否则，root 的左子树的深度小于root 的右子树的深度，则返回 root 的右子树的 mallest Subtree with all the Deepest Nodes;
     * @param root
     * @return
     */
    /**
     * one
     */
//    TreeNode subtreeWithAllDeepestNode;
//    int subtreeWithAllDeepestMaxSum = 0;
//    public TreeNode subtreeWithAllDeepest(TreeNode root) {
//        subtreeWithAllDeepestHelper(root,0);
//        return subtreeWithAllDeepestNode;
//    }
//
//    public int subtreeWithAllDeepestHelper(TreeNode node,int i){
//        if(node == null){
//            return i;
//        }
//        int left = -1;
//        int right = -1;
//            left = subtreeWithAllDeepestHelper(node.left,i+1);
//            right = subtreeWithAllDeepestHelper(node.right,i+1);
//        if(left != -1 && right != -1 && left == right && left >= subtreeWithAllDeepestMaxSum){
//            subtreeWithAllDeepestNode = node;
//            subtreeWithAllDeepestMaxSum = left;
//        }
//        if(left > right)
//            return left;
//        return right;
//    }
    /**
     * two
     */
    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        if (root == null) return null;
        if (depth(root.left) == depth(root.right)) return root;
        if (depth(root.left) > depth(root.right)) return subtreeWithAllDeepest(root.left);
        return subtreeWithAllDeepest(root.right);
    }
    public int depth(TreeNode node) {
        if (node == null) return 0;
        return Math.max(depth(node.left), depth(node.right)) + 1;
    }

    /**
     * leetcode 655 print binary tree
     * @param root binary root
     * @return List<List<String>>
     */
    public List<List<String>> printTree(TreeNode root) {
        int depth = printTreeHelper(root,0);
        List<List<String>> lists = new ArrayList<>();
        int len = (int) Math.pow(2,depth)-1;
        for (int i = 0;i<depth;i++){
            List<String> list = new ArrayList<>();
            for (int j = 0;j<len;j++){
                list.add("");
            }
            lists.add(list);
        }
        printTreeHelper2(root,0,len-1,lists,0);
        return lists;
    }

    private void printTreeHelper2(TreeNode node,int left,int right,List<List<String>> lists,int currentDepth){
        if(node == null) return;
        List<String> list = lists.get(currentDepth);
        int temp = (left+right)/2;
        list.set(temp,node.val+"");
        printTreeHelper2(node.left,left,temp-1,lists,currentDepth+1);
        printTreeHelper2(node.right,temp+1,right,lists,currentDepth+1);
    }

    private int printTreeHelper(TreeNode node,int deepest){
        if(node == null){
            return deepest;
        }
        int left = printTreeHelper(node.left,deepest+1);
        int right = printTreeHelper(node.right,deepest+1);
        return Integer.max(left,right);
    }

    /**
     * leetcode 144 binary tree preorder traversal
     * iteratively
     * @param root
     * @return
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        /**
         * stack
         */
//        Stack<TreeNode> stack = new Stack<>();
//        List<Integer> list = new ArrayList<>();
//        if(root == null){
//            return list;
//        }
//        stack.push(root);
//        while (!stack.isEmpty()){
//            TreeNode node = stack.pop();
//            list.add(node.val);
//            if(node.left != null){
//                stack.push(node.left);
//            }
//            if(node.right != null){
//                stack.push(node.right);
//            }
//        }
//        return list;
        /**
         * morris
         */
        List<Integer> list = new ArrayList<>();
        TreeNode node = root;
        TreeNode pre;
        while (node != null){
            if(node.left == null){
                list.add(node.val);
                node = node.right;
            }else {
                pre = node.left;
                while (pre.right != null && pre.right != node){
                    pre = pre.right;
                }
                if(pre.right == null){
                    list.add(node.val);
                    pre.right = node;
                    node = node.left;
                }else {
                    pre.right = null;
                    node = node.right;
                }
            }
        }
        return list;
    }

    /**
     * leetcode 701 insert into a binary search tree
     * @param root
     * @param val
     * @return
     */
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if(root == null){
            return new TreeNode(val);
        }
        insertIntoBSTHelper(root,val);
        return root;
    }

    public void insertIntoBSTHelper(TreeNode node,int val){
        if(node.val > val){
            if(node.left == null){
                node.left = new TreeNode(val);
                return;
            }
            insertIntoBSTHelper(node.left,val);
        }else {
            if(node.right == null){
                node.right = new TreeNode(val);
                return;
            }
            insertIntoBSTHelper(node.right,val);
        }
    }

    /**
     * leetcode 230 kth smallest element in a BST
     * @param root
     * @param k
     * @return
     */
    public int kthSmallest(TreeNode root, int k) {
        List<Integer> list = new LinkedList<>();
        kthSmallestHelper(root,list);
        return list.get(k-1);
    }

    public void kthSmallestHelper(TreeNode node,List<Integer> list){
        if(node == null) return;
        kthSmallestHelper(node.left,list);
        list.add(node.val);
        kthSmallestHelper(node.right,list);
    }

    /**
     * leetcode 623 add one row to tree
     * @param root
     * @param v
     * @param d
     * @return
     */
    public TreeNode addOneRow(TreeNode root, int v, int d) {
        if(d == 1){
            TreeNode node = new TreeNode(v);
            node.left = root;
            return node;
        }
        addRowHelper(root,v,d,1);
        return root;
    }

    public void addRowHelper(TreeNode root,int v,int d,int current){
        if(root == null){
            return;
        }
        if(current == d-1){
            TreeNode node = new TreeNode(v);
            TreeNode node1 = new TreeNode(v);
            if(root.left != null){
                TreeNode left = root.left;
                root.left = node;
                node.left = left;
            }else {
                root.left = node;
            }
            if(root.right != null){
                TreeNode right = root.right;
                root.right = node1;
                node1.right = right;
            }else {
                root.right = node1;
            }
            return;
        }
        addRowHelper(root.left,v,d,current+1);
        addRowHelper(root.right,v,d,current+1);
    }

    /**
     * 102 binary tree level order traversal
     * @param root root
     * @return level order list
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
//        List<List<Integer>> lists = new ArrayList<>();
//        Queue<TreeNode> queue = new LinkedList<>();
//        if(root == null) return lists;
//        ((LinkedList<TreeNode>) queue).push(root);
//        int pre = 1,cur = 0;
//        List<Integer> list = new ArrayList<>();
//        while (!queue.isEmpty()){
//            pre--;
//            TreeNode node = queue.poll();
//            list.add(node.val);
//            if(node.left != null){
//                queue.offer(node.left);
//                cur++;
//            }
//            if(node.right != null){
//                queue.offer(node.right);
//                cur++;
//            }
//            if(pre == 0){
//                pre = cur;
//                cur = 0;
//                List<Integer> temp = new ArrayList<>(list);
//                lists.add(temp);
//                list = new ArrayList<>();
//            }
//        }
//        return lists;
        List<List<Integer>> lists = new ArrayList<>();
        levelOrderHelper(lists,root,0);
        return lists;
    }

    private void levelOrderHelper(List<List<Integer>> res,TreeNode node,int level){
        if(node != null) {
            if(level >= res.size()) {
                res.add(new ArrayList<Integer>());
            }
            res.get(level).add(node.val);
            levelOrderHelper(res, node.left, level+1);
            levelOrderHelper(res, node.right, level+1);
        }
    }

    /**
     * 199 binary tree right side view
     * @param root root
     * @return list
     */
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        rightSideViewHelper(root,list,0);
        return list;
    }

    private void rightSideViewHelper(TreeNode node,List<Integer> list,int depth){
        if(node == null) return;
        if(depth >= list.size())
            list.add(node.val);
        else {
            list.set(depth,node.val);
        }
        rightSideViewHelper(node.left,list,depth+1);
        rightSideViewHelper(node.right,list,depth+1);
    }

    /**
     * 863 all nodes distance K in binary tree
     * @param root root
     * @param target target node
     * @param K distance K
     * @return all nodes that match condition
     */
    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        // dfs
//        List<Integer> list = new ArrayList<>();
//        distanceKHelper(target,K,list);
//        distanceKHelper2(root,K,list,target);
//        return list;

        // bfs
        // queue 使用 null 区分离target相同距离的treeNode.遇见k次null，则queue中的节点是求得答案
//        Map<TreeNode,TreeNode> parent = new HashMap<>();
//        distanceKHelper3(root,null,parent);
//        Queue<TreeNode> queue = new LinkedList<>();
//        ((LinkedList<TreeNode>) queue).add(null);
//        queue.add(target);
//        Set<TreeNode> set = new HashSet<>();
//        set.add(target);
//        set.add(null);
//        int dist = 0;
//        while (!queue.isEmpty()){
//            TreeNode node = queue.poll();
//            if(node == null){
//                if(dist == K){
//                    List<Integer> list = new ArrayList<>();
//                    for (TreeNode n : queue){
//                        list.add(n.val);
//                    }
//                    return list;
//                }
//                queue.offer(null);
//                dist++;
//            }else {
//                if(!set.contains(node.left)){
//                    set.add(node.left);
//                    queue.offer(node.left);
//                }
//                if(!set.contains(node.right)){
//                    set.add(node.right);
//                    queue.offer(node.right);
//                }
//                TreeNode p = parent.get(node);
//                if(!set.contains(p)){
//                    set.add(p);
//                    queue.offer(p);
//                }
//            }
//        }
//        return new ArrayList<Integer>();

        // tree => graph dfs
        List<Integer> list = new ArrayList<>();
        Map<TreeNode,List<TreeNode>> map = new HashMap<>();
        distanceKHelper4(root,map);
        Set<TreeNode> set = new HashSet<>();
        distanceKHelper5(map,list,target,K,set);
        return list;
    }

    private void distanceKHelper(TreeNode node,int K,List<Integer> list){
        if(node == null)
            return;
        if(K == 0){
            list.add(node.val);
            return;
        }
        distanceKHelper(node.left,K-1,list);
        distanceKHelper(node.right,K-1,list);
    }
    private int distanceKHelper2(TreeNode node,int k,List<Integer> list,TreeNode target){
        if(node.val == target.val){
            return 0;
        }
        int k1 = 0,k2 = 0;
        if(node.left != null){
            k1 = distanceKHelper2(node.left,k,list,target);
            if(k1 != -1){
                if(k1+1 == k){
                    list.add(node.val);
                }else {
                    distanceKHelper(node.right,k-k1-2,list);
                }
                return k1+1;
            }
        }
        if(node.right != null){
            k2 = distanceKHelper2(node.right,k,list,target);
            if(k2 != -1){
                if(k2 +1 == k){
                    list.add(node.val);
                }else {
                    distanceKHelper(node.left,k-k2-2,list);
                }
                return k2+1;
            }
        }
        return -1;
    }
    private void distanceKHelper3(TreeNode node,TreeNode parent,Map<TreeNode,TreeNode> map){
        if(node == null) return;
        map.put(node,parent);
        distanceKHelper3(node.left,node,map);
        distanceKHelper3(node.right,node,map);
    }
    private void distanceKHelper4(TreeNode node,Map<TreeNode,List<TreeNode>> map){
        if(node == null) return;
        if(node.left != null){
            List<TreeNode> l1 = map.get(node);
            List<TreeNode> l2 = map.get(node.left);
            if(l1 == null){
                l1 = new ArrayList<>();
                map.put(node,l1);
            }
            l1.add(node.left);
            if(l2 == null){
                l2 = new ArrayList<>();
                map.put(node.left,l2);
            }
            l2.add(node);
            distanceKHelper4(node.left,map);
        }
        if(node.right != null){
            List<TreeNode> l1 = map.get(node);
            List<TreeNode> l2 = map.get(node.right);
            if(l1 == null){
                l1 = new ArrayList<>();
                map.put(node,l1);
            }
            l1.add(node.right);
            if(l2 == null){
                l2 = new ArrayList<>();
                map.put(node.right,l2);
            }
            l2.add(node);
            distanceKHelper4(node.right,map);
        }
    }
    private void distanceKHelper5(Map<TreeNode,List<TreeNode>> map,List<Integer> list,TreeNode node,int k,Set<TreeNode> set){
        if(k == 0){
            if(!set.contains(node)){
                list.add(node.val);
            }
            set.add(node);
            return;
        }
        List<TreeNode> list1 = map.get(node);
        if(list1 == null) return;
        set.add(node);
        for (TreeNode n : list1){
            if(!set.contains(n)){
                distanceKHelper5(map,list,n,k-1,set);
            }
        }
    }

    /**
     * 652 find duplicate subtree
     * 比较树是否相同 => 将树的结构转换成字符串
     * @param root root
     * @return root in the form of a list
     */
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        List<TreeNode> list = new ArrayList<>();
        if(root == null) return list;
        Map<String,Integer> map = new HashMap<>();
        findDuplicateSubtreesHelper(root,map,list);
        return list;
    }
    private String findDuplicateSubtreesHelper(TreeNode node,Map<String,Integer> map,List<TreeNode> list){
        if(node == null) return "";
        String s = "<"+findDuplicateSubtreesHelper(node.left,map,list)+node.val+findDuplicateSubtreesHelper(node.right,map,list) + ">";
        if(map.containsKey(s) && map.get(s) == 1) list.add(node);
        map.put(s,map.getOrDefault(s,0)+1);
        return s;
    }

    /**
     * 222 count complete binary trees
     */
    public int countNodes(TreeNode root) {
        if (root == null)
            return 0;
        int l = 1,r = 1;
        TreeNode left = root.left,right = root.right;
        while (left != null) {
            l++;
            left = left.left;
        }
        while (right != null) {
            r++;
            right = right.left;
        }
        if (l == r) {
            return (1 << l) + countNodes(root.right);
        }else {
            return (1 << l) + countNodes(root.left);
        }
    }

    /**
     *      5
     *     / \
     *    2   -5
     *     \    \
     *
     * @param args main function parameter
     */
    public static void main (String[] args){
        Solution solution = new Solution();
        TreeNode treeNode1 = new TreeNode(3);
        TreeNode treeNode2 = new TreeNode(2);TreeNode treeNode3 = new TreeNode(3);
        TreeNode treeNode4 = new TreeNode(3);TreeNode treeNode5 = new TreeNode(10);
        treeNode1.left = treeNode2;treeNode1.right = treeNode3;
        treeNode2.right = treeNode4;treeNode3.right = treeNode5;
        solution.distanceK(treeNode1,treeNode3,2);
    }
}