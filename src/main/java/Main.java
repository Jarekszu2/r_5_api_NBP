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
    public static void main(String[] args) {
        ScannerWork scannerWork = new ScannerWork();
        Utilities utilities = new Utilities();
        System.out.println();

        System.out.println("Get the start date: ");
        System.out.println(scannerWork.getLocalDate().format(utilities.getDateTimeFormatter()));
    }
}
