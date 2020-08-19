package unsw.dungeon;

public class OperationTowards implements EnemyStrategy {
	@Override
	public void doOperation(Dungeon dungeon) {
		//dungeon.getEnemy().towardsPlayer(dungeon);
		for (Enemy e : dungeon.getEnemy()) {
			e.towardsPlayer(dungeon);
		}
	}

}
