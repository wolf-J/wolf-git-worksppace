package com.aia.search;

public interface Searcher {
	Hits search(String keyword);
	Hits search(String keyword, SearchMode searchMode);
	Hits search(String keyword, int page);
	Hits search(String keyword, SearchMode searchMode, int page);
	
}
