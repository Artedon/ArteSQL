package nl.artesql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class SQLQuery {

    private final Helper helper;

    public SQLQuery(Helper helper) {
        this.helper = helper;
    }

    public HashMap<String, HashMap<String, String>> query(String query) {

        if(!helper.isConnected()) return null;

        HashMap<String, String> result = new HashMap<>();
        HashMap<String, HashMap<String, String>> results = new HashMap<>();

        try {

            PreparedStatement ps = helper.getConnection().prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                System.out.println("before for loop");
                for(int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    System.out.println("in for loop");
                    System.out.println(rs.getMetaData().getColumnLabel(i));
                    System.out.println(rs.getString(i));
                    result.put(rs.getMetaData().getColumnLabel(i), rs.getString(i));
                }
                results.put(result.get("uuid"), result);
            }
            ps.close();
            return results;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    public boolean update(String query) {

        try {

            PreparedStatement ps = helper.getConnection().prepareStatement(query);
            ps.executeUpdate();
            ps.close();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    public HashMap<String, String> findByPk(String pk) {

        HashMap<String, String> result = new HashMap<>();

        try {

            PreparedStatement ps = helper.getConnection().prepareStatement("SELECT * FROM user WHERE uuid = ?");
            ps.setString(1, pk);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                for(int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    result.put(rs.getMetaData().getColumnLabel(i), rs.getString(i));
                }
            }
            ps.close();
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

}
