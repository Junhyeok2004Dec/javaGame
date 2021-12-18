package main;


import main.util.Time;
import org.lwjgl.Version;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.NULL;

public class Window {
    private int width, height;
    private String title;
    private long glfwWindow;

    //color
    public float r,g,b,a;
    private boolean fadeBlack = false;

    private static Window window = null;

    //Scene(Stage 구현 등)
    private static Scene currentScene;



    private Window() {
        this.width = 1280;
        this.height = 720;
        this.title = "Jeil RPG";
        r = 1;
        g = 1;
        b = 1;
        a = 1;
    }

        public static void changeScene(int newScene) {

            switch (newScene) {
                case 0:
                    currentScene = new LevelScene();
                    //currentScene.init();
                    break;
                default:
                    assert false : "Unknown scene / levels'" + newScene + "'";
                    break;

            }

        }







    public static Window get() {

        if (Window.window == null){
            Window.window = new Window();


        }
        return Window.window;
    }



    public void run() {

        System.out.println("LWJGL version : " + Version.getVersion());

        init();
        loop();


        // free memory
        glfwFreeCallbacks(glfwWindow);
        glfwDestroyWindow(glfwWindow);

        // terminate glfw , free err callback
        glfwTerminate();
        glfwSetErrorCallback(null).free();

    }

    public void init() {
        // Setup Err Callback


        GLFWErrorCallback.createPrint(System.err).set();

        // Initialize GLFW
        if (!glfwInit()) {
            throw new IllegalStateException("Unable to initialize GLFW");

        }


        // GLFW configure
        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);
        glfwWindowHint(GLFW_MAXIMIZED, GLFW_TRUE);



        // Window Create

        glfwWindow = glfwCreateWindow(this.width, this.height, this.title, NULL, NULL);

        if (glfwWindow == NULL) {
            throw new IllegalStateException("Failed to create the GLFW window");
        }

        //MouseListener
        glfwSetCursorPosCallback(glfwWindow, MouseListener::mousePosCallback);
        glfwSetMouseButtonCallback(glfwWindow, MouseListener::mouseButtonCallback);
        glfwSetScrollCallback(glfwWindow, MouseListener::mouseScrollCallback);

        //KeyListener
        glfwSetKeyCallback(glfwWindow, KeyListener::KeyCallback);


        glfwMakeContextCurrent(glfwWindow);
        glfwSwapInterval(1);
        glfwShowWindow(glfwWindow);

        GL.createCapabilities();



    }

    public void loop() {
        float beginTime = Time.getTime();
        float endTime = Time.getTime();

        while (!glfwWindowShouldClose(glfwWindow))
        {
            glfwPollEvents();



            glClearColor(r, g, b, a);
            glClear(GL_COLOR_BUFFER_BIT);

            if (fadeBlack) {
                r = Math.max(r - 0.01f, 0);
                g = Math.max(g - 0.01f, 0);
                b = Math.max(b - 0.01f, 0);
                a = Math.max(a - 0.01f, 0);

            }

            if (KeyListener.isKeyPressed(GLFW_KEY_SPACE)) {
                fadeBlack = true;

            }

            glfwSwapBuffers(glfwWindow);

            // Time management
            endTime = Time.getTime();
            float dt = endTime - beginTime;
            beginTime = endTime;
        }

    }


}
