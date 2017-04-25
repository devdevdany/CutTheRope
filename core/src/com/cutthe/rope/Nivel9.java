/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cutthe.rope;

import com.badlogic.gdx.physics.box2d.World;

/**
 *
 * @author oscarantonio
 */
public class Nivel9 extends PantallaJuego {

    private static final float OMNOM_X = 18;
    private static final float OMNOM_Y = 3;

    private static final float DULCE_X = -17;
    private static final float DULCE_Y = 32;

    private static final float CUERDA_X = -17;
    private static final float CUERDA_Y = 39;

    private static final float CUERDA_ATRAPA1_X = -10;
    private static final float CUERDA_ATRAPA1_Y = 27;
    private static final float RADIO1 = 7;

    private static final float CUERDA_ATRAPA2_X = 0;
    private static final float CUERDA_ATRAPA2_Y = 20;
    private static final float RADIO2 = 7;

    private static final float CUERDA_ATRAPA3_X = 10;
    private static final float CUERDA_ATRAPA3_Y = 13;
    private static final float RADIO3 = 7;

    private static final float ESTRELLA1_X = -10;
    private static final float ESTRELLA1_Y = 20;

    private static final float ESTRELLA2_X = 0;
    private static final float ESTRELLA2_Y = 13;

    private static final float ESTRELLA3_X = 10;
    private static final float ESTRELLA3_Y = 6;

    public Nivel9(CutTheRope juego) {
        super(juego);
    }

    @Override
    public void crearNivel(World mundo) {
        this.crearOmNom(OMNOM_X, OMNOM_Y);
        this.crearDulce(DULCE_X, DULCE_Y);
        this.crearCuerda(CUERDA_X, CUERDA_Y);
        this.crearCuerdaDinamica(CUERDA_ATRAPA1_X, CUERDA_ATRAPA1_Y, RADIO1);
        this.crearCuerdaDinamica(CUERDA_ATRAPA2_X, CUERDA_ATRAPA2_Y, RADIO2);
        this.crearCuerdaDinamica(CUERDA_ATRAPA3_X, CUERDA_ATRAPA3_Y, RADIO3);
        this.crearEstrella(ESTRELLA1_X, ESTRELLA1_Y);
        this.crearEstrella(ESTRELLA2_X, ESTRELLA2_Y);
        this.crearEstrella(ESTRELLA3_X, ESTRELLA3_Y);
    }

}
