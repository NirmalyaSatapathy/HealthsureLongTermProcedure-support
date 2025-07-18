<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Prescribed Medicines Dashboard</title>
    <link rel="stylesheet" href="css/healthsure-style.css" />
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #eef5f9;
            margin: 0;
            padding: 0;
        }

        .dashboard-container {
            max-width: 600px;
            margin: 80px auto;
            padding: 30px;
            background-color: #ffffff;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
            text-align: center;
        }

        .dashboard-title {
            font-size: 24px;
            font-weight: bold;
            color: #2a3f54;
            margin-bottom: 30px;
        }

        .action-button, .action-link {
            background-color: #007bff;
            color: white;
            border: none;
            padding: 10px 25px;
            border-radius: 6px;
            text-decoration: none;
            font-size: 16px;
            margin: 10px;
            display: inline-block;
            cursor: pointer;
        }

        .action-link:hover, .action-button:hover {
            background-color: #0056b3;
        }

        .spacer {
            height: 20px;
        }
    </style>
</head>
<body>
    <f:view>
        <div class="dashboard-container">
            <div class="dashboard-title">Prescription Dashboard</div>
            <h:form>
            	
                <h:commandButton value="Add Medicine" action="#{procedureController.createNewPrescribedMedicine()}" />
                
                <div class="spacer"></div>
                
                <h:commandButton value="Add Test" action="#{procedureController.createNewProcedureTest()}"
                                 styleClass="action-button" />
                  <div class="spacer"></div>
                <h:commandButton value="submit" action="#{procedureController.prescriptionDetailsSubmit()}" 
                                 styleClass="action-button" />
            </h:form>
        </div>
    </f:view>
</body>
</html>
