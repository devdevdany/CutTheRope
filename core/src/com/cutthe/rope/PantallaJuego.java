package com.cutthe.rope;

import com.cutthe.rope.Objetos.PropiedadesObjeto;
import com.cutthe.rope.Objetos.Burbuja;
import com.cutthe.rope.Objetos.CuerdaDinamica;
import com.cutthe.rope.Objetos.Dulce;
import com.cutthe.rope.Objetos.Estrella;
import com.cutthe.rope.Objetos.OmNom;
import com.cutthe.rope.Objetos.Cuerda;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.QueryCallback;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.cutthe.rope.Objetos.Pico;
import com.cutthe.rope.Objetos.SoporteCuerda;
import java.util.ArrayList;

public abstract class PantallaJuego extends ScreenAdapter implements InputProcessor {

    /**
     * Medidas en metros. Valor por defecto, cuando se crean los niveles se
     * puede cambiar según se quiera
     */
    protected static final int ANCHO = 30;
    protected static final int ALTO = 40;

    protected static final int ANCHOCAMARA = 60;
    protected static final int ALTOCAMARA = 40;

    protected enum EstadoJuego {
        PAUSADO,
        CORRIENDO;
    }

    protected static final float CONSTANTE_GRAVEDAD = -9.8f;
    protected static final float FPS = 60f;
    protected static final float FRECUENCIA_STEP_MUNDO = 1 / FPS;
    protected static final int PRECISIONCALCULOS_CONTANCTJOINT_ITERACIONESVELOCIDAD = 6;
    protected static final int PRECISIONCALCULOS_CONTANCTJOINT_ITERACIONESPOSICION = 2;
    protected static final float CONSTANTE_TIEMPOFRAME_MAXIMO = 0.25f;

    protected static final int NUMEROESTRELLAS = 3;

    protected static final float DENSIDAD_OBJETOS = 5;
    protected static final float FRICCION_OBJETOS = 0.6f;
    protected static final float RESTITUCION_OBJETOS = 0.4f;
    protected static final int CONSTANTE_FUERZA_CUERDA = 45;
    /**
     * Valor en milisegundos
     */
    protected static final int ESPERA_FUERA_MUNDO = 250;

    protected static final int NUMERO_OBJETOS = 7;

    protected static final float RADIO_DULCE = 1.3f;

    protected static final float MITAD_ANCHOPICO = 0.5f;
    protected static final float MITAD_ALTOPICO = 0.5f;
    protected static final float ANGULO_PICO = 45;

    protected static final float RADIO_SOPORTECUERDA = 0.5f;
    protected static final float ANGULO_SOPORTECUERDA = 90f;

    protected static final float RADIO_SOPORTECUERDA_DINAMICA = 0.7f;
    protected static final float ANGULO_SOPORTECUERDA_DINAMICA = 90f;

    protected static final float MITAD_ANCHO_OMNOM = 2;
    protected static final float MITAD_ALTO_OMNOM = 2;
    protected static final float ANGULO_OMNOM = 0;

    protected static final float RADIO_ESTRELLA = 0.75f;

    protected static final float RADIO_BURBUJA = 1.75f;
    protected static final float DISTANCIA_DE_REFERENCIA_BURBUJA = 5;
    protected static final float DISTANCIA_DE_REFERENCIA_COMERDULCE = 8;

    protected enum ValoresGravedadBurbuja {
        VALOR_NORMAL(-2.25f),
        DESPUES_CORTARCUERDA(-1.5f),
        BURBUJA_DESTRUIDA(1);

        protected float valor;

        ValoresGravedadBurbuja(float valor) {
            this.valor = valor;
        }

        public float getValor() {
            return this.valor;
        }
    }

    /**
     * Damping es un valor en física para reducir las oscilaciones en un sistema
     * oscilatorio. Aquí se usa para cuando está el dulce en la burbuja, en
     * combinación a cambiar la escala de la gravedad, ayudar a que su subida o
     * caída no sea tan brusca.
     */
    protected enum ValoresDamping {
        VALOR_NORMAL(3.75f),
        DESPUES_CORTARCUERDA(3),
        BURBUJA_DESTRUIDA(0);

        protected float valor;

        ValoresDamping(float valor) {
            this.valor = valor;
        }

        public float getValor() {
            return this.valor;
        }
    }

    protected enum LimiteVelocidadAlCuadrado {
        INFERIOR(9),
        SUPERIOR(27);

        protected float valor;

        LimiteVelocidadAlCuadrado(float valor) {
            this.valor = valor;
        }

        public float getValor() {
            return this.valor;
        }
    }

    protected static final int SIN_LONGITUDEXTRA = 0;
    protected static final int SIN_ROTACION = 0;
    protected static final float SIN_FUERZA_VERTICAL = 0;
    protected static final int LONGITUD_CUERDADINAMICA = 100;

    protected static final int MITADPANTALLA_X = 0;
    protected static final int MITADPANTALLA_Y = ALTOCAMARA / 2;

    protected static final int OFFSET_FRONTERANIVEL = 5;

    protected static final float OFFSET_ESTRELLAS_OBTENIDAS_X = 2.5f;
    protected static final float OFFSET_ESTRELLAS_OBTENIDAS_Y = 3.75f;
    protected static final String PAUSAR = "PAUSAR";
    protected static final int OFFSETPAUSAR = 60;
    protected static final String REINICIAR = "REINICIAR";
    protected static final int OFFSETREINICIAR = 160;
    protected static final int ALTURABOTONES = 20;
    protected static final int ESPACIO_ENTRE_BOTONES = OFFSETPAUSAR - 10;

    protected enum IndicesObjetos {
        DULCE(0),
        OMNOM(1),
        SOPORTECUERDA(2),
        ESTRELLA(3),
        BURBUJA(4),
        PICO(5),
        SOPORTECUERDADINAMICA(6);

        private final int valor;

        IndicesObjetos(int valor) {
            this.valor = valor;
        }

        public int getValor() {
            return this.valor;
        }
    }

    protected final CutTheRope juego;
    protected World mundo;
    protected EstadoJuego estado;
    protected final Vector2 vectorGravedad;

    protected Dulce dulce;
    protected OmNom omNom;
    protected ArrayList<Cuerda> cuerdas;
    protected ArrayList<Estrella> estrellas;
    protected ArrayList<Burbuja> burbujas;
    protected ArrayList<Pico> picos;
    protected ArrayList<SoporteCuerda> soportes;
    protected ArrayList<CuerdaDinamica> cuerdasDinamicas;
    /**
     * ArrayList para almacenar un objeto de cada tipo y obtener las instancias
     * después
     */
    protected ArrayList<PropiedadesObjeto> propiedadesObjetos;

    protected int numeroEstrellasObtenidas;

    protected boolean destruirBurbuja;
    protected boolean ganador;
    protected boolean juegoTerminado;
    protected boolean reiniciarNivel;

    /**
     * Variables temporales para crear bodies para los objetos de juego
     */
    protected Fixture fixtureTemporal;
    protected FixtureDef definicionFixtureTemporal;
    protected BodyDef definicionBodyTemporal;
    protected PropiedadesObjeto nuevaPropiedadObjeto;
    /**
     * Tipo Vector3 porque camera.unproject() ocupa como parámetro un Vector3.
     * Estas variables nos sirven para convertir las coordenadas del input del
     * mouse a coordenadas del mundo en box2D
     */
    protected Vector3 coordenadasMouse;
    protected Vector2 coordenadasMundo;

    /**
     * Variables callback para iterar sobre las fixtures del mundo y detectar
     * colisiones usando queries AABB, las cuales consisten en checar si los
     * rectángulos o hitboxes alrededor de los objetos se sobreponen
     */
    protected QueryCallback callbackEstrellas;
    protected QueryCallback callbackBurbuja;
    protected QueryCallback callbackPicos;
    /**
     * Para destruir los objetos que se salgan del nivel
     */
    protected float tiempoDestruirObjetoFueraMundo;

    /**
     * Para controlar el render
     */
    protected float acumulador;

    /**
     * Booleanos para indicarle al renderer de Box2DDebugRenderer lo que va a
     * dibujar. Quitar al momento de que se pongan los gráficos
     */
    protected final boolean dibujarBodies;
    protected final boolean dibujarJoints;
    protected final boolean dibujarAABB;
    protected final boolean dibujarBodiesNoActivos;

    protected OrthographicCamera camara;
    protected Box2DDebugRenderer rendererBox2D;

    protected SpriteBatch spriteBatch;
    /**
     * Para mostrar información en pantalla. Quitar al momento de que se pongan
     * los gráficos
     */
    protected BitmapFont fuente;

    PantallaJuego(CutTheRope juego) {
        this.juego = juego;
        this.vectorGravedad = new Vector2(0, CONSTANTE_GRAVEDAD);

        this.cuerdas = new ArrayList<Cuerda>();
        this.estrellas = new ArrayList<Estrella>(NUMEROESTRELLAS);
        this.burbujas = new ArrayList<Burbuja>();
        this.picos = new ArrayList<Pico>();
        this.soportes = new ArrayList<SoporteCuerda>();
        this.cuerdasDinamicas = new ArrayList<CuerdaDinamica>();

        this.numeroEstrellasObtenidas = 0;

        this.destruirBurbuja = false;
        this.ganador = false;
        this.juegoTerminado = false;
        this.reiniciarNivel = false;

        this.fixtureTemporal = null;
        this.definicionFixtureTemporal = new FixtureDef();
        this.definicionBodyTemporal = new BodyDef();

        this.coordenadasMouse = new Vector3();
        this.coordenadasMundo = new Vector2();

        this.callbackEstrellas = new QueryCallback() {
            /**
             * @param fxtr
             * @return true para seguir iterando todas las fixtures del mundo o
             * false para detenerse
             */
            @Override
            public boolean reportFixture(Fixture fxtr) {
                for (Estrella e : PantallaJuego.this.estrellas) {
                    if (e.getBody() == fxtr.getBody()) {
                        e.setObtenida(true);
                        e.getBody().setTransform(
                                -ANCHO + OFFSET_ESTRELLAS_OBTENIDAS_X + (PantallaJuego.this.numeroEstrellasObtenidas * NUMEROESTRELLAS),
                                ALTO - OFFSET_ESTRELLAS_OBTENIDAS_Y,
                                0
                        );
                        ++PantallaJuego.this.numeroEstrellasObtenidas;
                        return false;
                    }
                }
                return true;
            }
        };
        this.callbackBurbuja = new QueryCallback() {
            /**
             * @param fxtr
             * @return true para seguir iterando todas las fixtures del mundo o
             * false para detenerse
             */
            @Override
            public boolean reportFixture(Fixture fxtr) {
                for (Burbuja b : PantallaJuego.this.burbujas) {
                    if (b.getBody() == fxtr.getBody()) {
                        b.setUsado(true);
                        PantallaJuego.this.dulce.setBurbuja(b);
                        PantallaJuego.this.dulce.getBody().setGravityScale(ValoresGravedadBurbuja.VALOR_NORMAL.getValor());
                        PantallaJuego.this.dulce.getBody().setLinearDamping(ValoresDamping.VALOR_NORMAL.getValor());
                        return false;
                    }
                }
                return true;
            }
        };
        this.callbackPicos = new QueryCallback() {
            /**
             * @param fxtr
             * @return true para seguir iterando todas las fixtures del mundo o
             * false para detenerse
             */
            @Override
            public boolean reportFixture(Fixture fxtr) {
                if (fxtr.getBody().getUserData() != null
                        && ((PropiedadesObjeto) fxtr.getBody().getUserData()).esPico()) {
                    PantallaJuego.this.juegoTerminado = true;
                    PantallaJuego.this.dulce.getBody().setType(BodyType.StaticBody);
                    return false;
                }
                return true;
            }
        };

        this.acumulador = 0;

        this.dibujarBodies = true;
        this.dibujarJoints = false;
        this.dibujarAABB = false;
        this.dibujarBodiesNoActivos = true;
    }

    /**
     * Sobreescribir en las clases Niveles para posicionar los objetos
     *
     * @param mundo
     */
    public abstract void crearNivel(World mundo);

    public void crearObjetos() {
        this.propiedadesObjetos = new ArrayList<PropiedadesObjeto>(NUMERO_OBJETOS);
        //Dulce
        this.propiedadesObjetos.add(new PropiedadesObjeto(RADIO_DULCE, BodyType.DynamicBody));
        this.propiedadesObjetos.get(IndicesObjetos.DULCE.getValor()).setDulce(true);
        //Om Nom
        this.propiedadesObjetos.add(new PropiedadesObjeto(MITAD_ANCHO_OMNOM, MITAD_ALTO_OMNOM, ANGULO_OMNOM, BodyType.StaticBody));
        this.propiedadesObjetos.get(IndicesObjetos.OMNOM.getValor()).setSensor(true);
        //Soporte cuerda
        this.propiedadesObjetos.add(new PropiedadesObjeto(RADIO_SOPORTECUERDA, BodyType.StaticBody));
        this.propiedadesObjetos.get(IndicesObjetos.SOPORTECUERDA.getValor()).setSensor(true);
        this.propiedadesObjetos.get(IndicesObjetos.SOPORTECUERDA.getValor()).setAngulo(ANGULO_SOPORTECUERDA);
        //Estrella
        this.propiedadesObjetos.add(new PropiedadesObjeto(RADIO_ESTRELLA, BodyType.KinematicBody));
        this.propiedadesObjetos.get(IndicesObjetos.ESTRELLA.getValor()).setSensor(true);
        //Burbuja
        this.propiedadesObjetos.add(new PropiedadesObjeto(RADIO_BURBUJA, BodyType.KinematicBody));
        this.propiedadesObjetos.get(IndicesObjetos.BURBUJA.getValor()).setSensor(true);
        //Pico
        this.propiedadesObjetos.add(new PropiedadesObjeto(MITAD_ANCHOPICO, MITAD_ALTOPICO, ANGULO_PICO, BodyType.KinematicBody));
        this.propiedadesObjetos.get(IndicesObjetos.PICO.getValor()).setPico(true);
        //Soporte cuerda dinámica
        this.propiedadesObjetos.add(new PropiedadesObjeto(RADIO_SOPORTECUERDA_DINAMICA, BodyType.StaticBody));
        this.propiedadesObjetos.get(IndicesObjetos.SOPORTECUERDADINAMICA.getValor()).setSensor(true);
        this.propiedadesObjetos.get(IndicesObjetos.SOPORTECUERDADINAMICA.getValor()).setAngulo(ANGULO_SOPORTECUERDA_DINAMICA);
    }

    public PropiedadesObjeto obtenerObjeto(int indiceObjeto) {
        return new PropiedadesObjeto(this.propiedadesObjetos.get(indiceObjeto));
    }

    public void crearNuevoBody(PropiedadesObjeto propiedadesObjeto, float posX, float posY) {
        propiedadesObjeto.getVectorPosicion().x = posX;
        propiedadesObjeto.getVectorPosicion().y = posY;
        this.definicionBodyTemporal.position.x = posX;
        this.definicionBodyTemporal.position.y = posY;
        this.definicionBodyTemporal.type = propiedadesObjeto.getTipoBody();
        this.definicionBodyTemporal.angle = propiedadesObjeto.getAngulo();
        Body nuevoBody = this.mundo.createBody(this.definicionBodyTemporal);
        if (nuevoBody.getType() == BodyType.StaticBody) {
            this.definicionFixtureTemporal.shape = propiedadesObjeto.getForma();
            this.fixtureTemporal = nuevoBody.createFixture(this.definicionFixtureTemporal);
        } else {
            this.fixtureTemporal = nuevoBody.createFixture(propiedadesObjeto.getForma(), DENSIDAD_OBJETOS);
            this.fixtureTemporal.setFriction(FRICCION_OBJETOS);
            this.fixtureTemporal.setRestitution(RESTITUCION_OBJETOS);
        }
        this.fixtureTemporal.setSensor(propiedadesObjeto.getSensor());
        propiedadesObjeto.setBody(nuevoBody);
        /**
         * User Data para poder obtener información sobre el objeto
         * posteriormente. Por ejemplo al momento de checar su tipo en una
         * colisión
         */
        if (nuevoBody.getType() == BodyType.DynamicBody || nuevoBody.getType() == BodyType.KinematicBody) {
            this.fixtureTemporal.getBody().setUserData(new PropiedadesObjeto(propiedadesObjeto));
        }
    }

    public void crearDulce(float posX, float posY) {
        this.nuevaPropiedadObjeto = obtenerObjeto(IndicesObjetos.DULCE.getValor());
        crearNuevoBody(this.nuevaPropiedadObjeto, posX, posY);
        this.dulce = new Dulce(this.nuevaPropiedadObjeto, this.nuevaPropiedadObjeto.getBody());
    }

    public void crearOmNom(float posX, float posY) {
        this.nuevaPropiedadObjeto = obtenerObjeto(IndicesObjetos.OMNOM.getValor());
        crearNuevoBody(this.nuevaPropiedadObjeto, posX, posY);
        this.omNom = new OmNom(this.nuevaPropiedadObjeto, this.nuevaPropiedadObjeto.getBody());
    }

    public void crearCuerda(float posX, float posY, int longitudExtra, boolean cuerdaElastica) {
        this.nuevaPropiedadObjeto = obtenerObjeto(IndicesObjetos.SOPORTECUERDA.getValor());
        crearNuevoBody(this.nuevaPropiedadObjeto, posX, posY);
        this.cuerdas.add(new Cuerda(this.nuevaPropiedadObjeto.getBody(), this.dulce.getBody(), this.mundo, longitudExtra, cuerdaElastica));
        this.soportes.add(new SoporteCuerda(this.nuevaPropiedadObjeto, this.nuevaPropiedadObjeto.getBody()));
    }

    //Para las cuerdas dinámicas
    public void crearCuerda(Body cuerdaDinamica, int longitudExtra) {
        this.cuerdas.add(new Cuerda(cuerdaDinamica, this.dulce.getBody(), this.mundo, longitudExtra, false));
    }

    public void crearCuerda(float posX, float posY, int longitudExtra) {
        this.crearCuerda(posX, posY, longitudExtra, false);
    }

    public void crearCuerda(float posX, float posY) {
        this.crearCuerda(posX, posY, SIN_LONGITUDEXTRA);
    }

    public void crearEstrella(float posX, float posY) {
        this.nuevaPropiedadObjeto = obtenerObjeto(IndicesObjetos.ESTRELLA.getValor());
        crearNuevoBody(this.nuevaPropiedadObjeto, posX, posY);
        this.estrellas.add(new Estrella(this.nuevaPropiedadObjeto, this.nuevaPropiedadObjeto.getBody()));
    }

    public void crearBurbuja(float posX, float posY) {
        this.nuevaPropiedadObjeto = obtenerObjeto(IndicesObjetos.BURBUJA.getValor());
        crearNuevoBody(this.nuevaPropiedadObjeto, posX, posY);
        this.burbujas.add(new Burbuja(this.nuevaPropiedadObjeto, this.nuevaPropiedadObjeto.getBody()));
    }

    public void crearPicoSencillo(float posX, float posY) {
        this.nuevaPropiedadObjeto = obtenerObjeto(IndicesObjetos.PICO.getValor());
        crearNuevoBody(this.nuevaPropiedadObjeto, posX, posY);
        this.picos.add(new Pico(this.nuevaPropiedadObjeto, this.nuevaPropiedadObjeto.getBody()));
    }

    public void crearCuerdaDinamica(float posX, float posY, float radio) {
        this.nuevaPropiedadObjeto = obtenerObjeto(IndicesObjetos.SOPORTECUERDADINAMICA.getValor());
        crearNuevoBody(this.nuevaPropiedadObjeto, posX, posY);
        this.cuerdasDinamicas.add(new CuerdaDinamica(this.nuevaPropiedadObjeto, this.nuevaPropiedadObjeto.getBody(), radio, this.mundo));
    }

    /**
     * Lógica de las interacciones entre los objetos del juego
     *
     * @param deltaStepMundo el time step del mundo en box2D, en milisegundos
     */
    public void logicaJuego(float deltaStepMundo) {
        if (this.juegoTerminado) {
            this.reiniciarNivel = true;
        }
        if (this.reiniciarNivel) {
            this.numeroEstrellasObtenidas = 0;
            this.reiniciarNivel = false;
            this.juegoTerminado = false;
            this.ganador = false;
            /**
             * Destello
             */
            this.juego.reiniciar();
        }
        if (this.ganador) {
            this.reiniciarNivel = false;
            this.juegoTerminado = false;
            /**
             * Añadir pantalla de transición -> pantalla de ganador -> boton
             * para iniciar siguiente nivel
             */
            this.juego.iniciarSiguienteNivel();
        }

        if (this.dulce != null) {

            //Revisar si el dulce llega a OmNom
            if (this.omNom != null && this.omNom.getBody().getPosition().dst2(this.dulce.getBody().getPosition()) < DISTANCIA_DE_REFERENCIA_COMERDULCE) {
                this.ganador = true;
                this.dulce.getBody().setType(BodyType.StaticBody);
                /**
                 * Destruir las cuerdas existentes
                 */
                for (Cuerda c : this.cuerdas) {
                    c.destruir();
                }
                return;
            }

            //Revisar si el dulce sale del nivel
            if ((this.dulce.getBody().getPosition().x > (ANCHO + OFFSET_FRONTERANIVEL))
                    || (this.dulce.getBody().getPosition().x < (-ANCHO - OFFSET_FRONTERANIVEL))
                    || (this.dulce.getBody().getPosition().y > (ALTO + OFFSET_FRONTERANIVEL))
                    || (this.dulce.getBody().getPosition().y < (-OFFSET_FRONTERANIVEL))) {

                this.juegoTerminado = true;
                return;
            }

            if (!this.cuerdas.isEmpty()) {
                /**
                 * Aplicar fuerza en dirección horizontal al centro del dulce si
                 * está colgando de una cuerda
                 */
                float velocidadLinearCuadrada = this.dulce.getBody().getLinearVelocity().len2();
                if (velocidadLinearCuadrada > LimiteVelocidadAlCuadrado.INFERIOR.getValor()
                        && velocidadLinearCuadrada > LimiteVelocidadAlCuadrado.SUPERIOR.getValor()) {
                    this.dulce.getBody().applyForceToCenter(this.dulce.getBody().getLinearVelocity().x * deltaStepMundo * (CONSTANTE_FUERZA_CUERDA + CONSTANTE_FUERZA_CUERDA * this.cuerdas.size()),
                            SIN_FUERZA_VERTICAL,
                            true
                    );
                }
            }

            /**
             * Coordenadas x y de la esquina inferior izquierda del rectángulo
             * AABB
             */
            float xInferiorRectanguloAABB = this.dulce.getBody().getPosition().x - RADIO_DULCE;
            float yInferiorRectanguloAABB = this.dulce.getBody().getPosition().y - RADIO_DULCE;
            /**
             * Coordenadas x y de la esquina superior derecha del rectángulo
             * AABB
             */
            float xSuperiorRectanguloAABB = this.dulce.getBody().getPosition().x + RADIO_DULCE;
            float ySuperiorRectanguloAABB = this.dulce.getBody().getPosition().y + RADIO_DULCE;

            /**
             * Colisiones estrellas
             */
            this.mundo.QueryAABB(
                    this.callbackEstrellas,
                    xInferiorRectanguloAABB,
                    yInferiorRectanguloAABB,
                    xSuperiorRectanguloAABB,
                    ySuperiorRectanguloAABB
            );

            /**
             * Colisiones burbujas. Si existen burbujas en el nivel pero
             * actualmente el dulce no está en una
             */
            if (!this.burbujas.isEmpty() && this.dulce.getBurbuja() == null) {
                this.mundo.QueryAABB(
                        this.callbackBurbuja,
                        xInferiorRectanguloAABB,
                        yInferiorRectanguloAABB,
                        xSuperiorRectanguloAABB,
                        ySuperiorRectanguloAABB
                );
            } //Si no, si el dulce está dentro de una burbuja, actualizar la posición de la burbuja
            else if (this.dulce.getBurbuja() != null) {
                this.dulce.getBurbuja().getBody().setTransform(this.dulce.getBody().getPosition(), SIN_ROTACION);
            }
            /**
             * Si el dulce está dentro de una burbuja, y la bandera para
             * destruir la burbuja está activa
             */
            if (this.dulce.getBurbuja() != null && this.destruirBurbuja) {
                this.burbujas.remove(this.dulce.getBurbuja());
                this.mundo.destroyBody(this.dulce.getBurbuja().getBody());
                this.dulce.setBurbuja(null);
                this.dulce.getBody().setGravityScale(ValoresGravedadBurbuja.BURBUJA_DESTRUIDA.getValor());
                this.dulce.getBody().setLinearDamping(ValoresDamping.BURBUJA_DESTRUIDA.getValor());
            }
            /**
             * Colisiones picos
             */
            this.mundo.QueryAABB(
                    this.callbackPicos,
                    xInferiorRectanguloAABB,
                    yInferiorRectanguloAABB,
                    xSuperiorRectanguloAABB,
                    ySuperiorRectanguloAABB
            );
            /**
             * Revisar si el dulce se acerca y activar la cuerda dinámica
             */
            for (CuerdaDinamica cd : this.cuerdasDinamicas) {
                if (cd.revisarDistanciaDulce(this.dulce.getBody().getPosition()) && !cd.getUsado()) {
                    this.crearCuerda(cd.getBody(), LONGITUD_CUERDADINAMICA + (int) cd.getRadioAtrapar());
                    cd.setUsado(true);
                }
            }
        }

        /**
         * Destruir objetos que se salgan del nivel
         */
        this.tiempoDestruirObjetoFueraMundo += deltaStepMundo;
        if (this.tiempoDestruirObjetoFueraMundo >= ESPERA_FUERA_MUNDO) {
            this.tiempoDestruirObjetoFueraMundo = 0;
            Array<Body> bodies = new Array<Body>();
            this.mundo.getBodies(bodies);
            for (Body b : bodies) {
                if ((b.getPosition().x > (ANCHO + OFFSET_FRONTERANIVEL))
                        || (b.getPosition().x < (-ANCHO - OFFSET_FRONTERANIVEL))
                        || (b.getPosition().y > (ALTO + OFFSET_FRONTERANIVEL))
                        || (b.getPosition().y < (-OFFSET_FRONTERANIVEL))) {
                    this.mundo.destroyBody(b);
                }
            }
        }
    }

    @Override
    public void render(float delta) {
        switch (this.estado) {
            case CORRIENDO:
                Gdx.gl.glClearColor(0f, 0f, 0f, 0f);
                Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

                this.camara.update();

                this.rendererBox2D.render(this.mundo, this.camara.combined);

                this.spriteBatch.begin();
                //Cambiar textos de pausar y reiniciar por botones
                this.fuente.draw(this.spriteBatch, PAUSAR, Gdx.graphics.getWidth() - OFFSETPAUSAR, Gdx.graphics.getHeight());
                this.fuente.draw(this.spriteBatch, REINICIAR, Gdx.graphics.getWidth() - OFFSETREINICIAR, Gdx.graphics.getHeight());
                this.spriteBatch.end();

                /**
                 * Tiempo de step constante y tiempo de frame máximo para evitar
                 * "espiral de la muerte". Por si se realizan steps en tiempos
                 * intermedios o, en otras palabras, por si no se alcanza a
                 * completar el step a tiempo
                 */
                float tiempoFrame = Math.min(delta, CONSTANTE_TIEMPOFRAME_MAXIMO);
                this.acumulador += tiempoFrame;
                while (this.acumulador >= FRECUENCIA_STEP_MUNDO) {
                    this.mundo.step(FRECUENCIA_STEP_MUNDO, PRECISIONCALCULOS_CONTANCTJOINT_ITERACIONESVELOCIDAD, PRECISIONCALCULOS_CONTANCTJOINT_ITERACIONESPOSICION);
                    this.logicaJuego(FRECUENCIA_STEP_MUNDO);
                    this.acumulador -= FRECUENCIA_STEP_MUNDO;
                }
                break;
            case PAUSADO:
                /**
                 * Pantalla pausa -> cuando renaude llamar a this.resume()
                 */
                break;
            default:
                break;
        }
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void show() {
        this.mundo = new World(this.vectorGravedad, true);

        this.crearObjetos();

        this.camara = new OrthographicCamera(ANCHOCAMARA, ALTOCAMARA);
        this.camara.position.set(MITADPANTALLA_X, MITADPANTALLA_Y, 0);
        this.rendererBox2D = new Box2DDebugRenderer(this.dibujarBodies, this.dibujarJoints, this.dibujarAABB, this.dibujarBodiesNoActivos, false, false);

        this.crearNivel(this.mundo);

        this.spriteBatch = new SpriteBatch();
        this.fuente = new BitmapFont();

        Gdx.input.setInputProcessor(this);

        this.tiempoDestruirObjetoFueraMundo = 0;

        this.estado = EstadoJuego.CORRIENDO;
    }

    @Override
    public void hide() {
        this.estado = EstadoJuego.PAUSADO;
        this.dispose();
    }

    @Override
    public void pause() {
        this.estado = EstadoJuego.PAUSADO;
    }

    @Override
    public void resume() {
        this.estado = EstadoJuego.CORRIENDO;
    }

    @Override
    public void dispose() {
        this.propiedadesObjetos.clear();
        this.cuerdas.clear();
        this.estrellas.clear();
        this.burbujas.clear();
        this.picos.clear();
        this.soportes.clear();
        this.cuerdasDinamicas.clear();
        /**
         * Cuando se haga la pantalla de ganador, descomentar
         */
        //this.spriteBatch.dispose();
        //this.mundo.dispose();
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int x, int y, int pointer, int button) {
        //Pausar y reiniciar, cambiar por botones
        if (x > (Gdx.graphics.getWidth() - OFFSETPAUSAR) && y < ALTURABOTONES) {
            this.pause();
        }
        if (x > (Gdx.graphics.getWidth() - OFFSETREINICIAR) && x < (Gdx.graphics.getWidth() - ESPACIO_ENTRE_BOTONES) && y < ALTURABOTONES) {
            this.reiniciarNivel = true;
        }

        this.camara.unproject(this.coordenadasMouse.set(x, y, 0));
        this.coordenadasMundo.set(this.coordenadasMouse.x, this.coordenadasMouse.y);

        //Activar bandera para reventar burbuja
        if (this.dulce.getBurbuja() != null && this.dulce.getBurbuja().getBody().getPosition().dst2(this.coordenadasMundo) < DISTANCIA_DE_REFERENCIA_BURBUJA) {
            this.destruirBurbuja = true;
        }

        return false;
    }

    @Override
    public boolean touchUp(int x, int y, int pointer, int button) {
        this.camara.unproject(this.coordenadasMouse.set(x, y, 0));
        this.coordenadasMundo.set(this.coordenadasMouse.x, this.coordenadasMouse.y);

        this.destruirBurbuja = false;

        return false;
    }

    @Override
    public boolean touchDragged(int x, int y, int pointer) {
        this.camara.unproject(this.coordenadasMouse.set(x, y, 0));
        this.coordenadasMundo.set(this.coordenadasMouse.x, this.coordenadasMouse.y);

        /**
         * Destruir la cuerda cortada
         */
        if (!this.destruirBurbuja) {
            Cuerda cuerdaTemporal = null;
            for (Cuerda c : this.cuerdas) {
                if (c.revisarDistanciaMouse(this.coordenadasMundo)) {
                    cuerdaTemporal = c;
                }
            }
            if (cuerdaTemporal != null) {
                this.cuerdas.remove(cuerdaTemporal);
                cuerdaTemporal.destruir();
                if (this.dulce.getBurbuja() != null) {
                    /**
                     * Para que flote el dulce dentro de la burbuja
                     */
                    this.dulce.getBody().setGravityScale(ValoresGravedadBurbuja.DESPUES_CORTARCUERDA.getValor());
                    this.dulce.getBody().setLinearDamping(ValoresDamping.DESPUES_CORTARCUERDA.getValor());
                }
            }
        }
        return false;
    }

    @Override
    public boolean mouseMoved(int x, int y) {
        this.camara.unproject(this.coordenadasMouse.set(x, y, 0));
        this.coordenadasMundo.set(this.coordenadasMouse.x, this.coordenadasMouse.y);

        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}