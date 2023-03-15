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
public class Assignment_4 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        MyAVLTree m = new MyAVLTree();
        m.insert(new Phone(1, "vinh", 2003, 123, 5));
        m.insert(new Phone(2, "vinh", 2029, 123, 4));
        m.insert(new Phone(2, "v", 2029, 123, 4));
        m.insert(new Phone(3, "vinh", 2020, 123, 6));
        m.insert(new Phone(4, "vinh", 2009, 123, 5));
         m.insert(new Phone(6, "vinh", 2006, 123, 3));
        m.insert(new Phone(7, "vinh", 2007, 123, 1));

         m.breadth();
        System.out.println("\n");
      
    }

}
