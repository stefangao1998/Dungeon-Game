package unsw.dungeon;

import java.util.ArrayList;

public interface KeyState {
	
	public void doAction(Dungeon dungeon, Entity e, Player player, ArrayList<Entity> tempDel);
}
