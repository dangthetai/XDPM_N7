package com.classicminer;

import java.util.ArrayList;
import java.util.Random;

import org.andengine.entity.Entity;
import org.andengine.entity.primitive.Vector2;
import org.andengine.entity.text.Text;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import com.classicminer.ShopItem.ShopItemType;

public class Shop extends Entity{
	public int nItems = 5;
	public ArrayList<ShopItem> shopItems = new ArrayList<ShopItem>();;
	public int itemSeleted = -1;
	public Vector2 offSet;
	private ArrayList<ITiledTextureRegion> textureRegions = new ArrayList<ITiledTextureRegion>();
	private VertexBufferObjectManager vertex;
	private Font font;
	private Text description;
	
	public Shop(Vector2 _offSet, Font _font, ArrayList<ITiledTextureRegion> _textureRegions, VertexBufferObjectManager _vertex )
	{
		offSet = _offSet;
		textureRegions = _textureRegions;
		vertex = _vertex;
		font = _font; 
	}
	
	public void RandomItems()
	{
		Random r = new Random();
		int count = 0;
		Vector2 position = offSet;
		ShopItem[] temp = new ShopItem[nItems];
		while(count < r.nextInt(nItems) + 1)
		{
			int index = r.nextInt(nItems);
			switch(index)
			{
				case 0:
				{
					if (temp[index] == null)
					{
						temp[index] = new ShopItem(ShopItemType.Dynamic, position.x, position.y - textureRegions.get(index).getHeight(), textureRegions.get(index), vertex);
						int cost = Random(20, 300);
						temp[index].cost = new Text(position.x, position.y + 3, font, Integer.toString(cost) + "$", vertex);
						position.x += textureRegions.get(index).getWidth() + 10;
						count++;
						shopItems.add(temp[index]);
						this.attachChild(temp[index]);
						this.attachChild(temp[index].cost);
					}
					break;
				}
				case 1:
				{
					if (temp[index] == null)
					{
						temp[index] = new ShopItem(ShopItemType.EnergyWater, position.x, position.y - textureRegions.get(index).getHeight(), textureRegions.get(index), vertex);
						int cost = Random(20, 300);
						temp[index].cost = new Text(position.x, position.y + 3, font, Integer.toString(cost) + "$", vertex);
						position.x += textureRegions.get(index).getWidth() + 10;
						count++;
						shopItems.add(temp[index]);
						this.attachChild(temp[index]);
						this.attachChild(temp[index].cost);
					}
					break;
				}
				case 2:
				{
					if (temp[index] == null)
					{
						temp[index] = new ShopItem(ShopItemType.LuckyFlower, position.x, position.y - textureRegions.get(index).getHeight(), textureRegions.get(index), vertex);
						int cost = Random(20, 300);
						temp[index].cost = new Text(position.x, position.y + 3, font, Integer.toString(cost) + "$", vertex);
						position.x += textureRegions.get(index).getWidth() + 10;
						count++;
						shopItems.add(temp[index]);
						this.attachChild(temp[index]);
						this.attachChild(temp[index].cost);
					}
					break;
				}
				case 3:
				{
					if (temp[index] == null)
					{
						temp[index] = new ShopItem(ShopItemType.DiamondWater, position.x, position.y - textureRegions.get(index).getHeight(), textureRegions.get(index), vertex);
						int cost = Random(20, 300);
						temp[index].cost = new Text(position.x, position.y + 3, font, Integer.toString(cost) + "$", vertex);
						position.x += textureRegions.get(index).getWidth() + 10;
						count++;
						shopItems.add(temp[index]);
						this.attachChild(temp[index]);
						this.attachChild(temp[index].cost);
					}
					break;
				}
				case 4:
				{
					if (temp[index] == null)
					{
						temp[index] = new ShopItem(ShopItemType.RockBook, position.x, position.y - textureRegions.get(index).getHeight(), textureRegions.get(index), vertex);
						int cost = Random(20, 300);
						temp[index].cost = new Text(position.x, position.y + 3, font, Integer.toString(cost) + "$", vertex);
						position.x += textureRegions.get(index).getWidth() + 10;
						count++;
						shopItems.add(temp[index]);
						this.attachChild(temp[index]);
						this.attachChild(temp[index].cost);
					}
					break;
				}
			}
		}
	}
	
	public void RemoveItem(ShopItem item)
	{
		this.detachChild(item.cost);
		this.detachChild(item);
	}
	
	public int Random(int min, int max)
	{
		Random r = new Random();
		if (r.nextInt(max) + 1 < min)
			return min;
		return r.nextInt(max) + 1;
	}
}
