/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.CourseDAO;
import dao.RoomDAO;
import dao.SlotDAO;
import dao.TeacherDAO;
import dao.TimetableDAO;
import entity.Course;
import entity.Room;
import entity.Slot;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author khoi.tranvan
 */
public class AddController extends HttpServlet {

    public Date getTomorrowDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 1);
        return new java.sql.Date(calendar.getTimeInMillis());
    }

    public void formData(HttpServletRequest request, HttpServletResponse response) throws Exception {
        SlotDAO slotDAO = new SlotDAO();
        CourseDAO courseDAO = new CourseDAO();
        RoomDAO roomDAO = new RoomDAO();

        List<Slot> listSlot = slotDAO.getListSlot();
        List<Course> listCourse = courseDAO.getListCourse();
        List<Room> listRoom = roomDAO.getListRoom();

        request.setAttribute("listSlot", listSlot);
        request.setAttribute("listCourse", listCourse);
        request.setAttribute("listRoom", listRoom);
        request.setAttribute("currentDate", getTomorrowDate());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        try {
            formData(request, response);
            request.getRequestDispatcher("add.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("error", e.toString());
            request.getRequestDispatcher("add.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        try {
            String teacher = request.getParameter("teacher").trim();
            int slot = Integer.parseInt(request.getParameter("slot"));
            int room = Integer.parseInt(request.getParameter("room"));
            String course = request.getParameter("course");
            Date date = Date.valueOf(request.getParameter("date"));

            TeacherDAO teacherDAO = new TeacherDAO();
            RoomDAO roomDAO = new RoomDAO();
            TimetableDAO timetableDAO = new TimetableDAO();
            CourseDAO courseDAO = new CourseDAO();

            if (teacher.equals("")) {
                /* check if teacher is empty */
                request.setAttribute("alert", "Teacher is empty . Please fill this field!");
            } else if (!teacherDAO.isValidTeacher(teacher)) {
                /* check if teacher is not in database */
                request.setAttribute("alert", "Teacher doesn't exist in database . ");
            } else if (teacherDAO.isValidTeacherTime(date, slot, teacher)) {
                /* check if teacher is teaching at the same time */
                request.setAttribute("alert", "Teacher " + teacher + " is teaching at this time . Please book another teacher");
            } else if (roomDAO.isValidRoomTime(date, slot, room)) {
                /* check if room is in use at the same time */
                request.setAttribute("alert", "Room " + room + " is booked at this time . Please book another room");
            } else if (courseDAO.isValidCourse(date, slot, course)) {
                /* check if course is at the same time */
                request.setAttribute("alert", "Class " + course + " is learning at this time . Please book another class");
            } else {
                /* if data is valid */
                String teacherDB = teacherDAO.getTeacherName(teacher);
                int result = timetableDAO.insertTimetable(date, slot, room, teacherDB, course);
                if (result == 1) {
                    request.setAttribute("refresh", "<meta http-equiv=\"refresh\" content=\"2;url=home\" />");
                    request.setAttribute("success", "Add new timetable successfully. Redirect to home in 2 seconds.");
                } else {
                    request.setAttribute("alert", "Insert new timetable failed!");
                }
            }

            request.setAttribute("teacher", teacher);
            request.setAttribute("slot", slot);
            request.setAttribute("room", room);
            request.setAttribute("course", course);
            request.setAttribute("date", date);

            formData(request, response);
            request.getRequestDispatcher("add.jsp").forward(request, response);

        } catch (Exception e) {
            request.setAttribute("error", e.toString());
            request.getRequestDispatcher("add.jsp").forward(request, response);
        }
    }

}
