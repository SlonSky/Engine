package com.company.Game;

import com.company.Engine.core.Game;
import com.company.Engine.rendering.Camera;
import com.company.Engine.rendering.RenderingEngine;
import com.company.Engine.rendering.guis.GUITexture;
import com.company.Engine.rendering.meshManagment.Texture;
import com.company.Engine.rendering.text.Font;
import com.company.Engine.util.Quaternion;
import com.company.Engine.util.Vector2f;
import com.company.Engine.util.Vector3f;
import com.company.Engine.windows.UIButton;
import com.company.Engine.windows.UIFrame;

/**
 * Created by Slon on 27.04.2016.
 */
public class WindowedGame  extends Game{

    UIFrame mainWindow;
    UIButton button;
    @Override
    public void init() {
        camera = new Camera(new Vector3f(0,0,0),new Quaternion(0,0,0,1));
        setCamera(camera);

        Font font = new Font("rus.png", "rus.fnt");

        mainWindow = new UIFrame(new Texture("1.png"), "coke na");

        button = new UIButton(new Vector2f(0, 0), new Vector2f(1, 1), new Texture("button.png"),
                "button", 0.3f, font, new Vector3f(0, 1, 0));

        mainWindow.addComponent(button);

    }

    @Override
    public void render(RenderingEngine renderingEngine) {
        super.render(renderingEngine);
        renderingEngine.render(mainWindow);
    }

    @Override
    public void update() {
        mainWindow.update();
        button.update();
    }
}
