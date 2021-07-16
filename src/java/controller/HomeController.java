/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.TimetableDAO;
import entity.Timetable;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author khoi.tranvan
 */
public class HomeController extends HttpServlet {

    public Date beginningDayOfWeek() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);    /*Set the calendar to monday of the current week*/
        return new java.sql.Date(calendar.getTimeInMillis());
    }

    public Date endDayOfWeek() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);    /*Set the calendar to monday of the current week*/
        calendar.add(Calendar.DATE, 6);                         /*Plus 6 days*/
        return new java.sql.Date(calendar.getTimeInMillis());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {

            Date startDate = beginningDayOfWeek();
            Date endDate = endDayOfWeek();
            
            if (request.getParameterMap().containsKey("from") && request.getParameterMap().containsKey("to")) {
                startDate = Date.valueOf(request.getParameter("from"));
                endDate = Date.valueOf(request.getParameter("to"));
            }
            
            TimetableDAO timetableDAO = new TimetableDAO();
            List<Timetable> listTimeTable = timetableDAO.getListTimetableByDate(startDate, endDate);
            
            request.setAttribute("startDate", startDate);
            request.setAttribute("endDate", endDate);
            request.setAttribute("listTimeTable", listTimeTable);
            request.getRequestDispatcher("home.jsp").forward(request, response);
            
        } catch (Exception e) {
            request.setAttribute("error", e.toString());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
}
