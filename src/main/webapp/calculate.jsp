<%@include file="WEB-INF/jspf/header.jspf"%>
<div class="notification">
    ${missingCookies}
</div>
<div id="calcContainer">
    <div class="calculationWrapper">
        <form id="calculationForm" action="calculate" method="post">
            <div class="calculationFieldWrapper">
                <input type="text"
                   placeholder="First binary number"
                   value="${number1}"
                   class="inputElement"
                   id="calculationField"
                   name="binaryNumber"/>
            </div>
            <div class="operationAndField">
                <select class="inputElement"
                        id="operationField"
                        name="operation">
                    <option value="+">&plus;</option>
                    <option value="-">&minus;</option>
                    <option value="*">&times;</option>
                    <option value="/">&divide;</option>
                </select>
                <input type="text"
                       placeholder="Second binary number"
                       value="${number2}"
                       class="inputElement"
                       id="contentField"
                       name="binaryNumber"/>
            </div>
            <input type="submit" value="calculate" class="solveButton" id="solve_calc"/>
        </form>
    </div>
    <div id="resultContainer">
        <h3 id="resultHeading">RESULT</h3>
        <div id="result" class="message">
            ${result}
            ${message}
        </div>
    </div>
</div>
    
<%@include file="WEB-INF/jspf/footer.jspf"%>
