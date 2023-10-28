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
                o.put("available", rs.getString("available"));

                data.add(o);
            }
        } catch (SQLException e) {
            System.out.println(e.getErrorCode());
        }

        return data;
    }

    private PreparedStatement insertStatement(Connection conn, Hashtable<String,String> data) throws SQLException {
        String query = "insert into books(id,name,Description,Quantity,Available) values(?,?,?,?,?);";
        return getPreparedStatement(conn, data, query);
    }

    private PreparedStatement updateStatement(Connection conn,Hashtable<String,String> data) throws SQLException{
        String query = "update books set name = ?, Description = ?, Quantity = ?, Available = ? where id = ?";
        return getPreparedStatement(conn, data, query);
    }

    private PreparedStatement getPreparedStatement(Connection conn, Hashtable<String, String> data, String query) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1,data.get("id"));
        stmt.setString(2,data.get("name"));
        stmt.setString(3,data.get("description"));
        stmt.setString(4,data.get("quantity"));
        stmt.setString(5,data.get("available"));
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
        return DriverManager.getConnection("jdbc:mysql://localhost/SCDLab7","root","DragonBall1!");
    }
}
