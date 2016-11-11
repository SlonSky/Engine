package com.company.Engine.rendering;

import com.company.Game.objects.GameComponent;

/**
 * Created by Slon on 13.02.2016.
 */
public class Model extends GameComponent {
    private Mesh mesh;
    private Material material;

    public Model(Mesh mesh, Transform transform, Material material) {
        this.mesh = mesh;
        this.material = material;
    }

    public Mesh getMesh() {
        return mesh;
    }

    public void setMesh(Mesh mesh) {
        this.mesh = mesh;
    }


    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

}
