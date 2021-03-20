package ru.weatherclub;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import ru.weatherclub.formatter.WeatherFormatter;
import ru.weatherclub.model.Weather;
import ru.weatherclub.weatherapi.ListService;
import ru.weatherclub.weatherapi.WeatherService;

import java.util.ArrayList;
import java.util.List;

public class GuiWeather extends Application {
    private App app = new App();
    private TextField textField = new TextField();
    private List<Text> texts = new ArrayList<>(10);
    private List<WeatherService> weatherServiceList = new ArrayList<>();

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        for (int i = 0; i < 3; i++){
            weatherServiceList.add(new WeatherService());
        }

        Group group = new Group();
        HBox poisk = new HBox();
        HBox service = new HBox();
        VBox osnova = new VBox();

        Button button = new Button("найти город");
        Button buttonAvailable = new Button("проверить сервисы");
        ChoiceBox choiceService = new ChoiceBox();
        choiceService.setItems(FXCollections.observableArrayList(
                 "weatherstack.com", "openweathermap.org", "weatherbit.io"
        ));
        choiceService.getSelectionModel().selectFirst();
        Label label = new Label("выберите источник");

        osnova.setSpacing(10);
        osnova.setPadding(new Insets(10));
        poisk.setSpacing(10);
        service.setSpacing(10);
        Scene scene = new Scene(group, 400, 350);

        poisk.getChildren().addAll(textField, button, buttonAvailable);
        service.getChildren().addAll(choiceService, label);
        osnova.getChildren().addAll(poisk, service);
        for (int i = 0; i < 10; i++) {
            Text text = new Text();
            texts.add(text);
            osnova.getChildren().add(texts.get(i));
        }
        button.setOnAction(actionEvent -> {
            int choice = choiceService.getSelectionModel().getSelectedIndex();
            btn(choice);
        });
        buttonAvailable.setOnAction(actionEvent -> {
            btn2();
        });
        textField.setOnAction(actionEvent -> {
            int choice = choiceService.getSelectionModel().getSelectedIndex();
            btn(choice);
        });

        primaryStage.setTitle("Синоптик");
        primaryStage.setScene(scene);
        group.getChildren().addAll(osnova);
        primaryStage.show();
    }
    private void btn(int choice){
        String s = app.city(weatherServiceList.get(choice), textField.getText(), choice);
        String[] s1 = s.split("\n");
        for (int i = 0; i < s1.length; i++){
            texts.get(i).setText(s1[i]);
        }
    }
    private void btn2(){
        ListService list = new ListService();
        for (Text text : texts){
            text.setText("");
        }
        for (int i = 0; i < weatherServiceList.size(); i++){
            String s = app.city(weatherServiceList.get(i),"moscow", i);
            if (s.equals("")){
                texts.get(i).setText(list.listService(i) + " недоступен");
            }
            else {
                texts.get(i).setText(list.listService(i) + " доступен");
            }
        }
    }
}
