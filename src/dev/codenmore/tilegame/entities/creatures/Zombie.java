package dev.codenmore.tilegame.entities.creatures;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import dev.codenmore.tilegame.Handler;
import dev.codenmore.tilegame.entities.Entity;
import dev.codenmore.tilegame.entities.projectiles.Fireball;
import dev.codenmore.tilegame.gfx.Animation;
import dev.codenmore.tilegame.gfx.Assets;
//import dev.codenmore.tilegame.inventory.Inventory;
import dev.codenmore.tilegame.tiles.Tile;

public class Zombie extends Creature {
	
	
	public static int countZombies = 4;
	
	public static Zombie[] zombies = new Zombie[countZombies];
	
	//public static Fireball[] fireballs = new Fireball[20];
	
	public static Fireball fireball = null;
	
	//public static Fireball[] fireballs = new Fireball[20];
	//Animations
	private Animation animDown, animUp, animLeft, animRight;
	// Attack timer
	private long lastAttackTimer, attackCooldown = 800, attackTimer = attackCooldown;
	// Inventory
	//private Inventory inventory;
	private int world;
	//public boolean fireball = false;
	int i = 0;
	int fireballCount=0;
	
	
	public Zombie(Handler handler, float x, float y, int id) {
		super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);
		
		bounds.x = 22;
		bounds.y = 44;
		bounds.width = 19;
		bounds.height = 19;
		
		//Animatons
		animDown = new Animation(500, Assets.zombie_down);
		animUp = new Animation(500, Assets.zombie_up);
		animLeft = new Animation(500, Assets.zombie_left);
		animRight = new Animation(500, Assets.zombie_right);
		
		this.id = id;
		
		
	    //new Fireball(handler, this.getX()+50,this.getY(), id );
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
		
		//if(!fireball.isActive())
		//yMove = -speed;
//		if(checkEntityCollisions(0f, yMove))
//			yMove = speed;
		//System.out.println("Fire added ");
		//new Fireball(handler, this.x+50,this.y, id );
		
		//new Fireball(handler, x+50,y,id);
		
	
		
		
		
		//handler.getWorld()h
		
			//fireball = new Fireball(handler,x+40,y);
		
//		if(handler.getKeyManager().down)
//			yMove = speed;
		//handler.getGameCamera().centerOnEntity(this);
		// Attack
		checkAttacks();
		
		i++;
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
		int arSize = 40;
		ar.width = arSize;
		ar.height = arSize;
		
		if(handler.getKeyManager().aUp){
			ar.x = cb.x + cb.width / 2 - arSize / 2;
			ar.y = cb.y - arSize;
			//knockBack();
		}else if(handler.getKeyManager().aDown){
			ar.x = cb.x + cb.width / 2 - arSize / 2;
			ar.y = cb.y + cb.height;
			//knockBack();
		}else if(handler.getKeyManager().aLeft){
			ar.x = cb.x - arSize;
			ar.y = cb.y + cb.height / 2 - arSize / 2;
			//knockBack();
		}else if(handler.getKeyManager().aRight){
			ar.x = cb.x + cb.width;
			ar.y = cb.y + cb.height / 2 - arSize / 2;
			//knockBack();
		}else{
			return;
		}
		
		attackTimer = 0;
		
		for(Entity e : handler.getWorld().getEntityManager().getEntities()){
			if(e.equals(this)) {
				
				continue;
			}
			if(e.getCollisionBounds(0, 0).intersects(ar)){
				//knockBack();
				//knockBack();
				
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
		xMove = 0;
		yMove = 0;

		if( x < handler.getWorld().getEntityManager().getPlayer().getX() )
			xMove = speed/5;
		
		if( y < handler.getWorld().getEntityManager().getPlayer().getY() )
			yMove = speed/5;
		
		if( x > handler.getWorld().getEntityManager().getPlayer().getX() )
			xMove = -speed/5;
		
		if( y > handler.getWorld().getEntityManager().getPlayer().getY() )
			yMove = -speed/5;
		
		//knockBack();
//		if( x - handler.getWorld().getEntityManager().getPlayer().getX()  < 2 )
//			x=x-1;
		
		
//		if(handler.getKeyManager().up)
//			yMove = -speed;
//		if(handler.getKeyManager().down)
//			yMove = speed;
//		if(handler.getKeyManager().left)
//			xMove = -speed;
//		if(handler.getKeyManager().right)
//			xMove = speed;
		
		
		
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

//	@Override
//	public void render(Graphics g) {
//		// TODO Auto-generated method stub
//		
//	}

//	public Inventory getInventory() {
//		return inventory;
//	}

//	public void setInventory(Inventory inventory) {
//		this.inventory = inventory;
//	}

}
