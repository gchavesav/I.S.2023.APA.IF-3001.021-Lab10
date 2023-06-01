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
            //Punto 5.g
            avl.remove(11);
            avl.remove(13);
            avl.remove(15);


            //Punto 5.h
            System.out.println(avl.toString());
            //Punto 5.i
            System.out.println("Is it Balanced? "+avl.isBalanced());



        }catch (TreeException ex){
            throw new RuntimeException(ex);
        }
    }



}