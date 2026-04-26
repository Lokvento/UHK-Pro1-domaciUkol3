package pro1.reports.report1;

import com.google.gson.Gson;
import pro1.DataSource;
import pro1.apiDataModel.BooksList;
import pro1.apiDataModel.TeacherCoursesList;
import pro1.reports.report1.reportDataModel.CourseBook;

import java.util.ArrayList;
import java.util.List;

public class TeacherBooksReporting {

    public static List<CourseBook> GetReport(DataSource dataSource, String rok, int ucitIdno, String katedra) {
        var coursesJson = dataSource.getPredmetyByUcitel(rok, ucitIdno, katedra);

        // TODO 1.1: Převeď coursesJson na objekt typu apiDataModel.TeacherCoursesList.
        var teacherCoursesList = new Gson().fromJson(coursesJson, TeacherCoursesList.class);

        // TODO 1.2: Doplň nutné atributy do třídy apiDataModel.TeacherCourse

        var reportItems = new ArrayList<CourseBook>();

        // TODO 1.3: Pro každý předmět získej z dataSource ještě seznam knih. Pro každou
        // z nich přidej prvek do reportItems.
        if (teacherCoursesList != null && teacherCoursesList.items != null) {
            for (var course : teacherCoursesList.items) {
                var booksJson = dataSource.getLiteraturaPredmetu(course.code, katedra);
                var booksList = new Gson().fromJson(booksJson, BooksList.class);

                if (booksList != null && booksList.items != null) {
                    for (var book : booksList.items) {
                        reportItems.add(new CourseBook(book.title, book.author, course.code));
                    }
                }
            }
        }

        return reportItems;
    }
}