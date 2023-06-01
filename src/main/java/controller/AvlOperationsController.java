package controller;

import domain.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

public class AvlOperationsController
{
    @javafx.fxml.FXML
    private Pane treePane;
    private AVL avl = new AVL();
    private double dVertical = 50;
    private Alert alert;
    private Dialog dialog;
    private TextInputDialog dialog1;
    @FXML
    private Text txtIsBalanced;

    @javafx.fxml.FXML
    public void initialize() {
        this.alert = util.FXUtility.alert("", "");
        randomize(20);
        drawTree(avl.getRoot(), 400, 50, 350);
    }
    private void drawTree(BTreeNode node, double x, double y, double levelWidth) {
        //Gabriel y Blanca
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

    private void randomize(int n) {
        //Gabriel y Blanca
        avl = new AVL();
        for (int i = 0; i < n; i++) {
            avl.add(util.Utility.random(50));
        }
    }
    public void btnAdd(ActionEvent actionEvent) throws TreeException {
        //Gabriel
        int newNode = util.Utility.random(50);
        if (!avl.contains(newNode)) {
            avl.add(newNode);
            drawTree(avl.getRoot(), 400, 50, 350);
            alert.setAlertType(Alert.AlertType.INFORMATION);
            alert.setContentText("The node: " + newNode + " has been added successfully!");
            alert.showAndWait();
        }else{
            this.alert = util.FXUtility.alert("Error", "Node not added");
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setContentText("Node already exist in this Tree: "+ newNode);
            alert.showAndWait();
        }

    }
    public void btnIsBalanced(ActionEvent actionEvent) {
        //Gabriel
        try {
            if (avl.isBalanced()){
                txtIsBalanced.setText("Está balanceado");
            }else if (!avl.isBalanced()){
                txtIsBalanced.setText("No está balanceado");
            }
        } catch (TreeException e) {
            throw new RuntimeException(e);
        }
    }
    public void btnNodeHeight(ActionEvent actionEvent) {
        //Gabriel
        dialog = util.FXUtility.dialog("Node Height", "Height: ");
        dialog.showAndWait();
        int value = Integer.parseInt(dialog.getResult().toString());
        this.alert = util.FXUtility.alert("Node Height", "Height: ");
        alert.setAlertType(Alert.AlertType.INFORMATION);
        try {
            alert.setContentText(String.valueOf(avl.height(value)));
            alert.showAndWait();
        } catch (TreeException e) {
            throw new RuntimeException(e);
        }

    }
    public void btnContains(ActionEvent actionEvent) {
        //Blanca
        dialog1 = util.FXUtility.dialog("Node Contains","Contains: ");
        dialog1.showAndWait();
        int value = Integer.parseInt(dialog1.getResult());
        this.alert=util.FXUtility.alert("Node Contains","Contains: ");
        alert.setAlertType(Alert.AlertType.INFORMATION);
        try {
            alert.setContentText(String.valueOf(avl.contains(value)));
            alert.showAndWait();
        } catch (TreeException e) {
            throw new RuntimeException(e);
        }

    }
    public void btnRemove(ActionEvent actionEvent) {
        //Blanca
        try {
            boolean insertado= false;
            do {
                int value = util.Utility.random(50);
                if (avl.contains(value)) {
                    avl.remove(value);
                    this.alert = util.FXUtility.alert("Node to remove", "Remove: ");
                    alert.setContentText("Node " + value + " removed.");
                    alert.showAndWait();
                    treePane.getChildren().clear();
                    drawTree(avl.getRoot(), 400, 50, 350);
                    insertado=true;
                }
            }while (!insertado);

        } catch (TreeException e) {
            throw new RuntimeException(e);
        }
    }
    public void btnTreeHeight(ActionEvent actionEvent) {
        //Blanca
        this.alert = util.FXUtility.alert("Tree Height", "Tree Height: ");
        alert.setAlertType(Alert.AlertType.INFORMATION);
        try {
            alert.setContentText(String.valueOf(avl.height()));
            alert.showAndWait();
        } catch (TreeException e) {
            throw new RuntimeException(e);
        }
    }
    public void btnRandomize(ActionEvent actionEvent) {
        treePane.getChildren().clear();
        avl.clear();
        randomize(20);
        drawTree(avl.getRoot(), 400, 50, 350);
    }
}