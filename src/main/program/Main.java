package main.program;

import main.scene.Window;

public class Main implements Settings {

    public static void main(String[] args)
    {
        Window window = Window.get();
        window.run();
    }
}
