package com.fenfei.myapplicationdemo.designpattern.factroypattern;

/**
 * Created by shefenfei on 2016/11/21.
 */

public class ChicagoPizzaStore extends PizzaStrore {

    @Override
    Pizza createPizza(String type) {
        Pizza pizza = new Pizza();
        if (type.equals("Chicago")) {
            pizza.setName("ChicagoPizza");
        }
        return pizza;
    }
}
