package wenyu.learning.Others;

/**
 * Created by Wenyu on 12/19/16.
 *
 * There are N gas stations along a circular route, where the amount of gas at station i is gas[i].
 * You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from station i to its next
 * station (i+1). You begin the journey with an empty tank at one of the gas stations.
 * Return the starting gas station's index if you can travel around the circuit once, otherwise return -1.
 *
 *
 * Solution:
 * To solve this problem, we need to understand and use the following 2 facts:
 *    1) if the sum of gas >= the sum of cost, then the circle can be completed.
 *    2) if A can not reach C in a the sequence of A-->B-->C, then B can not make it either.
 *
 * Logic:
 *    1) Go through every item from 0 to length-1, mark start as 0
 *    2) If failed at k to k+1, which means no node between start and k can reach k+1. Mark k+1 as new start
 *    3) At last, got the start point.
 *    4) And check if condition 1 meet.
 *          4a) if condition 1 meet, which means at least there is one point that can be a start
 *          4b) if condition 2 not meet, which means no point in the circle can be a start
 */
public class GasStationProblem {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int sumRemaining = 0; // track current remaining
        int total = 0; // track total remaining
        int start = 0;

        for (int i = 0; i < gas.length; i++) {
            int remaining = gas[i] - cost[i];

            //if sum remaining of (i-1) >= 0, continue
            if (sumRemaining >= 0) {
                sumRemaining += remaining;
                //otherwise, reset start index to be current
            } else {
                sumRemaining = remaining;
                start = i;
            }
            total += remaining;
        }

        if (total >= 0){
            return start;
        }else{
            return -1;
        }
    }
}
