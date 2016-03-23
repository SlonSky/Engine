//package com.company.Game.objects;
//
//import com.company.Engine.core.Input;
//import com.company.Engine.core.Time;
//import com.company.Engine.core.Window;
//import com.company.Engine.rendering.*;
//import com.company.Engine.util.Quaternion;
//import com.company.Engine.util.Vector2f;
//import com.company.Engine.util.Vector3f;
//
///**
// * Created by Slon on 16.02.2016.
// * TODO: WARNING! Multiple function calling
// */
//
//
//public class Player{
//    public static final Vector3f Y_AXIS = new Vector3f(0,1,0);
//
//    private Camera camera;
//    private GameObject body;
//
//    public Player(Camera camera, Entity entity) {
//        this.camera = camera;
//        this.body = new GameObject(entity, new Vector3f(2,2,2), false);
//    }
//
//
//    boolean mouseLocked = false;
//    Vector2f centerPos = new Vector2f(Window.getWidth()/2, Window.getHeight()/2);
//    public void input(Vector3f intersect){
//
////        if(intersect){
////           body.getEntity().getTransform().setPosition(body.getEntity().getTransform().getPosition().sub(new Vector3f(0.1f,0,0.1f)));
////            return;
////        }
//
//        float movAmt = (float)(10 * Time.getDelta());
//        if(Input.getKey(Input.KEY_W)){
//            move(body.getEntity().getTransform().getRotation().getForward().normalized(), movAmt);
//        }
//        if(Input.getKey(Input.KEY_S)){
//            move(body.getEntity().getTransform().getRotation().getForward().normalized(), -movAmt);
//        }
//        if(Input.getKey(Input.KEY_D)){
//            move(body.getEntity().getTransform().getRotation().getRight().normalized(), movAmt);
//        }
//        if(Input.getKey(Input.KEY_A)){
//            move(body.getEntity().getTransform().getRotation().getLeft().normalized(), movAmt);
//        }
//        if(!intersect.equals(new Vector3f(0,0,0))){
//            move(intersect, -movAmt);
//        }
//
////        TODO: object/component system!
////        freeLookComponent.input(forward, up);
//
//        float sensitivity = 0.5f;
//        if(Input.getKey(Input.KEY_ESCAPE)){
//            Input.setCursor(true);
//            mouseLocked = false;
//        }
//        if(Input.getMouse(0)){
//            Input.setCursor(false);
//            Input.setMousePosition(centerPos);
//            mouseLocked = true;
//        }
//
//        if(mouseLocked){
//            Vector2f deltaPos = Input.getMousePosition().sub(centerPos);
//            boolean rotX = deltaPos.getY() != 0;
//            boolean rotY = deltaPos.getX() != 0;
//
//            if(rotX){
//                rotateX(-deltaPos.getY() * sensitivity);
//            }
//            if(rotY){
//                rotateY(deltaPos.getX() * sensitivity);
//            }
//            if(rotX || rotY){
//                Input.setMousePosition(centerPos);
//            }
//        }
//    }
//
//    private void move(Vector3f dir, float amt){
//        body.getEntity().getTransform().setPosition(body.getEntity().getTransform().getPosition().add(dir.mul(amt)));
//    }
//
//    private void rotateY(float angle){
//        body.getEntity().getTransform().setRotation(
//                (new Quaternion(Y_AXIS, (float) Math.toRadians(angle))).mul(body.getEntity().getTransform().getRotation()).normalized());
//    }
//
//    private void rotateX(float angle){
//        body.getEntity().getTransform().setRotation(
//                (new Quaternion(body.getEntity().getTransform().getRotation().getRight(), (float) Math.toRadians(angle))).mul(body.getEntity().getTransform().getRotation()).normalized());
//    }
//
//    public void update(){
//          camera.setPos(body.getEntity().getTransform().getPosition().sub(new Vector3f(0, -2, 3).rotate(body.getEntity().getTransform().getRotation())));
//          camera.setRot(body.getEntity().getTransform().getRotation());
//          body.getCullingCube().moveTo(body.getEntity().getTransform().getPosition());
//          body.getBound().getTransform().setPosition(body.getEntity().getTransform().getPosition());
//          body.getEntity().getTransform().setPosition(body.getEntity().getTransform().getPosition());
//    }
//
//    public GameObject getBody() {
//        return body;
//    }
//}
