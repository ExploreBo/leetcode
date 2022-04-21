// using hashmap, set is not enough, need map to store the count for each number
public int[] intersect(int[] nums1, int[] nums2) {
    if (nums1.length > nums2.length) {
        return intersect(nums2, nums1);
    }
    HashMap<Integer, Integer> m = new HashMap<>();
    for (int n : nums1) {
        m.put(n, m.getOrDefault(n, 0) + 1);
    }
    int k = 0;
    for (int n : nums2) {
        int cnt = m.getOrDefault(n, 0);
        // only copy the value when the cnt is positive
        // this is to make sure "appear as many times as it shows in both arrays"
        if (cnt > 0) {
            nums1[k++] = n;
            // to make sure "appear as many times as it shows in both arrays"
            m.put(n, cnt - 1);
        }
    }
    return Arrays.copyOfRange(nums1, 0, k);
}

// require the array being sorted, with extra space
class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        int[] result;
        int m = nums1.length;
        int n = nums2.length;
        if (m == 0 || n == 0) return new int[]{};
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        List<Integer> list = new ArrayList();
        int i = 0, j = 0;
        while (i < m && j < n) {
            if (nums1[i] == nums2[j]) {
                // int count1 = 1;
                // int count2 = 1;
                // while (i + 1 < m && nums1[i] == nums1[i + 1]) {
                //     i++;
                //     count1++;
                // }
                // while (j + 1 < n && nums2[j] == nums2[j + 1]) {
                //     j++;
                //     count2++;
                // }
                // for (int k = 0; k < Math.min(count1, count2); k++) {
                //     list.add(nums1[i]);
                // }
                list.add(nums1[i]);
                i++;
                j++;
            } else if (nums1[i] < nums2[j]) {
                i++;
            } else {
                j++;
            }
        }
        result = new int[list.size()];
        for (int k = 0; k < list.size(); k++) {
            result[k] = list.get(k);
        }
        return result;
    }
}

// require the array being sorted, in place
public int[] intersect(int[] nums1, int[] nums2) {
    Arrays.sort(nums1);
    Arrays.sort(nums2);
    int i = 0, j = 0, k = 0;
    while (i < nums1.length && j < nums2.length) {
        if (nums1[i] < nums2[j]) {
            ++i;
        } else if (nums1[i] > nums2[j]) {
            ++j;
        } else {
            nums1[k++] = nums1[i++];
            ++j;
        }
    }
    return Arrays.copyOfRange(nums1, 0, k);
}