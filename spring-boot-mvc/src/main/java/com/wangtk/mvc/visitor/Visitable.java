package com.wangtk.mvc.visitor;

public interface Visitable {
    void accept(Visitor visitor);
}

class Body implements Visitable {
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}

class Engine implements Visitable {
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}