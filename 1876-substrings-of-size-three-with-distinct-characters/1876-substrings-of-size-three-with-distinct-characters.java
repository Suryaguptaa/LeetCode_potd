class Solution {
    public int countGoodSubstrings(String s) {
        Set<Character> set = new HashSet<>();
        int n = s.length();
        int c =0;
        int i =0;
        int l =0;
        // String a = s.charAt(0)+s.charAt(1)+s.charAt(2);

        while(i<n){
            if(!set.contains(s.charAt(i))){
                set.add(s.charAt(i));

                if(set.size()==3){
                    c++;
                    set.remove(s.charAt(l));
                    l++;
                }

                i++;
            }

            else{
                set.remove(s.charAt(l));
                    l++;
                }
            }
        

        return c;
    }
}