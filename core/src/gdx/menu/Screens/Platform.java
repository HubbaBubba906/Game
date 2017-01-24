
package gdx.menu.Screens;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class Platform extends Sprite {
    double dPlatX, dPlatY;
    Texture texPlatform;
    float fPlatW, fPlatH;
    Rectangle rect;

    //Constuctor
    //Sprite2(sFile, fX, fY)
    Platform(Texture DonkeyKong, float fW_, float fH_, float fHX_, float fHY_) {
        fPlatW = fW_;
        fPlatH = fH_;
        dPlatX = fHX_;
        dPlatY = fHY_;
    }
    
    public Rectangle retRect() { //replaces .getBoundingRectangle() for hit detection 
        rect = new Rectangle(Math.round((float)dPlatX), Math.round((float)dPlatY), fPlatW, fPlatH);
        return rect;
    }
}

