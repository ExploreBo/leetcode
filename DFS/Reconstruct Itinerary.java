// DFS(backtracking) + sorting
class Solution {
  // origin -> list of destinations
  HashMap<String, List<String>> flightMap = new HashMap<>();
  HashMap<String, boolean[]> visitBitmap = new HashMap<>();
  int flights = 0;
  List<String> result = null;


  public List<String> findItinerary(List<List<String>> tickets) {
    // Step 1). build the graph first
    for (List<String> ticket : tickets) {
      String origin = ticket.get(0);
      String dest = ticket.get(1);
      if (this.flightMap.containsKey(origin)) {
        List<String> destList = this.flightMap.get(origin);
        destList.add(dest);
      } else {
        List<String> destList = new LinkedList<String>();
        destList.add(dest);
        this.flightMap.put(origin, destList);
      }
    }

    // Step 2). order the destinations and init the visit bitmap
    for (Map.Entry<String, List<String>> entry : this.flightMap.entrySet()) {
      Collections.sort(entry.getValue());
      this.visitBitmap.put(entry.getKey(), new boolean[entry.getValue().size()]);
    }

    this.flights = tickets.size();
    LinkedList<String> route = new LinkedList<String>();
    route.add("JFK");

    // Step 3). backtracking
    this.backtracking("JFK", route);
    return this.result;
  }

  protected boolean backtracking(String origin, LinkedList<String> route) {
    if (route.size() == this.flights + 1) {
      this.result = (List<String>) route.clone();
      return true;
    }

    if (!this.flightMap.containsKey(origin))
      return false;

    int i = 0;
    boolean[] bitmap = this.visitBitmap.get(origin);

    for (String dest : this.flightMap.get(origin)) {
      if (!bitmap[i]) {
        bitmap[i] = true;
        route.add(dest);
        boolean ret = this.backtracking(dest, route);
        route.pollLast();
        bitmap[i] = false;

        if (ret)
          return true;
      }
      ++i;
    }

    return false;
  }
}


// post-order DFS
class Solution {
  // origin -> list of destinations
  HashMap<String, LinkedList<String>> flightMap = new HashMap<>();
  LinkedList<String> result = null;

  public List<String> findItinerary(List<List<String>> tickets) {
    // Step 1). build the graph first
    for(List<String> ticket : tickets) {
      String origin = ticket.get(0);
      String dest = ticket.get(1);
      if (this.flightMap.containsKey(origin)) {
        LinkedList<String> destList = this.flightMap.get(origin);
        destList.add(dest);
      } else {
        LinkedList<String> destList = new LinkedList<String>();
        destList.add(dest);
        this.flightMap.put(origin, destList);
      }
    }

    // Step 2). order the destinations
    this.flightMap.forEach((key, value) -> Collections.sort(value));

    this.result = new LinkedList<String>();
    // Step 3). post-order DFS
    this.DFS("JFK");
    return this.result;
  }

  protected void DFS(String origin) {
    // Visit all the outgoing edges first.
    if (this.flightMap.containsKey(origin)) {
      LinkedList<String> destList = this.flightMap.get(origin);
      while (!destList.isEmpty()) {
        // while we visit the edge, we trim it off from graph.
        String dest = destList.pollFirst();
        DFS(dest);
      }
    }
    // add the airport to the head of the itinerary
    this.result.offerFirst(origin);
  }
}