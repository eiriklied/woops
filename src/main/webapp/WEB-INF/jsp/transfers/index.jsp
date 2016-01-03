<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/jsp/layout/header.jspf"%>

<div class="header">
    <h1>Woops</h1>
    <h2>Social transfers</h2>
</div>

<div class="content">
    <c:choose>
        <c:when test="${empty currentUser}">
            <h1 class="align-center">Please <a href="/sessions/new">sign in</a></h1>
        </c:when>
        <c:when test="${not empty transfers}">
            <div class="transfers push-1">
                <c:forEach items="${transfers}" var="transfer">
                    <div class="transfer">
                        <h1>${transfer.amount} NOK</h1>
                        <p>${transfer.comment}</p>
                        <p class="align-right">From <strong>${transfer.from.name}</strong> to <strong>${transfer.to.name}</strong></p>
                    </div>
                </c:forEach>
            </div>
        </c:when>
        <c:otherwise>
            <h1>No transfers yet!</h1>
        </c:otherwise>
    </c:choose>
</div>

<%@ include file="/WEB-INF/jsp/layout/footer.jspf"%>