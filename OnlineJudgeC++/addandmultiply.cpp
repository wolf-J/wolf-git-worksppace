/*
 * addandmultiply.cpp
 *
 *  Created on: 2019Äê1ÔÂ2ÈÕ
 *      Author: wolf
 */

#include<iostream>
using namespace std;

int addandmultiply(int argc, char **argv) {
	int a, b, c;
	cin >> a >> b >> c;

	int l = a + b * c;
	l = l > (a + b) * c ? l : (a + b) * c;
	l = l > a * b + c ? l : a * b + c;
	l = l > a * (b + c) ? l : a * (b + c);
	l = l > a + b + c ? l : a + b + c;
	l = l > a * b * c ? l : a * b * c;
	cout << l;
}

