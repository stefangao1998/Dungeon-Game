package unsw.dungeon;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.RandomAccessFile;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DungeonApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setTitle("Dungeon");
		
        //DungeonControllerLoader dungeonLoader = new DungeonControllerLoader("maze.json");
        //DungeonControllerLoader dungeonLoader = new DungeonControllerLoader("boulders.json");
        //DungeonControllerLoader dungeonLoader = new DungeonControllerLoader("advanced.json");
        DungeonControllerLoader dungeonLoader = new DungeonControllerLoader("complex.json");
        //DungeonControllerLoader dungeonLoader = new DungeonControllerLoader("marking.json");
        
        DungeonController controller = dungeonLoader.loadController();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("DungeonView.fxml"));
        loader.setController(controller);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        root.requestFocus();
        primaryStage.setScene(scene);
        primaryStage.show();

    }
public static boolean createGuide(File file)throws Exception{
        	  boolean flag=false;
        	  try{
        	   if(file.exists() == false){
        	    file.createNewFile();
        	    flag=true;
        	   }
        	  }catch(Exception e){
        	   e.printStackTrace();
        	  }
        	  return true;
 } 

public static boolean writeGuide(String s,File file)throws Exception{
	  RandomAccessFile random=null;
	  boolean flag=false;
	  FileOutputStream o=null;
	  try {
	   o = new FileOutputStream(file);
		   FileWriter fr = new FileWriter(file, true);
		   fr.write("data");
	      o.write(s.getBytes("GBK"));
	      o.close();
	   flag=true;
	  } catch (Exception e) {
	   e.printStackTrace();
	  }finally{
	   if(random!=null){
	    random.close();
	   }
	  }
	  return flag;
 }

private static void myf (File source, File dest) {
	try{
	    
        BufferedReader br = new BufferedReader(new FileReader(source));
   BufferedWriter bw = new BufferedWriter(new FileWriter(dest)); 
  String strOutput = "yes\n";
  String strTemp = "======\n";
  String strTemp1 = "======\n";
        while ((strTemp = br.readLine()) != null){  
        	
        	strOutput = strTemp.replaceAll("\"type\": \"wall\"", ""); 
        	strOutput = strTemp.replaceAll("\"type\": \"dog\"", ""); 
        	strOutput = strTemp.replaceAll("\"type\": \"player\"", ""); 
        	strOutput = strTemp.replaceAll("\"type\": \"enemy\"", ""); 
        	strOutput = strTemp.replaceAll("\"type\": \"boulder\"", ""); 
        	strOutput = strTemp.replaceAll("\"type\": \"treasure\"", ""); 
        	strOutput = strTemp.replaceAll("\"type\": \"switch\"", ""); 
        	strOutput = strTemp.replaceAll("\"type\": \"sword\"", ""); 
        	strOutput = strTemp.replaceAll("\"type\": \"exit\"", ""); 
        	strOutput = strTemp.replaceAll("\"type\": \"key\"", ""); 
        	strOutput = strTemp.replaceAll("\"type\": \"door\"", ""); 
        	strOutput = strTemp.replaceAll("\"type\": \"bomb\"", ""); 
        	strOutput = strTemp.replaceAll("\"type\": \"invincibility\"", ""); 
        	strOutput = strTemp.replaceAll("\"type\": \"teleport\"", ""); 
        	strOutput = strTemp.replaceAll("\"type\": \"time\"", ""); 
        	strOutput = strTemp.replaceAll("\"type\": \"teleport\"", ""); 
        	strOutput = strTemp.replaceAll("\"type\": \"teleport\"", ""); 
        	strOutput = strTemp.replaceAll("\"type\": \"teleport\"", ""); 
        	strOutput = strTemp.replaceAll("\"type\": \"teleport\"", ""); 
        	strOutput = strTemp.replaceAll("\"type\": \"teleport\"", ""); 
        	strOutput = strTemp.replaceAll("\"type\": \"teleport\"", ""); 
        	strOutput = strTemp.replaceAll("\"type\": \"teleport\"", ""); 
        	strOutput = strTemp.replaceAll("\"type\": \"stone\"", ""); 
        	strOutput = strTemp.replaceAll("\"type\": \"sttwo\"", ""); 
        	strOutput = strTemp.replaceAll("\"type\": \"stthree\"", "");
            strOutput = strTemp.replaceAll("\"type\": \"stfour\"", "");
            strOutput = strTemp.replaceAll("\"type\": \"stfive\"", ""); 
            
         bw.write(strOutput);  
          bw.newLine();
   
     }
     br.close();
     bw.flush();
     bw.close();
     System.out.println("done");
     }catch(IOException ex){
       
     }
     
}

private static void copyFileUsingStream(File source, File dest) throws IOException {
    InputStream is = null;
    OutputStream os = null;
    try {
        is = new FileInputStream(source);
        os = new FileOutputStream(dest);
        byte[] buffer = new byte[1024];
        int length;
        while ((length = is.read(buffer)) > 0) {
            os.write(buffer, 0, length);
        }
    } finally {
        is.close();
        os.close();
    }
}

public static void main(String[] args) throws Exception {
    File file = new File ("/tmp_amd/glass/export/glass/4/z5211215/COMP2511/Guiano/Guide.txt");
    //File file = new File ("/tmp_amd/glass/export/glass/3/z5182291/COMP2511/Guide.txt");
    //File file = new File ("/Users/vincent/Desktop/Guiano/Guide.txt");
    createGuide(file);
    //if(writeGuide("here's Guide:", file)) {System.out.println("=====");}
    //if(writeGuide("hello world:", file)) {System.out.println("=====");}
    FileWriter fr = new FileWriter(file, true);
	fr.write("Here's the guide:\n\n");
	fr.write("Use keyboard to control your player to move.\n\nSpace for dropping bomb if you have one.\n\nC for dropping key if you have one\n\n"
			+ "Hound will bite Enemy after receiving Meat, Do not meet Hound without Meat.\n\n"
			+ "Use Teleport to flee from dangerous situation.\n\nUse time-freeze to frozon the motion of enemies.\n\n"
			+ "you can pick-up the around the map, use sword to kill enemies, use invicible portion to make you transparent, also use specific key to open the specific door\n\n\n"
			+ "You might have some goals:\n\n"
			+ "For example: collecting all the treasures, kill all the enemies, go the the exit or play with boulders.(or the combinition of these goals!!)\n\n"
			+ "Good luck!!!!!!");
	
	fr.close();
    launch(args);
    file.delete();

}

}
