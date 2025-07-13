<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>

<f:view>
<html>
<head>
    <title>Add Prescribed Medicine</title>
    <link rel="stylesheet" href="css/healthsure-style.css" />
    <style>
    .form-container {
        max-width: 600px;
        margin: 40px auto;
        padding: 30px;
        background-color: #f4f8fb;
        border-radius: 10px;
        box-shadow: 0px 0px 10px #ccc;
    }
    .form-group {
        margin-bottom: 15px;
    }
    label {
        font-weight: bold;
        display: block;
        margin-bottom: 5px;
    }
    .form-control {
        width: 100%;
        padding: 8px;
        border-radius: 5px;
        border: 1px solid #ccc;
    }
    .form-title {
        text-align: center;
        font-size: 22px;
        margin-bottom: 25px;
        color: #2a3f54;
    }
    .error {
        color: red;
        font-size: 12px;
        margin-top: 4px;
        display: block;
    }

    .button-group {
        display: flex;
        justify-content: center;
        gap: 20px;
        margin-top: 20px;
    }

    .btn-add {
        background-color: #28a745; /* Green */
        color: white;
        padding: 10px 20px;
        border: none;
        border-radius: 6px;
        cursor: pointer;
    }

    .btn-add:hover {
        background-color: #218838;
    }

    .btn-reset {
        background-color: #007bff; /* Blue */
        color: white;
        padding: 10px 20px;
        border: none;
        border-radius: 6px;
        cursor: pointer;
    }

    .btn-reset:hover {
        background-color: #0056b3;
    }
</style>
</head>
<body>
    <div class="form-container">
        <div class="form-title">Add Prescribed Medicine</div>

        <h:form prependId="false">

            <!-- Global error messages -->
            <h:messages globalOnly="true" styleClass="error" />

            <div class="form-group">
                <label for="prescribedId">Prescribed ID</label>
                <h:inputText id="prescribedId" value="#{procedureController.prescribedMedicine.prescribedId}" 
                             readonly="true" styleClass="form-control"/>
            </div>

            <div class="form-group">
                <label for="prescriptionId">Prescription ID</label>
                <h:inputText id="prescriptionId" value="#{procedureController.prescription.prescriptionId}" 
                             readonly="true" styleClass="form-control"/>
                <h:message for="prescriptionId" styleClass="error"/>
            </div>

            <div class="form-group">
                <label for="medicineName">Medicine Name <span style="color:red">*</span></label>
                <h:inputText id="medicineName" value="#{procedureController.prescribedMedicine.medicineName}" 
                             required="true" styleClass="form-control"/>
                <h:message for="medicineName" styleClass="error"/>
            </div>

            <div class="form-group">
                <label for="type">Medicine Type <span style="color:red">*</span></label>
                <h:selectOneMenu id="type" value="#{procedureController.prescribedMedicine.type}" 
                                 styleClass="form-control" required="true">
                    <f:selectItem itemLabel="-- Select Type --" itemValue="" />
                    <f:selectItem itemLabel="Tablet" itemValue="TABLET" />
                    <f:selectItem itemLabel="Syrup" itemValue="SYRUP" />
                    <f:selectItem itemLabel="Injection" itemValue="INJECTION" />
                    <f:selectItem itemLabel="Drop" itemValue="DROP" />
                </h:selectOneMenu>
                <h:message for="type" styleClass="error"/>
            </div>

            <div class="form-group">
                <label for="dosage">Dosage(In whole number) <span style="color:red">*</span></label>
                <h:inputText id="dosage" value="#{procedureController.prescribedMedicine.dosage}" 
                             required="true" styleClass="form-control"/>
                <h:message for="dosage" styleClass="error"/>
            </div>

            <div class="form-group">
                <label for="duration">Duration(In days) <span style="color:red">*</span></label>
                <h:inputText id="duration" value="#{procedureController.prescribedMedicine.duration}" 
                             required="true" styleClass="form-control"/>
                <h:message for="duration" styleClass="error"/>
            </div>

            <div class="form-group">
                <label for="startDate">Start Date <span style="color:red">*</span></label>
                <h:inputText id="startDate" value="#{procedureController.prescribedMedicine.startDate}" 
                             styleClass="form-control" required="true">
                    <f:convertDateTime pattern="yyyy-MM-dd" />
                </h:inputText>
                <h:message for="startDate" styleClass="error"/>
            </div>

            <div class="form-group">
                <label for="endDate">End Date <span style="color:red">*</span></label>
                <h:inputText id="endDate" value="#{procedureController.prescribedMedicine.endDate}" 
                             styleClass="form-control" required="true" >
                    <f:convertDateTime pattern="yyyy-MM-dd" />
                </h:inputText>
                <h:message for="endDate" styleClass="error"/>
            </div>

            <div class="form-group">
                <label for="notes">Notes</label>
                <h:inputTextarea id="notes" value="#{procedureController.prescribedMedicine.notes}" 
                                 cols="30" rows="4" styleClass="form-control"/>
                <h:message for="notes" styleClass="error"/>
            </div>

         <div class="button-group">
    <h:commandButton value="Add Medicine" 
                     action="#{procedureController.addPresribedMedicinesController(procedureController.prescribedMedicine)}" 
                     styleClass="btn-add" />

    <h:commandButton value="Reset Form"
                     action="#{procedureController.createNewPrescribedMedicine()}"
                     immediate="true"
                     styleClass="btn-reset" />
</div>

        </h:form>
    </div>
</body>
</html>
</f:view>
