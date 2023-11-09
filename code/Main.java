import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

public class Main extends Application
{
    private Button showData;
    private TextField inputCity;
    private ComboBox<String> selectSource;
    private Label weatherData;
    private WeatherData data;
    
    private VBox layout;
    private Scene scene;
    
    public static void main(String[] args){
        launch(args);
    }
    
    @Override
    public void start(Stage stage) {
        data = new Adapter();
        
        selectSource = new ComboBox<>();
        selectSource.setPromptText("Select a source");
        selectSource.setMaxSize(150, 30);
        selectSource.getItems().addAll("OpenWeatherMap", "WeatherStack");
        
        inputCity = new TextField();
        inputCity.setPromptText("Write a city");
        inputCity.setMaxSize(150, 30);
        
        showData = new Button("Show");
        showData.setOnAction(event -> {
            String cityName = inputCity.getText();
            String source = selectSource.getValue();
            data.setSource(source);
            data.setCity(cityName);
            
            weatherData.setText(data.getData());
        });
        
        weatherData = new Label();
        
        layout = new VBox(selectSource, inputCity, showData, weatherData);
        layout.setSpacing(20);
        layout.setAlignment(Pos.TOP_CENTER);
        layout.setPadding(new Insets(20, 0, 0, 0));
        
        scene = new Scene(layout, 250, 300);
        
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.setTitle("Weather App");
        stage.setScene(scene);
        stage.show();
    }
    public static void alertError(String text) {
        Alert alert = new Alert(AlertType.ERROR, text, ButtonType.OK);
        alert.setTitle("ERROR");
        alert.setHeaderText(null);
        alert.showAndWait();
    }
}
