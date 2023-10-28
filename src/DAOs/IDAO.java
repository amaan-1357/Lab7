package DAOs;

import java.util.ArrayList;
import java.util.Hashtable;

public interface IDAO {

    public boolean save(Hashtable<String,String> data);
    public boolean delete(Integer id);
    public Hashtable<String,String> load(Integer id);
    public ArrayList<Hashtable<String,String>> load();

}