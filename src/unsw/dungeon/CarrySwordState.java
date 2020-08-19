package unsw.dungeon;

import java.util.ArrayList;

public class CarrySwordState implements SwordState {

	@Override
	public void doAction(Dungeon dungeon, Entity e, Player player, ArrayList<Entity> tempDel) {
		// TODO Auto-generated method stub
		//System.out.println("Now you have a sword under carryswordstate");
		System.out.println("You grab a sword!");
		player.setAvalableHit(5);;
		//dungeon.removeEntity(e);
		tempDel.add(e);
		e.x().set(dungeon.getWidth());
		e.y().set(dungeon.getHeight());
		/**for (Entity en : dungeon.getEntity()) {
			if(en instanceof Wall  && (en.getX() ==18 && en.getY() == 15 )) {
				en.x().set(18);
				en.y().set(15);
			}
		}*/
	}

}
