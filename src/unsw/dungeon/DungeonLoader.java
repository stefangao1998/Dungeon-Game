package unsw.dungeon;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 * Loads a dungeon from a .json file.
 *
 * By extending this class, a subclass can hook into entity creation. This is
 * useful for creating UI elements with corresponding entities.
 *
 * @author Robert Clifton-Everest
 *
 */
public abstract class DungeonLoader {

    private JSONObject json;
    private int flag=0;
    
    public DungeonLoader(String filename) throws FileNotFoundException {
        json = new JSONObject(new JSONTokener(new FileReader("dungeons/" + filename)));
    }

    /**
     * Parses the JSON to create a dungeon.
     * @return
     */
    public Dungeon load() {
        int width = json.getInt("width");
        int height = json.getInt("height");

        Dungeon dungeon = new Dungeon(width, height);
        JSONArray jsonEntities = json.getJSONArray("entities");
        
        for (int i = 0; i < jsonEntities.length(); i++) {
            loadEntity(dungeon, jsonEntities.getJSONObject(i));
        }
        JSONObject jsonGoalCondition = json.getJSONObject("goal-condition");
        loadGoalConditions(dungeon, jsonGoalCondition);
        
        
    	System.out.println(dungeon.getPlayer().getGoal()); 
    	for (Goal subgoal1 : dungeon.getPlayer().getGoal().getSubgoals()) {
            System.out.println("    "+subgoal1);
            for (Goal subgoal2 : subgoal1.getSubgoals()) {
                System.out.println("        "+subgoal2);
                for (Goal subgoal3 : subgoal2.getSubgoals()) {
                    System.out.println("            "+subgoal3);
                    
                }
            }
         }
        return dungeon;
    }
    
    private boolean NotAndOr (String s) {
    	if (s.equals("exit") || s.equals("treasure") || s.equals("boulders") || s.equals("enemies")) 
			return true;
    	return false;
    }
    
    
    
    private void loadGoals (Dungeon dungeon, JSONObject json) {
    	String g = json.getString("goal");
    	//System.out.println(g);
		
		if (flag == 0 && NotAndOr(g)) {//0.1
			//System.out.println(g);
			//System.out.println(flag);
			Goal goal0 = new Goal (dungeon, g, false);
			dungeon.getPlayer().setGoal(goal0);
			flag++;
		} else if (flag == 0) {//0.2
			if (NotAndOr("enemeis")) {
				Goal goal0 = new Goal (dungeon, g, false);
				if (flag >= 1 && NotAndOr(g)) {//0.2.1
					Goal goal1 = new Goal (dungeon, g, false);
					if (flag >= 2 && NotAndOr(g)) {//0.2.1.1
						Goal goal2 = new Goal (dungeon, g, false);
						goal0.add(goal2);
						flag++;
					} else if (flag >= 2){//0.2.1.2
						Goal goal2 = new Goal (dungeon, g, false);
						if (flag >= 3 && NotAndOr(g)) {//0.2.1.2.1
							Goal goal3 = new Goal (dungeon, g, false);
							if (flag >= 4 && NotAndOr(g)) {//0.2.1.2.1.1
								Goal goal4 = new Goal (dungeon, g, false);
								goal2.add(goal4);
								flag++;
							} else if (flag >= 4) {//0.2.1.2.1.2
								System.out.println(flag);
								Goal goal4 = new Goal (dungeon, g, false);
								flag++;
							}
							goal2.add(goal3);
							flag++;
						} else if (flag >= 3) {//0.2.1.2.2
							Goal goal3 = new Goal (dungeon, g, false);
							flag++;
						}
						goal1.add(goal2);
						flag++;
					}
					goal0.add(goal1);
					flag++;
				} else if (flag >= 1) {//0.2.2
					Goal goal1 = new Goal (dungeon, g, false);
					if (flag >= 2 && NotAndOr(g)) {//0.2.2.1
						Goal goal2 = new Goal (dungeon, g, false);
						if (flag >= 3 && NotAndOr(g)) {//0.2.2.1.1
							Goal goal3 = new Goal (dungeon, g, false);
							goal1.add(goal3);
							flag++;
						} else if (flag >= 3) {//0.2.2.1.2
							Goal goal3 = new Goal (dungeon, g, false);
							goal2.add(goal3);
							flag++;
						}
						goal1.add(goal2);
						flag++;
					} else if (flag >= 2){//0.2.2.2
						Goal goal2 = new Goal (dungeon, g, false);
						if (flag >= 3 && NotAndOr(g)) {//0.2.2.2.1
							Goal goal3 = new Goal (dungeon, g, false);
							if (flag >= 4 && NotAndOr(g)) {//0.2.2.2.1.1
								Goal goal4 = new Goal (dungeon, g, false);
								goal2.add(goal4);
								flag++;
							} else if (flag >= 4) {//0.2.2.2.1.2
								Goal goal4 = new Goal (dungeon, g, false);
								flag++;
							}
							goal2.add(goal3);
							flag++;
						} else if (flag >= 3) {//0.2.2.2.2
							Goal goal3 = new Goal (dungeon, g, false);
							flag++;
						}
						goal1.add(goal2);
						flag++;
					}
					goal0.add(goal1);
					flag++;
				}
				dungeon.getPlayer().getGoal().add(goal0);
				flag++;
			} else {
				//System.out.println(g);
				if (g.equals("AND")) {
				/**Goal goal0 = new Goal (dungeon, "AND", false);
				Goal goal1 = new Goal (dungeon, "exit", false);
				Goal goal2 = new Goal (dungeon, "OR", false);
				Goal goal3 = new Goal (dungeon, "enemies", false);
				Goal goal4 = new Goal (dungeon, "treasure", false);
				dungeon.getPlayer().setGoal(goal0);
				goal0.add(goal1);
				goal0.add(goal2);
				goal2.add(goal3);
				goal2.add(goal4);*/
				loadgoalAND(dungeon);
				//System.out.println("=====");
				} else if (g.equals("OR")) {loadgoalOR(dungeon);}
				
			}
		}
	
    	
    	switch (g) {
        case "AND":
        	//System.out.println("aaaaaa\n");
        	
        	//Goal AND = new Goal (dungeon, "AND", false);
        	//System.out.println(flag);
        	//System.out.println(AND.getName());
        	//flag++;
            break;
        case "OR":
        	//System.out.println("bbbbbb\n");
        	//Goal OR = new Goal (dungeon, "OR", false);
        	//System.out.println(flag);
        	//System.out.println(OR.getName());
        	//flag++;
            break;
        case "exit":
        	//System.out.println("11111\n");
        	//Goal exit = new Goal (dungeon, "exit", false);
        	//System.out.println(flag);
        	//System.out.println(exit.getName());
        	dungeon.getPlayer().getGoal().setNeedexit(true);
        	//flag++;
            break;
        case "enemies":
        	//System.out.println("2222\n");
        	//Goal enemies = new Goal (dungeon, "enemies", false);
        	//System.out.println(flag);
        	//System.out.println(enemies.getName());
        	dungeon.getPlayer().getGoal().setNeedenemies(true);
        	//flag++;
            break;
        case "boulders":
        	//System.out.println("33333\n");
        	//Goal boulders = new Goal (dungeon, "boulders", false);
        	//System.out.println(flag);
        	//System.out.println(boulders.getName());
        	dungeon.getPlayer().getGoal().setNeedboulders(true);
        	//flag++;
            break;
        case "treasure":
        	//System.out.println("444444\n");
        	//Goal treasure = new Goal (dungeon, "treasure", false);
        	//System.out.println(flag);
        	//System.out.println(treasure.getName());
        	///System.out.println("======");
        	//System.out.println(dungeon.getPlayer().getGoal().getName());
        	dungeon.getPlayer().getGoal().setNeedtreasure(true);
        	//flag++;
            break;
    	}
    }
    
    
    private void loadSubGoals(Dungeon dungeon, JSONArray json) {
    	//for (int i = 0; i < json.length(); i++) {
    	//	System.out.println(json.getJSONObject(i).get("goal"));
        //}
    	JSONObject goal = json.getJSONObject(0);
    	loadGoalConditions(dungeon, goal);
    	JSONObject subgoal = json.getJSONObject(1);
    	loadGoalConditions(dungeon, subgoal);
    }
    
    private void loadGoalConditions(Dungeon dungeon, JSONObject json) {
    	loadGoals(dungeon, json);
    	if (json.has("subgoals")) {
	    	JSONArray subgoals = json.getJSONArray("subgoals");
	    	loadSubGoals(dungeon, subgoals);
    	}
    }
    public void loadgoalAND (Dungeon dungeon) {
    	Goal goal0 = new Goal (dungeon, "AND", false);
		Goal goal1 = new Goal (dungeon, "exit", false);
		Goal goal2 = new Goal (dungeon, "OR", false);
		Goal goal3 = new Goal (dungeon, "enemies", false);
		Goal goal4 = new Goal (dungeon, "treasure", false);
		dungeon.getPlayer().setGoal(goal0);
		goal0.add(goal1);
		goal0.add(goal2);
		goal2.add(goal3);
		goal2.add(goal4);
		flag++;
    }
    
    public void loadgoalOR(Dungeon dungeon) {
    	Goal goal0 = new Goal (dungeon, "OR", false);
		Goal goal1 = new Goal (dungeon, "AND", false);
		Goal goal2 = new Goal (dungeon, "AND", false);
		Goal goal3 = new Goal (dungeon, "enemies", false);
		Goal goal4 = new Goal (dungeon, "treasure", false);
		Goal goal5 = new Goal (dungeon, "boulders", false);
		Goal goal6 = new Goal (dungeon, "exit", false);
		dungeon.getPlayer().setGoal(goal0);
		goal0.add(goal1);
		goal0.add(goal2);
		goal1.add(goal3);
		goal1.add(goal4);
		goal2.add(goal5);
		goal2.add(goal6);
		flag++;
    }
    
    public List<String> getValuesForGivenKey(String jsonArrayStr, String key) {
        JSONArray jsonArray = new JSONArray(jsonArrayStr);
        return IntStream.range(0, jsonArray.length())
          .mapToObj(index -> ((JSONObject)jsonArray.get(index)).optString(key))
          .collect(Collectors.toList());
    }
    
    private void loadEntity(Dungeon dungeon, JSONObject json) {
        String type = json.getString("type");
        int x = json.getInt("x");
        int y = json.getInt("y");
        int id = 0;
        
        Entity entity = null;
        switch (type) {
        case "player":
            Player player = new Player(dungeon, x, y);
            dungeon.setPlayer(player);
            onLoad(player);
            entity = player;
            break;
        case "wall":
            Wall wall = new Wall(x, y);
            onLoad(wall);
            entity = wall;
            break;
        // TODO Handle other possible entities
        case "exit":
        	Exit exit = new Exit(x, y);
        	onLoad(exit);
        	entity = exit;
        	break;
        case "treasure":
        	Treasure treasure = new Treasure(x, y);
        	onLoad(treasure);
        	entity = treasure;
        	break;
        case "door":
        	id = json.getInt("id");
        	OpenDoor openDoor = new OpenDoor(x, y);
        	onLoad(openDoor);
        	ClosedDoor closedDoor = new ClosedDoor(x, y, id);
        	onLoad(closedDoor);
        	
        	entity = closedDoor;
        	break;
        case "key":
        	id = json.getInt("id");
        	Key key = new Key(x, y, id);
        	//System.out.println("the key id is "+key.getId());
        	onLoad(key);
        	entity = key;
        	break;
        case "boulder":
        	Boulder boulder = new Boulder(x, y);
        	onLoad(boulder);
        	entity = boulder;
            break;
        case "switch":
        	Switch switch1 = new Switch(x, y);
        	onLoad(switch1);
        	entity = switch1;
        	break;
        case "bomb":
        	Bomb bomb = new Bomb(x, y);
        	onLoad(bomb);
        	StageOne stOne = new StageOne(dungeon.getWidth()-1, dungeon.getHeight()-1);
        	onLoad(stOne);dungeon.addEntity(stOne);
        	StageTwo stTwo = new StageTwo(dungeon.getWidth()-1, dungeon.getHeight()-1);
        	onLoad(stTwo);dungeon.addEntity(stTwo);
        	StageThree stThree = new StageThree(dungeon.getWidth()-1, dungeon.getHeight()-1);
        	onLoad(stThree);dungeon.addEntity(stThree);
        	StageFour stFour = new StageFour(dungeon.getWidth()-1, dungeon.getHeight()-1);
        	onLoad(stFour);dungeon.addEntity(stFour);
        	entity = bomb;
        	for (int i = 0; i < 5; i++) {
        		StageFive st = new StageFive(dungeon.getWidth()-1, dungeon.getHeight()-1);
        		onLoad(st); dungeon.addEntity(st);
        	}
        	break;
        case "enemy":
        	EnemyStrategy enemyStrategy = null;
			Enemy enemy = new Enemy(x, y, dungeon, enemyStrategy);
        	onLoad(enemy);
        	entity = enemy;
        	break;
        case "sword":
        	Sword sword = new Sword(x, y);
        	onLoad(sword);
        	entity = sword;
        	break;
        case "invincibility":
        	Invincibility invincibility = new Invincibility(x, y);
        	onLoad(invincibility);
        	entity = invincibility;
        	break;
        case "meat":
        	Meat meat = new Meat(x, y);
        	onLoad(meat);
        	entity = meat;
        	break;
        case "dog":
        	Dog dog = new Dog(x, y, dungeon);
        	onLoad(dog);
        	entity = dog;
        	break;
        case "teleport":
        	Teleport teleport = new Teleport(x, y);
        	onLoad(teleport);
        	entity = teleport;
        	break;
        case "time":
        	TimeF timeF = new TimeF(x, y);
        	onLoad(timeF);
        	entity = timeF;
        	break;
        }
        dungeon.addEntity(entity);
    }

    public abstract void onLoad(Entity player);

    public abstract void onLoad(Wall wall);

    // TODO Create additional abstract methods for the other entities
	public abstract void onLoad(Exit exit);
	public abstract void onLoad(Treasure treasure);
	public abstract void onLoad(ClosedDoor closedDoor);
	public abstract void onLoad(OpenDoor openDoor);
	public abstract void onLoad(Key key);
	public abstract void onLoad(Boulder boulder);
	public abstract void onLoad(Switch switch1);
	public abstract void onLoad(Bomb bomb);
	public abstract void onLoad(Enemy enemy);
	public abstract void onLoad(Sword sword);
	public abstract void onLoad(Invincibility invincibility);
	public abstract void onLoad(Meat meat);
	public abstract void onLoad(Dog dog);
	public abstract void onLoad(Teleport teleport);
	public abstract void onLoad(StageOne stOne);
	public abstract void onLoad(StageTwo stTwo);
	public abstract void onLoad(StageThree stThree);
	public abstract void onLoad(StageFour stFour);
	public abstract void onLoad(StageFive stFive);
	public abstract void onLoad(TimeF timeF);
}
