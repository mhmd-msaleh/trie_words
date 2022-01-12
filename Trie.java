import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Mohammed Almohammedsaleh
 * 201814920
 */
public class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public boolean contains(String s) {
        return contains(s, root);
    }
    private boolean contains(String s, TrieNode node) {
        for (int i = 0; i < s.length(); i++) {
            char index = s.charAt(i);
            if (null == node.get(index) || node.isEmpty())
                return false; 
            else 
                node = node.get(index);
        }
        return true; 
    }


    public boolean isWord(String s){
        return isWord(s, root);
    }
    public boolean isWord(String s, TrieNode node){
        boolean bool ;
        for (int i = 0; i < s.length(); i++) {
            char index = s.charAt(i);
            if (null == node.get(index) || node.isEmpty())
                bool = false; 
            else 
                node = node.get(index);
        }
        bool = true;   //make proccessing on node
        
        return bool && node.isMarked(); 
    }

    public void insert(String s) {
        insert(s, root);
    }
    private void insert(String s, TrieNode pCrawl) {
        for (int i = 0; i < s.length(); i++) {
            char index = s.charAt(i);
            if (pCrawl.get(index) == null)
                pCrawl.add(index);
 
            pCrawl = pCrawl.get(index);
        }
        // mark last node as a word
        pCrawl.markWord();
        return; 
    }

    public boolean isPrefix(String p) {
        return isPrefix(p, root); 
    }
    public boolean isPrefix(String p, TrieNode node){
        if (null == p || p.isEmpty())
            return !node.isEmpty(); 
        char firstChar = p.charAt(0);
        if (!node.contains(firstChar))
            return false; 
        return isPrefix(p.substring(1), node.get(firstChar));
    }

    public void delete(String key){
        delete(key, root, 0);
    }
    public void delete(String s, TrieNode node, int depth){
        if (node == null)
            return;
        if (depth == s.length()-1) {
            // This node is no more end of word after
            // removal of given key
            if (node.isMarked())
                node.unmarkWord();
            return;
        }
        else{
            char index = s.charAt(depth);
            node.getChildren().forEach((c, subnode) -> delete(s, root.get(index), depth+1));
            
            return;
        }
    }

    // public void delete(String s) {
    //     delete(s, root, s.charAt(s.length()-1)); 
    // }
    // public void delete(String s, TrieNode node, char lastChar){
    //     if (null == s || s.isEmpty())
    //         return;
    //     char firstChar = s.charAt(0);
    //     if (!node.contains(firstChar))
    //         return; 
    //     if (firstChar == lastChar){
    //         node.unmarkWord();
    //             //node.erase();
    //             return; 
    //     }
    //     delete(s.substring(1), node.get(firstChar), lastChar);
    //     return;
    // }

    public boolean isEmpty() {
        return root.isEmpty();
    }

    public void clear() {
        root.clear();
    }

    public List<String> allWordsPrefix(String p) {
        if (null == p || p.isEmpty()) // empty string
            return null;
        return allWordsPrefix(p, new ArrayList<String>(), root, p);
    }
    private List<String> allWordsPrefix(String p, List<String> words, TrieNode node, String original) {
        if (null == p || p.isEmpty())
            return getWords(original, node, words); 
        char firstChar = p.charAt(0);
        if (!node.contains(firstChar)) 
            return words;
        return allWordsPrefix(p.substring(1), words, node.get(firstChar), original); 
    }
    private List<String> getWords(String original, TrieNode node, List<String> words) {
        if (node.isEmpty() || node.isMarked()) // if we hit an empty node or marked node
            words.add(original);
        node.getChildren().forEach((k, v) -> getWords(original + k, v, words)); 
        return words;
    }

    public int size() {
        TrieNode node = root;
        Queue<TrieNode> queue = new LinkedList<>();
        queue.add(node);
        int size = 0;
        while (!queue.isEmpty()) {
            node = queue.poll();
            size += node.size();
            node.getChildren().forEach((k, v) -> {
                queue.add(v);
            });
        }
        return size;
    }

}