import java.time.LocalDate;
import java.util.*;

class ScannerWork {
    private Scanner scanner = new Scanner(System.in);
    private Utilities utilities = new Utilities();
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_YELLOW = "\u001B[33m";

    private LocalDate getLocalDateStart() {
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

    LocalDate getLocalDateEnd(LocalDate localDateStart) {
        boolean flag = false;
        LocalDate localDateEnd;
        do {
            localDateEnd = getLocalDateStart();
            if (localDateEnd.isBefore(localDateStart)) {
                System.err.println("The end date of the period is befoere the start date of the period!");
            } else {
                flag = true;
            }
        } while (!flag);
        return localDateEnd;
    }

    CurrencyCode getCurrencyCode() {
        CurrencyCode[] currencyCodeTab = CurrencyCode.values();
        Map<Character, CurrencyCode> characterCurrencyCodeMap = new HashMap<>();
        int intForMap = 97;
        for (CurrencyCode cc : currencyCodeTab) {
            characterCurrencyCodeMap.put((char) intForMap, cc);
            intForMap++;
        }
        characterCurrencyCodeMap.forEach((k, v) -> System.out.println(k + ") " + v));

        System.out.println();
        int intForList = 97;
        List<Character> characterList = new ArrayList<>();
        for (int i = 0; i < CurrencyCode.values().length; i++) {
            characterList.add((char) intForList);
            intForList++;
        }
        characterList.forEach(character -> System.out.print(character + "), "));

        boolean flag = false;
        char choosenChar;
        do {
            System.out.println("Choose a currency: ");
            choosenChar = scanner.next().toLowerCase().charAt(0);
            if (characterList.contains(choosenChar)) {
                flag = true;
            } else {
                System.err.println("Bad choice, try again: ");
            }
        } while (!flag);

        return characterCurrencyCodeMap.get(choosenChar);
    }

    char getCharAQ() {
        boolean flag = false;
        char sign;
        do {
            System.out.println(ANSI_YELLOW + "Choose: a, q = ?" + ANSI_RESET);
            sign = scanner.next().toLowerCase().charAt(0);
            if (sign == 'a' || sign == 'q') {
                flag = true;
            } else {
                System.err.println("Bad choice.");
            }
        } while (!flag);
        return sign;
    }
}
