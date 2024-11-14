package tree;

import java.util.ArrayList;

public class Queue {
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
    // all members of the given bst tree in pre order
    public static ArrayList<Integer> getTreeInPreOrder(Tree bst){
        QNode headNode = new QNode(bst.Root, null);
        QNode currentNode = headNode;
        ArrayList<Integer> nodes = new ArrayList<>();
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
    public static QNode getTreeInPostOrder(Tree tree){
        QNode currentNode = null;
        if(tree.Root != null){
            currentNode = new QNode(tree.Root, null);
        }
        QNode firstNode = currentNode;
        while(currentNode != null){
            if(currentNode.TNode.Left != null){
                QNode leftNode = new QNode(currentNode.TNode.Left, null);
                // if currentNode has a next node that place
                // left node in between them
                if(currentNode.Next != null){
                    leftNode.Next = currentNode.Next;
                    currentNode.Next = leftNode;
                }else{
                    currentNode.Next = leftNode;
                }
            }
            if(currentNode.TNode.Right != null){
                QNode rightNode = new QNode(currentNode.TNode.Right, null);
                // if currentNode has a next node then
                // set rightNode in between them
                if(currentNode.Next != null){
                    rightNode.Next = currentNode.Next;
                    currentNode.Next = rightNode;
                }else{
                    currentNode.Next = rightNode;
                }
            }
            currentNode = currentNode.Next;
        }
        return firstNode;
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
