package unsw.dungeon;

public class InvincibleObserver extends Observer{
	private Enemy enemy;
	public InvincibleObserver (Subject subject) {
		this.subject = subject;
		this.subject.attach(this);
	}
	
	@Override
	public void update() {
		enemy.setObState("invincible");
	}
	
}
