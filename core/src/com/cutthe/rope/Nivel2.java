package com.cutthe.rope;

import com.badlogic.gdx.physics.box2d.World;

public class Nivel2 extends PantallaJuego {

    private static final float OMNOM_X = 10;
    private static final float OMNOM_Y = 4;

    private static final float ESTRELLA1_X = -10;
    private static final float ESTRELLA1_Y = 21;

    private static final float ESTRELLA2_X = 10;
    private static final float ESTRELLA2_Y = 21;

    private static final float ESTRELLA3_X = -10;
    private static final float ESTRELLA3_Y = 13;

    private static final float DULCE_X = -10;
    private static final float DULCE_Y = 24;

    private static final float CUERDA1_X = -10;
    private static final float CUERDA1_Y = 32;
    
    private static final float CUERDA2_X = 0;
    private static final float CUERDA2_Y = 32;
    private static final int LONGITUD_EXTRA_CUERDA2 = 4;
    
    private static final float CUERDA3_X = 10;
    private static final float CUERDA3_Y = 32;
    private static final int LONGITUD_EXTRA_CUERDA3 = 9;

    Nivel2(CutTheRope juego) {
        super(juego);
    }

    @Override
    public void crearNivel(World mundo) {
        this.crearOmNom(OMNOM_X, OMNOM_Y);
        this.crearEstrella(ESTRELLA1_X, ESTRELLA1_Y);
        this.crearEstrella(ESTRELLA2_X, ESTRELLA2_Y);
        this.crearEstrella(ESTRELLA3_X, ESTRELLA3_Y);
        this.crearDulce(DULCE_X, DULCE_Y);
        this.crearCuerda(CUERDA1_X, CUERDA1_Y);
        this.crearCuerda(CUERDA2_X, CUERDA2_Y, LONGITUD_EXTRA_CUERDA2);
        this.crearCuerda(CUERDA3_X, CUERDA3_Y, LONGITUD_EXTRA_CUERDA3);
    }

}
