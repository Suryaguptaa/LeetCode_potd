class Solution {
    public boolean hasAllCodes(String s, int k) {
        int r = 1 << k;
        int ans = countDistinctSubstrings(s,k);
        
        if(ans==r){
            return true;
        }
        else {
            return false;
        }
    }

    public static int countDistinctSubstrings(String s, int k) {
        if (s.length() < k) return 0;

        Set<String> set = new HashSet<>();

        for (int i = 0; i <= s.length() - k; i++) {
            String sub = s.substring(i, i + k);
            set.add(sub);
        }

        return set.size();
    }

    
}