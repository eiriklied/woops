<%@ include file="/WEB-INF/jsp/layout/header.jspf"%>

<div class="header">
    <h1>Sign in</h1>
    <h2>And start transferring :)</h2>
</div>

<div class="content">
    <form:form action="/sessions" method="post" class="pure-form pure-form-stacked">
        <label for="phone">Phone</label>
        <input id="phone" name="phone" type="text" maxlength="8" autofocus>
        <label for="password">Password</label>
        <input id="password" name="password" type="password">
        <input type="submit" value="Sign in" class="pure-button pure-button-primary">
    </form:form>
</div>

<%@ include file="/WEB-INF/jsp/layout/footer.jspf"%>