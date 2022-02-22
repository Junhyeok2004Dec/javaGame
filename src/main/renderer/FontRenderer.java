package main.renderer;

import main.util.object.Component;

import java.awt.*;
import java.awt.image.BufferedImage;

import static java.awt.image.BufferedImage.TYPE_INT_ARGB;

public class FontRenderer extends Component {

    public static BufferedImage defaultImage = new BufferedImage(1, 1, TYPE_INT_ARGB);
    private static Graphics2D defaultGrapics2D = (Graphics2D) defaultImage.getGraphics();




    @Override
    public void start() {
        if (gameObject.getComponent(SpriteRenderer.class) != null) {
            System.out.println("Found font renderer");
        }
    }

    @Override
    public void update(float dt) {

    }

    public static Point getTextSize(Font font, String text) {
        FontMetrics fontmetrics = defaultGrapics2D.getFontMetrics(font);

        return new Point(fontmetrics.stringWidth(text), font.getSize());
    }

    //java.awt library(font)
    public BufferedImage textToImage(Font font, String text, Color color) {

        Point size = getTextSize(font, text);

        BufferedImage image = new BufferedImage(((int)( size.getX() * 1.1)), ((int) (size.getY() * 1.2)), TYPE_INT_ARGB);
        Graphics g = image.getGraphics();
        g.setColor(color);
        g.setFont(font);

        g.drawString(text, 0, (int) size.getY());

        return image;
    }
}
