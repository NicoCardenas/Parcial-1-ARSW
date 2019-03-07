package edu.eci.arsw.Persistance.impl;

import edu.eci.arsw.Entities.Data;
import edu.eci.arsw.Persistance.InMemory;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class InMemoryImpl implements InMemory {
    
    private List<Data> searchs;

    @Override
    public void saveSearch(Data search) {
        if (searchs == null)
            searchs = new ArrayList<>();
        searchs.add(search);
    }

    @Override
    public List<Data> getData() {
        if (searchs == null)
            return new ArrayList<>();
        else
            return searchs;
    }

    @Override
    public Data getData(String guid) {
        if (searchs == null)
            return new Data();
        else
            if (searchs.contains(guid))
                return searchs.get(searchs.indexOf(guid));
            else
                return new Data();
    }        
    
}
