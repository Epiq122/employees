package com.gleasondev.employees;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CEO extends Employee {


    private int averageStockPrice = 0;

    private final String ceoRegex = "\\w+=(?<avgStockPrice>\\w+)";
    private final Pattern ceoPat = Pattern.compile(ceoRegex);


    public CEO(String personText) {
        super(personText);
        Matcher ceoMat = ceoPat.matcher(peopleMat.group("details"));
        if (ceoMat.find()) {
            this.averageStockPrice = Integer.parseInt(ceoMat.group("avgStockPrice"));
        }
    }


    public int getSalary() {

        return 10000 * averageStockPrice;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
