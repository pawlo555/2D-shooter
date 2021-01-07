package GUI.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

public class VersionChooseController {

    @FXML private Button nextButton;
    @FXML private ToggleGroup versionButtons;

    public void nextPressed() {
        System.out.println("NextPressed");
        RadioButton selectedRadioButton =
                (RadioButton) versionButtons.getSelectedToggle();
        System.out.println(selectedRadioButton.getText());
        //TODO scene change and not selecting element save
    }
}
