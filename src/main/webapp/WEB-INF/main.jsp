<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="style.css">
    <title>WEBLAB#2</title>
</head>
<body>
    <header>
        <h1><span class="theme">Tsiu</span> T.</h1>
        <h2>P3132 <span class="gray">Web Lab #1 | Вариант: 3218</span></h2>
    </header>
    <div class='main'>
        <div class="panel" style="text-align: center;">
            <canvas width="300" height="300"> 
                <img src="graph.png"/>
            </canvas>
        </div>
        <div class="panel" id="form-panel">
            <form id="htmlForm" action="/send_point.php" method="post">    
                <div class="row">
                    <label htmlFor="select-x">X</label>
                    <select name="x" id="select-x">
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
                    <label htmlFor="input-y">Y</label>
                    <div>
                        <input type="text" name="y" id="input-y" placeholder="0"/>
                        <p id="input-y-warning" class="warning">fuck</p>
                    </div>
                </div>
                <div class="row">
                    <label>R</label>
                    <div>
                        <div>
                            <div class="row">
                                <input type="radio" name="r" id="r-1" value="1"/>
                                <label htmlFor="r-1">1</label>
                            </div>
                            <div class="row">
                                <input type="radio" name="r" id="r-2" value="2"/>
                                <label htmlFor="r-2">2</label>
                            </div>
                            <div class="row">
                                <input type="radio" name="r" id="r-3" value="3"/>
                                <label htmlFor="r-3">3</label>
                            </div>
                            <div class="row">
                                <input type="radio" name="r" id="r-4" value="4"/>
                                <label htmlFor="r-4">4</label>
                            </div>
                            <div class="row">
                                <input type="radio" name="r" id="r-5" value="5"/>
                                <label htmlFor="r-5">5</label>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <button id="htmlForm-submit" type="submit" class="row-fill">Send</button>
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
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</body>
</html>