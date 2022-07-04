module br.com.alura.loja {
    requires javafx.controls;
    requires javafx.fxml;


    opens br.com.alura.loja to javafx.fxml;
    exports br.com.alura.loja;
}