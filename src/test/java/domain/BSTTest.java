package domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BSTTest {

    @Test
    void test() throws TreeException {
        //4.a
        BST bst= new BST();
        for (int i = 0; i < 70; i++) {
            bst.add(util.Utility.random(200));
        }
        //4.b
        System.out.println(bst);
        //4.c
        System.out.println("Binary Search Tree size is: "+bst.size());
        System.out.println("Binary Search Tree min element is: "+bst.min()+
                "\nBinary Search Tree max element is: "+bst.max());
        //4.d
        System.out.println("Binary Search Tree contains " +1+"?: " +bst.contains(1)
        +"\nBinary Search Tree contains "+3+"?: "  +bst.contains(3)+
                "\nBinary Search Tree contains "+10+"?: "  +bst.contains(10)+
                "\nBinary Search Tree contains " +62+"?: " + bst.contains(62)+
                "\nBinary Search Tree contains "+5+"?; " +bst.contains(5));
        //4.g
        bst.remove(1);
        bst.remove(3);
        bst.remove(10);
        bst.remove(62);
        bst.remove(5);
        //4.h
        System.out.println(bst);
        //4.i
        if (bst.isBalanced()){
            System.out.println("Binary Search Tree is balanced");
        }else {
            System.out.println("Binary Search Tree is not balanced");
        }
        System.out.println(isBalanced(bst)
                ?"\n(Testing Class method) BST tree is balanced!!!"
                :"\n(Testing Class method) BST tree is not balanced"
        );

        //4.j altura de cada nodo

        //4.k modify
        System.out.println("Modifying 10, new value 201");
        System.out.println("Adding 10..."); bst.add(10);
        System.out.println(bst);
        bst.modify(10,201); // Se agrega el 10 para asegurarse de que el objeto buscado este presente y no salte la excepción.
        System.out.println(bst);

    }

    public boolean isBalanced(Tree bst) throws TreeException {
        if (bst.isEmpty()) {
            throw new TreeException("Binary Search Tree is empty");
        }
        if(bst instanceof BST) return ((BST)bst).isBalanced();
        if(bst instanceof AVL) return ((AVL)bst).isBalanced();
        return false;
    }

}