import model.Rate;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;

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
                    String table = utilities.getTable(charTable);

                    System.out.println();
                    System.out.println("Enter the period:");
                    System.out.println("Get the start date of the period: ");
                    LocalDate localDateStart = scannerWork.getLocalDateStart();
                    System.out.println(localDateStart.format(utilities.getDateTimeFormatter_dd_MM_yyyy()));

                    System.out.println("Get the end date of the period:");
                    LocalDate localDateEnd = scannerWork.getLocalDateEnd(localDateStart);
                    System.out.println(localDateEnd.format(utilities.getDateTimeFormatter_dd_MM_yyyy()));

                    System.out.println();
                    System.out.println("List of available currencies: ");
                    CurrencyCode currencyCode = scannerWork.getCurrencyCode();
                    System.out.println(currencyCode);

                    System.out.println();
                    String requestURL = BASE_NBP_API_URL
                            .replace("{table}", table)
                            .replace("{code}", currencyCode.toString())
                            .replace("{startDate}", localDateStart.format(utilities.getDateTimeFormatter_yyyy_MM_dd()))
                            .replace("{endDate}", localDateEnd.format(utilities.getDateTimeFormatter_yyyy_MM_dd()))
                            .replace("{dataFormat}", "json");
                    System.out.println(requestURL);

                    System.out.println();
                    System.out.println("Api content:");
                    String apiContent = utilities.loadContentFromURL(requestURL);
                    System.out.println(apiContent);

                    if (apiContent != null) {
                        System.out.println();
                        System.out.println("List rates:");
                        List<Rate> rates = utilities.processingJSON(apiContent);
                        rates.forEach(System.out::println);

                        if (charTable == 'a') {
                            boolean flagIfA = false;
                            do {
                                System.out.println();
                                System.out.println("Tabela A kursów średnich walut obcych. Choose:" +
                                        "\n a) mid min" +
                                        "\n b) mid max" +
                                        "\n c) mid average" +
                                        "\n q) quit");
                                char signIfA = scannerWork.getChars('a', 'b', 'c', 'q');
                                switch (signIfA) {
                                    case 'a' :
                                        System.out.println("Calculate minimum mid.");
                                        OptionalDouble optIfA_Min = utilities.getMinMid(rates);
                                        if (optIfA_Min.isPresent()) {
                                            System.out.println("Minimum mid = " + optIfA_Min.getAsDouble() + ".");
                                        }
                                        break;
                                    case 'b' :
                                        System.out.println("Calculate maximum mid.");
                                        OptionalDouble optIfA_Max = utilities.getMaxMid(rates);
                                        if (optIfA_Max.isPresent()) {
                                            System.out.println("Maximum mid = " + optIfA_Max.getAsDouble() + ".");
                                        }
                                        break;
                                    case 'c' :
                                        System.out.println("Calculate average mid.");
                                        OptionalDouble optIfA_Average = utilities.getAverageMid(rates);
                                        if (optIfA_Average.isPresent()) {
                                            System.out.println("Average mid = " + optIfA_Average.getAsDouble() + ".");
                                        }
                                        break;
                                    case 'q' :
                                        flagIfA = true;
                                        break;
                                }
                            } while (!flagIfA);
                        } else if (charTable == 'b') {
                            boolean flagIfB = false;
                            do {
                                System.out.println();
                                System.out.println("Tabela B kursów średnich walut obcych. Choose:" +
                                        "\n a) mid min" +
                                        "\n b) mid max" +
                                        "\n c) mid average" +
                                        "\n q) quit");
                                char signIfB = scannerWork.getChars('a', 'b', 'c', 'q');
                                switch (signIfB) {
                                    case 'a' :
                                        System.out.println("Calculate minimum mid.");
                                        OptionalDouble optIfB_Min = utilities.getMinMid(rates);
                                        if (optIfB_Min.isPresent()) {
                                            System.out.println("Minimum mid = " + optIfB_Min.getAsDouble() + ".");
                                        }
                                        break;
                                    case 'b' :
                                        System.out.println("Calculate maximum mid.");
                                        OptionalDouble optIfB_Max = utilities.getMaxMid(rates);
                                        if (optIfB_Max.isPresent()) {
                                            System.out.println("Maximum mid = " + optIfB_Max.getAsDouble() + ".");
                                        }
                                        break;
                                    case 'c' :
                                        System.out.println("Calculate average mid.");
                                        OptionalDouble optIfB_Average = utilities.getAverageMid(rates);
                                        if (optIfB_Average.isPresent()) {
                                            System.out.println("Average mid = " + optIfB_Average.getAsDouble() + ".");
                                        }
                                        break;
                                    case 'q' :
                                        flagIfB = true;
                                        break;
                                }
                            } while (!flagIfB);
                        } else if (charTable == 'c') {
                            System.out.println("Project C.");
                        }
                    }
                    break;
                case 'q' :
                    flag = true;
                    break;
            }
        } while (!flag);
    }
}
