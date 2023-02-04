package com.example.ex4.beans;

import org.springframework.stereotype.Component;

@Component(value="autowiredLabelDependency")
public class Label {
    private String label = "Arbitrary Label";
    private boolean active =false;
    public Label() {
    }
    @Override
    public String toString() {
        return label;
    }
    public void setLabel(String l) {
        label = l;
    }
    public void setActive(boolean active){this.active=active;}
    public boolean getActive(){return this.active;}


}