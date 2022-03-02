import javafx.event.ActionEvent;
import model.Kategorie;
import model.ToDo;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.TextField;

//import static email.JavaEmail.prepareMessage;

public class Controller {

    public TextField personInfo;
    public TextField dateInfo;
    public ListView<Kategorie> categoryList;
    public ListView<ToDo> toDoList;

    public void initialize() {
        categoryList.getItems().addAll(Kategorie.loadAll());
    }

    public void categoryListViewClicked(MouseEvent mouseEvent) {
        Kategorie selected = categoryList.getSelectionModel().getSelectedItem();
        if (selected != null) {
            toDoList.setItems(selected.loadToDo());
        }
    }

    public void toDoListViewClicked(MouseEvent mouseEvent) {
        ToDo selected = toDoList.getSelectionModel().getSelectedItem();
        if (selected != null) {
            personInfo.setText(selected.getPersonName());
            dateInfo.setText(selected.getDateInfo());
        }

    }

    public void messagePerson(ActionEvent actionEvent) {
        System.out.println("Nachricht gesendet");
    }

}



