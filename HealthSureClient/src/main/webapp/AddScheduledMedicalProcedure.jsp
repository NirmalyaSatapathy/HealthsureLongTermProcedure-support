<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<html>
<head>
    <title>Scheduled Medical Procedure</title>
    <style>
        /* Same CSS as you provided before */
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background: linear-gradient(to right, #e0f7fa, #f1f8e9);
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

        .form-grid {
            display: grid;
            grid-template-columns: 1fr 1fr;
            gap: 18px 30px;
        }

        .form-group {
            display: flex;
            flex-direction: column;
        }

        label {
            font-weight: 600;
            color: #2c3e50;
            margin-bottom: 5px;
            font-size: 14px;
        }

        input[type="text"], textarea {
            padding: 8px 10px;
            font-size: 13px;
            border: 1px solid #ccc;
            border-radius: 6px;
            width: 100%;
            box-sizing: border-box;
        }

        textarea {
            resize: vertical;
            min-height: 60px;
        }

        .full-width {
            grid-column: 1 / 3;
        }

        .button-group {
            display: flex;
            justify-content: center;
            gap: 20px;
            margin-top: 25px;
        }

        .green-button {
            background-color: #43a047;
            color: white;
            padding: 10px 20px;
            font-size: 13px;
            font-weight: bold;
            border: none;
            border-radius: 6px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        .green-button:hover {
            background-color: #388e3c;
        }

        .blue-button {
            background-color: #007bff;
            color: white;
            padding: 10px 20px;
            font-size: 13px;
            font-weight: bold;
            border: none;
            border-radius: 6px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        .blue-button:hover {
            background-color: #0056b3;
        }

        .error-message {
            color: #d32f2f;
            font-size: 12px;
            margin-top: 3px;
        }
    </style>
</head>
<body>
<f:view>
    <div class="container">
        <h:form prependId="false">
            <h2>Scheduled Medical Procedure</h2>

            <div class="form-grid">
                <div class="form-group">
                    <h:outputLabel for="procedureId" value="Procedure ID:" />
                    <h:inputText id="procedureId" value="#{procedureController.procedure.procedureId}" readonly="true" />
                </div>

                <div class="form-group">
                    <h:outputLabel for="appointmentId">
                        Appointment ID: <span style="color:red">*</span>
                    </h:outputLabel>
                    <h:inputText id="appointmentId" value="#{procedureController.procedure.appointment.appointmentId}" required="true" />
                    <h:message for="appointmentId" styleClass="error-message" />
                </div>

                <div class="form-group">
                    <h:outputLabel for="recipientId">
                        Patient (h_id): <span style="color:red">*</span>
                    </h:outputLabel>
                    <h:inputText id="recipientId" value="#{procedureController.procedure.recipient.hId}" required="true" />
                    <h:message for="recipientId" styleClass="error-message" />
                </div>

                <div class="form-group">
                    <h:outputLabel for="providerId">
                        Provider ID: <span style="color:red">*</span>
                    </h:outputLabel>
                    <h:inputText id="providerId" value="#{procedureController.procedure.provider.providerId}" required="true" />
                    <h:message for="providerId" styleClass="error-message" />
                </div>

                <div class="form-group">
                    <h:outputLabel for="doctorId">
                        Doctor ID: <span style="color:red">*</span>
                    </h:outputLabel>
                    <h:inputText id="doctorId" value="#{procedureController.procedure.doctor.doctorId}" required="true" />
                    <h:message for="doctorId" styleClass="error-message" />
                </div>

                <div class="form-group">
                    <h:outputLabel for="scheduledDate">
                        Scheduled Date: <span style="color:red">*</span>
                    </h:outputLabel>
                    <h:inputText id="scheduledDate" value="#{procedureController.procedure.scheduledDate}" required="true">
                        <f:convertDateTime pattern="yyyy-MM-dd" />
                    </h:inputText>
                    <h:message for="scheduledDate" styleClass="error-message" />
                </div>

                <div class="form-group full-width">
                    <h:outputLabel for="diagnosis">
                        Diagnosis: <span style="color:red">*</span>
                    </h:outputLabel>
                    <h:inputTextarea id="diagnosis" value="#{procedureController.procedure.diagnosis}" required="true" />
                    <h:message for="diagnosis" styleClass="error-message" />
                </div>

                <div class="form-group full-width">
                    <h:outputLabel for="recommendations" value="Recommendations:" />
                    <h:inputTextarea id="recommendations" value="#{procedureController.procedure.recommendations}" />
                    <h:message for="recommendations" styleClass="error-message" />
                </div>
            </div>

            <div class="button-group">
                <h:commandButton value="Add Scheduled Procedure"
                                 styleClass="green-button"
                                 action="#{procedureController.addScheduledMedicalProcedureController(procedureController.procedure)}" />

                <h:commandButton value="Reset Form"
                                 styleClass="blue-button"
                                 action="#{procedureController.createNewProcedure()}"
                                  />
            </div>
        </h:form>
    </div>
</f:view>
</body>
</html>
