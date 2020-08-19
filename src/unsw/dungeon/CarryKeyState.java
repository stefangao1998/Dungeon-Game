package unsw.dungeon;

import java.util.ArrayList;

public class CarryKeyState implements KeyState {
	//public Player player;
	private Dungeon dungeon;
	
	public CarryKeyState () {
		this.dungeon = dungeon;
	}
	
	@Override
	public void doAction(Dungeon dungeon, Entity e, Player player, ArrayList<Entity> tempDel) {
		// TODO Auto-generated method stub
		
		player.setKey(e);
		//System.out.println("under carrykeystate");
		//player.getClass().
		//dungeon.removeEntity(e);
		//tempDel.add(e);
		//player.carryKey()
	}

}
