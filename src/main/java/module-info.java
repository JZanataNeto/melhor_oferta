module com.joaozanata.melhor_oferta {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.joaozanata.melhor_oferta to javafx.fxml;
    exports com.joaozanata.melhor_oferta;
}