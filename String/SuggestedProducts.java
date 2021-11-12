class Solution {
    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        List<List<String>> result = new ArrayList();
        Arrays.sort(products);
        for (int i = 0; i < searchWord.length(); i++) {
            result.add(helper(products, searchWord.substring(0, i + 1)));
        }
        return result;
    }
    
    public List<String> helper(String[] products, String sWord) {
        List<String> result = new ArrayList();
        for (String product : products) {
            if (product.startsWith(sWord)) {
                result.add(product);
            }
            if (result.size() == 3) {
                break;
            } 
        }
        return result;
    }   
}