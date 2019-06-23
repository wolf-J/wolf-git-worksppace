/*
 * eluosi.cpp
 *
 *  Created on: 2019Äê1ÔÂ2ÈÕ
 *      Author: wolf
 */

#include <iostream>
using namespace std;

int eluosi() {
	int n, m;
	cin >> n >> m;
	int *a, *b;
	a = new int[m];
	b = new int[n];
	for (int i = 0; i < n; ++i) {
		b[i] = 0;
	}
	for (int i = 0; i < m; ++i) {
		cin >> a[i];
		b[a[i]-1] += 1;
	}
	int l = b[0];
	for (int i = 0; i < n; ++i) {
		if (b[i] < l)
			l = b[i];
	}
	cout << l;
	return 0;

}

