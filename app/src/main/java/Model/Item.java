package Model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Item implements Parcelable {
    private String name;
    private double price;
    private int usage;

    public Item(String name, double price, int usage){
        this.name = name;
        this.price = price;
        this.usage = usage;
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

    protected Item(Parcel in) {
        name = in.readString();         // Read the String from Parcel
        price = in.readDouble();        // Read the double from Parcel
        usage = in.readInt();           // Read the int from Parcel
    }


    // Describe contents (generally return 0)
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(name);         // Write the name to Parcel
        dest.writeDouble(price);        // Write the price to Parcel
        dest.writeInt(usage);           // Write the usage to Parcel
    }

    // This is the CREATOR object that creates an Item from a Parcel
    public static final Creator<Item> CREATOR = new Creator<Item>() {
        @Override
        public Item createFromParcel(Parcel in) {
            return new Item(in);  // Use the constructor that takes a Parcel
        }

        @Override
        public Item[] newArray(int size) {
            return new Item[size];  // Return an array of Item objects
        }
    };

}
