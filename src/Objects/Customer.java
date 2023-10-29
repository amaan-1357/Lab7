package Objects;

import DAOs.CustomerDao;
import DAOs.IDAO;

import java.util.ArrayList;
import java.util.Hashtable;

public class Customer {
    private Integer id;
    private String name;
    private static IDAO dao = new CustomerDao();

    public Customer(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Customer() {

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

    public boolean load(){
        Hashtable<String,String> data = dao.load(id);
        if (data == null || data.isEmpty()){
            return false;
        }

        id = Integer.parseInt(data.get("id"));
        name = data.get("name");
        return true;
    }

    public boolean delete(){
        return dao.delete(id);
    }

    public boolean save(){

        Hashtable<String,String> data = new Hashtable<>();
        data.put("id",id.toString());
        data.put("name",name);
        return dao.save(data);
    }

    public static ArrayList<Customer> getCustomers(){
        ArrayList<Hashtable<String,String>> data = dao.load();
        ArrayList<Customer> customers = new ArrayList<>();

        for(Hashtable<String,String> d : data){
            customers.add(new Customer(Integer.parseInt(d.get("id")),
                    d.get("name"))
            );
        }

        return customers;
    }
}
