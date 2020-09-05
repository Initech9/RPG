package dev.codenmore.tilegame.entities.statics;

import java.awt.Graphics;

import dev.codenmore.tilegame.Handler;
import dev.codenmore.tilegame.gfx.Assets;
import dev.codenmore.tilegame.items.Item;
import dev.codenmore.tilegame.tiles.Tile;

public class Layer2 extends StaticEntity {

	public static Layer2[] layer2 = new Layer2[512];
	public int id;
	//public boolean renderLayer2 = true;
	
	public Layer2(Handler handler, float x, float y, int id, boolean renderLayer2) {
		super(handler, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT);
		
//		bounds.x = 3;
		bounds.y = (int) (height * 2f);
		bounds.width = width - width;
		bounds.height = (int) (height * 3f );
		
		this.renderLayer2 = renderLayer2;
		layer2[id] = this;
		
		this.id = id;
	}

	@Override
	public void tick() {
		
	}
	
	@Override
	public void die(){
		handler.getWorld().getItemManager().addItem(Item.rockItem.createNew((int) x, (int) y));
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.tilesBufferLayer2[id], (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
	}
	

	public void setRenderId(boolean set, int id){
		layer2[id].renderLayer2 = set;
				
	}


}
