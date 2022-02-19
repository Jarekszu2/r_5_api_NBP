import java.time.LocalDate;
import java.util.Scanner;

class ScannerWork {
    private Scanner scanner = new Scanner(System.in);
    private Utilities utilities = new Utilities();

    LocalDate getLocalDate() {
        boolean flag = false;
        LocalDate localDate = null;
        do {
            System.out.println("Enter a date in format: dd-MM-yyyy: ");
            String stringDate = scanner.next();
            try {
                localDate = LocalDate.parse(stringDate, utilities.getDateTimeFormatter());
                flag = true;
            } catch (Exception e) {
                System.err.println("Bad format of the date!");
            }
        } while (!flag);
        return localDate;
    }
}
