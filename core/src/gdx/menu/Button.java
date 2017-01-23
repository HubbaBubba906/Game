package gdx.menu;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;


public class Button extends TextButton{
    String sText;
    public Button(String text, TextButtonStyle tbs) {
        super(text, tbs);
        sText = text;
        this.setSize(250, 100);
        this.addListener(new ClickListener() {
            public void clicked(InputEvent e, float x, float y) {
                System.out.println(sText);
            }
        });
    }}
