package dev.codenmore.tilegame.tiles;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import dev.codenmore.tilegame.gfx.Assets;

public class Tile {
	
	//STATIC STUFF HERE
	
	public static Tile[] tiles = new Tile[512];
	

	
	public static Tile dirtTile = new DirtTile(1);
	public static Tile grassTile = new GrassTile(2);
	public static Tile rockTile = new RockTile(3);
	
	
	public boolean solid=false;
	//public static Tile 

	
	//CLASS
	
	public static final int TILEWIDTH = 64, TILEHEIGHT = 64;
	
	protected BufferedImage texture;
	protected final int id;
	
	public Tile(BufferedImage texture, int id){
		this.texture = texture;
		this.id = id;
		
		tiles[id] = this;
		
		
	}
	
	public void tick(){
		
	}
	
	public void render(Graphics g, int x, int y){
		g.drawImage(texture, x, y, TILEWIDTH, TILEHEIGHT, null);
	}
	
	public boolean isSolid(){
		return this.solid;
	}
	
	public void setSolid(boolean set){
		this.solid = set;
	}
	
	public void setSolidId(boolean set, int id){
		tiles[id].solid = set;
				
	}
	
	public int getId(){
		return id;
	}
	
}
