package com.company.Engine.rendering;

import com.company.Engine.rendering.meshManagment.Material;
import com.company.Engine.rendering.meshManagment.Mesh;
import com.company.Engine.rendering.meshManagment.Texture;
import com.company.Engine.util.Quaternion;
import com.company.Engine.util.Vector3f;

/**
 * Created by Slon on 31.03.2016.
 */
public class GraphicBound extends Graphic {
    private static final Mesh MESH = new Mesh("bound.obj");
    private static final Texture TEXTURE = new Texture("bound.png");

    private Vector3f bound;
    private boolean aabb;
    private Vector3f offset;


    public GraphicBound(Vector3f bound, Vector3f color) {
        this(bound, color, false);
    }

    public GraphicBound(Vector3f bound, Vector3f color, boolean aabb) {
        this(bound, new Vector3f(0,0,0), color, aabb);
    }

    public GraphicBound(Vector3f bound, Vector3f offset, Vector3f color, boolean aabb) {
        super(MESH, new Material(TEXTURE, color, 0, 0));
        this.bound = bound.div(2);
        this.offset = offset;
        this.aabb = aabb;
    }

    @Override
    public void render(Shader shader, RenderingEngine renderingEngine) {
        shader.bind();
        shader.updateUniforms(new Transform(
                getTransform().getPosition().add(offset),
                aabb ? new Quaternion(0,0,0,1) : getTransform().getRotation(),
                bound), getMaterial(), renderingEngine);
        getMesh().draw();
    }
}
