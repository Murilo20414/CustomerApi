package com.br.project.customer.util;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class GeneralConversions {

    //1.000,00 -> 1000.00
    public static BigDecimal convertToBigDecimal(String value) {
        if(value == null)
            return null;

        value = value.replace(".", "").replace(",", ".");
        return new BigDecimal(value);
    }

    public static LocalDate stringToLocalDate(String date) {
        return LocalDate.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public static String localDateToString(LocalDate localDate) {
        return localDate.toString();
    }

}
