package xdpm.game;

import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.opengl.texture.TextureManager;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import android.content.Context;

public class  Sprites {
	//Lop khoi tao cai sprite
	private BitmapTextureAtlas bitmapTextureAtlas;
	private TiledTextureRegion tiledTextureRegion;
	private TextureManager textureManager;
	private VertexBufferObjectManager vertexBufferObject;
	public AnimatedSprite sprite;
	private float width;
	private float height;
	
	public Sprites(){
		
	}
	public Sprites(int Width,int Height,String Path,int Colum,int Row, Context context,TextureManager Texturemanager){
		
		//Rong cao duongdan socot sohang activitycanload texturecuascene
		this.Load(Width, Height, Path, Colum, Row, context, Texturemanager);
	}
	public void Load(int Width,int Height,String Path,int Colum,int Row, Context context,TextureManager Texturemanager){
		textureManager=Texturemanager;
		this.bitmapTextureAtlas = new BitmapTextureAtlas(textureManager, Width, Height, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
        //Load ảnh
		this.tiledTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(this.bitmapTextureAtlas, context, Path, 0, 0, Colum, Row);
        textureManager.loadTexture(this.bitmapTextureAtlas); 
        sprite=new AnimatedSprite(0, 0, tiledTextureRegion ,vertexBufferObject);
        width=Width;
        height=Height;
        
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
	public void setPoint(float X,float Y){
		sprite.setPosition(X, Y);
	}
	public float getX(){
		return sprite.getX();
	}
	public float getY(){
		return sprite.getY();
	}
	public float getWidth(){
		return width;
	}
	public float getHeight(){
		return height;
	}
	public float getRotation(){
		return sprite.getRotation();
	}
	public void setVisible(Boolean pVisible){
		sprite.setVisible(pVisible);
	}
}
