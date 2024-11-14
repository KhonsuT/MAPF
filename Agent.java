import java.sql.Time;
import java.sql.Timestamp;
import java.util.*;

public class Agent implements Runnable{
    int[] pos = new int[2];
    int orientation = 0;
    List<int[]> path = new ArrayList<>();
    List<int[]> history = new ArrayList<>();
    MapEnv map; 
    int agentID;
    public Agent( int[] pos, MapEnv map, int agentID) {
        this.pos = pos;
        this.map = map;
        this.agentID = agentID;
    }
    
    //getters
    public int[] getPos() {return pos;}
    public List<int[]> getPath() {return path;}
    public List<int[]> getHistory() {return history;}
    public int getAgentID() {return agentID;}

    //setters
    public void setPath(List<int[]> path) {this.path=path;}
    public void setPos(int[] newPos) {this.pos=newPos;}

    //Send update at each motion
    private void sendUpdate(Timestamp currTimestamp, int[] newPos) throws Exception{
            this.map.updateAgent(agentID,currTimestamp, newPos);
    }

    @Override
    public void run() {
        // extends thread class to run each agent independent of main thread
        // run method will execute the path based on orientation and current position
        if (path.size()!=0) {
            System.out.println("Agent "+agentID+" starts moving");
            try {
                for (int[] p: path) {
                    sendUpdate(new Timestamp(System.currentTimeMillis()), p);
                    history.add(this.pos);
                    this.pos = p;
                    Thread.sleep(1000);
                }
            }catch(Exception e) {
                System.err.println(e);
            }finally {
                this.path = new ArrayList<>();
            }
        }else System.err.println("Path Empty, cancel movement request!");
    }
}