package DAOs;


import java.sql.*;
import java.util.ArrayList;
import java.util.Hashtable;


public class BookDao implements IDAO{
    @Override
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
            System.out.println(ex.getMessage());
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
                data.put("name", rs.getString("name"));
                data.put("description", rs.getString("description"));
                data.put("quantity", rs.getString("quantity"));
                data.put("available", rs.getString("available"));
            }
        } catch(SQLException ex){
            // log exception
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
        String query = "select id,Name,Description,Quantity,Available from books";
        try {
            Connection conn = getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Hashtable<String,String> o = new Hashtable<>();
                o.put("id", rs.getString("id"));
                o.put("name", rs.getString("name"));
                o.put("description", rs.getString("description"));
                o.put("quantity", rs.getString("quantity"));
                if(Integer.parseInt(rs.getString("quantity"))>0)
                    o.put("available", "true");
                else
                    o.put("available", "false");

                data.add(o);
            }
        } catch (SQLException e) {
            e.printStackTrace();
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
                data.put("name", rs.getString("name"));
                data.put("description", rs.getString("description"));
                data.put("quantity", rs.getString("quantity"));
                if(Integer.parseInt(rs.getString("quantity"))>0)
                    data.put("available", "true");
                else
                    data.put("available", "false");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return data;
    }

    private PreparedStatement insertStatement(Connection conn, Hashtable<String,String> data) throws SQLException {
        String query = "insert into books(name,Description,Quantity,Available) values(?,?,?,?);";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1,data.get("name"));
        stmt.setString(2,data.get("description"));
        stmt.setString(3,data.get("quantity"));
        stmt.setString(4,"1");
        return stmt;
    }

    private PreparedStatement updateStatement(Connection conn,Hashtable<String,String> data) throws SQLException{
        String query = "update books set name = ?, Description = ?, Quantity = ?, Available = ? where id = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1,data.get("name"));
        stmt.setString(2,data.get("description"));
        stmt.setString(3,data.get("quantity"));
        if(Integer.parseInt(data.get("quantity")) == 0)
            stmt.setString(4,"0");
        else
            stmt.setString(4,"1");
        stmt.setString(5,data.get("id"));
        return stmt;
    }



    private PreparedStatement selectSingleStatement(Connection conn,Integer id) throws SQLException{
        String query = "select id,Name,Description,Quantity,Available from books where id = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1,id.toString());
        return stmt;
    }

    private PreparedStatement deleteStatement(Connection conn,Integer id) throws SQLException{
        String query = "delete from books where id = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1,id.toString());
        return stmt;
    }
    private static Connection getConnection()
            throws SQLException
    {
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/SCDLab7", "root", "DragonBall1!");
    }
}
