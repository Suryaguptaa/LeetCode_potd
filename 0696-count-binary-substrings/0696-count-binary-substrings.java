class Solution {
    public int countBinarySubstrings(String s) {
    
    int n = s.length();

    char [] crr = s.toCharArray();

    int pc =0;
    int cc=1;
    int res =0;

    for(int i =1;i<n;i++){
        if(s.charAt(i)==s.charAt(i-1)){
            cc++;
        }
        else{
            res+=Math.min(pc,cc);
            pc=cc;
            cc=1;
        }

    }   

    res+=Math.min(pc,cc);

    return res;
    
    }
}