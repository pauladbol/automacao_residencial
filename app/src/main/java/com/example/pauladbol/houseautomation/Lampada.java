package com.example.pauladbol.houseautomation;

/**
 * Created by pauladbol on 2017-06-12.
 */
public class Lampada {
    Boolean acender;

    public Lampada(Boolean acender) {
        this.acender = acender;
    }

    public Lampada() {

    }

    public Boolean getAcender() {
        return acender;
    }

    public void setAcender(Boolean acender) {
        this.acender = acender;
    }
}
