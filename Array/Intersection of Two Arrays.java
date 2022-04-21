// using 2 sets
class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        if (nums1.length == 0 || nums2.length == 0) return new int[]{};
        Set<Integer> set1 = new HashSet();
        Set<Integer> set2 = new HashSet();
        for (int i = 0; i < nums1.length; i++) {
            set1.add(nums1[i]);
        }
        for (int j = 0; j < nums2.length; j++) {
            if (set1.contains(nums2[j])) {
                set2.add(nums2[j]);
            }
        }
        int[] result = new int[set2.size()];
        Iterator<Integer> setIterator = set2.iterator();
        int count = 0;
        while (setIterator.hasNext()) {
            result[count++] = setIterator.next();
        }
        return result;
    }
}

// using 1 set
class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set = new HashSet<>();
        for (int i : nums1) {
            set.add(i);
        }

        List<Integer> result = new ArrayList<>();

        for (int i : nums2) {
            if(set.contains(i)){
                result.add(i);
                // remove in the set to avoid duplicates
                set.remove(i);
            }
        }

        int[] ints = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            ints[i]=result.get(i);
        }
        return ints;
    }
}



// without using set, need to sort the arrays
class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
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
                list.add(nums1[i]);
                while (i + 1 < m && nums1[i] == nums1[i + 1]) {
                    i++;
                }
                while (j + 1 < n && nums2[j] == nums2[j + 1]) {
                    j++;
                }            
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