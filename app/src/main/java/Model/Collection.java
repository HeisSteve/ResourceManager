package Model;

import java.util.ArrayList;
import java.util.List;

public class Collection {
    private String name;
    private List<Item> itemList;

    public Collection(String name){
        this.name = name;
        this.itemList = new ArrayList<>();
    }

    public void changeName(String name){
        this.name = name;
    }

    public void addItem(Item item){
        if (!itemList.contains(item)){
            itemList.add(item);
        }
    }

    public void removeItem(Item item){
            itemList.remove(item);
    }
    public String getName(){
        return name;
    }

    public List<Item> getItems(){
        return itemList;
    }

    public String getListSize(){
        int num = itemList.size();
        return Integer.toString(num);
    }

}
