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

	// Common Elements
	public static void commonElements(int[] arr1, int[] arr2) {
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();

		for (int i = 0; i < arr1.length; i++) {
			if (map.containsKey(arr1[i])) {
				map.put(arr1[i], map.get(arr1[i] + 1));
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

	public static void main(String[] args) throws Exception {
		maxFrequency("test string");
	}

}