// hashmap
class Solution {
  public int[] singleNumber(int[] nums) {
    Map<Integer, Integer> hashmap = new HashMap();
    for (int n : nums)
      hashmap.put(n, hashmap.getOrDefault(n, 0) + 1);

    int[] output = new int[2];
    int idx = 0;
    for (Map.Entry<Integer, Integer> item : hashmap.entrySet())
      if (item.getValue() == 1) output[idx++] = item.getKey();

    return output;
  }
}

/*
In the first pass, we XOR all elements in the array, and get the XOR of the two numbers we need to find. 
Note that since the two numbers are distinct, so there must be a set bit (that is, the bit with value '1') in the XOR result. 
Find out an arbitrary set bit (for example, the rightmost set bit).

In the second pass, we divide all numbers into two groups, one with the aforementioned bit set,
 another with the aforementinoed bit unset. Two different numbers we need to find must fall into thte two distrinct groups.
XOR numbers in each group, we can find a number in either group.
*/
class Solution {
  public int[] singleNumber(int[] nums) {
    // difference between two numbers (x and y) which were seen only once
    int bitmask = 0;
    for (int num : nums) bitmask ^= num;

    // rightmost 1-bit diff between x and y
    int diff = bitmask & (-bitmask);

    int x = 0;
    // bitmask which will contain only x
    for (int num : nums) if ((num & diff) != 0) x ^= num;

    return new int[]{x, bitmask^x};
  }
}