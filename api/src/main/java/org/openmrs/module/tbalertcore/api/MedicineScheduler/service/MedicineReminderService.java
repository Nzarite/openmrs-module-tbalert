package org.openmrs.module.tbalertcore.api.MedicineScheduler.service;

import org.openmrs.module.tbalertcore.api.MedicineScheduler.MedicineReminderDTO;

import java.util.List;

public interface MedicineReminderService {
    Integer scheduleDailySMS(List<MedicineReminderDTO> arg1);
}
