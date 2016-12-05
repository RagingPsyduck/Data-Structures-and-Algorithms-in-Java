// time : worst case
//        one level - check if valid o(n)
//        second level ( n - 1 ) new string , each has (n-1) character
// length of n 
// every character has 2 states (keep/remove) 2^n
// check valid is o(n)
// all together o(n*2^n)
public class Solution {
    public List<String> removeInvalidParentheses(String s) {
        // 1. with the input string s, generate all possible state
        // by removing one (  or ) 
        // 2. check if they are valid
        // 3. if valid, put them to the final result list
        // other, add them to a queue and carry on to the next level
        
        List<String> res = new ArrayList<>();
        if(s==null) return res;
        
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        
        queue.add(s);
        visited.add(s);
        
        boolean found = false;
        
        while(!queue.isEmpty()){
            s = queue.poll();
            if(isValid(s)){
                res.add(s);
                found = true;
            }
            // skip next function if found
            if(found) continue;
            
            // generate all possible states
            for(int i=0 ; i < s.length() ; i++){
                // try to remove left or right
                if(s.charAt(i) != '(' && s.charAt(i) != ')' ) continue;
                // 0 ~ i - 1  +  i + 1 ~ end
                String t = s.substring(0,i) + s.substring(i+1);
                if(!visited.contains(t)){
                    queue.add(t);
                    visited.add(t);
                }
            }
        }
        return res;
    }
    private boolean isValid(String s){
        int count = 0;
        for(int i = 0 ; i < s.length() ; i++){
            char c = s.charAt(i);
            if(c == '(') count++;
            if(c == ')' && count-- ==0 ) return false;
        }
        return count == 0;
    }
}