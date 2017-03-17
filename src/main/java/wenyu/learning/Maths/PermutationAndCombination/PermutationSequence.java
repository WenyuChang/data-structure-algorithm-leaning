package wenyu.learning.Maths.PermutationAndCombination;

import java.util.Arrays;

/**
 * Created by Wenyu on 11/30/16.
 *
 * The set [1,2,3,…,n] contains a total of n! unique permutations.
 * By listing and labeling all of the permutations in order,
 * We get the following sequence (ie, for n = 3):
     "123"
     "132"
     "213"
     "231"
     "312"
     "321"
 * Given n and k, return the kth permutation sequence.
 * Note: Given n will be between 1 and 9 inclusive.
 *
 * Solution1: using find next bigger
 * Solution2: http://stackoverflow.com/questions/31216097/given-n-and-k-return-the-kth-permutation-sequence (don't understand yet)
 */

class Solution1 {
    public boolean find(int[] numStr) {
        for(int i=numStr.length-2; i>=0; i--) {
            if(numStr[i] < numStr[i+1]) {
                // Find first digit greater than current digit
                for(int j=numStr.length-1; j>i; j--) {
                    if(numStr[j] > numStr[i]) {
                        int tmp = numStr[j];
                        numStr[j] = numStr[i];
                        numStr[i] = tmp;
                        break;
                    }
                }

                // reverse sort rest
                int start = i+1;
                int end = numStr.length-1;
                while(start < end) {
                    int tmp = numStr[start];
                    numStr[start] = numStr[end];
                    numStr[end] = tmp;
                    start++;
                    end--;
                }
                return true;
            }
        }
        return false;
    }

    public String getPermutation(int n, int k) {
        int[] arr = new int[n];
        for (int i=1; i<=n; i++) {
            arr[i-1] = i;
        }

        for (int i=1; i<k; i++) {
            if (!find(arr)) return null;
        }

        StringBuilder builder = new StringBuilder();
        for (int i=0; i<n; i++) {
            builder.append(arr[i]);
        }
        return builder.toString();
    }
}

class Solution2 {
    public void findPermutation(int n, int k)
    {
        int[] numbers = new int[n];
        int[] indices = new int[n];

        // initialise the numbers 1, 2, 3...
        for (int i = 0; i < n; i++)
            numbers[i] = i + 1;

        int divisor = 1;
        for (int place = 1; place <= n; place++)
        {
            if((k / divisor) == 0)
                break;  // all the remaining indices will be zero

            // compute the index at that place:
            indices[n-place] = (k / divisor) % place;
            divisor *= place;
        }

        // print out the indices:
        // System.out.println(Arrays.toString(indices));

        // permute the numbers array according to the indices:
        for (int i = 0; i < n; i++)
        {
            int index = indices[i] + i;

            // take the element at index and place it at i, moving the rest up
            if(index != i)
            {
                int temp = numbers[index];
                for(int j = index; j > i; j--)
                    numbers[j] = numbers[j-1];
                numbers[i] = temp;
            }
        }

        // print out the permutation:
        System.out.println(Arrays.toString(numbers));
    }
}

public class PermutationSequence {
}
