package wenyu.learning.BinarySearch;

public class FindOccurrences {
	public static class FO_Rec {
		public static int findLast(int[] arr, int low, int high, int k) {
			if (high >= low) {
				int mid = low + (high - low) / 2;
				if ((mid == arr.length - 1 || k < arr[mid + 1]) && arr[mid] == k) {
					return mid;
				} else if (k < arr[mid]) {
					return findLast(arr, low, (mid - 1), k);
				} else {
					return findLast(arr, (mid + 1), high, k);
				}
			}
			return -1;
		}

		public static int findFirst(int[] arr, int low, int high, int k) {
			if (low <= high) {
				int mid = low + (high - low) / 2;
				if (arr[mid] == k && (mid == 0 || k > arr[mid - 1])) {
					return mid;
				} else if (k > arr[mid]) {
					return findFirst(arr, mid + 1, high, k);
				} else {
					return findFirst(arr, low, mid - 1, k);
				}
			}
			return -1;
		}

		public static int findOccurrences(int[] array, int k) {
			int first = findFirst(array, 0, array.length - 1, k);

			if (first == -1) {
				return first;
			}

			int last = findLast(array, first, array.length - 1, k);

			return last - first + 1;
		}
	}
	
	
	public static class FO_NonRec {
		private static int findFirst(int[] arr, int k) {
			int start = 0;
			int end = arr.length-1;
			
			while (start <= end) {
				int mid = start + (end - start)/2;
				if(arr[mid] > k) {
					end = mid - 1;
				} else if(arr[mid] < k) {
					start = mid + 1;
				} else if(mid > 0 && arr[mid-1]==k) {
					end = mid-1;
				} else {
					return mid;
				}
			}
			return -1;
		}
		
		private static int findLast(int[] arr, int k) {
			int start = 0;
			int end = arr.length-1;
			
			while (start <= end) {
				int mid = start + (end - start)/2;
				if(arr[mid] > k) {
					end = mid - 1;
				} else if(arr[mid] < k) {
					start = mid + 1;
				} else if(mid < arr.length-1 && arr[mid+1]==k) {
					start = mid+1;
				} else {
					return mid;
				}
			}
			return -1;
		}
		
		public static int findOccurrences(int[] array, int k) {
			int first = findFirst(array, k);

			if (first == -1) {
				return first;
			}

			int last = findLast(array, k);

			return last - first + 1;
		}
	}
	

	public static void main(String[] args) {
		int[] array = { 1, 1, 1, 1, 2, 2, 2, 3, 3, 3, 5, 5, 5, 5, 5, 5, 6, 7, 8, 8, 8, 8, 9, 9, 9, 9, 9, 9, 9, 9, 9 };
		int occ1 = FindOccurrences.FO_Rec.findOccurrences(array, 9);
		System.out.println("Occurrences is " + occ1);
		
		int occ2 = FindOccurrences.FO_NonRec.findOccurrences(array, 9);
		System.out.println("Occurrences is " + occ2);
	}

}
