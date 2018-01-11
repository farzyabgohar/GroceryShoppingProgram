

//Farzyab Gohar
//101021301
public class GroceryBag implements Carryable {
    private static double MAX_WEIGHT = 5;
    private static int MAX_ITEMS = 25;

    private GroceryItem[] items = new GroceryItem[MAX_ITEMS]; //each bag can only hold 25
    private int numItems;
    private float weight;

    public int getNumItems(){return numItems;}
    public float getWeight(){return weight;}
    public GroceryItem[] getItems() {return items;}

    public GroceryBag(){}

    public void addItem(GroceryItem gi){
        //only adds item if it doesn't exceed the bag's max weight or max item limit.
        if (((gi.getWeight() + weight) <= MAX_WEIGHT) && (numItems < MAX_ITEMS)) {
            weight = weight + gi.getWeight(); //adjusts weight accordingly
            items[numItems] = gi;
            numItems = numItems + 1;
        }
    }
    public void removeItem(GroceryItem item) {
            for (int i = 0; i < numItems; i++) {
                //if match found, adjust weight, remove item (replace with last), and break loop (so it doesn't remove multiple items with same name)
                if (items[i].getName().equals(item.getName())) {
                    weight = weight - items[i].getWeight();
                    items[i] = items[numItems-1];
                    break;
                }
            }

        numItems = numItems - 1;
    }
    public GroceryItem heaviestItem(){
        if (numItems==0) {
            return null;
        } else {
            float heaviest = 0.0f;
            int heaviestpos = 0;
            for (int i =0; i<numItems;i++){
                //stores heaviest item's weight and its position
                if (items[i].getWeight() > heaviest) {
                    heaviest = items[i].getWeight();
                    heaviestpos = i;
                }
            }
            return items[heaviestpos]; //returns heaviest item
        }
    }

    public boolean has(GroceryItem item){
        boolean flag = false;
        for (int i = 0;i<numItems;i++){
            if (items[i].getName().equals(item.getName())){
                flag = true;
            }
        }
        return flag;
    }

    public String toString(){
        if (numItems==0) {
            return "An empty grocery bag ";
        } else {
            return "A " + weight  + "kg grocery bag with " + numItems + " items";
        }
    }
    public PerishableItem[] unpackPerishables() {
        //since max bag items is 25, need an array of size 25 in case all 100 items are perishable
        PerishableItem[] perishables = new PerishableItem[25];
        int a = 0;
        int limit = numItems; //need a static limit because value of numItems changes when items are removed
        for (int i = 0; i < limit; i++) {
            //perishable items are added to the perishable array
            if (items[i] instanceof PerishableItem) {
                perishables[a] = (PerishableItem) items[i];
                removeItem(items[i]);
                a = a +1;
            }
        }
        //returns an array of size equal to the number of perishable items found
        PerishableItem[] finalPerishable = new PerishableItem[a];
        for (int j = 0;j<a;j++) {
            finalPerishable[j] = perishables[j];
        }
        return finalPerishable;
    }

    public String getContents() {
        String x = "";
        for (int i=0;i<numItems;i++){
            x = x  + "   " + items[i] + "\n";
        }
        if (x=="") {
            x = "t";
        }
        return x;
    }

    public String getDescription() {
        return "GROCERY BAG (" + getWeight() + " kg)";
    }

    public float getPrice() {
        float totalprice = 0.0f;
        for (int i=0;i<getNumItems();i++){
            totalprice = totalprice + items[i].getPrice();
        }
        return totalprice;
    }
}
