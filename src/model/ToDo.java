package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ToDo {

    private String dateInfo;
    private String finished;
    private String email;
    private String personName;
    private Kategorie category;
    private int categoryID;
    private int toDoID;

    ToDo(String dateInfo, String finished, String email, String name, Kategorie category, int toDoID){
        this.dateInfo=dateInfo;
        this.finished=finished;
        this.email=email;
        this.personName=personName;
        this.categoryID=categoryID;
        this.toDoID=toDoID;
    }

    public String getDateInfo(){
        return dateInfo;
    }
    public String getpersonInfo(){
        return personName;
    }
    public String getEmail(){
        return email;
    }

    public static ToDo getById(int toDoID) {


        Kategorie result = null;

        try {
            Connection c= Database.getInstance();
            PreparedStatement statement= c.prepareStatement("SELECT * FROM snovkini_toDo WHERE toDo_ID = ?");

            statement.setInt(1, toDoID);

            ResultSet results = statement.executeQuery();

            if (results.next()) {
                result = new ToDo(results.getInt("toDo_ID"), results.getString("toDo_name));
            }

            results.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static ObservableList<ToDo> loadAll() {
        //load all ToDos from the database
        ObservableList<Kategorie> result = FXCollections.observableArrayList();

        try {
            Connection c = Database.getInstance();
            PreparedStatement statement = c.prepareStatement("SELECT * FROM snovkini_toDo");

            ResultSet results = statement.executeQuery();       //querry weil Abfrage

            while (results.next()) {                                    //if reicht weil nur 1 zurück kommt
                ToDo tmp = new ToDo(results.getInt("department_id"), results.getString("dcategorie_name"));
                result.add(tmp);
            }

            results.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

}
