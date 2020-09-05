package dev.codenmore.tilegame.entities;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import dev.codenmore.tilegame.Handler;
import dev.codenmore.tilegame.gfx.Animation;
import dev.codenmore.tilegame.gfx.Assets;

public abstract class Entity {

	public static final int DEFAULT_HEALTH = 3;
	protected Handler handler;
	protected float x, y;
	protected int width, height;
	protected int health;
	protected boolean active = true;
	protected Rectangle bounds;
	//protected int world;
	public boolean renderLayer2 = false;
	public boolean isSolid = false;
	public int id = 0;
	public int world;
	public float toX;
	public float toY;
	
	public Entity(Handler handler, float x, float y, int width, int height){
		this.handler = handler;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		//this.world = world;
		health = DEFAULT_HEALTH;
		

		bounds = new Rectangle(0, 0, width, height);
	}
	
	public abstract void tick();
	
	public abstract void render(Graphics g);
	
	public abstract void die();
	
	public void hurt(int amt){
		health -= amt;
		
		if(health <= 0){
			active = false;
			die();
		}
	}
	
	
	
	public void knockBack(int distance) {
		if( x > handler.getWorld().getEntityManager().getPlayer().getX() )
			x += distance;
		else if( x < handler.getWorld().getEntityManager().getPlayer().getX() )
			x -= distance;
		else if( y > handler.getWorld().getEntityManager().getPlayer().getY() )
			y += distance;
		else if( y < handler.getWorld().getEntityManager().getPlayer().getY() )
			y -= distance;
		else {};
	}
	
	
	public boolean checkEntityCollisions(float xOffset, float yOffset){
		for(Entity e : handler.getWorld().getEntityManager().getEntities()){
			if(e.equals(this))
				continue;
			if(e.getCollisionBounds(0f, 0f).intersects(getCollisionBounds(xOffset, yOffset)))
				return true;
		}
		return false;
	}
	
	public Rectangle getCollisionBounds(float xOffset, float yOffset){
		return new Rectangle((int) (x + bounds.x + xOffset), (int) (y + bounds.y + yOffset), bounds.width, bounds.height);
	}
	
	public int getWorld()
	{
		
		return world;
	}
	public void setWorld(int w)
	{
		
		this.world = w;
	}
	
	public int getTeleport() {
		return world;
	}
	
	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
}
