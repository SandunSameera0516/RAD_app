module com.example.javafx_rad {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.javafx_rad to javafx.fxml;
    exports com.example.javafx_rad;
}