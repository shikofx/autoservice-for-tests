package com.epam.data;

import java.util.Date;

public class RequestToFindOrders {

    private Date startDate;
    private Date endDate;

    public RequestToFindOrders withStartDate(Date startDate) {
        this.startDate = startDate;
        return this;
    }

    public RequestToFindOrders withEndDate(Date endDate) {
        this.endDate = endDate;
        return this;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }
}
