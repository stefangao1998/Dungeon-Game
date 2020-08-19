package unsw.dungeon;

import java.util.ArrayList;

public class CarryInvinciState implements InvincibilityState {

	@Override
	public void doAction(Dungeon dungeon, Entity e, Player player, ArrayList<Entity> tempDel) {
		// TODO Auto-generated method stub
		player.setAvalableInv(5);
		//dungeon.removeEntity(e);
		tempDel.add(e);
		e.x().set(dungeon.getWidth());
		e.y().set(dungeon.getHeight());
	}

}
