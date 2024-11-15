package tree;

import java.util.ArrayList;
import java.util.Collections;

public class Tree {
    public Node Root;
    public Tree(Node initNode){
        // initial node cannot be null
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
        ArrayList<Node> nodesToVisit = new ArrayList<Node>();
        ArrayList<Integer> nodes = new ArrayList<>();
        nodesToVisit.add(this.Root);
        while(nodesToVisit.size() > 0){
            ArrayList<Node> newNodesToVisit = new ArrayList<Node>();
            for(Node currentNode : nodesToVisit){
                nodes.add(currentNode.Val);
                if(currentNode.Left != null){
                    newNodesToVisit.add(currentNode.Left);
                }
                if(currentNode.Right != null){
                    newNodesToVisit.add(currentNode.Right);
                }
            }
            nodesToVisit = newNodesToVisit;
        }
        Collections.sort(nodes);
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
        int smallestVal = currentNode.Val;
        while(currentNode != null){
            currentNode = currentNode.Left;
            if(currentNode != null){
                smallestVal = currentNode.Val;
            }
        }
        return new Node(smallestVal);
    }
    public void reBalanceTree(Node startNode, Node newHead){
        ArrayList<Node> nodesToVisit = new ArrayList<>();
        if(startNode.Left != null){
            nodesToVisit.add(startNode.Left);
        }
        if(startNode.Right != null){
            nodesToVisit.add(startNode.Right);
        }
        Tree subTree = new Tree(newHead);
        while(nodesToVisit.size() > 0){
            ArrayList<Node> newNodesToVisit = new ArrayList<>();
            for(Node currentNode : nodesToVisit){
                if(currentNode.Left != null){
                    newNodesToVisit.add(currentNode.Left);
                }
                if(currentNode.Right != null){
                    newNodesToVisit.add(currentNode.Right);
                }
                if(currentNode.Val != newHead.Val){
                    Node newNode = new Node(currentNode.Val);
                    subTree.add(newNode);                    
                }
            }
            nodesToVisit = newNodesToVisit;
        }
    }
    public void delete(int val){
        Node currentNode = this.Root;
        Node previousNode = currentNode;
        // first we will go through the tree trying to find a node with that value
        // if we find it we delete it and re-balance the tree
        while(currentNode != null){
            if(currentNode.Val == val){
                // delete and re balance the tree
                Node subTreeStart = null;
                // choose new node to replace the currentNode
                if(currentNode.Left != null){
                    subTreeStart = currentNode.Left;
                }
                if(currentNode.Right != null){
                    subTreeStart = currentNode.Right;
                }
                // if subTreeStart is still null that means that the
                // node we are deleting has no children
                if(subTreeStart == null){
                    // check if node is root of tree and if so since it has no
                    // children than just empty the tree
                    if(this.Root.Val == currentNode.Val){
                        this.Root = null;
                        return;
                    }
                    // if currentNode has no children then all we need to do
                    // is just delete the memory reference of that node in previousNode
                    if(previousNode.Left.Val == currentNode.Val){
                        previousNode.Left = null;
                        return;
                    }
                    if(previousNode.Right.Val == currentNode.Val){
                        previousNode.Right = null;
                        return;
                    }
                }
                // proceed to re-balance tree
                if(subTreeStart != null){
                    // new head does not have any children, it is ready to start re-balancing
                    // tree
                    Node subTreeHead = Tree.findLowest(subTreeStart);
                    this.reBalanceTree(currentNode, subTreeHead);
                    // now check if subTreeHead should go to right of left of previousNode
                    // or if it supposed to replace root node
                    if(previousNode.Val == currentNode.Val){
                        if(currentNode.Right != null){
                            if(currentNode.Right.Val == subTreeStart.Val){
                                if(currentNode.Left != null){
                                    subTreeHead.Left = currentNode.Left;
                                }
                            }
                        }
                        if(currentNode.Left != null){
                            if(currentNode.Left.Val == subTreeStart.Val){
                                if(currentNode.Right != null){
                                    subTreeHead.Right = currentNode.Right;
                                }
                            }
                        }
                        this.Root = subTreeHead;
                    }
                    if(previousNode.Val != currentNode.Val){
                        if(previousNode.Right.Val == currentNode.Val){
                            previousNode.Right = subTreeHead;
                        }
                        if(previousNode.Left.Val == currentNode.Val){
                            previousNode.Left = subTreeHead;
                        }
                        return;
                    }
                    return;
                }
                
            }else{
                // keep searching the node
                if(val > currentNode.Val){
                    if(currentNode.Right == null){
                        // no node with that value has been found
                        // end loop
                        currentNode = currentNode.Right;
                    }else{
                        previousNode = currentNode;
                        currentNode = currentNode.Right;
                    }
                }else{
                    if(currentNode.Left == null){
                        // no node with that value has been found
                        // end loop
                        currentNode = currentNode.Left;
                    }else{
                        previousNode = currentNode;
                        currentNode = currentNode.Left;
                    }
                }
            }
        }
        // if program gets here that means that no node with that value was found
        System.out.println("No node with that value was found");
    }
    public void printSchema(){
        ArrayList<Node> nodes = new ArrayList<>();
        nodes.add(this.Root);
        while(nodes.size() > 0){
            ArrayList<Node> newNodes = new ArrayList<>();
            for(Node currentNode : nodes){
                if(currentNode.Left != null){
                    newNodes.add(currentNode.Left);
                }
                if(currentNode.Right != null){
                    newNodes.add(currentNode.Right);
                }
                System.out.print(currentNode.Val);
            }
            System.out.println("\n");
            nodes = newNodes;
        }
    }
}
