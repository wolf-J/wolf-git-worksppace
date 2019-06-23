/*
 * apple.cpp
 *
 *  Created on: 2019Äê1ÔÂ2ÈÕ
 *      Author: wolf
 */

#include<iostream>
using namespace std;
int apple(int argc, char **argv) {
	int n;
	cin >> n;
	int *a, *a1;
	a = new int[n];
	a1 = new int[n + 1];
	a1[1] = 0;
	for (int i = 0; i < n; ++i) {
		cin >> a[i];
		a1[i + 1] = a1[i] + a[i];
	}
	int m;
	cin >> m;
	int *q;
	q = new int[m];
	for (int i = 0; i < m; ++i) {
		cin >> q[i];
		for (int j = 0; j < n + 1; ++j) {
			if (q[i] > a1[j] && q[i] <= a1[j + 1]) {
				cout << j + 1 << endl;
				break;
			}
		}
	}
	return 0;

}




#include <iostream>
#include <vector>
using namespace std;

int apple()
{
    int n;
    cin >> n;
    vector<int> a(n,0);
    for (int i = 0;i != n; ++i)
    {
        cin >> a[i];
    }
    int m;
    cin >> m;
    vector<int> q(m,0);
    for (int i = 0; i != m;++i)
    {
        cin >> q[i];
    }
    vector<int> sum(n,0);
    vector<int> res(m,0);
    sum[0] = a[0];
    for (int i = 1; i != n;++i)
    {
        sum[i] = sum[i-1] + a[i];
    }
    for (int i = 0;i != m; ++i)
    {
        int fi= 0, la = n-1;
        while (fi < la)
        {
            int mid = (fi + la)>>1;
            if (sum[mid] < q[i])
            {
                fi = mid + 1;
            }
            else
            {
                la = mid;
            }
        }
        res[i] = la + 1;
    }
    for (int i = 0; i != m; ++i)
    {
        cout << res[i] << endl;
    }
    return 0;
}
