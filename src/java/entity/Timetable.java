/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import dao.SlotDAO;
import java.sql.Date;

/**
 *
 * @author khoi.tranvan
 */
public class Timetable {

    private int id;
    private Date date;
    private int slot;
    private String classname;
    private int room;
    private String teacher;

    public Timetable(int id, Date date, int slot, String classname, int room, String teacher) {
        this.id = id;
        this.date = date;
        this.slot = slot;
        this.classname = classname;
        this.room = room;
        this.teacher = teacher;
    }

    public Timetable() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getSlot() {
        return slot;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public int getRoom() {
        return room;
    }

    public void setRoom(int room) {
        this.room = room;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getSlotDetail() throws Exception {
        SlotDAO slotDAO = new SlotDAO();
        return slotDAO.getSlotStartEnd(this.slot);
    }
}
