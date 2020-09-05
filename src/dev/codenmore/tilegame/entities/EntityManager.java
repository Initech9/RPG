package dev.codenmore.tilegame.entities;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

import dev.codenmore.tilegame.Handler;
import dev.codenmore.tilegame.entities.creatures.Player;
import dev.codenmore.tilegame.entities.creatures.Zombie;
import dev.codenmore.tilegame.entities.projectiles.Fireball;
import dev.codenmore.tilegame.entities.statics.Layer2;
import dev.codenmore.tilegame.entities.statics.Teleport;

public class EntityManager {
	
	private Handler handler;
	private Player player;
	private Teleport teleport;
	private Zombie zombie;
	private Layer2 layer2;
	private ArrayList<Entity> entities;
	private ArrayList<Teleport> teleports;
	
	private Comparator<Entity> renderSorter = new Comparator<Entity>(){
		@Override
		public int compare(Entity a, Entity b) {
			
			
			if(a.renderLayer2 == true || b.renderLayer2 == true) 
				return 2;
	
			
			if(a.getY() + a.getHeight() < b.getY() + b.getHeight())
				return -1;
			return 1;


		}
		
	};
	
	public EntityManager(Handler handler, Player player){
		this.handler = handler;
		this.player = player;
		entities = new ArrayList<Entity>();
		addEntity(player);
	}
	

	public void tick(){
		//boolean launch = true;
		
		
		Iterator<Entity> it = entities.iterator();
		while(it.hasNext()){
			Entity e = it.next();
			//if(e.getClass().toString().equals(fireCollide)) {
				
				
					
			
			e.tick();
			if(!e.isActive())
				it.remove();

		}
		
			entities.sort(renderSorter);
	
	}
	
	
	public void deleteAllEntities(){
		Iterator<Entity> it = entities.iterator();
		while(it.hasNext()){
			Entity e = it.next();
			e.tick();
			if(e.isActive() && !e.equals(player))
				it.remove();
			
		}
		entities.sort(renderSorter);
	}
	
	
	public void render(Graphics g){
		for(Entity e : entities){
			e.render(g);
		}
		
		player.postRender(g);
	}
	
	public void addEntity(Entity e){
		entities.add(e);
	}
	
	//GETTERS SETTERS

	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	public Player getPlayer() {
		return player;
	}
	public Teleport getTeleport(int world) {
		
		Iterator<Teleport> it = teleports.iterator();
		while(it.hasNext()){
			Teleport e = it.next();
			
			e.tick();
			if(e.world==world)
				return e;
				
		}
		
		return null;
	}

//	public Teleport getTeleport() {
//		
//		
//		
//		return teleport;
//	}	
	
	public Layer2 getLayer2() {
		return layer2;
	}

//	public Zombie setZombie() {
//		return zombie;
//	}
	
	public Zombie getZombie() {
		return zombie;
	}
	
	public void setPlayer(Player player) {
		this.player = player;
	}

	public ArrayList<Entity> getEntities() {
		return entities;
	}

	public void setEntities(ArrayList<Entity> entities) {
		this.entities = entities;
	}

}
