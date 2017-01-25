package gdx.randomroom;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import java.util.*;

public class GdxRandomRoom extends ApplicationAdapter implements InputProcessor {
    SpriteBatch batch;
    SpriteHero sprHero;
    Sprite arsprRock[] = new Sprite[10];
    Texture txRock, txBG;
    Texture DKSprite;
    int nWH, nHH;
    float fRX, fRY;
    int nSpeed;
    boolean newRoom = true;

    @Override
    public void create() {
        Gdx.input.setInputProcessor((this));
        nSpeed = 5;
        batch = new SpriteBatch();
        txBG = new Texture("Room.jpg");
        txRock = new Texture("platform.png");
        DKSprite= new Texture("Donkey_Kong.png");
        for (int i = 0; i < 9; i++) {
            arsprRock[i] = new Sprite(txRock);
            arsprRock[i].setSize(400, 100);
            fRX=RockX();
            fRY=RockY();
            arsprRock[i].setPosition(fRX, fRY);
        }
        arsprRock[0].setSize(30, 600);
        arsprRock[0].setPosition(0, 0);
        arsprRock[1].setSize(30, 600);
        arsprRock[1].setPosition(1170, 0);
        arsprRock[2].setSize(1200, 40);
        arsprRock[2].setPosition(0, 0);
        arsprRock[3].setSize(1200, 40);
        arsprRock[3].setPosition(0, 560);
        //Contains for the wall
        nWH = 70;
        nHH = 70;
        sprHero = new SpriteHero(DKSprite, nWH, nHH, 30, 40);

    }

    @Override
    public void render() {
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            sprHero.DKX -= nSpeed;
            for (int i = 0; i < 9; i++) {
                if (isHit(sprHero, arsprRock[i])) {
                    sprHero.DKX += nSpeed;
                }
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            sprHero.DKX += nSpeed;
            for (int i = 0; i < 9; i++) {
                if (isHit(sprHero, arsprRock[i])) {
                    sprHero.DKX -= nSpeed;
                }
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            sprHero.DKY += nSpeed;
            for (int i = 0; i < 9; i++) {
                if (isHit(sprHero, arsprRock[i])) {
                    sprHero.DKY -= nSpeed;
                }
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            sprHero.DKY -= nSpeed;
            for (int i = 0; i < 9; i++) {
                if (isHit(sprHero, arsprRock[i])) {
                    sprHero.DKY += nSpeed;
                }
            }
        }

        batch.begin();
        batch.draw(txBG, 0, 0, 1200, 600);
        batch.draw(DKSprite, Math.round((float) sprHero.DKX), Math.round((float) sprHero.DKY), 70, 70);
        for (int i = 0; i < 5; i++) {
            batch.draw(txRock, arsprRock[i].getX(), arsprRock[i].getY(), 400, 100);
        }
        batch.end();
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
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

    @Override
    public void dispose() {
    }

    public static boolean isHit(SpriteHero spr1, Sprite spr2) {
        return spr1.retRect().overlaps(spr2.getBoundingRectangle()); //System.out.println("is hit");
    }
    public static int RockX(){
        Random RNGX = new Random();
        int fRX=40, fRN;
        fRN=RNGX.nextInt(11);
        for(int i=0;i<fRN;i++){
            fRX+=100;
        }
        return fRX;
    }
    public static int RockY(){
        Random RNGY = new Random();
        int fRY=40, fRN;
        fRN=RNGY.nextInt(5);
        for(int i=0;i<fRN;i++){
            fRY+=100;
        }
        return fRY;
    }
}
