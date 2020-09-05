package dev.codenmore.tilegame.worlds;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import dev.codenmore.tilegame.Handler;
import dev.codenmore.tilegame.entities.Entity;
import dev.codenmore.tilegame.entities.EntityManager;
import dev.codenmore.tilegame.entities.creatures.NPC;
import dev.codenmore.tilegame.entities.creatures.Player;
import dev.codenmore.tilegame.entities.creatures.Zombie;
import dev.codenmore.tilegame.entities.projectiles.Fireball;
import dev.codenmore.tilegame.entities.statics.Layer2;
import dev.codenmore.tilegame.entities.statics.Rock;
import dev.codenmore.tilegame.entities.statics.Teleport;
import dev.codenmore.tilegame.entities.statics.Tree;
import dev.codenmore.tilegame.gfx.Assets;
import dev.codenmore.tilegame.items.ItemManager;
import dev.codenmore.tilegame.states.State;
import dev.codenmore.tilegame.tiles.Tile;
import dev.codenmore.tilegame.utils.Utils;

public class World {

	private Handler handler;
	private int width, height;
	private int spawnX, spawnY;
	private int[][] tiles;
	private int[][] tiles2;
	// Entities
	private EntityManager entityManager;
	// Item
	private ItemManager itemManager;

	// public boolean worldChanged;
	public int currentWorld;
	// public World world2;
	public String path;
	public boolean renderLayer2 = false;
	public boolean worldChanged = false;
	
	int countFireballs = 0;
	public JButton button = new JButton();

	//player height and grow count when button pressed
	double heightGrow = 64;
	int growCount = 0;
	boolean clicked = false;
	

	public World(Handler handler, String path) {
		this.handler = handler;
		currentWorld = 1;
		entityManager = new EntityManager(handler, new Player(handler, 30, 100));

		itemManager = new ItemManager(handler);
		// Temporary entity code!
		this.path = path;

		spawnX = 775;
		spawnY = 200;

		startWorld(spawnX, spawnY);
		button = handler.getGame().display.button;
	}

	public void tick() {

		int playerWorld = (int) entityManager.getPlayer().getWorld();

		itemManager.tick();

		entityManager.tick();

		
		fireball();
		



			if(button.getModel().isPressed()){
			System.out.println("the button is pressed");
				
			if(growCount<30){
				entityManager.getPlayer().setHeight((int)heightGrow);
				entityManager.getPlayer().setWidth((int)heightGrow);
				
				heightGrow += 1;
				growCount++;
				clicked = true;
				}

			}

			if(growCount<30 && clicked == true){
				entityManager.getPlayer().setHeight((int)heightGrow);
				entityManager.getPlayer().setWidth((int)heightGrow);
				
				heightGrow += 5;
				growCount++;
				//clicked = false;
				}



		

			// if(growCount < 40){

			// 	heightGrow += 1;
			// 	growCount++;
			// }


		
		
		
				// NOTE: This is DEACTIVATED for now, you can add more text file worlds
		// with the loadNextWorld() method you can add more dungeons and maps
		// loadNextWorld(128, 1200);

		loadNextWorld();
		
		//System.out.println("X " + handler.getWorld().entityManager.getPlayer().getX());
		//System.out.println(handler.getWorld().entityManager.getPlayer().getY());
	}

	public void startWorld(int x, int y) {

		currentWorld = 1;


		loadEntities();
		
//		entityManager.addEntity(new Layer2(handler, 8*Tile.TILEWIDTH,3*Tile.TILEHEIGHT, 48 ));
//		entityManager.addEntity(new Layer2(handler, 9*Tile.TILEWIDTH,3*Tile.TILEHEIGHT, 49 ));
//		entityManager.addEntity(new Layer2(handler, 10*Tile.TILEWIDTH,3*Tile.TILEHEIGHT, 50 ));
//		entityManager.addEntity(new Layer2(handler, 8*Tile.TILEWIDTH,3*Tile.TILEHEIGHT, 48 ));
//		
		// NOTE: You can load monsters this way too AND OTHER LAYERS WOW!!!

//
		// i = 0;

		loadLayer2("res/worlds/layer2.txt");
		// loadLayer3("res/worlds/layer3.txt");

		loadWorld(this.path);
		// loadWorld2("res/worlds/layer2.txt");
		// loadWorld("res/worlds/world2.txt");
		// loadWorld("res/worlds/layer2.txt");

//		getTile(3,0).setSolid(true);
//		//setSolidId(116).setSolid(true);
//		Tile.tiles[116].setSolid(true);
//		Tile.tiles[84].setSolid(true);
//		Tile.tiles[409].setSolid(true);
//		Tile.tiles[377].setSolid(true);
//		Tile.tiles[345].setSolid(true);
//		Tile.tiles[143].setSolid(true);
//		Tile.tiles[346].setSolid(true);
//		Tile.tiles[347].setSolid(true);
//		Tile.tiles[19].setSolid(true);
//		Tile.tiles[51].setSolid(true);
//		Tile.tiles[83].setSolid(true);
//		Tile.tiles[115].setSolid(true);
		int setSolid[] = {157,29, 61, 30, 28,218,124,125,126,155};

		entityManager.getPlayer().setX(x);
		entityManager.getPlayer().setY(y);

		Tile.tiles[3].setSolid(true);

		for (int i = 10; i < 512; i++) {
			Tile.tiles[i].setSolid(true);
		}
//		Tile.tiles[218].setSolid(false);
//		Tile.tiles[124].setSolid(false);
//		Tile.tiles[125].setSolid(false);
//		Tile.tiles[126].setSolid(false);
//		Tile.tiles[155].setSolid(false);
		
		for (int i = 0; i < setSolid.length; i++) {
			Tile.tiles[setSolid[i]].setSolid(false);
		}
		
		renderLayer2();
		
		//entityManager.addEntity(new Teleport(handler, 766, 852, 400, 1200, 1));

		entityManager.addEntity(new Teleport(handler, 766, 852, 280, 500,3));
	}

	
	public void loadEntities() {
		
		entityManager.addEntity(new Tree(handler, 132, 250));
		entityManager.addEntity(new Rock(handler, 132, 450));
		entityManager.addEntity(new Rock(handler, 350, 300));
		entityManager.addEntity(new Rock(handler, 400, 345));
		entityManager.addEntity(new Tree(handler, 925, 325));

		entityManager.addEntity(Zombie.zombies[0] = new Zombie(handler, 140, 100,0));
		entityManager.addEntity(Zombie.zombies[1] = new Zombie(handler, 150, 180,1));
		entityManager.addEntity(Zombie.zombies[2] = new Zombie(handler, 160, 380,2));
		entityManager.addEntity(Zombie.zombies[3] = new Zombie(handler, 170, 480,3));

		//fireball();

		
		
		
		entityManager.addEntity(new Teleport(handler, 2 * Tile.TILEWIDTH, 0, 200, 1200, 2));
		entityManager.addEntity(new Teleport(handler, 440, 20, 100,100, 1));

	}
	
	public void fireball() {
		for (int j = 0; j < Zombie.zombies.length; j++) {
			
			if( Zombie.zombies[j] != null && Fireball.countFireballs < Zombie.countZombies && Zombie.zombies[j].isActive()  ) {
			entityManager.addEntity(new Fireball(handler, Zombie.zombies[j].getX()+50,Zombie.zombies[j].getY()+50,j));
			Fireball.countFireballs++;
			}

			
		}
		
	}
	
	public boolean renderAbove(int tile) {

		int tileNums[] = { 52, 124, 125, 126 };

		// int tileNums2[] = {17, 48,49,50};

		for (int i = 0; i < tileNums.length; i++) {
			if (tileNums[i] == tile)
				return true;
		}

		return false;

	}

	public void renderLayer2() {

		for (int y2 = 0; y2 < height; y2++) {

			for (int x2 = 0; x2 < width; x2++) {

				if (tiles2[x2][y2] > 0) {
					renderLayer2 = true;
					entityManager.addEntity(new Layer2(handler, x2 * Tile.TILEWIDTH, y2 * Tile.TILEHEIGHT,
							tiles2[x2][y2], renderLayer2));

				}

			}

		}

	}

	public void loadNextWorld() {

		if (worldChanged) {

			entityManager.deleteAllEntities();

			if (currentWorld == 1) {

				//loadWorld("res/worlds/world1.txt");
				//startWorld(100,100);
				//entityManager.deleteAllEntities();
				loadWorld("res/worlds/world1.txt");
				loadLayer2("res/worlds/layer2.txt");
				renderLayer2();
				loadEntities();
				entityManager.addEntity(new Teleport(handler, 766, 852, 30, 50, 3));

			} else if (currentWorld == 2) {

				loadWorld("res/worlds/world2.txt");

				entityManager.addEntity(new Teleport(handler, 440, 20, 100, 1200,1));
				entityManager.addEntity(new Teleport(handler, 130, 1254, 100, 1200, 1));
				//teleport(100,1200);
			} else if (currentWorld == 3) {
				
				//entityManager.deleteAllEntities();
				
				loadWorld("res/worlds/church.txt");
				loadLayer2("res/worlds/churchLayer2.txt");
				renderLayer2();
				entityManager.addEntity(new Teleport(handler, 290, 550, 766, 870,1));
				entityManager.addEntity(NPC.npcs[0] = new NPC(handler, 300, 200,0));
				entityManager.addEntity(NPC.npcs[1] = new NPC(handler, 200, 200,1));
				
				NPC.npcs[0].dialog.say = "Ahoy Mate!";
				NPC.npcs[1].dialog.say = "Hello, my name is Luta Gemma.\nI was walking along the many corridors\nwhen I heard a strange sound coming from the far east.";
				//renderLayer2();
				//entityManager.addEntity(new Teleport(handler, 130, 1254, 100, 1200, 1));
				//teleport(100,1200);
			}

		}

		worldChanged = false;
	}

	public void teleport(int x, int y) {
		
		handler.getWorld().getEntityManager().getPlayer().setX(x);
		handler.getWorld().getEntityManager().getPlayer().setY(y);
	}
	
	public void render(Graphics g) {
		int xStart = (int) Math.max(0, handler.getGameCamera().getxOffset() / Tile.TILEWIDTH);
		int xEnd = (int) Math.min(width,
				(handler.getGameCamera().getxOffset() + handler.getWidth()) / Tile.TILEWIDTH + 1);
		int yStart = (int) Math.max(0, handler.getGameCamera().getyOffset() / Tile.TILEHEIGHT);
		int yEnd = (int) Math.min(height,
				(handler.getGameCamera().getyOffset() + handler.getHeight()) / Tile.TILEHEIGHT + 1);

		for (int y = yStart; y < yEnd; y++) {
			for (int x = xStart; x < xEnd; x++) {
				getTile(x, y).render(g, (int) (x * Tile.TILEWIDTH - handler.getGameCamera().getxOffset()),
						(int) (y * Tile.TILEHEIGHT - handler.getGameCamera().getyOffset()));
			}
		}

		// Items
		itemManager.render(g);
		// Entities
		entityManager.render(g);
	}

	public Layer2 getLayer2(int x, int y) {

		Layer2 t = Layer2.layer2[tiles2[x][y]];

		return t;
	}

	public Tile getTile(int x, int y) {

		// Tile t = Tile.tiles[tiles[x][y]];
		if (x < 0 || y < 0 || x >= width || y >= height)
			return Tile.grassTile;

		Tile t = Tile.tiles[tiles[x][y]];

		if (t == null)
			return Tile.dirtTile;

		return t;
	}

	private void loadWorld(String path) {
		String file = Utils.loadFileAsString(path);
		String[] tokens = file.split("\\s+");
		width = Utils.parseInt(tokens[0]);
		height = Utils.parseInt(tokens[1]);
		spawnX = Utils.parseInt(tokens[2]);
		spawnY = Utils.parseInt(tokens[3]);

		tiles = new int[width][height];
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				tiles[x][y] = Utils.parseInt(tokens[(x + y * width) + 4]);
			}
		}

	}

	private void loadLayer2(String path) {
		String file = Utils.loadFileAsString(path);
		String[] tokens = file.split("\\s+");
		width = Utils.parseInt(tokens[0]);
		height = Utils.parseInt(tokens[1]);
		spawnX = Utils.parseInt(tokens[2]);
		spawnY = Utils.parseInt(tokens[3]);

		tiles2 = new int[width][height];
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				tiles2[x][y] = Utils.parseInt(tokens[(x + y * width) + 4]);
			}
		}

	}

	private void loadLayer3(String path) {
		String file = Utils.loadFileAsString(path);
		String[] tokens = file.split("\\s+");
		width = Utils.parseInt(tokens[0]);
		height = Utils.parseInt(tokens[1]);
		spawnX = Utils.parseInt(tokens[2]);
		spawnY = Utils.parseInt(tokens[3]);

		tiles2 = new int[width][height];
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				tiles2[x][y] = Utils.parseInt(tokens[(x + y * width) + 4]);
			}
		}

	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	public ItemManager getItemManager() {
		return itemManager;
	}

	public void setItemManager(ItemManager itemManager) {
		this.itemManager = itemManager;
	}

}








