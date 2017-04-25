package com.cutthe.rope.Objetos;

import com.badlogic.gdx.physics.box2d.Body;

public class Objeto {
    
    protected final PropiedadesObjeto propiedadesObjeto;
    protected final Body body;
    
    Objeto(PropiedadesObjeto propiedadesObjeto, Body body) {
        this.propiedadesObjeto = propiedadesObjeto;
        this.body = body;
    }
    
    public PropiedadesObjeto getPropiedadesObjeto() {
        return this.propiedadesObjeto;
    }
    
    public Body getBody() {
        return this.body;
    }
}
