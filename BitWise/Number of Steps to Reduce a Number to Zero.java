class Solution {
    public int numberOfSteps(int num) {
        if (num == 0) return 0;
        if (num == 1) return 1;
        if (num % 2 == 0) {
            return 1 + numberOfSteps(num >> 1);
        } else {
            return 2 + numberOfSteps(num >> 1);
        }
    }
}

// more bit wise operations
 public int numberOfSteps(int num) {
    int totalSteps = 0;
    
    while (num > 0) {
        if ((num & 1) == 0) 
            num = num >> 1;
        else 
            num = num ^ 1;
        
        totalSteps++;
    }
    
    return totalSteps;
}