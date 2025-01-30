package org.openmrs.module.tbalertcore.api.MedicineScheduler;

import org.openmrs.BaseOpenmrsData;

import java.time.LocalDateTime;

public class Reminder extends BaseOpenmrsData {
    private int reminderId;
    private int patientId;
    private int medicineId;
    private LocalDateTime scheduleTime;
    private boolean isActive;

    public Reminder() {
    }

    public Reminder(int patientId, int medicineId, LocalDateTime scheduleTime, boolean isActive) {
        this.patientId = patientId;
        this.medicineId = medicineId;
        this.scheduleTime = scheduleTime;
        this.isActive = isActive;
    }

    public int getReminderId() {
        return reminderId;
    }

    public void setReminderId(int reminderId) {
        this.reminderId = reminderId;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public int getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(int medicineId) {
        this.medicineId = medicineId;
    }

    public LocalDateTime getScheduleTime() {
        return scheduleTime;
    }

    public void setScheduleTime(LocalDateTime scheduleTime) {
        this.scheduleTime = scheduleTime;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @Override
    public Integer getId() {
        return getReminderId();
    }

    @Override
    public void setId(Integer id) {
        setReminderId(id);
    }
}
