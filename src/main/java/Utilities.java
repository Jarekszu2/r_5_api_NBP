import java.time.format.DateTimeFormatter;

class Utilities {
    DateTimeFormatter getDateTimeFormatter() {
        return DateTimeFormatter.ofPattern("dd-MM-yyyy");
    }
}
