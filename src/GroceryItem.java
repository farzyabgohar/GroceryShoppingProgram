//Farzyab Gohar
//101021301
public class GroceryItem implements Carryable {
    private String name;
    private float weight;
    private float price;

    public GroceryItem(){

    }

    public GroceryItem(String n, float p, float w){
        name = n;
        weight = w;
        price = p;
    }


    public String getName(){return name;}
    public float getWeight(){return weight;}
    public float getPrice(){return price;}

    public String getContents() {
        return "";
    }

    public String getDescription() {
        return name;
    }

    public String toString() {
        return name + " weighing " + weight + "kg with price $" + price;
    }

}
