package Utilities;

import GUI.Controllers.OptionsController;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;

import java.util.ArrayList;

public class Settings {
    private boolean FirstOrSecondMap;
    private boolean PVPorPVE;
    private ArrayList<SoldierLevel> botsLevels = new ArrayList<>();

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
        fillBotsLevels(controller);

        firstAidKit = controller.getFirstAidKit().isSelected();
        fasterReload = controller.getFasterReload().isSelected();
        strongerBullets = controller.getStrongerBullets().isSelected();
        fasterMovement = controller.getFasterMovement().isSelected();
    }

    private void fillBotsLevels(OptionsController controller) {
        botsLevels.add(getEnemyLevel(controller.getFirstOpponentsButtons()));
        botsLevels.add(getEnemyLevel(controller.getSecondOpponentsButtons()));
        botsLevels.add(getEnemyLevel(controller.getThirdOpponentsButtons()));
    }

    private SoldierLevel getEnemyLevel(ToggleGroup toggleGroup) {
        RadioButton secondOpponentRadioButton = (RadioButton) toggleGroup.getSelectedToggle();
        if (secondOpponentRadioButton.getText().equals("Easy")) {
            return SoldierLevel.EASY;
        }
        else if (secondOpponentRadioButton.getText().equals("Medium")){
            System.out.println("Medium");
            return SoldierLevel.MEDIUM;

        }
        else {
            return SoldierLevel.HARD;
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

    public SoldierLevel getOpponentsLevels(int index) {
        return botsLevels.get(index);
    }
}
