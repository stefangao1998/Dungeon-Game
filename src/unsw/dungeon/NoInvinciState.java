package unsw.dungeon;

import java.util.ArrayList;

public class NoInvinciState implements InvincibilityState {

	@Override
	public void doAction(Dungeon dungeon, Entity e, Player player, ArrayList<Entity> tempDel) {
		player.setAvalableInv(0);
		
	}

}
