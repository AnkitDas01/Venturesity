import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

class WebsiteAnalyzer {

	// Maintaining a map of page url and access counts
	private static Map<String, Integer> pageMap = new HashMap<String, Integer>();

	public static void reportPageAccess(String pageUrl) {
		// Since this operation will be called very frequently, just
		// incrementing the page access count here, no extra computation
		Integer count = pageMap.get(pageUrl);
		if (count == null)
			pageMap.put(pageUrl, 1);
		else
			pageMap.put(pageUrl, count + 1);
	}

	// This API should be synchronized
	public static List<String> getTopNPages(int n) {
		// Since this operation will be called less frequently, doing all
		// computation here
		List<String> l = new ArrayList<>();

		// Getting the values of the map and sorting in descending order.
		List<Integer> valuelist = new ArrayList<Integer>(pageMap.values());
		Comparator comparator = Collections.reverseOrder();
		Collections.sort(valuelist, comparator);
		//
		for (Integer i : valuelist) {
			// Iterating over the page map to find which keys have values equal
			// to the max value of above list
			for (Entry<String, Integer> entry : pageMap.entrySet()) {
				if (entry.getValue().equals(i) && l.size() < n) {
					l.add(entry.getKey());
				}
			}
		}

		return l;
	}
}