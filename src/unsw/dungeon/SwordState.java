package unsw.dungeon;

import java.util.ArrayList;

public interface SwordState {
	public void doAction(Dungeon dungeon, Entity e, Player player, ArrayList<Entity> tempDel);
}
