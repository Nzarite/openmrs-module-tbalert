package org.openmrs.module.tbalertcore.api.MedicineScheduler.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.openmrs.api.db.DAOException;
import org.openmrs.module.tbalertcore.api.MedicineScheduler.Reminder;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class MedicineReminderDAOImpl implements MedicineReminderDAO {
    public final Long REMINDER_LEAD_TIME = 60L * 60L;

    private HibernateTemplate hibernateTemplate;

    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    @Override
    @Transactional
    public void saveReminder(Reminder reminder) throws DAOException {
        try {
            if (reminder == null) throw new DAOException("Reminder cannot be null");
            hibernateTemplate.saveOrUpdate(reminder);
        } catch (Exception e) {
            throw new DAOException("Error saving reminder", e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List getRemindersByPatientId(Integer patientId) throws DAOException {
        try {
            if (patientId == null) {
                throw new DAOException("Patient ID cannot be null");
            }

            return hibernateTemplate.execute(session -> {
                Criteria criteria = session.createCriteria(Reminder.class);
                criteria.add(Restrictions.eq("patientId", patientId));
                criteria.add(Restrictions.eq("isActive", true));
                criteria.add(Restrictions.eq("voided", false));
                criteria.addOrder(Order.asc("scheduleTime"));
                return criteria.list();
            });
        } catch (Exception e) {
            throw new DAOException("Error retrieving reminders for patient: " + patientId, e);
        }
    }


    @Override
    @Transactional(readOnly = true)
    public List getRemindersByTime(LocalDateTime time) throws DAOException {
        try {
            if (time == null) throw new DAOException("Time cannot be null");

            return hibernateTemplate.execute(session -> {
                Criteria criteria = session.createCriteria(Reminder.class);
                criteria.add(Restrictions.le("scheduleTime", time));
                criteria.add(Restrictions.eq("voided", false));

                return criteria.list();
            });
        } catch (Exception e) {
            throw new DAOException("Error retrieving reminders for time: " + time, e);
        }
    }


    @Override
    public List getActiveRemindersByTime(LocalDateTime time) throws DAOException {
        try {
            if (time == null) throw new DAOException("Time cannot be null");

            return hibernateTemplate.execute(session -> {
                Criteria criteria = session.createCriteria(Reminder.class);
                criteria.add(Restrictions.le("scheduleTime", time));
                criteria.add(Restrictions.eq("voided", false));
                criteria.add(Restrictions.eq("isActive", true));

                return criteria.list();
            });
        } catch (Exception e) {
            throw new DAOException("Error retrieving active reminders for time: " + time, e);
        }
    }

    @Override
    @Transactional
    public void updateReminder(Reminder reminder) throws DAOException {
        try {
            if (reminder == null) {
                throw new DAOException("Reminder cannot be null");
            }
            hibernateTemplate.update(reminder);
        } catch (Exception e) {
            throw new DAOException("Error updating reminder: " + reminder, e);
        }
    }

    @Override
    public Reminder getReminderByReminderId(Integer reminderId) throws DAOException {
        try {
            if (reminderId == null) {
                throw new DAOException("Reminder ID cannot be null");
            }
            return hibernateTemplate.get(Reminder.class, reminderId);
        } catch (Exception e) {
            throw new DAOException("Error retrieving reminder: " + reminderId, e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List getAllReminders() throws DAOException {
        try {
            return hibernateTemplate.execute(session -> {
                Criteria criteria = session.createCriteria(Reminder.class);
                criteria.add(Restrictions.eq("voided", false));
                criteria.addOrder(Order.asc("scheduleTime"));
                return criteria.list();
            });
        } catch (Exception e) {
            throw new DAOException("Error retrieving all reminders", e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List getAllActiveReminders() throws DAOException {
        try {
            return hibernateTemplate.execute(session -> {
                Criteria criteria = session.createCriteria(Reminder.class);
                criteria.add(Restrictions.eq("voided", false));
                criteria.add(Restrictions.eq("isActive", true));
                criteria.addOrder(Order.asc("scheduleTime"));
                return criteria.list();
            });
        } catch (Exception e) {
            throw new DAOException("Error retrieving all reminders", e);
        }
    }

    @Override
    @Transactional
    public void deleteReminder(Reminder reminder) throws DAOException {
        try {
            if (reminder == null) {
                throw new DAOException("Reminder cannot be null");
            }
            reminder.setVoided(true);
            reminder.setDateVoided(new Date());
            reminder.setActive(false);
            hibernateTemplate.update(reminder);
        } catch (Exception e) {
            throw new DAOException("Error deleting reminder: " + reminder.getReminderId(), e);
        }
    }
}
