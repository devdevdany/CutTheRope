package com.cutthe.rope.Objetos;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

public class CuerdaDinamica extends ObjetoUsable {

    private final float radioAtrapar;
    private final BodyDef definicionBody;
    private final Body bodyRadioAtrapar;
    private final CircleShape forma;
    private final FixtureDef definicionFixture;

    public CuerdaDinamica(PropiedadesObjeto propiedadesObjeto, Body bodySoporteCuerdaDinamica, float radioAtrapar, World mundo) {
        super(propiedadesObjeto, bodySoporteCuerdaDinamica);

        /**
         * Radio que va a detectar si entra el dulce en él, para activar la
         * cuerda
         */
        this.radioAtrapar = radioAtrapar;

        this.definicionBody = new BodyDef();
        this.definicionBody.type = BodyType.StaticBody;
        this.definicionBody.position.set(body.getPosition());

        /**
         * Body que representa el radio
         */
        this.bodyRadioAtrapar = mundo.createBody(this.definicionBody);

        this.forma = new CircleShape();
        this.forma.setRadius(radioAtrapar);

        this.definicionFixture = new FixtureDef();
        this.definicionFixture.shape = this.forma;
        this.definicionFixture.isSensor = true;

        this.bodyRadioAtrapar.createFixture(this.definicionFixture);

        this.forma.dispose();
    }

    public boolean revisarDistanciaDulce(Vector2 coordenadaDulce) {
        /**
         * Revisar si la distancia del dulce al soporte de la cuerda dinámica es
         * menor a radioAtrapar al cuadrado
         */
        float radioCuadrado = this.radioAtrapar * this.radioAtrapar;
        return coordenadaDulce.dst2(this.body.getPosition()) < radioCuadrado;
    }

    public float getRadioAtrapar() {
        return this.radioAtrapar;
    }
}
