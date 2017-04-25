package com.cutthe.rope.Objetos;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.joints.DistanceJointDef;
import com.badlogic.gdx.physics.box2d.joints.RopeJointDef;
import com.badlogic.gdx.scenes.scene2d.Actor;
import java.util.ArrayList;

public class Cuerda extends Actor {

    private static final float VALOR_DAMPING = 0.8f;
    private static final float VALOR_OSCILACION = 9;
    private static final float VALOR_DAMPING_HEBRAS = 50;

    private static final float MITAD_ANCHOHEBRA = 0.5f;
    private static final float MITAD_ALTOHEBRA = 0.13f;

    private static final float CONSTANTEDENSIDAD = 45;
    private static final float LIMITEINFERIOR_DENSIDAD = 5;

    private static final float FRECUENCIA_NORMAL = 1.5f;
    private static final float FRECUENCIA_ELASTICA = 1.75f;

    private static final float PUNTOANCLAJE_HORIZONTAL_BODY = 0.4f;
    private static final float LONGITUDMAXIMA_JOINTS = 0.3f;

    private static final float DISTANCIA_HEBRAS = 0.4f;

    private static final float SIN_DISTANCIA_ENTRE_JOINTS = 0;

    private static final float DISTANCIA_DE_REFERENCIA = 1;

    private static final int PRIMER_JOINT = 0;

    private enum LimiteLongitud {
        INFERIOR(0),
        SUPERIOR(100);

        private final int valor;

        LimiteLongitud(int valor) {
            this.valor = valor;
        }

        public int getValor() {
            return this.valor;
        }
    }

    /**
     * La cuerda va del extremo 1 al 2, siendo el extremo 1 la "parte superior"
     */
    private final Body bodyExtremo1;
    private final Body bodyExtremo2;
    private final World mundo;
    private final ArrayList<Body> bodiesCuerda;

    private final int distanciaTotal;
    private final BodyDef definicionBodies;
    private final PolygonShape formaHebra;
    private final FixtureDef definicionFixture;
    private final float frecuenciaDeCreacion;
    private final RopeJointDef jointCuerda;
    private final DistanceJointDef jointDistancia;
    private final Vector2 vectorDistanciaEntreBodies;
    private final Vector2 vectorPosicionSiguienteBody;
    private Body bodyACortar;

    public Cuerda(Body bodyExtremo1, Body bodyExtremo2, World mundo, int longitudExtra, boolean elastica) {
        this.bodyExtremo1 = bodyExtremo1;
        this.bodyExtremo2 = bodyExtremo2;
        this.mundo = mundo;

        this.bodyExtremo1.setAngularDamping(VALOR_DAMPING);
        this.bodyExtremo2.setAngularDamping(VALOR_DAMPING);

        this.bodiesCuerda = new ArrayList<Body>();

        this.distanciaTotal = (longitudExtra > LimiteLongitud.SUPERIOR.getValor())
                ? longitudExtra - LimiteLongitud.SUPERIOR.getValor()
                : (int) this.bodyExtremo1.getPosition().dst(this.bodyExtremo2.getPosition());

        this.definicionBodies = new BodyDef();
        this.definicionBodies.type = BodyType.DynamicBody;
        this.definicionBodies.angularDamping = VALOR_DAMPING_HEBRAS;

        this.formaHebra = new PolygonShape();
        this.formaHebra.setAsBox(MITAD_ANCHOHEBRA, MITAD_ALTOHEBRA);

        this.definicionFixture = new FixtureDef();
        this.definicionFixture.isSensor = true;
        this.definicionFixture.shape = this.formaHebra;
        this.definicionFixture.density = ((CONSTANTEDENSIDAD / this.distanciaTotal) < LIMITEINFERIOR_DENSIDAD)
                ? LIMITEINFERIOR_DENSIDAD
                : CONSTANTEDENSIDAD / this.distanciaTotal;

        this.frecuenciaDeCreacion = elastica ? FRECUENCIA_ELASTICA : FRECUENCIA_NORMAL;

        this.jointCuerda = new RopeJointDef();
        /**
         * Como la longitud de las hebras será de 1, se colocan los puntos de
         * articulación a 0.5 del centro, para que sea el extremo de cada hebra.
         * Se usa el valor de x para crear las hebras de forma horizontal y
         * cuando se inicie el nivel dar la impresión de que se acaba de
         * "colgar" la cuerda
         */
        this.jointCuerda.localAnchorA.x = -PUNTOANCLAJE_HORIZONTAL_BODY;
        this.jointCuerda.localAnchorB.x = PUNTOANCLAJE_HORIZONTAL_BODY;
        this.jointCuerda.maxLength = LONGITUDMAXIMA_JOINTS;

        this.jointDistancia = new DistanceJointDef();
        if (elastica) {
            /**
             * Proporción de damping toma valores de 0 a 1
             */
            this.jointDistancia.dampingRatio = VALOR_DAMPING;
            /**
             * Valor de oscilación del sistema masa resorte en Hz
             */
            this.jointDistancia.frequencyHz = VALOR_OSCILACION;
        }

        this.vectorDistanciaEntreBodies = new Vector2(this.bodyExtremo2.getPosition());
        this.vectorDistanciaEntreBodies.sub(this.bodyExtremo1.getPosition());
        this.vectorDistanciaEntreBodies.nor();
        this.vectorDistanciaEntreBodies.scl(DISTANCIA_HEBRAS);

        this.vectorPosicionSiguienteBody = new Vector2(this.bodyExtremo1.getPosition());

        this.bodiesCuerda.add(this.bodyExtremo1);

        /**
         * Para unir el primer body
         */
        Body bodyAnterior = this.bodyExtremo1;
        Body bodyTemporal;

        /**
         * Crear cuerda entre los dos bodies indicados
         */
        for (float i = 0; i < this.distanciaTotal; i += this.frecuenciaDeCreacion) {
            this.vectorPosicionSiguienteBody.add(this.vectorDistanciaEntreBodies.x * 2, this.vectorDistanciaEntreBodies.y * 2);
            this.definicionBodies.angle = (float) Math.toRadians(this.vectorDistanciaEntreBodies.angle());
            this.definicionBodies.position.set(this.vectorPosicionSiguienteBody);
            bodyTemporal = this.mundo.createBody(this.definicionBodies);
            bodyTemporal.createFixture(this.definicionFixture);
            if (elastica) {
                this.jointDistancia.initialize(bodyAnterior, bodyTemporal, bodyAnterior.getPosition(), bodyTemporal.getPosition());
                this.jointDistancia.length = SIN_DISTANCIA_ENTRE_JOINTS;
                this.mundo.createJoint(this.jointDistancia);
            } else {
                this.jointCuerda.bodyA = bodyAnterior;
                this.jointCuerda.bodyB = bodyTemporal;
                this.mundo.createJoint(this.jointCuerda);
            }
            this.bodiesCuerda.add(bodyTemporal);
            bodyAnterior = bodyTemporal;
        }

        /**
         * Añadir longitud a la cuerda para que "cuelgue" y no sea sólo una
         * cuerda recta entre los bodies indicados
         */
        if (longitudExtra > LimiteLongitud.INFERIOR.getValor() && longitudExtra < LimiteLongitud.SUPERIOR.getValor()) {
            for (int i = 0; i < longitudExtra; ++i) {
                this.vectorPosicionSiguienteBody.add(this.vectorDistanciaEntreBodies.x * 2, this.vectorDistanciaEntreBodies.y * 2);
                this.definicionBodies.angle = (float) Math.toRadians(this.vectorDistanciaEntreBodies.angle());
                this.definicionBodies.position.set(this.vectorPosicionSiguienteBody);
                bodyTemporal = this.mundo.createBody(this.definicionBodies);
                bodyTemporal.createFixture(this.definicionFixture);
                if (elastica) {
                    this.jointDistancia.initialize(bodyAnterior, bodyTemporal, bodyAnterior.getPosition(), bodyTemporal.getPosition());
                    this.mundo.createJoint(this.jointDistancia);
                } else {
                    this.jointCuerda.bodyA = bodyAnterior;
                    this.jointCuerda.bodyB = bodyTemporal;
                    this.mundo.createJoint(this.jointCuerda);
                }
                this.bodiesCuerda.add(bodyTemporal);
                bodyAnterior = bodyTemporal;
            }
        }

        /**
         * Para unir el último body
         */
        if (elastica) {
            this.jointDistancia.initialize(bodyAnterior, this.bodyExtremo2, bodyAnterior.getPosition(), this.bodyExtremo2.getPosition());
            this.mundo.createJoint(this.jointDistancia);
        } else {
            this.jointCuerda.bodyA = bodyAnterior;
            this.jointCuerda.bodyB = this.bodyExtremo2;
            this.mundo.createJoint(this.jointCuerda);
        }

        this.formaHebra.dispose();

        this.bodyACortar = null;
    }

    public boolean revisarDistanciaMouse(Vector2 coordenadaCorte) {
        for (Body b : this.bodiesCuerda) {
            if (b.getPosition().dst2(coordenadaCorte) < DISTANCIA_DE_REFERENCIA) {
                this.bodyACortar = b;
                return true;
            }
        }
        return false;
    }

    public void destruir() {
        ArrayList<Body> bodiesADestruir = new ArrayList<Body>();
        /**
         * Si se cortó la cuerda
         */
        if (this.bodyACortar != null) {
            for (Body b : this.bodiesCuerda) {
                if (b == this.bodyACortar) {
                    //Destruir primer joint
                    this.mundo.destroyJoint(b.getJointList().get(PRIMER_JOINT).joint);
                }
                if (b != this.bodyExtremo1 && b != this.bodyExtremo2) {
                    bodiesADestruir.add(b);
                }
            }
        } /**
         * Sino, si llegó el dulce a OmNom destruir toda la cuerda
         */
        else {
            for (Body b : this.bodiesCuerda) {
                if (b != this.bodyExtremo1 && b != this.bodyExtremo2) {
                    bodiesADestruir.add(b);
                }
            }
        }

        /**
         * Poner antes de destruir animación de desvanecer para no borrar luego
         * luego
         */
        /*for (Body b : bodiesADestruir) {
            this.bodiesCuerda.remove(b);
            this.mundo.destroyBody(b);
        }*/
    }
}