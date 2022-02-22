import java.time.LocalDate;

/**
 * Zadanie NBP API:
 *
 * Stwórz main'a w którym pytasz użytkownika o 4 parametry, są nimi:
 * - kod waluty
 * - data początku zakresu
 * - data końca zakresu  (zweryfikuj że data końca jest
 *      późniejsza niż początku zakresu)
 * - rodzaj tabeli
 *      - jeśli użytkownik wybierze ASK/BID, chodzi o tabelę C
 *      - jeśli użytkownik wybierze MID, chodzi o tabelę A/B
 *      (możemy przyjąć że będzie to zawsze tabela A, przy wybraniu
 *       drugiej opcji).
 *
 *
 * Jako wynik aplikacji wypisz System.out.println() zapytanie które
 * należy wywołać na API by otrzymać wynik zgodny z danymi które
 * wprowadził użytkownik.
 *
 * Przetestuj działanie aplikacji - sprawdź czy zapytanie (skopiuj je do
 * przeglądarki) zwraca poprawne wyniki.
 *
 **/

public class Main {
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private final static String BASE_NBP_API_URL = "http://api.nbp.pl/api/exchangerates/rates/{table}/{code}/{startDate}/{endDate}/?format={dataFormat}";

    public static void main(String[] args) {
        ScannerWork scannerWork = new ScannerWork();
        Utilities utilities = new Utilities();
        System.out.println();

        boolean flag = false;
        char sign;
        do {
            System.out.println();
            System.out.println("a) Currencies." +
                    "\nq) Quit.");
            sign = scannerWork.getCharAQ();
            switch (sign) {
                case 'a' :
                    System.out.println("Aplication to work with currencies.");

                    System.out.println();
                    System.out.println("Choose:" +
                            "\n a) Tabela A kursów średnich walut obcych" +
                            "\n b) Tabela B kursów średnich walut obcych" +
                            "\n c) Tabela C kursów kupna i sprzedaży walut obcych");
                    char charTable = scannerWork.getCharABC();
                    scannerWork.description1(charTable);

                    System.out.println();
                    System.out.println("Enter the period:");
                    System.out.println("Get the start date of the period: ");
//        LocalDate localDateStart = scannerWork.getLocalDateStart();
//        System.out.println(localDateStart.format(utilities.getDateTimeFormatter()));

                    System.out.println("Get the end date of the period:");
//        LocalDate localDateEnd = scannerWork.getLocalDateEnd(localDateStart);
//        System.out.println(localDateEnd.format(utilities.getDateTimeFormatter()));

                    System.out.println();
                    System.out.println("List of available currencies: ");
                    CurrencyCode currencyCode = scannerWork.getCurrencyCode();
                    System.out.println(currencyCode);
                    break;
                case 'q' :
                    flag = true;
                    break;
            }
        } while (!flag);
    }
}
