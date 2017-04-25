package com.cutthe.rope.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.cutthe.rope.CutTheRope;

public class DesktopLauncher {

    private static final String TITULO = "Cut The Rope";
    private static final int ANCHO = 1000;
    private static final int ALTO = 600;

    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        
        config.title = TITULO;
        
        config.width = ANCHO;
        config.height = ALTO;
        
        config.resizable = false;
        new LwjglApplication(new CutTheRope(), config);
    }
}
