package com.fenfei.myapplicationdemo.designpattern.factroypattern;

/**
 * Created by shefenfei on 2016/11/21.
 */

public abstract class PizzaStrore {

    public final Pizza orderPizza(String type) {
        Pizza pizza;
        pizza = createPizza(type);
        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();
        return pizza;
    }

    abstract Pizza createPizza(String type);
}
