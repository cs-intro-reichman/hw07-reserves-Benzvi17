

public class HashTagTokenizer {

	public static void main(String[] args) {

		String hashTag = args[0];
		String []dictionary = readDictionary("dictionary.txt");
		breakHashTag(hashTag, dictionary);
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
		in.close();
		return dictionary;
	}

	public static boolean existInDictionary(String word, String []dictionary) {
		word = word.toLowerCase();
		for (int i = 0; i < dictionary.length; i++) {
			if (word.equals(dictionary[i].toLowerCase())) {
				return true;
			}
		}
		return false;
	}

	public static void breakHashTag(String hashtag, String[] dictionary) {

		// Base case: do nothing (return) if hashtag is an empty string.
        if (hashtag.isEmpty()) {
            return;
        }
 
        int N = hashtag.length();

        for (int i = 1; i <= N; i++) {
		if (existInDictionary(hashtag.substring(0, i).toLowerCase(), dictionary)) {
			System.out.print(hashtag.substring(0, i));
			breakHashTag(hashtag.substring(i), dictionary);

		return;
		
 	       }
    	}
	System.out.println(hashtag.substring(0, 1));
	breakHashTag(hashtag.substring(1), dictionary);
	}
}
