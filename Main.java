import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;


public class Main{
    public static class QNode {
        public Node TNode;
        public QNode Next;
        public QNode(Node treeNode, QNode next){
            this.TNode = treeNode;
            this.Next = next;
        }
    }
    public static class Queue {
        public QNode Head;
        public Queue(QNode head){
            this.Head = head;
        }
        // the following method will empty the queue and
        // return the values of each QNode in an array
        public ArrayList<Integer> emptyAndGetAsArray(){
            ArrayList<Integer> nodes = new ArrayList<Integer>();
            while(this.Head != null){
                nodes.add(this.Head.TNode.Val);
                this.Head = this.Head.Next;
            }
            return nodes;
        }
        // this method will return a QNode that will be contain
        // all members of the given bst in pre order
        public static ArrayList<Integer> getTreeInPreOrder(Tree bst){
            QNode headNode = new QNode(bst.Root, null);
            QNode currentNode = headNode;
            ArrayList<Integer> nodes = new ArrayList<Integer>();
            while(currentNode != null){
                // if there is a TNode with a right node then add it
                if(currentNode.TNode.Right != null){
                    QNode newNode = new QNode(currentNode.TNode.Right,null);
                    // if currentNode already has a next node than place
                    // newNode in between them
                    if(currentNode.Next != null){
                        newNode.Next = currentNode.Next;
                        currentNode.Next = newNode;
                    }else{
                        currentNode.Next = newNode;
                    }
                }
                // if there is a TNode with a left node then add it
                if(currentNode.TNode.Left != null){
                    QNode newNode = new QNode(currentNode.TNode.Left,null);
                    // if currentNode already has a next node that place
                    // newNode in between them
                    if(currentNode.Next != null){
                        newNode.Next = currentNode.Next;
                        currentNode.Next = newNode;
                    }else{
                        currentNode.Next = newNode;
                    }
                }
                // keep going until there are no more nodes to add
                nodes.add(currentNode.TNode.Val);
                currentNode = currentNode.Next;
            }
            return nodes;
        }
        
        // this method will return a QNode that will have every
        // member of the given tree in post order
        public static ArrayList<Integer> getTreeInPostOrder(Tree tree){
            if(tree.Root == null){
                return null;
            }
            QNode head = new QNode(tree.Root,null);
            QNode currentNode = head;
            ArrayList<Integer> nodes = new ArrayList<Integer>();
            while(currentNode != null){
                if(currentNode.TNode.Left != null){
                    QNode newNode = new QNode(currentNode.TNode.Left,null);
                    if(currentNode.Next != null){
                        newNode.Next = currentNode.Next;
                        currentNode.Next = newNode;
                    }else{
                        currentNode.Next = newNode;
                    }
                }
                if(currentNode.TNode.Right != null){
                    QNode newNode = new QNode(currentNode.TNode.Right,null);
                    if(currentNode.Next != null){
                        newNode.Next = currentNode.Next;
                        currentNode.Next = newNode;
                    }else{
                        currentNode.Next = newNode;
                    }
                }
                nodes.addFirst(currentNode.TNode.Val);
                currentNode = currentNode.Next;
            }
            return nodes;
        }
        // this method will iterate over the QNode and append each TNode value
        // to the array list that is going to be returned
        public static ArrayList<Integer> getQNodeAsArrayList(QNode node){
            QNode currentNode = node;
            ArrayList<Integer> nodes = new ArrayList<>();
            while(currentNode != null){
                nodes.add(currentNode.TNode.Val);
                currentNode = currentNode.Next;
            }
            return nodes;
        }
        // this method will iterate over the QNode and add at the start of the array
        // list to be returned the value of TNode
        public static ArrayList<Integer> getQNodeArrayListFromEnd(QNode node){
            QNode currentNode = node;
            ArrayList<Integer> nodes = new ArrayList<Integer>();
            while(currentNode != null){
                nodes.addFirst(currentNode.TNode.Val);
            }
            return nodes;
        }
    }
    public static class Node {
        public int Val;
        public Node Left;
        public Node Right;
        public Node(int val){
            this.Val = val;
        }
    }
    public static class Tree {
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
        // this method will traverse the bst InOrder
        public ArrayList<Integer> traverseInOrder(){
            ArrayList<Node> nodesToVisit = new ArrayList<Node>();
            ArrayList<Integer> nodes = new ArrayList<>();
            nodesToVisit.add(this.Root);
            // loop will run until we re assign it to an empty array
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
        // using a queue for traversing in preorder is more readable
        // then using recursion
        public ArrayList<Integer> traverseInPreOrder(){
            return Queue.getTreeInPreOrder(this);
        }
        // using a queue for traversin in postorder is more readable
        // then using recursion
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
        // this method will handle the rebalancing of a bst when we delete a node
        public void reBalanceTree(Node startNode, Node newHead){
            ArrayList<Node> nodesToVisit = new ArrayList<>();
            if(startNode.Left != null){
                nodesToVisit.add(startNode.Left);
            }
            if(startNode.Right != null){
                nodesToVisit.add(startNode.Right);
            }
            Tree subTree = new Tree(newHead);
            // loop will run until we re-assign nodesToVisit to an empty array
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
        // method to delete node from bst and re-balance tree
        public void delete(int val){
            if(this.Root == null){
                System.out.println("tree is empty");
                return;
            }
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
    }
    public static class App{
        public Tree AppTree;
        Scanner AppScanner;
        public App(){
            this.AppTree = null;
            this.AppScanner = new Scanner(System.in);
        }
        public void startApp(){
            String firstOpt = "select 1 to create a binary search tree with some pre introduced values";
            String secondOpt = "select 2 to add a node to the binary search tree";
            String thirdOpt = "select 3 to delete node";
            String fourthOpt = "select 4 to print by InOrder";
            String fifthOpt = "select 5 to print nodes by PreOrder";
            String sixthOpt = "select 6 to print nodes by PostOrder";
            String seventhOpt = "select 7 to exit program";
            int userChoice = 0;
            while(userChoice != 7){
                System.out.println(firstOpt);
                System.out.println(secondOpt);
                System.out.println(thirdOpt);
                System.out.println(fourthOpt);
                System.out.println(fifthOpt);
                System.out.println(sixthOpt);
                System.out.println(seventhOpt);
                try {
                    userChoice = this.AppScanner.nextInt();
                    switch (userChoice) {
                        case 1:
                            this.createBinarySearchTree();
                            break;
                        case 2:
                            this.addNode();
                            break;
                        case 3:
                            this.deleteNode();
                            break;
                        case 4:
                            this.printInOrder();
                            break;
                        case 5:
                            this.printPreOrder();
                            break;
                        case 6:
                            this.printPostOrder();
                            break;
                        case 7:
                            System.out.println("goodbye");
                            this.AppScanner.close();
                            userChoice = 7;
                            break;
                        default:
                            userChoice = 0;
                            break;
                    }
                } catch (Exception e) {
                    System.out.println(e.toString());
                    userChoice = 0;
                    this.AppScanner.next();
                }
            }
        }
        // method to prepopulate a bst
        public void createBinarySearchTree(){
            Node rootNode = new Node(4);
            Node secondNode = new Node(2);
            Node thirdNode = new Node(6);
            Node fourthNode = new Node(1);
            Node fifthNode = new Node(3);
            Node sixthNode = new Node(5);
            Node seventhNode = new Node(7);
            this.AppTree = new Tree(rootNode);
            this.AppTree.add(secondNode);
            this.AppTree.add(thirdNode);
            this.AppTree.add(fourthNode);
            this.AppTree.add(fifthNode);
            this.AppTree.add(sixthNode);
            this.AppTree.add(seventhNode);
        }
        // method will ask for user input to create a new node in the bst
        public void addNode(){
            System.out.println("please enter the value of the new node");
            try {
                int nodeValue = this.AppScanner.nextInt();
                Node newNode = new Node(nodeValue);
                this.AppTree.add(newNode);
                System.out.println("success in adding node");
            } catch (Exception e) {
                System.out.println(e.toString());
                this.AppScanner.next();
            }
        }
        // method will ask for input and then search a node in the bst to delete
        public void deleteNode(){
            try {
                System.out.println("please enter the value of the node to delete");
                int nodeVal = this.AppScanner.nextInt();
                this.AppTree.delete(nodeVal);
            } catch (Exception e) {
                System.out.println(e.toString());
                this.AppScanner.next();
            }
        }
        // print the bst inorder
        public void printInOrder(){
            ArrayList<Integer> nodes = this.AppTree.traverseInOrder();
            System.out.println(nodes.toString());
        }
        // print the bst in preorder
        public void printPreOrder(){
            ArrayList<Integer> nodes = this.AppTree.traverseInPreOrder();
            System.out.println(nodes.toString());
        }
        // print the bst in postorder
        public void printPostOrder(){
            ArrayList<Integer> nodes = this.AppTree.traverseInPostOrder();
            System.out.println(nodes.toString());
        }
    }
    public static void main(String[] args) {
        App assignmentApp = new App();
        assignmentApp.startApp();
    }
}