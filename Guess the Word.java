/*
Idea: trying to narrow the candidates after each time we call master.guess()

How: Because secret has exactly x matches with word, we can just search in the candidates, 
and only keep the ones that have exact x matches with word. 
In this way, we narrow the candidates after we call master.guess(),
 and guarantee that secret is in candidates left
*/
class Solution {
    public void findSecretWord(String[] wordlist, Master master) {
        Random random = new Random();
        for(int i = 0, matches = 0; i < 10 && matches != 6; i ++){
            String guess = wordlist[random.nextInt(wordlist.length)];
            matches = master.guess(guess);
            List<String> candidates = new ArrayList<>();
            for(String word: wordlist){
                if(matches == getMatches(guess, word)){
                    candidates.add(word);
                }
            }
            
            wordlist = candidates.toArray(new String[0]);
        }
    }
    
    private int getMatches(String word1, String word2){
        int matches = 0;
        for(int i = 0; i < word1.length(); i ++){
            if(word1.charAt(i) == word2.charAt(i)){
                matches ++;
            }
        }
        
        return matches;
    }
}