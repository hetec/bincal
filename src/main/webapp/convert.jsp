<%@include file="WEB-INF/jspf/header.jspf"%>
<div id="convertContainer">
    <form id="input" action="convert" method="get">
        <span class="wrapperInput">
            <input
                   id = "bin"
                   class="inputElement"
                   type="text"
                   name="bin"
                   value="${bin}"
                   placeholder="Your binary number ...">
            <input type="checkbox" value="true" name="twos"/>
        </span>
        <button id="solve" class="solveButton" class="inputElement" type="submit" name="solve">convert</button>
        <span class="wrapperOutput">
            <input
                   id = "dec"
                   class="inputElement"
                   type="text"
                   name="dec"
                   value="${dec}"
                   placeholder="Your decimal number ...">
        </span>
        <input id="last" type="hidden" name="last">
    </form>
    <div class="message" id="conversionError">
        ${err}
    </div>
</div>
<script src="resources/js/submitActions.js" rel="javascript"></script>
<%@include file="WEB-INF/jspf/footer.jspf"%>
