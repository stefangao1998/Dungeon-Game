package unsw.dungeon;

import java.util.ArrayList;

public class NoSwordState implements SwordState {

	@Override
	public void doAction(Dungeon dungeon, Entity e, Player player, ArrayList<Entity> tempDel) {
		player.setAvalableHit(0);;
		//dungeon.removeEntity(e);
		tempDel.add(e);
	}

}
