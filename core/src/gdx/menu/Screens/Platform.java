
package gdx.menu.Screens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

public class Platform extends Sprite {
    double DKX, DKY;
    Texture txHero;
    float DKW, DKH;
    Rectangle rect;

    //Constuctor
    //Sprite2(sFile, fX, fY)
    Platform(Texture DonkeyKong, float fW_, float fH_, float fHX_, float fHY_) {
        DKW = fW_;
        DKH = fH_;
        DKX = fHX_;
        DKY = fHY_;
    }
    
    public Rectangle retRect() { //replaces .getBoundingRectangle() for hit detection 
        rect = new Rectangle(Math.round((float)DKX), Math.round((float)DKY), DKW, DKH);
        return rect;
    }
}
