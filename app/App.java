package app;
import java.util.ArrayList;
import java.util.Scanner;

import tree.Node;
import tree.Tree;

public class App{
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
            }
        }
    }
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
    public void addNode(){
        System.out.println("please enter the value of the new node");
        try {
            int nodeValue = this.AppScanner.nextInt();
            Node newNode = new Node(nodeValue);
            this.AppTree.add(newNode);
            System.out.println("success in adding node");
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
    public void deleteNode(){
        try {
            System.out.println("please enter the value of the node to delete");
            int nodeVal = this.AppScanner.nextInt();
            this.AppTree.delete(nodeVal);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
    public void printInOrder(){
        ArrayList<Integer> nodes = this.AppTree.traverseInOrder();
        System.out.println(nodes.toString());
    }
    public void printPreOrder(){
        ArrayList<Integer> nodes = this.AppTree.traverseInPreOrder();
        System.out.println(nodes.toString());
    }
    public void printPostOrder(){
        ArrayList<Integer> nodes = this.AppTree.traverseInPostOrder();
        System.out.println(nodes.toString());
    }
}