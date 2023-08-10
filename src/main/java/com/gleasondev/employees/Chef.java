package com.gleasondev.employees;

public interface Chef {

    String favoriteFood = "Pork Chops!";

    // default method
    default void cook(String food) {
        System.out.println("I am now cooking " + food);

    }

    default String cleanUp() {
        return "Done cleaning up";
    }

    default String getFavoriteFood() {
        return favoriteFood;
    }

//    default void setFavoriteFood(String favoriteFood){
//    }


//    static int convertUnits() {
//        return 0;
//    }

    String yellAtPeople();


}
