package pro1.reports.report4;

import com.google.gson.Gson;
import pro1.DataSource;
import pro1.apiDataModel.ThesisList;
import pro1.reports.report4.reportDataModel.YearDuration;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class ThesisDurationReporting {
    public static List<YearDuration> Getreport(DataSource dataSource, String katedra, String[] years) {
        var reportItems = new ArrayList<YearDuration>();
        Gson gson = new Gson();

        if (years != null) {
            for (String year : years) {
                var json = dataSource.getKvalifikacniPrace(year, katedra);
                var thesisList = gson.fromJson(json, ThesisList.class);

                long totalDays = 0;
                long validCount = 0;

                if (thesisList != null && thesisList.items != null) {
                    for (var thesis : thesisList.items) {
                        if (thesis.assigmentDate != null && thesis.assigmentDate.isValid()
                                && thesis.submissionDate != null && thesis.submissionDate.isValid()) {

                            long days = ChronoUnit.DAYS.between(thesis.assigmentDate.toLocalDate(),
                                    thesis.submissionDate.toLocalDate());

                                    totalDays += days;
                                    validCount++;
                        }
                    }
                }
                long avarageDuration = validCount > 0 ? Math.round((double) totalDays/validCount) : 0;
                reportItems.add(new YearDuration(year, avarageDuration));
            }
        }
        return reportItems;
    }
}
