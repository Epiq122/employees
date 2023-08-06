package com.gleasondev.employees;

import java.text.NumberFormat;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Analyst extends Employee {


    private int projectCount = 0;


    private final String peopleRegex = "(?<lastName>\\w+),\\s*(?<firstName>\\w+),\\s*(?<dob>\\d{1,2}/\\d{1,2}/\\d{4}),\\s*(?<role>\\w+)(?:,\\s*\\{(?<details>.*)\\})?\\n";

    private final String alystRegex = "\\w+=(?<projectCount>\\w+)";
    private final Pattern alystPat = Pattern.compile(alystRegex);
    private final NumberFormat moneyFormat = NumberFormat.getCurrencyInstance();

    DateTimeFormatter dtFormatter = DateTimeFormatter.ofPattern("M/d/yyyy");

    public Analyst(String personText) {
        super(personText);

        Matcher alystMat = alystPat.matcher(peopleMat.group("details"));
        if (alystMat.find()) {
            this.projectCount = Integer.parseInt(alystMat.group("projectCount"));
        }
    }


    public int getSalary() {

        return 2500 * projectCount;
    }


}
