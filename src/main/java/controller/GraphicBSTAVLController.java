package controller;

import domain.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

public class GraphicBSTAVLController
{
    @FXML
    private BorderPane bp;
    @FXML
    private Label treeHeightTextField;
    @FXML
    private Label preOrderTextField;
    @FXML
    private Label inOrderTextField;
    @FXML
    private Label postOrderTextField;

    ///private Tree tree = null;
    //private BTree tree;
    private BST treeBST;
    private AVL treeAVL;

    @FXML
    private RadioButton radioAVL;

    @FXML
    private RadioButton radioBST;
    @FXML
    private Pane lienzo;

    @FXML
    private Label textBalanced;

    private Alert alert;

    private int typeTree = 0;


    private int countHeight;
    //1 arbol binario
    //2 arbol AVL

    @FXML
    public void initialize() throws TreeException {

        ToggleGroup toggleGroup = new ToggleGroup();
        radioAVL.setToggleGroup(toggleGroup);
        radioBST.setToggleGroup(toggleGroup);
        this.alert = util.FXUtility.alert("", "");
        textBalanced.setText("");
        treeAVL = new AVL();
        treeBST = new BST();
        countHeight = 0;
    }

    private void drawBinaryTree(BTreeNode node, double x, double y, double level) {
        if (node != null) {
            // Dibujar las conexiones con los nodos hijos primero
            if (node.left != null) {
                //double childX = x - level / 1.1;
                double childX = x - level / 2;
                //double childY = y + 50;
                double childY = y + 40;
                //drawBinaryTree(node.left, childX, childY, level / 2);
                drawBinaryTree(node.left, childX, childY, level / 1.8);

                // Dibujar una línea desde el nodo actual al nodo hijo izquierdo
                Line line = new Line(x, y, childX, childY - 20);
                lienzo.getChildren().add(line);
            }

            if (node.right != null) {
                //double childX = x +level / 1.1;
                double childX = x +level / 2;
                double childY = y + 40;
                //double childY = y + 50;
                //drawBinaryTree(node.right, childX, childY, level / 2);
                drawBinaryTree(node.right, childX, childY, level / 1.8);

                // Dibujar una línea desde el nodo actual al nodo hijo derecho
                Line line = new Line(x, y, childX, childY - 20);
                lienzo.getChildren().add(line);
            }

            // Dibujar el nodo actual como un círculo después de las líneas
            Circle circle = new Circle(x, y, 20);
            circle.setFill(Color.LIGHTGREEN); // Cambiar el color a verde claro
            lienzo.getChildren().add(circle);

            // Mostrar el valor del nodo
            Text text = new Text(String.valueOf(node.data));
            text.setX(x - 5);
            text.setY(y + 5);
            lienzo.getChildren().add(text);
        }
    }

    private void setFields(Tree tree) throws TreeException {
        inOrderTextField.setText(tree.InOrder());
        postOrderTextField.setText(tree.postOrder());
        preOrderTextField.setText(tree.preOrder());
        treeHeightTextField.setText("Tree Height: " + tree.height());
    }

    public void randomizeOnAction(ActionEvent actionEvent) throws TreeException {
        treeBST = new BST();
        treeAVL = new AVL();

        countHeight = 0;//reiniciar contador de height
        alert.setAlertType(Alert.AlertType.INFORMATION);
        //Segun el tipo de Radio Button, crear la instancia indicada
        textBalanced.setText("");

        if (typeTree == 0){
            alert.setContentText("Please select one button");
            alert.showAndWait();

        }else if (typeTree == 1){

            for (int i = 0; i < 20; i++) {
                treeBST.add(util.Utility.random(25));
            }
            lienzo.getChildren().clear();
            //drawBinaryTree(treeBST.getRoot(), lienzo.getPrefWidth(), 50, lienzo.getPrefWidth()/1.7);
            drawBinaryTree(treeBST.getRoot(), 400, 50, 350);
            setFields(treeBST);
        }else{
            for (int i = 0; i < 20; i++) {
                treeAVL.add(util.Utility.random(25));
            }
            lienzo.getChildren().clear();
//            drawBinaryTree(treeAVL.getRoot(), lienzo.getPrefWidth(), 50, lienzo.getPrefWidth()/1.7);
            drawBinaryTree(treeAVL.getRoot(), 400, 50, 350);
            setFields(treeAVL);
        }
    }

    @FXML
    void balancedOnAction(ActionEvent event) throws TreeException {


        alert.setAlertType(Alert.AlertType.INFORMATION);
        //Segun el tipo de Radio Button, crear la instancia indicada

        if (typeTree == 0){
            alert.setContentText("Please select one button");
            alert.showAndWait();

        }else if (typeTree == 1){

            textBalanced.setText("Balanced: " + treeBST.isBalanced());;

        }else{
            textBalanced.setText("Balanced: " + treeAVL.isBalanced());;
        }

    }
    public void levelsOnAction(ActionEvent actionEvent) throws TreeException {
        alert.setAlertType(Alert.AlertType.INFORMATION);
        if (typeTree == 0){
            alert.setContentText("Please select one button");
            alert.showAndWait();
        }else if (typeTree == 1){

            //lienzo.getChildren().clear(); // Limpiar el lienzo

            // Obtener el número máximo de niveles en el árbol
            int maxLevel = treeBST.height();

            // Dibujar el árbol con líneas adicionales en cada nivel
            //drawBinaryTreeWithLevels(treeBST.getRoot(), lienzo.getPrefWidth(), 50, lienzo.getPrefWidth() / 1.7, maxLevel, 0);
            lienzo.getChildren().clear();
            drawBinaryTreeWithLevels(treeBST.getRoot(), 400, 50, 350,maxLevel,0);

        }else{

            //lienzo.getChildren().clear(); // Limpiar el lienzo

            // Obtener el número máximo de niveles en el árbol
            int maxLevel = treeAVL.height();

            // Dibujar el árbol con líneas adicionales en cada nivel
            //drawBinaryTreeWithLevels(treeAVL.getRoot(), lienzo.getPrefWidth(), 50, lienzo.getPrefWidth() / 1.7, maxLevel, 0);
            lienzo.getChildren().clear();
            drawBinaryTreeWithLevels(treeAVL.getRoot(), 400, 50, 350,maxLevel,0);
        }
    }

    private void drawBinaryTreeWithLevels(BTreeNode node, double x, double y, double level, int maxLevel, int currentLevel) throws TreeException {
        if (node != null) {
            // Dibujar las conexiones con los nodos hijos primero
            if (node.left != null) {
                //double childX = x - level / 1.1;
                double childX = x - level / 2;
                //double childY = y + 50;
                double childY = y + 40;
                //drawBinaryTreeWithLevels(node.left, childX, childY, level / 2, maxLevel, currentLevel + 1);
                drawBinaryTreeWithLevels(node.left, childX, childY, level / 1.8, maxLevel, currentLevel + 1);

                // Dibujar una línea desde el nodo actual al nodo hijo izquierdo
                Line line = new Line(x, y, childX, childY - 20);
                lienzo.getChildren().add(line);

                double startX = x - level;
                double endX = x + level*2;
                double lineY = y + 20;
                String textHeight = "";
                Text text = new Text();

                if (typeTree == 1){
                    text.setText(String.valueOf(treeBST.height(node.data)));
                }else {
                    text.setText(String.valueOf(treeAVL.height(node.data)));
                }

                text.setX(startX);
                text.setY(lineY);
                //Line levelLine = new Line(startX, lineY, endX, lineY);
                Line levelLine = new Line();
                levelLine.startXProperty().bind(lienzo.layoutXProperty());
                levelLine.endXProperty().bind(lienzo.widthProperty());
                levelLine.setEndY(lineY);
                levelLine.setStartY(lineY);
                lienzo.getChildren().add(text); //numero que indica el tamano actual de este nodo
                lienzo.getChildren().add(levelLine);
            }

            if (node.right != null) {
                //double childX = x + level / 1.1;
                double childX = x + level / 2;
                //double childY = y + 50;
                double childY = y + 40;
                //drawBinaryTreeWithLevels(node.right, childX, childY, level / 2, maxLevel, currentLevel + 1);
                drawBinaryTreeWithLevels(node.right, childX, childY, level / 1.8, maxLevel, currentLevel + 1);

                // Dibujar una línea desde el nodo actual al nodo hijo derecho
                Line line = new Line(x, y, childX, childY - 20);
                lienzo.getChildren().add(line);
            }

            // Dibujar una línea debajo del nodo actual si no es el último nivel
//            if (currentLevel < maxLevel && (node.left != null && node.right == null)) {
//                double startX = x - level;
//                double endX = x + level*2;
//                double lineY = y + 20;
//                String textHeight = "";
//                Text text = new Text();
//
//                if (typeTree == 1){
//                    text.setText(String.valueOf(treeBST.height(node.data)));;
//                }else {
//                    text.setText(String.valueOf(treeAVL.height(node.data)));
//                }
//
//                text.setX(startX);
//                text.setY(lineY);
//                //Line levelLine = new Line(startX, lineY, endX, lineY);
//                Line levelLine = new Line();
//                levelLine.startXProperty().bind(lienzo.layoutXProperty());
//                levelLine.endXProperty().bind(lienzo.widthProperty());
//                levelLine.setEndY(lineY);
//                levelLine.setStartY(lineY);
//                lienzo.getChildren().add(text); //numero que indica el tamano actual de este nodo
//                lienzo.getChildren().add(levelLine);
//            }

            // Dibujar el nodo actual como un círculo después de las líneas
            Circle circle = new Circle(x, y, 20);
            circle.setFill(Color.LIGHTGREEN); // Cambiar el color a verde claro
            lienzo.getChildren().add(circle);

            // Mostrar el valor del nodo
            Text text = new Text(String.valueOf(node.data));
            text.setX(x - 5);
            text.setY(y + 5);
            lienzo.getChildren().add(text);
        }
    }

    @FXML
    public void btnRadioBST(ActionEvent actionEvent) {
        lienzo.getChildren().clear();
        //this.radioAVL.setSelected(!this.radioAVL.selectedProperty().getValue());
        typeTree = 1;
    }

    @FXML
    public void btnRadioAVL(ActionEvent actionEvent) {
        lienzo.getChildren().clear();
        //this.radioBST.setSelected(!this.radioBST.selectedProperty().getValue());
        typeTree = 2;
    }

//    public void btnRadioBST(ActionEvent actionEvent) {
//    }
}