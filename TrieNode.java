import java.util.HashMap;

/**
 * Mohammed Almohammedsaleh
 * 201814920
 */
public class TrieNode {
    public static final int Stack = 0;
    private HashMap<Character, TrieNode> children;
    private boolean terminal; 

    public TrieNode(){
        this.children = new HashMap<>();
        this.terminal = false; 
    }

    // add character to children
    public void add(char c){
        this.children.put(c, new TrieNode()); 
    }

    // children contains character c
    public boolean contains(char c){
        return this.children.containsKey(c); 
    }

    // get the node that holds c
    public TrieNode get(char c){
        return this.children.get(c); 
    }

    public boolean isMarked(){
        return this.terminal; 
    }
    public void markWord(){
        this.terminal = true; 
    }
    public void unmarkWord(){
        this.terminal = false; 
    }

    public boolean isEmpty(){
        return this.children.isEmpty(); 
    }

    public void clear(){
        this.children.clear();
    }


    public int size(){
        return this.children.size(); 
    }

    public HashMap<Character, TrieNode> getChildren(){
        return this.children; 
    }


}
