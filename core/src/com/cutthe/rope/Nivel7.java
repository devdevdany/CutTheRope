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
public class Nivel7 extends PantallaJuego {

    private static final float OMNOM_X = 8;
    private static final float OMNOM_Y = 6;

    private static final float DULCE_X = 0;
    private static final float DULCE_Y = 28;

    private static final float CUERDA1_X = 0;
    private static final float CUERDA1_Y = 38;
    private static final int LONGITUD_EXTRA_CUERDA1 = 1;

    private static final float CUERDA2_X = 0;
    private static final float CUERDA2_Y = 18;
    private static final int LONGITUD_EXTRA_CUERDA2 = 1;

    private static final float CUERDA3_X = -8;
    private static final float CUERDA3_Y = 33;
    private static final int LONGITUD_EXTRA_CUERDA3 = 1;

    private static final float CUERDA4_X = 8;
    private static final float CUERDA4_Y = 33;
    private static final int LONGITUD_EXTRA_CUERDA4 = 1;

    private static final float CUERDA5_X = -8;
    private static final float CUERDA5_Y = 23;
    private static final int LONGITUD_EXTRA_CUERDA5 = 1;

    private static final float CUERDA6_X = 8;
    private static final float CUERDA6_Y = 23;
    private static final int LONGITUD_EXTRA_CUERDA6 = 1;
    
    private static final float ESTRELLA1_X = 8;
    private static final float ESTRELLA1_Y = 25;

    private static final float ESTRELLA2_X = -8;
    private static final float ESTRELLA2_Y = 13;

    private static final float ESTRELLA3_X = 8;
    private static final float ESTRELLA3_Y = 13;

    public Nivel7(CutTheRope juego) {
        super(juego);
    }

    @Override
    public void crearNivel(World mundo) {
        this.crearOmNom(OMNOM_X, OMNOM_Y);
        this.crearDulce(DULCE_X, DULCE_Y);
        this.crearCuerda(CUERDA1_X, CUERDA1_Y,LONGITUD_EXTRA_CUERDA1);
        this.crearCuerda(CUERDA2_X, CUERDA2_Y,LONGITUD_EXTRA_CUERDA2);
        this.crearCuerda(CUERDA3_X, CUERDA3_Y,LONGITUD_EXTRA_CUERDA3);
        this.crearCuerda(CUERDA4_X, CUERDA4_Y,LONGITUD_EXTRA_CUERDA4);
        this.crearCuerda(CUERDA5_X, CUERDA5_Y,LONGITUD_EXTRA_CUERDA5);
        this.crearCuerda(CUERDA6_X, CUERDA6_Y,LONGITUD_EXTRA_CUERDA6);
        this.crearEstrella(ESTRELLA1_X, ESTRELLA1_Y);
        this.crearEstrella(ESTRELLA2_X, ESTRELLA2_Y);
        this.crearEstrella(ESTRELLA3_X, ESTRELLA3_Y);
    }

}
