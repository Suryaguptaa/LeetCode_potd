import java.math.BigInteger;

class Solution {
    public int numSteps(String s) {
        // int x = Integer.parseInt(s, 2);
        BigInteger x = new BigInteger(s, 2);
        int c=0;

        while(!x.equals(BigInteger.ONE)){
            if(x.mod(BigInteger.TWO).equals(BigInteger.ZERO)){
                x=x.divide(BigInteger.TWO);
            }
            else{
                x=x.add(BigInteger.ONE);
            }
            c++;
        }

        return c;
    }
}