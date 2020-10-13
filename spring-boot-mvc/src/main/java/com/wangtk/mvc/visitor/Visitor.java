package com.wangtk.mvc.visitor;


public interface Visitor {
    void visit(Engine engine);

    void visit(Body body);

    void visit(Car car);
}

class PrintCar implements Visitor {
    public void visit(Engine engine) {
        System.out.println("Visiting engine");
    }

    public void visit(Body body) {
        System.out.println("Visiting body");
    }

    public void visit(Car car) {
        System.out.println("Visiting car");
    }
}