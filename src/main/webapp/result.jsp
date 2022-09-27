<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.Date" %>
<%@ page import="io.dmtri.models.PointAttempt" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<% PointAttempt lastAttempt = (PointAttempt) request.getAttribute("lastAttempt"); %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/static/css/style.css">
    <script src="<%= request.getContextPath() %>/static/js/graph.js" defer></script>
    <title>WEBLAB#2</title>
</head>
<body>
    <header>
        <h1><span class="theme">Tsiu</span> T.</h1>
        <h2>P3132 <span class="gray">Web Lab #1 | Вариант: 3218</span></h2>
    </header>
    <div class='main'>
        <div class="panel" style="text-align: center;">
            <img src="<%= request.getContextPath() %>/static/img/zxc-cursed.gif" id="loading-gif" width="300" height="300">
            <canvas id="graph-canvas" width="300" height="300"> 
                <img src="/static/img/graph.png"/>
            </canvas>
        </div>
        <div class="panel" id="form-panel">
            <div class="row">
                <c:choose>
                    <c:when test="${ lastAttempt != null }"> 
                        <table>
                            <tbody>
                                <tr>
                                    <td>X</td>
                                    <td>${ lastAttempt.point().x() }</td>
                                </tr>
                                <tr>
                                    <td>Y</td>
                                    <td>${ lastAttempt.point().y() }</td>
                                </tr>
                                <tr>
                                    <td>R</td>
                                    <td>${ lastAttempt.point().r() }</td>
                                </tr>
                                <tr>
                                    <td>Attempt Time</td>
                                    <td><%= new Date(lastAttempt.attemptTime()) %></td>
                                </tr>
                                <tr>
                                    <td>Process Time</td>
                                    <td>${ lastAttempt.processTime() } ms</td>
                                </tr>
                                <tr>
                                    <td>Result</td>
                                    <td class="${ attempt.success() ? 'theme' : 'warning' }">${ attempt.success() ? 'HIT' : 'MISS' }</td>
                                </tr>
                            </tbody>
                        </table>
                    </c:when>
                    <c:otherwise>
                        Not available
                    </c:otherwise>
                </c:choose>
            </div>
            <div class="row">
                <a href="<%= request.getContextPath() %>/" class="row-fill"><button style="width:100%">Return</button></a>
            </div>
        </div>
    </div>
</body>
</html>