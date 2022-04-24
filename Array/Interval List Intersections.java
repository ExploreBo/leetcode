// initial version
class Solution {
    public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        LinkedList<int[]> result = new LinkedList();
        int i = 0;
        int j = 0;
        while (i < firstList.length && j < secondList.length) {
            if (firstList[i][0] > secondList[j][1]) {
                j++;
            } else if (firstList[i][1] < secondList[j][0]) {
                i++;
            } else {
                result.add(new int[]{Math.max(firstList[i][0], secondList[j][0]), Math.min(firstList[i][1], secondList[j][1])});
                if (firstList[i][1] > secondList[j][1]) {
                    j++;
                } else {
                    i++;
                }
            }
        }
        return result.toArray(new int[result.size()][]);
    }
}

// better version
class Solution {
  public int[][] intervalIntersection(int[][] A, int[][] B) {
    List<int[]> ans = new ArrayList();
    int i = 0, j = 0;

    while (i < A.length && j < B.length) {
      // Let's check if A[i] intersects B[j].
      // lo - the startpoint of the intersection
      // hi - the endpoint of the intersection
      int lo = Math.max(A[i][0], B[j][0]);
      int hi = Math.min(A[i][1], B[j][1]);
      if (lo <= hi)
        ans.add(new int[]{lo, hi});

      // Remove the interval with the smallest endpoint
      if (A[i][1] < B[j][1])
        i++;
      else
        j++;
    }

    return ans.toArray(new int[ans.size()][]);
  }
}