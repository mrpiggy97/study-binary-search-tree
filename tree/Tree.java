package tree;

public class Tree {
    Node Root;
    public Tree(Node initNode){
        // initial node cannot be null
        if(initNode == null){
            String message = "root node cannot be null";
            throw new Error(message);
        }
        this.Root = initNode;
    }
    public void add(Node newNode){
    }
}
