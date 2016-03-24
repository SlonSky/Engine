package com.company.Engine.rendering.skybox;

import com.company.Engine.rendering.meshManagment.Mesh;

/**
 * Created by Slon on 24.03.2016.
 */
public class SkyBox {
    private Mesh box;
    private CubeMapTexture texture;
// todo dynamic parameters / constants
    public SkyBox(Mesh box, CubeMapTexture texture) {
        this.box = box;
        this.texture = texture;
    }

    public CubeMapTexture getTexture() {
        return texture;
    }

    public Mesh getBox() {
        return box;
    }
}
