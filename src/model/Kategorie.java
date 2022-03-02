package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Kategorie {

    private String name;
    private int categoryID;

    private Kategorie(int categoryID, String name) {
        this.categoryID = categoryID;
        this.name = name;
    }

    public static Kategorie getById(int categoryID) {
        //load one single department with the given id

        Kategorie result = null;

        try {
            Connection c = Database.getInstance();
            PreparedStatement statement = c.prepareStatement("SELECT * FROM snovkini_categories WHERE categories_ID = ?");

            statement.setInt(1, categoryID);

            ResultSet results = statement.executeQuery();

            if (results.next()) {
                result = new Kategorie(results.getInt("categories_ID"), results.getString("categorie_name"));
            }

            results.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static ObservableList<Kategorie> loadAll() {
        //load all Categories from the database
        ObservableList<Kategorie> result = FXCollections.observableArrayList();

        try {
            Connection c = Database.getInstance();
            PreparedStatement statement = c.prepareStatement("SELECT * FROM snovkini_categories");

            ResultSet results = statement.executeQuery();       //querry weil Abfrage

            while (results.next()) {
                Kategorie tmp = new Kategorie(results.getInt("categories_ID"), results.getString("categorie_name"));
                result.add(tmp);
            }

            results.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public ObservableList<ToDo> loadToDo() {
        /**
         * @ToDo
         * load all ToDos for this Category!
         */

        ObservableList<ToDo> result = FXCollections.observableArrayList();

        try {
            Connection c = Database.getInstance();
            PreparedStatement statement = c.prepareStatement("Select * From employees WHERE department_id = ?");

            statement.setInt(1, this.categoryID);
            ResultSet results = statement.executeQuery();       //querry weil Abfrage

            while (results.next()) {                                    //if reicht weil nur 1 zurück kommt
                //Department tmp = new Department(results.getInt("department_id"), results.getString("department_name"));
                result.add(new ToDo(results.getInt("toDo_ID"),
                        results.getString("toDo_name"),
                        results.getString("date"),
                        results.getString("person_Name"),
                        results.getString("emailOfPerson")));
            }

            results.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return result;
    }
}
