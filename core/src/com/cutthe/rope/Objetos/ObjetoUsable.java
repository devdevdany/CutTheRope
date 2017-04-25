package com.cutthe.rope.Objetos;

import com.badlogic.gdx.physics.box2d.Body;

public class ObjetoUsable extends Objeto {

    private boolean usado;

    ObjetoUsable(PropiedadesObjeto propiedadesObjeto, Body body) {
        super(propiedadesObjeto, body);
    }

    public void setUsado(boolean usado) {
        this.usado = usado;
    }
    
    public boolean getUsado() {
        return this.usado;
    }
    
}
