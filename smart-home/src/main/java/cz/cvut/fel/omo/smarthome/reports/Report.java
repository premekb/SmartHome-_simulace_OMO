package cz.cvut.fel.omo.smarthome.reports;

abstract public class Report {
    protected final String reportText;


    protected Report(String reportText) {
        this.reportText = reportText;
    }

    @Override
    public String toString() {
        return reportText;
    }
}
