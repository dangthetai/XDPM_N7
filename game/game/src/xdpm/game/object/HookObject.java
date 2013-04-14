package xdpm.game.object;

import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.opengl.texture.TextureManager;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import android.content.Context;

public class  HookObject {
	//Lop khoi tao cai sprite
	private BitmapTextureAtlas bitmapTextureAtlas;
	private TiledTextureRegion tiledTextureRegion;
	private TextureManager textureManager;
	private VertexBufferObjectManager vertexBufferObject;
	public AnimatedSprite sprite;
	private Vector Size;
	public Vector Point;
	private Vector Table;
	private String Path;
	private float Speed;
	public Boolean isBack=false;
	public float moodule=-1;
	
	public HookObject(){
		
	}
	public HookObject(Vector point,Vector size,String path,Vector table, Context context,TextureManager Texturemanager){
		
		this.Load(point, size, path, table, context, Texturemanager);
	}
	public void Load(Vector point,Vector size,String path,Vector table, Context context,TextureManager Texturemanager){
		textureManager=Texturemanager;
		Size=size;	//X=witdh, Y=height
		Point=point;	//X=left Y=top
		Table=table;	//X=colum Y=row
		Path=path;
		Speed=8;
		this.bitmapTextureAtlas = new BitmapTextureAtlas(textureManager, (int)Size.X, (int)Size.Y, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.tiledTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(this.bitmapTextureAtlas, context, Path, 0, 0, (int)Table.X, (int)Table.Y);
        textureManager.loadTexture(this.bitmapTextureAtlas); 
        sprite=new AnimatedSprite(Point.X, Point.Y, tiledTextureRegion ,vertexBufferObject);
	}
	public TiledTextureRegion getTiledTextureRegion(){
		return tiledTextureRegion;
	}
	public BitmapTextureAtlas getBitmapTextureAtlas(){
		return bitmapTextureAtlas;
	}
	public AnimatedSprite getSprite(){
		return sprite;
	}
	public VertexBufferObjectManager getVertexBufferOjectManager(){
		return vertexBufferObject;
	}
	public void setRotation(float X){
		sprite.setRotation(X);
	}
	public void setPoint(Vector point){
		sprite.setPosition(Point.X, Point.Y);
	}
	public float getX(){
		return sprite.getX();
	}
	public float getY(){
		return sprite.getY();
	}
	public float getWidth(){
		return Size.X;
	}
	public float getHeight(){
		return Size.Y;
	}
	public float getRotation(){
		return sprite.getRotation();
	}
	public void setVisible(Boolean pVisible){
		sprite.setVisible(pVisible);
	}
	public void setSpeed(float speed){
		Speed=speed;
	}
	public float getSpeed(){
		return Speed;
	}
	public void Rotation(){
    	if (Math.abs(sprite.getRotation())>75){
        	moodule=-moodule;
        }
        sprite.setRotation(sprite.getRotation()+moodule);
	}
	public void Move(){
		float SpeedY=Speed*(float)Math.cos(sprite.getRotation()/180*Math.PI);
    	float SpeedX=Speed*(float)Math.sin(sprite.getRotation()/180*Math.PI);
    	if (isBack){
    			sprite.setPosition(sprite.getX()+SpeedX, sprite.getY()-SpeedY);
    	}
    	else{
    		sprite.setPosition(sprite.getX()-SpeedX, sprite.getY()+SpeedY);
    	}
	}
	public Boolean isStop(){
		if (sprite.getY()<=Point.Y){
			sprite.setPosition(Point.X,Point.Y);
			return true;
		}
		else{
			return false;
		}
	}
}
