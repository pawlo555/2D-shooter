package GUI.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleGroup;

public class PVEOptionsController {

    @FXML private ToggleGroup firstOpponentsButtons;
    @FXML private ToggleGroup secondOpponentsButtons;
    @FXML private ToggleGroup thirdOpponentsButtons;
    @FXML private Button start;

    public void startPVE() {
        System.out.println("Start a game");
    }
}
