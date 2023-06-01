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

            // Prueba de isBalanced()
           if(isBalanced(bst)){
               System.out.println("The tree is balanced");
           }else {
               System.out.println("The tree is not balanced");
           }





        }catch (TreeException ex){
            throw new RuntimeException(ex);
        }
    }

    public boolean isBalanced(Tree bst) throws TreeException {
        if (bst.isEmpty()) {
            throw new TreeException("Binary Search Tree is empty");
        }
        return bst.isBalanced();
    }
}