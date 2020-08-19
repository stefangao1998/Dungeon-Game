package unsw.dungeon;

public class ClosedDoor extends Entity {
	
	private int id;

	public ClosedDoor(int x, int y, int id) {
        super(x, y);
        this.id = id;
    }

	public int getId() {
		return this.id;
	}
	
	
}
