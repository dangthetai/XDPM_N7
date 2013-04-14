package xdpm.game.object;

import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.opengl.texture.TextureManager;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import android.content.Context;

public class  MapObject {
	//Lop khoi tao cai sprite
	private BitmapTextureAtlas bitmapTextureAtlas;
	private TiledTextureRegion tiledTextureRegion;
	private TextureManager textureManager;
	private VertexBufferObjectManager vertexBufferObject;
	public AnimatedSprite sprite;
	private Vector Size;
	private Vector Point;
	private Vector Table;
	private String Path;
	private float Value;
	private float Weight;
	public MapObject(){
		
	}
	public MapObject(Vector point,Vector size,String path,Vector table,float value, float weight, Context context,TextureManager Texturemanager){
		
		this.Load(point, size, path, table, value, weight, context, Texturemanager);
	}
	public void Load(Vector point,Vector size,String path,Vector table,float value, float weight, Context context,TextureManager Texturemanager){
		textureManager=Texturemanager;
		Size=size;	//X=witdh, Y=height
		Point=point;	//X=left Y=top
		Table=table;	//X=colum Y=row
		Value=value;
		Weight=weight;
		Path=path;
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
		Point=point;
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
	public float getValue(){
		return Value;
	}
	public float getWeight(){
		return Weight;
	}
	public void Move(HookObject hook){
		float SpeedY=hook.getHeight()*(float)Math.cos(hook.getRotation()/180*Math.PI);
    	float SpeedX=hook.getHeight()*(float)Math.sin(hook.getRotation()/180*Math.PI);
    	sprite.setRotation(hook.getRotation());
    	sprite.setPosition(hook.getX()-SpeedX-(sprite.getWidth()-hook.getWidth())/2,hook.getY()+SpeedY);
	}
}
