package Trie;

/**
 * 前缀树
 */
public class TrieNode {

    TrieNode[] children;

    String word;

    public TrieNode(String s){
        this.word = s;
        children = new TrieNode[26];
    }

    public void insert(String s){
        insert(s,0);
    }

    public void insert(String w,int i){
        if(w.length() <= i) return;
        char c = w.charAt(i);
        if(children[c-'a'] == null) children[c-'a'] = new TrieNode(null);
        if(i == w.length()-1) children[c-'a'] = new TrieNode(w);
        children[c-'a'].insert(w,i+1);
    }

}
