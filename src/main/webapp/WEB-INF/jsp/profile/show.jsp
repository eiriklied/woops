<%@ include file="/WEB-INF/jsp/layout/header.jspf"%>

<div class="header">
    <h1>Your profile</h1>
    <h2>&nbsp;</h2>
</div>

<div class="content">
    <form:form action="/profile" method="post" class="pure-form pure-form-stacked">
        <label for="name">Name</label>
        <input id="name" name="name" type="text" autofocus value="${currentUser.name}">
        <input type="submit" value="Save" class="pure-button pure-button-primary">
    </form:form>
</div>

<%@ include file="/WEB-INF/jsp/layout/footer.jspf"%>