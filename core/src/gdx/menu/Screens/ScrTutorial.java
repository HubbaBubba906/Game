package gdx.menu.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
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

public class ScrTutorial implements Screen, InputProcessor {
    GdxMenu gdxMenu;
    TbsMenu tbsMenu;
    Button tbMenu;
    Stage stage;
    SpriteBatch batch;
    BitmapFont screenName;
    Texture TexTutorial, texBackground, TexArrow, TexBanana, imgCursor, imgTitle;
    Sprite SprTutorial, sprBackGround, SprArrow, SprBanana, spCursor, spTitle;

    public ScrTutorial(GdxMenu _gdxMenu) {  //Referencing the main class.
        gdxMenu = _gdxMenu;
    }

    public void show() {
        imgCursor = new Texture("DKHammer.png");
        spCursor = new Sprite(imgCursor);
        imgTitle = new Texture("TItle.png");
        spTitle = new Sprite(imgTitle);
        TexTutorial = new Texture("donkey-swinging.png");
        SprTutorial = new Sprite(TexTutorial);
        TexArrow = new Texture("Tutorial.png");
        SprArrow = new Sprite(TexArrow);
        TexBanana = new Texture("Banana.png");
        SprBanana = new Sprite(TexBanana);
        texBackground = new Texture("Light_Brown.png");
        sprBackGround = new Sprite(texBackground);
        stage = new Stage();
        tbsMenu = new TbsMenu();
        tbsMenu = new TbsMenu();
        batch = new SpriteBatch();
        screenName = new BitmapFont();
        tbMenu = new Button("BACK", tbsMenu);
        tbMenu.setY(Gdx.graphics.getHeight()- 100);
        tbMenu.setX(0);
        stage.addActor(tbMenu);
        Gdx.input.setInputProcessor(stage);
        btnMenuListener();
    }

    public void render(float delta) {
        Gdx.gl.glClearColor(210, 105, 30, 56);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(sprBackGround, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.draw(SprTutorial, 50, 350, 350, 450);
        batch.draw(SprArrow, 350, 200, 400, 400);
        batch.draw(SprBanana, 450, 50, 200, 200);
        batch.draw(spTitle, 10, 60, 300, 300);
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
