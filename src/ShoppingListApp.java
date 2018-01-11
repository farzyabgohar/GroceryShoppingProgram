//Farzyab Gohar
//101021301
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


public class ShoppingListApp extends Application {
        private Shopper model;
        private ShoppingListView view;
        private int restarts = 0;

        public void start(Stage primaryStage) {
            model = new Shopper();
            view = new ShoppingListView(model);
            view.getProductlist().setOnMousePressed(new EventHandler<MouseEvent>() {
                public void handle(MouseEvent mouseEvent) { handleListSelection(); }
            });
            view.getCartlist().setOnMousePressed(new EventHandler<MouseEvent>() {
                public void handle(MouseEvent mouseEvent) { handleListSelection(); }
            });
            view.getBuybutton().setOnAction(new EventHandler<ActionEvent>() {
                public void handle(ActionEvent event) {
                    handleaddbuttonpress();

                }
            });
            view.getReturnbutton().setOnAction(new EventHandler<ActionEvent>() {
                public void handle(ActionEvent event) {
                    handlereturnbuttonpress();
                }
            });
            view.getCheckoutbutton().setOnAction(new EventHandler<ActionEvent>() {
                public void handle(ActionEvent event) {
                    restarts = restarts + 1;
                    handlecheckoutbuttonpress();
                }
            });



            Scene scene = new Scene(view, 740, 390); // Set window size
            primaryStage.setTitle("Grocery Store Application"); // Set window title
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.show(); // Show window
        }
        private void handleListSelection() { view.update(); }
        private void handleaddbuttonpress(){
            GroceryItem x = view.getProductlist().getSelectionModel().getSelectedItem();
            model.addItem(x);
            view.update();
        }
        private void handlereturnbuttonpress(){
            int index = view.getCartlist().getSelectionModel().getSelectedIndex();
            model.removeItem(model.getCart()[index]);
            view.update();
        }
        private void handlecheckoutbuttonpress(){
            if (restarts % 2 == 0) {
                handlerestart();
            } else {
                for (int j=0;j<model.getNumItems();j++){
                    if (model.getCart()[j] instanceof GroceryItem){
                        System.out.print(String.format("%-30s",model.getCart()[j].getDescription()));
                        System.out.print(String.format("%20s", model.getCart()[j].getPrice()));
                        System.out.println();
                    } else {
                        for (int k=0;k<((GroceryBag)model.getCart()[j]).getNumItems();k++){
                            System.out.print(String.format("%-30s", ((GroceryBag)model.getCart()[j]).getItems()[k].getDescription()));
                            System.out.print(String.format("%20s", ((GroceryBag)model.getCart()[j]).getItems()[k].getPrice()));
                            System.out.println();
                        }
                    }
                }
                System.out.println("--------------------------------------------------");
                System.out.println(String.format("%-30s%20s", "Total", model.computeTotalCost()));
                model.packBags();
                view.update();
                view.getProductlist().setDisable(true);
                view.getBuybutton().setDisable(true);
                view.getReturnbutton().setDisable(true);
                view.getCheckoutbutton().setText("Restart Shopping");

                view.getCartlist().setOnMousePressed(new EventHandler<MouseEvent>() {
                    public void handle(MouseEvent event) {
                        FinHandler();
                    }
                });
            }


        }
        private void FinHandler(){
            int index = view.getCartlist().getSelectionModel().getSelectedIndex();
            if (model.getCart()[index] instanceof GroceryBag){
                int numitems = ((GroceryBag)model.getCart()[index]).getNumItems();
                GroceryItem[] exactContentList = new GroceryItem[numitems];
                for (int i=0;i<numitems;i++){
                    exactContentList[i] = ((GroceryBag)model.getCart()[index]).getItems()[i];
                }
                view.getContentlist().setItems(FXCollections.observableArrayList(exactContentList));
            }else {
                view.getContentlist().setItems(FXCollections.observableArrayList());
            }
        }
        private void handlerestart(){

            int x = model.getNumItems();
            for (int i=0;i<x;i++){
                model.removeItem(model.getCart()[0]);
            }
            view.getContentlist().setItems(FXCollections.observableArrayList());
            view.getProductlist().setDisable(false);
            view.getCheckoutbutton().setText("Checkout");
            view.update();
        }
        public static void main(String[] args) {
            launch(args); // Initialize/start
        }
}
