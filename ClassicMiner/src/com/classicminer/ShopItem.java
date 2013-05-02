package com.classicminer;

import org.andengine.entity.sprite.TiledSprite;
import org.andengine.entity.text.Text;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;


public class ShopItem extends TiledSprite {
	
	public static enum ShopItemType
	{
		Dynamic, EnergyWater, LuckyFlower, DiamondWater, RockBook
	}
	public Text cost;
	public int pressCount = 0;
	public ShopItemType type;
	public String description;
	public ShopItem(ShopItemType _type, float pX, float pY,
			ITiledTextureRegion pTiledTextureRegion,
			VertexBufferObjectManager vertexBufferObjectManager) {
		super(pX, pY, pTiledTextureRegion, vertexBufferObjectManager);
		type = _type;
		setStringDescription();
	}
	
	public String setStringDescription()
	{
		this.description = "";
		switch(type)
		{
			case DiamondWater:
				this.description = "Nước đánh bóng kim cương, tăng giá trị kim cương kéo được";
				break;
			case Dynamic:
				this.description = "Pháo giúp phá hủy đá, các chướng ngại vật";
				break;
			case EnergyWater:
				this.description = "Nước tăng lực tăng sức kéo người chơi, giúp kéo nhanh hơn";
				break;
			case LuckyFlower:
				this.description = "Hoa may mắn giúp tăng cơ hội nhận được những thứ có giá trị trong túi đồ ngẫu nhiên.";
				break;
			case RockBook:
				this.description = "Bộ sưu tập đá giúp tăng giá trị vàng gấp 2 lần";
				break;
			default:
				break;
		}
		return this.description;
	}
}
