package domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AVLTest {

    @Test
    void test() {
        try {
            AVL avl = new AVL();

            //Punto a
            avl.add(1);
            avl.add(2);
            avl.add(3);
            avl.add(4);
            avl.add(5);
            avl.add(6);
            avl.add(7);
            avl.add(15);
            avl.add(14);
            avl.add(13);
            avl.add(12);
            avl.add(11);
            avl.add(10);
            avl.add(9);
            avl.add(8);

            //Punto 5.b
            System.out.println(avl.toString());
            //Punto 5.c
            System.out.println("MIN and MAX");
            System.out.println("Min: "+avl.min()+"\nMax: "+avl.max());
            System.out.println("\nIs it Balanced? "+avl.isBalanced());
            System.out.println("");

            //Punto 5.f
            System.out.println("\nROTATION SEQUENCE\n"+avl.getSequence());

            //Punto 5.g
            System.out.println("Removing 11"); avl.remove(11);
            System.out.println("Removing 13"); avl.remove(13);
            System.out.println("Removing 15"); avl.remove(15);


            //Punto 5.h
            System.out.println(avl.toString());
            //Punto 5.d, 5.e
            System.out.println("Is it Balanced? "+avl.isBalanced());
            System.out.println(isBalanced(avl)
                    ?"\n(Testing Class method) AVL tree is balanced!!!"
                    :"\n(Testing Class method) AVL tree is not balanced"
            );
            //Punto 5.i
            System.out.println("Binary Search Tree is balanced? "+avl.isBalanced());
            //Punto 5.j
            // ver en remove en AVL
            // Punto 5.k
            System.out.println(avl.toString());
            //Punto 5.l
            System.out.println("Binary Search Tree is balanced? "+avl.isBalanced());


        }catch (TreeException ex){
            throw new RuntimeException(ex);
        }
    }

    public boolean isBalanced(Tree bst) throws TreeException {
        if (bst.isEmpty()) {
            throw new TreeException("AVL Binary Search Tree is empty");
        }
        if(bst instanceof BST) return ((BST)bst).isBalanced();
        if(bst instanceof AVL) return ((AVL)bst).isBalanced();
        return false;
    }




}