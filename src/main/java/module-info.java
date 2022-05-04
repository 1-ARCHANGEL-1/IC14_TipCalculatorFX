module edu.miracosta.cs112.cphilip.ic14_tipcalculatorfx {
    requires javafx.controls;
    requires javafx.fxml;


    opens edu.miracosta.cs112.cphilip.ic14_tipcalculatorfx to javafx.fxml;
    exports edu.miracosta.cs112.cphilip.ic14_tipcalculatorfx;
}