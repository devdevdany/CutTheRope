package com.cutthe.rope.Objetos;

import com.badlogic.gdx.physics.box2d.Body;

public class Estrella extends Objeto {

    private boolean obtenida;

    public Estrella(PropiedadesObjeto propiedadesObjeto, Body body) {
        super(propiedadesObjeto, body);
    }
    
    public void setObtenida(boolean obtenida) {
        this.obtenida = obtenida;
    }

    public boolean getObtenida() {
        return this.obtenida;
    }
}
