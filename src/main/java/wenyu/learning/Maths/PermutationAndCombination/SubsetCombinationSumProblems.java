package wenyu.learning.Maths.PermutationAndCombination;

import java.util.*;

/**
 * Created by Wenyu on 11/29/16.
 *
 * Problem 1: Given a set of candidate numbers (C) and a target number (T), find all unique combinations in C where
 * the candidate numbers sums to T. The same repeated number may be chosen from C unlimited number of times.
 *
 * For example, given candidate set [2, 3, 6, 7] and target 7,
 * A solution set is:
 *    [
 *    [7],
 *    [2, 2, 3]
 *    ]
 */
class Problem1 {
    private void helper(int[] candidates, int currIdx, int target, List<List<Integer>> result, List<Integer> currSeq) {
        for (int i=currIdx; i<candidates.length; i++) {
            currSeq.add(candidates[i]);
            if (target == candidates[i]) {
                List<Integer> currRes = new ArrayList<Integer>();
                for (int item : currSeq) {
                    currRes.add(item);
                }
                result.add(currRes);
            } else if (target > candidates[i]) {
                helper(candidates, i, target-candidates[i], result, currSeq);
            } else {
                currSeq.remove(currSeq.size()-1);
                return;
            }
            currSeq.remove(currSeq.size()-1);
        }
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);

        List<List<Integer>> result = new ArrayList<List<Integer>>();
        List<Integer> currSeq = new ArrayList<Integer>();
        helper(candidates, 0, target, result, currSeq);

        return result;
    }
}

class Problem2 {
    private void choose(int[] candidates, int target, int curr, int count, List<List<Integer>> result, Stack<Integer> stack) {
        if (stack.size() == count && target == 0) {
            Iterator<Integer> it = stack.iterator();
            ArrayList<Integer> currSeq = new ArrayList<Integer>();
            while (it.hasNext()) {
                currSeq.add(it.next());
            }
            result.add(currSeq);
        } else if (stack.size() < count && target > 0) {
            for (int i=curr; i<candidates.length; i++) {
                if (target < candidates[i]) {
                    return;
                }
                if (i > 0 && candidates[i] == candidates[i-1] && i != curr) {
                    continue;
                }

                stack.push(candidates[i]);
                choose(candidates, target-candidates[i], i+1, count, result, stack);
                stack.pop();
            }
        } else {
            return;
        }
    }

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);

        List<List<Integer>> result = new ArrayList<List<Integer>>();

        for (int i=1; i<=candidates.length; i++) {
            choose(candidates, target, 0, i, result, new Stack<Integer>());
        }

        return result;
    }
}

public class SubsetCombinationSumProblems {

}
