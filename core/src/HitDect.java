import java.util.Iterator;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

public class HitDect implements ApplicationListener {
	private Texture txDrop;
	private Texture txBucket;
	private Sound dropSound;
	private Music rainMusic;
	private SpriteBatch batch;
	private Sprite sprDrop, sprBucket; // a Sprite allows you to get the bounding rectangle
	private OrthographicCamera camera;
	private Array<Sprite> arsprDrop; // use an array of Sprites rather than rectangles
	private long lastDropTime;
    private int nScore;
    private int nLives;
    private BitmapFont font;
    private int spawnMillis;

	@Override
	public void create() {
		// load the images for the droplet and the bucket, 64x64 pixels each
		txDrop = new Texture(Gdx.files.internal("droplet.png"));
		txBucket = new Texture(Gdx.files.internal("bucket.png"));
        font =new BitmapFont();
        nLives=3;
        spawnMillis = 1000;
		sprBucket= new Sprite(txBucket);
		sprDrop= new Sprite(txDrop);

		// load the drop sound effect and the rain background "music"
		dropSound = Gdx.audio.newSound(Gdx.files.internal("drop.wav"));
		rainMusic = Gdx.audio.newMusic(Gdx.files.internal("rain.mp3"));

		// start the playback of the background music immediately
		rainMusic.setLooping(true);
		rainMusic.play();

		// create the camera and the SpriteBatch
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);
		batch = new SpriteBatch();
		arsprDrop = new Array<Sprite>();// array of Sprites rather than Rectangles
		spawnRaindrop();
	}

	private void spawnRaindrop() {
		Sprite sprDrop = new Sprite(txDrop);
		sprDrop.setX( MathUtils.random(0, 800-64));
		sprDrop.setY(480);
		arsprDrop.add(sprDrop);
		lastDropTime = TimeUtils.nanoTime();
	}

	@Override
	public void render() {
		// clear the screen with a dark blue color. The
		// arguments to glClearColor are the red, green
		// blue and alpha component in the range [0,1]
		// of the color to be used to clear the screen.
		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		// tell the camera to update its matrices.
		camera.update();

		// tell the SpriteBatch to render in the
		// coordinate system specified by the camera.
		batch.setProjectionMatrix(camera.combined);

		// begin a new batch and draw the bucket and
		// all drops
		batch.begin();
		if(nLives>0){
			batch.draw(sprBucket, sprBucket.getX(), sprBucket.getY());

			for(Sprite sprDrop: arsprDrop) {
				batch.draw(sprDrop, sprDrop.getX(), sprDrop.getY());
			}
		}
        font.draw(batch,Integer.toString(nScore) , 10, 10);
        font.draw(batch, Integer.toString(nLives), 200, 10);
        font.draw(batch, Integer.toString(spawnMillis), 400, 10);
		batch.end();

		// process user input
		if(Gdx.input.isTouched()) {
			Vector3 touchPos = new Vector3();
			touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			camera.unproject(touchPos);
			sprBucket.setX( touchPos.x - 64 / 2   );
		}
		/*if(Gdx.input.isKeyPressed(Keys.LEFT)) bucket.x -= 200 * Gdx.graphics.getDeltaTime();
		if(Gdx.input.isKeyPressed(Keys.RIGHT)) bucket.x += 200 * Gdx.graphics.getDeltaTime();*/
		if(Gdx.input.isKeyPressed(Keys.LEFT)) sprBucket.setX(sprBucket.getX() - 200 * Gdx.graphics.getDeltaTime() )  ;
		if(Gdx.input.isKeyPressed(Keys.RIGHT)) sprBucket.setX(sprBucket.getY() + 200 * Gdx.graphics.getDeltaTime());

		// make sure the bucket stays within the screen bounds
		/*if(bucket.x < 0) bucket.x = 0;
		if(bucket.x > 800 - 64) bucket.x = 800 - 64;*/
		if(sprBucket.getX() < 0) sprBucket.setX(0);
		if(sprBucket.getX() > 800 - 64) sprBucket.setX( 800 - 64);

		// check if we need to create a new raindrop
        spawnMillis = 1000 - (nScore * 5 / 2);
     	if(TimeUtils.nanoTime() - lastDropTime > 1000000 * spawnMillis) spawnRaindrop();

		// move the raindrops, remove any that are beneath the bottom edge of
		// the screen or that hit the bucket. In the later case we play back
		// a sound effect as well.
		//Iterator<Rectangle> iter = raindrops.iterator();
		Iterator<Sprite> iter = arsprDrop.iterator();
		while(iter.hasNext()) {
			Sprite sprDrop = iter.next();
			// lower the drop
			//raindrop.y -= (150 + 2*nScore) * Gdx.graphics.getDeltaTime();
			sprDrop.setY( sprDrop.getY() - (150 + 2*nScore) * Gdx.graphics.getDeltaTime());
			if(sprDrop.getY() + 64 < 0) {
                nLives--;
                iter.remove();
            }
			if(sprDrop.getBoundingRectangle().overlaps(sprBucket.getBoundingRectangle())) {
				dropSound.play();
                nScore++;
				iter.remove();
			}
		}
	}

	@Override
	public void dispose() {
		// dispose of all the native resources
		txDrop.dispose();
		txBucket.dispose();
		dropSound.dispose();
		rainMusic.dispose();
		batch.dispose();
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
}