package com.gleasondev.employees;

import java.text.NumberFormat;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Manager extends Employee {
    private int organizationSize = 0;
    private int directReports = 0;


    private final String peopleRegex = "(?<lastName>\\w+),\\s*(?<firstName>\\w+),\\s*(?<dob>\\d{1,2}/\\d{1,2}/\\d{4}),\\s*(?<role>\\w+)(?:,\\s*\\{(?<details>.*)\\})?\\n";
    private final String mgrRegex = "\\w+=(?<orgSize>\\w+),\\w+=(?<dr>\\w+)";
    private final Pattern mgrPat = Pattern.compile(mgrRegex);
    private final NumberFormat moneyFormat = NumberFormat.getCurrencyInstance();
    DateTimeFormatter dtFormatter = DateTimeFormatter.ofPattern("M/d/yyyy");

    public Manager(String personText) {
        super(personText);

        Matcher mgrMat = mgrPat.matcher(peopleMat.group("details"));
        if (mgrMat.find()) {
            this.organizationSize = Integer.parseInt(mgrMat.group("orgSize"));
            this.directReports = Integer.parseInt(mgrMat.group("dr"));
        }
    }


    public int getSalary() {

        return 5000 + organizationSize * directReports;
    }


}
