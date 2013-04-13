package xdpm.game;

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

import android.os.Bundle;

public class PlayActivity extends SimpleBaseGameActivity {
	public static final int CAMERA_WIDTH = 800;
    public static final int CAMERA_HEIGHT = 480;
    public Font mFont;
    public Camera mCamera;
    public static Scene mScene;
    public static PlayActivity instance;
    public Sprites moccau;
    private int isBack=0;
    private float moodule=-1;
    private ArrayList<GoldSprite> _sprite;
    private int _index=-1;
	private float longXY=6;	//cai nay la 10ms di duoc bao nhieu
	//luu toa do luc cham
    private float pY;
    //kiem tra da cham xuong hay chua
    private Boolean isPress=false;
	
    public void Rotation(){
    	if (Math.abs(moccau.sprite.getRotation())>75){
        	moodule=-moodule;
        }
        moccau.setRotation(moccau.getRotation()+moodule);
    }
    
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
		moccau=new Sprites(33,61,"play/moccau.png",1,1,this,this.getTextureManager());
		moccau.setRotation(-75);
		moccau.setPoint(400, 50);
		pY=moccau.getY();
		_sprite=new ArrayList<GoldSprite>();
		GoldSprite temp=new GoldSprite(59,46,"play/vang50.png",1,1,50,2,this,this.getTextureManager());
		temp.setPoint(200, 300);
		_sprite.add(temp);
		temp=new GoldSprite(293,229,"menu/vang250.png",1,1,250,5,this,this.getTextureManager());
		temp.setPoint(100, 400);
		_sprite.add(temp);
		/*temp=new GoldSprite(293,229,"menu/vang250.png",1,1,250,4,this,this.getTextureManager());
		temp.setPoint(300, 200);
		_sprite.add(temp);*/
	}

	@Override
	protected Scene onCreateScene() {
		// TODO Auto-generated method stub
		//this.mEngine.registerUpdateHandler(new FPSLogger());
        mScene=new Scene();    
        mScene.setBackground(new Background(84/255f,141/255f,212/255f));
        //Add vao scene
        mScene.attachChild(moccau.getSprite());
        for (int i=0;i<_sprite.size();i++)
        	mScene.attachChild(_sprite.get(i).getSprite());
        mScene.setTouchAreaBindingOnActionDownEnabled(true);//cho phép lắng nghe
        mScene.setTouchAreaBindingOnActionMoveEnabled(true);
        
        mScene.setOnSceneTouchListener(new IOnSceneTouchListener() {                
                public boolean onSceneTouchEvent(final Scene pScene, final TouchEvent pSceneTouchEvent) {
                    //Cai nay la de an vao man hinh lam gi thi lam nhe
                	if (pSceneTouchEvent.getAction()==TouchEvent.ACTION_DOWN){
                		longXY=6;
                		isPress=true;
                		isBack=0;
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
                	float longY=longXY*(float)Math.cos(moccau.sprite.getRotation()/180*Math.PI);
                	float longX=longXY*(float)Math.sin(moccau.sprite.getRotation()/180*Math.PI);
                	if (moccau.getX()>CAMERA_WIDTH||moccau.getX()<0||moccau.getY()>CAMERA_HEIGHT)
                		isBack=1;
                	if (_index==-1)
                	{
	                	for (int i=0;i<_sprite.size();i++)
	                		if (moccau.sprite.collidesWith(_sprite.get(i).sprite)){
	                			_index=i;
	                			isBack=1;
	                			longXY=longXY-_sprite.get(i).getSucnang();
	                			break;
	                		}
                	}
                	if (isBack==0)
                		moccau.setPoint(moccau.getX()- longX, moccau.getY()+ longY);
                	else
                	{
                		moccau.setPoint(moccau.getX()+ longX, moccau.getY()- longY);
                		if (_index>-1){
                			_sprite.get(_index).setRotation(moccau.getRotation());
                			_sprite.get(_index).setPoint(_sprite.get(_index).getX()+longX,_sprite.get(_index).getY()-longY);
                		}
                	}
                	
                	if (moccau.getY()<=pY)
                	{
                		isPress=false;
                		if (_index>-1){
                			_sprite.get(_index).setPoint(-100, -100);
                			_sprite.get(_index).setVisible(false);
                		}
                		moccau.setPoint(moccau.getX(), pY+1);	
                	}
                }
                else{
                	Rotation();
                }
                	
                
			}
		});
		return mScene;
	}
}
