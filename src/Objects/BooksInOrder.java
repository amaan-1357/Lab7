package Objects;


import DAOs.BooksInOrderDao;
import DAOs.IDAO;
import DAOs.OrderDao;

import java.util.ArrayList;
import java.util.Hashtable;

public class BooksInOrder {
    private Integer id;
    private Integer orderID;
    private Integer bookID;
    private static IDAO dao = new BooksInOrderDao();

    public BooksInOrder(Integer ID, Integer orderID, Integer bookID) {
        this.id = ID;
        this.orderID = orderID;
        this.bookID = bookID;
    }

    public BooksInOrder(Integer orderID, Integer bookID) {
        this.orderID = orderID;
        this.bookID = bookID;
    }

    public BooksInOrder() {
    }

    public Integer getID() {
        return id;
    }

    public void setID(Integer ID) {
        this.id = ID;
    }

    public Integer getOrderID(){
        return orderID;
    }

    public void setOrderID(Integer orderID){
        this.orderID = orderID;
    }

    public Integer getBookID() {
        return bookID;
    }

    public void setBookID(Integer bookID) {
        this.bookID = bookID;
    }
    public boolean load(Integer i){
        Hashtable<String,String> data = dao.load(i);
        if (data == null || data.isEmpty()){
            return false;
        }

        id = Integer.parseInt(data.get("id"));
        orderID = Integer.parseInt(data.get("orderID"));
        bookID = Integer.parseInt(data.get("bookID"));
        return true;
    }
    public ArrayList<Hashtable<String,String>> loadMultiple(Integer i){
        ArrayList<Hashtable<String,String>> data = dao.loadMultiple(i);
        if (data == null || data.isEmpty()){
            return new ArrayList<Hashtable<String,String>>();
        }
        return data;
    }

    public boolean delete(){
        return dao.delete(id);
    }

    public boolean save(){
        Hashtable<String,String> data = new Hashtable<>();
        data.put("id",id.toString());
        data.put("orderID",orderID.toString());
        data.put("bookID",bookID.toString());
        return dao.save(data);
    }
    public boolean insert(){
        Hashtable<String,String> data = new Hashtable<>();
        data.put("orderID",orderID.toString());
        data.put("bookID",bookID.toString());
        return dao.insert(data);
    }

    public static ArrayList<BooksInOrder> getBooksInOrders(){
        ArrayList<Hashtable<String,String>> data = dao.load();
        ArrayList<BooksInOrder> booksInOrders = new ArrayList<>();

        for(Hashtable<String,String> d : data){
            booksInOrders.add(new BooksInOrder(Integer.parseInt(d.get("id")),
                    Integer.parseInt(d.get("orderID")),
                    Integer.parseInt(d.get("bookID")))
            );
        }

        return booksInOrders;
    }
}
