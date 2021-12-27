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

    private void colorReset() {
        r = 1;
        g = 1;
        b = 1;
        a = 1;
    }


    private Window() {
        this.width = 1280;
        this.height = 720;
        this.title = "Jeil RPG";
        colorReset();

    }

        public static void changeScene(int newScene) {



        /*
        Scene 구성

        -1 : default(씬 없음)
        0 : 메인화면
        41 : data창
        42 : map 창
        99 : 옵션창
         */



            switch (newScene) {
                case 0:
                    currentScene = new MainScene();
                    currentScene.init();
                    currentScene.start();
                    break;
                case 41:
                    currentScene = new OptionScene();
                    currentScene.init();
                    currentScene.start();
                    break;

                case 42:
                    currentScene = new MapScene();
                    currentScene.init();
                    currentScene.start();
                    break;

                case 99:
                    currentScene = new OptionScene();
                    currentScene.init();
                    currentScene.start();
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

    public static Scene getScene() {
        return get().currentScene;


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
        glfwWindowHint(GLFW_RESIZABLE, GLFW_FALSE);
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

        //Vsync Mode
        glfwSwapInterval(1);

        //Window 보이게
        glfwShowWindow(glfwWindow);

        GL.createCapabilities();

        Window.changeScene(0);



    }

    public void loop() {
        float beginTime = Time.getTime();
        float endTime;
        float dt = -1.0f;

        while (!glfwWindowShouldClose(glfwWindow))
        {
            glfwPollEvents();



            //색 설정
            glClearColor(r, g, b, a);


            glClear(GL_COLOR_BUFFER_BIT);



            // dt에 따른 시간 변화 (scene update by dt)
            if (dt >= 0) {
                currentScene.update(dt);
            }



            //graphic work -- 하얀색 -> 검정색 변화


            if (KeyListener.isKeyPressed(GLFW_KEY_SPACE)) {
                changeScene(0   );

            }

            glfwSwapBuffers(glfwWindow);






            // Time management
            endTime = Time.getTime();
            dt = endTime - beginTime;
            beginTime = endTime;
        }

    }


}
