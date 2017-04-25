package com.cutthe.rope;

import com.badlogic.gdx.physics.box2d.World;

public class Nivel3 extends PantallaJuego {

    private static final float OMNOM_X = 9;
    private static final float OMNOM_Y = 4;

    private static final float ESTRELLA1_X = -9;
    private static final float ESTRELLA1_Y = 21;

    private static final float ESTRELLA2_X = -12;
    private static final float ESTRELLA2_Y = 15;

    private static final float ESTRELLA3_X = 0;
    private static final float ESTRELLA3_Y = 4;

    private static final float DULCE_X = 7;
    private static final float DULCE_Y = 25;

    private static final float CUERDA1_X = 0;
    private static final float CUERDA1_Y = 30;
    private static final int LONGITUD_EXTRA_CUERDA1 = 3;
    
    private static final float CUERDA2_X = 11;
    private static final float CUERDA2_Y = 30;
    private static final int LONGITUD_EXTRA_CUERDA2 = 1;
    
    private static final float CUERDA3_X = 0;
    private static final float CUERDA3_Y = 17;
    private static final int LONGITUD_EXTRA_CUERDA3 = 4;

    Nivel3(CutTheRope juego) {
        super(juego);
    }

    @Override
    public void crearNivel(World mundo) {
        this.crearOmNom(OMNOM_X, OMNOM_Y);
        this.crearEstrella(ESTRELLA1_X, ESTRELLA1_Y);
        this.crearEstrella(ESTRELLA2_X, ESTRELLA2_Y);
        this.crearEstrella(ESTRELLA3_X, ESTRELLA3_Y);
        this.crearDulce(DULCE_X, DULCE_Y);
        this.crearCuerda(CUERDA1_X, CUERDA1_Y, LONGITUD_EXTRA_CUERDA1);
        this.crearCuerda(CUERDA2_X, CUERDA2_Y, LONGITUD_EXTRA_CUERDA2);
        this.crearCuerda(CUERDA3_X, CUERDA3_Y, LONGITUD_EXTRA_CUERDA3);
    }

}
