class Solution {
    public int maximumDetonation(int[][] bombs) {
        int max = 0;
        //iterate through each bomb and keep track of max
        for(int i = 0; i<bombs.length; i++){
            max = Math.max(max, getMaxBFS(bombs, i));    
        }
        return max;
    }
    
   private int getMaxBFS(int[][] bombs, int index){
        Queue<Integer> queue = new LinkedList<>();
        boolean[] seen = new boolean[bombs.length];
        
        seen[index] = true;
        queue.offer(index);
        
        int count = 1; // start from 1 since the first added bomb can detonate itself
        
        while(!queue.isEmpty()){
            int currBomb = queue.poll();
            for(int j = 0; j<bombs.length; j++){ //search for bombs to detonate
                if(!seen[j] && isInRange(bombs[currBomb], bombs[j])){
                    seen[j] = true;
                    count++;
                    queue.offer(j);
                }
            }
        }
        
        return count;
    }
    
    //use the distance between two points formula
    //then check if curr bomb radius is greater than the distance; meaning we can detonate the second bombs
    private boolean isInRange(int[] point1, int[] point2) {
        long dx = point1[0] - point2[0], dy = point1[1] - point2[1], radius = point1[2];
        long distance =  dx * dx + dy * dy;
        return distance <= radius * radius;
    }
}