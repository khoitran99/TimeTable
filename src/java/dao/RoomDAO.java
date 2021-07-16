/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dbcontext.DBContext;
import entity.Room;
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
public class RoomDAO {

    /* get list room from database */
    public List<Room> getListRoom() throws Exception {
        DBContext db = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM room";
        List<Room> listRoom = new ArrayList<>();
        try {
            db = new DBContext();
            con = db.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                listRoom.add(new Room(rs.getInt("id"), rs.getInt("name")));
            }
            return listRoom;
        } catch (Exception e) {
            throw e;
        } finally {
            db.closeConnection(con, ps, rs);
        }
    }

    
    /* Check if teacher teaches 2 class/course in the same time */
    public boolean isValidRoomTime(Date date, int slot, int room) throws Exception {
        DBContext db = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM timetable WHERE [date] = ? AND slot = ? AND room = ?";
        try {
            db = new DBContext();
            con = db.getConnection();
            ps = con.prepareStatement(sql);
            ps.setDate(1, date);
            ps.setInt(2, slot);
            ps.setInt(3, room);
            rs = ps.executeQuery();
            return rs.next();
        } catch (Exception e) {
            throw e;
        } finally {
            db.closeConnection(con, ps, rs);
        }
    }
 
}
