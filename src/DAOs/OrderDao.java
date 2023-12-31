package DAOs;

import java.sql.*;
import java.util.ArrayList;
import java.util.Hashtable;

public class OrderDao implements IDAO{
    public boolean save(Hashtable<String, String> data) {
        int count;
        try{
            Connection conn = getConnection();
            PreparedStatement stmt = updateStatement(conn,data);
            count = stmt.executeUpdate();
            if (count == 0){
                stmt = insertStatement(conn,data);
                count = stmt.executeUpdate();
            }
        } catch(SQLException ex){
            return false;
        }
        return count > 0;
    }

    @Override
    public boolean insert(Hashtable<String, String> data) {
        int count;
        try{
            Connection conn = getConnection();
            PreparedStatement stmt = insertStatement(conn,data);
            count = stmt.executeUpdate();
            if (count == 0){
                stmt = insertStatement(conn,data);
                count = stmt.executeUpdate();
            }
        } catch(SQLException ex){
            return false;
        }
        return count > 0;
    }


    @Override
    public boolean delete(Integer id) {
        int count;
        try{
            Connection conn = getConnection();
            PreparedStatement stmt = deleteStatement(conn,id);
            count = stmt.executeUpdate();
        } catch(SQLException ex){
            return false;
        }
        return count > 0;
    }

    @Override
    public Hashtable<String, String> load(Integer id) {
        Hashtable<String,String> data = new Hashtable<>();
        try{
            Connection conn = getConnection();
            PreparedStatement stmt = selectSingleStatement(conn,id);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()){
                data.put("id", rs.getString("id"));
                data.put("customerID", rs.getString("customerID"));
            }
        } catch(SQLException ex){
            System.out.println(ex.getErrorCode());
        }
        return data;
    }

    @Override
    public ArrayList<Hashtable<String, String>> loadMultiple(Integer id) {
        return null;
    }

    @Override
    public ArrayList<Hashtable<String, String>> load() {
        ArrayList<Hashtable<String,String>> data = new ArrayList<>();

        String query = "select id,customerID from orders";
        try {
            Connection conn = getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Hashtable<String,String> o = new Hashtable<>();
                o.put("id", rs.getString("id"));
                o.put("customerID", rs.getString("customerID"));

                data.add(o);
            }
        } catch (SQLException e) {
            System.out.println(e.getErrorCode());
        }

        return data;
    }

    @Override
    public Hashtable<String, String> customLoad(String query) {
        Hashtable<String,String> data = new Hashtable<>();

        try {
            Connection conn = getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                data.put("id", rs.getString("id"));
                data.put("customerID", rs.getString("customerID"));
            }
        } catch (SQLException e) {
            System.out.println(e.getErrorCode());
        }

        return data;
    }

    private PreparedStatement insertStatement(Connection conn, Hashtable<String,String> data) throws SQLException {
        String query = "insert into orders (customerID) values(?);";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1,data.get("customerID"));
        return stmt;
    }

    private PreparedStatement updateStatement(Connection conn,Hashtable<String,String> data) throws SQLException{
        String query = "update orders set customerID = ? where id = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1,data.get("id"));
        stmt.setString(2,data.get("customerID"));
        return stmt;
    }


    private PreparedStatement selectSingleStatement(Connection conn,Integer id) throws SQLException{
        String query = "select id,customerID from orders where id = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1,id.toString());
        return stmt;
    }

    private PreparedStatement deleteStatement(Connection conn,Integer id) throws SQLException{
        String query = "delete from orders where id = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1,id.toString());
        return stmt;
    }
    private static Connection getConnection()
            throws SQLException
    {
        return DriverManager.getConnection("jdbc:mysql://localhost/SCDLab7","root","DragonBall1!");
    }
}
