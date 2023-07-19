package com.fiap.intelligrid.domain.entity;

public enum Genero {
    MASCULINO, FEMININO, OUTROS;

    public static boolean contains(String sexo) {
        for (Genero genero : Genero.values()) {
            if (genero.name().equals(sexo)) {
                return true;
            }
        }
        return false;
    }
}