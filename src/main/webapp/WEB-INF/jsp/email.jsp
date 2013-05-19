<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<link rel="stylesheet" type="text/css" media="screen" href='<c:url value="/assets/css/bootstrap.css"/>'/>
<link rel="stylesheet" type="text/css" media="screen" href='<c:url value="/assets/css/style.css"/>'/>

<title>E-mail</title>
</head>

<body>
<div class="container">
    <div class="row-fluid">
        <div class="well">
            <form:form modelAttribute="emailMessage" action="/send" cssClass="form-horizontal">
                <fieldset>
                    <legend>E-mail</legend>

                    <div class="control-group">
                        <form:label path="toName" for="to" cssClass="control-label">To Name:</form:label>
                        <div class="controls">
                            <form:input id="toName" path="toName"/>
                        </div>
                    </div>

                    <div class="control-group">
                        <form:label path="toAddress" for="to" cssClass="control-label">To Address:</form:label>
                        <div class="controls">
                            <form:input id="toAddress" path="toAddress"/>
                        </div>
                    </div>

                    <div class="control-group">
                        <form:label path="fromName" for="to" cssClass="control-label">From Name:</form:label>
                        <div class="controls">
                            <form:input id="fromName" path="fromName"/>
                        </div>
                    </div>

                    <div class="control-group">
                        <form:label path="fromAddress" for="to" cssClass="control-label">From Address:</form:label>
                        <div class="controls">
                            <form:input id="fromAddress" path="fromAddress"/>
                        </div>
                    </div>

                    <div class="control-group">
                        <form:label path="subject" for="to" cssClass="control-label">Subject:</form:label>
                        <div class="controls">
                            <form:input id="subject" path="subject"/>
                        </div>
                    </div>

                    <div class="control-group">
                        <form:label path="body" for="to" cssClass="control-label">Message:</form:label>
                        <div class="controls">
                            <form:textarea id="body" path="body"/>
                        </div>
                    </div>

                    <div class="control-group">
                        <form:button type="submit" id="submit" value="Send">Send</form:button>
                    </div>
                </fieldset>
            </form:form>

        </div>

        <div class="row-fluid">
            <p class="text-info"><spring:message text="${status.message}"/></p>
        </div>

    </div>
</div>

<div id='msgbox' title='' style='display:none'></div>
</body>
</html>