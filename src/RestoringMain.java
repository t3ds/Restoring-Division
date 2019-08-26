//Contains main function and thus is the driver file
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import java.util.Arrays;
import java.io.IOException;

import static javafx.scene.paint.Color.RED;

public class RestoringMain extends Application {
//Create instance RestoringDivision Class
    RestoringDivision rd = new RestoringDivision();

    public static void main(String[] args) {

        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws IOException {
//Loading the  FXML file for the GUI
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/RestoringMain.fxml"));
        Parent root =  loader.load();

        primaryStage.setTitle("Restoring Division");
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();

    }
//Initializations

    @FXML private Label Rb_ans;
    @FXML private Label Rd_ans;
    @FXML private Label Qb_ans;
    @FXML private Label Qd_ans;
    @FXML private TextField TextField1;
    @FXML private TextField TextField2;
    @FXML private TextArea TextArea1;

    @FXML protected void EnterButtonEvent(ActionEvent e) {

        TextArea1.setEditable(false);
        int A[] = {0, 0, 0, 0, 0, 0, 0, 0};
        int B[] = {0, 0, 0, 0, 0, 0, 0, 0};

        //Checks if the input is valid and either shows an error or takes the input for further solving
        if (TextField1.getText().isEmpty() || TextField2.getText().isEmpty()) {
            TextArea1.setText("ERROR! PLEASE ENTER THE VALUES FOR DIVISOR AND DIVIDEND");
        }

        else if((Integer.parseInt(TextField1.getText())<0 || Integer.parseInt(TextField1.getText())>127) ||
                (Integer.parseInt(TextField2.getText())<0 || Integer.parseInt(TextField2.getText())>127))
        {
            TextArea1.setText("ERROR! PLEASE ENTER A NUMBER BETWEEN 0-127");
        }
        else {
            int dividend = Integer.parseInt(TextField1.getText());
            int divisor = Integer.parseInt(TextField2.getText());


            int C[] = rd.binary(dividend, A);
            int D[] = rd.binary(divisor,B);

             StringBuilder fieldContent = new StringBuilder();

            fieldContent.append(Integer.toString(dividend) + " = ");
            for (int i = 0; i < 8; i++) {
                fieldContent.append(Integer.toString(C[i]));
            }

            fieldContent.append("\n" + divisor + " = ");
            for (int i = 0; i < 8; i++) {
                fieldContent.append(Integer.toString(D[i]));
            }

            fieldContent.append("\n\n-------------------------------------------------------------------------------------------------------------------------------\n");
            fieldContent.append("OPERATION\t\t\tM\t\t\tA\t\t\tQ\n");

            //Actual start of algorithm is here
            fieldContent.append(rd.compute(C,D));

            TextArea1.setText(fieldContent.toString());

            String quotient="";
            String remainder="";
            for(int i=0;i<8;i++)
            {
                quotient=quotient + rd.Q[i];
                remainder=remainder + rd.A[i];
            }

            Qb_ans.setText(quotient);
            Qd_ans.setText(String.valueOf(Integer.parseInt(quotient,2)));
            Rb_ans.setText(remainder);
            Rd_ans.setText(String.valueOf(Integer.parseInt(remainder,2)));
        }

    }
}
