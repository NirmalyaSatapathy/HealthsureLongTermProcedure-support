<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>

<html>
<head>
    <title>Select Procedure Type</title>
</head>
<body>
    <f:view>
        <h:form>
            <h:panelGrid columns="1" cellpadding="5">
                <h:outputText value="Select Procedure Type:" />

               <h:selectOneRadio value="#{procedureController.procedureType}">
    <f:selectItem itemLabel="Single Day (Completed Today)" itemValue="single" />
    <f:selectItem itemLabel="Long-Term (Scheduled for Future)" itemValue="scheduled" />
    <f:selectItem itemLabel="Long-Term (Starting Today)" itemValue="inprogress" />
</h:selectOneRadio>
                <h:commandButton value="Create New Procedure"
                                 action="#{procedureController.createNewProcedure()}" />
            </h:panelGrid>
        </h:form>
    </f:view>
</body>
</html>
