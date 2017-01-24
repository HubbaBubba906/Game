package gdx.menu.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import gdx.menu.GdxMenu;
import gdx.menu.Button;
import gdx.menu.TbsMenu;

public class ScrMenu implements Screen, InputProcessor {
    Music DKMenu;
    int nPlayMusic = 0;
    GdxMenu gdxMenu;
    TbsMenu tbsMenu;
    Button tbPlay, tbTutorial;
    Stage stage;
    SpriteBatch batch;
    BitmapFont screenName;
    Texture texBackground, texDKlogo, texClimblogo, imgCursor;
    Sprite sprBackGround, sprDKlogo, sprClimblogo, spCursor;

    public ScrMenu(GdxMenu _gdxMenu) {  //Referencing the main class.
        gdxMenu = _gdxMenu;
    }

    public void show() {
        DKMenu = Gdx.audio.newMusic(Gdx.files.internal("DKMenu.mp3"));
        imgCursor = new Texture("DKHammer.png");
        spCursor = new Sprite(imgCursor);
        texDKlogo = new Texture("DonkeyKonglogo.png");
        sprDKlogo = new Sprite(texDKlogo);
        texClimblogo = new Texture("Dk climb.png");
        sprClimblogo = new Sprite(texClimblogo);
        texBackground = new Texture("jungle.jpg");
        sprBackGround = new Sprite(texBackground);
        stage = new Stage();
        tbsMenu = new TbsMenu();
        batch = new SpriteBatch();
        screenName = new BitmapFont();
        tbPlay = new Button("PLAY", tbsMenu);
        tbPlay.setY(200);
        tbPlay.setX(Gdx.graphics.getWidth() / 2 - 130);
        tbTutorial = new Button("HOW TO PLAY", tbsMenu);
        tbTutorial.setY(Gdx.graphics.getHeight() - 100);
        tbTutorial.setX(0);
        stage.addActor(tbPlay);
        stage.addActor(tbTutorial);
        Gdx.input.setInputProcessor(stage);
        btnPlayListener();
        btnTutorialListener();
    }

    public void render(float delta) { 
        if (!DKMenu.isPlaying() && nPlayMusic == 0 && gdxMenu.currentState == gdxMenu.gameState.MENU) {
            DKMenu.play();
        }
        batch.begin();
        batch.draw(sprBackGround, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.draw(sprDKlogo, Gdx.graphics.getWidth() / 2 - 250, Gdx.graphics.getWidth() - 200, 500, 150);
        batch.draw(sprClimblogo, Gdx.graphics.getWidth() / 2 - 250, Gdx.graphics.getWidth() - 340, 500, 130);
        batch.end();
        stage.act();
        stage.draw();
        batch.begin();
        batch.draw(spCursor, Gdx.input.getX() - Gdx.graphics.getHeight() / 80, Gdx.graphics.getHeight() - Gdx.input.getY() - Gdx.graphics.getHeight() / 60, Gdx.graphics.getHeight() / 20, Gdx.graphics.getHeight() / 20);
        batch.end();
    }

    public void btnPlayListener() {
        tbPlay.addListener(new ChangeListener() {
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                DKMenu.pause();
                gdxMenu.currentState = gdxMenu.gameState.PLAY;
                gdxMenu.updateState();
                nPlayMusic = 0;
            }
        });
    }

    public void btnTutorialListener() {
        tbTutorial.addListener(new ChangeListener() {
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                nPlayMusic = 1;
                gdxMenu.currentState = gdxMenu.gameState.TUTORIAL;
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
