class Solution {
  public boolean isPowerOfFour(int num) {
    return (num > 0) && (Math.log(num) / Math.log(2) % 2 == 0);
  }
}


// 0xaaaaaaaa is 10101010101010...
class Solution {
  public boolean isPowerOfFour(int num) {
    return (num > 0) && ((num & (num - 1)) == 0) && ((num & 0xaaaaaaaa) == 0);
  }
}