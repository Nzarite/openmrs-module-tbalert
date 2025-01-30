package org.openmrs.module.tbalertcore.api.MedicineScheduler.dao;

import org.openmrs.api.db.DAOException;
import org.openmrs.module.tbalertcore.api.MedicineScheduler.Reminder;

import java.time.LocalDateTime;
import java.util.List;

public interface MedicineReminderDAO {
    void saveReminder(Reminder reminder) throws DAOException;

    Reminder getReminderByReminderId(Integer reminderId) throws DAOException;

    List<Reminder> getRemindersByPatientId(Integer var1) throws DAOException;

    List<Reminder> getRemindersByTime(LocalDateTime scheduleTime) throws DAOException;

    List<Reminder> getActiveRemindersByTime(LocalDateTime checkTime) throws DAOException;

    void updateReminder(Reminder var1) throws DAOException;

    List<Reminder> getAllReminders() throws DAOException;

    List<Reminder> getAllActiveReminders() throws DAOException;

    void deleteReminder(Reminder var1) throws DAOException;
}
