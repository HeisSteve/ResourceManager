package Model;

public class Item {
    private String name;
    private double price;
    private int usage;

    public Item(String name, double price){
        this.name = name;
        this.price = price;
        this.usage = 0;
    }

    public void changeName(String name){
        this.name = name;
    }

    public void changePrice(double price){
        this.price += price;
    }

    public void changeUsage(int usage){
        this.usage = usage;
    }

    public String getName(){
        return name;
    }

    public double getPrice(){
        return price;
    }

    public int getUsage(){
        return this.usage;
    }

}
