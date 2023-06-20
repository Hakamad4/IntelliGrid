package com.fiap.intelligrid.domain.entity;

public enum Voltagem {
        BIVOLT ("Bivolt"),
        VOLTS_127 ("127 V"),
        VOLTS_220 ("220 V");

    public final String label;

    private Voltagem(String label) {
        this.label = label;
    }

    @Override 
    public String toString() { 
        return this.label; 
    }
}
