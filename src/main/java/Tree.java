public class Tree {
    private EntryNode root = new EntryNode(' ');

    public EntryNode getRoot() {
        return this.root;
    }

    public void print() {
        root.print("", true);
    }
}
