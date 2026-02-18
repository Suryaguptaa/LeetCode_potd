class Solution {
    public boolean hasAlternatingBits(int n) {

        BitSet bs = BitSet.valueOf(new long[]{n});

        int hI = 31 - Integer.numberOfLeadingZeros(n);

        for (int i = 31; i >= 0; i--) {
            System.out.print(bs.get(i) ? "1" : "0");
        }

        for(int i=0;i<hI;i++){
            if(bs.get(i)==bs.get(i+1)){
                return false;
            }
        }
    
    return true;
        
    }
}