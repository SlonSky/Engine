package com.company.Engine.rendering;

/**
 * Created by Slon on 13.02.2016.
 */
public class Entity {
    private Mesh mesh;
    private Transform transform;
    private Material material;

    public Entity(Mesh mesh, Transform transform, Material material) {
        this.mesh = mesh;
        this.transform = transform;
        this.material = material;
    }

    public Mesh getMesh() {
        return mesh;
    }

    public void setMesh(Mesh mesh) {
        this.mesh = mesh;
    }

    public Transform getTransform() {
        return transform;
    }

    public void setTransform(Transform transform) {
        this.transform = transform;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

}
