/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dbcontext.DBContext;
import entity.Timetable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author khoi.tranvan
 */
public class TimetableDAO {

    /* get list timetable from selected date to selected date */
    public List<Timetable> getListTimetableByDate(Date from, Date to) throws Exception {
        DBContext db = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM timetable WHERE [date] BETWEEN ? AND ? ORDER BY [date]";
        List<Timetable> listTimetable = new ArrayList<>();
        try {
            db = new DBContext();
            con = db.getConnection();
            ps = con.prepareStatement(sql);
            ps.setDate(1, from);
            ps.setDate(2, to);
            rs = ps.executeQuery();
            while (rs.next()) {
                Timetable timetable = new Timetable();
                timetable.setId(rs.getInt("id"));
                timetable.setSlot(rs.getInt("slot"));
                timetable.setRoom(rs.getInt("room"));
                timetable.setTeacher(rs.getString("teacher"));
                timetable.setDate(rs.getDate("date"));
                timetable.setClassname(rs.getString("class"));
                listTimetable.add(timetable);
            }
            return listTimetable;
        } catch (Exception e) {
            throw e;
        } finally {
            db.closeConnection(con, ps, rs);
        }
    }
    
    public int insertTimetable(Date date, int slot, int room, String teacher, String course) throws Exception {
        DBContext db = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "INSERT INTO timetable ([date],slot,class,room,teacher)\n"
                + "VALUES (?,?,?,?,?)";
        try {
            db = new DBContext();
            con = db.getConnection();
            ps = con.prepareStatement(sql);
            ps.setDate(1, date);
            ps.setInt(2, slot);
            ps.setString(3, course);
            ps.setInt(4, room);
            ps.setString(5, teacher);
            return ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            db.closeConnection(con, ps, rs);
        }
    }
}
