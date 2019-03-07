package edu.eci.arsw.Persistance;

import edu.eci.arsw.Entities.Data;
import java.util.List;

public interface InMemory {
    
    public void saveSearch(Data search);
    
    public List<Data> getData();
    
    public Data getData(String guid);        
    
}
