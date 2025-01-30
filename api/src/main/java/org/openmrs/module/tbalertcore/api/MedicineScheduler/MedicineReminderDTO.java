package org.openmrs.module.tbalertcore.api.MedicineScheduler;

public class MedicineReminderDTO {

    private String contactNumber;
    private String contactTime;
    private String textMessage;

    public MedicineReminderDTO(String contactNumber, String contactTime, String textMessage) {
        this.contactNumber = contactNumber;
        this.contactTime = contactTime;
        this.textMessage = textMessage;
    }

    public MedicineReminderDTO() {
    }

    public String getContactTime() {
        return contactTime;
    }

    public void setContactTime(String contactTime) {
        this.contactTime = contactTime;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getTextMessage() {
        return textMessage;
    }

    public void setTextMessage(String textMessage) {
        this.textMessage = textMessage;
    }
}
