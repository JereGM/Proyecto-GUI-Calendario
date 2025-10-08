package com.example.calendar_view_2;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CalendarController {

    @FXML
    private BorderPane mainBorderPane;

    @FXML
    private Label monthYearLabel;

    @FXML
    private GridPane calendarGrid;

    @FXML
    private Label selectedDateLabel;

    @FXML
    private TextArea festivalInfoArea;

    // All button references
    @FXML private Button button1, button2, button3, button4, button5, button6, button7, button8;
    @FXML private Button button9, button10, button11, button12, button13, button14, button15, button16;
    @FXML private Button button17, button18, button19, button20, button21, button22, button23, button24;
    @FXML private Button button25, button26, button27, button28, button29, button30, button31;

    private Map<LocalDate, List<Event>> savedEvents;
    private int currentYear = 2025;
    private int currentMonth = 10; // October

    @FXML
    public void initialize() {
        // Load any saved events
        loadSavedEvents();
    }

    private void loadSavedEvents() {
        savedEvents = new HashMap<>();
        List<Event> events = CalendarApp.loadEvents();

        // Group events by date
        for (Event event : events) {
            if (event.getDate() != null) {
                savedEvents.computeIfAbsent(event.getDate(), k -> new java.util.ArrayList<>()).add(event);
            }
        }
    }

    private void handleDateClick(int day) {
        selectedDateLabel.setText("Selected: October " + day + ", 2025");

        // Display saved events for this date
        StringBuilder info = new StringBuilder();
        LocalDate selectedDate = LocalDate.of(currentYear, currentMonth, day);

        if (savedEvents.containsKey(selectedDate)) {
            info.append("Your Events:\n");
            for (Event event : savedEvents.get(selectedDate)) {
                info.append("- ").append(event.getDescription())
                        .append(" (").append(event.getStartTime())
                        .append(" - ").append(event.getEndTime()).append(")\n");
            }
        } else {
            info.append("No events on this day.");
        }

        festivalInfoArea.setText(info.toString());

        // Show dialog to add new event
        showAddEventDialog(day);
    }

    private void showAddEventDialog(int day) {
        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setTitle("Add Event - October " + day + ", 2025");

        // Create form fields
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20));

        Label descLabel = new Label("Description:");
        TextField descriptionField = new TextField();
        descriptionField.setPrefWidth(250);

        Label startLabel = new Label("Start Time:");
        TextField startField = new TextField();
        startField.setPromptText("e.g., 09:00 AM");

        Label endLabel = new Label("End Time:");
        TextField endField = new TextField();
        endField.setPromptText("e.g., 10:30 AM");

        Button saveButton = new Button("Save Event");
        Button cancelButton = new Button("Cancel");

        // Layout
        grid.add(descLabel, 0, 0);
        grid.add(descriptionField, 1, 0);
        grid.add(startLabel, 0, 1);
        grid.add(startField, 1, 1);
        grid.add(endLabel, 0, 2);
        grid.add(endField, 1, 2);

        VBox buttonBox = new VBox(10);
        buttonBox.getChildren().addAll(saveButton, cancelButton);
        buttonBox.setPadding(new Insets(10, 0, 0, 0));
        grid.add(buttonBox, 1, 3);

        // Save button action
        saveButton.setOnAction(e -> {
            String description = descriptionField.getText().trim();
            String startTime = startField.getText().trim();
            String endTime = endField.getText().trim();

            if (description.isEmpty() || startTime.isEmpty() || endTime.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Incomplete Information");
                alert.setHeaderText(null);
                alert.setContentText("Please fill in all fields");
                alert.showAndWait();
                return;
            }

            // Load existing events
            List<Event> events = CalendarApp.loadEvents();

            // Create and add new event with date
            LocalDate eventDate = LocalDate.of(currentYear, currentMonth, day);
            Event newEvent = new Event(description, startTime, endTime, eventDate);
            events.add(newEvent);

            // Save to file
            CalendarApp.saveEvents(events);

            // Update local cache
            LocalDate selectedDate = LocalDate.of(currentYear, currentMonth, day);
            savedEvents.computeIfAbsent(selectedDate, k -> new java.util.ArrayList<>()).add(newEvent);

            // Show success message
            Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
            successAlert.setTitle("Success");
            successAlert.setHeaderText(null);
            successAlert.setContentText("Event saved successfully!");
            successAlert.showAndWait();

            // Refresh the display
            handleDateClick(day);

            dialog.close();
        });

        // Cancel button action
        cancelButton.setOnAction(e -> dialog.close());

        Scene dialogScene = new Scene(grid, 400, 250);
        dialog.setScene(dialogScene);
        dialog.showAndWait();
    }

    // Event handlers for all 31 days
    @FXML
    void clicked1(MouseEvent event) {
        handleDateClick(1);
    }

    @FXML
    void clicked2(MouseEvent event) {
        handleDateClick(2);
    }

    @FXML
    void clicked3(MouseEvent event) {
        handleDateClick(3);
    }

    @FXML
    void clicked4(MouseEvent event) {
        handleDateClick(4);
    }

    @FXML
    void clicked5(MouseEvent event) {
        handleDateClick(5);
    }

    @FXML
    void clicked6(MouseEvent event) {
        handleDateClick(6);
    }

    @FXML
    void clicked7(MouseEvent event) {
        handleDateClick(7);
    }

    @FXML
    void clicked8(MouseEvent event) {
        handleDateClick(8);
    }

    @FXML
    void clicked9(MouseEvent event) {
        handleDateClick(9);
    }

    @FXML
    void clicked10(MouseEvent event) {
        handleDateClick(10);
    }

    @FXML
    void clicked11(MouseEvent event) {
        handleDateClick(11);
    }

    @FXML
    void clicked12(MouseEvent event) {
        handleDateClick(12);
    }

    @FXML
    void clicked13(MouseEvent event) {
        handleDateClick(13);
    }

    @FXML
    void clicked14(MouseEvent event) {
        handleDateClick(14);
    }

    @FXML
    void clicked15(MouseEvent event) {
        handleDateClick(15);
    }

    @FXML
    void clicked16(MouseEvent event) {
        handleDateClick(16);
    }

    @FXML
    void clicked17(MouseEvent event) {
        handleDateClick(17);
    }

    @FXML
    void clicked18(MouseEvent event) {
        handleDateClick(18);
    }

    @FXML
    void clicked19(MouseEvent event) {
        handleDateClick(19);
    }

    @FXML
    void clicked20(MouseEvent event) {
        handleDateClick(20);
    }

    @FXML
    void clicked21(MouseEvent event) {
        handleDateClick(21);
    }

    @FXML
    void clicked22(MouseEvent event) {
        handleDateClick(22);
    }

    @FXML
    void clicked23(MouseEvent event) {
        handleDateClick(23);
    }

    @FXML
    void clicked24(MouseEvent event) {
        handleDateClick(24);
    }

    @FXML
    void clicked25(MouseEvent event) {
        handleDateClick(25);
    }

    @FXML
    void clicked26(MouseEvent event) {
        handleDateClick(26);
    }

    @FXML
    void clicked27(MouseEvent event) {
        handleDateClick(27);
    }

    @FXML
    void clicked28(MouseEvent event) {
        handleDateClick(28);
    }

    @FXML
    void clicked29(MouseEvent event) {
        handleDateClick(29);
    }

    @FXML
    void clicked30(MouseEvent event) {
        handleDateClick(30);
    }

    @FXML
    void clicked31(MouseEvent event) {
        handleDateClick(31);
    }
}