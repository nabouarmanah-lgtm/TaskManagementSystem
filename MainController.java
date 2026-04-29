/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

/**
 *
 * @author Admin
 */

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import models.Task;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class MainController {

    @FXML private ListView<Task> taskListView;
    @FXML private Label taskCountLabel;
    @FXML private ComboBox<String> statusCombo;
    @FXML private TextField titleField;
    @FXML private TextField addedByField;
    @FXML private TextField dateField;
    @FXML private TextArea resultArea;

    private Map<Integer, Task> taskMap = new LinkedHashMap<>();

    @FXML
    public void initialize() {
        if (statusCombo != null) {
            statusCombo.setItems(FXCollections.observableArrayList("open", "closed"));
        }
        loadTasksFromCSV();
        refreshListView();
    }

    private void loadTasksFromCSV() {
        try {
            URL resource = getClass().getResource("/data/tasks.csv");
            Files.lines(Paths.get(resource.toURI()))
                    .skip(1)
                    .map(line -> line.split(","))
                    .map(data -> new Task(
                            Integer.parseInt(data[0].trim()),
                            data[1].trim(),
                            data[2].trim(),
                            data[3].trim(),
                            data[4].trim()
                    ))
                    .forEach(t -> taskMap.put(t.getId(), t));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void refreshListView() {
        taskListView.getItems().setAll(taskMap.values());
        taskCountLabel.setText("Tasks: " + taskMap.size());
    }

    @FXML
    private void handleAddTask() {
        String title = titleField.getText().trim();
        String status = statusCombo.getValue();
        String addedBy = addedByField.getText().trim();
        String date = dateField.getText().trim();

        if (title.isEmpty()) {
            showAlert("Validation", "Title must not be empty."); return;
        }
        if (status == null || (!status.equals("open") && !status.equals("closed"))) {
            showAlert("Validation", "Status must be 'open' or 'closed'."); return;
        }
        if (addedBy.isEmpty()) {
            showAlert("Validation", "Added By must not be empty."); return;
        }
        if (!date.matches("\\d{4}-\\d{2}-\\d{2}")) {
            showAlert("Validation", "Date must be YYYY-MM-DD format."); return;
        }

        int newId = taskMap.keySet().stream().max(Integer::compareTo).orElse(0) + 1;
        taskMap.put(newId, new Task(newId, title, status, addedBy, date));
        refreshListView();
        handleClear();
        showAlert("Success", "Task added! ID: " + newId);
    }

    @FXML
    private void handleClear() {
        titleField.clear();
        statusCombo.setValue(null);
        addedByField.clear();
        dateField.clear();
    }

    @FXML
    private void handleTasksByAli() {
        List<Task> list = taskMap.values().stream()
                .filter(t -> t.getAddedBy().equalsIgnoreCase("Ali"))
                .sorted(Comparator.comparingInt(Task::getId))
                .collect(Collectors.toList());
        showResult("Tasks by Ali:", list);
    }

    @FXML
    private void handleEarliest4() {
        List<Task> list = taskMap.values().stream()
                .sorted(Comparator.comparing(Task::getCreationDate))
                .limit(4)
                .collect(Collectors.toList());
        showResult("Earliest 4 Tasks:", list);
    }

    @FXML
    private void handleASevenLetters() {
        List<Task> list = taskMap.values().stream()
                .filter(t -> t.getTitle().toLowerCase().startsWith("a"))
                .filter(t -> t.getTitle().length() == 7)
                .collect(Collectors.toList());
        showResult("Titles starting with A, 7 letters:", list);
    }

    @FXML
    private void handleMostActive() {
        String user = taskMap.values().stream()
                .collect(Collectors.groupingBy(Task::getAddedBy, Collectors.counting()))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .get().getKey();
        resultArea.setText("Most Active User: " + user);
    }

    @FXML
    private void handleCountStatus() {
        Map<String, Long> map = taskMap.values().stream()
                .collect(Collectors.groupingBy(Task::getStatus, Collectors.counting()));
        resultArea.setText("Open: " + map.getOrDefault("open", 0L) +
                "\nClosed: " + map.getOrDefault("closed", 0L));
    }

    @FXML
    private void handleTasksBySami() {
        long count = taskMap.values().stream()
                .filter(t -> t.getAddedBy().equalsIgnoreCase("Sami"))
                .count();
        List<Task> list = taskMap.values().stream()
                .filter(t -> t.getAddedBy().equalsIgnoreCase("Sami"))
                .collect(Collectors.toList());
        resultArea.setText("Tasks by Sami: " + count + "\n\n" +
                list.stream().map(Task::toString).collect(Collectors.joining("\n")));
    }

    private void showResult(String title, List<Task> list) {
        if (resultArea != null) {
            resultArea.setText(title + "\n\n" +
                    list.stream().map(Task::toString).collect(Collectors.joining("\n")));
        }
    }

    private void showAlert(String title, String msg) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}