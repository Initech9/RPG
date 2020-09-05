package dev.codenmore.tilegame.entities.statics;

import java.awt.Graphics;

import dev.codenmore.tilegame.Handler;
import dev.codenmore.tilegame.gfx.Assets;
import dev.codenmore.tilegame.items.Item;
import dev.codenmore.tilegame.tiles.Tile;
import dev.codenmore.tilegame.utils.Utils;
import dev.codenmore.tilegame.entities.EntityManager;
import dev.codenmore.tilegame.entities.creatures.Player;

public class Teleport extends StaticEntity {

	public static Teleport[] teleports = new Teleport[5];
	

	//public int id; 
	private EntityManager entityManager;
	
	public Teleport(Handler handler, float x, float y, float toX, float toY, int world) {
		super(handler, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT);
		
		bounds.x = 3;
		bounds.y = (int) (height / 2f);
		bounds.width = width - 6;
		bounds.height = (int) (height - height / 2f);
		
		this.world = world;
	
		this.toX = toX;
		this.toY = toY;
		

		
		this.health = 99999;
		
		teleports[world] = this;
	}
	
	//entityManager.
	
	//loadWorld("res/worlds/world2.txt");


	
	
	@Override
	public void tick() {
		
	}
	
	@Override
	public void die(){
		handler.getWorld().getItemManager().addItem(Item.rockItem.createNew((int) x, (int) y));
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.teleport, (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
	}
	

	

}
