package com.cutthe.rope;

import com.badlogic.gdx.physics.box2d.World;

public class Nivel5 extends PantallaJuego {

    private static final float OMNOM_X = 6;
    private static final float OMNOM_Y = 6;

    private static final float ESTRELLA1_X = 6;
    private static final float ESTRELLA1_Y = 35;

    private static final float ESTRELLA2_X = 6;
    private static final float ESTRELLA2_Y = 25;

    private static final float ESTRELLA3_X = -6;
    private static final float ESTRELLA3_Y = 17;

    private static final float DULCE_X = -6;
    private static final float DULCE_Y = 22;

    private static final float CUERDA1_X = -6;
    private static final float CUERDA1_Y = 31;
    private static final int LONGITUD_EXTRA_CUERDA1 = 2;

    private static final float CUERDA2_X = 6;
    private static final float CUERDA2_Y = 22;
    private static final int LONGITUD_EXTRA_CUERDA2 = 4;

    private static final float BURBUJA_X = -6;
    private static final float BURBUJA_Y = 12;

    Nivel5(CutTheRope juego) {
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
        this.crearBurbuja(BURBUJA_X, BURBUJA_Y);
    }
}
