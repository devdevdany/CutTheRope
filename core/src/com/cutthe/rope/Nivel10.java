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
public class Nivel10 extends PantallaJuego {

    private static final float OMNOM_X = 0;
    private static final float OMNOM_Y = 3;

    private static final float DULCE_X = 0;
    private static final float DULCE_Y = 30;

    private static final float CUERDA1_X = 0;
    private static final float CUERDA1_Y = 39;
    private static final int LONGITUD_EXTRA_CUERDA1 = 2;

    private static final float CUERDA2_X = -7;
    private static final float CUERDA2_Y = 35;
    private static final int LONGITUD_EXTRA_CUERDA2 = 2;

    private static final float CUERDA3_X = 7;
    private static final float CUERDA3_Y = 35;
    private static final int LONGITUD_EXTRA_CUERDA3 = 2;

    private static final float CUERDA_ATRAPA1_X = -15;
    private static final float CUERDA_ATRAPA1_Y = 25;
    private static final float RADIO1 = 6;

    private static final float CUERDA_ATRAPA2_X = -5;
    private static final float CUERDA_ATRAPA2_Y = 15;
    private static final float RADIO2 = 6;

    private static final float CUERDA_ATRAPA3_X = 5;
    private static final float CUERDA_ATRAPA3_Y = 15;
    private static final float RADIO3 = 6;

    private static final float CUERDA_ATRAPA4_X = 15;
    private static final float CUERDA_ATRAPA4_Y = 25;
    private static final float RADIO4 = 6;

    private static final float ESTRELLA1_X = -15;
    private static final float ESTRELLA1_Y = 19;

    private static final float ESTRELLA2_X = -5;
    private static final float ESTRELLA2_Y = 9;

    private static final float ESTRELLA3_X = 5;
    private static final float ESTRELLA3_Y = 9;

    public Nivel10(CutTheRope juego) {
        super(juego);
    }

    @Override
    public void crearNivel(World mundo) {
        this.crearOmNom(OMNOM_X, OMNOM_Y);
        this.crearDulce(DULCE_X, DULCE_Y);
        this.crearCuerda(CUERDA1_X, CUERDA1_Y, LONGITUD_EXTRA_CUERDA1);
        this.crearCuerda(CUERDA2_X, CUERDA2_Y, LONGITUD_EXTRA_CUERDA2);
        this.crearCuerda(CUERDA3_X, CUERDA3_Y, LONGITUD_EXTRA_CUERDA3);
        this.crearCuerdaDinamica(CUERDA_ATRAPA1_X, CUERDA_ATRAPA1_Y, RADIO1);
        this.crearCuerdaDinamica(CUERDA_ATRAPA2_X, CUERDA_ATRAPA2_Y, RADIO2);
        this.crearCuerdaDinamica(CUERDA_ATRAPA3_X, CUERDA_ATRAPA3_Y, RADIO3);
        this.crearCuerdaDinamica(CUERDA_ATRAPA4_X, CUERDA_ATRAPA4_Y, RADIO4);
        this.crearEstrella(ESTRELLA1_X, ESTRELLA1_Y);
        this.crearEstrella(ESTRELLA2_X, ESTRELLA2_Y);
        this.crearEstrella(ESTRELLA3_X, ESTRELLA3_Y);
    }

}
