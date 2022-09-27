<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.Date" %>
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
            <form id="form" action="" method="get">    
                <div class="row">
                        <label for="input-x">X</label>
                        <div>
                            <input type="text" name="x" id="input-x" placeholder="0"/>
                            <p id="input-x-warning" class="warning">fuck</p>
                        </div>
                </div>
                <div class="row">
                    <label for="select-y">Y</label>
                    <select name="y" id="select-y">
                        <option value="-2">-2</option>
                        <option value="-1.5">-1.5</option>
                        <option value="-1">-1</option>
                        <option value="-0.5">-0.5</option>
                        <option value="0">0</option>
                        <option value="0.5">0.5</option>
                        <option value="1">1</option>
                        <option value="1.5">1.5</option>
                        <option value="2">2</option>
                    </select>
                </div>
                <div class="row">
                    <label>R</label>
                    <div>
                        <div>
                            <div class="row">
                                <input type="radio" name="r" id="r-1" value="1"/>
                                <label for="r-1">1</label>
                            </div>
                            <div class="row">
                                <input type="radio" name="r" id="r-2" value="2"/>
                                <label for="r-2">2</label>
                            </div>
                            <div class="row">
                                <input type="radio" name="r" id="r-3" value="3"/>
                                <label for="r-3">3</label>
                            </div>
                            <div class="row">
                                <input type="radio" name="r" id="r-4" value="4"/>
                                <label for="r-4">4</label>
                            </div>
                            <div class="row">
                                <input type="radio" name="r" id="r-5" value="5"/>
                                <label for="r-5">5</label>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <button id="form-submit" type="submit" class="row-fill">Send</button>
                </div>
            </form>
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