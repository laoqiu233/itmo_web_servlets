<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.Random" %>
<%@ page import="io.dmtri.attemptsmanagers.AttemptsManager" %>
<%@ page import="io.dmtri.models.PointAttempt" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<% AttemptsManager am = (AttemptsManager) request.getAttribute("attemptsManager"); %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/static/css/style.css">
    <script src="<%= request.getContextPath() %>/static/js/graph.js" defer></script>
    <script src="<%= request.getContextPath() %>/static/js/validation.js" defer></script>
    <title>WEBLAB#2</title>
    <script>
        const POINTS = [
            <%
                for (int i=0; i < am.getAttemptsCount(); i++) {
                    Random r = new Random(i);
            %>
                {
                    x: <%= am.getAttempts().get(i).point().x() %>,
                    y: <%= am.getAttempts().get(i).point().y() %>,
                    r: <%= am.getAttempts().get(i).point().r() %>,
                    color: "rgb(<%= r.nextInt(256) %>,<%= r.nextInt(256) %>,<%= r.nextInt(256) %>)"
                },
            <%
                }
            %>
        ]

        const FORM_DATA = ${ formManager.renderJSON() };
    </script>
</head>
<body>
    <header>
        <h1><span class="theme">Tsiu</span> T.</h1>
        <h2>P32312 <span class="gray">Web Lab #2 | Вариант: 6663128</span></h2>
    </header>
    <div class='main'>
        <div class="panel" style="text-align: center;">
            <img src="<%= request.getContextPath() %>/static/img/zxc-cursed.gif" id="loading-gif" width="300" height="300">
            <canvas id="graph-canvas" width="300" height="300"> 
                <img src="/static/img/graph.png"/>
            </canvas>
        </div>
        <div class="panel" id="form-panel">
            <form id="form" action="" method="get"></form>
        </div>
        <div id="results" class="panel">
            <h1>Results</h1>
            <div class="table-wrapper">
                <table>
                    <tbody>
                        <tr>
                            <th>Attempt #</th>
                            <th>X</th>
                            <th>Y</th>
                            <th>R</th>
                            <th>Result</th>
                            <th>Attempt time</th>
                            <th>Processing time</th>
                        </tr>
                        <c:set var="i" value="1"/>
                        <c:forEach items="${attemptsManager.getAttempts()}" var="attempt">
                            <tr>
                                <td>${i}</td>
                                <td>${attempt.point().x()}</td>
                                <td>${attempt.point().y()}</td>
                                <td>${attempt.point().r()}</td>
                                <td class="${attempt.success() ? 'theme' : 'warning'}">${ attempt.success() ? 'HIT' : 'MISS' }</td>
                                <td><%= new Date(((PointAttempt) pageContext.getAttribute("attempt")).attemptTime()) %></td>
                                <td>${attempt.processTime()} ms</td>
                                <c:set var="i" value="${i+1}"/>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</body>
</html>