import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

/**
 * Created by saravm on 4/9/16.
 */
public class JamCoin {

    public static void main(String[] args) {
        String fileName = "/Users/saravm/a.in";
        String line;
        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            bufferedReader.readLine();
            line = bufferedReader.readLine();
            Long min, max;
            int J;
            int N;
            if(line.equals("16 50")) {
                min = Long.valueOf("1000000000000001", 2);
                max = Long.valueOf("1111111111111111", 2);
                J = 50;
                N = 16;
            } else {
                min = Long.valueOf("10000000000000000000000000000001", 2);
                max = Long.valueOf("11111111111111111111111111111111", 2);
                for(Long l = min; l <= max; l++) {
                    if(getDivisor(l) != -1) {
                        nonPrimes.add(l);
                        if(nonPrimes.size() > 10000)
                            break;
                    }
                }

                J=500;
                N=32;
            }
            System.out.println("Case #1:");
//                for(Long number = min; number <= max; number++) {
                for(Long number : nonPrimes) {
                    boolean isPrimeInAnyBase = false;
                    String binaryRep = Long.toString(number, 2);
                    if(binaryRep.charAt(N-1) == '0') {
                        continue;
                    }
                    List<Long> divisorList = new ArrayList<>();
                    for(int base=2; base<=10; base++) {
                        Long numberInBase = Long.valueOf(binaryRep, base);
                        Long divisor = getDivisor(numberInBase);
                        if(divisor == -1) {
                            isPrimeInAnyBase = true;
                            break;
                        }
                        divisorList.add(divisor);
                    }
                    if(!isPrimeInAnyBase) {
                        System.out.println(binaryRep + " " + printList(divisorList));
                        J--;

                    }
                    if(J<=0) {
                        break;
                    }
            }
            bufferedReader.close();
        } catch (Exception e ) {

        }
    }

    private static List<Long> nonPrimes  = new ArrayList<>();

    private static  String printList(List<Long> l) {
        String result="";
        for(Long s: l) {
            result = result + s +" ";
        }
        return  result;
    }

    public static long getDivisor(long num){
        if ( num > 2 && num%2 == 0 ) {
            return 2;
        }
        int top = (int)Math.sqrt(num) + 1;
        for(int i = 3; i < top; i+=2){
            if(num % i == 0){
                return i;
            }
        }
        return -1;
    }
}


