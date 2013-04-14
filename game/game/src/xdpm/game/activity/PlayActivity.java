package xdpm.game.activity;

import java.util.ArrayList;

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

import xdpm.game.object.HookObject;
import xdpm.game.object.MapObject;
import xdpm.game.object.Vector;
import android.os.Bundle;

public class PlayActivity extends SimpleBaseGameActivity {
	public static final int CAMERA_WIDTH = 800;
    public static final int CAMERA_HEIGHT = 480;
    public Font mFont;
    public Camera mCamera;
    public static Scene mScene;
    public static PlayActivity instance;
    public HookObject hook;
    private ArrayList<MapObject> _sprite;
    private int _index=-1;
    private Boolean isPress=false;
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
		hook=new HookObject(new Vector(400,50),new Vector(33,61),"play/moccau.png",new Vector(1,1),this,this.getTextureManager());
		hook.setRotation(-75);
		_sprite=new ArrayList<MapObject>();
		MapObject temp=new MapObject(new Vector(200, 300),new Vector(59, 46),"play/vang50.png",new Vector(1, 1),50,2,this,this.getTextureManager());
		_sprite.add(temp);
		temp=new MapObject(new Vector(100, 400),new Vector(59, 46),"play/vang50.png",new Vector(1, 1),50,2,this,this.getTextureManager());
		_sprite.add(temp);
		temp=new MapObject(new Vector(400, 200),new Vector(59, 46),"play/vang50.png",new Vector(1, 1),250,4,this,this.getTextureManager());
		_sprite.add(temp);
		temp=new MapObject(new Vector(600, 250),new Vector(59, 46),"play/vang50.png",new Vector(1, 1),250,4,this,this.getTextureManager());
		_sprite.add(temp);
	}

	@Override
	protected Scene onCreateScene() {
		// TODO Auto-generated method stub
        mScene=new Scene();    
        mScene.setBackground(new Background(84/255f,141/255f,212/255f));
        //Add vao scene
        mScene.attachChild(hook.getSprite());
        for (int i=0;i<_sprite.size();i++)
        	mScene.attachChild(_sprite.get(i).getSprite());
        mScene.setTouchAreaBindingOnActionDownEnabled(true);//cho phép lắng nghe
        mScene.setTouchAreaBindingOnActionMoveEnabled(true);
        mScene.setOnSceneTouchListener(new IOnSceneTouchListener() {                
                public boolean onSceneTouchEvent(final Scene pScene, final TouchEvent pSceneTouchEvent) {
                    //Cai nay la de an vao man hinh lam gi thi lam nhe
                	if (pSceneTouchEvent.getAction()==TouchEvent.ACTION_DOWN){
                		isPress=true;
                		_index=-1;
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
                	hook.Move();
                	if (hook.getX()>CAMERA_WIDTH||hook.getX()<0||hook.getY()>CAMERA_HEIGHT)
                		hook.isBack=true;
                	if (_index==-1)
                	{
	                	for (int i=0;i<_sprite.size();i++)
	                		if (hook.sprite.collidesWith(_sprite.get(i).sprite)){
	                			_index=i;
	                			hook.isBack=true;
	                			hook.setSpeed(hook.getSpeed()-_sprite.get(i).getWeight());
	                			break;
	                		}
                	}
                	else{
                		_sprite.get(_index).Move(hook);
                	}
                	if (hook.isStop())
                	{
                		isPress=false;
                		if (_index>-1){
                			_sprite.get(_index).setPoint(new Vector(-100, -100));
                			_sprite.get(_index).setVisible(false);
                			hook.setSpeed(hook.getSpeed()+_sprite.get(_index).getWeight());
                		}
                		hook.isBack=false;
                	}
                }
                else{
                	hook.Rotation();
                }
			}
		});
		return mScene;
	}
}
