package unsw.dungeon;

import java.util.ArrayList;

public interface InvincibilityState {
	public void doAction(Dungeon dungeon, Entity e, Player player, ArrayList<Entity> tempDel);
}
