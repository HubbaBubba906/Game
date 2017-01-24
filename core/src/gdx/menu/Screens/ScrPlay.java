package gdx.menu.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import gdx.menu.GdxMenu;
import gdx.menu.Button;
import gdx.menu.TbsMenu;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class ScrPlay implements Screen, InputProcessor {

    Timer tTimer = new Timer();
    GdxMenu gdxMenu;
    TbsMenu tbsMenu;
    Button tbMenu, tbGameover, tbWin;
    Stage stage;
    public Music DKMusic, DKJump, DKHurt, DKWin;
    int MOUSEX, MOUSEY, nSecondspassed = 0;
    public int nLives = 3;
    double dBarrelSpeed;
    SpriteBatch batch;
    BitmapFont screenName;
    Texture TexWooden, TexBanana, texCursor, texCursorPressed, texLadder;
    Sprite SprWood, SprBanana, spCursor, spCursorPressed, spCurrentCursor, spLadder;
    Platform sprPlat[] = new Platform[5];
//    Platform = new Sprite(TexWooden);
    double dSpeed = 0, dGravity = 0.15;
    boolean bJump, bBarrels = false, bDeath = false;
    int nJumps, DKSize = 50;
    float dXstart, dYstart;
    private static final int COLS = 6, COLS1 = 4, COLS3 = 5;
    private static final int ROWS = 3, ROWS1 = 1, ROWS3 = 1;
    Texture DKSprite, BarrelSprite, MarioSprite;
    Texture BackGround, Ground;
    TextureRegion[] frames, frames1, frames3;
    TextureRegion DonkeyKong, Barrels, Mario;
    float DKX = 0, DKY = 0, fBarrelX = 520, fBarrelY = 530;
    float SpriteSpeed = 125f;
    float Time = 0f, Time1 = 0f, Time3 = 0f;
    Animation animation, animation1, animation3;
    Sprite aLadders[] = new Sprite[5];

    public ScrPlay(GdxMenu _gdxMenu) {
        gdxMenu = _gdxMenu;
    }

    public void show() {
        DKJump = Gdx.audio.newMusic(Gdx.files.internal("DKJump.mp3"));
        DKHurt = Gdx.audio.newMusic(Gdx.files.internal("DKHurt.mp3"));
        DKMusic = Gdx.audio.newMusic(Gdx.files.internal("Donkey Kong Country OST 3 Simian Segue.mp3"));
        DKMusic.setLooping(true);
        texCursor = new Texture("DKHammer.png");
        spCursor = new Sprite(texCursor);
        texLadder = new Texture("ladder.png");
        spLadder = new Sprite(texLadder);
        texCursorPressed = new Texture("DKHammer flat.png");
        spCursorPressed = new Sprite(texCursorPressed);
        spCurrentCursor = spCursor;
        dSpeed = 0;
        DKX = 0;
        DKY = 0;
        dBarrelSpeed = 2.5;
        TexWooden = new Texture("platform.png");
        SprWood = new Sprite(TexWooden);
        TexBanana = new Texture("Banana.png");
        SprBanana = new Sprite(TexBanana);
        stage = new Stage();
        tbsMenu = new TbsMenu();
        batch = new SpriteBatch();
        screenName = new BitmapFont();
        tbMenu = new Button("BACK TO MENU", tbsMenu);
        tbGameover = new Button("GAMEOVER", tbsMenu);
        tbWin = new Button("WIN", tbsMenu);
        Gdx.input.setInputProcessor(stage);
        btnMenuListener();
        batch = new SpriteBatch();
        BackGround = new Texture(Gdx.files.internal("back.jpg"));
        Ground = new Texture(Gdx.files.internal("ground.png"));
        DKSprite = new Texture(Gdx.files.internal("Dkspritesheet_edited-212.png"));
        BarrelSprite = new Texture(Gdx.files.internal("Barrrrrrels sprite.png"));
        MarioSprite = new Texture(Gdx.files.internal("maro sprit.png"));
        TextureRegion[][] tmp = TextureRegion.split(DKSprite, DKSprite.getWidth() / COLS, DKSprite.getHeight() / ROWS);
        frames = new TextureRegion[COLS * ROWS];
        TextureRegion[][] tmp1 = TextureRegion.split(BarrelSprite, BarrelSprite.getWidth() / COLS1,
                BarrelSprite.getHeight() / ROWS1);
        frames1 = new TextureRegion[COLS1 * ROWS1];
        TextureRegion[][] tmp3 = TextureRegion.split(MarioSprite, MarioSprite.getWidth() / COLS3,
                MarioSprite.getHeight() / ROWS3);
        frames3 = new TextureRegion[COLS3 * ROWS3];
        int index3 = 0;
        for (int p = 0; p < ROWS3; p++) {
            for (int j = 0; j < COLS3; j++) {
                frames3[index3++] = tmp3[p][j];
            }
            int index = 0;
            for (int i = 0; i < ROWS; i++) {
                for (int j = 0; j < COLS; j++) {
                    frames[index++] = tmp[i][j];
                }
                int index1 = 0;
                for (int f = 0; f < ROWS1; f++) {
                    for (int g = 0; g < COLS1; g++) {
                        frames1[index1++] = tmp1[f][g];
                    }
                }
                animation = new Animation(1f, frames);
                animation1 = new Animation(1f, frames1);
                animation3 = new Animation(1f, frames3);
            }
        }
        Gdx.input.setCursorCatched(false);
        if (Gdx.input.getX() <= Gdx.graphics.getWidth() && Gdx.input.getX() >= 0
                && Gdx.input.getY() <= Gdx.graphics.getHeight() && Gdx.input.getX() >= 0) {
            Gdx.input.setCursorCatched(false);
        }

    }

    public void render(float delta) {
        TimerTask task = new TimerTask() {
            public void run() {
                nSecondspassed++;
            }
        };
        tTimer.scheduleAtFixedRate(task, 1000, 1000);
        for (int i = 0; i <= 5; i++) {
            batch.begin();
//        batch.draw(sprPlat[1], -20, 100, Gdx.graphics.getWidth() - 50, 40);
//        batch.draw(sprPlat[2], 70, 240, Gdx.graphics.getWidth() - 50, 40);
//        batch.draw(sprPlat[3], -20, 380, Gdx.graphics.getWidth() - 50, 40);
//        batch.draw(sprPlat[4], 70, 520, Gdx.graphics.getWidth() - 50, 40);
//        batch.draw(sprPlat[5], Gdx.graphics.getWidth() - 300, 620, 200, 40);
            batch.end();
        }
        if (!DKMusic.isPlaying() && gdxMenu.currentState == gdxMenu.gameState.PLAY) {
            DKMusic.play();
        }
        dXstart = DKX;
        dYstart = DKY;
        if (DKY <= Gdx.graphics.getHeight()) { //Gravity
            dSpeed += dGravity;
        }
        DKY -= dSpeed;
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        if (Time < 6) {
            Time += Gdx.graphics.getDeltaTime() + 0.15;
        } else {
            Time = 0;
        }
        if (Time1 < 4) {
            Time1 += Gdx.graphics.getDeltaTime() + 0.15;
        } else {
            Time1 = 0;
        }
        if (Time3 < 5) {
            Time3 += Gdx.graphics.getDeltaTime() + 0.15;
        } else {
            Time3 = 0;
        }

        if (DKX + DKSize >= Gdx.graphics.getWidth() - 240 && DKY + DKSize > 660) {
            DKMusic.pause();
            gdxMenu.currentState = gdxMenu.gameState.WIN;
            gdxMenu.updateState();
            nLives = 3;
            fBarrelX = 520;
            fBarrelY = 530;
            dBarrelSpeed = 2.5;
        }
//        if (nSecondspassed > 3) { //barrelspawning
//            dBarrelSpeed = 2.5;
//            bBarrels = true;
//        }
        int nDie;
        if (bBarrels = true) { // barrelmovement
            fBarrelX -= dBarrelSpeed;
            Barrels = animation1.getKeyFrame(0 + Time1);
            if (fBarrelX == 150 && fBarrelY >= 390) { 
                nDie = (int) (Math.random() * 6) + 1;
                if (nDie <= 2) {
                    Barrels = animation1.getKeyFrame(0);
                    dBarrelSpeed = 0;
                    fBarrelY -= 4;
                }
            }
            if (fBarrelX == 190 && fBarrelY <= 300 && fBarrelY >= 290) { 
                nDie = (int) (Math.random() * 6) + 1;
                if (nDie <= 6) {
                    Barrels = animation1.getKeyFrame(0);
                    dBarrelSpeed = 0;
                    fBarrelY += 4;
                }
            }
            if (fBarrelX == 30) {
                dBarrelSpeed = 0;
                fBarrelY -= 4;
                Barrels = animation1.getKeyFrame(0);
            }
            if (fBarrelY == 390 || fBarrelY == 110) {
                dBarrelSpeed = -2.5;
                Barrels = animation1.getKeyFrame(0 + Time1);
            }
            if (fBarrelX == Gdx.graphics.getWidth() - 100 && fBarrelY <= 400) {
                dBarrelSpeed = 0;
                fBarrelY -= 4;
                Barrels = animation1.getKeyFrame(0);
            }
            if (fBarrelY == 250 || fBarrelY <= -8) {
                dBarrelSpeed = 2.5;
                Barrels = animation1.getKeyFrame(0 + Time1);
            }
            if (fBarrelX <= -50) {
                fBarrelX = 520;
                fBarrelY = 530;
            }
        }

        if (fBarrelX <= 500 && fBarrelY >= 500) {
            Mario = animation3.getKeyFrame(5);
        }
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        DonkeyKong = animation.getKeyFrame(0);      // movement/animation
        Mario = animation3.getKeyFrame(0 + Time3);
        if (Gdx.input.isKeyPressed(Input.Keys.DPAD_LEFT) && DKX > 0) {
            DKX -= Gdx.graphics.getDeltaTime() * SpriteSpeed;
            DonkeyKong = animation.getKeyFrame(6 + Time);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DPAD_RIGHT) && DKX < Gdx.graphics.getWidth()) {
            DKX += Gdx.graphics.getDeltaTime() * SpriteSpeed;
            DonkeyKong = animation.getKeyFrame(0 + Time);
            bBarrels = true;
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.DPAD_UP) && DKY < Gdx.graphics.getHeight() /*&& nJumps == 0*/) {
            bJump = true;
            nJumps++;
            DKJump.play();
        }
        if (DKX + (DKSize / 2) > fBarrelX && DKX < fBarrelX + 50 //HitdectBarrels
                && +DKY + DKSize - 10 > fBarrelY && DKY + 10 < fBarrelY + 50) {
            DKX = 0;
            DKY = 0;
            DKHurt.play();
            nLives--;
            fBarrelX = 520;
            fBarrelY = 530;
            dBarrelSpeed = 2.5;
        }
        if (dSpeed >= 0 && nJumps != 0) {
            DonkeyKong = animation.getKeyFrame(13);
        }
// if (DKX + DKSize > platform x && DKX < platformx + plaformwidth &&
//      +   DKY + DKSize > platform y && DKY < platform y+ plaform height) {  
//    }
//if (DKX + DKSize > SprWood.getOriginX() && DKX < SprWood.getOriginX() + SprWood.getWidth() //Hitdect
//                && + DKY + DKSize > SprWood.getOriginY() && DKY < SprWood.getOriginY() + SprWood.getHeight()) {
//          }
        if (DKX + DKSize > 0 && DKX < 0 + Gdx.graphics.getWidth() - 75 //Hitdect
                && +DKY + DKSize > 100 && DKY < 100 + 40) {
            if (DKX >= Gdx.graphics.getWidth() - 101 && DKX <= Gdx.graphics.getWidth() - 100) {
                DKX += 3;
            }
            if (DKY <= 75) { //bottomhit test
                dSpeed *= -0.4;
                DKY = 100 - DKSize;
            } else if (DKY - DKSize <= 70) { //top hit test
                DKY++;
                dSpeed = 0;
                nJumps = 0;
            }
        }
        if (DKX + DKSize - 27 > 70 && DKX < 70 + Gdx.graphics.getWidth() - 75 //Hitdect
                && +DKY + DKSize > 240 && DKY < 240 + 40) {
            if (DKY <= 205) { //bottomhit test
                dSpeed *= -0.4;
                DKY = 220 - DKSize;
            } else if (DKY - DKSize <= 210) { //top hit test
                DKY++;
                dSpeed = 0;
                nJumps = 0;
            }
        }
        if (DKX + DKSize > 0 && DKX < 0 + Gdx.graphics.getWidth() - 75 //Hitdect
                && +DKY + DKSize > 380 && DKY < 380 + 40) {
            if (DKX >= Gdx.graphics.getWidth() - 101 && DKX <= Gdx.graphics.getWidth() - 100) {
                DKX += 3;
            }
            if (DKY <= 345) { //bottomhit test
                dSpeed *= -0.4;
                DKY = 360 - DKSize;
            } else if (DKY - DKSize <= 350) { //top hit test
                DKY++;
                dSpeed = 0;
                nJumps = 0;
            }
        }
        if (DKX + DKSize - 27 > 70 && DKX < 70 + Gdx.graphics.getWidth() - 75 //Hitdect
                && +DKY + DKSize > 520 && DKY < 520 + 40) {
            if (DKY <= 495) { //bottomhit test
                dSpeed *= -0.4;
                DKY = 510 - DKSize;
            } else if (DKY - DKSize <= 490) { //top hit test
                DKY++;
                dSpeed = 0;
                nJumps = 0;
            }
        }
        if (DKX + DKSize - 27 > Gdx.graphics.getWidth() - 300
                && DKX < Gdx.graphics.getWidth() - 300 + Gdx.graphics.getWidth() - 75 //Hitdect
                && +DKY + DKSize > 620 && DKY < 620 + 40) {
            if (DKY <= 595) { //bottomhit test
                dSpeed *= -0.4;
                DKY = 610 - DKSize;
            } else if (DKY - DKSize <= 590) { //top hit test
                DKY++;
                dSpeed = 0;
                nJumps = 0;
            }
        }
        if (DKY >= 500 && DKX >= 525) {
            DKX -= Gdx.graphics.getDeltaTime() * SpriteSpeed;
        }
        if (bJump == true) {
            DonkeyKong = animation.getKeyFrame(13);
            dSpeed = -6.5 + Gdx.graphics.getDeltaTime() * SpriteSpeed;
            bJump = false;
        }

        if (DKY <= 5) {  //floor hit test
            DKY = 1;
            nJumps = 0;
        }
        if (DKX >= Gdx.graphics.getWidth() - 50) {
            DKX = Gdx.graphics.getWidth() - 50;
        }

        if (nLives == 0) {
            DKMusic.pause();
            gdxMenu.currentState = gdxMenu.gameState.OVER;
            gdxMenu.updateState();
            nLives = 3;
            bDeath = false;
        }
        batch.begin();
        batch.draw(BackGround, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.draw(Ground, 0, 0, Gdx.graphics.getWidth(), 10);
        batch.draw(SprBanana, Gdx.graphics.getWidth() - 240, 660, 70, 70);
        batch.draw(SprWood, -20, 100, Gdx.graphics.getWidth() - 50, 40);
        batch.draw(SprWood, 70, 240, Gdx.graphics.getWidth() - 50, 40);
        batch.draw(SprWood, -20, 380, Gdx.graphics.getWidth() - 50, 40);
        batch.draw(SprWood, 70, 520, Gdx.graphics.getWidth() - 50, 40);
        batch.draw(SprWood, Gdx.graphics.getWidth() - 300, 620, 200, 40);
        batch.draw(spLadder, 500, 65, 50, 60);
        batch.draw(spLadder, 460, 205, 50, 60);
        batch.draw(spLadder, 270, 205, 50, 60);
        batch.draw(spLadder, 380, 345, 50, 60);
        batch.draw(spLadder, 180, 345, 50, 60);
        batch.draw(spLadder, 150, 485, 50, 60);
        batch.draw(DonkeyKong, (int) DKX, (int) DKY, DKSize, DKSize);
        batch.draw(Barrels, fBarrelX, fBarrelY, DKSize + 10, DKSize + 10);
        batch.draw(Mario, 550, 550, 70, 70);
        batch.end();
        stage.act();
        stage.draw();
        tbMenu.setY(Gdx.graphics.getHeight() + 100);
        tbMenu.setX(0);
        tbWin.setY(Gdx.graphics.getHeight() + 100);
        tbWin.setX(300);
        tbGameover.setY(Gdx.graphics.getHeight() + 100);
        tbGameover.setX(450);
        stage.addActor(tbMenu);
        stage.addActor(tbGameover);
        stage.addActor(tbWin);
        batch.begin();
        batch.draw(spCurrentCursor, Gdx.input.getX() - Gdx.graphics.getHeight() / 80,
                Gdx.graphics.getHeight() - Gdx.input.getY() - Gdx.graphics.getHeight() / 60,
                Gdx.graphics.getHeight() / 20, Gdx.graphics.getHeight() / 20);
        batch.end();
    }

    public void btnMenuListener() {
        tbMenu.addListener(new ChangeListener() {
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                gdxMenu.currentState = gdxMenu.gameState.MENU;
                gdxMenu.updateState();
            }
        });
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {

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
}
