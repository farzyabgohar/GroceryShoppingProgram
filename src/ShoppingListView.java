
//Farzyab Gohar
//101021301
import javafx.collections.FXCollections;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;


public class ShoppingListView extends Pane {
    private Shopper model;
    private ListView<GroceryItem> productlist;
    private ListView<String> cartlist;
    private ListView<GroceryItem> contentlist;
    private Label productlabel;
    private Label shoppingcartlabel;
    private Label contentlabel;
    private Label totalpricelabel;
    private TextField pricefield;
    private Button buybutton;
    private Button returnbutton;
    private Button checkoutbutton;

    public Button getBuybutton(){
        return buybutton;
    }
    public Button getReturnbutton(){
        return returnbutton;
    }
    public Button getCheckoutbutton(){
        return checkoutbutton;
    }
    public ListView<GroceryItem> getProductlist(){
        return productlist;
    }
    public ListView<String> getCartlist(){
        return cartlist;
    }
    public ListView<GroceryItem> getContentlist(){
        return contentlist;
    }
    public TextField getPricefield(){return pricefield;}

    public ShoppingListView(Shopper m){
        model = m;
        productlabel = new Label("Products");
        shoppingcartlabel = new Label("Shopping Cart");
        contentlabel = new Label("Contents");
        totalpricelabel = new Label("Total Price:");
        buybutton = new Button("Buy");
        returnbutton = new Button("Return");
        checkoutbutton = new Button("Checkout");
        pricefield = new TextField(String.format("%22s", "$0.00"));

        GroceryItem[] products = {
                new FreezerItem("Smart-Ones Frozen Entrees", 1.99f, 0.311f),
                new GroceryItem("SnackPack Pudding", 0.99f, 0.396f),
                new FreezerItem("Breyers Chocolate Icecream",2.99f,2.27f),
                new GroceryItem("Nabob Coffee", 3.99f, 0.326f),
                new GroceryItem("Gold Seal Salmon", 1.99f, 0.213f),
                new GroceryItem("Ocean Spray Cranberry Cocktail",2.99f,2.26f),
                new GroceryItem("Heinz Beans Original", 0.79f, 0.477f),
                new RefrigeratorItem("Lean Ground Beef", 4.94f, 0.75f),
                new FreezerItem("5-Alive Frozen Juice",0.75f,0.426f),
                new GroceryItem("Coca-Cola 12-pack", 3.49f, 5.112f),
                new GroceryItem("Toilet Paper - 48 pack", 40.96f, 10.89f),
                new RefrigeratorItem("2L Sealtest Milk",2.99f,2.06f),
                new RefrigeratorItem("Extra-Large Eggs",1.79f,0.77f),
                new RefrigeratorItem("Yoplait Yogurt 6-pack",4.74f,1.02f),
                new FreezerItem("Mega-Sized Chocolate Icecream",67.93f,15.03f)};
        productlist = new ListView<GroceryItem>();
        cartlist = new ListView<>();
        contentlist = new ListView<>();
        productlist.setItems(FXCollections.observableArrayList(products));

        productlist.setPrefSize(200, 300);
        cartlist.setPrefSize(200, 300);
        contentlist.setPrefSize(300, 300);
        buybutton.setPrefSize(200, 25);
        returnbutton.setPrefSize(200, 25);
        checkoutbutton.setPrefSize(120, 25);
        productlabel.setPrefSize(200, 35);
        shoppingcartlabel.setPrefSize(200, 35);
        contentlabel.setPrefSize(120, 35);
        totalpricelabel.setPrefSize(65, 25);
        pricefield.setPrefSize(100, 25);
        productlabel.relocate(10, 0);
        shoppingcartlabel.relocate(220, 0);
        contentlabel.relocate(430, 0);
        productlist.relocate(10, 45);
        cartlist.relocate(220, 45);
        contentlist.relocate(430, 45);
        buybutton.relocate(10, 355);
        returnbutton.relocate(220, 355);
        checkoutbutton.relocate(430, 355);
        totalpricelabel.relocate(565, 355);
        pricefield.relocate(630, 355);


        getChildren().addAll(productlist, cartlist, contentlist, productlabel, shoppingcartlabel, contentlabel, buybutton, returnbutton, checkoutbutton, totalpricelabel, pricefield);
        update();
    }
    public void update(){
        String[] exactcartlist = new String[model.getNumItems()];
        for (int i=0;i<model.getNumItems();i++){
            exactcartlist[i] = model.getCart()[i].getDescription();
        }
        int selectedIndex = cartlist.getSelectionModel().getSelectedIndex();
        cartlist.setItems(FXCollections.observableArrayList(exactcartlist));
        cartlist.getSelectionModel().select(selectedIndex);
        pricefield.setText(String.format("%19s", "$" + model.computeTotalCost()));
        pricefield.setEditable(false);
        returnbutton.setDisable(cartlist.getSelectionModel().getSelectedIndex() <0);
        buybutton.setDisable(productlist.getSelectionModel().getSelectedIndex() <0);
        checkoutbutton.setDisable(model.getNumItems()<1);

    }


}
