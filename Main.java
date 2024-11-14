import java.util.ArrayList;

import tree.Node;
import tree.Tree;

public class Main{
    public static void main(String[] args) {
        Node rootNode = new Node(4);
        Node secondNode = new Node(2);
        Node thirdNode = new Node(6);
        Node fourthNode = new Node(1);
        Node fifthNode = new Node(3);
        Node sixthNode = new Node(5);
        Node seventhNode = new Node(7);
        Tree AppTree = new Tree(null);
        AppTree = new Tree(rootNode);
        AppTree.add(secondNode);
        AppTree.add(thirdNode);
        AppTree.add(fourthNode);
        AppTree.add(fifthNode);
        AppTree.add(sixthNode);
        AppTree.add(seventhNode);
        ArrayList<Integer> nodes = AppTree.traverseInPreOrder();
        System.out.println(nodes.toString());
    }
}