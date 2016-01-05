<%@ include file="/WEB-INF/jsp/layout/header.jspf"%>

<div class="header">
    <h1>Make a transfer</h1>
    <h2>&nbsp;</h2>
</div>

<div class="content">
    <form:form action="/transfers" method="post" class="pure-form pure-form-stacked">

        <label for="amount">Amount (NOK)</label>
        <input id="amount" name="amount" type="number" autofocus>
        <label for="to">Receivers phone</label>
        <input id="to" type="tel" name="to" maxlength="8">
        <label for="comment">Comment</label>
        <textarea id="comment" name="comment"></textarea>
        <input type="submit" value="Transfer!" class="pure-button pure-button-primary">
    </form:form>
</div>

<%@ include file="/WEB-INF/jsp/layout/footer.jspf"%>