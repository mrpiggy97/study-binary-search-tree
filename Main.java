import tree.Node;
import tree.Tree;

public class Main{
    public static void main(String[] args) {
        Node rootNode = new Node(2);
        Node leftNode = new Node(1);
        Node rightNode = new Node(3);
        Tree testingTree = new Tree(rootNode);
        testingTree.add(rightNode);
        testingTree.add(leftNode);
        assert testingTree.Root.Val == 2 : "test failed, root value should be 2";
        assert testingTree.Root.Left.Val == 1 : "test failed, left value should be 1";
        assert testingTree.Root.Right.Val == 3 : "test failed, right value should be 3";
    }
}