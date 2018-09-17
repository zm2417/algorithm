package Trie;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution {

    /**
     * leetcode 648 replace words
     * @param dict
     * @param sentence
     * @return
     */
    public String replaceWords(List<String> dict, String sentence) {
        /**
         * one
         * hashSet
         */
//        Set<String> set = new HashSet<>(dict);
//        String[] temp = sentence.split("\\s+");
//        String[] res = new String[temp.length];
//        int j = 0;
//        for(String s : temp){
//            boolean mask = false;
//            for (int i = 1;i<=s.length();i++){
//                if(set.contains(s.substring(0,i))){
//                    res[j++] = s.substring(0,i);
//                    mask = true;
//                    break;
//                }
//            }
//            if(!mask){
//                res[j] = temp[j++];
//            }
//        }
//        StringBuilder sb = new StringBuilder();
//        for (String s : res){
//            sb.append(s+" ");
//        }
//        sb.deleteCharAt(sb.length()-1);
//        return sb.toString();
        /**
         * two
         * trie
         */
        TrieNode node = new TrieNode("");
        for (String s : dict){
            node.insert(s);
        }
        String[] splits = sentence.split(" ");
        StringBuilder sb = new StringBuilder();

        for (String split : splits) {
            String r = find(split, node);
            sb.append(r == null ? split : r);
            sb.append(" ");
        }
        return sb.deleteCharAt(sb.length() - 1).toString();
    }

    private String find(String s, TrieNode root) {
        TrieNode node = root;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (node.children[c - 'a'] == null) return null;
            node = node.children[c - 'a'];
            if (node.word != null) return node.word;
        }
        return node.word;
    }

}
