package com.example.calendar_view_2;


import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.time.LocalDate;
import java.io.File;
import java.util.Scanner;

public class EventSaver {
    public static void saveEvent(LocalDate date, String eventText) {
        try (FileWriter writer = new FileWriter("events.txt", true)) {
            writer.write(date.toString() + " | " + eventText + "\n");
            System.out.println("Event saved: " + date + " - " + eventText);
        } catch (IOException e) {
            System.err.println("Error saving event: " + e.getMessage());
        }
    }

    public static Map<LocalDate, String> loadEvents() {
        Map<LocalDate, String> events = new HashMap<>();
        try {
            File file = new File("events.txt");
            if (file.exists()) {
                Scanner scanner = new Scanner(file);
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    String[] parts = line.split(" \\| ", 2);
                    if (parts.length == 2) {
                        LocalDate date = LocalDate.parse(parts[0]);
                        events.put(date, parts[1]);
                    }
                }
                scanner.close();
            }
        } catch (IOException e) {
            System.err.println("Error loading events: " + e.getMessage());
        }
        return events;
    }
}