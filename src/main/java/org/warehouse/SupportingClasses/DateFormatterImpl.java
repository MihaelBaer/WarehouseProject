package warehouse.SupportingClasses;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Service class for formatting a date in docs.
 * Income date always in dd-MM-yyyy format.
 * Returned format yyyy-MM-dd
 */
@Service
public class DateFormatterImpl implements DateFormatter {

    /**
     * @param dateToFormat - income date to be formatted
     * @return - formatted date
     */
    @Override
    public LocalDate formatDate(String dateToFormat) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return LocalDate.parse(dateToFormat, formatter);
    }
}
