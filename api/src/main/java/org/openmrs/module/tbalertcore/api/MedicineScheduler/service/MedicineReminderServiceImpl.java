package org.openmrs.module.tbalertcore.api.MedicineScheduler.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.module.sms.api.data.AdHocSMSData;
import org.openmrs.module.sms.api.service.ScheduleAdHocSMSesService;
import org.openmrs.module.tbalertcore.api.MedicineScheduler.MedicineReminderDTO;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MedicineReminderServiceImpl implements MedicineReminderService {
    private static final Log LOGGER = LogFactory.getLog(MedicineReminderServiceImpl.class);

    private final ScheduleAdHocSMSesService scheduleAdHocSMSesService;

    public MedicineReminderServiceImpl(ScheduleAdHocSMSesService scheduleAdHocSMSesService) {
        this.scheduleAdHocSMSesService = scheduleAdHocSMSesService;
    }

    @Scheduled(cron = "0 0 * * *")
    @Override
    public Integer scheduleDailySMS(List<MedicineReminderDTO> medicineReminderDTOS) {
        if (medicineReminderDTOS == null || medicineReminderDTOS.isEmpty()) {
            LOGGER.warn("No reminders found to schedule SMS.");
            return 0;
        }

        List<AdHocSMSData> smsData = new ArrayList<>();
        Set<String> uniqueContacts = new HashSet<>();

        for (MedicineReminderDTO reminderDTO : medicineReminderDTOS) {
            AdHocSMSData adHocSMSData = new AdHocSMSData();
            adHocSMSData.setContactTime(reminderDTO.getContactTime());
            adHocSMSData.setSmsText(reminderDTO.getTextMessage());
            adHocSMSData.setPhone(reminderDTO.getContactNumber());

            smsData.add(adHocSMSData);
            uniqueContacts.add(reminderDTO.getContactNumber());
        }

        scheduleAdHocSMSesService.scheduleAdHocSMSes(smsData);
        LOGGER.info("Medicine Reminder SMS scheduled for " + uniqueContacts.size() + " patients");
        return uniqueContacts.size();
    }
}
