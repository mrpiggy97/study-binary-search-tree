package tree;

import java.util.ArrayList;

public class Tree {
    public Node Root;
    public Tree(Node initNode){
        // initial node cannot be null
        if(initNode == null){
            String message = "root node cannot be null";
            throw new Error(message);
        }
        this.Root = initNode;
    }
    public void add(Node newNode){
        Node currentNode = this.Root;
        // we will loop until we get a Node with a left
        // and right value of null
        while(currentNode != null){
            if(newNode.Val == currentNode.Val){
                System.out.println("a node with that value already exists");
                break;
            }
            // if newNode has a bigger value than currentNode then
            // see if currentNode has a right node, if it does not
            // have it that's where we place our new node, otherwise
            // keep going through the tree
            if(newNode.Val > currentNode.Val){
                if(currentNode.Right != null){
                    currentNode = currentNode.Right;
                }else{
                    currentNode.Right = newNode;
                    currentNode = null;
                }
            }else{
                // newNode's value is less than currentNode's value,
                // check if currentNode has a left value, if not
                // then that is the position of our newNode, otherwise
                // we keep going through the tree
                if(currentNode.Left != null){
                    currentNode = currentNode.Left;
                }else{
                    currentNode.Left = newNode;
                    currentNode = null;
                }
            }
        }
    }
    public ArrayList<Integer> traverseInOrder(){
        ArrayList<Node> nodesToVisit = new ArrayList<>();
        nodesToVisit.add(this.Root);
        Queue queue = new Queue(null);
        while(nodesToVisit.size() > 0){
            ArrayList<Node> newNodesToVisit = new ArrayList<>();
            for(Node currentNode : nodesToVisit){
                if(currentNode.Val == this.Root.Val){
                    QNode rootQNOde = new QNode(currentNode, null);
                    queue.Head = rootQNOde;
                    // add nodes if any to newNodesToVisit
                    if(currentNode.Left != null){
                        newNodesToVisit.add(currentNode.Left);
                    }
                    if(currentNode.Right != null){
                        newNodesToVisit.add(currentNode.Right);
                    }
                }else{
                    QNode newNode = new QNode(currentNode, null);
                    queue.addInOrder(newNode);
                    // add nodes if any to newNodesToVisit
                    if(currentNode.Left != null){
                        newNodesToVisit.add(currentNode.Left);
                    }
                    if(currentNode.Right != null){
                        newNodesToVisit.add(currentNode.Right);
                    }
                }
            }
            nodesToVisit = newNodesToVisit;
        }
        return queue.emptyAndGetAsArray();
    }
    public ArrayList<Integer> traverseInPreOrder(){
        QNode nodesInPreorder = Queue.getTreeInPreOrder(this);
        return Queue.getQNodeAsArrayList(nodesInPreorder);
    }
    public void delete(int val){
        Node currentNode = this.Root;
        // previousNode will serve as a holder
        // to re balance the tree
        Node previousNode = null;
        // first we will go through the tree trying to find a node with that value
        // if we find it we break the loop
        while(currentNode != null){
            if(currentNode.Val == val){
                // delete and re balance the tree
                if(previousNode == null){
                    if(currentNode.Right != null){
                    }
                }
            }else{
                // keep searching the node
                if(val > currentNode.Val){
                    if(currentNode.Right == null){
                        // no node with that value has been found
                        // end loop
                        currentNode = null;
                    }else{
                        previousNode = currentNode;
                        currentNode = currentNode.Right;
                    }
                }else{
                    if(currentNode.Left == null){
                        // no node with that value has been found
                        // end loop
                        currentNode = null;
                    }else{
                        previousNode = currentNode;
                        currentNode = currentNode.Left;
                    }
                }
            }
        }
    }
}
