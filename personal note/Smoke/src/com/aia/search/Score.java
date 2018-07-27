package com.aia.search;

import java.util.List;

@FunctionalInterface
public interface Score {

	public Float score(Doc doc, List<String> words);
}
