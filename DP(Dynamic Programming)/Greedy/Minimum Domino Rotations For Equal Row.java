// My version
class Solution {
    public int minDominoRotations(int[] tops, int[] bottoms) {
        int L = tops.length;
        int[] topBucket = new int[7];
        int[] bottomBucket = new int[7];
        int fixed = 0;
        int dupCount = 0;
        for (int i = 0; i < L; ++i) {
            topBucket[tops[i]]++;
            bottomBucket[bottoms[i]]++;
            if (tops[i] == bottoms[i]) {
                if (fixed == 0) {
                    fixed = tops[i];
                    ++dupCount;
                } else {
                    if (fixed == tops[i]) {
                        ++dupCount;
                    } else {
                        return -1;
                    }
                }
            }
        }
        if (fixed != 0) {
             if (topBucket[fixed] + bottomBucket[fixed] >= L + dupCount) {
                 return Math.min(topBucket[fixed], bottomBucket[fixed]) - dupCount;
             } else {
                 return -1;
             }
        } else {
            int min = Integer.MAX_VALUE;
            for (int i = 1; i <= 6; ++i) {
                if (topBucket[i] + bottomBucket[i] >= L) {
                    min = Math.min(min, Math.min(topBucket[i], bottomBucket[i]));
                }
            }
            return min == Integer.MAX_VALUE ? -1 : min;
        }
        
    }
}


// LC official version
class Solution {
  /*
  Return min number of rotations 
  if one could make all elements in A or B equal to x.
  Else return -1.
  */
  public int check(int x, int[] A, int[] B, int n) {
    // how many rotations should be done
    // to have all elements in A equal to x
    // and to have all elements in B equal to x
    int rotations_a = 0, rotations_b = 0;
    for (int i = 0; i < n; i++) {
      // rotations coudn't be done
      if (A[i] != x && B[i] != x) return -1;
      // A[i] != x and B[i] == x
      else if (A[i] != x) rotations_a++;
      // A[i] == x and B[i] != x    
      else if (B[i] != x) rotations_b++;
    }
    // min number of rotations to have all
    // elements equal to x in A or B
    return Math.min(rotations_a, rotations_b);
  }

  public int minDominoRotations(int[] A, int[] B) {
    int n = A.length;
    int rotations = check(A[0], B, A, n);
    // If one could make all elements in A or B equal to A[0]
    if (rotations != -1 || A[0] == B[0]) return rotations;
    // If one could make all elements in A or B equal to B[0]
    else return check(B[0], B, A, n);
  }
}