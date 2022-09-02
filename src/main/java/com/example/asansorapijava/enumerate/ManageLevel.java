package com.example.asansorapijava.enumerate;

import org.hibernate.boot.AttributeConverterInfo;

import javax.persistence.AttributeConverter;

public enum ManageLevel {
    Manager(0), Admin(1), Reader(2);

    private final int id;

    ManageLevel(int id) {
        this.id = id;
    }

    public static ManageLevel getManagerLevel(Integer id) {
        if (id == null) {
            return null;
        }

        for (ManageLevel manageLevel : ManageLevel.values()) {
            if (id.equals(manageLevel.getID())) {
                return manageLevel;
            }
        }

        return null;
    }


    public int getID() {
        return id;
    }
}
