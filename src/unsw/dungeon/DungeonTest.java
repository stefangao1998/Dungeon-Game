package unsw.dungeon;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
//import junit.framework.Assert;
//import junit.framework.TestCase;

public class DungeonTest {
	// *******************************************
	// Test Player Move up, down, left and right
	
	// This won't change the Player's position
	// Since there's a Wall blocked
	@Test
	public void testPlayerMoveUpFail() {
		System.out.println("Testing Player's Movement");
		Dungeon dungeon = new Dungeon(5,5);
		Player player = new Player(dungeon, 0,0); 
		Wall wall = new Wall(0,1);
		dungeon.addEntity(wall);
		dungeon.addEntity(player);
		player.moveUp();
		assert(player.getX() == 0);
		assert(player.getY() == 0);
		
	}
	// This will success
	@Test
	public void testPlayerMoveLeftSuccess() {
		Dungeon dungeon = new Dungeon(5,5);
		Player player = new Player(dungeon, 1,1);
		dungeon.addEntity(player);
		player.moveLeft();
		assert(player.getX() == 0);
		assert(player.getY() == 1);
	}
	
	// This won't change the Player's position
	// Since there are a boulder before a wall
	@Test
	public void testPlayerMoveDownFail() {
		Dungeon dungeon = new Dungeon(6, 6);
		Player player = new Player(dungeon, 1, 1);
		Boulder boulder = new Boulder(2,1);
		Wall wall = new Wall(3,1);
		dungeon.addEntity(wall);
		dungeon.addEntity(boulder);
		dungeon.addEntity(player);
		player.moveRight();
		//player.test();
		assert(player.getX() == 1);
		assert(player.getY() == 1);
		System.out.println("Passed");
	}
	
	// *******************************************
	// Test Player can collect treasure
	
	// The dungeon contains 2 treasure initially
	// and all picked up by player,
	// so total treasure is 0 eventually
	@Test
	public void testPlayerCollectTreasure1() {
		System.out.println("Testing Player collects treasure");
		Dungeon dungeon = new Dungeon(5,5);
		Player player = new Player(dungeon, 1,2);
		dungeon.addEntity(player);
		dungeon.addEntity(new Treasure(1,3));
		dungeon.addEntity(new Treasure(1,4));
		assert(dungeon.totalTreasure() == 2);
		player.moveDown();
		player.moveDown();
		assert(dungeon.totalTreasure() == 0);
	}
	
	@Test
	public void testPlayerCollectTreasure2() {
		Dungeon dungeon = new Dungeon(5,5);
		Player player = new Player(dungeon, 2,2);
		dungeon.addEntity(player);
		dungeon.addEntity(new Treasure(2,3));
		dungeon.addEntity(new Treasure(1,4));
		assert(dungeon.totalTreasure() == 2);
		player.moveDown();
		assert(dungeon.totalTreasure() == 1);
		System.out.println("Passed");
	}
	
	// *******************************************
	// Test Player can pick up a key
	
	// The Player has no key
	// So he/she picks up the key
	@Test
	public void testPlayerPickKeySuccess() {
		System.out.println("Testing Player picks up key");
		Dungeon dungeon = new Dungeon(5,5);
		Player player = new Player(dungeon, 2,2);
		assert(player.getKeyState() instanceof NoKeyState);
		dungeon.addEntity(player);
		dungeon.addEntity(new Key(2,4,1));
		player.moveDown();
		player.moveDown();
		assert(player.getKeyState() instanceof CarryKeyState);
	}
	
	// The Player carries a key with id 1
	// So he/she cannot pick up the key with id 2
	@Test
	public void testPlayerPickKeyFail() {
		Dungeon dungeon = new Dungeon(5,5);
		Player player = new Player(dungeon, 2,2);
		assert(player.getKeyState() instanceof NoKeyState);
		dungeon.addEntity(player);
		dungeon.addEntity(new Key(2,4,1));
		player.moveDown();
		player.moveDown();
		assert(player.getKeyState() instanceof CarryKeyState);
		assert(player.getKey().getId() == 1);
		// add a new key with id 2, but player still hold id1
		dungeon.addEntity(new Key(2,5,2));
		player.moveDown();
		assert(player.getKey().getId() == 1);
		System.out.println("Passed");
	}
	
	// *******************************************
	// Test Player opens the door with specific key
	@Test
	public void testOpenDoorSuccess() {
		Dungeon dungeon = new Dungeon(5,5);
		Player player = new Player(dungeon, 2,2);
		dungeon.addEntity(player);
		dungeon.addEntity(new Key(2,3,1));
		player.moveDown();
		ClosedDoor closedDoor = new ClosedDoor(2,4,1);
		dungeon.addEntity(closedDoor);
		assert(dungeon.getEntity().contains(closedDoor) == true);
		player.moveDown();
		assert(dungeon.getEntity().contains(closedDoor) == false);
	}
	
	// Player cannot open the door with a wrong key
	@Test
	public void testOpenDoorFail() {
		Dungeon dungeon = new Dungeon(5,5);
		Player player = new Player(dungeon, 2,2);
		dungeon.addEntity(player);
		dungeon.addEntity(new Key(2,3,1));
		player.moveDown();
		ClosedDoor closedDoor = new ClosedDoor(2,4,2);
		dungeon.addEntity(closedDoor);
		assert(dungeon.getEntity().contains(closedDoor) == true);
		player.moveDown();
		assert(dungeon.getEntity().contains(closedDoor) == true);
	}
	// *******************************************
	// Test Player can move boulders
	@Test
	public void testMoveBouldersSuccess() {
		Dungeon dungeon = new Dungeon(5,5);
		Player player = new Player(dungeon, 2,2);
		Boulder boulder = new Boulder(2,3);
		dungeon.addEntity(player);
		dungeon.addEntity(boulder);
		player.moveDown();
		assert(dungeon.getEntity().contains(boulder) == true);
		//for (Entity e:dungeon.getEntity()) if (e instanceof Boulder) assert(e.getX() == 2 && e.getY() == 4);
	}
	
	// Test Player cannot move boulders since there exists adjacent boulders
	@Test
	public void testMoveBouldersFail1() {
		Dungeon dungeon = new Dungeon(5,5);
		Player player = new Player(dungeon, 2,2);
		Boulder boulder1 = new Boulder(2,3);
		Boulder boulder2 = new Boulder(2,4);
 		dungeon.addEntity(player);
		dungeon.addEntity(boulder1);
		dungeon.addEntity(boulder2);
		player.moveDown();
		assert(dungeon.getEntity().contains(boulder1) == true);
	}
	
	// Test Player cannot move boulders since there exits wall
	@Test
	public void testMoveBouldersFail2() {
		Dungeon dungeon = new Dungeon(5,5);
		Player player = new Player(dungeon, 2,2);
		Boulder boulder = new Boulder(2,3);
		Wall wall = new Wall(2,4);
 		dungeon.addEntity(player);
		dungeon.addEntity(boulder);
		dungeon.addEntity(wall);
		player.moveDown();
		assert(dungeon.getEntity().contains(boulder) == true);
	}
	
	// Test Player cannot move boulders since there exits enemy
	@Test
	public void testMoveBouldersFail3() {
		Dungeon dungeon = new Dungeon(5,5);
		Player player = new Player(dungeon, 2,2);
		Boulder boulder = new Boulder(2,3);
		EnemyStrategy enemyStrategy = new OperationTowards();
		Enemy enemy = new Enemy(2,4,dungeon,enemyStrategy);
 		dungeon.addEntity(player);
		dungeon.addEntity(boulder);
		dungeon.addEntity(enemy);
		player.moveDown();
		assert(dungeon.getEntity().contains(boulder) == true);
	}
	
	// *******************************************
	// Test floor switch can be triggered while there's a boulder
	@Test
	public void testTriggerSuccess() {
		Dungeon dungeon = new Dungeon(5,5);
		Player player = new Player(dungeon, 2,2);
		Boulder boulder = new Boulder(2,3);
		Switch switc = new Switch(2,4);
		dungeon.addEntity(player);
		dungeon.addEntity(boulder);
		dungeon.addEntity(switc);
		player.moveDown();
		assert(player.triggerCheck(player.getX(), player.getY()+1) == 1);
	}
	
	// Test floor switch can not be triggered once the boulder removed
	@Test
	public void testTriggerFail() {
		Dungeon dungeon = new Dungeon(6,6);
		Player player = new Player(dungeon, 2,2);
		Boulder boulder = new Boulder(2,3);
		Switch switc = new Switch(2,4);
		dungeon.addEntity(player);
		dungeon.addEntity(boulder);
		dungeon.addEntity(switc);
		player.moveDown();
		assert(player.triggerCheck(player.getX(), player.getY()+1) == 1);
		player.moveDown();
		assert(player.triggerCheck(player.getX(), player.getY()) == 0);
	}
	
	// *******************************************
	// Test Player can pick up unlit bomb
	@Test
	public void testPickBombSuccess() {
		Dungeon dungeon = new Dungeon(6,6);
		Player player = new Player(dungeon, 2,2);
		Bomb bomb = new Bomb(3,2);
		dungeon.addEntity(player);
		dungeon.addEntity(bomb);
		assert(player.getCarryBomb() == false);
		player.moveRight();
		assert(player.getCarryBomb() == true);
	}
	
	// Player can carry only one bomb at a time
	@Test
	public void testPickBombFail() {
		Dungeon dungeon = new Dungeon(6,6);
		Player player = new Player(dungeon, 2,2);
		Bomb bomb = new Bomb(3,2);
		dungeon.addEntity(player);
		dungeon.addEntity(bomb);
		assert(player.getCarryBomb() == false);
		player.moveRight();
		assert(player.getCarryBomb() == true);
		Bomb bomb2 = new Bomb(4,2);
		dungeon.addEntity(bomb2);
		player.moveRight();
		// The bomb still exists since the player can pick it up
		for (Entity e:dungeon.getEntity()) if (e.getX() == player.getX() && e.getY() == player.getY() && e instanceof Player == false) assert(e instanceof Bomb);
	}
	
	// *******************************************
	// Test Player can drop a bomb and
	// the bomb will be exploded after 5 steps 
	
	// Destroy Player
	@Test
	public void testExplodedSuccess1() {
		Dungeon dungeon = new Dungeon(6,6);
		Player player = new Player(dungeon, 2,2);
		Bomb bomb = new Bomb(3,2);
		dungeon.addEntity(player);
		dungeon.addEntity(bomb);
		player.moveRight();
		player.dropBomb();
		player.moveRight();
		player.moveRight();
		player.moveRight();
		player.moveLeft();
		player.moveLeft();
		for (Entity e:dungeon.getEntity()) assert(e == null);
	}
	
	// Destroy Boulders
	@Test
	public void testExplodedSuccess2() {
		Dungeon dungeon = new Dungeon(6,6);
		Player player = new Player(dungeon, 2,2);
		Bomb bomb = new Bomb(3,2);
		Boulder boulder = new Boulder(4,2);
		dungeon.addEntity(player);
		dungeon.addEntity(bomb);
		dungeon.addEntity(boulder);
		player.moveRight();
		player.dropBomb();
		player.moveUp();
		player.moveUp();
		player.moveDown();
		player.moveDown();
		player.moveDown();
		for (Entity e:dungeon.getEntity()) assert(e == null);
	}
	
	// Destroy Enemy
	@Test
	public void testExplodedSuccess3() {
		Dungeon dungeon = new Dungeon(6,6);
		Player player = new Player(dungeon, 2,2);
		Bomb bomb = new Bomb(3,2);
		Boulder boulder = new Boulder(4,2);
		EnemyStrategy enemyStrategy = new OperationTowards();
		Enemy enemy = new Enemy(3,2, dungeon, enemyStrategy);
		dungeon.addEntity(player);
		dungeon.addEntity(bomb);
		dungeon.addEntity(boulder);
		player.moveRight();
		player.dropBomb();
		player.moveUp();
		player.moveUp();
		player.moveDown();
		player.moveDown();
		player.moveDown();
		for (Entity e:dungeon.getEntity()) assert(e.equals((Entity) enemy) == false);
	}
	
	// *******************************************
	// Test Player can kill the Enemy
	
	// Kill the enemy by sword
	@Test
	public void testKillbySword() {
		Dungeon dungeon = new Dungeon(1,5);
		Player player = new Player(dungeon, 1,1);
		EnemyStrategy enemyStrategy = new OperationTowards();
		Enemy enemy = new Enemy(1,5,dungeon, enemyStrategy);
		Sword sword = new Sword(1,2);
		dungeon.addEntity(player);
		dungeon.addEntity(sword);
		//dungeon.addEntity(enemy);
		//if (player == null) System.out.println("nnnnnnull");
		player.moveDown();
		player.moveDown();
		player.moveDown();
		//player.moveDown();
		//player.moveDown();
		for (Entity e:dungeon.getEntity()) assert(e.equals((Entity) enemy) == false);
	}
	
	// Kill the enemy by bomb
	@Test
	public void testKillbyBomb() {
		Dungeon dungeon = new Dungeon(6,6);
		Player player = new Player(dungeon, 3,1);
		Bomb bomb = new Bomb(4,1);
		Boulder boulder = new Boulder(5,1);
		EnemyStrategy enemyStrategy = new OperationTowards();
		Enemy enemy = new Enemy(4,1, dungeon,enemyStrategy);
		dungeon.addEntity(player);
		dungeon.addEntity(bomb);
		dungeon.addEntity(boulder);
		player.moveRight();
		player.dropBomb();
		player.moveUp();
		player.moveUp();
		player.moveDown();
		player.moveDown();
		player.moveDown();
		for (Entity e:dungeon.getEntity()) assert(e.equals((Entity) enemy) == false);
	}
	
	// Kill the enemy by potion
	@Test
	public void testKillbyPotion() {
		Dungeon dungeon = new Dungeon(6,6);
		Player player = new Player(dungeon, 3,1);
		Invincibility invinci = new Invincibility(3,2);
		EnemyStrategy enemyStrategy = new OperationTowards();
		Enemy enemy = new Enemy(4,3, dungeon, enemyStrategy);
		dungeon.addEntity(player);
		dungeon.addEntity(invinci);
		player.moveDown();
		player.moveRight();
		for (Entity e:dungeon.getEntity()) assert(e.equals((Entity) enemy) == false);
	}
	
	// *******************************************
	// Test Player can pick up sword
	@Test
	public void testPickSwordSuccess() {
		Dungeon dungeon = new Dungeon(6,6);
		Player player = new Player(dungeon, 3,1);
		Sword sword = new Sword(3,2);
		dungeon.addEntity(player);
		dungeon.addEntity(sword);
		assert(player.getSwordState() instanceof NoSwordState);
		player.moveDown();
		assert(player.getSwordState() instanceof CarrySwordState);
		assert(player.getAvalableHit() == 5);
	}
	
	// Test Player can only have one sword
	// the sword remains still since player carries one
	@Test
	public void testPickSwordFail() {
		Dungeon dungeon = new Dungeon(6,6);
		Player player = new Player(dungeon, 3,1);
		Sword sword = new Sword(3,2);
		dungeon.addEntity(player);
		dungeon.addEntity(sword);
		player.moveDown();
		Sword sword2 = new Sword(3,3);
		dungeon.addEntity(sword2);
		player.moveDown();
		for (Entity e:dungeon.getEntity()) if (e instanceof Sword) assert(e.getX() == player.getX() && e.getY() == player.getY());
	}
	
	// *******************************************
	// Test Player can pick up Invisible Potion
	@Test
	public void testPickPotionSuccess() {
		Dungeon dungeon = new Dungeon(6,6);
		Player player = new Player(dungeon, 3,1);
		Invincibility invinci = new Invincibility(3,2);
		dungeon.addEntity(player);
		dungeon.addEntity(invinci);
		assert(player.getInvinciState() instanceof NoInvinciState);
		player.moveDown();
		assert(player.getInvinciState() instanceof CarryInvinciState);
		assert(player.getAvalableInv() == 5);
	}
	
	// Test Player can only have one potion
	// the potion remains still since player carries one
	@Test
	public void testPickPotionFail() {
		Dungeon dungeon = new Dungeon(6,6);
		Player player = new Player(dungeon, 3,1);
		Invincibility invinci = new Invincibility(3,2);
		dungeon.addEntity(player);
		dungeon.addEntity(invinci);
		player.moveDown();
		Invincibility invinci2 = new Invincibility(3,3);
		dungeon.addEntity(invinci2);
		player.moveDown();
		assert(player.getAvalableInv() == 4);
		for (Entity e:dungeon.getEntity()) if (e instanceof Invincibility) assert(e.getX() == player.getX() && e.getY() == player.getY());
	}
	
	// *******************************************
	// Test Enemy can kill the player
	@Test
	public void testKillPlayer() {
		Dungeon dungeon = new Dungeon(6,6);
		EnemyStrategy enemyStrategy = new OperationTowards();
		Enemy enemy = new Enemy(4,2,dungeon, enemyStrategy);
		Player player = new Player(dungeon, 3,1);
		//Enemy enemy = new Enemy(4,2,dungeon);
		dungeon.addEntity(player);
		dungeon.addEntity(enemy);
		//player.moveRight();
		assert(player.isKilled1() == true);
	}
	
	// Test Player can push a boulder
	@Test
	public void testPushBoulder() {
		//Dungeon dungeon = new Dungeon(6,6);
		//Player player = new Player(dungeon, 3,1);
		Dungeon dungeon = new Dungeon(5,5);
		Player player = new Player(dungeon, 2,2);
		Boulder boulder = new Boulder(2,3);
		dungeon.addEntity(player);
		dungeon.addEntity(boulder);
		player.moveDown();
		for (Entity e:dungeon.getEntity()) if (e instanceof Boulder) assert(e.getX() == 2 && e.getY() ==4);
	}
	
	// *******************************************
	// Test exit goal success
	@Test
	public void testexitsuccess() {
		Dungeon dungeon = new Dungeon(3,3);
		Player player = new Player(dungeon, 1,1);
		Exit exit = new Exit (3,3);
		dungeon.addEntity(player);
		dungeon.addEntity(exit);
		player.moveRight();
		player.moveRight();
		player.moveDown();
		player.moveDown();
		assert(player.getGoal().isNeedexit() == false);
	}
	// Test exit goal fail
	@Test
	public void testexitfail() {
		Dungeon dungeon = new Dungeon(3,3);
		Player player = new Player(dungeon, 1,1);
		Exit exit = new Exit (3,3);
		dungeon.addEntity(player);
		dungeon.addEntity(exit);
		player.moveRight();
		player.moveDown();
		assert(player.getGoal().isNeedexit()) == false;
	}
	// *******************************************
	// Test collect treasure goal success
	@Test
	public void testcollectsuccess() {
		Dungeon dungeon = new Dungeon(9,9);
		Player player = new Player(dungeon, 5,5);
		Treasure Treasure1 = new Treasure(5,6);
		Treasure Treasure2 = new Treasure(5,7);
		dungeon.addEntity(player);
		dungeon.addEntity(Treasure1);
		dungeon.addEntity(Treasure2);
		player.moveDown();
		player.moveDown();
		player.moveDown();
		assert(player.getGoal().isNeedboulders() == false);
	}
	// Test collect treasure goal fail
	@Test
	public void testcollectfail() {
		Dungeon dungeon = new Dungeon(9,9);
		Player player = new Player(dungeon, 5,5);
		Treasure Treasure1 = new Treasure(5,6);
		Treasure Treasure2 = new Treasure(5,7);
		dungeon.addEntity(player);
		dungeon.addEntity(Treasure1);
		dungeon.addEntity(Treasure2);
		player.moveUp();
		player.moveUp();
		player.moveUp();
		assert(player.getGoal().isNeedtreasure() == false);
	}
	// *******************************************
	// Test boulder goal success
	@Test
	public void testbouldersuccess() {
		Dungeon dungeon = new Dungeon(9,9);
		Player player = new Player(dungeon, 5,5);
		Switch switch1 = new Switch(5,3);
		Switch switch2 = new Switch(5,7);
		Boulder boulder1 = new Boulder(5,4);
		Boulder boulder2 = new Boulder(5,6);
		dungeon.addEntity(player);
		dungeon.addEntity(switch1);
		dungeon.addEntity(switch2);
		dungeon.addEntity(boulder1);
		dungeon.addEntity(boulder2);
		player.moveUp();
		player.moveDown();
		player.moveDown();
		assert(player.getGoal().isNeedboulders() == false);
	}
	// Test boulder goal fail
	@Test
	public void testboulderfail() {
		Dungeon dungeon = new Dungeon(9,9);
		Player player = new Player(dungeon, 5,5);
		Switch switch1 = new Switch(5,3);
		Switch switch2 = new Switch(1,7);
		Boulder boulder1 = new Boulder(2,7);
		Boulder boulder2 = new Boulder(3,6);
		dungeon.addEntity(player);
		dungeon.addEntity(switch1);
		dungeon.addEntity(switch2);
		dungeon.addEntity(boulder1);
		dungeon.addEntity(boulder2);
		player.moveDown();
		player.moveDown();
		player.moveDown();
		assert(player.getGoal().isNeedboulders() == false);
	}
	
	// *******************************************
	// Test extensions
	
	// Test collect meat
	@Test
	public void testPickMeat() {
		Dungeon dungeon = new Dungeon(6,6);
		Player player = new Player(dungeon, 3,1);
		Meat meat = new Meat(3,2);
		dungeon.addEntity(player);
		dungeon.addEntity(meat);
		assert(player.getMeat() == 0);
		player.moveDown();
		assert(player.getMeat() == 1);
	}
	
	// Test feed dog success
	@Test
	public void testFeedDogSuccess() {
		Dungeon dungeon = new Dungeon(6,6);
		Player player = new Player(dungeon, 3,1);
		Meat meat = new Meat(3,2);
		Dog dog = new Dog(3,3,dungeon);
		dungeon.addEntity(player);
		dungeon.addEntity(meat);
		dungeon.addEntity(dog);
		player.moveDown();
		player.moveDown();
		player.moveDown();
		assert(dungeon.getDog().isPickedup() == true);
	}
	
	// Test feed dog fail
	@Test
	public void testFeedDogFail() {
		Dungeon dungeon = new Dungeon(6,6);
		Player player = new Player(dungeon, 3,1);
		Dog dog = new Dog(3,2,dungeon);
		dungeon.addEntity(player);
		dungeon.addEntity(dog);
		player.moveDown();
		player.moveDown();
		assert(dungeon.getDog().isPickedup() == false);
		assert(player.isKilled() == true);
	}
	
	// Test Time Freeze
	@Test
	public void testTimeFreezeDog() {
		Dungeon dungeon = new Dungeon(6,6);
		Player player = new Player(dungeon, 3,1);
		TimeF timef = new TimeF(3,2);
		Dog dog = new Dog(4,2,dungeon);
		dungeon.addEntity(player);
		dungeon.addEntity(timef);
		dungeon.addEntity(dog);
		player.moveDown();
		player.moveDown();
		assert(player.getX() == 3);
		assert(player.getY() == 3);
		assert(dog.getX() == 4);
		assert(dog.getY() == 2);
	}
	
	// Test teleport
	@Test
	public void testTeleport() {
		Dungeon dungeon = new Dungeon(6,6);
		Player player = new Player(dungeon, 3,1);
		Teleport teleport1 = new Teleport(3,2);
		Teleport teleport2 = new Teleport(3,4);
		dungeon.addEntity(player);
		dungeon.addEntity(teleport1);
		dungeon.addEntity(teleport2);
		player.moveDown();
		assert(player.getX() == 3);
		assert(player.getY() == 4);
	}
}