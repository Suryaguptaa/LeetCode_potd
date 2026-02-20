class Solution {
    public String makeLargestSpecial(String s) {
        
        int n = s.length();

        ArrayList<String> bestAns = new ArrayList<>();

        int count =0;
        int start =0;
        
        for(int i=0;i<n;i++){
            count+=(s.charAt(i)=='1') ? 1 : -1;

            if(count == 0){
                String inner = s.substring(start+1,i);
                
                String innerRes = "1" + makeLargestSpecial(inner)+"0";
                bestAns.add(innerRes);
                start = i+1;
            } 
        }

        Collections.sort(bestAns,Collections.reverseOrder());

        StringBuilder res = new StringBuilder();
        for(String str : bestAns){
            res.append(str);
        }

        return res.toString();
    }
}