package com.gleasondev.employees;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.regex.Matcher;

public class Main {
    public static void main(String[] args) {
        String peopleText = """
                Flinstone, Fred, 1/1/1900, Programmer, {locpd=2000,yoe=10,iq=140}
                Flinstone2, Fred2, 1/1/1900, Programmer, {locpd=1300,yoe=14,iq=100}
                Flinstone3, Fred3, 1/1/1900, Programmer, {locpd=2300,yoe=8,iq=105}
                Flinstone4, Fred4, 1/1/1900, Programmer, {locpd=1630,yoe=3,iq=115}
                Flinstone5, Fred5, 1/1/1900, Programmer, {locpd=5,yoe=10,iq=100}
                Rubble, Barney, 2/2/1905, Manager, {orgSize=300,dr=10}
                Rubble2, Barney2, 2/2/1905, Manager, {orgSize=100,dr=4}
                Rubble3, Barney3, 2/2/1905, Manager, {orgSize=200,dr=2}
                Rubble4, Barney4, 2/2/1905, Manager, {orgSize=500,dr=8}
                Rubble5, Barney5, 2/2/1905, Manager, {orgSize=175,dr=20}
                Flinstone, Wilma, 3/3/1910, Analyst, {projectCount=3}
                Flinstone2, Wilma2, 3/3/1910, Analyst, {projectCount=4}
                Flinstone3, Wilma3, 3/3/1910, Analyst, {projectCount=5}
                Flinstone4, Wilma4, 3/3/1910, Analyst, {projectCount=6}
                Flinstone5, Wilma5, 3/3/1910, Addnalyst, {projectCount=10}
                Rubble, Betty, 4/4/1915, CEO, {avgStockPrice=115}
                """;


        Matcher peopleMat = Employee.PEOPLE_PAT.matcher(peopleText);
        Flyer flyer = new CEO("");
        flyer.fly();


        // default method
        Programmer coder = new Programmer("");
        coder.cook("Steaks");
        coder.cleanUp();


        int totalSalaries = 0;
        int totalWithBonus = 0;
        IEmployee employee = null;
        while (peopleMat.find()) {
            employee = Employee.createEmployee(peopleMat.group());
            if (employee instanceof Programmer) {
                System.out.println(((Programmer) employee).getIq());
            } else if (employee instanceof Manager mang) {
                System.out.println(mang.getSalary());
            } else if (employee instanceof Analyst) {
                System.out.println();
            } else if (employee instanceof CEO) {
                System.out.println();
            }
            System.out.println(employee.toString());
            totalSalaries += employee.getSalary();
//            totalWithBonus += employee.getBonus();


        }
        NumberFormat currencyInstance = NumberFormat.getCurrencyInstance();

        System.out.printf("The total payout should be %s%n", currencyInstance.format(totalSalaries));
//        System.out.printf("The total payout should be with bonus %s%n", currencyInstance.format(totalWithBonus));


        // Record!
        Weirdo bucky = new Weirdo("Chuckluck", "Bucky", LocalDate.now());
        System.out.println(bucky.firstName());
        Weirdo jack = new Weirdo("Black", "Jack");
        System.out.println(jack.sayHello() + " " + jack.lastName());

    }
}
