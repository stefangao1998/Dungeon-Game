package unsw.dungeon;
/**
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
*/

import java.util.ArrayList;

/**
 * The player entity
 * @author Robert Clifton-Everest
 *
 */
public class Player extends Entity {

    private Dungeon dungeon;
    private Key key;
    //private Enemy enemy;
    private ClosedDoor closedDoor;
    private int timeCount;
    private int litX;
    private int litY;
    private int AvalableHit;
    private int AvalableInv;
    private boolean killed;
    private boolean exited;
    private int meat;
    private int freeze;
    private int invFlag;
    
    private KeyState keyState;
    private BombState bombState;
    private SwordState swordState;
    private InvincibilityState invinciState;
    
    //private Enemy enemy;
    private boolean carryBomb;
    //private String myState;
    private Goal goal;

    private boolean exitFlag = true;
    private boolean treasureFlag = true;
    private boolean bouldersFlag = true;
    private boolean enemiesFlag = true;
     
    //private DoorState doorState;
    //private DungeonController dungeonController;
    //private boolean carry;
    //private int carriedTreasure;
    //@FXML
    //private GridPane squares;
    //Image ground = new Image("/dirt_0_new.png");
    /**
     * Create a player positioned in square (x,y)
     * @param x
     * @param y
     */
    public Player(Dungeon dungeon, int x, int y) {
        super(x, y);
        this.dungeon = dungeon;
        //this.carry = false;
        //this.carriedTreasure = 0;
        this.keyState = new NoKeyState();
        this.bombState = new NoBombState();
        this.swordState = new NoSwordState();
        //this.doorState = new ClosedDoorState();
        this.carryBomb = false;
        this.timeCount = 0;
        this.litX = -1;
        this.litY = -1;
        //this.enemy = new Enemy();
        this.AvalableHit = 0;
        this.killed = false;
        this.AvalableInv = 0;
        this.invinciState = new NoInvinciState();
        this.meat = 0;
        this.freeze = 0;
        this.invFlag = 0;
        this.exited = false;
    }

    public void moveUp() {
        if (killed == true) {
            System.out.println("Game Over");
            //deletePlayer();
        } else if (exited == true) {
        	//System.out.println("exited");
        } else if (getY() > 0 && validMove(0, -1)) {
            y().set(getY() - 1);
            if (freeze > 0) freeze--;
            if (timeCount == 0) Doaction();
            if (timeCount > 0) timeCount++;
            if (AvalableInv >= 0) AvalableInv--;
            //System.out.println(timeCount);
            //System.out.println(AvalableInv);
            //this.enemy.doAction(dungeon, this);
            
        checkSwordState();
        //checkPosition();
        checkKillEnemy();
        checkBoulderGoal();
        //checkbombBomb();
        checkInv();
        if (dungeon.getEnemy() != null && this.freeze <= 0) {
                for (Enemy e : dungeon.getEnemy()) {
                    if (e instanceof Enemy)
                        //e = (Enemy) e;
                        e.doAction(dungeon);
                }
                
            }
            if (dungeon.getDog() != null && this.freeze <= 0) {
                dungeon.getDog().doAction(dungeon);
            }
        }
        checkbombBomb();
        checkPosition();
    }

    public void moveDown() {
        if (killed == true) {
            System.out.println("Game Over");
            //deletePlayer();
        } else if (exited == true) {
        	//System.out.println("exited");
        } else if (getY() < dungeon.getHeight() - 1 && validMove(0, 1)) {
            y().set(getY() + 1);
            if (freeze > 0) freeze--;
            if (timeCount == 0) Doaction();
            if (timeCount > 0) timeCount++;
            if (AvalableInv >= 0) AvalableInv--;
            //System.out.println(timeCount);
            //System.out.println(AvalableInv);
            //enemy.doAction(dungeon, this);
            //dungeon.getEnemy().doAction(dungeon, this);
            /**if (dungeon.getEnemy() != null) {
                dungeon.getEnemy().doAction(dungeon);
            }*/
        //System.out.println("movedown");
        //System.out.println(getX()+" "+getY());
        checkSwordState();
        //checkPosition();
        checkKillEnemy();
        checkBoulderGoal();
        //checkbombBomb();
        checkInv();
        //checkSwordState();
            if (dungeon.getEnemy() != null && this.freeze <= 0) {
                for (Enemy e : dungeon.getEnemy()) {
                    if (e instanceof Enemy)
                        //e = (Enemy) e;
                        e.doAction(dungeon);
                }
            }
            if (dungeon.getDog() != null && this.freeze <= 0) {
                dungeon.getDog().doAction(dungeon);
            }
        }
        checkbombBomb();
        checkPosition();
    }

    public void moveLeft() {
        if (killed == true) {
            System.out.println("Game Over");
            //deletePlayer();
        } else if (exited == true) {
        	//System.out.println("exited");
        } else if (getX() > 0 && validMove(-1, 0)) {
            x().set(getX() - 1);
            if (freeze > 0) freeze--;
            if (timeCount == 0) Doaction();
            if (timeCount > 0) timeCount++;
            if (AvalableInv >= 0) AvalableInv--;
            //System.out.println(timeCount);
            //System.out.println(AvalableInv);
            //dungeon.getEnemy().doAction(dungeon, this);
            /**if (dungeon.getEnemy() != null) {
                dungeon.getEnemy().doAction(dungeon);
            }*/
        checkSwordState();
        //checkPosition();
        checkKillEnemy();
        checkBoulderGoal();
        //checkbombBomb();
        checkInv();
            if (dungeon.getEnemy() != null && this.freeze <= 0) {
                for (Enemy e : dungeon.getEnemy()) {
                    if (e instanceof Enemy)
                        //e = (Enemy) e;
                        e.doAction(dungeon);
                }
                
            }
            if (dungeon.getDog() != null && this.freeze <= 0) {
                dungeon.getDog().doAction(dungeon);
            }
        }
        //checkSwordState();
        checkbombBomb();
        checkPosition();
    }

    public void moveRight() {
        if (killed == true) {
            System.out.println("Game Over");
            //this.x().set(dungeon.getWidth());
            //this.y().set(dungeon.getHeight());
            //deletePlayer();
        } else if (exited == true) {
        	//System.out.println("exited");
        } else if (getX() < dungeon.getWidth() - 1 && validMove(1, 0)) {
            x().set(getX() + 1);
            if (freeze > 0) freeze--;
            if (timeCount == 0) Doaction();
            if (timeCount > 0) timeCount++;
            if (AvalableInv >= 0) AvalableInv--;
            //System.out.println(AvalableInv);
            
            //this.enemy.doAction(dungeon, this);
            //test();
            //dungeon.getEnemy().doAction(dungeon, this);
            /**if (dungeon.getEnemy() != null) {
                dungeon.getEnemy().doAction(dungeon);
            }*/
        //System.out.println(getX()+" "+getY());
        checkSwordState();
        //checkPosition();
        checkKillEnemy();
        checkBoulderGoal();
        //checkbombBomb();
        //checkSwordState();
        checkInv();
            if (dungeon.getEnemy() != null && this.freeze <= 0) {
                for (Enemy e : dungeon.getEnemy()) {
                    if (e instanceof Enemy)
                        //e = (Enemy) e;
                        e.doAction(dungeon);
                }
                
            }
            if (dungeon.getDog() != null && this.freeze <= 0) {
                dungeon.getDog().doAction(dungeon);
            }
        }
        checkbombBomb();
        checkPosition();
    }
    
    public void dropBomb() {
    	if (exited == false) {
	        if (this.getCarryBomb()) {
	        	if (freeze > 0) freeze--;
	            this.carryBomb = false;
	            this.setBombState(new NoBombState());
	            this.getBombState().doActionDrop(dungeon, this, new Bomb(getX(), getY()));
	            this.litX = getX(); this.litY = getY();
	            for (Entity e : dungeon.getEntity()) {
	                if (e instanceof StageOne) {e.x().set(this.litX); e.y().set(this.litY);break;}
	            }
	            timeCount++;
	        } else {
	            System.out.println("You don't have a bomb!");
	        }
	        //test();
    	}
    }
    
    
    public Goal getGoal() {
        return goal;
    }
    
    public void setGoal(Goal goal) {
        this.goal = goal;
    }
    
    public void setGoalTrue(String s) {
    	//System.out.println(dungeon.getPlayer().getGoal()); 
    	if (dungeon.getPlayer().getGoal().getName().equals(s)) {dungeon.getPlayer().getGoal().setState(true);}
        for (Goal subgoal1 : dungeon.getPlayer().getGoal().getSubgoals()) {
            //System.out.println(subgoal1 + "11111");
            if (subgoal1.getName().equals(s)) {
                subgoal1.setState(true);
            }
            for (Goal subgoal2 : subgoal1.getSubgoals()) {
                //System.out.println(subgoal2 + "22222");
                if (subgoal2.getName().equals(s)) {
                    subgoal2.setState(true);
                }
                for (Goal subgoal3 : subgoal2.getSubgoals()) {
                    //System.out.println(subgoal3 + "33333");
                    if (subgoal3.getName().equals(s)) {
                        subgoal3.setState(true);
                    }
                    
                }
            }
         }
    }

    
    public void checkPosition() {

        //System.out.println(goal.isExit());
        //System.out.println(goal.isEnemies());
        //System.out.println(goal.isBoulders());
        //System.out.println(goal.isTreasure());
        //System.out.println("=====");
        //System.out.println(goal.isNeedexit());
        //System.out.println(goal.isNeedenemies());
        //System.out.println(goal.isNeedboulders());
        //System.out.println(goal.isNeedtreasure());
        
        ArrayList<Entity> tempDel = new ArrayList<Entity>();
        int once = 0;
        for (Entity e : dungeon.getEntity()) {
            if (e instanceof Exit && e.getX() == this.getX() && e.getY() == this.getY() && exitFlag && goal.isNeedexit()) {
                System.out.println("You Passed the Exit Goal!!");
                this.x().set(dungeon.getWidth());
                this.y().set(dungeon.getHeight());
                setGoalTrue("exit");
                exitFlag=false;
                goal.updateGoal();
                this.exited = true;
                //System.out.println(this.goal.isState());
                if (!this.goal.isState()) {System.out.println("you did NOT pass all the goal, Game Over");}
                //goal.printGoal();
                break;
            } else if (e instanceof Treasure && e.getX() == this.getX() && e.getY() == this.getY()) {
                System.out.println("You grab a treasure!");
                //dungeon.removeEntity(e);
                tempDel.add(e);
                //e.x().set(18);
                //e.y().set(15);
                e.x().set(dungeon.getWidth());
                e.y().set(dungeon.getHeight());
                /**for (Entity en : dungeon.getEntity()) {
                    if(en instanceof Wall && (en.getX() == 18 && en.getY() == 15)) {
                        en.x().set(18);
                        en.y().set(15);
                    }
                }*/
                //carriedTreasure++;
                //dungeonController.removeImage(e);
                //squares.getChildren().remove(e);
                //squares.add(new ImageView(ground), e.getX(), e.getY());
                //gridPane.getChildren().remove(image);
                //if (dungeon.totalTreasure() == 0) System.out.println("You Passed the Treasure Goal!!");
                //dungeonController.removeImage(e, e.getX(), e.getY());
                break;
            } else if (e instanceof Key && e.getX() == this.getX() && e.getY() == this.getY() && this.getKeyState() instanceof NoKeyState) {
                //this.key = (Key) e;
                //this.carry = true;
                this.setKeyState(new CarryKeyState());
                this.getKeyState().doAction(dungeon, e, this, tempDel);
                //System.out.println("Under Player class, this key's id is "+this.key.getId());
                System.out.println("You grab a key, id is "+this.key.getId());
                e.x().set(dungeon.getWidth()+1);
                e.y().set(dungeon.getHeight()+1);
                //dungeon.removeEntity(e);
                break;
            } else if (e instanceof ClosedDoor && e.getX() == this.getX() && e.getY() == this.getY() && this.getKeyState() instanceof CarryKeyState) {
                this.closedDoor = (ClosedDoor) e;
                //System.out.println("Under Player class, this closed door's id is "+this.closedDoor.getId());
                
                if (this.key != null && this.closedDoor != null) {
                    if (this.closedDoor.getId() == this.key.getId()) {
                        System.out.println("Matched. Door opens!");
                        //this.key = null;
                        //this.carry = false;
                        //dungeon.removeEntity(e);
                        e.x().set(dungeon.getWidth());
                        e.y().set(dungeon.getHeight());
                        this.setKeyState(new NoKeyState());
                        this.getKeyState().doAction(dungeon, e, this, tempDel);
                    } else {
                        System.out.println("Not Matched. Door keeps closed!");
                    }
                }
                break;
            } else if (e instanceof Bomb && e.getX() == this.getX() && e.getY() == this.getY() && this.getBombState() instanceof NoBombState && this.timeCount == 0) {
                this.setBombState(new CarryBombState());
                this.getBombState().doAction(dungeon, this, e, tempDel);
                this.carryBomb = true;
                break;
            } else if (e instanceof Sword && e.getX() == this.getX() && e.getY() == this.getY() && this.getSwordState() instanceof NoSwordState) {
                //System.out.println("now you have a sword");
                this.setSwordState(new CarrySwordState());
                //dungeon.getDungeonController().deleteImage(e.getX(), e.getY());
                //dungeon.
                this.getSwordState().doAction(dungeon, e, this, tempDel);
            } else if (e instanceof Enemy && e.getX() == this.getX() && e.getY() == this.getY() && (this.getSwordState() instanceof CarrySwordState || this.getInvinciState() instanceof CarryInvinciState)) {
                //if (this.AvalableHit > 0) { dungeon.removeEntity(e); this.AvalableHit--; }
                //else if (this.AvalableHit <= 0) { killed = true; dungeon.removeEntity(this); this.setSwordState(new NoSwordState());}
                tempDel.add(e); this.AvalableHit--;
                //e
                
                e.x().set(dungeon.getWidth());
                e.y().set(dungeon.getHeight());
            } else if (e instanceof Enemy && e.getX() == this.getX() && e.getY() == this.getY() && this.getSwordState() instanceof NoSwordState) {
                killed = true; tempDel.add(this); 
                this.x().set(dungeon.getWidth());
                this.y().set(dungeon.getHeight());
                break;
            } else if (e instanceof Invincibility && e.getX() == this.getX() && e.getY() == this.getY() && this.getInvinciState() instanceof NoInvinciState) {
                System.out.println("You are now invisible!");
                invFlag = 1;
                this.setInvinciState(new CarryInvinciState());
                this.getInvinciState().doAction(dungeon, e, this, tempDel);
            } else if (e instanceof Meat && e.getX() == this.getX() && e.getY() == this.getY()) {
                this.meat++;
                e.x().set(dungeon.getWidth());
                e.y().set(dungeon.getHeight());
                tempDel.add(e);
            } else if (e instanceof Dog && e.getX() == this.getX() && e.getY() == this.getY()) {
                if (this.meat <= 0) {
                    System.out.println("Game Over");
                    this.x().set(dungeon.getWidth());
                    this.y().set(dungeon.getHeight());
                    this.killed = true;
                } else {
                    
                    dungeon.getDog().setPickedup(true);
                }
            } else if (e instanceof Teleport && e.getX() == this.getX() && e.getY() == this.getY()) {
                for (Entity t : dungeon.getEntity()) {
                    if (t instanceof Teleport && t != e) {
                        //System.out.println("here");
                        //System.out.println(t.getX());
                        //System.out.println(t.getY());
                        this.x().set(t.getX());
                        this.y().set(t.getY());
                        break;
                    }
                }
                break;
            } else if (e instanceof TimeF && e.getX() == this.getX() && e.getY() == this.getY()) {
            	e.x().set(dungeon.getWidth());
            	e.y().set(dungeon.getHeight());
            	this.freeze = 4;
            	break;
            }
        }
        /**if (dungeon.getEnemy().isKilled() == true) {
            //System.out.println("sdfsfd");
            dungeon.getEnemy().x().set(dungeon.getWidth());
            dungeon.getEnemy().y().set(dungeon.getHeight());
        }*/
        for (Entity e : tempDel) {
            dungeon.removeEntity(e);
        }
        if (dungeon.totalTreasure() == 0 && treasureFlag && goal.isNeedtreasure()) {
            System.out.println("You Passed the Treasure Goal!!");
            setGoalTrue("treasure");
            treasureFlag=false;
            goal.updateGoal();
            //goal.printGoal();
            //goal.setTreasure(true);
        }
        if (dungeon.totalEnemy() == 0 && enemiesFlag && goal.isNeedenemies()) {
            System.out.println("You Passed the Enemies Goal!!");
            setGoalTrue("enemies");
            enemiesFlag = false;
            goal.updateGoal();
            //goal.printGoal();
            //goal.setEnemies(true);
        }
        
        
        //if (dungeon.totalTreasure() == 0) System.out.println("You Passed the Treasure Goal!!");
    }
    
    public InvincibilityState getInvinciState() {
        return this.invinciState;
    }
    
    public void setInvinciState(InvincibilityState state) {
        this.invinciState = state;
    }

    public void setSwordState(SwordState state) {
        this.swordState = state;
    }
    
    public SwordState getSwordState() {
        return this.swordState;
    }
    
    public void setAvalableHit(int num) {
        this.AvalableHit = num;
    }

    public int getAvalableHit() {
        return this.AvalableHit;
    }
    
    public boolean validMove(int mX, int mY) {
        //System.out.println(dungeon.getPlayer());
        for (Entity e : dungeon.getEntity()) {
            if (e instanceof Wall && e.getX() == getX()+mX && e.getY() == getY()+mY) {
                return false;
            } else if (e instanceof Boulder && e.getX() == getX()+mX && e.getY() == getY()+mY) {
                //System.out.println("boulder boulder");
                if (boulderValidMv(mX, mY)) {
                    //dungeon.removeEntity(e);
                    //dungeon.addEntity(new Boulder(getX()+mX+mX, getY()+mY+mY));
                    //dungeon.getEntity()
                    //System.out.println("mY is "+mY);
                    //System.out.println(getX()+mX+mX+" "+(getY()+mY+mY)+" asdf");
                    //test();
                    for (Entity en : dungeon.getEntity()) {
                        if (en instanceof Boulder && en.getX() == getX()+mX && en.getY() == getY()+mY) {
                            en.x().set(en.getX()+mX);
                            en.y().set(en.getY()+mY);
                        }
                    }
                    //dungeon.removeEntity(e);
                    return true;
                } else {
                    return false;
                }
                /**switch (move) {
                    case "up":
                        return boulderValidMv(mX, mY, 0, -1);
                        //break;
                    case "down":
                        return boulderValidMv(mX, mY, 0, 1);
                        //break;
                    case "left":
                        return boulderValidMv(mX, mY, -1, 0);
                        //break;
                    case "right":
                        return boulderValidMv(mX, mY, 1, 0);
                        //break;
                }*/
            } else if (e instanceof ClosedDoor && e.getX() == getX()+mX && e.getY() == getY()+mY) {
                this.closedDoor = (ClosedDoor) e;
                if (this.key != null && this.closedDoor != null) {
                    if (this.closedDoor.getId() == this.key.getId() && this.getKeyState() instanceof CarryKeyState) {
                        return true;
                    } else {
                        return false;
                    }
                } else 
                    return false;
            }
        }
        return true;
    }

    /**public boolean carryKey() {
        if (this.carry == false) return false;
        else return true;
    }*/
    
    public void setKey(Entity e) {
        this.key = (Key) e;
    }
    
    public Key getKey() {
        return this.key;
    }
    
    public void setKeyState(KeyState state) {
        this.keyState = state;
    }
    
    public KeyState getKeyState() {
        return this.keyState;
    }
    
    public void setBombState(BombState state) {
        this.bombState = state;
    }
    
    public BombState getBombState() {
        return this.bombState;
    }
    
    public boolean boulderValidMv(int mX, int mY) {
        for (Entity e : dungeon.getEntity()) {
            if (e instanceof Wall && e.getX() == getX()+mX+mX && e.getY() == getY()+mY+mY) return false;
            if (e instanceof Boulder && e.getX() == getX()+mX+mX && e.getY() == getY()+mY+mY) return false;
            if (e instanceof Enemy && e.getX() == getX()+mX+mX && e.getY() == getY()+mY+mY) return false;
        }
        return true;
    }
    
    public void checkBoulderGoal() {
        int meet = 0;
        for (Entity e : dungeon.getEntity()) {
            if (e instanceof Switch) {
                meet+=triggerCheck(e.getX(), e.getY());
            }
        }
        
        if (meet == dungeon.totalSwitch() && bouldersFlag && goal.isNeedboulders()) {
            System.out.println("You Passed the Boulder Goal!!");
            setGoalTrue("boulders");
            bouldersFlag = false;
            goal.updateGoal();
            //goal.printGoal();
            //goal.setBoulders(true);
        } else {
            //System.out.println("Did not pass the Boulder Goal!");
        }
    }
    
    /**public void setMyState(String myState) {
        this.myState = myState;
    }
    
    public String getmyState() {
        return this.myState;
    }*/
    
    public int triggerCheck(int mX, int mY) {
        for (Entity e : dungeon.getEntity()) {
            if (e instanceof Boulder && e.getX() == mX && e.getY() == mY) return 1; 
        }
        return 0;
    }

    /**public void test() {
        for (Entity e : dungeon.getEntity()) {
            if (e instanceof Wall) {
                
            } else {
                //System.out.println(e.getClass().getName()+" "+e.getX()+" "+e.getY());
            }
        }
    }*/
    
    public boolean getCarryBomb() {
        return this.carryBomb;
    }
    
    public void setCarryBomb(boolean b) {
        this.carryBomb = b;
    }
    
    public void deletePlayer() {
        this.x().set(dungeon.getWidth());
        this.y().set(dungeon.getHeight());
    }
    
    /**public void checkbombBomb() {
        if (this.timeCount == 5) {
            System.out.println("testetstst");
            //test();
        }
        ArrayList<Entity> tempDel = new ArrayList<Entity>();
        if (this.timeCount == 5) {
            //test();
            this.timeCount = 0;
            //destroyEntity(tempDel);
            for (Entity e : dungeon.getEntity()) {
                if ((e instanceof Player || e instanceof Enemy || e instanceof Boulder || e instanceof Dog) && adjacent(e)) {
                    //if (e instanceof Player && this.AvalableInv >= 0) {System.out.println("can not be destroyed since invincibitliy");}
                    //System.out.println(e.getClass()+" will be destroyed! "+e.getX()+" "+e.getY());
                    tempDel.add(e);
                    e.x().set(dungeon.getWidth());
                    e.y().set(dungeon.getHeight());
                }
            }
            for (Entity e : dungeon.getEntity()) {
                if (e instanceof Bomb && e.getX() == this.litX && e.getY() == this.litY) {
                    //dungeon.removeEntity(e);
                    tempDel.add(e);
                }
            }
            this.litX = -1; this.litY = -1;
            //System.out.println("after");
            //test();
            //System.out.println("here is the entity need to be deleted");
            for (Entity e : tempDel) {
                //System.out.println(e.getClass()+" "+e.getX()+" "+e.getY());
                dungeon.removeEntity(e);
            }
        }
        if (tempDel.contains(this)) {
            System.out.println("You are out!");
            killed = true;
        }
    }*/

    public void checkbombBomb() {
        /**for (Entity e: dungeon.getEntity()) {
            if (e instanceof StageOne && this.timeCount == 1) {
                e.x().set(dungeon.getWidth());
                e.y().set(dungeon.getHeight());
                break;
            } else if (e instanceof StageTwo && this.timeCount == 2) {
                e.x().set(dungeon.getWidth());
                e.y().set(dungeon.getHeight());
                break;
            } else if (e instanceof StageThree && this.timeCount == 3) {
                e.x().set(dungeon.getWidth());
                e.y().set(dungeon.getHeight());
                break;
            } else if (e instanceof StageFour && this.timeCount == 4) {
                e.x().set(dungeon.getWidth());
                e.y().set(dungeon.getHeight());
                break;
            }
        }*/
        for (Entity e : dungeon.getEntity()) {
            if (this.timeCount == 2 && e instanceof StageOne) {
                e.x().set(dungeon.getWidth());
                e.y().set(dungeon.getHeight());
            } else if (this.timeCount == 2 && e instanceof StageTwo) {
                e.x().set(this.litX);
                e.y().set(this.litY);
            } else if (this.timeCount == 3 && e instanceof StageTwo) {
                e.x().set(dungeon.getWidth());
                e.y().set(dungeon.getHeight());
            } else if (this.timeCount == 3 && e instanceof StageThree) {
                e.x().set(this.litX);
                e.y().set(this.litY);
            } else if (this.timeCount == 4 && e instanceof StageThree) {
                e.x().set(dungeon.getWidth());
                e.y().set(dungeon.getHeight());
            } else if (this.timeCount == 4 && e instanceof StageFour) {
                //System.out.println("over here123123212312");
                e.x().set(this.litX);
                e.y().set(this.litY);
            } else if (this.timeCount == 5 && e instanceof StageFour) {
                //System.out.println("aaaaaaaaaaaaaaaaaaaaaaa");
                e.x().set(dungeon.getWidth());
                e.y().set(dungeon.getHeight());
            } /**else if (this.timeCount == 5 && e instanceof StageFive) {
                System.out.println("over here");
                e.x().set(this.litX);
                e.y().set(this.litY);
            }*/
        }
        if (this.timeCount == 5) {
            //System.out.println("testetstst");
            //test();
            /**int flagForb = 0;
            for (Entity e : dungeon.getEntity()) {
                if (e instanceof StageFive) {
                    if (flagForb == 0) {
                        e.x().set(this.litX);
                        e.y().set(this.litY);
                    } else if (flagForb == 1) {
                        e.x().set(this.litX-1);
                        e.y().set(this.litY);
                    } else if (flagForb == 2) {
                        e.x().set(this.litX+1);
                        e.y().set(this.litY);
                    } else if (flagForb == 3) {
                        e.y().set(this.litX);
                        e.y().set(this.litY-1);
                    } else if (flagForb == 4){
                        e.y().set(this.litX);
                        e.y().set(this.litY+1);
                    }
                    flagForb++;
                }
            }*/
            int fl = 0;
            for (StageFive s : dungeon.getStageFive()) {
                if (fl == 0) {
                    s.x().set(this.litX);
                    s.y().set(this.litY);
                    fl++;
                } else if (fl == 1) {
                    s.x().set(this.litX-1);
                    s.y().set(this.litY);
                    fl++;
                } else if (fl == 2) {
                    s.x().set(this.litX+1);
                    s.y().set(this.litY);
                    fl++;
                } else if (fl == 3) {
                    s.x().set(this.litX);
                    s.y().set(this.litY-1);
                    fl++;
                } else if (fl == 4) {
                    s.x().set(this.litX);
                    s.y().set(this.litY+1);
                    fl++;
                }
            }
        }
        ArrayList<Entity> tempDel = new ArrayList<Entity>();
        if (this.timeCount == 5) {
            //test();
            this.timeCount = 0;
            //destroyEntity(tempDel);
            for (Entity e : dungeon.getEntity()) {
                if (((e instanceof Player && this.getInvinciState() instanceof NoInvinciState) || e instanceof Enemy || e instanceof Boulder || e instanceof Dog) && adjacent(e)) {
                    //if (e instanceof Player && this.AvalableInv >= 0) {System.out.println("can not be destroyed since invincibitliy");}
                    //System.out.println(e.getClass()+" will be destroyed! "+e.getX()+" "+e.getY());
                    tempDel.add(e);
                    e.x().set(dungeon.getWidth());
                    e.y().set(dungeon.getHeight());
                }
            }
            for (Entity e : dungeon.getEntity()) {
                if (e instanceof Bomb && e.getX() == this.litX && e.getY() == this.litY) {
                    //dungeon.removeEntity(e);
                    tempDel.add(e);
                }
            }
            this.litX = -1; this.litY = -1;
            //System.out.println("after");
            //test();
            //System.out.println("here is the entity need to be deleted");
            for (Entity e : tempDel) {
                //System.out.println(e.getClass()+" "+e.getX()+" "+e.getY());
                dungeon.removeEntity(e);
            }
        }
        if (tempDel.contains(this)) {
            System.out.println("You are out!");
            killed = true;
        }
    }
    
    public boolean isKilled1() {
        return true;
    }
    
    public void destroyEntity(ArrayList<Entity> tempDel) {
        for (Entity e : dungeon.getEntity()) {
            if ((e instanceof Player || e instanceof Enemy || e instanceof Boulder || e instanceof Dog) && adjacent(e)) {
                //if (e instanceof Player && this.AvalableInv >= 0) {System.out.println("can not be destroyed since invincibitliy");}
                //System.out.println(e.getClass()+" will be destroyed! "+e.getX()+" "+e.getY());
                tempDel.add(e);
            }
        }
    }
    
    public boolean adjacent(Entity e) {
        if (e.getX() == this.litX && (e.getY() == this.litY || e.getY() == this.litY+1 || e.getY() == this.litY-1)) return true;
        if (e.getY() == this.litY && (e.getX() == this.litX-1 || e.getX() == this.litX+1 || e.getX() == this.litX)) return true;
        return false;
    }
    
    public void checkSwordState() {
        if (this.AvalableHit <= 0) {
            this.setSwordState(new NoSwordState());
        }
    }
    
    public void setAvalableInv(int num) {
        this.AvalableInv = num;
    }
    
    public int getAvalableInv() {
        return this.AvalableInv;
    }
    
    public void checkInv() {
        if (this.AvalableInv <= 0 && invFlag == 1) {
        	this.setInvinciState(new NoInvinciState());
        	System.out.println("You are now visible!");
        	invFlag = 0;
        }
    }
    
    public boolean isKilled() {
        return killed;
    }

    public void Doaction() {
        for (Entity e : dungeon.getEntity()) {
            if (e instanceof StageFive) {
                e.x().set(dungeon.getWidth());
                e.y().set(dungeon.getHeight());
            }
        }
    }
    public void checkKillEnemy() {
        for (Enemy e : dungeon.getEnemy()) {
            if (e.getX() == this.getX() && e.getY() == this.getY()) {
                e.setKilled(true);
            }
        }
    }
    
    public int getMeat() {
    	return this.meat;
    }
    
    public void dropKey() {
    	if (exited == false) {
	    	if (getKeyState() instanceof CarryKeyState) {
	    		int idd = getKey().getId();
	    		for (Entity e : dungeon.getEntity()) {
	    			if (e instanceof Key && ((Key) e).getId() == idd) {
	    				e.x().set(getX());
	    				e.y().set(getY());
	    			}
	    		}
	    		this.setKeyState(new NoKeyState());
	    		System.out.println("You dropped a key: id " + getKey().getId());
	    	} else {
	    		System.out.println("You don't have a key!");
	    	}
    	}
    }
}
