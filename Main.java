import java.util.Arrays;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        try {
            MapEnv map = new MapEnv();
            Agent a1 = new Agent(new int[]{0,0}, map, 9);
            Agent a2 = new Agent(new int[]{0,2}, map, 8);
            map.addAgent(a1.getAgentID(), a1.getPos());
            map.addAgent(a2.getAgentID(), a2.getPos());
            a1.setPath(Arrays.asList(new int[]{1,0},new int[]{2,0},new int[]{2,1}));
            a2.setPath(Arrays.asList(new int[]{1,2},new int[]{2,2},new int[]{2,1}));
            new Thread(a1).start();
            new Thread(a2).start();
            Thread.sleep(5000);
            System.out.println("a1: "+Arrays.toString(a1.getPos())+"a2: "+Arrays.toString(a2.getPos()));
            a1.setPath(Arrays.asList(new int[]{a1.getPos()[0]+1,a1.getPos()[1]},new int[]{a1.getPos()[0]+2,a1.getPos()[1]}));
            a2.setPath(Arrays.asList(new int[]{a2.getPos()[0],a2.getPos()[1]+1},new int[]{a2.getPos()[0],a2.getPos()[1]+2}));
            new Thread(a1).start();
            new Thread(a2).start();
            Thread.sleep(1000);
            a1.getHistory().stream().map(arr -> Arrays.stream(arr)  // Stream the int[] elements
                                    .mapToObj(String::valueOf)  // Convert each int to String
                                    .collect(Collectors.joining(", ")))  // Join with commas
                                    .forEach(str -> System.out.print(str + " "));
            System.out.println();
            a2.getHistory().stream().map(arr -> Arrays.stream(arr)  // Stream the int[] elements
                                    .mapToObj(String::valueOf)  // Convert each int to String
                                    .collect(Collectors.joining(", ")))  // Join with commas
                                    .forEach(str -> System.out.print(str + " "));
        } catch(Exception e) {
            System.err.println(e);
        }

        
    }
}
