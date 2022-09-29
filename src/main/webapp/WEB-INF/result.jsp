<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.Date" %>
<%@ page import="io.dmtri.models.PointAttempt" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/static/css/style.css">
    <script src="<%= request.getContextPath() %>/static/js/graph.js" defer></script>
    <title>WEBLAB#2</title>
    <script>
        const POINTS = [
            {
                x: ${ attemptToShow.point().x() },
                y: ${ attemptToShow.point().y() },
                r: ${ attemptToShow.point().r() },
                color: '#FFF'
            }
        ];
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
            <canvas id="graph-canvas" width="300" height="300" aria-disabled="true"> 
                <img src="/static/img/graph.png"/>
            </canvas>
        </div>
        <div class="panel">
            <div class="row">
                <c:choose>
                    <c:when test="${ attemptToShow != null }"> 
                        <table>
                            <tbody>
                                <tr>
                                    <td>X</td>
                                    <td>${ attemptToShow.point().x() }</td>
                                </tr>
                                <tr>
                                    <td>Y</td>
                                    <td>${ attemptToShow.point().y() }</td>
                                </tr>
                                <tr>
                                    <td>R</td>
                                    <td>${ attemptToShow.point().r() }</td>
                                </tr>
                                <tr>
                                    <td>Attempt Time</td>
                                    <td><%= new Date(((PointAttempt) request.getAttribute("attemptToShow")).attemptTime()) %></td>
                                </tr>
                                <tr>
                                    <td>Process Time</td>
                                    <td>${ attemptToShow.processTime() } ms</td>
                                </tr>
                                <tr>
                                    <td>Result</td>
                                    <td class="${ attemptToShow.success() ? 'theme' : 'warning' }">${ attemptToShow.success() ? 'HIT' : 'MISS' }</td>
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