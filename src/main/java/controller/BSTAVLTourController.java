package controller;

import domain.AVL;
import domain.BST;
import domain.BTreeNode;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

public class BSTAVLTourController {

    @FXML
    private Button btnInOrder;

    @FXML
    private Button btnPostOrder;

    @FXML
    private Button btnPreOrder;

    @FXML
    private Button btnRandomize;

    @FXML
    private Label tourNameLabel;
    private double dVertical = 50;
    private int counter = 1;
    private int SIZE = 20;
    @FXML
    private Pane treePane;
    @FXML
    private RadioButton AVLRadioButton;

    @FXML
    private RadioButton BSTRadioButton;
    private AVL avl = new AVL();
    private BST bst = new BST();

    @FXML
    public void initialize() {

        fill(SIZE);
        if (BSTRadioButton.isSelected()) {
            if (bst.getRoot() != null) {
                drawTree(bst.getRoot(), 400, 50, 350);
            }
        } else if (AVLRadioButton.isSelected()) {
            if (avl.getRoot() != null) {
                drawTree(avl.getRoot(), 400, 50, 350);
            }
        }
    }

    public void fill(int size) {

        for (int i = 0; i < size; i++) {
            if (BSTRadioButton.isSelected()) {
                bst.add(util.Utility.random(50));
            } else if (AVLRadioButton.isSelected()) {
                avl.add( util.Utility.random(50));
            }
        }
    }

/*
    public void displayTree() {
        if (BSTRadioButton.isSelected()) {
            if (bst.getRoot() != null) {
                drawTree(bst.getRoot(), 400, 50, 350);
            }
        } else if (AVLRadioButton.isSelected()) {
            if (avl.getRoot() != null) {
                drawTree(avl.getRoot(), 400, 50, 350);
            }
        }
    }
*/

    private void drawTree(BTreeNode node, double x, double y, double levelWidth) {
        if (node != null) {
            // Dibujar las conexiones con los nodos hijos primero
            if (node.left != null) {
                double childX = x - levelWidth / 2;
                double childY = y + 50;
                drawTree(node.left, childX, childY, levelWidth / 2);

                // Dibujar una línea desde el nodo actual al nodo hijo izquierdo
                Line line = new Line(x, y, childX, childY - 20);
                treePane.getChildren().add(line);
            }

            if (node.right != null) {
                double childX = x + levelWidth / 2;
                double childY = y + 50;
                drawTree(node.right, childX, childY, levelWidth / 2);

                // Dibujar una línea desde el nodo actual al nodo hijo derecho
                Line line = new Line(x, y, childX, childY - 20);
                treePane.getChildren().add(line);
            }

            // Dibujar el nodo actual como un círculo después de las líneas
            Circle circle = new Circle(x, y, 15);
            circle.setFill(Color.LIGHTGREEN); // Cambiar el color a verde claro
            treePane.getChildren().add(circle);

            // Mostrar el valor del nodo
            Text text = new Text(String.valueOf(node.data));
            text.setX(x - 5);
            text.setY(y + 5);
            treePane.getChildren().add(text);
        }
    }
    public void displayInOrder() {
        counter = 1;
        if (BSTRadioButton.isSelected()) {
            if (bst.getRoot() != null) {
                displayInOrder(bst.getRoot(), treePane.getPrefWidth() / 2, dVertical, treePane.getPrefWidth() / 4);
            }
        } else if (AVLRadioButton.isSelected()) {
            if (avl.getRoot() != null) {
                displayInOrder(avl.getRoot(), treePane.getPrefWidth() / 2, dVertical, treePane.getPrefWidth() / 4);
            }
        }


    }

    private void displayInOrder(BTreeNode node, double x, double y, double dHorizontal) {
        if (node != null) {
            displayInOrder(node.left, x - dHorizontal, y + dVertical, dHorizontal / 2);
            treePane.getChildren().addAll(new Text(x - 5, y + 30, "" + counter));
            counter++;
            displayInOrder(node.right, x + dHorizontal, y + dVertical, dHorizontal / 2);
        }
    }

    public void displayBSTPreOrder() {
        counter = 1;
        if (BSTRadioButton.isSelected()) {
            if (bst.getRoot() != null) {
                displayBSTPreOrder(bst.getRoot(), treePane.getPrefWidth() / 2, dVertical, treePane.getPrefWidth() / 4);
            }
        } else if (AVLRadioButton.isSelected()) {
            if (avl.getRoot() != null) {
                displayBSTPreOrder(avl.getRoot(), treePane.getPrefWidth() / 2, dVertical, treePane.getPrefWidth() / 4);
            }
        }

    }

    private void displayBSTPreOrder(BTreeNode node, double x, double y, double dHorizontal) {
        if (node != null) {
            treePane.getChildren().addAll(new Text(x - 5, y + 30, "" + counter));
            counter++;
            displayBSTPreOrder(node.left, x - dHorizontal, y + dVertical, dHorizontal / 2);
            displayBSTPreOrder(node.right, x + dHorizontal, y + dVertical, dHorizontal / 2);

        }
    }

    public void displayBSTPostOrder() {
        counter = 1;
        if (BSTRadioButton.isSelected()) {
            if (bst.getRoot() != null) {
                displayBSTPostOrder(bst.getRoot(), treePane.getPrefWidth() / 2, dVertical, treePane.getPrefWidth() / 4);
            }
        } else if (AVLRadioButton.isSelected()) {
            if (avl.getRoot() != null) {
                displayBSTPostOrder(avl.getRoot(), treePane.getPrefWidth() / 2, dVertical, treePane.getPrefWidth() / 4);
            }
        }

    }

    private void displayBSTPostOrder(BTreeNode node, double x, double y, double dHorizontal) {
        if (node != null) {
            displayBSTPostOrder(node.left, x - dHorizontal, y + dVertical, dHorizontal / 2);
            displayBSTPostOrder(node.right, x + dHorizontal, y + dVertical, dHorizontal / 2);
            treePane.getChildren().addAll(new Text(x - 5, y + 30, "" + counter));
            counter++;
        }
    }

    @FXML
    void btnInOrder(ActionEvent event) {
        treePane.getChildren().clear();
        if (BSTRadioButton.isSelected()) {
            if (bst.getRoot() != null) {
                drawTree(bst.getRoot(), 400, 50, 350);
            }
        } else if (AVLRadioButton.isSelected()) {
            if (avl.getRoot() != null) {
                drawTree(avl.getRoot(), 400, 50, 350);
            }
        }
        tourNameLabel.setText("In Order Transversal Tour (L-N-R)");
        displayInOrder();
    }

    @FXML
    void btnPostOrder(ActionEvent event) {
        treePane.getChildren().clear();
        if (BSTRadioButton.isSelected()) {
            if (bst.getRoot() != null) {
                drawTree(bst.getRoot(), 400, 50, 350);
            }
        } else if (AVLRadioButton.isSelected()) {
            if (avl.getRoot() != null) {
                drawTree(avl.getRoot(), 400, 50, 350);
            }
        }
        tourNameLabel.setText("Post Order Transversal Tour (L-R-N)");
        displayBSTPostOrder();
    }

    @FXML
    void btnPreOrder(ActionEvent event) {
        treePane.getChildren().clear();
        if (BSTRadioButton.isSelected()) {
            if (bst.getRoot() != null) {
                drawTree(bst.getRoot(), 400, 50, 350);
            }
        } else if (AVLRadioButton.isSelected()) {
            if (avl.getRoot() != null) {
                drawTree(avl.getRoot(), 400, 50, 350);
            }
        }
        tourNameLabel.setText("Pre Order Transversal Tour (N-L-R)");
        displayBSTPreOrder();

    }

    @FXML
    void btnRandomize(ActionEvent event) {
        tourNameLabel.setText("");
        treePane.getChildren().clear(); // Clear the pane
        avl.clear();
        bst.clear();
        fill(SIZE);
        int random = util.Utility.random(1, 3);
        if (random == 1) {
            btnPreOrder(event);
        }
        if (random == 2) {
            btnInOrder(event);
        }
        if (random == 3) {
            btnPostOrder(event);
        }
    }

    @FXML
    void BSTOnAction(ActionEvent event) {
        this.AVLRadioButton.setSelected(!this.AVLRadioButton.selectedProperty().getValue());
        btnRandomize(event);
    }

    @FXML
    void AVLOnAction(ActionEvent event) {
        this.BSTRadioButton.setSelected(!this.BSTRadioButton.selectedProperty().getValue());
        btnRandomize(event);

    }


}
