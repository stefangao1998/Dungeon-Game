package unsw.dungeon;

import java.util.ArrayList;

public interface BombState {
	public void doAction(Dungeon dungeon, Player player, Entity e, ArrayList<Entity> tempDel);

	public void doActionDrop(Dungeon dungeon, Player player, Entity e);
}
