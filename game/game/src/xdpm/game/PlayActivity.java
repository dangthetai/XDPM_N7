package xdpm.game;

import org.andengine.engine.camera.Camera;
import org.andengine.engine.handler.IUpdateHandler;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.scene.IOnSceneTouchListener;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.font.Font;
import org.andengine.ui.activity.SimpleBaseGameActivity;

import android.os.Bundle;

public class PlayActivity extends SimpleBaseGameActivity {
	public static final int CAMERA_WIDTH = 800;
    public static final int CAMERA_HEIGHT = 480;
    public Font mFont;
    public Camera mCamera;
    public static Scene mScene;
    public static PlayActivity instance;
    public Sprites vang50;
    public Sprites oldman;
    public Sprites btstart;

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

	
	public EngineOptions onCreateEngineOptions() {
		// TODO Auto-generated method stub
		instance = this;
		mCamera = new Camera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);
        return new EngineOptions(true, ScreenOrientation.LANDSCAPE_SENSOR,
                new RatioResolutionPolicy(CAMERA_WIDTH, CAMERA_HEIGHT), mCamera);
	}

	@Override
	protected void onCreateResources() {
		// TODO Auto-generated method stub
		vang50=new Sprites(293,229,"play/vang50.png",1,1,this,this.getTextureManager());
		
	}
	//luu toa do luc cham
	private float pX=0;
    private float pY=0;
    
    //kiem tra da cham xuong hay chua
    private Boolean isPress=false;
	@Override
	protected Scene onCreateScene() {
		// TODO Auto-generated method stub
		//this.mEngine.registerUpdateHandler(new FPSLogger());
        mScene=new Scene();    
        mScene.setBackground(new Background(84/255f,141/255f,212/255f));
        //Add vao scene
        mScene.attachChild(vang50.getSprite());
        
        mScene.setTouchAreaBindingOnActionDownEnabled(true);//cho phép lắng nghe
        mScene.setTouchAreaBindingOnActionMoveEnabled(true);
        
        mScene.setOnSceneTouchListener(new IOnSceneTouchListener() {                
                public boolean onSceneTouchEvent(final Scene pScene, final TouchEvent pSceneTouchEvent) {
                    //Cai nay la de an vao man hinh lam gi thi lam nhe
                	if (pSceneTouchEvent.getAction()==TouchEvent.ACTION_DOWN){
                		isPress=true;
                		pX=pSceneTouchEvent.getX();
                		pY=pSceneTouchEvent.getY();
                	}
                    return true;
                }
        });
        mScene.registerUpdateHandler(new IUpdateHandler() {
			
			public void reset() {
				// TODO Auto-generated method stub
				
			}
			
			public void onUpdate(float arg0) {
				// TODO Auto-generated method stub
				try {
                    //Tạm dừng cập nhật trong 10 ms
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }     
                if (isPress){
                	//tinh chieu dai doan duong di quy dinh 10ms thi di duoc 20px nhe
                	float longX=(pX-vang50.getX());	//Tinh khoang cach theo chieu x
        			float longY=(pY-vang50.getY());	//Tinh khoang cach theo chieu y
                	int step=(int)Math.sqrt(longX*longX+longY*longY)/20;	//tinh con bao nhieu buoc nua la cham toi dich
                	if (step>0)
                		vang50.setPoint(vang50.getX()+longX/step, vang50.getY()+longY/step);	//moi lan con them buoc nhay
                }
			}
		});
		return mScene;
	}
}
