import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

/**
 * Main
 * 
 * Mohammed Almohammedsaleh
 * 201814920
 */
public class Main {
    private static Trie trie;

    public static void main(String[] args) throws IOException {

        Set<String> dictionary = new HashSet<String>();
        Scanner filescan = new Scanner(new File("dictionary.txt"));
        while (filescan.hasNext()) {
            dictionary.add(filescan.nextLine().toUpperCase());
        }
        filescan.close();
        Scanner keyboard = new Scanner(System.in);
        int choice = 0;
        while (true) {

            System.out.println(
                    "\nTRIE PROJECT: Please Enter you choice\n" +
                            "\t1) Create an empty trie\n" +
                            "\t2) Create a trie with initial letters\n" +
                            "\t3) Insert a word\n" +
                            "\t4) Delete a word\n" +
                            "\t5) List all words that begin with a prefix\n" +
                            "\t6) Size of the trie\n" +
                            "\t7) Search for a word\n "+
                            "\t8) End");
            try {
                System.out.print("Your choice: ");
                choice = keyboard.nextInt();
                keyboard.nextLine();
                if (choice < 1 || choice > 8)
                    throw new IllegalArgumentException();
            } catch (Exception ex) {

                System.out.println("Wrong Input: Please try again");
                keyboard.nextLine();
                // keyboard.nextLine();
            }
            switch (choice) {
                case 1:
                    createEmptyTrie();
                    continue;
                case 2:
                    createTrieWithInitialLetters(keyboard, dictionary);
                    continue;
                case 3:
                    insertWord(keyboard);
                    continue;
                case 4:
                    deleteWord(keyboard);
                    continue; 
                case 5:
                    listWordsBeginWith(keyboard);
                    continue;
                case 6:
                    sizeOfTrie();
                    continue;
                case 7: 
                    findWord(keyboard); 
                    continue;
                case 8:
                    System.out.println("The program is terminated !");
                    keyboard.close();
                    System.exit(0);
                default:
                    continue;
            }
        }

    }

    static void findWord(Scanner keyboard){
        if (trie != null) {
            String word = "";
            System.out.print("Enter a word to Search for> ");
            word = keyboard.nextLine();
            word = word.toUpperCase();
            boolean exist = trie.isWord(word);
            if(exist == true)
                System.out.println("The word \"" + word + "\"" + " is in the Trie");
            else 
                System.out.println("The word \"" + word + "\"" + " is NOT in the Trie");
        } else
            System.out.println("There is no Trie !!");
    }

    static void createEmptyTrie() {
        trie = new Trie();
        System.out.println("Empty Trie created");
        return;
    }

    static void createTrieWithInitialLetters(Scanner keyboard, Set<String> dictionary) {
        trie = new Trie();
        System.out.print("Enter your list of letters> ");
        String letters = keyboard.nextLine();
        letters = letters.toUpperCase();
        ArrayList<String> words = getAllEnglishWords(letters, dictionary);
        for (String word : words) {
            trie.insert(word);
        }
        System.out.println("Trie created");
        return;
    }

    static void insertWord(Scanner keyboard) {
        if (trie != null) {
            String word = "";
            System.out.print("Enter a word to Insert> ");
            word = keyboard.nextLine();
            word = word.toUpperCase();
            trie.insert(word);
            System.out.println("\"" + word + "\"" + " inserted in the Trie");
        } else
            System.out.println("There is no Trie !!");

    }

    static void deleteWord(Scanner keyboard) {
        if (trie != null) {
            String word = "";
            System.out.print("Enter a word to delete> ");
            word = keyboard.nextLine();
            word = word.toUpperCase();
            trie.delete(word);
            System.out.print("\"" + word + "\"" + " deleted");
        } else
            System.out.println("There is no Trie !!");
    }

    static void listWordsBeginWith(Scanner keyboard) {
        if (trie != null) {
            String prefix = "";
            System.out.print("Enter a prefix> ");
            prefix = keyboard.nextLine();
            prefix = prefix.toUpperCase();
            List<String> listOfWords = trie.allWordsPrefix(prefix);
            System.out.print("Found the following words: ");
            for (String string : listOfWords)
                System.out.print(string + " ");

        } else
            System.out.println("There is no Trie !!");

    }

    static void sizeOfTrie() throws NullPointerException {
        if (trie != null)
            System.out.println("The size of the trie is: " + trie.size());
        else
            System.out.println("There is no Trie !!");

        return;
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