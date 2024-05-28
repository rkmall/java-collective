package j_maths;

import java.util.Arrays;

public class RandomEx {

    public static void randomExample() {
        int totalNumbersToDraw = 5;
        int maxNum = 30;

        int[] numbers = new int[100];               // init array
        for(int i = 0; i < numbers.length; i++){    // fill array
            numbers[i] = i + 1;
        }

        // Draw total numbers and put in the second array(result)
        int[] result = new int[totalNumbersToDraw];
        for (int i = 0; i < result.length; i++) {

            // Math.random() produces floating point number between 0 (incl.) and 1 (excl.)
            // Multiplying the result with maxNum, we get number between 0 and (maxNum – 1)
            int random = (int) (Math.random() * maxNum);

            // Store random element in result[]
            // Elements in numbers[] start from 0. So, if random = 10, numbers[10] = 11
            result[i] = numbers[random];

            // Move last element to random position
            // We do not want to repeat the element
            numbers[random] = numbers[maxNum - 1];
            maxNum--;
        }

        // Sort array and print result
        Arrays.sort(result);
        System.out.println("The combination: ");
        for(int val : result){
            System.out.println(val);
        }
    }
}
