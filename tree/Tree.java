package tree;

import java.util.ArrayList;
import java.util.Arrays;

public class Tree {
    public Node Root;
    public int NodeCount;
    public Tree(Node initNode){
        // initial node cannot be null
        this.Root = initNode;
        this.NodeCount = 1;
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
        this.NodeCount = this.NodeCount+1;
    }
    public int[] traverseInOrder(){
        ArrayList<Node> nodesToVisit = new ArrayList<Node>();
        int size = this.NodeCount;
        int[] nodes = new int[size];
        nodesToVisit.add(this.Root);
        int index = 0;
        while(nodesToVisit.size() > 0){
            ArrayList<Node> newNodesToVisit = new ArrayList<Node>();
            for(Node currentNode : nodesToVisit){
                nodes[index] = currentNode.Val;
                index++;
                if(currentNode.Left != null){
                    newNodesToVisit.add(currentNode.Left);
                }
                if(currentNode.Right != null){
                    newNodesToVisit.add(currentNode.Right);
                }
            }
            nodesToVisit = newNodesToVisit;
        }
        Arrays.sort(nodes);
        return nodes;
    }
    public ArrayList<Integer> traverseInPreOrder(){
        return Queue.getTreeInPreOrder(this);
    }
    public ArrayList<Integer> traverseInPostOrder(){
        return Queue.getTreeInPostOrder(this);
    }
    // method will help use find the lowest value in currentNode subtree
    public static Node findLowest(Node currentNode){
        boolean stop = false;
        while(stop == false){
            if(currentNode.Left != null){
                currentNode = currentNode.Left;
            }else{
                break;
            }
        }
        return new Node(currentNode.Val);
    }
    public void delete(int val){
        Node currentNode = this.Root;
        Node previousNode = currentNode;
        // first we will go through the tree trying to find a node with that value
        // if we find it we delete it and re-balance the tree
        while(currentNode != null){
            if(currentNode.Val == val){
                // delete and re balance the tree
                
                // currentNode to be deleted has no children, therefore
                // just delete any reference to that node, no need to re-balance

                if(currentNode.Left == null && currentNode.Right == null){
                    // if currentNode is the root of the tree then empty tree
                    if(currentNode.Val == this.Root.Val){
                        this.Root = null;
                    }
                    // if currentNode has children but it's not the root node simply
                    // set the previousNode reference of currentNode to null
                    if(previousNode.Left.Val == currentNode.Val){
                        previousNode.Left = null;
                    }
                    if(previousNode.Right.Val == currentNode.Val){
                        previousNode.Right = null;
                    }
                    return;
                }
                // switchNode will help seek for the new root node of the subtree
                Node switchNode = null;
                if(currentNode.Right != null){
                    switchNode = currentNode.Right;
                }else{
                    switchNode = currentNode.Left;
                }
                // get lowest value from the subtree and set it as root node
                // of new tree
                Node newRootNode = Tree.findLowest(switchNode);
                Tree newTree = new Tree(newRootNode);
                ArrayList<Node> nodesToVisit = new ArrayList<Node>();
                nodesToVisit.add(currentNode);
                while(nodesToVisit.size() > 0){
                    ArrayList<Node> newNodesToVisit = new ArrayList<Node>();
                    for(Node oldNode : nodesToVisit){
                        // append children of old node to newNodesToVisit
                        if(oldNode.Left != null){
                            newNodesToVisit.add(oldNode.Left);
                        }
                        if(oldNode.Right != null){
                            newNodesToVisit.add(oldNode.Right);
                        }
                        // if oldNode does not have the same value as the root node
                        // of the new tree then add it
                        if(oldNode.Val != newTree.Root.Val){
                            Node newNode = new Node(oldNode.Val);
                            newTree.add(newNode);
                        }
                    }
                    nodesToVisit  = newNodesToVisit;
                }
                // re balance tree

                // if previousNode is equal to currentNode that means we
                // are deleting the root node, therefore newRootNode will
                // become the rootNode of our current tree
                if(previousNode.Val == currentNode.Val){
                    this.Root = newRootNode;
                }

                // if previousNode is not root Node than set newRootNode as either
                // left or right node of previousNode
                if(previousNode.Val != currentNode.Val){
                    if(newRootNode.Val > previousNode.Val){
                        previousNode.Right = newRootNode;
                    }
                    if(newRootNode.Val < previousNode.Val){
                        previousNode.Left = newRootNode;
                    }
                    newTree = null;
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
