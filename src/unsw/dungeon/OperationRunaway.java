package unsw.dungeon;

public class OperationRunaway implements EnemyStrategy {

	@Override
	public void doOperation(Dungeon dungeon) {
		//dungeon.getEnemy().runawayPlayer(dungeon);
		for (Enemy e : dungeon.getEnemy()) {
			e.runawayPlayer(dungeon);
		}
	}

}
