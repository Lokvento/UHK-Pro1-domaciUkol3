package pro1.reports.report5;

import com.google.gson.Gson;
import pro1.DataSource;
import pro1.apiDataModel.ExamsList;
import pro1.reports.report5.reportDataModel.DepartmentExamsStats;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DepartmentExamsStatsReporting {
    public static DepartmentExamsStats GetReport(DataSource dataSource, String katedra) {
        var json = dataSource.getTerminyZkousek2(katedra);
        var examsList = new Gson().fromJson(json, ExamsList.class);

        long realizedExamsCount = 0;
        Set<Long> uniqueTeacherIds = new HashSet<>();

        if (examsList != null && examsList.items != null) {
            for (var exam : examsList.items) {
                if (exam.registeredStudents != null && !exam.registeredStudents.isEmpty()) {
                    try {
                        if (Long.parseLong(exam.registeredStudents) > 0) {
                            realizedExamsCount++;
                        }
                    } catch (NumberFormatException ignored) {
                    }
                }

                if (exam.teacherId != null) {
                    uniqueTeacherIds.add(exam.teacherId);
                }
            }
        }

        List<Long> teacherIds = new ArrayList<>(uniqueTeacherIds);
        Collections.sort(teacherIds);

        return new DepartmentExamsStats(realizedExamsCount, teacherIds);
    }
}