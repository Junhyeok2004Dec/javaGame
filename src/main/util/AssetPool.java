package main.util;

import main.assets.Shader;
import main.assets.Texture;
import main.assets.components.SpriteSheet;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class AssetPool {
    private static Map<String, Shader> shaders = new HashMap<>();
    private static Map<String, Texture> textures = new HashMap<>();
    private static Map<String, SpriteSheet> spritesheets = new HashMap<>();

    public static Shader getShader(String resourceName) {
        File file = new File(resourceName);
        if (AssetPool.shaders.containsKey(file.getAbsolutePath())) {
            return AssetPool.shaders.get(file.getAbsolutePath());
        } else {
            Shader shader = new Shader(resourceName);
            shader.compile();
            AssetPool.shaders.put(file.getAbsolutePath(), shader);
            return shader;
        }
    }

    public static Texture getTexture(String resourceName) {
        File file = new File(resourceName);
        if (AssetPool.textures.containsKey(file.getAbsolutePath())) {
            return AssetPool.textures.get(file.getAbsolutePath());
        } else {
            Texture texture = new Texture(resourceName);
            AssetPool.textures.put(file.getAbsolutePath(), texture);
            return texture;
        }
    }

    public static void addSpritesheet(String resourceName, SpriteSheet spritesheet) {
        File file = new File(resourceName);
        if (!AssetPool.spritesheets.containsKey(file.getAbsolutePath())) {
            AssetPool.spritesheets.put(file.getAbsolutePath(), spritesheet);
        }
    }

    public static SpriteSheet getSpritesheet(String resourceName) {
        File file = new File(resourceName);
        if (!AssetPool.spritesheets.containsKey(file.getAbsolutePath())) {
            assert false : "Error: Tried to access spritesheet '" + resourceName + "' and it has not been added to asset pool.";
        }
        return AssetPool.spritesheets.getOrDefault(file.getAbsolutePath(), null);
    }
}

// todo :: Nullpointer 오류 해결하기 // 배열과 vector2f 관련해서 찾아볼 것. 기한 : 12 / 21 18:20 KST
//  Exception in thread "main" java.lang.NullPointerException: Cannot invoke "java.nio.ByteBuffer.position()" because "buffer" is null
//	at org.lwjgl.system.MemoryUtil.memAddress(MemoryUtil.java:737)
//	at org.lwjgl.stb.STBImage.stbi_image_free(STBImage.java:732)
//	at main.assets.Texture.<init>(Texture.java:81)
//	at main.util.AssetPool.getTexture(AssetPool.java:33)
//	at main.MainScene.loadResources(MainScene.java:38)
//	at main.MainScene.init(MainScene.java:20)
//	at main.Window.changeScene(Window.java:65)
//	at main.Window.init(Window.java:181)
//	at main.Window.run(Window.java:123)
//	at main.Main.main(Main.java:8)

// 해결하기