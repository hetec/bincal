<%@include file="WEB-INF/jspf/header.jspf"%>
    <h1>Calculate binary numbers!</h1>
    <h1>${message}</h1>
    <div class="calculationWrapper">
        <form id="calculationForm" action="calculate" method="get">
            <div class="calculationFieldWrapper">
                <input type="text"
                   placeholder="110101..."
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
                       placeholder="1001..."
                       class="inputElement"
                       id="contentField"
                       name="binaryNumber"/>
            </div>
            <input type="submit" value="calc"/>
            
        </form>
        <h1>Res: ${result}</h1>
    </div>
    
    
<%@include file="WEB-INF/jspf/footer.jspf"%>
