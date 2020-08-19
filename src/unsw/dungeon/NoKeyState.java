package unsw.dungeon;

import java.util.ArrayList;

public class NoKeyState implements KeyState {
	private Dungeon dungeon;
	
	public NoKeyState () {
		this.dungeon = dungeon;
	}
	@Override
	public void doAction(Dungeon dungeon, Entity e, Player player, ArrayList<Entity> tempDel) {
		// TODO Auto-generated method stub
		//System.out.println("Under nokeystate");
		player.setKey(null);
		//dungeon.removeEntity(e);
		tempDel.add(e);
	}

}


//this.key = null;
//this.carry = false;
//dungeon.removeEntity(e);