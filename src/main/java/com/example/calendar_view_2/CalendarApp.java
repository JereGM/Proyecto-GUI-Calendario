package com.example.calendar_view_2;

import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CalendarApp extends Application {

    private static final String FILE_NAME = "events.json";
    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Method to load events from JSON file
     * @return List of Event objects, or empty list if file doesn't exist
     */
    public static List<Event> loadEvents() {
        try (FileReader reader = new FileReader(FILE_NAME)) {
            Event[] eventsArray = objectMapper.readValue(reader, Event[].class);
            List<Event> events = new ArrayList<>();
            for (Event e : eventsArray) {
                events.add(e);
            }
            return events;
        } catch (IOException e) {
            return new ArrayList<>(); // if file doesn't exist, return empty list
        }
    }

    /**
     * Method to save events to JSON file
     * @param events List of Event objects to save
     */
    public static void saveEvents(List<Event> events) {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(FILE_NAME))) {
            objectMapper.writeValue(writer, events);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start(Stage stage) throws Exception {
        // Use an absolute resource path to avoid class-path surprises
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/com/example/calendar_view_2/calendar.fxml")
        );

        Scene scene = new Scene(loader.load()); // sizes to FXML preferred sizes
        stage.setTitle("Festival Calendar");
        stage.setScene(scene);

        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}