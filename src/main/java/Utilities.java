import com.google.gson.Gson;
import model.ExchangeRateSeries;
import model.Rate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.OptionalDouble;

class Utilities {
    DateTimeFormatter getDateTimeFormatter_dd_MM_yyyy() {
        return DateTimeFormatter.ofPattern("dd-MM-yyyy");
    }

    DateTimeFormatter getDateTimeFormatter_yyyy_MM_dd() {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd");
    }

    String getTable(char charTable) {
        String table = "";
        if (charTable == 'a') {
            table = "a";
        } else if (charTable == 'b') {
            table = "b";
        } else if (charTable == 'c') {
            table = "c";
        }
        return table;
    }

    String loadContentFromURL(String requestUrl) {
        String apiContent = null;
        try {
            URL url = new URL(requestUrl);
            InputStream inputStream = url.openStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            StringBuilder stringBuilder = new StringBuilder();
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
            bufferedReader.close();
            apiContent = stringBuilder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return apiContent;
    }

    List<Rate> processingJSON(String apiContent) {
        Gson gson = new Gson();
        ExchangeRateSeries exchangeRateSeries = gson.fromJson(apiContent, ExchangeRateSeries.class);
        return exchangeRateSeries.getRates();
    }

    OptionalDouble getMinMid(List<Rate> rates) {
        return rates.stream()
                .mapToDouble(Rate::getMid).min();
    }

    OptionalDouble getMaxMid(List<Rate> rates) {
        return rates.stream()
                .mapToDouble(Rate::getMid).max();
    }

    OptionalDouble getAverageMid(List<Rate> rates) {
        return rates.stream()
                .mapToDouble(Rate::getMid).average();
    }
}
