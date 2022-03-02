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
    private int toDoID;
    private String toDoName;

    public ToDo(String toDoName, String dateInfo, String personName) {
        this.dateInfo = dateInfo;
        this.personName = personName;
        this.toDoID = toDoID;
        this.toDoName = toDoName;
    }

    public ToDo(String toDoName) {
        this.toDoName = toDoName;
    }

    public String getDateInfo() {
        return dateInfo;
    }

    @Override
    public String toString() {
        return toDoName;
    }

    public String getPersonName() {
        return personName;
    }

    public String getEmail() {
        return email;
    }


    public static ToDo getById(int toDoID) {
        ToDo result = null;
//load all ToDos by ID
        try {
            Connection c = Database.getInstance();
            PreparedStatement statement = c.prepareStatement("SELECT * FROM snovkini_toDo WHERE toDo_ID = ?");

            statement.setInt(1, toDoID);

            ResultSet results = statement.executeQuery();

            if (results.next()) {
                result = new ToDo(
                        results.getString("toDo_name"),
                        results.getString("date"),
                        results.getString("person_Name"));
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
                ToDo tmp = new ToDo(
                        results.getString("toDo_name"),
                        results.getString("date"),
                        results.getString("person_Name"));
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
