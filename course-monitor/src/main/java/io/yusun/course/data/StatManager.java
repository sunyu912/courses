package io.yusun.course.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class StatManager {
	
	private Map<String, Set<String>> statMap = new HashMap<String, Set<String>>();
	private List<String> optionList = new ArrayList<String>(){{
		add("A");
		add("B");
		add("C");
		add("D");
		add("E");
		add("F");
		add("G");
		add("H");
		add("I");
	}};
	
	private StatManager() {
		
	}
	
	private static StatManager instance;
	
	public static StatManager get() {
		if (instance == null) {
			instance = new StatManager();
		}
		return instance;
	}
	
	public void resetMap() {
		statMap = new HashMap<String, Set<String>>();
	}
	
	public void addSubmission(String userId, Set<String> answers) {
		statMap.put(userId, answers);
	}
	
	public int getTotalSubmission() {
		if (statMap.keySet() != null) {
			return statMap.keySet().size();
		} else {
			return 0;
		}
	}
	
	public Map<String, Integer> getStatMap() {
		Map<String, Integer> countMap = new HashMap<String, Integer>();
		for(String option : optionList) {
			countMap.put(option, 0);
		}
		
		for(Set<String> answerList : statMap.values()) {
			for(String answer : answerList) {
				if (optionList.contains(answer)) {
					Integer count = countMap.get(answer);
					if (count == null) {
						count = 0;
					}
					count++;
					countMap.put(answer, count);
				}
			}
		}
		
		Map<String, Integer> treeMap = new TreeMap<String, Integer>(countMap);
		return treeMap;
	}
}
