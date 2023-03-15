/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment_4;

/**
 *
 * @author Admin
 */
public class Node {
    Phone info;
    int height;
    Node left;
    Node right;

    public Node(Phone x) {
        this.info =x;
        this.height=1;
        this.left = null;
        this.right =null;
    }

    public Phone getInfo() {
        return info;
    }

    public void setInfo(Phone info) {
        this.info = info;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }
   
    public void printInfo(){
        System.out.println("Key: "+ this.info.Id+";height: "+this.height+ "\n");
        
    }
    public boolean greaterThan(int key){
        return this.info.Id > key;
    }
   public boolean lessThan(int key){
        return this.info.Id < key;
    }
   public boolean equal(int key){
        return this.info.Id == key;
    }

    @Override
    public String toString() {
        return "Node{" + "info=" + info + ", height=" + height + ", left=" + left + ", right=" + right + '}';
    }
   
}
