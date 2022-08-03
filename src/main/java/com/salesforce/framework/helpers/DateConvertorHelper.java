package com.salesforce.framework.helpers;

import io.qameta.allure.Step;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateConvertorHelper {

    @Step("Convert '{0}' to civilian date format")
    public static String convertLocalDateToCivilianDateFormat(LocalDate localDate) {
        return localDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }
}
