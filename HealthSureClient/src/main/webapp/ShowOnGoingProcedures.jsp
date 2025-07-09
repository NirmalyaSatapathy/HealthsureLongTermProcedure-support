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

        .data-table {
            width: 100%;
            margin-top: 30px;
            border-collapse: collapse;
        }

        .data-table th,
        .data-table td {
            padding: 12px;
            text-align: left;
            border: 1px solid #ddd;
        }

        .data-table th {
            background-color: #3f51b5;
            color: white;
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

        center {
            margin-bottom: 30px;
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

<h2>In-Progress Procedures</h2>

<h:form>
    <h:dataTable value="#{procedureController.getInProgressProcedures()}" var="p" styleClass="data-table">

        <h:column>
            <f:facet name="header">
                <h:outputText value="Appointment ID" />
            </f:facet>
            <h:outputText value="#{p.appointment.appointmentId}" />
        </h:column>

        <h:column>
            <f:facet name="header">
                <h:outputText value="Procedure ID" />
            </f:facet>
            <h:outputText value="#{p.procedureId}" />
        </h:column>

        <h:column>
            <f:facet name="header">
                <h:outputText value="Recipient First Name" />
            </f:facet>
            <h:outputText value="#{p.recipient.firstName}" />
        </h:column>

        <h:column>
            <f:facet name="header">
                <h:outputText value="Recipient Last Name" />
            </f:facet>
            <h:outputText value="#{p.recipient.lastName}" />
        </h:column>

        <h:column>
            <f:facet name="header">
                <h:outputText value="Doctor" />
            </f:facet>
            <h:outputText value="#{p.doctor.doctorName}" />
        </h:column>

        <h:column>
            <f:facet name="header">
                <h:outputText value="Provider" />
            </f:facet>
            <h:outputText value="#{p.provider.name}" />
        </h:column>

        <h:column>
            <f:facet name="header">
                <h:outputText value="Started On" />
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
</h:form>

</body>
</html>
</f:view>
