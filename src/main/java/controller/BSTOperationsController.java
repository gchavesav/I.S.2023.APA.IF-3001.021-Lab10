package controller;

import domain.BST;
import domain.BTree;
import domain.BTreeNode;
import domain.TreeException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

public class BSTOperationsController {

    @FXML
    private Pane drawPane;
    private BST bst;
    private Alert alert;
    private TextInputDialog dialog;

    @FXML
    public void initialize() {
        this.alert = util.FXUtility.alert("", "");
        randomize(20);
        drawTree(bst.getRoot(), 600, 50, 500);
    }

    @FXML
    void onActionRandomize(ActionEvent event) {

    }

    @FXML
    void onActionAdd(ActionEvent event) {

    }

    @FXML
    void onActionNodeHeight(ActionEvent event) {


    }

    @FXML
    void onActionContains(ActionEvent event) {
        dialog = util.FXUtility.dialog("Node Contains", "Contains: ");
        dialog.showAndWait();
        int value = Integer.parseInt(dialog.getResult());
        this.alert = util.FXUtility.alert("Node Contains", "Contains: ");
        alert.setAlertType(Alert.AlertType.INFORMATION);
        try {
            alert.setContentText(String.valueOf(bst.contains(value)));
            alert.showAndWait();
        } catch (TreeException e) {
            throw new RuntimeException(e);
        }
    }


    @FXML
    void onActionRemove(ActionEvent event) {
        try {
            boolean insertado = false;
            do {
                int value = util.Utility.random(50);
                if (bst.contains(value)) {
                    bst.remove(value);
                    this.alert = util.FXUtility.alert("Node to remove", "Remove: ");
                    alert.setContentText("Node " + value + " removed");
                    alert.showAndWait();
                    drawPane.getChildren().clear();
                    drawTree(bst.getRoot(), 600, 50, 500);
                    insertado = true;
                }
            } while (!insertado);

        } catch (TreeException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void onActionTreeHeight(ActionEvent event) {
        this.alert = util.FXUtility.alert("Tree Height", "Tree Height: ");
        alert.setAlertType(Alert.AlertType.INFORMATION);
        try {
            alert.setContentText(String.valueOf(bst.height()));
            alert.showAndWait();
        } catch (TreeException e) {
            throw new RuntimeException(e);
        }
    }


    private void drawTree(BTreeNode node, double x, double y, double levelWidth) {
        if (node != null) {
            // Dibujar las conexiones con los nodos hijos primero
            if (node.left != null) {
                double childX = x - levelWidth / 2;
                double childY = y + 40;
                drawTree(node.left, childX, childY, levelWidth / 1.8);

                // Dibujar una línea desde el nodo actual al nodo hijo izquierdo
                Line line = new Line(x, y, childX, childY - 20);
                drawPane.getChildren().add(line);
            }

            if (node.right != null) {
                double childX = x + levelWidth / 2;
                double childY = y + 40;
                drawTree(node.right, childX, childY, levelWidth / 1.8);

                // Dibujar una línea desde el nodo actual al nodo hijo derecho
                Line line = new Line(x, y, childX, childY - 20);
                drawPane.getChildren().add(line);
            }

            // Dibujar el nodo actual como un círculo después de las líneas
            Circle circle = new Circle(x, y, 20);
            circle.setFill(Color.LIGHTGREEN); // Cambiar el color a verde claro
            drawPane.getChildren().add(circle);

            // Mostrar el valor del nodo
            Text text = new Text(String.valueOf(node.data));
            text.setX(x - 5);
            text.setY(y + 5);
            drawPane.getChildren().add(text);
        }
    }

    private void randomize(int n) {
        bst = new BST();
        for (int i = 0; i < n; i++) {
            bst.add(util.Utility.random(50));
        }
    }
}
