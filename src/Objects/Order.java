package Objects;

import DAOs.IDAO;
import DAOs.OrderDao;

import java.util.ArrayList;
import java.util.Hashtable;

public class Order {
    private Integer id;
    private Integer customerID;
    private static IDAO dao = new OrderDao();

    public Order(Integer ID, Integer customerID) {
        this.id = ID;
        this.customerID = customerID;
    }

    public Order() {
    }

    public Order(Integer customerID) {
        this.customerID = customerID;
    }

    public Integer getID() {
        return id;
    }

    public void setID(Integer ID) {
        this.id = ID;
    }

    public Integer getCustomerID() {
        return customerID;
    }

    public void setCustomerID(Integer customerID) {
        this.customerID = customerID;
    }
    public boolean load(){
        Hashtable<String,String> data = dao.load(id);
        if (data == null || data.isEmpty()){
            return false;
        }

        id = Integer.parseInt(data.get("id"));
        customerID = Integer.parseInt(data.get("customerID"));
        return true;
    }

    public boolean customLoad(String query){
        Hashtable<String,String> data = dao.customLoad(query);
        if (data == null || data.isEmpty()){
            return false;
        }

        id = Integer.parseInt(data.get("id"));
        customerID = Integer.parseInt(data.get("customerID"));
        return true;
    }

    public boolean delete(){
        return dao.delete(id);
    }

    public boolean save(){

        Hashtable<String,String> data = new Hashtable<>();
        data.put("id",id.toString());
        data.put("customerID",customerID.toString());
        return dao.save(data);
    }
    public boolean insert(){
        Hashtable<String,String> data = new Hashtable<>();
        data.put("customerID",customerID.toString());
        return dao.insert(data);
    }

    public static ArrayList<Order> getOrders(){
        ArrayList<Hashtable<String,String>> data = dao.load();
        ArrayList<Order> orders = new ArrayList<>();

        for(Hashtable<String,String> d : data){
           orders.add(new Order(Integer.parseInt(d.get("id")),
                    Integer.parseInt(d.get("customerID")))
           );
        }

        return orders;
    }
}
