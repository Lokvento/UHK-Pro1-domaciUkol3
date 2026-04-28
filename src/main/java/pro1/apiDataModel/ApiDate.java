package pro1.apiDataModel;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class ApiDate {
    public String value;

    public boolean isValid() {
        return toLocalDate() != null;
    }

    public LocalDate toLocalDate() {
        if (value == null || value.isEmpty())
            return null;
        {

        }
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.M.yyyy");
            return LocalDate.parse(value, formatter);
        } catch (DateTimeParseException e) {
            return null;
        }
    }
}
