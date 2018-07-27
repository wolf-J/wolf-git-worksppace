package com.aia.search;

import java.util.Set;

public enum SearchMode {
	INTERSECTION, UNION;
	
	
	public static void interfaction(Set<Doc> existingDocs, Set<Doc> increasedDocs)
	{
		existingDocs.parallelStream().forEach(existenDoc -> {
			if(!increasedDocs.contains(existingDocs))
			{
				existingDocs.remove(existenDoc);
				return;
			}
			for(Doc increasedDoc : increasedDocs)
			{
				if(existenDoc.getDocId()==increasedDoc.getDocId())
				{
					existenDoc.merge(increasedDoc);
					break;
				}
			}
		});
	}
	
	public static void union(Set<Doc> existingDocs, Set<Doc> increasedDocs)
	{
		increasedDocs.parallelStream().forEach(increasedDoc -> {
			if(!existingDocs.contains(increasedDoc))
			{
				existingDocs.add(increasedDoc);
			}
		});
	}
	
}
