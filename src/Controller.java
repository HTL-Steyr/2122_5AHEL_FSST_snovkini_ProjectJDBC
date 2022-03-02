import javax.jms.Message;
import javax.jms.Session;
import java.awt.*;
import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.util.Properties;

import com.sun.jdi.connect.Transport;
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
        public void toDoListViewClicked(MouseEvent mouseEvent){
            ToDo selectedItem = toDoList.getSelectionModel().getSelectedItem();
            personInfo.setText(selectedItem.getPersonName());
            dateInfo.setText(selectedItem.getDateInfo());
        }

    public void messagePerson(ActionEvent actionEvent) {
        System.out.println("Nachricht gesendet");
    }


        /*public void messagePerson (ActionEvent actionEvent){
            ToDo person = null;
            Properties properties = new Properties();
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.starttls.enable", "true");
            properties.put("mail.smtp.host", "smtp.gmail.com");
            properties.put("mail.smtp.port", "587");

            String myAccount = "allfamily@gmail.com"; //Gmail Server
            String myPassword = "familypassword";
            String empfaenger = person.getEmail();

            Session session = Session.getInstance(properties, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(myAccount,myPassword);
                }
            });

            // Message-Objekt erzeugen und senden!
            try {
                Message message = prepareMessage(session, myAccount, empfaenger);
                Transport.send(message); // E-Mail senden!
                System.out.println("Person hat eine Nachricht bekommen!");
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }*/
    }



