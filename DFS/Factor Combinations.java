class Solution {
    public List<List<Integer>> getFactors(int n) {
        List<List<Integer>> res = new LinkedList<>();
        backtrack(2, n, new LinkedList<>(), res);
        return res;
    }

    void backtrack(int start, int n, List<Integer> list, List<List<Integer>> res) {
        for (int i = start; i * i <= n; i++) {
            if (n % i == 0) {
                list.add(i);
                list.add(n / i);
                res.add(new LinkedList<>(list));
                list.remove(list.size() - 1);
                backtrack(i, n / i, list, res);
                list.remove(list.size() - 1);
            }
        }
    }
}


// This function will cause stackOverFlow, this is caused by int overflow and new Product would be 0 and results in a infinite loop.
class Solution {
    private int target;
    private List<Integer> factors;
    private Set<List<Integer>> set;
    public List<List<Integer>> getFactors(int n) {
        if (n == 1) return new ArrayList();
        set = new HashSet();
        this.target = n;
        factors = new ArrayList();
        for (int i = 2; i < n; i++) {
            if (n % i == 0) {
                factors.add(i);
            }
        }
        // if (factors.size() <= 1) return new ArrayList(set);
        helper(1, 0, new ArrayList());
        
        return new ArrayList(set);
    }
    
    void helper(int product, int curr, List<Integer> temp) {
        if (product == target) {
            set.add(new ArrayList(temp));       
        } else {
            for (int i = curr; i < factors.size(); i++) {
                int newProduct = factors.get(i) * product;
                if (newProduct <= target) {
                    temp.add(factors.get(i));
                    helper(newProduct, i, temp);
                    temp.remove(temp.size() - 1); 
                } else {
                    break;
                }
            }
        }
    }
}

