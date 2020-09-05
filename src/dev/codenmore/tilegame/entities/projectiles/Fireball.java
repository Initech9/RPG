package dev.codenmore.tilegame.entities.projectiles;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import dev.codenmore.tilegame.Handler;
import dev.codenmore.tilegame.entities.Entity;
import dev.codenmore.tilegame.gfx.Animation;
import dev.codenmore.tilegame.gfx.Assets;
//import dev.codenmore.tilegame.inventory.Inventory;
import dev.codenmore.tilegame.tiles.Tile;

public class Fireball extends Projectile {
	
	public static Fireball[] fireballs = new Fireball[20];
	
	//Animations
	private Animation animDown, animUp, animLeft, animRight;
	// Attack timer
	private long lastAttackTimer, attackCooldown = 800, attackTimer = attackCooldown;
	// Inventory
	//private Inventory inventory;
	private int world;
	public static int countFireballs = 0;
	public int angleSet = 0;

	public int initialPosition = 0;
	float x2;
	float y2;

	public int trailLength = 0;
	public String fireCollide = "class dev.codenmore.tilegame.entities.projectiles.Fireball";
	public boolean hasCollided = false;
	
	public int countCollision = 0;
	
	public Fireball(Handler handler, float x, float y, int id) {
		super(handler, x, y, Projectile.DEFAULT_CREATURE_WIDTH, Projectile.DEFAULT_CREATURE_HEIGHT);
		
		bounds.x = 22;
		bounds.y = 44;
		bounds.width = 19;
		bounds.height = 19;
		
		//Animatons
		animDown = new Animation(500, Assets.player_attack_hit);
		animUp = new Animation(500, Assets.player_attack_hit);
		animLeft = new Animation(500, Assets.player_attack_hit);
		animRight = new Animation(500, Assets.player_attack_hit);
		
		this.id = id;
		
		x2 =  handler.getWorld().getEntityManager().getPlayer().getX();
		y2 =  handler.getWorld().getEntityManager().getPlayer().getY();
		//inventory = new Inventory(handler);
	}

	@Override
	public void tick() {
		//Animations
		animDown.tick();
		animUp.tick();
		animRight.tick();
		animLeft.tick();
		//Movement
		moveTowards();
		move();

		checkAttacks();
		
		
		// Inventory
		//inventory.tick();
	}
	
	private void checkAttacks(){
		attackTimer += System.currentTimeMillis() - lastAttackTimer;
		lastAttackTimer = System.currentTimeMillis();
		if(attackTimer < attackCooldown)
			return;
		
//		if(inventory.isActive())
//			return;
		
		Rectangle cb = getCollisionBounds(0, 0);
		Rectangle ar = new Rectangle();
		int arSize = 20;
		ar.width = arSize;
		ar.height = arSize;
		
	
			ar.x = cb.x + cb.width / 2 - arSize / 2;
			ar.y = cb.y - arSize;
			//knockBack();
		
			ar.x = cb.x + cb.width / 2 - arSize / 2;
			ar.y = cb.y + cb.height;
			//knockBack();
		
		
		attackTimer = 0;
		//int prevX = 0;
		//int prevY = 0;
		
		for(Entity e : handler.getWorld().getEntityManager().getEntities()){
			
			
			if(e.equals(this) ) {
				if(this.collisionWithTile(0, 0)) {
					countCollision++;
					//Fireball.countFireballs--;
					
				//System.out.println("True YO");	
				}			
				//the larger the number on the next line, the tougher the fireball
				if(countCollision>5) {
					this.setActive(false);
					Fireball.countFireballs--;
				}
//				if(handler.getWorld().getEntityManager().getPlayer().getCollisionBounds(0, 0).intersects(ar))
//					this.setActive(false);
				
				
				
				continue;
			}
			if(e.getCollisionBounds(0, 0).intersects(ar)){
				
				//this.getCollisionBounds(0, 0).intersects(ar)
				
				//this.setActive(false);
				
				
				
	
				
				//knockBack();
				e.hurt(1);
				
				
				
					return;
			}
			
			//knockBack();
		}
		
	}
	
	
	

	
	@Override
	public void die(){
		System.out.println("You lose");
	}
	
//	private void followPlayer(Player player) {
//		
//		if(player.getX() < x)
//			xMove = -speed;
//			
//	}
	
	public void moveTowards(){
		//xMove = 0;
		//yMove = 0;
		float angle = 0;
		
		int FAST = 4;

		angleSet = 1;
//		if(inventory.isActive())
//			return;
		if(angleSet == 1) {

			angle = (float)super.calculateAngle(x, y, x2, y2);	
		}
			
		//System.out.println("Sin is " + Math.sin(angle));
//		if(inventory.isActive())
//			return;
		
		
		
		if( x < x2 && y < y2) {
			xMove = (float) Math.cos(angle)*FAST;
			yMove = (float) -Math.sin(angle)*FAST;
			
		}
		if( x < x2 && y > y2) {
			xMove = (float) -Math.cos(angle)*FAST;
			yMove = (float) Math.sin(angle)*FAST;
			//System.out.println("THIS QUAD");
		}
		if( x > x2 && y < y2) {
			xMove = (float) Math.cos(angle)*FAST;
			yMove = (float) -Math.sin(angle)*FAST;
		}
		if( x > x2 && y > y2) {
			xMove = (float) Math.cos(angle)*FAST;
			yMove = (float) -Math.sin(angle)*FAST;
		}


		trailLength++;
		angleSet=2;
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(getCurrentAnimationFrame(), (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
	}
	
//	public void postRender(Graphics g){
//		inventory.render(g);
//	}
	
	private BufferedImage getCurrentAnimationFrame(){
		if(xMove < 0){
			return animLeft.getCurrentFrame();
		}else if(xMove > 0){
			return animRight.getCurrentFrame();
		}else if(yMove < 0){
			return animUp.getCurrentFrame();
		}else{
			return animDown.getCurrentFrame();
		}
	}


}
