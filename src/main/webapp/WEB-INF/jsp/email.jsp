<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
    <link rel="stylesheet" type="text/css" media="screen" href='<c:url value="/assets/css/style.css"/>'/>

    <title>E-mail</title>
</head>

<body>
<div>
    <form:form modelAttribute="emailMessage" action="/send">
        <fieldset>
            <legend>E-mail</legend>
            
            <form:label path="toName" for="to">To Name:</form:label>
            <form:input id="toName" path="toName"/>

            <form:label path="toAddress" for="to">To Address:</form:label>
            <form:input id="toAddress" path="toAddress"/>

            <form:label path="fromName" for="to">From Name:</form:label>
            <form:input id="fromName" path="fromName"/>

            <form:label path="fromAddress" for="to">From Address:</form:label>
            <form:input id="fromAddress" path="fromAddress"/>

            <form:label path="subject" for="to">Subject:</form:label>
            <form:input id="subject" path="subject"/>

            <form:label path="body" for="to">Message:</form:label>
            <form:textarea id="body" path="body"/>

            <form:button type="submit" id="submit" value="Send">Send</form:button>
        </fieldset>
    </form:form>

</div>
    <spring:message text="${status.message}"/>
<div>

</div>

<div id='msgbox' title='' style='display:none'></div>
</body>
</html>