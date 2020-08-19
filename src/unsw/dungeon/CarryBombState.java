package unsw.dungeon;

import java.util.ArrayList;

public class CarryBombState implements BombState {

	@Override
	public void doAction(Dungeon dungeon, Player player, Entity e, ArrayList<Entity> tempDel) {
		// TODO Auto-generated method stub
		player.setCarryBomb(true);
		//dungeon.removeEntity(e);
		tempDel.add(e);
		e.x().set(dungeon.getWidth());
		e.y().set(dungeon.getHeight());
	}

	@Override
	public void doActionDrop(Dungeon dungeon, Player player, Entity e) {
		// TODO Auto-generated method stub
		
	}

}
