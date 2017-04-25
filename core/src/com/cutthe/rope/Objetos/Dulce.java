package com.cutthe.rope.Objetos;

import com.badlogic.gdx.physics.box2d.Body;

public class Dulce extends Objeto {

    private Burbuja burbuja;

    public Dulce(PropiedadesObjeto propiedadesObjeto, Body body) {
        super(propiedadesObjeto, body);
    }
    
    public void setBurbuja(Burbuja burbuja) {
        this.burbuja = burbuja;
    }
    
    public Burbuja getBurbuja() {
        return this.burbuja;
    }
}
