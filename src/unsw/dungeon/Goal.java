/**package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

public class Goal {
    
    private Dungeon dungeon;
    private String name;
    private boolean state;
    private List<Goal> subgoals;


    public Goal(Dungeon dungeon, String name, boolean state) {
        super();
        this.dungeon = dungeon;
        this.name = name;
        this.state = state;
        subgoals = new ArrayList<Goal>();
    }

    
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }
    
    public void add(Goal g) {
        subgoals.add(g);
    }
         
    public void remove(Goal g) {
        subgoals.remove(g);
    }
    
    public List<Goal> getSubgoals(){
        return subgoals;
    }
    
    public String toString(){
        return ("Goal :[ Name : "+ name 
        +", State : "+ state +" ]");
    }  
    

	public boolean isNeedenemies() {
		return needenemies;
	}
	public void setNeedenemies(boolean needenemies) {
		this.needenemies = needenemies;
	}
	public boolean isNeedtreasure() {
		return needtreasure;
	}
	public void setNeedtreasure(boolean needtreasure) {
		this.needtreasure = needtreasure;
	}
	public boolean isNeedexit() {
		return needexit;
	}
	public void setNeedexit(boolean needexit) {
		this.needexit = needexit;
	}
	public boolean isNeedboulders() {
		return needboulders;
	}
	public void setNeedboulders(boolean needboulders) {
		this.needboulders = needboulders;
	}
	private boolean needenemies=false;
	private boolean needtreasure=false;
	
    public void printGoal() {
    	//System.out.println(dungeon.getPlayer().getGoal()); 
        for (Goal subgoal1 : dungeon.getPlayer().getGoal().getSubgoals()) {
           System.out.println(subgoal1);
           for (Goal subgoal2 : subgoal1.getSubgoals()) {
               System.out.println("    "+subgoal2);
               for (Goal subgoal3 : subgoal2.getSubgoals()) {
                   System.out.println("        "+subgoal3);
                   
               }
           }
        }
    }
    
    private boolean needexit=false;

    
    public void updateGoal() {
    	for (Goal subgoal1 : dungeon.getPlayer().getGoal().getSubgoals()) {
	            for (Goal subgoal2 : subgoal1.getSubgoals()) {  
	            	int ANDflag=0;
	            	int ORflag=1;
	                for (Goal subgoal3 : subgoal2.getSubgoals()) {
	                	if (subgoal2.getName().equals("AND")) {
	                		if (!subgoal3.isState()) {ANDflag++;}
	                	} else if (subgoal2.getName().equals("OR")) {
	                		if (subgoal3.isState()) {ORflag--;}
	                	}
	                }
	                if ((ANDflag <= 0 && subgoal2.getName().equals("AND"))||(subgoal2.getName().equals("OR") && ORflag<=0)) {subgoal2.setState(true);}
	            }
    	}
    	
    	for (Goal subgoal1 : dungeon.getPlayer().getGoal().getSubgoals()) {
    		int ANDflag=0;
        	int ORflag=1;
            for (Goal subgoal2 : subgoal1.getSubgoals()) { 
            	
            	if (subgoal1.getName().equals("AND")) {
            		if (!subgoal2.isState()) {ANDflag++;}
            	} else if (subgoal1.getName().equals("OR")) {
            		if (subgoal2.isState()) {ORflag--;}
            	}
            	//System.out.println(subgoal1.getName());
            	//System.out.println(ANDflag);
            	if ((ANDflag <= 0 && subgoal1.getName().equals("AND"))||(subgoal1.getName().equals("OR") && ORflag<=0)) {
            			subgoal1.setState(true);
            			dungeon.getPlayer().getGoal().setState(true);
            		}
            }
    	}
    	
    	//for (Goal subgoal1 : dungeon.getPlayer().getGoal().getSubgoals()) {
        //	if (subgoal1.isState()) {dungeon.getPlayer().getGoal().setState(true);}
    	//}
    	
    	
    	if (dungeon.getPlayer().getGoal().isState()) {System.out.println("Congratulation! You've completed the game!!");}
    	
    }

	private boolean needboulders=false;
    
    
}*/

package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

public class Goal {
    
    private Dungeon dungeon;
    private String name;
    private boolean state;
    private List<Goal> subgoals;


    public Goal(Dungeon dungeon, String name, boolean state) {
        super();
        this.dungeon = dungeon;
        this.name = name;
        this.state = state;
        subgoals = new ArrayList<Goal>();
    }

    
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }
    
    public void add(Goal g) {
        subgoals.add(g);
    }
         
    public void remove(Goal g) {
        subgoals.remove(g);
    }
    
    public List<Goal> getSubgoals(){
        return subgoals;
    }
    
    public String toString(){
        return ("Goal :[ Name : "+ name 
        +", State : "+ state +" ]");
    }  
    

    public boolean isNeedenemies() {
        return needenemies;
    }
    public void setNeedenemies(boolean needenemies) {
        this.needenemies = needenemies;
    }
    public boolean isNeedtreasure() {
        return needtreasure;
    }
    public void setNeedtreasure(boolean needtreasure) {
        this.needtreasure = needtreasure;
    }
    public boolean isNeedexit() {
        return needexit;
    }
    public void setNeedexit(boolean needexit) {
        this.needexit = needexit;
    }
    public boolean isNeedboulders() {
        return needboulders;
    }
    public void setNeedboulders(boolean needboulders) {
        this.needboulders = needboulders;
    }
    private boolean needenemies=false;
    private boolean needtreasure=false;
    private boolean needexit=false;
    private boolean needboulders=false;
    
    public void printGoal() {
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
    }
    
    
    public void updateGoal() {
    	int flag=0;
        for (Goal subgoal1 : dungeon.getPlayer().getGoal().getSubgoals()) {
                for (Goal subgoal2 : subgoal1.getSubgoals()) {  
                    //int flag=0;
                    for (Goal subgoal3 : subgoal2.getSubgoals()) {
                        if (subgoal2.getName().equals("AND")) {
                            if (subgoal3.isState()) {flag++;}
                        } else if (subgoal2.getName().equals("OR")) {
                            if (subgoal3.isState()) {subgoal2.setState(true);}
                        }
                    }
                    //System.out.println(subgoal2);
                    if (flag == 2 && (subgoal2.getName().equals("AND"))) {
                    	subgoal2.setState(true);
                    	//System.out.println(subgoal2);
                	}
                }
        }
        //flag=0;
        for (Goal subgoal1 : dungeon.getPlayer().getGoal().getSubgoals()) {
            flag=0;
            for (Goal subgoal2 : subgoal1.getSubgoals()) {  
                if (subgoal1.getName().equals("AND")) {
                    if (subgoal2.isState()) {flag++;}
                } else if (subgoal1.getName().equals("OR")) {
                    if (subgoal2.isState()) {subgoal1.setState(true);}
                }
                if (flag == 2 && ((subgoal1.getName().equals("AND")))) {
                	subgoal1.setState(true);
                	//System.out.println(dungeon.getPlayer().getGoal());
            	}
            }
        }
        flag=0;
        for (Goal subgoal1 : dungeon.getPlayer().getGoal().getSubgoals()) {
        	//System.out.println(dungeon.getPlayer().getGoal());
        	//System.out.println(subgoal1);
            //int flag=0;     
            if (dungeon.getPlayer().getGoal().getName().equals("AND")) {
                if (subgoal1.isState()) {flag++;}
            } else if (dungeon.getPlayer().getGoal().getName().equals("OR")) {
                if (subgoal1.isState()) {dungeon.getPlayer().getGoal().setState(true);}
            }
            //System.out.println(flag);
            if (flag == 2 && ((dungeon.getPlayer().getGoal().getName().equals("AND")))) {
            	dungeon.getPlayer().getGoal().setState(true);
            	//System.out.println(dungeon.getPlayer().getGoal());
        	}
        }
        
        if (dungeon.getPlayer().getGoal().isState()) {
        	System.out.println("Congratulation! You've completed the game!!");
    	}
        
    }

    
    
    
}

