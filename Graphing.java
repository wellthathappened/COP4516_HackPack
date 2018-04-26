import java.util.LinkedList;
import java.util.Queue;

public class Graphing
{
    public static class Point
    {
        int num;
        
        Point [] connections;
    
        boolean visited;
        
        public Point(int num)
        {
            this.num = num;
            connections = null;
            visited = false;
        }
    }

    public static void main(String[] args)
    {
        Point[] graph = new Point[5];
        
        for(int i = 0;i < graph.length;i++)
            graph[i] = new Point(i + 1);
        
        graph[0].connections = new Point[2];
        graph[0].connections[0] = graph[2];
        graph[0].connections[1] = graph[4];
        
        graph[1].connections = new Point[1];
        graph[1].connections[0] = graph[0];
        
        graph[2].connections = new Point[1];
        graph[2].connections[0] = graph[3];
        
        graph[3].connections = new Point[1];
        graph[3].connections[0] = graph[4];
        
        graph[4].connections = new Point[0];
        
        dfs(graph[0]);
        bfs(graph[0]);
    }
    
    static void dfs(Point currentPoint)
    {
        if(currentPoint.visited == false)
        {
            currentPoint.visited = true;
        
            for(Point connection : currentPoint.connections)
                if(connection.visited == false)
                    dfs(connection);
        }
    }
    
    static void bfs(Point currentPoint)
    {
        Queue<Point> pointsList = new LinkedList<>();
        
        pointsList.add(currentPoint);
                
        while(pointsList.isEmpty() == false)
        {
            Point node = pointsList.remove();
            
            for(Point connection : node.connections)
                if(connection.visited == false)
                {
                    connection.visited = true;
                    pointsList.add(connection);
                }
        }
    }
}