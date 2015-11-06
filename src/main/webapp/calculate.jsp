<%@include file="WEB-INF/jspf/header.jspf"%>
<div id="calcContainer">
    <h1>Calculate binary numbers!</h1>
    <h1>${message}</h1>
    <div class="calculationWrapper">
        <form id="calculationForm" action="calculate" method="get">
            <div class="calculationFieldWrapper">
                <input type="text"
                   placeholder="First binary number"
                   class="inputElement"
                   id="calculationField"
                   name="binaryNumber"/>
            </div>
            <div class="operationAndField">
                <select class="inputElement"
                        id="operationField"
                        name="operation">
                    <option>+</option>
                    <option>-</option>
                    <option>*</option>
                    <option>/</option>
                </select>
                <input type="text"
                       placeholder="Second binary number"
                       class="inputElement"
                       id="contentField"
                       name="binaryNumber"/>
            </div>
            <input type="submit" value="calculate" class="solve" id="solve_calc"/>
        </form>
    </div>
    <div id="resultContainer">
        <h3 id="resultHeading">RESULT</h3>
        <div id="result">
            ${result}
        </div>
    </div>
</div>
    
<%@include file="WEB-INF/jspf/footer.jspf"%>
