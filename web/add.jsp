<%-- 
    Document   : add
    Created on : Jul 9, 2021, 9:31:58 AM
    Author     : khoi.tranvan
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        ${refresh}
        <title>JSP Page</title>
        <link href="css/add.css" rel="stylesheet" type="text/css" />
    </head>
    <body>
        <div>
            <h2 class="blue"><img src="images/table.png" class="img"/> Timetable </h2>

        </div>
        <form action="add" method="POST">
            <h4 class="danger">${alert}</h4>
                    <h4 class="success">${success}</h4>
            <table>
                <tr>
                    <td>Date</td>
                    <td><input type="date" name="date" min="${currentDate}" value="${date!=null?date:currentDate}" /></td>
                </tr>
                <tr>
                    <td>Slot</td>
                    <td>
                        <select name="slot">
                            <c:forEach items="${listSlot}" var="o">
                                <option ${o.slotNo == slot ?"selected":""}>${o.slotNo}</option>
                            </c:forEach>
                        </select></td>
                </tr>
                <tr>
                    <td>Room</td>
                    <td>
                        <select name="room">
                            <c:forEach items="${listRoom}" var="o">
                                <option ${o.name == room ? "selected":""}>${o.name}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>Teacher</td>
                    <td><input type="text" name="teacher" value="${teacher}" required=""/></td>
                </tr>
                <tr>
                    <td>Class/Course</td>
                    <td>
                        <select name="course">
                            <c:forEach items="${listCourse}" var="o">
                                <option ${o.name == course ? "selected" : ""}>${o.name}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td></td>
                    <td>
                        <input class="btn" type="submit" value="Save"/>
                    </td> 
                </tr>

            </table>
                    
        </form>
    </body>
</html>
