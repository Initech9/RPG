package dev.codenmore.tilegame.gfx;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import dev.codenmore.tilegame.tiles.Tile;
import dev.codenmore.tilegame.utils.Utils;


public class Assets {
	
	private static final int width = 32, height = 32;
	
	public static Font font28;
	
	public static BufferedImage dirt, grass, stone, tree, rock, teleport;
	public static BufferedImage wood;
	public static BufferedImage[] player_down, player_up, player_left, player_right;
	public static BufferedImage[] player_attack_down, player_attack_up, player_attack_left, player_attack_right;
	public static BufferedImage[] player_sword_down, player_sword_up, player_sword_left, player_sword_right;
	public static BufferedImage[] player_attack_hit;
	public static BufferedImage[] zombie_down, zombie_up, zombie_left, zombie_right;
	public static BufferedImage[] btn_start;
	public static BufferedImage inventoryScreen;
	public static BufferedImage[][] tiles;
	public static Tile tile;
	public static BufferedImage[] tilesBufferLayer2;
	public static Tile tileLayer2[][];
	
	
	public static void init(){
		font28 = FontLoader.loadFont("res/fonts/slkscr.ttf", 28);
		
		SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/sheet.png"));
		
		inventoryScreen = ImageLoader.loadImage("/textures/inventoryScreen.png");
		
		
		
		//tiles = new Tile();
		int i = 0;
		
tiles = new BufferedImage[32][16]; //512 tiles ALL LOADED from the spritesheet
tilesBufferLayer2 = new BufferedImage[512]; //512 tiles ALL LOADED from the spritesheet
		
		
		for(int y = 0;y < 16;y++){
			for(int x = 0;x < 32;x++){
				tiles[x][y] = sheet.crop(width*x, height * y, width, height);
				   new Tile(Assets.tiles[x][y],i);
				i++;
			}
		}
		
		i = 0;
			
		for(int y = 0;y < 16;y++){
			for(int x = 0;x < 32;x++){
				tilesBufferLayer2[i] = sheet.crop(width*x, height * y, width, height);
				  // new Tile(Assets.tilesBufferLayer2[x][y],i);
				i++;
			}
		}
		
		//tile[3][0].setSolid(true);
			
		
		
		
		wood = sheet.crop(width, height, width, height);
		
		btn_start = new BufferedImage[2];
		btn_start[0] = sheet.crop(width * 6, height * 4, width * 2, height);
		btn_start[1] = sheet.crop(width * 6, height * 5, width * 2, height);
		
		player_down = new BufferedImage[2];
		player_up = new BufferedImage[2];
		player_left = new BufferedImage[2];
		player_right = new BufferedImage[2];
		
		player_attack_down = new BufferedImage[2];
		player_attack_up = new BufferedImage[2];
		player_attack_left = new BufferedImage[2];
		player_attack_right = new BufferedImage[2];
		
		player_attack_hit = new BufferedImage[3];

		
		
		
		player_sword_down = new BufferedImage[3];
		player_sword_up = new BufferedImage[3];
		player_sword_left = new BufferedImage[3];
		player_sword_right = new BufferedImage[3];
		

		player_down[0] = sheet.crop(width * 4, 0, width, height);
		player_down[1] = sheet.crop(width * 5, 0, width, height);
		player_up[0] = sheet.crop(width * 6, 0, width, height);
		player_up[1] = sheet.crop(width * 7, 0, width, height);
		player_right[0] = sheet.crop(width * 4, height, width, height);
		player_right[1] = sheet.crop(width * 5, height, width, height);
		player_left[0] = sheet.crop(width * 6, height, width, height);
		player_left[1] = sheet.crop(width * 7, height, width, height);
		
		
		
		player_sword_down[0] = rotateImage( sheet.crop(0, height * 7, width, height) , 180 );
		player_sword_down[1] = rotateImage( sheet.crop(width * 1, height * 7, width, height) , 180 );
		player_sword_down[2] = rotateImage( sheet.crop(width * 2, height * 7, width, height) , 180 );
		player_sword_up[0] = sheet.crop(0, height * 7, width, height);
		player_sword_up[1] = sheet.crop(width * 1, height * 7, width, height);
		player_sword_up[2] = sheet.crop(width * 2, height * 7, width, height);
		player_sword_right[0] = rotateImage( sheet.crop(0, height * 7, width, height) , 90 );
		player_sword_right[1] = rotateImage( sheet.crop(width * 1, height * 7, width , height) , 90);
		player_sword_right[2] = rotateImage( sheet.crop(width * 2, height * 7, width , height) , 90);
		player_sword_left[0] = rotateImage( sheet.crop(0, height * 7, width, height) , -90 );
		player_sword_left[1] = rotateImage( sheet.crop(width * 1, height * 7, width, height) , -90 );
		player_sword_left[2] = rotateImage( sheet.crop(width * 2, height * 7, width, height) , -90 );
		
		
		player_attack_hit[0] = sheet.crop(0, height * 6, width, height);
		player_attack_hit[1] = sheet.crop(width * 1, height * 6, width, height);
		player_attack_hit[2] = sheet.crop(width * 2, height * 6, width, height);
		
		
		player_attack_down[0] = sheet.crop(width * 4, height * 6, width, height);
		player_attack_down[1] = sheet.crop(width * 5, height * 6, width, height);
		player_attack_up[0] = sheet.crop(width * 6, height * 6, width, height);
		player_attack_up[1] = sheet.crop(width * 7, height * 6, width, height);
		player_attack_right[0] = sheet.crop(width * 4, height * 7, width, height);
		player_attack_right[1] = sheet.crop(width * 5, height * 7, width , height);
		player_attack_left[0] = sheet.crop(width * 6, height * 7, width, height);
		player_attack_left[1] = sheet.crop(width * 7, height * 7, width, height);
		
		
		
		zombie_down = new BufferedImage[2];
		zombie_up = new BufferedImage[2];
		zombie_left = new BufferedImage[2];
		zombie_right = new BufferedImage[2];
		
		zombie_down[0] = sheet.crop(width * 4, height * 2, width, height);
		zombie_down[1] = sheet.crop(width * 5, height * 2, width, height);
		zombie_up[0] = sheet.crop(width * 6, height * 2, width, height);
		zombie_up[1] = sheet.crop(width * 7, height * 2, width, height);
		zombie_right[0] = sheet.crop(width * 4, height * 3, width, height);
		zombie_right[1] = sheet.crop(width * 5, height * 3, width, height);
		zombie_left[0] = sheet.crop(width * 6, height * 3, width, height);
		zombie_left[1] = sheet.crop(width * 7, height * 3, width, height);
		
		
		
		
		dirt = sheet.crop(width, 0, width, height);
		grass = sheet.crop(width * 2, 0, width, height);
		stone = sheet.crop(width * 3, 0, width, height);
		tree = sheet.crop(0, 0, width, height * 2);
		rock = sheet.crop(0, height * 2, width, height);
		teleport = sheet.crop(0, height * 3, width, height);
		
		
		
		
		
	}
	
	
	
	public static BufferedImage rotateImage(BufferedImage src, int rotationAngle) {
	    double theta = (Math.PI * 2) / 360 * rotationAngle;
	    int width = src.getWidth();
	    int height = src.getHeight();
	    BufferedImage dest;
	    if (rotationAngle == 90 || rotationAngle == 270) {
	        dest = new BufferedImage(src.getHeight(), src.getWidth(), src.getType());
	    } else {
	        dest = new BufferedImage(src.getWidth(), src.getHeight(), src.getType());
	    }

	    Graphics2D graphics2D = dest.createGraphics();

	    if (rotationAngle == 90) {
	        graphics2D.translate((height - width) / 2, (height - width) / 2);
	        graphics2D.rotate(theta, height / 2, width / 2);
	    } else if (rotationAngle == 270) {
	        graphics2D.translate((width - height) / 2, (width - height) / 2);
	        graphics2D.rotate(theta, height / 2, width / 2);
	    } else {
	        graphics2D.translate(0, 0);
	        graphics2D.rotate(theta, width / 2, height / 2);
	    }
	    graphics2D.drawRenderedImage(src, null);
	    return dest;
	}
	
	
	
	
	
}
