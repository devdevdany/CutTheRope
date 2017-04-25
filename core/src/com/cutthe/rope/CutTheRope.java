package com.cutthe.rope;

import com.badlogic.gdx.Game;

public class CutTheRope extends Game {

    private static final int PRIMERNIVEL = 11;

    private PantallaJuego nivel;
    private int numeroNivel;

    @Override
    public void create() {
        this.iniciarNivel(PRIMERNIVEL);
    }

    public void iniciarNivel(int numeroNivel) {
        switch (numeroNivel) {
            case 1:
                this.nivel = new Nivel1(this);
                break;
            case 2:
                this.nivel = new Nivel2(this);
                break;
            case 3:
                this.nivel = new Nivel3(this);
                break;
            case 4:
                this.nivel = new Nivel4(this);
                break;
            case 5:
                this.nivel = new Nivel5(this);
                break;
            case 6:
                this.nivel = new Nivel6(this);
                break;
            case 7:
                this.nivel = new Nivel7(this);
                break;
            case 8:
                this.nivel = new Nivel8(this);
                break;
            case 9:
                this.nivel = new Nivel9(this);
                break;
            case 10:
                this.nivel = new Nivel10(this);
                break;
            case 11:
                this.nivel = new Nivel11(this);
                break;
            case 12:
                this.nivel = new Nivel12(this);
                break;
            default:
                break;
        }
        this.numeroNivel = numeroNivel;
        if (this.nivel != null) {
            this.setScreen(this.nivel);
        }
    }

    public void iniciarSiguienteNivel() {
        this.iniciarNivel(++this.numeroNivel);
    }

    public void reiniciar() {
        this.setScreen(this.nivel);
    }
}
