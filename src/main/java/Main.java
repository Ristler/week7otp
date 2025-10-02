import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    private TextField tempField = new TextField();
    private Label resultLabel = new Label();


    private double result;

    public static void main(String[] args) {
        launch(args);
    }

    private String choice = "nan";

    @Override
    public void start(Stage stage) {
        tempField.setPromptText("Enter temperature");

        Button convertButton = new Button("Convert");
        Button celsiusToFahrenheitButton = new Button("Celsius to fahrenheit");
        Button fahrenheitToCelsiusButton = new Button("Fahrenheit to celsius");
        Button kelvinToCelsiusButton = new Button("Kelvin to celsius");
        Button celsiusToKelvinButton = new Button("Celsius to kelvin");

        convertButton.setOnAction(e -> {
            try {
                double input = Double.parseDouble(tempField.getText());
                result = TempConverter.convert(choice, input);
                resultLabel.setText(String.format("%.2f", result));
            } catch (NumberFormatException ex) {
                resultLabel.setText("Invalid input!");
            }
        });

        celsiusToFahrenheitButton.setOnAction(e -> setData(celsiusToFahrenheitButton.getText(), stage, "Celsius to fahrenheit"));
        fahrenheitToCelsiusButton.setOnAction(e -> setData(fahrenheitToCelsiusButton.getText(), stage, "Fahrenheit to celsius"));
        kelvinToCelsiusButton.setOnAction(e -> setData(kelvinToCelsiusButton.getText(), stage, "Kelvin to celsius"));
        celsiusToKelvinButton.setOnAction(e -> setData(celsiusToKelvinButton.getText(), stage, "Celsius to kelvin"));


        Button saveButton = new Button("Save to DB");


        saveButton.setOnAction(e -> Database.saveTemperature(
                Double.parseDouble(tempField.getText()), result, choice, resultLabel));


        VBox root = new VBox(10, tempField, convertButton,
                celsiusToFahrenheitButton,
                fahrenheitToCelsiusButton,
                kelvinToCelsiusButton,
                celsiusToKelvinButton,
                resultLabel,
                saveButton);
        Scene scene = new Scene(root, 350, 300);
        stage.setTitle("Choose a conversion");

        stage.setScene(scene);
        stage.show();
    }
    private void setData(String option, Stage stage, String title) {
        choice = option;
        stage.setTitle(title);
    }
}