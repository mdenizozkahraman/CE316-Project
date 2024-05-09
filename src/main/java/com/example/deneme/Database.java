package com.example.deneme;

import java.sql.*;
public class Database {
    private static Database instance;
    Connection connection;
    private String path;

    private Database(){
    }

    public static Database getInstance() {
        if(instance == null){
            instance = new Database();
        }
        return instance;
    }

    public void createAssignmentConfig()throws SQLException{
        try {
            Statement st = connection.createStatement();
            st.executeUpdate("CREATE TABLE if not exists c_table (ID INTEGER PRIMARY KEY,COMPILER_PATH varchar(255), ARGS varchar(255),EXPECTED varchar(255),RUN_COMMAND varchar(255),SELECTED_LANGUAGE varchar(255));");
            st.close();
        }catch (SQLException e) {
            System.err.println(e);
        }
    }

    public void connect(String path){
        this.path = path;
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:" + path);
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    public void addConfig(String compilerPath, String args, String expected, String runCommand, String selectedLanguage) throws SQLException {
        String sql0 = "DELETE FROM c_table;";
        PreparedStatement ps0 = connection.prepareStatement(sql0); // reset table
        ps0.executeUpdate();
        ps0.close();
        String sql = "INSERT INTO c_table (ID, COMPILER_PATH, ARGS, EXPECTED, RUN_COMMAND, SELECTED_LANGUAGE) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, 1);
        ps.setString(2, compilerPath);
        ps.setString(3, args);
        ps.setString(4, expected);
        ps.setString(5, runCommand);
        ps.setString(6, selectedLanguage);
        ps.executeUpdate();
        ps.close();
    }

    public Config getConfig() throws SQLException {
        Config config = Config.getInstance();

        String sql = "SELECT COMPILER_PATH, ARGS, EXPECTED, RUN_COMMAND, SELECTED_LANGUAGE FROM c_table WHERE ID = 1";
        Statement stat = connection.createStatement();
        ResultSet rs = stat.executeQuery(sql);

        if (rs.next()) {
            config.COMPILERPATH = rs.getString("COMPILER_PATH");
            config.COMPILERINTERPRETERARGS = rs.getString("ARGS");
            config.EXPECTEDOUTCOME = rs.getString("EXPECTED");
            config.RUNCOMMAND = rs.getString("RUN_COMMAND");
            config.SELECTEDLANGUAGE = Language.valueOf(rs.getString("SELECTED_LANGUAGE"));
        }
        rs.close();
        stat.close();
        return config;
    }

    public void disconnect() throws SQLException {
        connection.close();
    }
}
