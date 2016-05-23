package Engine.util;

/**
 * Created by Slon on 10.02.2016.
 */
public class Matrix4f {
    private float m[][];

    public Matrix4f(){
        m = new float[4][4];
    }

    public Matrix4f initIdentity(){
        m[0][0] = 1;    m[0][1] = 0;    m[0][2] = 0;    m[0][3] = 0;
        m[1][0] = 0;    m[1][1] = 1;    m[1][2] = 0;    m[1][3] = 0;
        m[2][0] = 0;    m[2][1] = 0;    m[2][2] = 1;    m[2][3] = 0;
        m[3][0] = 0;    m[3][1] = 0;    m[3][2] = 0;    m[3][3] = 1;
        return this;
    }

    public Matrix4f initScale(float x, float y, float z) {
        m[0][0] = x;	m[0][1] = 0;	m[0][2] = 0;	m[0][3] = 0;
        m[1][0] = 0;	m[1][1] = y;	m[1][2] = 0;	m[1][3] = 0;
        m[2][0] = 0;	m[2][1] = 0;	m[2][2] = z;	m[2][3] = 0;
        m[3][0] = 0;	m[3][1] = 0;	m[3][2] = 0;	m[3][3] = 1;
        return this;
    }

    public Matrix4f initRotation(float x, float y, float z) {
        Matrix4f rx = new Matrix4f();
        Matrix4f ry = new Matrix4f();
        Matrix4f rz = new Matrix4f();

        x = (float)Math.toRadians(x);
        y = (float)Math.toRadians(y);
        z = (float)Math.toRadians(z);

        rx.m[0][0] = 1;  rx.m[0][1] = 0;				   rx.m[0][2] = 0;					  rx.m[0][3] = 0;
        rx.m[1][0] = 0;	 rx.m[1][1] = (float)Math.cos(x);  rx.m[1][2] = -(float)Math.sin(x);  rx.m[1][3] = 0;
        rx.m[2][0] = 0;	 rx.m[2][1] = (float)Math.sin(x);  rx.m[2][2] = (float)Math.cos(x);   rx.m[2][3] = 0;
        rx.m[3][0] = 0;	 rx.m[3][1] = 0;				   rx.m[3][2] = 0;					  rx.m[3][3] = 1;

        ry.m[0][0] = (float)Math.cos(y);  ry.m[0][1] = 0;  ry.m[0][2] = -(float)Math.sin(y);  ry.m[0][3] = 0;
        ry.m[1][0] = 0;					  ry.m[1][1] = 1;  ry.m[1][2] = 0;					  ry.m[1][3] = 0;
        ry.m[2][0] = (float)Math.sin(y);  ry.m[2][1] = 0;  ry.m[2][2] = (float)Math.cos(y);   ry.m[2][3] = 0;
        ry.m[3][0] = 0;					  ry.m[3][1] = 0;  ry.m[3][2] = 0;					  ry.m[3][3] = 1;

        rz.m[0][0] = (float)Math.cos(z);  rz.m[0][1] = -(float)Math.sin(z);  rz.m[0][2] = 0;  rz.m[0][3] = 0;
        rz.m[1][0] = (float)Math.sin(z);  rz.m[1][1] = (float)Math.cos(z);   rz.m[1][2] = 0;  rz.m[1][3] = 0;
        rz.m[2][0] = 0;					  rz.m[2][1] = 0;					 rz.m[2][2] = 1;  rz.m[2][3] = 0;
        rz.m[3][0] = 0;					  rz.m[3][1] = 0;					 rz.m[3][2] = 0;  rz.m[3][3] = 1;

        m = rz.mul(ry.mul(rx)).getM();
        return this;
    }

    public Matrix4f initRotation(Vector3f forward, Vector3f up){
        Vector3f f = forward.normalized();

        Vector3f r = up.normalized();
        r = r.cross(f);

        Vector3f u = f.cross(r);

        return initRotation(f, u, r);
    }

    public Matrix4f initRotation(Vector3f forward, Vector3f up, Vector3f right){
        Vector3f f = forward.normalized();
        Vector3f r = right.normalized();
        Vector3f u = up.normalized();

        m[0][0] = r.getX();     m[0][1] = r.getY();    m[0][2] = r.getZ();    m[0][3] = 0;
        m[1][0] = u.getX();     m[1][1] = u.getY();    m[1][2] = u.getZ();    m[1][3] = 0;
        m[2][0] = f.getX();     m[2][1] = f.getY();    m[2][2] = f.getZ();    m[2][3] = 0;
        m[3][0] = 0;            m[3][1] = 0;           m[3][2] = 0;           m[3][3] = 1;

        return this;
    }

    public Matrix4f initTranslation(float x, float y, float z){
        m[0][0] = 1;    m[0][1] = 0;    m[0][2] = 0;    m[0][3] = x;
        m[1][0] = 0;    m[1][1] = 1;    m[1][2] = 0;    m[1][3] = y;
        m[2][0] = 0;    m[2][1] = 0;    m[2][2] = 1;    m[2][3] = z;
        m[3][0] = 0;    m[3][1] = 0;    m[3][2] = 0;    m[3][3] = 1;
        return this;
    }

    public Matrix4f initPerspectiveProjection(float fov, float width, float height, float zNear, float zFar){
        float ar = width/height;
        float tanHalfFOV = (float)Math.tan(Math.toRadians(fov / 2));
        float zRange = zNear - zFar;

        m[0][0] = 1.0f / (tanHalfFOV * ar);	m[0][1] = 0;				  m[0][2] = 0;	                    m[0][3] = 0;
        m[1][0] = 0;						m[1][1] = 1.0f / tanHalfFOV;  m[1][2] = 0;	                    m[1][3] = 0;
        m[2][0] = 0;						m[2][1] = 0;				  m[2][2] = (-zNear -zFar)/zRange;	m[2][3] = 2 * zFar * zNear / zRange;
        m[3][0] = 0;						m[3][1] = 0;				  m[3][2] = 1;	                    m[3][3] = 0;

        return this;
    }

    public Matrix4f initOrthographic(float left, float right, float bottom, float top, float near, float far){
        float width = right - left;
        float height = top - bottom;
        float depth = far - near;
        m[0][0] = 2/width;    m[0][1] = 0;           m[0][2] = 0;           m[0][3] = -(right + left)/width;
        m[1][0] = 0;          m[1][1] = 2/height;    m[1][2] = 0;           m[1][3] = -(top + bottom)/height;
        m[2][0] = 0;          m[2][1] = 0;           m[2][2] = -2/depth;    m[2][3] = -(near + far)/depth;
        m[3][0] = 0;          m[3][1] = 0;           m[3][2] = 0;           m[3][3] = 1;

        return this;
    }

//    public Matrix4f initCamera(Vector3f forward, Vector3f up){
//        Vector3f n = forward.normalized();
//        Vector3f u = up.normalized();
//        u = u.cross(n);
//        Vector3f v = n.cross(u);
//
//        m[0][0] = u.getX();    m[0][1] = u.getY();    m[0][2] = u.getZ();    m[0][3] = 0;
//        m[1][0] = v.getX();    m[1][1] = v.getY();    m[1][2] = v.getZ();    m[1][3] = 0;
//        m[2][0] = n.getX();    m[2][1] = n.getY();    m[2][2] = n.getZ();    m[2][3] = 0;
//        m[3][0] = 0;           m[3][1] = 0;           m[3][2] = 0;           m[3][3] = 1;
//        return this;
//    }

//    public Matrix4f initCamOrientation(Vector3f forward, Vector3f up){
//        Vector3f n = forward.normalized();
//        Vector3f u = up.normalized();
//        u = u.cross(n);
//        Vector3f v = n.cross(u);
//
//        m[0][0] = u.getX();    m[0][1] = v.getX();    m[0][2] = n.getX();    m[0][3] = 0;
//        m[1][0] = u.getY();    m[1][1] = v.getY();    m[1][2] = n.getY();    m[1][3] = 0;
//        m[2][0] = u.getZ();    m[2][1] = v.getZ();    m[2][2] = n.getZ();    m[2][3] = 0;
//        m[3][0] = 0;           m[3][1] = 0;           m[3][2] = 0;           m[3][3] = 1;
//        return this;
//    }
//
//    public Matrix4f initCamTrans(Vector3f pos){
//        m[0][0] = 1;    m[0][1] = 0;    m[0][2] = 0;    m[0][3] = 0;
//        m[1][0] = 0;    m[1][1] = 1;    m[1][2] = 0;    m[1][3] = 0;
//        m[2][0] = 0;    m[2][1] = 0;    m[2][2] = 1;    m[2][3] = 0;
//        m[3][0] = -pos.getX();    m[3][1] = -pos.getY();    m[3][2] = -pos.getZ();    m[3][3] = 1;
//        return this;
//    }

    public Matrix4f mul(Matrix4f r){
        Matrix4f res = new Matrix4f();
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                res.set(i, j,
                        m[i][0] * r.get(0, j) +
                        m[i][1] * r.get(1, j) +
                        m[i][2] * r.get(2, j) +
                        m[i][3] * r.get(3, j));
            }
        }
        return res;
    }

    public void set(int i, int j, float value){
        m[i][j] = value;
    }

    public float get(int i, int j){
        return m[i][j];
    }

    public float[][] getM() {
        float[][] res = new float[4][4];

        for(int i = 0; i < 4; i++)
            System.arraycopy(m[i], 0, res[i], 0, 4);

        return res;
    }
}
