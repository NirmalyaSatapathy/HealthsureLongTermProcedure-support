<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Procedure Log</title>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background: linear-gradient(to right, #fce4ec, #e1f5fe);
            margin: 0;
            padding: 0;
        }

        .container {
            max-width: 850px;
            margin: 40px auto;
            background-color: #ffffff;
            border-radius: 10px;
            box-shadow: 0 4px 16px rgba(0, 0, 0, 0.08);
            padding: 25px 30px;
        }

        h2 {
            text-align: center;
            color: #00796b;
            font-size: 22px;
            margin-bottom: 20px;
        }

        .form-group {
            margin-bottom: 18px;
        }

        label, h\:outputLabel {
            font-weight: 600;
            color: #2c3e50;
            margin-bottom: 5px;
            font-size: 14px;
            display: block;
        }

        input[type="text"], textarea {
            padding: 8px 10px;
            font-size: 13px;
            border: 1px solid #ccc;
            border-radius: 6px;
            width: 100%;
            box-sizing: border-box;
        }

        .error-message {
            color: red;
            font-size: 12px;
            margin-top: 2px;
        }

        .button-group {
            display: flex;
            justify-content: center;
            gap: 20px;
            margin-top: 25px;
        }

        .custom-button {
            background-color: #00796b;
            color: white;
            padding: 10px 20px;
            font-size: 13px;
            font-weight: bold;
            border: none;
            border-radius: 6px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        .custom-button:hover {
            background-color: #004d40;
        }

        .blue-button {
            background-color: #0288d1;
        }

        .blue-button:hover {
            background-color: #0277bd;
        }
    </style>
</head>
<body>
<f:view>
    <div class="container">
        <h:form prependId="false">
            <h2>Add Daily Procedure Log</h2>

            <!-- Procedure ID (readonly from session) -->
            <div class="form-group">
                <h:outputLabel for="procedureId" value="Procedure ID:" />
                <h:inputText id="procedureId" value="#{procedureController.procedure.procedureId}" readonly="true" />
            </div>
            <div class="form-group">
                <h:outputLabel for="logId" value="Log ID:" />
                <h:inputText id="logId" value="#{procedureController.procedureLog.logId}" readonly="true" />
            </div>

            <!-- Log Date (readonly or auto-filled) -->
            <div class="form-group">
                <h:outputLabel for="logDate">
                    Log Date (yyyy-MM-dd): <span style="color:red">*</span>
                </h:outputLabel>
                <h:inputText id="logDate" value="#{procedureController.procedureLog.logDate}">
                    <f:convertDateTime pattern="yyyy-MM-dd" />
                </h:inputText>
                <h:message for="logDate" styleClass="error-message" />
            </div>
				<script>
    const calendarInput = document.querySelector("#logDate");
    if (calendarInput) {
        calendarInput.setAttribute("type", "date");
        const today = new Date().toISOString().split("T")[0];
        calendarInput.setAttribute("min", today);
    }
</script>
            <!-- Vitals -->
            <div class="form-group">
                <h:outputLabel for="vitals">Vitals (optional):</h:outputLabel>
                <h:inputText id="vitals" value="#{procedureController.procedureLog.vitals}" />
                <h:message for="vitals" styleClass="error-message" />
            </div>

            <!-- Notes -->
            <div class="form-group">
                <h:outputLabel for="notes">
                    Notes: <span style="color:red">*</span>
                </h:outputLabel>
                <h:inputTextarea id="notes" value="#{procedureController.procedureLog.notes}" rows="4" cols="50" />
                <h:message for="notes" styleClass="error-message" />
            </div>

            <!-- Submit + Reset Buttons -->
            <div class="button-group">
                <h:commandButton value="Save Log"
                                 styleClass="custom-button"
                                 action="#{procedureController.addProcedureLogController(procedureController.procedureLog)}" />

                <h:commandButton value="Reset Form"
                                 styleClass="custom-button blue-button"
                                 action="#{procedureController.createNewProcedureLog()}"
                                 immediate="true" />
            </div>
        </h:form>
    </div>
</f:view>
</body>
</html>
