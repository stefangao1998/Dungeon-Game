package unsw.dungeon;

public class SwordObserver extends Observer{
	private Enemy enemy;
	/**public SwordObserver (){
		this.enemy = enemy;
	}*/
	public SwordObserver (Subject subject) {
		this.subject = subject;
		this.subject.attach(this);
	}
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
		enemy.setObState("sword");
	}
	
}
