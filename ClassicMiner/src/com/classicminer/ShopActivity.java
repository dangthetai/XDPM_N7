package com.classicminer;

import java.util.ArrayList;

import org.andengine.engine.camera.Camera;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.primitive.Vector2;
import org.andengine.entity.scene.IOnSceneTouchListener;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.sprite.batch.SpriteBatch;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TickerText;
import org.andengine.entity.text.TickerText.TickerTextOptions;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.font.FontFactory;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.texture.region.TextureRegion;
import org.andengine.ui.activity.SimpleBaseGameActivity;

import android.graphics.Typeface;
import android.opengl.GLES20;

public class ShopActivity extends SimpleBaseGameActivity implements IOnSceneTouchListener {
	
	private Camera camera;
    private Scene scene;
	private Font font;
    
    private BitmapTextureAtlas backgroundAtlas;
    private TextureRegion backgroundRegion;
    
    private BitmapTextureAtlas salesManAtlas;
    private ITiledTextureRegion salesManRegion;
    
    private BitmapTextureAtlas nextLevelAtlas;
    private TextureRegion nextLevelRegion;
    
    private BitmapTextureAtlas dynamicAtlas;
    private ITiledTextureRegion dynamicRegion;
 
    private BitmapTextureAtlas energyWaterAtlas;
    private ITiledTextureRegion energyWaterRegion;
    
    private BitmapTextureAtlas luckyFlowerAtlas;
    private ITiledTextureRegion luckyFlowerRegion;
    
    private BitmapTextureAtlas diamondWaterAtlas;
    private ITiledTextureRegion diamondWaterRegion;
    
    private BitmapTextureAtlas rockBookAtlas;
    private ITiledTextureRegion rockBookRegion;
    private Shop shop;
    private Text description;
    Sprite nextLevel;
	@Override
	public EngineOptions onCreateEngineOptions() {
		camera = new Camera(0, 0, Global.CAMERA_WIDTH, Global.CAMERA_HEIGHT);
		return new EngineOptions(true, ScreenOrientation.LANDSCAPE_SENSOR,
				new RatioResolutionPolicy(Global.CAMERA_WIDTH, Global.CAMERA_HEIGHT), camera);
	}

	@Override
	protected void onCreateResources() {
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
		backgroundAtlas = new BitmapTextureAtlas(this.getTextureManager(), 1024, 512);
		backgroundRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(backgroundAtlas, this, "backgroundShop.png", 0, 0);
		backgroundAtlas.load();
		
		salesManAtlas = new BitmapTextureAtlas(this.getTextureManager(), 1024, 256);
		salesManRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(salesManAtlas, this, "salesMan.png", 0, 0, 3, 1);
		salesManAtlas.load();
		
		nextLevelAtlas = new BitmapTextureAtlas(this.getTextureManager(), 256, 256);
		nextLevelRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(nextLevelAtlas, this, "nextLevel.png", 0, 0);
		nextLevelAtlas.load();
		
		this.font = FontFactory.create(this.getFontManager(), this.getTextureManager(), 256, 256, TextureOptions.BILINEAR, Typeface.create(Typeface.DEFAULT, Typeface.BOLD), 24);
		this.font.load();
		
		dynamicAtlas =  new BitmapTextureAtlas(this.getTextureManager(), 1024, 1024);
		dynamicRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(dynamicAtlas, this, "dynamic.png", 0, 0, 2, 1);
		dynamicAtlas.load();
		
		energyWaterAtlas =  new BitmapTextureAtlas(this.getTextureManager(), 1024, 1024);
		energyWaterRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(energyWaterAtlas, this, "energyWater.png", 0, 0, 2, 1);
		energyWaterAtlas.load();
		
		luckyFlowerAtlas =  new BitmapTextureAtlas(this.getTextureManager(), 1024, 1024);
		luckyFlowerRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(luckyFlowerAtlas, this, "luckyFlower.png", 0, 0, 2, 1);
		luckyFlowerAtlas.load();
		
		diamondWaterAtlas =  new BitmapTextureAtlas(this.getTextureManager(), 1024, 1024);
		diamondWaterRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(diamondWaterAtlas, this, "diamondWater.png", 0, 0, 2, 1);
		diamondWaterAtlas.load();
		
		rockBookAtlas =  new BitmapTextureAtlas(this.getTextureManager(), 1024, 1024);
		rockBookRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(rockBookAtlas, this, "rockBook.png", 0, 0, 2, 1);
		rockBookAtlas.load();
	}

	
	@Override
	protected Scene onCreateScene() {
		scene = new Scene();
		
		Sprite background = new Sprite(0, 0, backgroundRegion, this.getVertexBufferObjectManager());
		scene.attachChild(background);
		AnimatedSprite salesMan = new AnimatedSprite(560, 156, salesManRegion, this.getVertexBufferObjectManager());
		salesMan.animate(new long[] {1000, 100, 100});
		scene.attachChild(salesMan);
		
		final Text text = new TickerText(30, 110, this.font,"Chạm một món đồ để xem, chạm lần nữa để\n" + " mua. Chạm nút qua màn nếu bạn muốn rời khỏi", new TickerTextOptions(10), this.getVertexBufferObjectManager());
		text.setBlendFunction(GLES20.GL_SRC_ALPHA, GLES20.GL_ONE_MINUS_SRC_ALPHA);
		scene.attachChild(text);
		
		nextLevel = new Sprite(643, 52, nextLevelRegion, getVertexBufferObjectManager());
		scene.attachChild(nextLevel);
		scene.registerTouchArea(nextLevel);
		
		ArrayList<ITiledTextureRegion> textureRegions = new ArrayList<ITiledTextureRegion>();
		textureRegions.add(dynamicRegion);
		textureRegions.add(energyWaterRegion);
		textureRegions.add(luckyFlowerRegion);
		textureRegions.add(diamondWaterRegion);
		textureRegions.add(rockBookRegion);
		shop = new Shop(new Vector2(72, 344), font, textureRegions, getVertexBufferObjectManager());
		shop.RandomItems();
		scene.attachChild(shop);
		
		for (ShopItem shopItem : shop.shopItems) {
			scene.registerTouchArea(shopItem);
		}
		scene.setOnSceneTouchListener(this);
		return scene;
	}
	
	@Override
	public boolean onSceneTouchEvent(Scene arg0, TouchEvent arg1) {
		if (arg1.isActionDown())
		{
			float x = arg1.getX();
			float y = arg1.getY();
			
			if (nextLevel.contains(x, y))
			{
				scene.detachChild(nextLevel);
			}
			
			for(int i = 0; i<shop.shopItems.size(); i++)
			{
				if (shop.shopItems.get(i).contains(x, y))
				{
					if (description != null)
						scene.detachChild(description);
					shop.itemSeleted = i;
					shop.shopItems.get(i).pressCount++;
					shop.shopItems.get(i).setCurrentTileIndex(1);
		
					description = new Text(37, 380, font, shop.shopItems.get(i).description, getVertexBufferObjectManager());
					scene.attachChild(description);
	
					
					for(int j=0; j<shop.shopItems.size(); j++)
					{
						if (j != i)
						{
							shop.shopItems.get(j).setCurrentTileIndex(0);
							if (shop.shopItems.get(j).pressCount != 0)
								shop.shopItems.get(j).pressCount = 0;
						}
					}
							
					if (shop.shopItems.get(i).pressCount == 2)
					{
						shop.RemoveItem(shop.shopItems.get(i));
						scene.detachChild(description);
						shop.shopItems.remove(i);
					}
				}
			}
		}
		return false;
	}

}
