package com.epam.domain;

import java.util.Date;
import java.util.Objects;

public class AutoOrder extends SqlEntity {

    int id;
    Date date;
    String ownerName;

    public AutoOrder withId(int id) {
        this.id = id;
        return this;
    }

    public AutoOrder withDate(Date date) {
        this.date = date;
        return this;
    }

    public AutoOrder withOwnerName(String ownerName) {
        this.ownerName = ownerName;
        return this;
    }

    @Override
    public String toString() {
        return "AutoOrder{" +
               "id=" + id +
               ", date=" + date +
               ", ownerName='" + ownerName + '\'' +
               '}';
    }


    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AutoOrder autoOrder = (AutoOrder) o;
        return id == autoOrder.id &&
               date.equals(autoOrder.date) &&
               ownerName.equals(autoOrder.ownerName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, ownerName);
    }
}
