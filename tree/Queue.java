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
    // this method will ensure the queue has the nodes
    // go in ascending order
    public void addInOrder(QNode newNode){
        QNode currentNode = this.Head;
        QNode previousNode = currentNode;
        while(currentNode != null){
            if(newNode.TNode.Val > currentNode.TNode.Val){
                // if currentNode.next is null that means that
                // newNode will become the tail of the queue
                if(currentNode.Next == null){
                    currentNode.Next = newNode;
                    break;
                }else{
                    previousNode = currentNode;
                    currentNode = currentNode.Next;
                }
            }else{
             // newNode has found a node that has a smaller value
             // proceed to set its position between previousNode
             // and currentNode and break the loop
             previousNode.Next = newNode;
             newNode.Next = currentNode;
             break;
            }
        }
    }
    public static QNode getTreeInPreOrder(Tree tree){
        QNode currentNode;
        if(tree.Root == null){
            currentNode = null;
        }else{
            currentNode = new QNode(tree.Root, null);
        }
        QNode firstNode = currentNode;
        while(currentNode != null){
            QNode rightNode = null;
            QNode leftNode = null;
            if(currentNode.TNode.Right != null){
                rightNode = new QNode(currentNode.TNode.Right, null);
                // if currentNode has a next node than we need to set 
                // right node before that next node
                if(currentNode.Next != null){
                    rightNode.Next = currentNode.Next;
                    currentNode.Next = rightNode;
                }else{
                    currentNode.Next = rightNode;
                }
            }
            if(currentNode.TNode.Left != null){
                leftNode = new QNode(currentNode.TNode.Left, null);
                // if currentNode has a next node than we need to set
                // left node before that next node
                if(currentNode.Next != null){
                    leftNode.Next = currentNode.Next;
                    currentNode.Next = leftNode;
                }else{
                    currentNode.Next = leftNode;
                }
            }
            currentNode = currentNode.Next;
        }
        return firstNode;
    }
    public static ArrayList<Integer> getQNodeAsArrayList(QNode node){
        QNode currentNode = node;
        ArrayList<Integer> nodes = new ArrayList<>();
        while(currentNode != null){
            nodes.add(currentNode.TNode.Val);
            currentNode = currentNode.Next;
        }
        return nodes;
    }
}
