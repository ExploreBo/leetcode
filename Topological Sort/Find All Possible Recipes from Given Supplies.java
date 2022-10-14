class Solution {
    public List<String> findAllRecipes(String[] recipes, List<List<String>> ingredients, String[] supplies) {
        Set<String> recipesSet = new HashSet();
        Set<String> suppliesSet = new HashSet();
        for (String recipe : recipes) {
            recipesSet.add(recipe);
        }
        for (String supply : supplies) {
            suppliesSet.add(supply);    
        }

        // initialize adjList and indegrees
        Map<String, List<String>> adjList = new HashMap();
        Map<String, Integer> indegrees = new HashMap();
        for (int i = 0; i < ingredients.size(); ++i) {
            String recipe = recipes[i];
            for (String ingredient : ingredients.get(i)) {
                adjList.putIfAbsent(ingredient, new ArrayList());
                adjList.get(ingredient).add(recipe);
                indegrees.put(recipe, indegrees.getOrDefault(recipe, 0) + 1);
                indegrees.putIfAbsent(ingredient, 0);
            }
        }

        // initilize the queue with o indegrees nodes
        Queue<String> queue = new LinkedList();
        List<String> result = new ArrayList();
        for (String ingredient : indegrees.keySet()) {
            if (indegrees.get(ingredient) == 0) {
                queue.add(ingredient);
            }
        }

        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                String ingredient = queue.poll();
                if (suppliesSet.contains(ingredient)) {
                    List<String> neighbors = adjList.get(ingredient);
                    if (neighbors != null) {
                        for (String neighbor : neighbors) {
                            indegrees.put(neighbor, indegrees.get(neighbor) - 1);
                            if (indegrees.get(neighbor) == 0) {
                                suppliesSet.add(neighbor);
                                queue.add(neighbor);
                                if (recipesSet.contains(neighbor)) {
                                    result.add(neighbor);
                                }
                            }
                        }
                    }
                }
            }
        }
        return result;       
    }
}