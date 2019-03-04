import java.util.*;


public class EntryNode {
    private char letter;
    private boolean isTerminal;
    private Map<Character, EntryNode> children = new HashMap<>();

    EntryNode(char letter) {
        this(letter, false);
    }

    EntryNode(char letter, boolean isTerminal) {
        this.letter = letter;
        this.isTerminal = isTerminal;
    }


    EntryNode getChild(Character data) {
        return children.get(data);
    }

    void addChild(EntryNode child) {
        this.children.put(child.letter, child);
    }

    boolean isTerminal() {
        return isTerminal;
    }

    void setTerminal(boolean isTerminal) {
        this.isTerminal = isTerminal;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof EntryNode)) {
            return false;
        }

        return ((EntryNode) obj).letter == this.letter;
    }

    public String toString() {
        char modifier = ' ';
        if (isTerminal) {
            modifier = '*';
        }

        return String.valueOf(new char[] {letter, modifier});
    }

    void print(String prefix, boolean isTail) {
        System.out.println(prefix + (isTail ? "└── " : "├── ") + this);
        for (char letter : children.keySet()) {
            children.get(letter).print(prefix + (isTail ? "    " : "│   "), false);
        }
    }
}

