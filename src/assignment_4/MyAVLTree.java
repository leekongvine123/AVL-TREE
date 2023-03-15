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
public class MyAVLTree {

    private Node root;

    private int size;

    public MyAVLTree() {
    }

    public MyAVLTree(Node root, int size) {
        this.root = root;
        this.size = size;
    }

   int size() {
        return size;
    }

   int height(Node node) {
        if (node == null) {
            return 0;
        } else {
            return node.height;
        }
    }

     boolean isEmpty() {
        return size == 0;
    }

    private int max(int key1, int key2) {
        if (key1 <= key2) {
            return key2;
        } else {
            return key1;
        }
    }
    

    public void insert(Phone x) {
        if(!isExisting(x.Id)){
        this.root = insert(this.root, x);
        }
    }

    private Node insert(Node node, Phone x) {
        if (node == null) {
            size++;
            return new Node(x);
        }

        if (node.equal(x.Id)) {
            return node;
        }

        if (node.lessThan(x.Id)) {
            node.right = insert(node.right, x);
        } else {
            node.left = insert(node.left, x);
        }

        node.height = max(height(node.left), height(node.right)) + 1;

        int balance = balanceVector(node);

        if (balance > 1) {
            if (node.left.lessThan(x.Id)) {
                node.left = leftRotation(node.left);
            }
            return rightRotation(node);
        } else if (balance < -1) {
            if (node.right.greaterThan(x.Id)) {
                node.right = leftRotation(node.right);
            }
            return leftRotation(node);
        }
        return node;
    }

    private int balanceVector(Node p) {
        if (p == null) {
            return 0;
        } else {
            return height(p.left) - height(p.right);
        }
    }

    private Node leftRotation(Node node) {
        if (node == null) {
            return null;
        }

        Node returnNode = node.right;
        node.right = returnNode.left;
        returnNode.left = node;

        node.height = max(height(node.left), height(node.right)) + 1;
        returnNode.height = max(height(returnNode.left), height(returnNode.right)) + 1;
        return returnNode;
    }

    private Node rightRotation(Node node) {
        if (node == null) {
            return null;
        }

        Node returnNode = node.left;
        node.left = returnNode.right;
        returnNode.right = node;

        node.height = max(height(node.left), height(node.right)) + 1;
        returnNode.height = max(height(returnNode.left), height(returnNode.right)) + 1;
        return returnNode;
    }

    private void visit(Node p) {
        System.out.println(p.info);
    }

    public void inOrder() {
        inOrder(root);
    }

    private void inOrder(Node node) {
        if (node == null) {
            return;
        }
        inOrder(node.left);
        System.out.printf("%s ", node.info);
        inOrder(node.right);
    }

    public void preOrder() {
        preOrder(root);
    }

    private void preOrder(Node node) {
        if (node == null) {
            return;
        }
        System.out.printf("%s ", node.info);
        preOrder(node.left);
        preOrder(node.right);
    }

    public void postOrder() {
        postOrder(root);
    }

    private void postOrder(Node node) {
        if (node == null) {
            return;
        }
        postOrder(node.left);
        postOrder(node.right);
        System.out.printf("%s ", node.info);
    }

    public void breadth() throws Exception {
        if (root == null) {
            return;
        }
        MyQueue q = new MyQueue();
        q.enqueue(root);
        Node p;
        while (!q.isEmpty()) {
            p = (Node) q.dequeue();
            if (p.left != null) {
                q.enqueue(p.left);
            }
            if (p.right != null) {
                q.enqueue(p.right);
            }
            visit(p);
        }
    }

    private Node search(Node p, int x) {
        if (isEmpty()) {
            return null;
        }
        Node cur = root;
        Node f = null;
        while (cur != null) {
            if (cur.info.Id == x) {
                return cur;
            } else {
                f = cur;
                cur = (cur.info.Id < x) ? cur.right : cur.left;
            }
        }
        return (f.info.Id != x) ? null : f;
    }

    private Node balances(Node node) {
        int balance = balanceVector(node);

        if (balance > 1 && balanceVector(node.left) >= 0) {
            return rightRotation(node);
        }

        if (balance > 1 && balanceVector(node.left) < 0) {
            root.left = leftRotation(root.left);
            return rightRotation(node);
        }

        if (balance < -1 && balanceVector(node.right) <= 0) {
            return leftRotation(node);
        }

        if (balance < -1 && balanceVector(node.right) > 0) {
            root.right = rightRotation(node.right);
            return leftRotation(node);
        }
        return node;
    }

   private boolean contains(int elem) { //search
        return contains(root, elem);
    }

    private boolean contains(Node node, int elem) { //dùng recursion
        if (node == null) {
            return false;
        }

        if (elem < node.info.Id) {
            return contains(node.left, elem);
        } else if (elem > node.info.Id) {
            return contains(node.right, elem);
        } else {
            return true;
        }
    }

    public void deleteByCopying(int x) {
        if (!contains(x)) {
            System.out.println("Does not exist");
        }

        root = deleteByCopying(root, x);
    }

    private Node deleteByCopying(Node node, int elem) {

        if (elem > node.info.Id) {
            node.right = deleteByCopying(node.right, elem);
        } else if (elem < node.info.Id) {
            node.left = deleteByCopying(node.left, elem);
        } else {
            if (node.left == null) {
                Node rightNode = node.right;
                node = null;
                return rightNode;

            } else if (node.right == null) {
                Node leftNode = node.left;
                node = null;

                return leftNode;
            } else {
                Phone term = minvalue(node.right);

                node.info = term;
                node.right = deleteByCopying(node.right, term.Id);

            }
        }
        node.height = max(height(node.left), height(node.right)) + 1;
        int balance = balanceVector(node);

        if (balance > 1 && balanceVector(node.left) >= 0) {
            return rightRotation(node);
        }

        if (balance > 1 && balanceVector(node.left) < 0) {
            root.left = leftRotation(root.left);
            return rightRotation(node);
        }

        if (balance < -1 && balanceVector(node.right) <= 0) {
            return leftRotation(node);
        }

        if (balance < -1 && balanceVector(node.right) > 0) {
            root.right = rightRotation(node.right);
            return leftRotation(node);
        }

        return node;
    }

    public void deleteByMerging(int x) {
        if (!contains(x)) {
            System.out.println("Does not exist");
        }
        root = deleteByMergingLeft(root, x);
    }

    private Node deleteByMergingLeft(Node node, int elem) {

        if (elem > node.getInfo().getId()) {
            node.setRight(deleteByMergingLeft(node.right, elem));
        } else if (elem < node.getInfo().getId()) {
            node.setLeft(deleteByMergingLeft(node.left, elem));
        } else {

            if (node.getLeft() == null) {
                Node rightNode = node.getRight();
                node = null;

                return rightNode;

            } else {

                Node maxLeftPoint = maxvalue(node.getLeft());
                maxLeftPoint.setRight(node.getRight());

                node.setInfo(node.getLeft().getInfo());

                node.setRight(null);
                node.setRight(node.getLeft().getRight());

                Node newLeft = node.getLeft().getLeft();
                node.setLeft(null);
                node.setLeft(newLeft);

            }

        }

        node.height = max(height(node.left), height(node.right)) + 1;

        int balance = balanceVector(node);

        if (balance > 1 && balanceVector(node.left) >= 0) {
            return rightRotation(node);
        }

        if (balance > 1 && balanceVector(node.left) < 0) {
            node.left = leftRotation(node.left);
            return rightRotation(node);
        }

        if (balance < -1 && balanceVector(node.right) <= 0) {
            return leftRotation(node);
        }

        if (balance < -1 && balanceVector(node.right) > 0) {
            node.right = rightRotation(node.right);
            return leftRotation(node);
        }

        return node;
    }

    public void deleteNode(int x) {
        root = deleteNode(root, x);
    }

    private Node deleteNode(Node p, int x) {
        if (p == null) {
            return p;
        }
        if (x < p.info.Id) {
            p.left = deleteNode(p.left, x);
        } else if (x > p.info.Id) {
            p.right = deleteNode(p.right, x);
        } else {
            // Found the node to be deleted
            if (p.left == null || p.right == null) {
                // Case 1: Node has 0 or 1 child
                Node temp = null;
                if (p.left == null) {
                    temp = p.right;
                } else {
                    temp = p.left;
                }
                if (temp == null) {
                    // Node has no child
                    temp = p;
                    p = null;
                } else {
                    // Node has one child
                    p = temp;
                }
            } else {
                // Case 2: Node has 2 children
                Node temp = minValueNode(p.right);
                p.info = temp.info;
                p.right = deleteNode(p.right, temp.info.Id);
            }
        }

        if (p == null) {
            return p;
        }
        p = balance(p);
        return p;
    }

    private int balanceFactor(Node p) {
        if (p == null) {
            return 0;
        }
        return height(p.right) - height(p.left);
    }

    private Node balance(Node p) {
        if (balanceFactor(p) == 2) {
            if (balanceFactor(p.right) < 0) {
                p.right = rightRotation(p.right);
            }
            return leftRotation(p);
        } else if (balanceFactor(p) == -2) {
            if (balanceFactor(p.left) > 0) {
                p.left = leftRotation(p.left);
            }
            return rightRotation(p);
        }
        return p;
    }

    private Node search(Phone x) {
        if (isEmpty()) {
            return null;
        }
        Node cur = root;
        Node f = null;
        while (cur != null) {
            if (cur.info.Id == x.Id) {
                return cur;
            } else {
                f = cur;
                cur = (cur.info.Id < x.Id) ? cur.right : cur.left;
            }
        }
        return (f.info.Id != x.Id) ? null : f;
    }

    private Node searchB(int x) {
        if (isEmpty()) {
            return null;
        }
        Node cur = root;
        Node f = null;
        while (cur != null) {
            if (cur.info.Id == x) {
                return cur;
            } else {
                f = cur;
                cur = (cur.info.Id < x) ? cur.right : cur.left;
            }
        }
        return (f.info.Id != x) ? null : f;
    }

    public void search(int x) {
        try {
            System.out.println(search(searchB(x).info).info);
        } catch (Exception e) {

        }

    }
     private boolean isExisting(int x) {
        try {
         if(search(searchB(x).info).info!=null){
             return true;
         }
        } catch (Exception e) {
return false;
        }
return false;
    }
     

    private Node find_Min_price(Node node) {
        if (node == null || (node.left == null && node.right == null)) {
            return node;
        }
        Phone minPhone = node.info;
        if (node.left != null) {
            Phone leftMinPhone = find_Min_price(node.left).info;
            if (leftMinPhone.price < minPhone.price) {
                minPhone = leftMinPhone;
            }
        }
        if (node.right != null) {
            Phone rightMinPhone = find_Min_price(node.right).info;
            if (rightMinPhone.price < minPhone.price) {
                minPhone = rightMinPhone;
            }
        }
        return new Node(minPhone);
    }

    public Phone find_Min_price() {
        Node node = find_Min_price(root);
        return node == null ? null : node.info;
    }

    private Node find_Max_Value(Node node) {
        if (node == null || (node.left == null && node.right == null)) {
            return node;
        }
        Phone maxPhone = node.info;
        if (node.left != null) {
            Phone leftMaxPhone = find_Max_Value(node.left).info;
            if (leftMaxPhone.price * leftMaxPhone.amount > maxPhone.price * maxPhone.amount) {
                maxPhone = leftMaxPhone;
            }
        }
        if (node.right != null) {
            Phone rightMaxPhone = find_Max_Value(node.right).info;
            if (rightMaxPhone.price * rightMaxPhone.amount > maxPhone.price * maxPhone.amount) {
                maxPhone = rightMaxPhone;
            }
        }
        return new Node(maxPhone);
    }

    public Phone find_max_Value() {
        Node node = find_Max_Value(root);
        return node == null ? null : node.info;
    }

    private Node find_Newest_Phone(Node node) {
        if (node == null || (node.left == null && node.right == null)) {
            return node;
        }
        Phone maxPhone = node.info;
        if (node.left != null) {
            Phone leftMaxPhone = find_Newest_Phone(node.left).info;
            if (leftMaxPhone.year > maxPhone.year) {
                maxPhone = leftMaxPhone;
            }
        }
        if (node.right != null) {
            Phone rightMaxPhone = find_Newest_Phone(node.right).info;
            if (rightMaxPhone.year > maxPhone.year) {
                maxPhone = rightMaxPhone;
            }
        }
        return new Node(maxPhone);
    }

    public Phone find_Newest_Phone() {
        Node node = find_Newest_Phone(root);
        return node == null ? null : node.info;
    }

    private Node maxvalue(Node p) {
        while (p.right != null) {
            p = p.right;
        }
        return p;
    }

   private Node minValueNode(Node node) {
        Node current = node;

        /* loop down to find the leftmost leaf */
        while (current.left != null) {
            current = current.left;
        }

        return current;
    }

  private  Phone minvalue(Node p) {
        Phone minv = p.info;
        while (p.left != null) {
            minv = p.left.info;
            p = p.left;
        }
        return minv;
    }
    
}
