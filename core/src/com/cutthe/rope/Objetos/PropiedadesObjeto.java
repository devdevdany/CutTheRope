package com.cutthe.rope.Objetos;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;

public class PropiedadesObjeto {

    private static final float ANGULO_POR_DEFECTO = 0;
    private static final float FRICCION_POR_DEFECTO = 0.5f;
    private static final float RESTITUCION_POR_DEFECTO = 0.5f;
    private static final float GRAVEDAD_POR_DEFECTO = 1;

    private final Vector2 vectorPosicion;
    private final Shape forma;
    private Body body;
    private final BodyType tipoBody;
    private float angulo = ANGULO_POR_DEFECTO;
    private float friccion = FRICCION_POR_DEFECTO;
    private float restitucion = RESTITUCION_POR_DEFECTO;
    private float escalaGravedad = GRAVEDAD_POR_DEFECTO;
    private boolean esDulce;
    private boolean esSensor;
    private boolean esPico;

    public PropiedadesObjeto(float radio, BodyType tipoBody) {
        this.vectorPosicion = new Vector2(0, 0);
        this.forma = new CircleShape();
        /**
         * Se castea al tipo de forma que se creo para evitar errores
         */
        ((CircleShape) this.forma).setRadius(radio);
        this.tipoBody = tipoBody;
    }

    public PropiedadesObjeto(float mitadX, float mitadY, float angulo, BodyType tipoBody) {
        this.vectorPosicion = new Vector2(0, 0);
        this.forma = new PolygonShape();
        /**
         * Se castea al tipo de forma que se creo para evitar errores
         */
        ((PolygonShape) this.forma).setAsBox(mitadX, mitadY);
        this.tipoBody = tipoBody;
        this.angulo = (float) Math.toRadians(angulo);
    }

    public PropiedadesObjeto(PropiedadesObjeto objeto) {
        this.vectorPosicion = new Vector2(objeto.vectorPosicion);
        this.forma = objeto.forma;
        this.body = objeto.body;
        this.tipoBody = objeto.tipoBody;
        this.angulo = objeto.angulo;
        this.friccion = objeto.friccion;
        this.restitucion = objeto.restitucion;
        this.escalaGravedad = objeto.escalaGravedad;
        this.esDulce = objeto.esDulce;
        this.esSensor = objeto.esSensor;
        this.esPico = objeto.esPico;
    }

    public void setDulce(boolean esDulce) {
        this.esDulce = esDulce;
    }

    public void setSensor(boolean esSensor) {
        this.esSensor = esSensor;
    }

    public boolean getSensor() {
        return this.esSensor;
    }

    public void setAngulo(float angulo) {
        this.angulo = (float) Math.toRadians(angulo);
    }

    public void setPico(boolean esPico) {
        this.esPico = esPico;
    }

    public boolean esPico() {
        return this.esPico;
    }

    public void setBody(Body body) {
        this.body = body;
    }

    public Body getBody() {
        return this.body;
    }

    public Vector2 getVectorPosicion() {
        return this.vectorPosicion;
    }

    public BodyType getTipoBody() {
        return this.tipoBody;
    }

    public float getAngulo() {
        return this.angulo;
    }

    public Shape getForma() {
        return this.forma;
    }
}
