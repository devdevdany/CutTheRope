package com.cutthe.rope;

import com.badlogic.gdx.physics.box2d.World;

public class Nivel11 extends PantallaJuego {

    private static final float OMNOM_X = 0;
    private static final float OMNOM_Y = 4;

    private static final float ESTRELLA1_X = 8;
    private static final float ESTRELLA1_Y = 25;

    private static final float ESTRELLA2_X = -8;
    private static final float ESTRELLA2_Y = 15;

    private static final float ESTRELLA3_X = 8;
    private static final float ESTRELLA3_Y = 15;

    private static final float DULCE_X = -8;
    private static final float DULCE_Y = 26;

    private static final float CUERDA1_X = -8;
    private static final float CUERDA1_Y = 35;
    private static final int LONGITUD_EXTRA_CUERDA1 = 2;

    private static final float CUERDA2_X = 0;
    private static final float CUERDA2_Y = 35;
    private static final int LONGITUD_EXTRA_CUERDA2 = 3;

    private static final float CUERDA3_X = 0;
    private static final float CUERDA3_Y = 25;
    private static final int LONGITUD_EXTRA_CUERDA3 = 3;

    private static final float CUERDA4_X = 0;
    private static final float CUERDA4_Y = 16;
    private static final int LONGITUD_EXTRA_CUERDA4 = 1;

    private static final int NUMEROPICOS = 5;
    private static final float DISTANCIAPICOS = MITAD_ANCHOPICO * 2;
    private static final float POS_PICOS1_X = -3;
    private static final float POS_PICOS1_Y = 18;
    private static final float POS_PICOS2_X = -3;
    private static final float POS_PICOS2_Y = 11;

    Nivel11(CutTheRope juego) {
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
        this.crearCuerda(CUERDA4_X, CUERDA4_Y, LONGITUD_EXTRA_CUERDA4);
        for (int i = 0; i < NUMEROPICOS; ++i) {
            this.crearPicoSencillo(POS_PICOS1_X + (i * DISTANCIAPICOS), POS_PICOS1_Y);
            this.crearPicoSencillo(POS_PICOS2_X + (i * DISTANCIAPICOS), POS_PICOS2_Y);
        }
    }
}
