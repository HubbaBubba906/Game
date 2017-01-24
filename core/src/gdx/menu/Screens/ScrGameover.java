package gdx.menu.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import gdx.menu.GdxMenu;
import gdx.menu.Button;
import gdx.menu.TbsMenu;

public class ScrGameover implements Screen, InputProcessor {

    Music DKDeath;
    GdxMenu gdxMenu;
    TbsMenu tbsMenu;
    Button tbPlay, tbMenu;
    Stage stage;
    int nPlayMusic = 0;
    SpriteBatch batch;
    BitmapFont screenName;
    Texture TexGameover, imgCursor;
    Sprite sprGameover, spCursor;

    public ScrGameover(GdxMenu _gdxMenu) {  //Referencing the main class.
        gdxMenu = _gdxMenu;
    }

    public void show() {
        DKDeath = Gdx.audio.newMusic(Gdx.files.internal("DKDeath.mp3"));
        imgCursor = new Texture("DKHammer.png");
        spCursor = new Sprite(imgCursor);
        TexGameover = new Texture("DKGameOver.jpg");
        sprGameover = new Sprite(TexGameover);
        stage = new Stage();
        tbsMenu = new TbsMenu();
        batch = new SpriteBatch();
        screenName = new BitmapFont();
        tbPlay = new Button("TRY AGAIN", tbsMenu);
        tbMenu = new Button("MENU", tbsMenu);
        tbPlay.setY(0);
        tbPlay.setX(450);
        tbMenu.setY(0);
        tbMenu.setX(0);
        stage.addActor(tbMenu);
        stage.addActor(tbPlay);
        Gdx.input.setInputProcessor(stage);
        btnMenuListener();
        btnPlayListener();
    }

    public void render(float delta) {
        if (!DKDeath.isPlaying() && nPlayMusic == 0 && gdxMenu.currentState == gdxMenu.gameState.OVER) {
            DKDeath.play();
        }
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(sprGameover, -40, 50, Gdx.graphics.getWidth() + 100, Gdx.graphics.getHeight() - 100);
        batch.end();
        stage.act();
        stage.draw();
        batch.begin();
        batch.draw(spCursor, Gdx.input.getX() - Gdx.graphics.getHeight() / 80, Gdx.graphics.getHeight() - Gdx.input.getY() - Gdx.graphics.getHeight() / 60, Gdx.graphics.getHeight() / 20, Gdx.graphics.getHeight() / 20);
        batch.end();
    }

    public void btnMenuListener() {
        tbMenu.addListener(new ChangeListener() {
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                DKDeath.pause();
                gdxMenu.currentState = gdxMenu.gameState.MENU;
                gdxMenu.updateState();
            }
        });
    }

    public void btnPlayListener() {
        tbPlay.addListener(new ChangeListener() {
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                DKDeath.pause();
                gdxMenu.currentState = gdxMenu.gameState.PLAY;
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
