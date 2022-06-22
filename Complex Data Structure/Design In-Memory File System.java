class FileSystem {
    
    class Directory {
        Map<String, Directory> directoryMap;
        Map<String, String> fileMap;
        
        Directory () {
            directoryMap = new HashMap<>();
            fileMap = new HashMap<>();
        }        
    }
    
    Directory root;

    public FileSystem() {
        root = new Directory();
    }
    
    public List<String> ls(String path) {
        Directory current = root;
        List <String> result = new ArrayList < > ();
        if (!path.equals("/")) {
            String[] elements = path.split("/");
            for (int i = 1; i < elements.length - 1; i++) {
                current = current.directoryMap.get(elements[i]);
            }
            if (current.fileMap.containsKey(elements[elements.length - 1])) {
                result.add(elements[elements.length - 1]);
                return result;
            } else {
                current = current.directoryMap.get(elements[elements.length - 1]);
            }            
        }
        result.addAll(current.directoryMap.keySet());
        result.addAll(current.fileMap.keySet());
        Collections.sort(result);
        return result;
    }
    
    public void mkdir(String path) {
        String[] elements = path.split("/");
        Directory current = root;
        for (int i = 1; i < elements.length; i++) {
            if (!current.directoryMap.containsKey(elements[i])) {
                current.directoryMap.put(elements[i], new Directory());
            }
            current = current.directoryMap.get(elements[i]);
        }
    }
    
    public void addContentToFile(String filePath, String content) {
        String[] elements = filePath.split("/");
        Directory current = root;
        for (int i = 1; i < elements.length - 1; i++) {
            current = current.directoryMap.get(elements[i]);
        }
        String fileName = elements[elements.length - 1];
        current.fileMap.put(fileName, current.fileMap.getOrDefault(fileName, "") + content);
    }
    
    public String readContentFromFile(String filePath) {
        String[] elements = filePath.split("/");
        Directory current = root;
        for (int i = 1; i < elements.length - 1; i++) {
            current = current.directoryMap.get(elements[i]);
        }

        return current.fileMap.get(elements[elements.length - 1]);       
    }
}