package com.test.employees.data.db;

import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.test.employees.data.db.dataclass.Specialist;
import com.test.employees.data.db.dataclass.SpecialistSpecialities;
import com.test.employees.data.db.dataclass.SpecialistSpecialities_Table;
import com.test.employees.data.db.dataclass.Specialist_Table;
import com.test.employees.data.db.dataclass.Speciality;
import com.test.employees.data.db.dataclass.Speciality_Table;

import java.util.List;

public class DBRepository {
    public static void saveData(List<com.test.employees.data.net.dataclass.Specialist> data) {
        for (com.test.employees.data.net.dataclass.Specialist specialist : data) {
            Specialist specialistDb =
                    SQLite.select().from(Specialist.class)
                            .where(Specialist_Table.fName.eq(specialist.fName)).and(Specialist_Table.lName.eq(specialist.lName))
                            .querySingle();
            if (specialistDb == null) {
                specialistDb = new Specialist();
            }
            specialistDb.setFName(specialist.fName);
            specialistDb.setLName(specialist.lName);
            specialistDb.setBirthday(specialist.birthday);
            specialistDb.setAvatarUrl(specialist.avatrUrl);
            specialistDb.save();
            for (com.test.employees.data.net.dataclass.Speciality speciality : specialist.speciality) {
                Speciality specialityDb = new Speciality();
                specialityDb.setSpecialityId(speciality.specialtyId);
                specialityDb.setName(speciality.name);
                specialityDb.save();
                SpecialistSpecialities specialistSpecialities = SQLite.select().from(SpecialistSpecialities.class)
                        .where(SpecialistSpecialities_Table.specialistId.eq(specialistDb.getSpecialistId()))
                        .and(SpecialistSpecialities_Table.specialityId.eq(specialityDb.getSpecialityId()))
                        .querySingle();
                if (specialistSpecialities == null) {
                    specialistSpecialities = new SpecialistSpecialities();
                    specialistSpecialities.setSpecialistId(specialistDb.getSpecialistId());
                    specialistSpecialities.setSpecialityId(specialityDb.getSpecialityId());
                    specialistSpecialities.save();
                }
            }
        }
    }

    public static List<Speciality> getSpecialities() {
        return SQLite.select().from(Speciality.class).queryList();
    }

    public static List<Speciality> getSpecialities(int specialistId) {
        return SQLite.select().from(Speciality.class)
                .innerJoin(SpecialistSpecialities.class)
                .on(SpecialistSpecialities_Table.specialityId.withTable().eq(Speciality_Table.specialityId.withTable()))
                .where(SpecialistSpecialities_Table.specialistId.eq(specialistId))
                .queryList();
    }

    public static Specialist getSpecialistById(int specialistId) {
        return SQLite.select().from(Specialist.class)
                .where(Specialist_Table.specialistId.withTable().eq(specialistId))
                .querySingle();
    }

    public static List<Specialist> getSpecialists() {
        return SQLite.select().from(Specialist.class).queryList();
    }

    public static List<Specialist> getSpecialists(int specialityId) {
        return SQLite.select().from(Specialist.class)
                .innerJoin(SpecialistSpecialities.class)
                .on(SpecialistSpecialities_Table.specialistId.withTable().eq(Specialist_Table.specialistId.withTable()))
                .where(SpecialistSpecialities_Table.specialityId.eq(specialityId))
                .queryList();
    }
}
