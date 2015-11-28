<%@include file="WEB-INF/jspf/header.jspf"%>
<div id="convertContainer">

    <div id="complementButtonBox" class="wrapperInput">
        <form action="twos" method="post">
            <c:if test="${fn:startsWith(bin, '-')}">
                <button type="submit" class="complement">to 2's complement</button>
                <input type="hidden" value="bin" name="field">
                <input type="hidden" value="convert.jsp" name="target">
            </c:if>
            <c:if test="${showUndo}">
                <button type="submit" class="complement">to signed number</button>
                <input type="hidden" value="bin" name="field">
                <input type="hidden" value="convert.jsp" name="target">
                <input type="hidden" value="true" name="undo">
            </c:if>
        </form>

    </div>
    <form id="input" action="convert" method="post">

        <span class="wrapperInput">
            <input
                   id = "bin"
                   class="inputElement"
                   type="text"
                   name="bin"
                   value="${bin}"
                   placeholder="Your binary number ...">
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
