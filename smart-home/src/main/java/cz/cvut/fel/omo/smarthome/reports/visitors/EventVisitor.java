package cz.cvut.fel.omo.smarthome.reports.visitors;

import cz.cvut.fel.omo.smarthome.events.abstractevents.Alert;
import cz.cvut.fel.omo.smarthome.events.abstractevents.Event;
import cz.cvut.fel.omo.smarthome.events.abstractevents.ImportantEvent;
import cz.cvut.fel.omo.smarthome.models.house.House;
import cz.cvut.fel.omo.smarthome.reports.EventReport;

import java.util.Queue;
import java.util.stream.Collectors;

public class EventVisitor {
    private StringBuilder reportTextBuilder = new StringBuilder();

    public void visitHouse(House house){
        Queue<Event> handledEvents = house.getHandledEvents();
        Queue<Event> unhandledEvents = house.getUnhandledEvents();

        reportTextBuilder
                .append("------------------------------------------------------------------\n")
                .append("Handled events:\n")
                .append("------------------------------------------------------------------\n");
        for (Event event : handledEvents){
            event.accept(this);
        }

        reportTextBuilder
                .append("------------------------------------------------------------------\n")
                .append("Unhandled events:\n")
                .append("------------------------------------------------------------------\n");
        for (Event event : unhandledEvents){
            event.accept(this);
        }
    }

    public void visitAlert(Alert alert){
        String handledBy = getHandledByString(alert);

        reportTextBuilder
                .append("Alert:\n")
                .append(getEventInfoString(alert, handledBy));
    }

    public void visitImportantEvent(ImportantEvent importantEvent){
        String handledBy = getHandledByString(importantEvent);

        reportTextBuilder
                .append("Important event:\n")
                .append(getEventInfoString(importantEvent, handledBy));
    }

    private String getEventInfoString(Event event, String handledBy){
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder
                .append("- Description: ").append(event.getDescription()).append("\n")
                .append("- Source: ").append(event.getSource()).append("\n")
                .append("- Handled by: ").append(handledBy).append("\n");

        return stringBuilder.toString();
    }

    private String getHandledByString(Event event){
        String handledBy;
        if (event.isHandled()) {
            handledBy = event.getHandledBy()
                    .stream()
                    .map(observer -> observer.toString())
                    .collect(Collectors.joining(". "));
        }

        else{
            handledBy = "";
        }

        return handledBy;
    }

    public EventReport getReport(){
        return new EventReport(reportTextBuilder.toString());
    }
}
