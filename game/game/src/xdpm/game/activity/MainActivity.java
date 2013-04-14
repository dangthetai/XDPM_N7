package xdpm.game.activity;

import org.andengine.engine.camera.Camera;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.font.Font;
import org.andengine.ui.activity.SimpleBaseGameActivity;

import xdpm.game.object.HookObject;
import xdpm.game.object.Vector;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends SimpleBaseGameActivity {
	public static final int CAMERA_WIDTH = 800;
    public static final int CAMERA_HEIGHT = 480;
    public Font mFont;
    public Camera mCamera;
    public Scene mScene;
    public static MainActivity instance;
    public HookObject vang250;
    public HookObject oldman;
    public HookObject btstart;

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
		vang250=new HookObject(new Vector(25,40),new Vector(293,229),"menu/vang250.png",new Vector(1,1),this,this.getTextureManager());
		oldman=new HookObject(new Vector(vang250.getX()+245, vang250.getY()+10),new Vector(535,435),"menu/oldMan.png",new Vector(1,1),this,this.getTextureManager());
		btstart=new HookObject(new Vector(0,0),new Vector(348,41),"menu/startButton.png",new Vector(2,1),this,this.getTextureManager());
	}

	@Override
	protected Scene onCreateScene() {
		// TODO Auto-generated method stub
		//this.mEngine.registerUpdateHandler(new FPSLogger());
        mScene=new Scene();    
        mScene.setBackground(new Background(84/255f,141/255f,212/255f));
        //Add vao scene
        mScene.attachChild(vang250.getSprite());
        mScene.attachChild(oldman.getSprite());
        mScene.setTouchAreaBindingOnActionDownEnabled(true);//cho phép lắng nghe
        mScene.setTouchAreaBindingOnActionMoveEnabled(true);
        //Cai nay co animation nen phai new lai de them animation cho no
        btstart.sprite=new AnimatedSprite(vang250.getX()+63, vang250.getY()+98, btstart.getTiledTextureRegion() ,btstart.getVertexBufferOjectManager()){
        	private Boolean isPress=false;
        	@Override
	        public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
	            if(pSceneTouchEvent.getAction() == TouchEvent.ACTION_DOWN){
	            	if (!isPress){
	            		isPress=true;
	            		btstart.sprite.animate(100);//Chay animation
	            	}
	            }
	            if(pSceneTouchEvent.getAction() == TouchEvent.ACTION_UP){
	            	if (isPress){
	            		isPress=false;
	            		btstart.sprite.stopAnimation(0);//dung tai texture 0
	            		//doi sang activity khac 
	            		Intent I=new Intent(instance, PlayActivity.class);
	            		startActivity(I);
	            		finish();
	            		//them cai gi minh muon vao day bay gio la minh cho next sang man hinh khac nhe
	            	}
	            }
	            return true;
	        }
        };
        
        mScene.attachChild(btstart.getSprite());
        mScene.registerTouchArea(btstart.getSprite());//Phải đăng ký là cho phép sprite này được lắng nghe sự kiện        
		return mScene;
	}
}
