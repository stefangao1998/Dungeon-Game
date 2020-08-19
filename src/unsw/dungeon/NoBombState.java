package unsw.dungeon;

import java.util.ArrayList;

public class NoBombState implements BombState {

	@Override
	public void doAction(Dungeon dungeon, Player player, Entity e, ArrayList<Entity> tempDel) {
		// TODO Auto-generated method stub
		player.setCarryBomb(false);
		dungeon.addEntity(e);
		//player.test();
	}
	
	/**public void doActionDrop(Dungeon dungeon, Player player, Entity e) {
		player.setCarryBomb(false);
		dungeon.addEntity(e);
		player.test();
	}*/

	@Override
	public void doActionDrop(Dungeon dungeon, Player player, Entity e) {
		// TODO Auto-generated method stub
		player.setCarryBomb(false);
		dungeon.addEntity(e);
		//player.test();
	}

}
