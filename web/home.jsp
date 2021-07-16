<%-- 
    Document   : home
    Created on : Jul 9, 2021, 9:31:52 AM
    Author     : khoi.tranvan
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        
        <title>JSP Page</title>
        <link href="css/home.css" rel="stylesheet" type="text/css" />
    </head>
    <body>
        <div>
            <h2 class="blue"><img src="images/table.png" class="img"/> Timetable </h2>

        </div>
        <form action="home" method="get">
            <table>
                <thead>
                    <tr class="above">
                <div class="from">
                    <h4>From Date</h4>
                    <input type="date" name="from" value="${startDate}" />
                </div>
                <div class="to">
                    <h4>To Date</h4>
                    <input type="date" name="to" value="${endDate}" />
                    <input class="button" type="submit" value="Search" name="Search" />

                </div>
                <a href="add"><img src="images/clock--v2.png" width="20px" height="20px"/>Add timetable</a>
                </tr>

                <tr>
                    <th><img src="images/table.png" width="20px" height="20px"/>Date</th>
                    <th><img src="images/clock--v2.png" width="20px" height="20px"/>Slot</th>
                    <th>Time</th>
                    <th>Class</th>
                    <th>Teacher</th>
                    <th>Room</th>
                </tr>

                </thead>
                <p>${result}<p>
                <tbody>
                    <c:forEach items="${listTimeTable}" var="o">
                        <tr>
                            <td>${o.date}</td>
                            <td>${o.slot}</td>
                            <td>${o.getSlotDetail()}</td>
                            <td class="course" >${o.classname}</td>
                            <td>${o.teacher}</td>
                            <td>${o.room}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>

        </form>
    </body>
</html>
