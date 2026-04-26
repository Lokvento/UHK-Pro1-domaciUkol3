package pro1.reports.report2;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import pro1.DataSource;
import pro1.apiDataModel.ActionsList;
import pro1.reports.report2.reportDataModel.DepartmentStats;

public class DepartmentStatsReporting {
    public static DepartmentStats GetReport(DataSource dataSource, String rok, String katedra) {
        var actionsListJson = dataSource.getRozvrhByKatedra(rok, katedra);
        var actionsList = new Gson().fromJson(actionsListJson, ActionsList.class);
        return new DepartmentStats(
                maxActionStudentsCount(actionsList),
                emptyActionsCount(actionsList),
                maxTeacherScore(actionsList));
        // TODO 2.5: Oprav testovací data
    }

    private static long maxActionStudentsCount(ActionsList actionsList) {
        // TODO 2.0: Doplň potřebné atributy do třídy apiDataModel.Action
        // TODO 2.1: Doplň: maximální počet přihlášených studentů na rozvrhové akci
        if (actionsList == null || actionsList.items == null)
            return 0;
        long max = 0;
        for (var action : actionsList.items) {
            if (action.studendtCount > max) {
                max = action.studendtCount;
            }

        }
        return max;
    }

    private static long emptyActionsCount(ActionsList actionsList) {
        // TODO 2.2: Doplň: počet rozvrhových akcí s 0 studenty
        if (actionsList == null || actionsList.items == null)
            return 0;
        long count = 0;
        for (var action : actionsList.items) {
            if (action.studendtCount == 0) {
                count++;
            }
        }
        return count;
    }

    private static long maxTeacherScore(ActionsList actionsList) {
        // TODO 2.4: Doplň: nejvyšší výsledek dosažený metodou teacherScore mezi všemi
        // učiteli ve vstupních datech
        if (actionsList == null || actionsList.items == null)
            return 0;
        Map<Long, Long> scores = new HashMap<>();
        long maxScore = 0;
        for (var action : actionsList.items) {
            if (action.teacherId != null) {
                if (!scores.containsKey(action.teacherId)) {
                    long score = teacherScore(action.teacherId, actionsList);
                    scores.put(action.teacherId, score);
                    if (score > maxScore) {
                        maxScore = score;
                    }
                }
            }
            
        }
        return maxScore;
    }

    private static long teacherScore(long teacherId, ActionsList actionsList) {
        // TODO 2.3: Doplň pomocnou metodu - součet všech přihlášených studentů na
        // akcích daného učitele
        if (actionsList == null || actionsList.items == null)return 0;
        long score = 0;
        for (var action : actionsList.items) {
            if (action.teacherId != null && action.teacherId == teacherId) {
                score += action.studendtCount;
            }
        }
        return score;
    }
}
