
public class SpellChecker {


	public static void main(String[] args) {
		String word = args[0];
		int threshold = Integer.parseInt(args[1]);
		String[] dictionary = readDictionary("dictionary.txt");
		String correction = spellChecker(word, threshold, dictionary);
		System.out.println(correction);
	}

	public static String tail(String str) {
		if (str.length() <= 1) {
			return "";
		}
		return str.substring(1);
	}

	public static int levenshtein(String word1, String word2) {
		word1 = word1.toLowerCase();
		word2 = word2.toLowerCase();

		if(word1.isEmpty()){
			return word2.length();
		}
		if (word2.isEmpty()) {
			return word1.length();
		}
		int replace_cost = word1.charAt(0) == word2.charAt(0) ? 0 : 1;
		int removal_cost = levenshtein(tail(word1), word2)+1;
		int insertion_cost = levenshtein(word1, tail(word2))+1;
		int afterRemoval_cost = levenshtein(tail(word1), tail(word2));

		return Math.min(replace_cost + afterRemoval_cost, Math.min(removal_cost, insertion_cost));
	}

	public static String[] readDictionary(String fileName) {
		String[] dictionary = new String[3000];

		In in = new In(fileName);

		int index = 0;
			while (!in.isEmpty() && index < dictionary.length) {
				String word = in.readString();
				dictionary[index] = word;
				index++;
			}
		return dictionary;
	}

	public static String spellChecker(String word, int threshold, String[] dictionary) {
		word = word.toLowerCase();
		String closesWord = word;
		int min_cost = Integer.MAX_VALUE;

		for (int i = 0; i < dictionary.length; i++) {
			String iWord = dictionary[i];
			int distance = levenshtein(word, iWord.toLowerCase());
			if (distance < min_cost) {
				min_cost = distance;
				closesWord = iWord;
			}
		}
		if (min_cost > threshold) {
			return word;
		}else {
			return closesWord;
			}
		}
	}

	


