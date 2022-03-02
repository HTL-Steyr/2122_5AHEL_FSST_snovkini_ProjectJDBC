package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ToDo {

    private String dateInfo;
    private String email;
    private String personName;
    private int categoryID;
    private int toDoID;

    public ToDo(String dateInfo, String finished, String email, String name, int toDoID) {
        this.dateInfo = dateInfo;
        this.email = email;
        this.personName = personName;
        this.categoryID = categoryID;
        this.toDoID = toDoID;
    }

    public String getDateInfo() {
        return dateInfo;
    }

    public String getPersonName() {
        return personName;
    }

    public String getEmail() {
        return email;
    }


    public static ToDo getById(int toDoID) {


        ToDo result = null;

        try {
            Connection c = Database.getInstance();
            PreparedStatement statement = c.prepareStatement("SELECT * FROM snovkini_toDo WHERE toDo_ID = ?");

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
        ObservableList<ToDo> result = FXCollections.observableArrayList();

        try {
            Connection c = Database.getInstance();
            PreparedStatement statement = c.prepareStatement("SELECT * FROM snovkini_toDo");

            ResultSet results = statement.executeQuery();

            while (results.next()) {
                ToDo tmp = new ToDo(results.getInt("toDo_ID"), results.getString("toDo_name"));
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
