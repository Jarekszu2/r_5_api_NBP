package model;

import lombok.Data;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@XmlRootElement(name = "Rate")
public class Rate {

    @XmlElement(name = "No")
    private String no;

    @XmlElement(name = "EffectiveDate")
    private String effectiveDate;

    @XmlElement(name = "Bid")
    private Double bid;

    @XmlElement(name = "Ask")
    private Double ask;

    @XmlElement(name = "Mid")
    private Double mid;
}
