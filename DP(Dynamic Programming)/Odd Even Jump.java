class Solution {
    public int oddEvenJumps(int[] arr) {
        int N = arr.length;
        if (N == 1) {
            return 1;
        }
        int[] oddNext = new int[N];
        int[] evenNext = new int[N];
        TreeMap<Integer, Integer> treeMap = new TreeMap();
        oddNext[N - 1] = evenNext[N - 1] = N - 1;
        treeMap.put(arr[N - 1], N - 1);
        for (int i = N - 2; i >= 0; --i) {
            Integer oddNextIndex = treeMap.ceilingKey(arr[i]);
            oddNext[i] = oddNextIndex == null ? -1 : treeMap.get(oddNextIndex);
            Integer evenNextIndex = treeMap.floorKey(arr[i]);
            evenNext[i] = evenNextIndex == null ? -1 : treeMap.get(evenNextIndex);
            treeMap.put(arr[i], i);
        }

        boolean[] odd = new boolean[N];
        boolean[] even = new boolean[N];
        odd[N - 1] = even[N - 1] = true;

        for (int i = N - 2; i >= 0; --i) {
            odd[i] = (oddNext[i] != -1) && even[oddNext[i]];
            even[i] = (evenNext[i] != -1) && odd[evenNext[i]];
        }
        int count = 0;
        for (int i = 0; i < N; ++i) {
            if (odd[i]) {
                ++count;
            }
        }
        return count;
    }
}