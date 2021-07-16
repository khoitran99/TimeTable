/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dbcontext.DBContext;
import entity.Slot;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author khoi.tranvan
 */
public class SlotDAO {

    /* get start time and end time of slot */
    public String getSlotStartEnd(int slotNO) throws Exception {
        DBContext db = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM slot WHERE slotNO = ?";
        String startend = "";
        try {
            db = new DBContext();
            con = db.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, slotNO);
            rs = ps.executeQuery();
            while (rs.next()) {
                startend = rs.getString("starttime") + "-" + rs.getString("endtime");
            }
        } catch (Exception e) {
            throw e;
        } finally {
            db.closeConnection(con, ps, rs);
        }
        return startend;
    }

    /* get all data from slot table in db */
    public List<Slot> getListSlot() throws Exception {
        DBContext db = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM slot";
        List<Slot> listSlot = new ArrayList<>();
        try {
            db = new DBContext();
            con = db.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Slot slot = new Slot(rs.getInt("slotNO"), rs.getString("starttime"), rs.getString("endtime"));
                listSlot.add(slot);
            }
            return listSlot;
        } catch (Exception e) {
            throw e;
        } finally {
            db.closeConnection(con, ps, rs);
        }
    }

}
