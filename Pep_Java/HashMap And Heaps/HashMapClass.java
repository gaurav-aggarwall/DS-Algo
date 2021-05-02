import java.util.*;

public class HashMapClass {

	// Max Frequency Character
	public static void maxFrequency(String str) {
		HashMap<Character, Integer> map = new HashMap<Character, Integer>();
		int maxCount = Integer.MIN_VALUE;
		char ans = str.charAt(0);

		for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);

			if (map.get(ch) == null) {
				map.put(ch, 1);
			} else {
				map.put(ch, map.get(ch) + 1);
			}

			if (map.get(ch) > maxCount) {
				maxCount = map.get(ch);
				ans = ch;
			}
		}

		System.out.println(ans);
	}

	// Common Elements Without repeat
	public static void commonElementsWithoutRepeat(int[] arr1, int[] arr2) {
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();

		for (int i = 0; i < arr1.length; i++) {
			if (map.containsKey(arr1[i])) {
				map.put(arr1[i], map.get(arr1[i]) + 1);
			} else {
				map.put(arr1[i], 1);
			}
		}

		for (int i = 0; i < arr2.length; i++) {
			if (map.containsKey(arr2[i])) {
				System.out.println(arr2[i]);
				map.remove(arr2[i]);
			}
		}
	}

	// Common Elements
	public static void commonElements(int[] arr1, int[] arr2) {
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();

		for (int i = 0; i < arr1.length; i++) {
			if (map.containsKey(arr1[i])) {
				map.put(arr1[i], map.get(arr1[i]) + 1);
			} else {
				map.put(arr1[i], 1);
			}
		}

		for (int i = 0; i < arr2.length; i++) {
			if (map.containsKey(arr2[i]) && map.get(arr2[i]) > 0) {
				System.out.println(arr2[i]);
				map.put(arr2[i], map.get(arr2[i]) - 1);
			}
		}
	}

	// Longest Consecutive Sequence
	// Create Boolean Hashmap for array
	// Now traverse and find ma consecutive count and maintain start for it
	// Note: Elements were given to be in range of 0 to 15 inclusive
	public static void lqs(int[] arr, int n) {
		HashMap<Integer, Boolean> map = new HashMap<Integer, Boolean>();

		for (int i = 0; i <= 15; i++) {
			map.put(i, false);
		}

		for (int i = 0; i < n; i++) {
			map.put(arr[i], true);
		}

		int maxCount = 0;
		int count = 0;
		int maxStart = 0;
		int start = Integer.MAX_VALUE;

		for (int i = 0; i <= 15; i++) {
			if (map.get(i) == true) {
				count++;
				start = Math.min(start, i);
			} else {
				if (count > maxCount) {
					maxCount = count;
					maxStart = start;
				}
				start = Integer.MAX_VALUE;
				count = 0;
			}
		}

		for (int i = 0; i < maxCount; i++) {
			System.out.println(maxStart + i);
		}
	}

	public static void main(String[] args) throws Exception {
		maxFrequency("test string");
	}

}