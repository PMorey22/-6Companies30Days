/*There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want to take course ai.

For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
Return true if you can finish all courses. Otherwise, return false.*/
class Solution {
    
    Map<Integer, List> adj = new HashMap<>();
    
    public List<Integer> getNeighbour(int n){
        return new ArrayList<>(adj.get(n));
    }
    
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        
        if (prerequisites.length == 0){
            return true;
        }
        
        int[] indegree = new int[numCourses];
        
        for (int i  = 0; i < prerequisites.length; i++){
            adj.computeIfAbsent(prerequisites[i][1], 
                                k -> new ArrayList<>()).add(prerequisites[i][0]);
            indegree[prerequisites[i][0]]++;
                                
        }
        
        Deque<Integer> queue = new ArrayDeque<>();
        
        for (int i = 0; i < indegree.length; i++){
            if (indegree[i] == 0){
                queue.offer(i);
            }
        }
        
        int count = 0;
        
        while (!queue.isEmpty()) {
            int current = queue.poll();
            
            if (indegree[current] == 0 ){
                count++;
            }
            
            if (!adj.containsKey(current)) {
                continue;
            }
            
            for (int neighbour :  getNeighbour(current)){
                indegree[neighbour]--;
                if (indegree[neighbour] == 0 ){
                    queue.offer(neighbour);
                }
            }
        }
 
        return numCourses == count;
    }
}
