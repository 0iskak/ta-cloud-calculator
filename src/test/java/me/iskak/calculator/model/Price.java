package me.iskak.calculator.model;

import org.apache.logging.log4j.LogManager;

import java.util.Objects;

public record Price(String currency, double value) {
    public Price {
        currency = currency.toUpperCase();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        return o instanceof Price price &&
                price.currency.equals(currency) &&
                Double.compare(price.value, value) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(currency, value);
    }


    public static Price parsePrice(String s) {
        LogManager.getLogger().info("Parsing price with value: " + s);
        var split = s.replaceAll("^.+ (\\S+ [\\d,.]+).*$", "$1")
                .split(" ");
        return new Price(split[0], Double.parseDouble(split[1].replace(",", "")));
    }
}
