import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;

public class test {
    public static void main(String[] args) throws IOException {
        Trie trie = new Trie(); 

        Set<String> dictionary = new HashSet<String>();
        Scanner filescan = new Scanner(new File("dictionary.txt"));
        while (filescan.hasNext()) {
            dictionary.add(filescan.nextLine().toUpperCase());
        }

        ArrayList<String> list = getAllEnglishWords("STOP", dictionary); 
        for (String word: list){
            //System.out.println(word);
            trie.insert(word);  
        }

        System.out.println(trie.contains("STOP"));
        trie.delete("STOP");
        System.out.println(trie.isWord("STOP"));

        System.out.println(trie.contains("STOP"));

        //trie.clear();

        System.out.println(trie.isEmpty());
        System.out.println(trie.allWordsPrefix("PO"));


    }





    static ArrayList<String> getAllEnglishWords(String letters, Set<String> dictionary) {
        ArrayList<String> list = new ArrayList<>();
        Set<String> set = permutate(letters);
        for (String str : set)
            if (dictionary.contains(str))
                list.add(str);
            else
                continue;

        return list;
    }

    static Set<String> permutate(String s) {
        Queue<String> permutations = new LinkedList<String>();
        Set<String> v = new HashSet<String>();
        permutations.add(s);
        while (permutations.size() != 0) {
            String str = permutations.poll();
            if (!v.contains(str)) {
                v.add(str);
                for (int i = 0; i < str.length(); i++) {
                    String c = String.valueOf(str.charAt(i));
                    permutations.add(str.substring(i + 1) + c + str.substring(0, i));
                }
            }
            if (str.length() > 1)
                permutations.add(str.substring(1));
        }
        return v;
    }
}
