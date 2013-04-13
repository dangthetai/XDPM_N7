package xdpm.game;

import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.opengl.texture.TextureManager;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import android.content.Context;

public class GoldSprite extends Sprites {
	public Sprites _Sprite;
	public AnimatedSprite sprite;
	private float giatri;
	private float sucnang;
	public GoldSprite(int Width,int Height,String Path,int Colum,int Row, float Giatri, float Sucnang, Context context,TextureManager Texturemanager){
		_Sprite=new Sprites(Width, Height, Path, Colum, Row, context, Texturemanager);
		giatri=Giatri;
		sucnang=Sucnang;
		sprite=_Sprite.sprite;
	}
	public float getGiatri(){
		return giatri;
	}
	public float getSucnang(){
		return sucnang;
	}	
	public TiledTextureRegion getTiledTextureRegion(){
		return _Sprite.getTiledTextureRegion();
	}
	public BitmapTextureAtlas getBitmapTextureAtlas(){
		return _Sprite.getBitmapTextureAtlas();
	}
	public AnimatedSprite getSprite(){
		return sprite;
	}
	public VertexBufferObjectManager getVertexBufferOjectManager(){
		return _Sprite.getVertexBufferOjectManager();
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
		return _Sprite.getWidth();
	}
	public float getHeight(){
		return _Sprite.getHeight();
	}
	public float getRotation(){
		return sprite.getRotation();
	}
	public void setVisible(Boolean pVisible){
		sprite.setVisible(pVisible);
	}
}
