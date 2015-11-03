<%@include file="WEB-INF/jspf/header.jspf"%>
    <h1>Calculate binary numbers!</h1>
    
    <div class="calculationWrapper">
        <form id="calculationForm">
            <div class="calculationFieldWrapper">
                <input type="text"
                   placeholder="110101..."
                   class="inputElement"
                   id="calculationField"/>
            </div>
            <div class="operationAndField">
                <select class="inputElement"
                        id="operationField">
                    <option>+</option>
                    <option>-</option>
                    <option>*</option>
                    <option>/</option>
                </select>
                <input type="text"
                       placeholder="1001..."
                       class="inputElement"
                       id="contentField"/>
            </div>
                
            
        </form>
        
    </div>
    
    
<%@include file="WEB-INF/jspf/footer.jspf"%>
