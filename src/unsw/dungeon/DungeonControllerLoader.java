package unsw.dungeon;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

/**
 * A DungeonLoader that also creates the necessary ImageViews for the UI,
 * connects them via listeners to the model, and creates a controller.
 * @author Robert Clifton-Everest
 *
 */
public class DungeonControllerLoader extends DungeonLoader {

    private List<ImageView> entities;

    //Images
    private Image playerImage;
    private Image wallImage;
    //Other images
    private Image exitImage;
    private Image treasureImage;
    private Image closedDoorImage;
    private Image openDoorImage;
    private Image keyImage;
    private Image boulderImage;
    private Image switchImage;
    private Image bombImage;
    private Image enemyImage;
    private Image stage1Image;
    private Image stage2Image;
    private Image stage3Image;
    private Image stage4Image;
    private Image stage5Image;
    private Image swordImage;
    private Image invincibilityImage;
    private Image meatImage;
    private Image dogImage;
    private Image teleportImage;
    private Image timeFImage;
    
    public DungeonControllerLoader(String filename)
            throws FileNotFoundException {
        super(filename);
        entities = new ArrayList<>();
        playerImage = new Image("/human_new.png");
        wallImage = new Image("/brick_brown_0.png");
        //Other images
        exitImage = new Image("/exit.png");
        treasureImage = new Image("/gold_pile.png");
        closedDoorImage = new Image("/closed_door.png");
        openDoorImage = new Image("/open_door.png");
        keyImage = new Image("/key.png");
        boulderImage = new Image("/boulder.png");
        switchImage = new Image("/pressure_plate.png");
        bombImage = new Image("/bomb_unlit.png");
        enemyImage = new Image("/deep_elf_master_archer.png");
        stage1Image = new Image("/bomb_unlit.png");
        stage2Image = new Image("/bomb_lit_1.png");
        stage3Image = new Image("/bomb_lit_2.png");
        stage4Image = new Image("/bomb_lit_3.png");
        stage5Image = new Image("/bomb_lit_4.png");
        swordImage = new Image("/greatsword_1_new.png");
        invincibilityImage = new Image("/brilliant_blue_new.png");
        meatImage = new Image("/meat.png");
        dogImage = new Image("/hound.png");
        teleportImage = new Image("/teleport.png");
        timeFImage = new Image("/time_freeze.png");
        
    }

    @Override
    public void onLoad(Entity player) {
        ImageView view = new ImageView(playerImage);
        addEntity(player, view);
    }

    @Override
    public void onLoad(Wall wall) {
        ImageView view = new ImageView(wallImage);
        addEntity(wall, view);
    }

    @Override
    public void onLoad(Exit exit) {
        ImageView view = new ImageView(exitImage);
        addEntity(exit, view);
    }
    
    @Override
    public void onLoad(Treasure treasure) {
        ImageView view = new ImageView(treasureImage);
        addEntity(treasure, view);
    }
    
    @Override
    public void onLoad(ClosedDoor closedDoor) {
    	ImageView view = new ImageView(closedDoorImage);
    	addEntity(closedDoor, view);
    }
    
    @Override
    public void onLoad(OpenDoor openDoor) {
    	ImageView view = new ImageView(openDoorImage);
    	addEntity(openDoor, view);
    }
    
    @Override
    public void onLoad(Key key) {
    	ImageView view = new ImageView(keyImage);
    	addEntity(key, view);
    }
    
    @Override
    public void onLoad(Boulder boulder) {
    	ImageView view = new ImageView(boulderImage);
    	addEntity(boulder, view);
    }
    
    @Override
    public void onLoad(Switch switch1) {
    	ImageView view = new ImageView(switchImage);
    	addEntity(switch1, view);
    }
    
    @Override
    public void onLoad(Bomb bomb) {
    	ImageView view = new ImageView(bombImage);
    	addEntity(bomb, view);
    }
    
    @Override
    public void onLoad(Enemy enemy) {
    	ImageView view = new ImageView(enemyImage);
    	addEntity(enemy, view);
    }
    
    @Override
    public void onLoad(StageOne stOne) {
    	ImageView view = new ImageView(stage1Image);
    	addEntity(stOne, view);
    }
    
    @Override
    public void onLoad(StageTwo stTwo) {
    	ImageView view = new ImageView(stage2Image);
    	addEntity(stTwo, view);
    }
    
    @Override
    public void onLoad(StageThree stThree) {
    	ImageView view = new ImageView(stage3Image);
    	addEntity(stThree, view);
    }
    
    @Override
    public void onLoad(StageFour stFour) {
    	ImageView view = new ImageView(stage4Image);
    	addEntity(stFour, view);
    }
    
    @Override
    public void onLoad(StageFive stFive) {
    	ImageView view = new ImageView(stage5Image);
    	addEntity(stFive, view);
    }
    
    @Override
    public void onLoad(Sword sword) {
    	ImageView view = new ImageView(swordImage);
    	addEntity(sword, view);
    }
    
    @Override
    public void onLoad(Invincibility invincibility) {
    	ImageView view = new ImageView(invincibilityImage);
    	addEntity(invincibility, view);
    }
    
    @Override
    public void onLoad(Meat meat) {
    	ImageView view = new ImageView(meatImage);
    	addEntity(meat, view);
    }
    
    @Override
    public void onLoad(Dog dog) {
    	ImageView view = new ImageView(dogImage);
    	addEntity(dog, view);
    }
    
    @Override
    public void onLoad(Teleport teleport) {
    	ImageView view = new ImageView(teleportImage);
    	addEntity(teleport, view);
    }
    
    @Override
    public void onLoad(TimeF timeF) {
    	ImageView view = new ImageView(timeFImage);
    	addEntity(timeF, view);
    }
    
    private void addEntity(Entity entity, ImageView view) {
        trackPosition(entity, view);
        entities.add(view);
    }

    /**
     * Set a node in a GridPane to have its position track the position of an
     * entity in the dungeon.
     *
     * By connecting the model with the view in this way, the model requires no
     * knowledge of the view and changes to the position of entities in the
     * model will automatically be reflected in the view.
     * @param entity
     * @param node
     */
    private void trackPosition(Entity entity, Node node) {
        GridPane.setColumnIndex(node, entity.getX());
        GridPane.setRowIndex(node, entity.getY());
        entity.x().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable,
                    Number oldValue, Number newValue) {
                GridPane.setColumnIndex(node, newValue.intValue());
            }
        });
        entity.y().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable,
                    Number oldValue, Number newValue) {
                GridPane.setRowIndex(node, newValue.intValue());
            }
        });
    }

    /**
     * Create a controller that can be attached to the DungeonView with all the
     * loaded entities.
     * @return
     * @throws FileNotFoundException
     */
    public DungeonController loadController() throws FileNotFoundException {
        return new DungeonController(load(), entities);
    }


}
