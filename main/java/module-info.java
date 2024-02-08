module com.example.contactmanagementsystem {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.contactmanagementsystem to javafx.fxml;
    exports com.example.contactmanagementsystem;
}