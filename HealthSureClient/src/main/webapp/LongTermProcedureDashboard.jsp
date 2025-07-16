<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Procedure Action Panel</title>
    <link rel="stylesheet" href="css/healthsure-style.css" />
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #f2f7fb;
            margin: 0;
            padding: 0;
        }

        .nav-container {
            max-width: 700px;
            margin: 100px auto;
            background-color: #ffffff;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 0 12px rgba(0, 0, 0, 0.1);
            text-align: center;
        }

        .nav-title {
            font-size: 26px;
            color: #2c3e50;
            font-weight: bold;
            margin-bottom: 30px;
        }

        .nav-actions {
            margin: 20px 0;
        }

        .nav-button {
            background-color: #007bff;
            color: white;
            border: none;
            padding: 10px 20px;
            border-radius: 6px;
            font-size: 16px;
            margin: 10px;
            display: inline-block;
            cursor: pointer;
        }

        .nav-button:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <f:view>
        <div class="nav-container">
            <div class="nav-title">Procedure Navigation Panel</div>
            
            <h:form>
                <div class="nav-actions">
                    <h:commandButton value="Add Prescription"
                                     action="#{procedureController.createNewPrescription()}"
                                     styleClass="nav-button" />

                    <h:commandButton value="Add Procedure Logs"
                                     action="#{procedureController.createNewProcedureLog()}"
                                     styleClass="nav-button" />

                    <h:commandButton value="Submit Procedure"
                                     action="#{procedureController.procedureSubmit()}"
                                     styleClass="nav-button" />
                </div>
            </h:form>
        </div>
    </f:view>
</body>
</html>
