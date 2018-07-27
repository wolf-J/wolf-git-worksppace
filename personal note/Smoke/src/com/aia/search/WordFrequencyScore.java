package com.aia.search;

import java.util.List;

public class WordFrequencyScore implements Score{

	@Override
	public Float score(Doc doc, List<String> words) {
		// TODO Auto-generated method stub
		return Float.valueOf(doc.getFrequence());
	}

	
}
