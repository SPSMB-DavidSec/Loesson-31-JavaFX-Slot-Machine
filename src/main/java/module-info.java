module cz.spsmb.sec.slot_machine {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;


    opens cz.spsmb.sec.slot_machine to javafx.fxml;
    exports cz.spsmb.sec.slot_machine;
}