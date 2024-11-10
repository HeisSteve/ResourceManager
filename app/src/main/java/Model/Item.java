package Model;

public class Item {
    private String name;
    private double price;
    private double usage;

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

    public void changeUsage(double usage){
        this.usage = usage;
    }

    public String getName(){
        return name;
    }

    public double getPrice(){
        return price;
    }

    public double getUsage(){
        return usage;
    }

}
