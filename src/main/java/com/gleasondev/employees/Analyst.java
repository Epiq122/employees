package com.gleasondev.employees;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Analyst {

    private String lastName;
    private String firstName;
    private LocalDate dob;

    private int projectCount = 0;


    private final String peopleRegex = "(?<lastName>\\w+),\\s*(?<firstName>\\w+),\\s*(?<dob>\\d{1,2}/\\d{1,2}/\\d{4}),\\s*(?<role>\\w+)(?:,\\s*\\{(?<details>.*)\\})?\\n";

    private final String alystRegex = "\\w+=(?<projectCount>\\w+)";
    private final NumberFormat moneyFormat = NumberFormat.getCurrencyInstance();

    DateTimeFormatter dtFormatter = DateTimeFormatter.ofPattern("M/d/yyyy");

    public Analyst(String personText) {
        Pattern peoplePat = Pattern.compile(peopleRegex);
        Matcher peopleMat = peoplePat.matcher(personText);
        if (peopleMat.find()) {
            this.lastName = peopleMat.group("lastName");
            this.firstName = peopleMat.group("firstName");
            this.dob = LocalDate.from(dtFormatter.parse(peopleMat.group("dob")));
            Pattern alystPat = Pattern.compile(alystRegex);
            Matcher alystMat = alystPat.matcher(peopleMat.group("details"));
            if (alystMat.find()) {
                this.projectCount = Integer.parseInt(alystMat.group("projectCount"));
            }
        }


    }

    public int getSalary() {

        return 2500 * projectCount;
    }

    @Override
    public String toString() {
        return String.format("%s, %s: %s", lastName, firstName, moneyFormat.format(getSalary()));
    }

}
