//Farzyab Gohar
//101021301
public class Shopper {
    private static int MAX_CART_ITEMS = 100;
    private Carryable[] cart = new Carryable[MAX_CART_ITEMS];

    private int numItems = 0;

    public int getNumItems(){return numItems;}
    public Carryable[] getCart(){return cart;}

    public Shopper(){

    }
    public void addItem(Carryable item){
        //only adds item as long as total cart size doesn't exceed max cart items
        if (numItems < MAX_CART_ITEMS) {
            cart[numItems] = item;
            numItems = numItems + 1;
        }
    }
    public void removeItem(Carryable g) {
        for (int i=0; i<numItems; i++) {
            if (cart[i] == g) {
                cart[i] = cart[numItems - 1];
                numItems -= 1;
                return;
            }
        }
    }
    public void packBags(){
        GroceryBag[] allBags = new GroceryBag[100]; //if each item = max bag weight, then need bags equal to max cart size
        int numBags = 0; //keeps track of number of packed bags
        int a = numItems; //static loop limit because size of numItems changes with removal of items
        int b;
        int c;
        for (int i = 0;i<a;i++) {
            allBags[i] = new GroceryBag(); //creates a new bag after each bag reaches max weight or max num items
            c = 0; //pointer for items that are too heavy to be put in a bag
            b = numItems; //changes limit for j loop depending on number of items remaining in cart
            for (int j = 0; j<b; j++) {
                cart[c] = cart[c];
                //adds item into bag if max weight or max num items hasn't been reached
                if ((((GroceryItem)cart[c]).getWeight() + allBags[i].getWeight() <= 5) && (allBags[i].getNumItems() < 25)) {
                    allBags[i].addItem((GroceryItem) cart[c]);
                    removeItem((GroceryItem) cart[c]);
                    //if item is too heavy, the pointer c makes sure the algorithm moves to the next item in cart
                } else if(((GroceryItem)cart[c]).getWeight() > 5){
                    c = c +1;
                    //if adding item will cross bag's max weight, increments numBags and breaks loop so new bag
                    //can be created when the loop i runs again
                } else {
                    numBags = numBags + 1;
                    break;
                }
            }
        }
        int pos = numItems;
        for (int x=0;x<=numBags;x++){
            cart[pos+x] = allBags[x];
            numItems = numItems +1;
        }



    }
    public void displayCartContents(){
        for(int i=0;i<getNumItems();i++){
            if (getCart()[i].getContents().equals("")){
                System.out.println(((GroceryItem)getCart()[i]).getName());
            }else if(getCart()[i].getContents().equals("t")){
                System.out.println(cart[i].getDescription());
            }
            else{
                System.out.println(cart[i].getDescription());
                System.out.println(cart[i].getContents());

            }
        }
    }
    public PerishableItem[] removePerishables(){
        PerishableItem[] temparray = new PerishableItem[100];
        int numperishables = 0;
        for(int i=0;i<getNumItems();i++){
            if (getCart()[i].getContents().equals("")){
                if (getCart()[i] instanceof PerishableItem){
                    temparray[numperishables] = (PerishableItem) getCart()[i];
                    numperishables = numperishables + 1;
                    removeItem(getCart()[i]);
                }
            }else {
                PerishableItem[] a = ((GroceryBag)cart[i]).unpackPerishables();
                for (int j=0;j<a.length;j++){
                    temparray[numperishables] = a[j];
                    numperishables = numperishables + 1;
                }
            }

            }
        PerishableItem[] finalarray = new PerishableItem[numperishables];
        for (int x=0;x<numperishables;x++){
            finalarray[x] = temparray[x];
        }
        return finalarray;

    }
    public float computeFreezerItemCost(){
        float totalfprice = 0.0f;
        for (int i=0;i<getNumItems();i++){
            if (cart[i] instanceof GroceryItem){
                if (cart[i] instanceof FreezerItem){
                    totalfprice = totalfprice + cart[i].getPrice();
                }
            }else {
                for (int j=0;j<((GroceryBag)cart[i]).getNumItems();j++){
                    if (((GroceryBag)cart[i]).getItems()[j] instanceof FreezerItem){
                        totalfprice = totalfprice + ((GroceryBag)cart[i]).getItems()[j].getPrice();

                    }
                }
            }
        }
        return totalfprice;
    }
    public float computeTotalCost(){
        float totalcost = 0.0f;
        for (int i=0;i<numItems;i++){
            totalcost = totalcost + cart[i].getPrice();
        }
        return totalcost;
    }
}
