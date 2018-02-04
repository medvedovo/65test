package com.test.employees.data.db.dataclass;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;
import com.test.employees.data.db.AppDatabase;

@Table(database = AppDatabase.class)
public class SpecialistSpecialities extends BaseModel {
    @PrimaryKey(autoincrement = true)
    private int id;

    @Column
    private int specialistId;

    @Column
    private int specialityId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSpecialistId() {
        return specialistId;
    }

    public void setSpecialistId(int specialistId) {
        this.specialistId = specialistId;
    }

    public int getSpecialityId() {
        return specialityId;
    }

    public void setSpecialityId(int specialityId) {
        this.specialityId = specialityId;
    }
}
