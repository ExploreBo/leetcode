class Solution {
    public int earliestAcq(int[][] logs, int n) {
        if (n < 1 || logs == null || logs.length < 1) return -1; 
        Arrays.sort(logs, Comparator.comparingInt(a -> a[0]));
        UF connections = new UF(n);
        for (int i = 0; i < logs.length; i++) {
            connections.union(logs[i][1], logs[i][2]);
            if (connections.count == 1) {
                return logs[i][0];
            }
        }
        return -1;
    }
    
    private class UF {
        public int count;
        public int[] id = null;

        public UF(int n) {
            count = n;
            id = new int[n];
            for(int i = 0; i < n; i++) {
                id[i] = i;
            }
        }

        public int find(int p) {
            while(p != id[p]) {
                id[p] = id[id[p]];
                p = id[p];
            }
            return p;
        }

        public boolean isConnected(int p, int q) {
            int pRoot = find(p);
            int qRoot = find(q);
            if(pRoot != qRoot) return false;
            else return true;
        }

        public void union(int p, int q) {
            int pRoot = find(p);
            int qRoot = find(q);
            if(pRoot == qRoot) return;
            id[pRoot] = qRoot;
            count--;
        }        
    }    
}
