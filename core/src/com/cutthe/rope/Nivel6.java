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
public class Nivel6 extends PantallaJuego {

    private static final float OMNOM_X = 0;
    private static final float OMNOM_Y = 3;

    private static final float ESTRELLA1_X = 0;
    private static final float ESTRELLA1_Y = 10;

    private static final float ESTRELLA2_X = 0;
    private static final float ESTRELLA2_Y = 18;

    private static final float ESTRELLA3_X = 0;
    private static final float ESTRELLA3_Y = 35;

    private static final float DULCE_X = 0;
    private static final float DULCE_Y = 23;

    private static final float CUERDA_X = 0;
    private static final float CUERDA_Y = 30;

    public Nivel6(CutTheRope juego) {
        super(juego);
    }

    @Override
    public void crearNivel(World mundo) {
        this.crearOmNom(OMNOM_X, OMNOM_Y);
        this.crearEstrella(ESTRELLA1_X, ESTRELLA1_Y);
        this.crearEstrella(ESTRELLA2_X, ESTRELLA2_Y);
        this.crearBurbuja(ESTRELLA2_X, ESTRELLA2_Y);
        this.crearEstrella(ESTRELLA3_X, ESTRELLA3_Y);
        this.crearDulce(DULCE_X, DULCE_Y);
        this.crearCuerda(CUERDA_X, CUERDA_Y);
    }

}
