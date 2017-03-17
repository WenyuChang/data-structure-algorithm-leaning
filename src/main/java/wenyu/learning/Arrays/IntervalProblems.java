package wenyu.learning.Arrays;

import java.util.*;

/*
 * Problem 1: You have N pairs of intervals, say integers. To find if has overlapping
 * Problem 2: Given a set of time intervals in any order, merge all overlapping intervals into one and output 
 * 			  the result which should have only mutually exclusive intervals.
 * Problem 3: Intert new interval to a sorted interval list and merge if needed.
 */

class Interval {
	int start;
	int end;
	public Interval(int start, int end) {
		if(end>=start) {
			this.start = start;
			this.end = end;
		} else {
			this.start = end;
			this.end = start;
		}
	}
}

class HasOverlap {
	/*
	 * Logic: O(nlogn)
	 * 	1. sort interval array according to the start value. O(nlogn)
	 *  2. scan array to see if its start smaller than maxEnd. O(n)
	 */
	public static boolean ifHasOverlap(Interval[] intervals) {
		Interval[] tmpIntervals = intervals.clone();
		Arrays.sort(tmpIntervals, new Comparator<Interval>() {
			public int compare(Interval o1, Interval o2) {
				if(o1.start>o2.start) {
					return 1;
				} else if(o1.start < o2.start) {
					return -1;
				} else {
					return 0;
				}
			}
		});
		
		int maxEnd = tmpIntervals[0].end;
		for(int i=1;i<tmpIntervals.length;i++) {
			if(tmpIntervals[i].start<maxEnd) {
				return true; // find overlapping
			} 
			maxEnd = tmpIntervals[i].end;
		}
		return false;
	}
}

class MergeInterval {
	public static Interval[] mergeInterval(Interval[] intervals) {
		/*
		 * Logic:
		 * 	1. Sort the intervals based on increasing order of starting time.
		 *  2. Push the first interval on to a stack.
		 *  3. For each interval do the following
		 *  	a. If the current interval does not overlap with the stack top, push it.
		 *  	b. If the current interval overlaps with stack top and ending time of current interval is 
		 *  	   more than that of stack top, update stack top with the ending time of current interval.
		 *  4. At the end stack contains the merged intervals.
		 */
		
		Interval[] tmpIntervals = intervals.clone();
		Arrays.sort(tmpIntervals, new Comparator<Interval>() {
			public int compare(Interval o1, Interval o2) {
				if(o1.start>o2.start) {
					return 1;
				} else if(o1.start < o2.start) {
					return -1;
				} else {
					return 0;
				}
			}
		});
		
		Interval[] result = new Interval[intervals.length];
		int idx = 0;
		result[idx] = tmpIntervals[0];
		
		for(int i=0;i<tmpIntervals.length;i++) {
			if(tmpIntervals[i].start<=result[idx].end) {
				result[idx].end = (tmpIntervals[i].end>result[idx].end)?tmpIntervals[i].end:result[idx].end;
			} else {
				result[++idx] = tmpIntervals[i];
			}
		}
		
		result = Arrays.copyOf(result, idx+1);
		for(int i=0;i<result.length;i++) {
			System.out.print("["+result[i].start+","+result[i].end+"] ");
		}
		return result;
	}
}

class InsertIntervalAndMerge {
	public class Interval {
		int start;
		int end;

		Interval() {
			start = 0;
			end = 0;
		}

		Interval(int s, int e) {
			start = s;
			end = e;
		}
	}

	public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
		if (intervals.size() == 0) {
			intervals.add(newInterval);
			return intervals;
		}

		boolean added = false;
		Stack<Interval> stack = new Stack<Interval>();

		// Spcial case: new interval should be added to the top
		if (newInterval.start <= intervals.get(0).start) {
			stack.add(newInterval);
			added = true;
		}

		for (Interval interval : intervals) {
			if (stack.isEmpty()) {
				stack.push(interval);
				continue;
			}

			Interval peek = stack.peek();
			if (interval.start >= newInterval.start) {
				if (peek.end >= newInterval.start && newInterval.end > peek.end) {
					peek.end = newInterval.end;
				} else if (newInterval.end <= peek.end) {

				} else {
					stack.push(newInterval);
				}
				added = true;
			}

			peek = stack.peek();
			if (peek.end >= interval.start && interval.end > peek.end) {
				peek.end = interval.end;
			} else if (interval.end <= peek.end) {

			} else {
				stack.push(interval);
			}
		}

		if (!added) {
			// Special case: new interval should be added to the end
			Interval peek = stack.peek();
			if (peek.end >= newInterval.start && newInterval.end > peek.end) {
				peek.end = newInterval.end;
			} else if (newInterval.end <= peek.end) {

			} else {
				stack.push(newInterval);
			}
		}

		List<Interval> result = new ArrayList<Interval>();
		Iterator<Interval> it = stack.iterator();
		while (it.hasNext()) {
			result.add(it.next());
		}

		return result;
	}
}

public class IntervalProblems {
	public static void main(String[] args) {
		Interval[] intervals = {new Interval(1,2),new Interval(3,5),new Interval(6,10),new Interval(10,12),new Interval(8,9)};
		if(HasOverlap.ifHasOverlap(intervals)) {
			System.out.println("Find overlapping...");
		} else {
			System.out.println("No overlapping...");
		}
		
		MergeInterval.mergeInterval(intervals);
	}

}
