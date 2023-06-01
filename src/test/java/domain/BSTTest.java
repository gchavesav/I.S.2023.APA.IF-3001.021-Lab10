package domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BSTTest {

    @Test
    void test() {
        try {
            BST bst = new BST();
            for (int i = 0; i < 50; i++) {
                bst.add(util.Utility.random(99));
            }
            System.out.println(bst);
            System.out.println("Size: " + bst.size() + ". Height: " + bst.height());
            System.out.println("Min: "+bst.min()+". Max: "+bst.max());
        }catch (TreeException ex){
            throw new RuntimeException(ex);
        }
    }

    @Test
    void testDiego(){
        BST bst = new BST();

        // Agregar elementos al árbol
        bst.add(50);
        bst.add(30);
        bst.add(20);
        bst.add(40);
        bst.add(70);
        bst.add(60);
        bst.add(80);

        try {
            // Imprimir la altura de cada elemento usando preOrderPrintElementHeights
            System.out.println("Height of each element using preOrderPrintElementHeights:");
            bst.preOrderPrintElementHeights(bst.getRoot(), 0);

            // Imprimir la altura de cada elemento usando printElementHeights
            System.out.println("\nHeight of each element using printElementHeights:");
            bst.printElementHeights();
        } catch (TreeException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}




