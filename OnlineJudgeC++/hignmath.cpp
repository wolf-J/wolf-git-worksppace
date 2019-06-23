/*
 * hignmath.cpp
 *
 *  Created on: 2019Äê1ÔÂ2ÈÕ
 *      Author: wolf
 */
#include<iostream>
using namespace std;
int hignmath(int argc, char **argv) {
	int n, k;
	cin >> n >> k;
	int *a, *b;
	a = new int[n];
	b = new int[n];
	for (int i = 0; i < n; ++i) {
		cin >> a[i];
	}
	for (int i = 0; i < n; ++i) {
		cin >> b[i];
	}

	int l = 0;
	int m = 0;
	for (int i = 0; i < n - k + 1; ++i) {
		m = 0;
		for (int j = 0; j < k; ++j) {
			if (b[i + j] == 0)
				m += a[i + j];
		}
		l = m > l ? m : l;
	}

	for (int i = 0; i < n; ++i) {
		if (b[i] == 1)
			l += a[i];
	}
	cout << l;
	return 0;
}

