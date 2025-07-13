<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>

<html>
<head>
    <title>Select Procedure Type</title>
    <style>
        body {
            font-family: 'Segoe UI', sans-serif;
            background-color: #fff8dc; /* Ghee color */
            margin: 0;
            padding: 0;
        }

        .container {
            width: 500px;
            margin: 80px auto;
            background-color: white;
            padding: 30px 40px;
            border-radius: 16px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            text-align: center;
        }

        h2 {
            color: #2e7d32;
            margin-bottom: 30px;
        }

        .radio-group {
            display: flex;
            flex-direction: column;
            align-items: center;
            gap: 15px;
            margin-bottom: 30px;
        }

        .radio-item {
            display: flex;
            align-items: center;
            font-size: 16px;
            color: #333;
        }

        .radio-item input[type="radio"] {
            margin-right: 10px;
        }

        .submit-btn {
            padding: 12px 24px;
            background-color: #4CAF50;
            color: white;
            font-size: 16px;
            border: none;
            border-radius: 8px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        .submit-btn:hover {
            background-color: #388e3c;
        }
    </style>
</head>
<body>
<f:view>
    <h:form>
        <div class="container">
            <h2>Select Procedure Type</h2>
            <div class="radio-group">
                <div class="radio-item">
                    <h:selectOneRadio value="#{procedureController.procedureType}" layout="lineDirection">
                        <f:selectItem itemLabel="Single Day (Completed Today)" itemValue="single" />
                        <f:selectItem itemLabel="Long-Term (Scheduled for Future)" itemValue="scheduled" />
                        <f:selectItem itemLabel="Long-Term (Starting Today)" itemValue="inprogress" />
                    </h:selectOneRadio>
                </div>
            </div>
            <h:commandButton value="Create New Procedure"
                             action="#{procedureController.createNewProcedure()}"
                             styleClass="submit-btn" />
        </div>
    </h:form>
</f:view>
</body>
</html>
