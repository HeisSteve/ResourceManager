package Model;

import java.util.ArrayList;
import java.util.List;

public class HomeResources {
    private List<Collection> allResources;

    public HomeResources(){
        this.allResources = new ArrayList<>();
    }

    public void addCollection(Collection collection){
        if(!allResources.contains(collection)){
            allResources.add(collection);
        }
    }

    public void removeCollection(Collection collection){
            allResources.remove(collection);
    }

    public List<Collection> getAllResources(){
        return allResources;
    }


//    public Collection getCollection(Collection collection){
//        Collection col = null;
//        for(Collection c : allResources){
//            if (c.equals(collection)){
//                return
//            }
//        }
//        return col;
//    }

}
