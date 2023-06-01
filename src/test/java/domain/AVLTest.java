package domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AVLTest {

    @Test
    void test() {
        try {
            AVL avl = new AVL();
            for (int i = 0; i < 20; i++) {
                avl.add(util.Utility.random(99));
            }
            System.out.println(avl);
            System.out.println("Size: " + avl.size() + ". Height: " + avl.height());
            System.out.println("Min: "+avl.min()+". Max: "+avl.max());



            // Prueba de isBalanced()
            if(avl.isBalanced()){
                System.out.println("The tree is balanced");
            }else {
                System.out.println("The tree is not balanced");
            }






        }catch (TreeException ex){
            throw new RuntimeException(ex);
        }
    }
}