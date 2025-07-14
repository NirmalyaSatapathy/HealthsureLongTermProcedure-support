<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core" %>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html" %>

<f:view>
<html>
<head>
<meta charset="UTF-8">
<title>Provider Dashboard</title>
<style>
    body {
        font-family: Arial, sans-serif;
        background-color: #fff8dc; /* Ghee yellow */
        margin: 0;
        padding: 0;
    }

    .dashboard-container {
        width: 60%;
        margin: 60px auto;
        background-color: #ffffff;
        border-radius: 12px;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        padding: 30px 50px;
    }

    h2 {
        color: #004d40;
        text-align: center;
        margin-bottom: 40px;
    }

    .button-row {
        margin: 15px 0;
        text-align: center;
    }

    .btn-blue {
        background-color: #2196F3;
        color: white;
    }

    .btn-green {
        background-color: #4CAF50;
        color: white;
    }

    .btn-orange {
        background-color: #FF9800;
        color: white;
    }

    .btn-purple {
        background-color: #9C27B0;
        color: white;
    }

    .dashboard-button {
        font-size: 16px;
        padding: 12px 30px;
        border: none;
        border-radius: 8px;
        cursor: pointer;
        transition: background-color 0.3s ease;
    }

    .dashboard-button:hover {
        opacity: 0.85;
    }
</style>
</head>
<body>
    <h:form>
        <div class="dashboard-container">
            <h2>Welcome to Provider's Patient Dashboard</h2>

            <div class="button-row">
                <h:commandButton value="Add New Procedure"
                                 action="ProcedureOptions"
                                 styleClass="dashboard-button btn-blue" />
            </div>

            <div class="button-row">
                <h:commandButton value="Show Scheduled Procedures"
                                 action="ShowScheduledProcedures"
                                 styleClass="dashboard-button btn-green" />
            </div>

            <div class="button-row">
                <h:commandButton value="Show Ongoing Procedures"
                                 action="ShowOnGoingProcedures"
                                 styleClass="dashboard-button btn-orange" />
            </div>

            <div class="button-row">
                <h:commandButton value="Show Patient Insurance Details"
                                 action="showInsuranceDetails"
                                 styleClass="dashboard-button btn-purple" />
            </div>
        </div>
    </h:form>
</body>
</html>
</f:view>
