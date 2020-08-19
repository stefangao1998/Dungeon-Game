/**
 *
 */
package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

/**
 * A dungeon in the interactive dungeon player.
 *
 * A dungeon can contain many entities, each occupy a square. More than one
 * entity can occupy the same square.
 *
 * @author Robert Clifton-Everest
 *
 */
public class Dungeon {

    private int width, height;
    private List<Entity> entities;
    private Player player;
    //private List<Enemy> enemies;

    public Dungeon(int width, int height) {
        this.width = width;
        this.height = height;
        this.entities = new ArrayList<>();
        this.player = null;
    }

    


	public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Player getPlayer() {
        return player;
    }

    public List<Enemy> getEnemy() {
    	List<Enemy> enemyreturn = new ArrayList<Enemy>(); 
    	for (Entity e : entities) {
    		if (e instanceof Enemy) enemyreturn.add((Enemy) e);
    	}
    	return enemyreturn;
    }
    
    public List<StageFive> getStageFive() {
    	List<StageFive> fivereturn = new ArrayList<StageFive>(); 
    	for (Entity e : entities) {
    		if (e instanceof StageFive) fivereturn.add((StageFive) e);
    	}
    	return fivereturn;
    }
    
    /**public List<Dog> getDog() {
    	List<Dog> dogreturn = new ArrayList<Dog>(); 
    	for (Entity e : entities) {
    		if (e instanceof Dog) dogreturn.add((Dog) e);
    	}
    	return dogreturn;
    }*/
    
    public Dog getDog() {
    	for (Entity e : getEntity()) {
    		if (e instanceof Dog) {
    			return (Dog) e;
    		}
    	}
    	return null;
    }
    
    public void setPlayer(Player player) {
        this.player = player;
    }

    public void addEntity(Entity entity) {
        entities.add(entity);
    }
    
    public List<Entity> getEntity() {
    	return this.entities;
    }
    
    public void removeEntity(Entity entity) {
    	entities.remove(entity);
    	//entities.
    }
    
    public int totalTreasure() {
    	int i = 0;
    	for (Entity m: entities) {
    		if (m instanceof Treasure) i++;
    	}
    	return i;
    }
    
    public int totalEnemy() {
    	int i = 0;
    	for (Entity m: entities) {
    		if (m instanceof Enemy) i++;
    	}
    	return i;
    }
    
    public int totalSwitch() {
    	int i = 0;
    	for (Entity m : entities) {
    		if (m instanceof Switch) i++;
    	}
    	return i;
    }
}
