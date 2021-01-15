package Utilities;

import GUI.Controllers.OptionsController;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;

public class Settings {
    private boolean FirstOrSecondMap;
    private boolean PVPorPVE;
    private String firstOpponent;
    private String secondOpponent;
    private String thirdOpponent;

    private boolean firstAidKit;
    private boolean strongerBullets;
    private boolean fasterReload;
    private boolean fasterMovement;

    public Settings(OptionsController controller) {
        ToggleGroup versionButtons = controller.getVersionButtons();
        RadioButton versionRadioButton = (RadioButton) versionButtons.getSelectedToggle();
        PVPorPVE = versionRadioButton.getText().equals("PVP");

        ToggleGroup mapChanger = controller.getMapChanger();
        RadioButton selectedMapRadioButton = (RadioButton) mapChanger.getSelectedToggle();
        FirstOrSecondMap = selectedMapRadioButton.getText().equals("First");
        System.out.println(isFirstOrSecondMap());
        firstOpponent = getEnemyLevel(controller.getFirstOpponentsButtons());
        secondOpponent = getEnemyLevel(controller.getSecondOpponentsButtons());
        thirdOpponent = getEnemyLevel(controller.getThirdOpponentsButtons());

        firstAidKit = controller.getFirstAidKit().isSelected();
        fasterReload = controller.getFasterReload().isSelected();
        strongerBullets = controller.getStrongerBullets().isSelected();
        fasterMovement = controller.getFasterMovement().isSelected();
    }

    private String getEnemyLevel(ToggleGroup toggleGroup) {
        RadioButton secondOpponentRadioButton = (RadioButton) toggleGroup.getSelectedToggle();
        if (secondOpponentRadioButton.getText().equals("EASY")) {
            return "EASY";
        }
        else if (secondOpponentRadioButton.getText().equals("MEDIUM")){
            return "MEDIUM";
        }
        else {
            return "HARD";
        }
    }

    public boolean isPVPorPVE() {
        return PVPorPVE;
    }

    public boolean isFasterMovement() {
        return fasterMovement;
    }

    public boolean isFasterReload() {
        return fasterReload;
    }

    public boolean isFirstAidKit() {
        return firstAidKit;
    }

    public boolean isFirstOrSecondMap() {
        return FirstOrSecondMap;
    }

    public boolean isStrongerBullets() {
        return strongerBullets;
    }

    public String getFirstOpponent() {
        return firstOpponent;
    }

    public String getSecondOpponent() {
        return secondOpponent;
    }

    public String getThirdOpponent() {
        return thirdOpponent;
    }
}
