package Objects;

import DAOs.BookDao;
import DAOs.IDAO;

import java.util.ArrayList;
import java.util.Hashtable;

public class Books {
    private Integer id;
    private String name;
    private String description;
    private Integer quantity;
    private Boolean available;

    private static IDAO dao = new BookDao();

    public Books(Integer id, String name, String description, Integer quantity, Boolean available) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.available = available;
    }

    public Books() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public boolean load(){
        Hashtable<String,String> data = dao.load(id);
        if (data == null || data.isEmpty()){
            return false;
        }

        id = Integer.parseInt(data.get("id"));
        name = data.get("name");
        description = data.get("description");
        quantity = Integer.parseInt(data.get("quantity"));
        available = Boolean.valueOf(data.get("Available"));
        return true;
    }

    public boolean delete(){
        return dao.delete(id);
    }

    public boolean save(){

        Hashtable<String,String> data = new Hashtable<>();
        data.put("id",id.toString());
        data.put("name",name);
        data.put("description",description);
        data.put("quantity",quantity.toString());
        data.put("available",available.toString());
        return dao.save(data);
    }

    public static ArrayList<Books> getBooks(){
        ArrayList<Hashtable<String,String>> data = dao.load();
        ArrayList<Books> books = new ArrayList<>();

        for(Hashtable<String,String> d : data){
            books.add(new Books(Integer.parseInt(d.get("id")),
                    d.get("name"),
                    d.get("description"),
                    Integer.parseInt(d.get("quantity")),
                    Boolean.valueOf(d.get("available")))
            );
        }

        return books;
    }
}
