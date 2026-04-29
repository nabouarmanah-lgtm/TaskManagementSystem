/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author Admin
 */

public class Task {
    private int id;
    private String title;
    private String status;
    private String addedBy;
    private String creationDate;

    public Task(int id, String title, String status, String addedBy, String creationDate) {
        this.id = id;
        this.title = title;
        this.status = status;
        this.addedBy = addedBy;
        this.creationDate = creationDate;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getStatus() { return status; }
    public String getAddedBy() { return addedBy; }
    public String getCreationDate() { return creationDate; }

    @Override
    public String toString() {
        return "[" + id + "] " + title + " | " + status + " | By: " + addedBy + " | " + creationDate;
    }
}