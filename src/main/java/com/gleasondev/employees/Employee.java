package com.gleasondev.employees;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Employee implements IEmployee {

    protected final NumberFormat moneyFormat = NumberFormat.getCurrencyInstance();
    private static final String PEOPLE_REGEX = "(?<lastName>\\w+),\\s*(?<firstName>\\w+),\\s*(?<dob>\\d{1,2}/\\d{1,2}/\\d{4}),\\s*(?<role>\\w+)(?:,\\s*\\{(?<details>.*)\\})?\\n";
    public static final Pattern PEOPLE_PAT = Pattern.compile(PEOPLE_REGEX);
    protected final Matcher peopleMat;
    protected String lastName;
    protected String firstName;
    protected LocalDate dob;
    protected final DateTimeFormatter dtFormatter = DateTimeFormatter.ofPattern("M/d/yyyy");


    // this is for our nested class
    protected Employee() {
        peopleMat = null;
        lastName = "N/A";
        firstName = "N/A";
        dob = null;
    }

    public Employee(String personText) {
        peopleMat = Employee.PEOPLE_PAT.matcher(personText);
        if (peopleMat.find()) {
            this.lastName = peopleMat.group("lastName");
            this.firstName = peopleMat.group("firstName");
            this.dob = LocalDate.from(dtFormatter.parse(peopleMat.group("dob")));
        }
    }


    // factory method
    // Flinstone, Fred, 1/1/1900, Programmer, {locpd=2000,yoe=10,iq=140}
    public static IEmployee createEmployee(String employeeText) {
        Matcher peopleMat = Employee.PEOPLE_PAT.matcher(employeeText);
        if (peopleMat.find()) {
            return switch (peopleMat.group("role")) {
                case "Programmer" -> new Programmer(employeeText);

                case "Manager" -> new Manager(employeeText);

                case "Analyst" -> new Analyst(employeeText);

                case "CEO" -> new CEO(employeeText);

                // lambda
                default -> () -> 0;

            };
        } else {
            return () -> 0;
        }


    }

    public abstract int getSalary();

    public double getBonus() {
        return getSalary() * 1.10;
    }

    @Override
    public String toString() {
        return String.format("%s, %s: %s - %s", lastName, firstName, moneyFormat.format(getSalary()), moneyFormat.format(getBonus()));
    }


    // nested class

    private static final class DummyEmployee extends Employee implements IEmployee {
        @Override
        public int getSalary() {
            return 0;
        }
    }

//    private final class MyInnerClass {
//        public int getStuff() {
//            System.out.println(firstName);
//            return 0;
//        }
}
