<%@include file="WEB-INF/jspf/header.jspf"%>
<p class="error">${err}</p>
<h1>Binary To Decimal Converter</h1>
<form id="input" action="convert" method="get">
    <span class="wrapperInput">
        <input 
               id = "bin"
               class="inputElement"
               type="text"
               name="bin"
               value="${bin}"
               placeholder="Your binary number ...">
    </span>
    <div id="solve" class="inputElement" type="submit" name="solve">convert</div>
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
<%@include file="WEB-INF/jspf/footer.jspf"%>
