package unsw.dungeon;

import javafx.beans.property.IntegerProperty;

public class Enemy extends Entity {
	
	private Player player;
	private Dungeon dungeon;
    //private ClosedDoor closedDoor;
    private boolean killed;
    private EnemyStrategy enemyStrategy;
    private String obState;
    private int lastX;
    private int lastY;
    
	public Enemy(int x, int y, Dungeon dungeon, EnemyStrategy enemyStrategy) {
		super(x, y);
		this.player = dungeon.getPlayer();
		this.dungeon = dungeon;
		this.killed = false;
		this.enemyStrategy = enemyStrategy;
		this.obState = "default";
		lastX = this.getX();
		lastY = this.getY();
	}
	
	public void doSpecificaction(Dungeon dungeon) {
		setEnemyStrategy(new OperationTowards());
	    this.executeStrategy(dungeon);
		this.towardsPlayer(dungeon);
	}
	
	public void doAction(Dungeon dungeon) {
		Subject subject = new Subject();
		new SwordObserver(subject);
		new InvincibleObserver(subject);
		//subject.setState("sword");
		switch (getObState()) {
		  	case "sword":
		  		setEnemyStrategy(new OperationRunaway());
			    this.executeStrategy(dungeon);
			    break;
		  	case "invincible":
		  		setEnemyStrategy(new OperationRunaway());
			    this.executeStrategy(dungeon);
		  		break;
		  	default:
		  		//setEnemyStrategy(new OperationTowards());
			    //this.executeStrategy(dungeon);
		  		doSpecificAction(dungeon);
		}
	}
	
	
	
	public void executeStrategy(Dungeon dungeon) {
		enemyStrategy.doOperation(dungeon);
	}

	public void towardsPlayer(Dungeon dungeon) {
		int x = this.getX();
		int y = this.getY();
		int xdis = this.XDistanceToPlayer();
		int ydis = this.YDistanceToPlayer();
		int xclose = this.Xclosecoordinate();
		int yclose = this.Yclosecoordinate();
		int xaway = this.Xawaycoordinate();
		int yaway = this.Yawaycoordinate();
		//System.out.println(String.format("enemy (x, y) is (%d, %d)\n", x, y));
		//System.out.println(String.format("player (x, y) is (%d, %d)\n", this.dungeon.getPlayer().getX(), this.dungeon.getPlayer().getY()));
		//System.out.println(String.format("dis (x,y) is (%d, %d)\n", xdis, ydis));
		//System.out.println(String.format("xclose is %d\n xaway is %d\n", xclose, xaway));
		//System.out.println(String.format("yclose is %d\n yaway is %d\n", yclose, yaway));
		if (xdis != 0 && ydis != 0) {
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
		} else if (xdis == 0) {
			if (this.validMove(x, yclose) == true) {
				//System.out.println("left\n");
				this.Ygetclose();
			} 
		} else if (ydis == 0) {
			if (this.validMove(xclose, y) == true) {
				//System.out.println("xclose\n");
				this.Xgetclose();
			} 
		}
		//System.out.println(String.format("now enemy (x, y) is (%d, %d)\n", this.getX(), this.getY()));
	}
	
	
	
	public void runawayPlayer(Dungeon dungeon) {
		int x = this.getX();
		int y = this.getY();
		int xdis = this.XDistanceToPlayer();
		int ydis = this.YDistanceToPlayer();
		int xclose = this.Xclosecoordinate();
		int yclose = this.Yclosecoordinate();
		int xaway = this.Xawaycoordinate();
		int yaway = this.Yawaycoordinate();
		if (xdis != 0 && ydis != 0) {
			if (java.lang.Math.abs(xdis) > java.lang.Math.abs(ydis)) {
				if (this.validMove(x, yaway) == true) {
					//System.out.println("1111\n");
					this.Ygetaway();
				} else if (this.validMove(xaway, y) == true) {
					//System.out.println("2222\n");
					this.Xgetaway();
				} else if (this.validMove(xclose, y) == true) {
					//System.out.println("33333\n");
					this.Xgetclose();
				} else if (this.validMove(x, yclose) == true) {
					//System.out.println("4444\n");
					this.Ygetclose();
				} else {
					//System.out.println("i can go nowhere1\n");
				}
			} else if (java.lang.Math.abs(xdis) < java.lang.Math.abs(ydis)) {
				if (this.validMove(xaway, y) == true) {
					//System.out.println("7777\n");
					this.Xgetaway();
				} else if (this.validMove(x, yaway) == true) {
					//System.out.println("88888\n");
					this.Ygetaway();
				} else if (this.validMove(x, yclose) == true) {
					//System.out.println("5555\n");
					this.Ygetclose();
				} else if (this.validMove(xclose, y) == true) {
					//System.out.println("6666\n");
					this.Xgetclose();
				} else {
					//System.out.println("i can go nowhere2\n");
				}
			} else {	// equal distance on x and y
				if (this.validMove(x, yaway) == true) {
					//System.out.println("aaa\n");
					this.Ygetaway();
				} else if (this.validMove(xaway, y) == true) {
					//System.out.println("bbbb\n");
					this.Xgetaway();
				} else if (this.validMove(xclose, y) == true) {
					//System.out.println("cccc\n");
					this.Xgetclose();
				} else if (this.validMove(x, yclose) == true) {
					//System.out.println("ddddd\n");
					this.Ygetclose();
				} else {
					//System.out.println("i can go nowhere3\n");
				}
			}
		} else if (xdis == 0) {
			if (this.validMove(x-1, y) == true) {
				//System.out.println("left\n");
				this.x().set(x-1);
			} else if (this.validMove(x+1, y) == true) {
				//System.out.println("right\n");
				this.x().set(x+1);
			} else if (this.validMove(x, yaway) == true) {
				//System.out.println("yaway\n");
				this.Ygetaway();
			} else if (this.validMove(x, yclose) == true) {
				//System.out.println("yclose\n");
				this.Ygetclose();
			} else {
				//System.out.println("i can go nowhere3\n");
			}
		} else if (ydis == 0) {
			if (this.validMove(x, y-1) == true) {
				//System.out.println("up\n");
				this.y().set(y-1);
			} else if (this.validMove(x, y+1) == true) {
				//System.out.println("down\n");
				this.y().set(y+1);
			} else if (this.validMove(xaway, y) == true) {
				//System.out.println("xaway\n");
				this.Xgetaway();
			} else if (this.validMove(xclose, y) == true) {
				//System.out.println("xclose\n");
				this.Xgetclose();
			} else {
				//System.out.println("i can go nowhere3\n");
			}
		}
	}
	public void doSpecificAction(Dungeon dungeon) {
		//System.out.println(isKilled());
		if (isKilled() == false) {
			if (player.getInvinciState() instanceof CarryInvinciState || player.getSwordState() instanceof CarrySwordState) {
				this.runawayPlayer(dungeon);
			} else {
				this.towardsPlayer(dungeon);
			}
		} else {
			//System.out.println("enemy is dead!!!");
		}
	}


////////////////////////////////////////////////////
	public void Xgetclose() {

		lastX = this.getX();
		lastY = this.getY();
		int xdis = this.XDistanceToPlayer();
		if (xdis > 0) {
			this.moveLeft();
		} else if (xdis < 0) {
			this.moveRight();
		} else {
			//System.out.println("i stayed\n");
		}
	}
	public void Xgetaway() {
		lastX = this.getX();
		lastY = this.getY();
		int xdis = this.XDistanceToPlayer();
		if (xdis > 0) {
			this.moveRight();
		} else if (xdis < 0){
			this.moveLeft();
		} else {
			//System.out.println("i stayed\n");
		}
	}
	public void Ygetclose() {
		lastX = this.getX();
		lastY = this.getY();
		int ydis = this.YDistanceToPlayer();
		if (ydis > 0) {
			this.moveUp();
		} else if (ydis < 0) {
			this.moveDown();
		} else {
			//System.out.println("i stayed\n");
		}
	}
	public void Ygetaway() {
		lastX = this.getX();
		lastY = this.getY();
		int ydis = this.YDistanceToPlayer();
		if (ydis > 0) {
			this.moveDown();
		} else if (ydis < 0){
			this.moveUp();
		} else {
			//System.out.println("i stayed\n");
		}
	}
////////////////////////////////////////////////////
	public int Xclosecoordinate () {
		int xdis = this.XDistanceToPlayer();
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
		int xdis = this.XDistanceToPlayer();
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
		int ydis = this.YDistanceToPlayer();
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
		int ydis = this.YDistanceToPlayer();
		int nowy = this.getY();
		if (ydis > 0) {
			return nowy+1;
		} else if (ydis < 0) {
			return nowy-1;
		} else {
			return nowy;
		}
	}
	
	public void setObState(String obState) {
    	this.obState = obState;
    }
    
    public String getObState() {
    	return this.obState;
    }

//////////////////////////////////////////////////////	
	
	public int XDistanceToPlayer() {
		int playerX = this.player.getX();
		int enemyX = this.getX();
		return (enemyX - playerX);
	}
	public int YDistanceToPlayer() {
		int playerY = this.player.getY();
		int enemyY = this.getY();
		//System.out.println(String.format("px is %d, py is %d\nex is %d, ey is %d\n", playerX, playerY, enemyX, enemyY));
		return (enemyY - playerY);
	}	
	
	public void setEnemyStrategy(EnemyStrategy s) {
		this.enemyStrategy = s;
	}
	
	public EnemyStrategy getEnemyStrategy() {
		return this.enemyStrategy;
	}
	
	
//////////////////////////////////////////////////////////////	
    public void moveUp() {
		if (killed == false && dungeon.getPlayer().isKilled() == false) {
			y().set(getY() - 1);
		} /**else {//if (killed == true) {
			System.out.println("\u7ba1\u7528");
			x().set(dungeon.getWidth());
			y().set(dungeon.getHeight());
		}*/
    }

    public void moveDown() {
		if (killed == false && dungeon.getPlayer().isKilled() == false) {
			y().set(getY() + 1);
		} /**else {//if (killed == true) {
		System.out.println("\u7ba1\u7528");
		x().set(dungeon.getWidth());
		y().set(dungeon.getHeight());
	}*/
    }

    public void moveLeft() {
    	if (killed == false && dungeon.getPlayer().isKilled() == false) {
        	x().set(getX() - 1);
        } /**else {//if (killed == true) {
		System.out.println("\u7ba1\u7528");
		x().set(dungeon.getWidth());
		y().set(dungeon.getHeight());
	}*/
    }

    public void moveRight() {
    	if (killed == false && dungeon.getPlayer().isKilled() == false) {
            x().set(getX() + 1);
        } /**else {//if (killed == true) {
		System.out.println("\u7ba1\u7528");
		x().set(dungeon.getWidth());
		y().set(dungeon.getHeight());
	}*/
    }
    
    public boolean validMove(int x, int y) {
    	for (Entity e : dungeon.getEntity()) {
    		if (e instanceof Wall && e.getX() == x && e.getY() == y) {
    			return false;
    		} else if (e instanceof Boulder && e.getX() == x && e.getY() == y) {
				return false;
    		} else if (e instanceof Enemy && e.getX() == x && e.getY() == y) {
    			return false;
    		} else if (e instanceof Exit && e.getX() == x && e.getY() == y) {
    			return false;
    		} else if (e instanceof ClosedDoor && e.getX() == x && e.getY() == y) {
    			return false;
    		} else if (e instanceof Dog && e.getX() == x && e.getY() == y) {
    			return false;
    		} else if (e instanceof Teleport && e.getX() == x && e.getY() == y) {
    			return false;
    		}
    	}
    	//if (x == this.lastX && y == this.lastY) {
 		//	return false;
 		//}
    	return true;
    }
    
    public boolean isKilled() {
    	return killed;
    }
    
    public void setKilled(boolean b) {
    	this.killed = b;
    }
}
