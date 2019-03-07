import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Trie {
    private Tree trie = new Tree();

    public void printTrie() {
        trie.print();
    }

    public void addWords(List<String> words) {
        for (String word : words) {
            insert(word);
        }
    }

    public void insert(String word) {
        EntryNode current = trie.getRoot();
        for (int i = 0; i < word.length(); i++) {
            char character = word.charAt(i);

            EntryNode child = current.getChild(character);
            if (child == null) {
                child = new EntryNode(character, i == word.length() - 1);
                current.addChild(child);
            }

            current = child;
        }
        current.setTerminal(true);
    }

    public boolean contains(String potentialWord) {
        EntryNode current = trie.getRoot();
        for (int i = 0; i < potentialWord.length(); i++) {
            char character = potentialWord.charAt(i);

            EntryNode child = current.getChild(character);
            if (child == null) {
                return false;
            }
            current = child;
        }

        return current.isTerminal();
    }

    public void remove(String word) {
        EntryNode current = trie.getRoot();
        String wordStorage = "";
        EntryNode child = null;

        if (contains(word)) {

            for (int i = 0; i < word.length(); i++) {
                char character = word.charAt(i);

                if (current.getChild(character) != null || word.length() - 1 == 0) {
                    child = current.getChild(character);

                } else if (word.length() - 1 == 1) {
                    character = word.charAt(i++);
                    child = current.getChild(character);
                }

                if (i == word.length() - 1) {

                    if (child == null) {
                        trie.getRoot().setNull(current);

                    } else if (current.isTerminal() && child.isTerminal()) {
                        current.setNull(child);
                        break;

                    } else if (child.hasChild(child)) {
                        break;

                    } else {
                        current.setNull(child);
                        current.setTerminal(true);
                        word = wordStorage;
                        wordStorage = "";
                        current = trie.getRoot();
                        character = word.charAt(0);
                        child = current.getChild(character);
                        i = -1;
                    }
                }

                current = child;
                wordStorage += character;
            }
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scan = new Scanner(new File("src/main/resources/dictionary.txt"));
        List<String> words = new ArrayList<>();

        while (scan.hasNext()) {
            words.addAll(Arrays.asList(scan.nextLine().split(" ")));
        }

        Trie trie = new Trie();
        trie.addWords(words);
        trie.printTrie();

        trie.remove("shorebird");
        trie.remove("she");
        trie.remove("shells");
        trie.remove("shorebird");
        trie.remove("by");
        trie.remove("the");

        System.out.println();
        trie.printTrie();
        System.out.println("This test should report false:");
        System.out.println("Contains 's': " + trie.contains("s"));
        System.out.println("Contains 'bye': " + trie.contains("bye"));
        System.out.println("Contains 'bird': " + trie.contains("bird"));

        System.out.println();
        System.out.println("These tests should report true:");
        System.out.println("Contains 'she': " + trie.contains("she"));
        System.out.println("Contains 'sells': " + trie.contains("sells"));
        System.out.println("Contains 'sea': " + trie.contains("sea"));
        System.out.println("Contains 'shells': " + trie.contains("shells"));
        System.out.println("Contains 'by': " + trie.contains("by"));
        System.out.println("Contains 'the': " + trie.contains("the"));
        System.out.println("Contains 'shore': " + trie.contains("shore"));
        System.out.println("Contains 'shorebird': " + trie.contains("shorebird"));
    }
}
