package pro1.reports.report3;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import pro1.DataSource;
import pro1.apiDataModel.ActionsList;
import pro1.reports.report3.reportDataModel.WeekdayStats;

public class DepartmentWeekdaysReporting {
    public static List<WeekdayStats> GetReport(DataSource dataSource, String rok, String katedra, String[] days) {
        var actionsListJson = dataSource.getRozvrhByKatedra(rok, katedra);
        var actionsList = new Gson().fromJson(actionsListJson, ActionsList.class);

        var reportItems = new ArrayList<WeekdayStats>();

        if (days != null) {
            for (String day : days) {
                long count = 0;
                if (actionsList != null && actionsList.items != null) {
                    for ( var action : actionsList.items) {
                        if (action.dayAbbrev != null && action.dayAbbrev.equals(day)) {
                            count++;
                        }
                        
                    }
                    reportItems.add(new WeekdayStats(day, count));
                }
            }
        }
        return reportItems;
    }
}
