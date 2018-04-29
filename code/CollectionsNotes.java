import java.util.*;                                         // Everything is in java.util package
public class CollectionsNotes {
	public static person john = new person("John", "Doe", 21);
	public static person jane = new person("Jane", "Doe", 21);
	public static void main(String [] args) { lists(); sets(); maps(); }
	public static void lists() {
		int[] scores = {1, 2, 3, 42, 69, 1337, 4516, 2018}; // Define + initialize array
		List<Integer> scoreList = new ArrayList<>();        // Construct ArrayList
		List<person> ppl = new ArrayList<person>();
		for (int score : scores) scoreList.add(score);      // Add elements
		ppl.add(john); ppl.add(jane);
		ppl.add(new person("Matt", "Smith", 22));
		ppl.add(new person("Josh", "Garrett", 21));
		Collections.shuffle(ppl);                           // Shuffle collection
		Collections.reverse(ppl);                           // Reverse collection
		Collections.sort(ppl);                              // Sort collection (low to high)
		scoreList.sort(new Comparator<Integer>() {          // Custom inline sort
			public int compare(Integer a, Integer b)  {
				return a % 2 - b % 2; } });                 // Even before odd
		System.out.println(Arrays.toString(scoreList.toArray()));
		System.out.println(Arrays.toString(ppl.toArray())); // Convert List to array, print array
		for (person dude : ppl) System.out.println(dude.fname); // For-each
		Iterator<person> it = ppl.iterator();               // Get iterator object
		while (it.hasNext()) {                              // Are there more objects to iterate?
			String lname = it.next().lname;                 // Get one object
			if (lname.charAt(0) == 'D') it.remove(); }      // Remove last object from .next();
		int idx = Arrays.binarySearch(scores, 42); }        // Get index of needle
	public static void sets() {
		Set<person> ppl = new HashSet<person>();            // Construct HashSet
		Set<person> ppl2 = new TreeSet<person>();            // Construct TreeSet (<T implements Comparable<T>)
		ppl.add(john);                                      // Add element
		if (ppl.contains(john))                             // Query element
			ppl.remove(john); }                             // Remove element
	public static void maps() {
		Map<person, Integer> scores = new HashMap<person, Integer>(); // Construct HashMap (O(1), uses hashing)
		Map<person, Integer> scores2 = new TreeMap<person, Integer>(); // Construct TreeMap (O(log n), <K implements Comparable<T>)
		scores.put(john, 1337);                             // Set key -> value
		if (scores.containsKey(john)) {                     // Query
			scores.put(john, scores.get(john) + 10);        // Increment (Retrieval, Set)
			scores.remove(john); }                          // Removal
		scores.getOrDefault(jane, 0);                       // Retrieval with default
		scores.putIfAbsent(jane, 0);                        // Set if null
		for (person dude : scores.keySet())                 // Iterate over keys
			System.out.println(dude.fname + ": " + scores.get(dude));
		scores.clear(); } }                                 // Remove all elements
class person implements Comparable<person> {
	public String fname; public String lname; public int age;
	public person(String _fname, String _lname, int _age) {
		fname = _fname; lname = _lname; age = _age; }
	public String toString() {                              // Override toString to make debugging easier
		return fname + " " + lname + " (" + age + ")"; }
	public int compareTo(person other) {                    // x<0: this<other | 0: this=other | x>0: this>other
		if (age != other.age) return Integer.compare(age, other.age); // Integer, Double, Long
		if (!lname.equals(other.lname)) return lname.compareTo(other.lname); // For String, use .equals/.compareTo
		return fname.compareTo(other.fname); } }