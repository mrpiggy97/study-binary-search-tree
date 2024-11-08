package tree;

// QNode will serve as a wrapper to a Tree node.
// Also it will contain a reference to another QNode
public class QNode {
    public Node TNode;
    public QNode Next;
    public QNode(Node treeNode, QNode next){
        this.TNode = treeNode;
        this.Next = next;
    }
}
