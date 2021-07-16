/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author khoi.tranvan
 */
public class Slot {
    private int slotNo;
    private String start;
    private String end;

    public Slot() {
    }

    public Slot(int slotNo, String start, String end) {
        this.slotNo = slotNo;
        this.start = start;
        this.end = end;
    }
    

    public int getSlotNo() {
        return slotNo;
    }

    public void setSlotNo(int slotNo) {
        this.slotNo = slotNo;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }
    
    
}
