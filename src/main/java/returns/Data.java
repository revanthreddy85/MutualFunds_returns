package returns;

import java.util.Date;

class Data {
    private Date startDate;
    private Date endDate;
    private double returns;

    Data(Date startDate, Date endDate, double returns) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.returns = returns;
    }

    Date getStartDate() {
        return startDate;
    }

    Date getEndDate() {
        return endDate;
    }

    double getReturns() {
        return returns;
    }
}
