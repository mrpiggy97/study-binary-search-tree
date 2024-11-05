package tree;

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
                    currentNode = currentNode.Right;
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
                    currentNode = currentNode.Left;
                }
            }
        }
    }
    public void delete(int val){
        Node currentNode = this.Root;
        // previousNode will serve as a holder
        // to re balance the tree
        Node previousNode = null;
        // first we will go through the tree trying to find a node with that value
        // if we find it we break the loop
        while(currentNode.Left != null && currentNode.Right != null){
            // node found break loop
            if(currentNode.Val == val){
                break;
            }
            //go to left side
            if(val < currentNode.Val){
                previousNode = currentNode;
                currentNode = currentNode.Left;
            }else{
                previousNode = currentNode;
                currentNode = currentNode.Right;
            }
        }
        // node not found, proceed to tell the user
        if(currentNode.Val != val){
            System.out.println("No node with that value was found in the tree");
        }else{
            // node found proceed to delete
            // if previousNode is still null that means that we
            // are deleting the root node
            if(previousNode == null){

            }
        }
    }
}
