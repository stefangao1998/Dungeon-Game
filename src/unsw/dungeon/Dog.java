package unsw.dungeon;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

import javafx.beans.property.IntegerProperty;

public class Dog extends Entity {
	
	private Player player;
	private Dungeon dungeon;
    //private ClosedDoor closedDoor;
    private boolean Pickedup;
    
	public Dog(int x, int y, Dungeon dungeon) {
		super(x, y);
		this.player = dungeon.getPlayer();
		this.dungeon = dungeon;
		this.Pickedup = false;
	}

	
	public void doAction(Dungeon dungeon) {
		if (Pickedup && checkExistEnemy(dungeon)) {
			towardsEnemy(dungeon);
			checkPosition(dungeon);
		}
		
	}
	
	public void towardsEnemy(Dungeon dungeon) {
		int x = this.getX();
		int y = this.getY();
		Enemy e = getClosestEnemy();
		int xdis = this.XDistanceToEnemy(e);
		int ydis = this.YDistanceToEnemy(e);
		int xclose = this.Xclosecoordinate();
		int yclose = this.Yclosecoordinate();
		int xaway = this.Xawaycoordinate();
		int yaway = this.Yawaycoordinate();
		//System.out.println(String.format("enemy (x, y) is (%d, %d)\n", x, y));
		//System.out.println(String.format("player (x, y) is (%d, %d)\n", this.dungeon.getPlayer().getX(), this.dungeon.getPlayer().getY()));
		//System.out.println(String.format("dis (x,y) is (%d, %d)\n", xdis, ydis));
		//System.out.println(String.format("xclose is %d\n xaway is %d\n", xclose, xaway));
		//System.out.println(String.format("yclose is %d\n yaway is %d\n", yclose, yaway));
		//System.out.println(xdis);
		//System.out.println(ydis);
		//if (xdis != 0 && ydis != 0) {
			if (java.lang.Math.abs(xdis) > java.lang.Math.abs(ydis)) {
				if (this.validMove(xclose, y) == true) {
					//System.out.println("1111\n");
					this.Xgetclose();
				} else if (this.validMove(x, yclose) == true) {
					//System.out.println("2222\n");
					this.Ygetclose();
				} else if (this.validMove(x, yaway) == true) {
					//System.out.println("33333\n");
					this.Ygetaway();
				} else if (this.validMove(xaway, y) == true) {
					//System.out.println("4444\n");
					this.Xgetaway();
				} else {
					//System.out.println("i can go nowhere\n");
				}
			} else if (java.lang.Math.abs(xdis) < java.lang.Math.abs(ydis)) {
				if (this.validMove(x, yclose) == true) {
					//System.out.println("5555\n");
					this.Ygetclose();
				} else if (this.validMove(xclose, y) == true) {
					//System.out.println("6666\n");
					this.Xgetclose();
				} else if (this.validMove(xaway, y) == true) {
					//System.out.println("7777\n");
					this.Xgetaway();
				} else if (this.validMove(x, yaway) == true) {
					//System.out.println("88888\n");
					this.Ygetaway();
				} else {
					//System.out.println("i can go nowhere\n");
				}
			} else {	// equal distance on x and y
				if (this.validMove(xclose, y) == true) {
					//System.out.println("aaaa\n");
					this.Xgetclose();
				} else if (this.validMove(x, yclose) == true) {
					//System.out.println("bbbb\n");
					this.Ygetclose();
				} else if (this.validMove(x, yaway) == true) {
					//System.out.println("cccc\n");
					this.Ygetaway();
				} else if (this.validMove(xaway, y) == true) {
					//System.out.println("ddddd\n");
					this.Xgetaway();
				} else {
					//System.out.println("i can go nowhere\n");
				}
			}
		} /**else if (xdis == 0) {
			//System.out.println(xdis);
			if (this.validMove(x, yclose) == true) {
				//System.out.println("yclose\n");
				this.Ygetclose();
			} 
		} else if (ydis == 0) {
			//System.out.println(ydis);
			if (this.validMove(xclose, y) == true) {
				//System.out.println("xclose\n");
				this.Xgetclose();
			} 
		}*/
		//System.out.println(String.format("now enemy (x, y) is (%d, %d)\n", this.getX(), this.getY()));
	//}
	
	
	
	


////////////////////////////////////////////////////
	public void Xgetclose() {
		Enemy e = getClosestEnemy();
		int xdis = this.XDistanceToEnemy(e);
		if (xdis > 0) {
			this.moveLeft();
		} else if (xdis < 0) {
			this.moveRight();
		} else {
			//System.out.println("i stayed\n");
		}
	}
	public void Xgetaway() {
		Enemy e = getClosestEnemy();
		int xdis = this.XDistanceToEnemy(e);
		if (xdis > 0) {
			this.moveRight();
		} else if (xdis < 0){
			this.moveLeft();
		} else {
			//System.out.println("i stayed\n");
		}
	}
	public void Ygetclose() {
		Enemy e = getClosestEnemy();
		int ydis = this.YDistanceToEnemy(e);
		if (ydis > 0) {
			this.moveUp();
		} else if (ydis < 0) {
			this.moveDown();
		} else {
			//System.out.println("i stayed\n");
		}
	}
	public void Ygetaway() {
		Enemy e = getClosestEnemy();
		int ydis = this.YDistanceToEnemy(e);
		if (ydis > 0) {
			this.moveDown();
		} else if (ydis < 0){
			this.moveUp();
		} else {
			//System.out.println("i stayed\n");
		}
	}
////////////////////////////////////////////////////
	
	public Enemy getClosestEnemy() {
		List<Integer> disAllEnemies= new ArrayList<Integer>();
		for (Enemy e : dungeon.getEnemy()) {
			int dis = java.lang.Math.abs(XDistanceToEnemy(e)) + java.lang.Math.abs(YDistanceToEnemy(e));
			disAllEnemies.add(dis);
		}
		Collections.sort(disAllEnemies);
		for (Enemy e : dungeon.getEnemy()) {
			if (java.lang.Math.abs(XDistanceToEnemy(e)) + java.lang.Math.abs(YDistanceToEnemy(e)) == disAllEnemies.get(0)) {
				return e;
			}
		}
		return null;
	}
	
	
	
////////////////////////////////////////////////////
	public int Xclosecoordinate () {
		Enemy e = getClosestEnemy();
		int xdis = this.XDistanceToEnemy(e);
		int nowx = this.getX();
		if (xdis > 0) {
			return nowx-1;
		} else if (xdis < 0){
			return nowx+1;
		} else {
			return nowx;
		}
	}
	public int Xawaycoordinate () {
		Enemy e = getClosestEnemy();
		int xdis = this.XDistanceToEnemy(e);
		int nowx = this.getX();
		if (xdis > 0) {
			return nowx+1;
		} else if (xdis < 0) {
			return nowx-1;
		} else {
			return nowx;
		}
	}
	public int Yclosecoordinate () {
		Enemy e = getClosestEnemy();
		int ydis = this.YDistanceToEnemy(e);
		int nowy = this.getY();
		if (ydis > 0) {
			return nowy-1;
		} else if (ydis < 0) {
			return nowy+1;
		} else {
			return nowy;
		}
	}
	public int Yawaycoordinate () {
		Enemy e = getClosestEnemy();
		int ydis = this.YDistanceToEnemy(e);
		int nowy = this.getY();
		if (ydis > 0) {
			return nowy+1;
		} else if (ydis < 0) {
			return nowy-1;
		} else {
			return nowy;
		}
	}
	

//////////////////////////////////////////////////////	
	
	public int XDistanceToEnemy(Enemy e) {
		int eX = e.getX();
		int myX = this.getX();
		return (myX - eX);
	}
	public int YDistanceToEnemy(Enemy e) {
		int eY = e.getY();
		int myY = this.getY();
		//System.out.println(String.format("px is %d, py is %d\nex is %d, ey is %d\n", playerX, playerY, enemyX, enemyY));
		return (myY - eY);
	}	
	
	
	
//////////////////////////////////////////////////////////////	
    public void moveUp() {
		if (Pickedup && dungeon.getPlayer().isKilled() == false) {
			y().set(getY() - 1);
		}
    }

    public void moveDown() {
		if (Pickedup && dungeon.getPlayer().isKilled() == false) {
			y().set(getY() + 1);
		} 
    }

    public void moveLeft() {
    	if (Pickedup && dungeon.getPlayer().isKilled() == false) {
        	x().set(getX() - 1);
        } 
    }

    public void moveRight() {
    	if (Pickedup && dungeon.getPlayer().isKilled() == false) {
            x().set(getX() + 1);
        }
    }
    
    public boolean validMove(int x, int y) {
    	for (Entity e : dungeon.getEntity()) {
    		if (e instanceof Wall && e.getX() == x && e.getY() == y) {
    			return false;
    		} else if (e instanceof Boulder && e.getX() == x && e.getY() == y) {
				return false;
    		} else if (e instanceof Exit && e.getX() == x && e.getY() == y) {
    			return false;
    		} else if (e instanceof ClosedDoor && e.getX() == x && e.getY() == y) {
    			return false;
    		}
    	}
    	return true;
    }
    
    public boolean isPickedup() {
    	return this.Pickedup;
    }
    
    public void setPickedup(boolean b) {
    	this.Pickedup = b;
    }
     
    public void checkPosition(Dungeon dungeon) {
    	ArrayList<Entity> tempDel = new ArrayList<Entity>();
    	for (Enemy e : dungeon.getEnemy()) {
    		if (e.getX() == this.getX() && e.getY() == this.getY()) {
    			e.x().set(dungeon.getWidth());
    			e.y().set(dungeon.getHeight());
    			tempDel.add(e);
    		}
    	}
    	for (Entity e : tempDel) {
    		dungeon.removeEntity(e);
    	}
    }
    
    public boolean checkExistEnemy(Dungeon dungeon) {
    	int i = 0;
    	for (Enemy e : dungeon.getEnemy()) i++;
    	if (i == 0) return false;
    	return true;
    }
}
