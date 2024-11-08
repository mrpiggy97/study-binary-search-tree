package tree;

import java.util.ArrayList;

public class Queue {
    public QNode Head;
    public Queue(QNode head){
        if(head == null){
            String message = "head in Queue cannot be null";
            throw new Error(message);
        }
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
}
