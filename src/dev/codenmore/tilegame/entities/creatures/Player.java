package dev.codenmore.tilegame.entities.creatures;





//
//import java.io.File;
//import java.io.IOException;
//
//import javax.sound.sampled.AudioFormat;
//import javax.sound.sampled.AudioInputStream;
//import javax.sound.sampled.AudioSystem;
//import javax.sound.sampled.DataLine.Info;
//import javax.sound.sampled.LineUnavailableException;
//import javax.sound.sampled.SourceDataLine;
//import javax.sound.sampled.UnsupportedAudioFileException;
//
//import static javax.sound.sampled.AudioSystem.getAudioInputStream;
//import static javax.sound.sampled.AudioFormat.Encoding.PCM_SIGNED;






import java.awt.Graphics;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import dev.codenmore.tilegame.Handler;
import dev.codenmore.tilegame.dialog.Dialog;
import dev.codenmore.tilegame.entities.Entity;
import dev.codenmore.tilegame.entities.EntityManager;
import dev.codenmore.tilegame.entities.projectiles.Fireball;
import dev.codenmore.tilegame.entities.statics.Rock;
import dev.codenmore.tilegame.entities.statics.Teleport;
import dev.codenmore.tilegame.gfx.Animation;
import dev.codenmore.tilegame.gfx.Assets;
import dev.codenmore.tilegame.inventory.Inventory;
import dev.codenmore.tilegame.tiles.Tile;

public class Player extends Creature {
	
	//Animations
	private Animation animDown, animUp, animLeft, animRight;
	private Animation animAttackDown, animAttackUp, animAttackLeft, animAttackRight;
	private  Animation animSwordDown, animSwordUp, animSwordLeft, animSwordRight;
	private Animation animAttackHit;
	// Attack timer
	private long lastAttackTimer, attackCooldown = 800, attackTimer = attackCooldown;
	// Inventory
	private Inventory inventory;
	private Dialog dialog;
	//private Handler handler;
	private EntityManager entityManager;
	private int xAttackOffset = 0; 
	private int yAttackOffset = 0;
	private boolean attack = false;
	private boolean hit = false;
	private int hitCount = 0;
	private float sparkX;
	private float sparkY;
	public String touchTeleport = "class dev.codenmore.tilegame.entities.statics.Teleport";
	public String talkNPC = "class dev.codenmore.tilegame.entities.creatures.NPC";
	public boolean NPCtalkFlag = false;
	public int TalkToNPC;
	//private int hitCount = 0;
	private int walkCounter = 0;
	public String fireCollide = "class dev.codenmore.tilegame.entities.projectiles.Fireball";
	
	
	public Player(Handler handler, float x, float y) {
		super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);
		
//		bounds.x = 22;
//		bounds.y = 44;
//		bounds.width = (int) (height - height / 1.5f);
//		bounds.height = 19;		
		
		bounds.x = 22;
		bounds.y = 44;
		bounds.width = 19;
		bounds.height = 19;
		
//		
//        final AudioFilePlayer player = new AudioFilePlayer ();
         
		
		play("res/sounds/music.wav");
//		bounds.x = 10;
//		bounds.y = (int) (height / 1.5f);
//		bounds.width = width - 20;
//		bounds.height = (int) (height - height / 1.5f);
		
		
		//Animatons
		animDown = new Animation(500, Assets.player_down);
		animUp = new Animation(500, Assets.player_up);
		animLeft = new Animation(500, Assets.player_left);
		animRight = new Animation(500, Assets.player_right);
		
		animAttackDown = new Animation(50, Assets.player_attack_down);
		animAttackUp = new Animation(50, Assets.player_attack_up);
		animAttackLeft = new Animation(50, Assets.player_attack_left);
		animAttackRight = new Animation(50, Assets.player_attack_right);
		
		animSwordDown = new Animation(50, Assets.player_sword_down);
		animSwordUp = new Animation(50, Assets.player_sword_up);
		animSwordLeft = new Animation(50, Assets.player_sword_left);
		animSwordRight = new Animation(50, Assets.player_sword_right);
		
		animAttackHit = new Animation(40, Assets.player_attack_hit);
		
		inventory = new Inventory(handler);
		dialog = new Dialog(handler);
	}

	@Override
	public void tick() {
		//Animations
		animDown.tick();
		animUp.tick();
		animRight.tick();
		animLeft.tick();
		
		animAttackDown.tick();
		animAttackUp.tick();
		animAttackRight.tick();
		animAttackLeft.tick();
		
		animSwordDown.tick();
		animSwordUp.tick();
		animSwordRight.tick();
		animSwordLeft.tick();
		

		
		//Movement
		getInput();
		move();
		handler.getGameCamera().centerOnEntity(this);
		// Attack
		checkAttacks();
		touchTeleport();
		//stalkNPC();
		animAttackHit.tick();
		// Inventory
		inventory.tick();
		
		dialog.tick();
		
		walkCounter++;
		//System.out.print("WALK COUNTER" + walkCounter);
		if(walkCounter > 1500) {
			
			play("res/sounds/music.wav");
			walkCounter = 0;
		}
		
	}
	
	private void checkAttacks(){
		
		attackTimer += System.currentTimeMillis() - lastAttackTimer;
		lastAttackTimer = System.currentTimeMillis();
		if(attackTimer < attackCooldown)
			return;
		
		if(inventory.isActive())
			return;
	
		if(dialog.isActive())
			return;
		
		Rectangle cb = getCollisionBounds(0, 0);
		Rectangle ar = new Rectangle();
		//int arSize = 40;
		int arSize = 80;
		ar.width = arSize;
		ar.height = arSize;
		
		if(handler.getKeyManager().aUp){
			ar.x = cb.x + cb.width / 2 - arSize / 2;
			ar.y = cb.y - arSize;
			
		}else if(handler.getKeyManager().aDown){
			ar.x = cb.x + cb.width / 2 - arSize / 2;
			ar.y = cb.y + cb.height;
		}else if(handler.getKeyManager().aLeft){
			ar.x = cb.x - arSize;
			ar.y = cb.y + cb.height / 2 - arSize / 2;
		}else if(handler.getKeyManager().aRight){
			ar.x = cb.x + cb.width;
			ar.y = cb.y + cb.height / 2 - arSize / 2;
		}else{
			
			//ar.x = cb.x + cb.width;
			//ar.y = cb.y + cb.height;
			return;
			
			
		}
		
		attackTimer = 0;
		
		
		
//		for(Entity e : handler.getWorld().getEntityManager().getEntities()){
//			if(e.equals(this))
//				continue;
//			if(e.getCollisionBounds(0, 0).intersects(ar)){
//				System.out.println("entity type is" + e.getClass().getPackage());
//				e.hurt(1);
//				return;
//			}
//		}
		
		for(Entity e : handler.getWorld().getEntityManager().getEntities()){
			if(e.equals(this))
				continue;
			if(e.getCollisionBounds(0, 0).intersects(ar)){
				//System.out.println("entity type is" + e.getClass().getPackage());
				
				//System.out.println("entity type is" + e.getClass().getPackage());
				
				//If it's a creature then knock it back with sword
				//If it's static like a tree, then don't knock it back
				if(e.getClass().getPackage() == this.getClass().getPackage())
				e.knockBack(30);
				else
				e.knockBack(2);
				//handler.getWorld().getEntityManager().
				sparkX = e.getX();
				sparkY = e.getY();
				
				hit = true;
				//this.hurt(1);
				//teleport(40,40);
				touchTeleport();
				//talkNPC();
				e.hurt(1);
				
			
				
				play("res/sounds/punch.wav");
				
		
				
				
				return;
			}
		}
		
	}
	
	private void touchTeleport(){
		
		attackTimer += System.currentTimeMillis() - lastAttackTimer;
		lastAttackTimer = System.currentTimeMillis();
		if(attackTimer < attackCooldown)
			return;
		
		if(inventory.isActive())
			return;
		
		if(dialog.isActive())
			return;
		
		Rectangle cb = getCollisionBounds(0, 0);
		Rectangle ar = new Rectangle();
		//int arSize = 40;
		int arSize = 40;
		ar.width = arSize;
		ar.height = arSize;
		
		if(handler.getKeyManager().up){
			ar.x = cb.x + cb.width / 2 - arSize / 2;
			ar.y = cb.y - arSize;
			
		}else if(handler.getKeyManager().down){
			ar.x = cb.x + cb.width / 2 - arSize / 2;
			ar.y = cb.y + cb.height;
		}else if(handler.getKeyManager().left){
			ar.x = cb.x - arSize;
			ar.y = cb.y + cb.height / 2 - arSize / 2;
		}else if(handler.getKeyManager().right){
			ar.x = cb.x + cb.width;
			ar.y = cb.y + cb.height / 2 - arSize / 2;
		}else{
			
			//ar.x = cb.x + cb.width;
			//ar.y = cb.y + cb.height;
			return;
			
			
		}
		
		attackTimer = 0;
		

		
		
		
		for(Entity e : handler.getWorld().getEntityManager().getEntities()){
			if(e.equals(this))
				continue;
			if(e.getCollisionBounds(0, 0).intersects(ar)){
				//System.out.println("entity type is" + e.getClass().getPackage());
				
				System.out.println("entity type is " + e.getClass().toString() );
				System.out.println("e.id is " + e.id );
				
				if(e.getClass().toString().equals(fireCollide) ) {
					e.setActive(false);
					Fireball.countFireballs--;
				}
				
				if(e.getClass().toString().equals(talkNPC) ) {
					//handler.getWorld().getEntityManager().deleteAllEntities();
					//entityManager.getHandler().getClass().
					System.out.println("e.id is " + e.id );
					
					//teleport(handler, e.world, e.toX, e.toY);
					
					//talkNPC(handler, NPC.npcs[e.id]);
					dialog = NPC.npcs[e.id].dialog;
					//dialog.setActive = true;
					dialog.active = true;
					//Teleport.teleports[e.id];
					
					NPCtalkFlag = true;
					
					TalkToNPC = e.id;
					
				}else {NPCtalkFlag = false; dialog.setActive = false; dialog.active = false;};
				
				
				if(e.getClass().toString().equals(touchTeleport) ) {
					//handler.getWorld().getEntityManager().deleteAllEntities();
					
					System.out.println("e.id is " + e.getWorld() );
					
					teleport(handler, e.world, e.toX, e.toY);
					
					
						//Teleport.teleports[e.id];
					
					
				}
				//e.getClass().
				//If it's a creature then knock it back with sword
				//If it's static like a tree, then don't knock it back
//				if(e.getClass().getPackage() == this.getClass().getPackage())
//				e.knockBack(30);
//				else
//				e.knockBack(2);
//				//handler.getWorld().getEntityManager().
//				sparkX = e.getX();
//				sparkY = e.getY();
//				
//				hit = true;
//				//this.hurt(1);
//				//teleport(40,40);
//				//checkTeleport();
//				e.hurt(1);
				
			
				
				//play("res/sounds/punch.wav");
				
		
				
				
				return;
			}
		}
		
	}
	
	public void dialogBubble(Graphics g) {
		
		NPC.npcs[TalkToNPC].dialog.render(g);	
		
		
	}
	
//	public void talkNPC(Handler handler, int world, float toX, float toY) {
//		
//		
//		handler.getWorld().worldChanged = true;
//		handler.getWorld().currentWorld = world;
//		handler.getWorld().getEntityManager().getPlayer().setX(toX);
//		
//		
//	}
	
//	public void talkNPC(Handler handler, NPC npc) {
//		int idNum;
//		
//		//Tile.tiles[218].setSolid(false);
//		//idNum = handler.getWorld().getEntityManager().
//		System.out.println("ID NUM IS: " + npc.id);
//		
//		//NPC.npcs[npc.id].dialog;
//		
//		
//		//npc.dialog.
//		
//	}
	
	public void teleport(Handler handler, int world, float toX, float toY) {
		
		
		handler.getWorld().worldChanged = true;
		handler.getWorld().currentWorld = world;
		handler.getWorld().getEntityManager().getPlayer().setX(toX);
		handler.getWorld().getEntityManager().getPlayer().setY(toY);
		
	}
	
	
	@Override
	public void die(){
		System.out.println("You lose");
	
		
	}
	
	public static void play(String filename)
	{
	    try
	    {
	        Clip clip = AudioSystem.getClip();
	        clip.open(AudioSystem.getAudioInputStream(new File(filename)));
	        clip.start();
	    }
	    catch (Exception exc)
	    {
	        exc.printStackTrace(System.out);
	    }
	}
	
	private void getInput(){
		xMove = 0;
		yMove = 0;

		if(inventory.isActive())
			return;
		
		if(dialog.isActive())
			return;
		
		if(handler.getKeyManager().up)
			yMove = -speed;
		if(handler.getKeyManager().down)
			yMove = speed;
		if(handler.getKeyManager().left)
			xMove = -speed;
		if(handler.getKeyManager().right)
			xMove = speed;
	}
	


	@Override
	public void render(Graphics g) {
		g.drawImage(getCurrentAnimationFrame(), (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width , height, null);
		
		g.drawImage(getCurrentSwordAnimationFrame(), (int) (x - handler.getGameCamera().getxOffset() + xAttackOffset), (int) (y - handler.getGameCamera().getyOffset() + yAttackOffset), width , height, null);
		
		//attack = false;
		xAttackOffset = 0;
		yAttackOffset = 0;
		
	}
	
	public void postRender(Graphics g){
		
		renderHitSpark(g);
		
		inventory.render(g);
		
		if(NPCtalkFlag == true);
		dialog.render(g);
			
		//NPC.npcs[TalkToNPC].hello
		//dialogBubble(g);
	}
	
	private void renderHitSpark(Graphics g) {
		
		
		if(hit) {
		g.drawImage(getAttackHitAnimationFrame(), (int) (sparkX - handler.getGameCamera().getxOffset()), (int) (sparkY - handler.getGameCamera().getyOffset() ), width , height, null);
		
		hitCount++;
		}
		//hit = false;
		
		if(hitCount > 20) {
			hit = false;
			
			hitCount = 0;
			sparkX = 0;
			sparkY = 0;
			play("res/sounds/castlevania.wav");
		}
			
		
	}
	
	
	private BufferedImage getAttackHitAnimationFrame(){
		

		if(hit)
			return animAttackHit.getCurrentFrame();


		else{
		
			return null;
		}

		
	}
	
	private BufferedImage getCurrentSwordAnimationFrame(){
		

		if(handler.getKeyManager().aLeft){
			//attack = true;
			xAttackOffset = - 48;
			return animSwordLeft.getCurrentFrame();
		}else if(handler.getKeyManager().aRight){
			//attack = true;
			xAttackOffset = 48;
			return animSwordRight.getCurrentFrame();
		}else if(handler.getKeyManager().aUp){
			//attack = true;
			yAttackOffset = -48;
			return animSwordUp.getCurrentFrame();
		}else if(handler.getKeyManager().aDown){
			//attack = true;
			yAttackOffset = 48;
			return animSwordDown.getCurrentFrame();
		}		
		
		
		
//		else if(xMove < 0){
//			return animSwordLeft.getCurrentFrame();
//		}else if(xMove > 0){
//			return animSwordRight.getCurrentFrame();
//		}else if(yMove < 0){
//			return animSwordUp.getCurrentFrame();
//		}	

		else{
			return null;
		}

		
	}
	
	private BufferedImage getCurrentAnimationFrame(){
		

		if(handler.getKeyManager().aLeft){
			//entityManager.addEntity(  new Zombie(handler, x - 20, y ) );
			//super.width = -128;
			//entityManager.addEntity(new Rock(handler, x+50, y));
//			xMove = xMove-32;
			//drawAttackOffset = -32;
			return animAttackLeft.getCurrentFrame();
		}else if(handler.getKeyManager().aRight){
			return animAttackRight.getCurrentFrame();
		}else if(handler.getKeyManager().aUp){
			return animAttackUp.getCurrentFrame();
		}else if(handler.getKeyManager().aDown){
			return animAttackDown.getCurrentFrame();
		}		
		
		
		
		else if(xMove < 0){
			return animLeft.getCurrentFrame();
		}else if(xMove > 0){
			return animRight.getCurrentFrame();
		}else if(yMove < 0){
			return animUp.getCurrentFrame();
		}	

		
		else{
			hit = false;
			return animDown.getCurrentFrame();
		}

		
	}

	
	
	public Inventory getInventory() {
		return inventory;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}

}
