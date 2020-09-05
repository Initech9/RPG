package dev.codenmore.tilegame.dialog;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JPanel;

import dev.codenmore.tilegame.Handler;
import dev.codenmore.tilegame.gfx.Assets;
import dev.codenmore.tilegame.gfx.Text;
import dev.codenmore.tilegame.items.Item;

public class Dialog extends JPanel{

	private Handler handler;
	public boolean active = false;
	private ArrayList<Item> inventoryItems;
	public String say = "Hello";
	public boolean setActive = false;
	
	private int invX = 64, invY = 28,
			invWidth = 512, invHeight = 84,
			invListCenterX = invX + 171,
			invListCenterY = invY + invHeight / 2 + 5,
			invListSpacing = 30;
	
	private int invImageX = 452, invImageY = 82,
			invImageWidth = 64, invImageHeight = 64;
	
	private int invCountX = 484, invCountY = 172;
	
	private int selectedItem = 0;
	
	public Dialog(Handler handler){
		this.handler = handler;
		inventoryItems = new ArrayList<Item>();
	}
	
	public void tick(){
//		if( setActive == true) {
//			active = !active;
//			
//	}
		if( setActive == true)
		active = !active;
		
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_E) 
				|| handler.getKeyManager().keyJustPressed(KeyEvent.VK_S)
					|| handler.getKeyManager().keyJustPressed(KeyEvent.VK_A)
						|| handler.getKeyManager().keyJustPressed(KeyEvent.VK_D))
			active = false;
		
		if(!active) {
			//setActive = false;
			return;
		}
		//active = false;

		
		//setActive = false;
		
//		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_W))
//			selectedItem--;
//		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_S)) {
//			selectedItem++;
//		//active = false;
//		//setActive = false;
//		}	
//		if(selectedItem < 0)
//			selectedItem = inventoryItems.size() - 1;
//		else if(selectedItem >= inventoryItems.size())
//			selectedItem = 0;
	}
	
	public void render(Graphics g){
		if(!active)
			return;
		int arcWidth = 20;
		int arcHeight = 20;
		
		
		//g.drawImage(Assets.stone, invX, invY, invWidth, invHeight, null);
		
		//g.drawRoundRect(invX, invY, invWidth, invHeight, arcWidth, arcHeight);
		g.fillRoundRect(invX, invY, invWidth, invHeight, arcWidth, arcHeight);
//		Text.drawString(g, say, invListCenterX, 
//				invListCenterY + invListSpacing, true, Color.YELLOW, Assets.font28);

		//drawString(g, say, 20,50);
		
		paintComponent(g);
		//drawTextUgly(say, Assets.font28, g);
		
	}
	
	   private void drawString(Graphics g, String text, int x, int y) {
	        int lineHeight = g.getFontMetrics().getHeight();
	        for (String line : text.split("\n"))
	            g.drawString(line, x, y += lineHeight);
	    }
	    
	    public void paintComponent(Graphics g) {
	        super.paintComponent(g);
	        g.setColor(Color.YELLOW);
	        //drawString(g, "hello\nworld", 20, 20);
	        
	        g.setFont(g.getFont().deriveFont(20f));
	        drawString(g, say, invX+5, invY);
	    }
//		int len = inventoryItems.size();
//		if(len == 0)
//			return;
//		
//		for(int i = -5;i < 6;i++){
//			if(selectedItem + i < 0 || selectedItem + i >= len)
//				continue;
//			if(i == 0){
//				Text.drawString(g, "> " + inventoryItems.get(selectedItem + i).getName() + " <", invListCenterX, 
//						invListCenterY + i * invListSpacing, true, Color.YELLOW, Assets.font28);
//			}else{
//				Text.drawString(g, inventoryItems.get(selectedItem + i).getName(), invListCenterX, 
//						invListCenterY + i * invListSpacing, true, Color.WHITE, Assets.font28);
//			}
//		}
//		
//		Item item = inventoryItems.get(selectedItem);
//		g.drawImage(item.getTexture(), invImageX, invImageY, invImageWidth, invImageHeight, null);
//		Text.drawString(g, Integer.toString(item.getCount()), invCountX, invCountY, true, Color.WHITE, Assets.font28);
//	}
//	
//	// Inventory methods
//	
//	public void addItem(Item item){
//		for(Item i : inventoryItems){
//			if(i.getId() == item.getId()){
//				i.setCount(i.getCount() + item.getCount());
//				return;
//			}
//		}
//		inventoryItems.add(item);
//	}
	
	// GETTERS SETTERS

	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	public boolean isActive() {
		return active;
	}
	
}
