package sashtgnv.plannerLogic.database;

import org.postgresql.jdbc.PgConnection;
import sashtgnv.plannerLogic.dates.DateMethods;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class DBConnection {

    private final static String URL = "jdbc:postgresql://localhost:5432/planner";
    private final static String LOGIN = "postgres";
    private final static String PASSWORD = "";

    private final static String insertQuery = "insert into notes (date,note) values (%s,%s)";
    private final static String selectNoteQuery = "select note from notes where date = %s";
    private final static String deleteDuplicateQuery = "delete from notes where date = %s";
    private final static String deleteOldNotesQuery = "delete from notes where extract(month from date) < extract(month from now())";
    private final static String selectDatesQuery = "select date from notes where extract(year from date)=%s and extract(month from date)=%s and note<>''";
    private static DBConnection dbConnection;
    private Connection connection = null;

    private DBConnection(){
        try{
            Class.forName("org.postgresql.Driver");
            this.connection = DriverManager.getConnection(URL,LOGIN,PASSWORD);
        }
        catch (SQLException | ClassNotFoundException e){
            System.out.println("connection not complite");
        }
    }

    public static DBConnection getInstance(){
        if (dbConnection == null){
            dbConnection = new DBConnection();
        }
        return dbConnection;
    }

//    public Connection getConnection(){
//        return this.connection;
//    }

    public void addNote(String text){
        String query1 = deleteDuplicateQuery.formatted("'" + DateMethods.dateToString() + "'");
        String query2 = insertQuery.formatted("'"+DateMethods.dateToString()+"'", "'"+text+"'");
        try(Statement statement = this.connection.createStatement()){
            statement.executeUpdate(query1);
            statement.executeUpdate(query2);
        }
        catch (SQLException e){
            e.getStackTrace();
        }
    }

    public String getNote(){
        String query = selectNoteQuery.formatted("'" + DateMethods.dateToString() + "'");
        try(Statement statement = this.connection.createStatement()){
            ResultSet res = statement.executeQuery(query);
            res.next();
            String result = res.getString(1);
            statement.close();
            return result;
        }
        catch (SQLException e){
            e.getStackTrace();
        }
        return null;
    }

    public ArrayList<GregorianCalendar> getDates(){
        int year = DateMethods.CURRENT_DATE.get(Calendar.YEAR);
        int month = DateMethods.CURRENT_DATE.get(Calendar.MONTH)+1;
        String query = selectDatesQuery.formatted(year,month);
        var array = new ArrayList<GregorianCalendar>();
        try(Statement statement = this.connection.createStatement()){
            ResultSet res = statement.executeQuery(query);
            while (res.next()){
                GregorianCalendar calendar = new GregorianCalendar();
                calendar.setTime(res.getDate(1));
                array.add(calendar);
            }
            return array;
        }
        catch (SQLException e){
            e.getStackTrace();
        }
        return null;
    }

    public void deleteOldNotes(){
        String query = deleteOldNotesQuery;
        try{
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            statement.close();
        }
        catch (SQLException e){
            e.getStackTrace();
        }
    }



    public static void main(String[] args) {

    }
}
