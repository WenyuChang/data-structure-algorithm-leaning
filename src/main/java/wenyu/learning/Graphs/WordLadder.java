package wenyu.learning.Graphs;

import java.util.*;

/**
 * Created by Wenyu on 12/14/16.
 * <p>
 * Problem1:
 * Given two words (beginWord and endWord), and a dictionary's word list, find the length of
 * shortest transformation sequence from beginWord to endWord, such that:
 * Only one letter can be changed at a time Each intermediate word must exist in the word list
 * For example,
 * Given:
 * beginWord = "hit"
 * endWord = "cog"
 * wordList = ["hot","dot","dog","lot","log"]
 * As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
 * return its length 5.
 * Solution:
 * using unwighted shortest path logic
 * <p>
 * <p>
 * <p>
 * Problem 2: Same as above, just print all possible shortest transformation
 * Return
 * [
 * ["hit","hot","dot","dog","cog"],
 * ["hit","hot","lot","log","cog"]
 * ]
 * Solution:
 * The idea is the same. To track the actual ladder, we need to add a pointer that
 * points to the previous node in the WordNode class. In addition, the used word can not directly removed from
 * the dictionary. The used word is only removed when steps change.
 */

public class WordLadder {
    public class Problem1 {
        class WordNode {
            String word;
            int numSteps;

            public WordNode(String word, int numSteps) {
                this.word = word;
                this.numSteps = numSteps;
            }
        }

        public int ladderLength(String beginWord, String endWord, Set<String> wordDict) {
            LinkedList<WordNode> queue = new LinkedList<WordNode>();
            queue.add(new WordNode(beginWord, 1));

            wordDict.add(endWord);

            while (!queue.isEmpty()) {
                WordNode top = queue.pop();
                String word = top.word;

                if (word.equals(endWord)) {
                    return top.numSteps;
                }

                char[] arr = word.toCharArray();
                for (int i = 0; i < arr.length; i++) {
                    for (char c = 'a'; c <= 'z'; c++) {
                        char temp = arr[i];
                        if (arr[i] != c) {
                            arr[i] = c;
                        }

                        String newWord = new String(arr);
                        if (wordDict.contains(newWord)) {
                            queue.add(new WordNode(newWord, top.numSteps + 1));
                            wordDict.remove(newWord);
                        }

                        arr[i] = temp;
                    }
                }
            }
            return 0;
        }
    }

    public class Problem2 {
        class WordNode {
            String word;
            int numSteps;
            WordNode pre;

            public WordNode(String word, int numSteps, WordNode pre) {
                this.word = word;
                this.numSteps = numSteps;
                this.pre = pre;
            }
        }

        public List<List<String>> findLadders(String start, String end, Set<String> dict) {
            List<List<String>> result = new ArrayList<List<String>>();

            LinkedList<WordNode> queue = new LinkedList<WordNode>();
            queue.add(new WordNode(start, 1, null));

            dict.add(end);

            int minStep = 0;

            HashSet<String> visited = new HashSet<String>();
            HashSet<String> unvisited = new HashSet<String>();
            unvisited.addAll(dict);

            int preNumSteps = 0;

            while (!queue.isEmpty()) {
                WordNode top = queue.remove();
                String word = top.word;
                int currNumSteps = top.numSteps;

                if (word.equals(end)) {
                    if (minStep == 0) {
                        minStep = top.numSteps;
                    }

                    if (top.numSteps == minStep && minStep != 0) {
                        //nothing
                        ArrayList<String> t = new ArrayList<String>();
                        t.add(top.word);
                        while (top.pre != null) {
                            t.add(0, top.pre.word);
                            top = top.pre;
                        }
                        result.add(t);
                        continue;
                    }

                }

                if (preNumSteps < currNumSteps) {
                    unvisited.removeAll(visited);
                }

                preNumSteps = currNumSteps;

                char[] arr = word.toCharArray();
                for (int i = 0; i < arr.length; i++) {
                    for (char c = 'a'; c <= 'z'; c++) {
                        char temp = arr[i];
                        if (arr[i] != c) {
                            arr[i] = c;
                        }

                        String newWord = new String(arr);
                        if (unvisited.contains(newWord)) {
                            queue.add(new WordNode(newWord, top.numSteps + 1, top));
                            visited.add(newWord);
                        }

                        arr[i] = temp;
                    }
                }


            }

            return result;
        }
    }
}
