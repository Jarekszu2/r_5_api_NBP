package model;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
@NoArgsConstructor
@Data
@XmlRootElement(name = "ExchangeRateSeries")
public class ExchangeRateSeries {

    @XmlElement(name = "Table")
    @SerializedName(value = "table")
    private String Table;

    @XmlElement(name = "Currency")
    @SerializedName(value = "currency")
    private String Currency;

    @XmlElement(name = "Code")
    @SerializedName(value = "code")
    private String Code;

    @XmlElement(name = "Rate")
    @XmlElementWrapper(name = "Rates")
    @SerializedName(value = "rates")
    private List<Rate> Rates;
}
