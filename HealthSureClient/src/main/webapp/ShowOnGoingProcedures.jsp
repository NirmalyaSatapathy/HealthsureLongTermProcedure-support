<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<f:view>
<html>
<head>
    <meta charset="UTF-8">
    <title>In-Progress Procedures</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f9f9f9;
            color: #333;
            padding: 20px;
        }

        h2 {
            color: #3f51b5;
            font-size: 30px;
            margin: 20px 0;
        }
       .form-group {
            margin-bottom: 20px;
        }

        label {
            font-weight: bold;
        }

        .error {
            color: red;
            font-size: 12px;
            margin-left: 10px;
        }

        .data-table {
            width: 100%;
            margin-top: 30px;
            border-collapse: collapse;
        }

        .data-table th,
        .data-table td {
            padding: 14px;
            text-align: left;
            border: 1px solid #ddd;
        }

        .data-table th {
            background-color: #3f51b5;
            color: #ffffff;
            font-weight: bold;
            white-space: nowrap;
        }

        .data-table td {
            background-color: #fff;
            color: #333;
        }

        .data-table tr:nth-child(even) {
            background-color: #f2f2f2;
        }

        .data-table tr:hover {
            background-color: #ddd;
        }

        @media (max-width: 600px) {
            h2 {
                font-size: 24px;
            }

            .data-table th,
            .data-table td {
                font-size: 14px;
                padding: 8px;
            }
        }
    </style>
</head>
<body>
<h:form prependId="false">
        <h2>Search on-going Procedures</h2>

        <div class="form-group">
            <label><span style="color: red;">*</span>Doctor ID:</label>
            <h:inputText id="doctorId" value="#{procedureController.doctorId}" />
            <br/>
            <h:message for="doctorId" styleClass="error" />
        </div>

        <div class="form-group">
            <label>Procedure ID (optional):</label>
            <h:inputText id="procedureId" value="#{procedureController.procedureId}" />
            <br/>
            <h:message for="procedureId" styleClass="error" />
        </div>

        <div class="form-group">
            <h:commandButton value="Search" action="#{procedureController.fetchInProgressProceduresController()}" />&nbsp;&nbsp;
            
            <h:commandButton value="Reset" action="#{procedureController.resetSearchForm()}" immediate="true" />&nbsp;&nbsp;
            <h:commandButton value="Go to Dashboard" action="#{procedureController.goToDashboard2()}"/>
        </div>

    <h:panelGroup rendered="#{not empty procedureController.allInProgressProcedures}">
        <h:outputText value="Total: #{procedureController.allInProgressProcedures.size()} procedures" style="font-weight: bold;" />

        <h:dataTable value="#{procedureController.allInProgressProcedures}" var="p" styleClass="data-table">
            <h:column>
                <f:facet name="header">
                        <h:commandLink action="#{procedureController.sortByInProgress('appointmentId')}">
                            <span class="sort-header">
                                Appointment ID
                                <h:outputText styleClass="arrow"
                                    value="#{procedureController.sortField eq 'appointmentId' ? (procedureController.sortAscending ? '↑' : '↓') : ''}" />
                            </span>
                        </h:commandLink>
                    </f:facet>
                <h:outputText value="#{p.appointment.appointmentId}" />
            </h:column>

            <h:column>
                <f:facet name="header">
                        <h:commandLink action="#{procedureController.sortByInProgress('procedureId')}">
                            <span class="sort-header">
                                Procedure ID
                                <h:outputText styleClass="arrow"
                                    value="#{procedureController.sortField eq 'procedureId' ? (procedureController.sortAscending ? '↑' : '↓') : ''}" />
                            </span>
                        </h:commandLink>
                    </f:facet>
                <h:outputText value="#{p.procedureId}" />
            </h:column>

            <h:column>
                 <f:facet name="header">
                        <h:commandLink action="#{procedureController.sortByInProgress('recipientFirstName')}">
                            <span class="sort-header">
                                Recipient First Name
                                <h:outputText styleClass="arrow"
                                    value="#{procedureController.sortField eq 'recipientFirstName' ? (procedureController.sortAscending ? '↑' : '↓') : ''}" />
                            </span>
                        </h:commandLink>
                    </f:facet>
                <h:outputText value="#{p.recipient.firstName}" />
            </h:column>

            <h:column>
              <f:facet name="header">
                        <h:commandLink action="#{procedureController.sortByInProgress('recipientLastName')}">
                            <span class="sort-header">
                                Recipient Last Name
                                <h:outputText styleClass="arrow"
                                    value="#{procedureController.sortField eq 'recipientLastName' ? (procedureController.sortAscending ? '↑' : '↓') : ''}" />
                            </span>
                        </h:commandLink>
                    </f:facet>
                <h:outputText value="#{p.recipient.lastName}" />
            </h:column>

            <h:column>
                <f:facet name="header">
                        <h:commandLink action="#{procedureController.sortByInProgress('doctorName')}">
                            <span class="sort-header">
                                Doctor
                                <h:outputText styleClass="arrow"
                                    value="#{procedureController.sortField eq 'doctorName' ? (procedureController.sortAscending ? '↑' : '↓') : ''}" />
                            </span>
                        </h:commandLink>
                    </f:facet>
                <h:outputText value="#{p.doctor.doctorName}" />
            </h:column>

            <h:column>
                <f:facet name="header">
                        <h:commandLink action="#{procedureController.sortByInProgress('providerName')}">
                            <span class="sort-header">
                                Provider
                                <h:outputText styleClass="arrow"
                                    value="#{procedureController.sortField eq 'providerName' ? (procedureController.sortAscending ? '↑' : '↓') : ''}" />
                            </span>
                        </h:commandLink>
                    </f:facet>
                <h:outputText value="#{p.provider.name}" />
            </h:column>

            <h:column>
                <f:facet name="header">
                        <h:commandLink action="#{procedureController.sortByInProgress('startedOn')}">
                            <span class="sort-header">
                                Started on
                                <h:outputText styleClass="arrow"
                                    value="#{procedureController.sortField eq 'startedOn' ? (procedureController.sortAscending ? '↑' : '↓') : ''}" />
                            </span>
                        </h:commandLink>
                    </f:facet>
                <h:outputText value="#{p.fromDate}">
                    <f:convertDateTime pattern="yyyy-MM-dd" />
                </h:outputText>
            </h:column>

            <h:column>
                <f:facet name="header">
                    <h:outputText value="Add Details" />
                </f:facet>
                <h:commandButton value="Add Procedure Details" action="#{procedureController.goToAddProcedureDetails(p)}" />
            </h:column>

            <h:column>
                <f:facet name="header">
                    <h:outputText value="Action" />
                </f:facet>
                <h:commandButton value="Completed" action="#{procedureController.completeProcedure(p)}" />
            </h:column>
        </h:dataTable>
    </h:panelGroup>
</h:form>

</body>
</html>
</f:view>
