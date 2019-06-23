/*
#include<iostream>
using namespace std;
int main(){
    int n,k,tempInt;
    int s;
    cin>>n>>k;
    int a[n][2],m[k][2];
    for(int i=0;i<n;i++){
        cin>>a[i][0];
        a[i][1]=i+1;
    }
    for(int i=0;i<n-1;i++){
        for(int j=0;j<n-1-i;j++){
            if(a[j][0]>a[j+1][0]){
                tempInt=a[j][0];
                a[j][0]=a[j+1][0];
                a[j+1][0]=tempInt;
                tempInt=a[j][1];
                a[j][1]=a[j+1][1];
                a[j+1][1]=tempInt;
            }
        }
    }
    //****************************进行操作以及更新s和m参数*************************************
    s=0;
    int minCount,maxCount,opNum;
    while(s<k&&(a[n-1][0]-a[0][0])>=2){
        minCount=1;
        while(minCount<n&&a[minCount][0]==a[0][0])minCount++;
        maxCount=1;
        while(maxCount<n&&a[n-1-maxCount][0]==a[n-1][0])maxCount++;
        opNum=(maxCount<minCount)?maxCount:minCount;
        if(s+opNum>k)break;
        //记录操作
        for(int j=0;j<opNum;j++){
            (a[minCount-1-j][0])++;
            (a[n-maxCount+j][0])--;
            m[s+j][0]=a[n-maxCount+j][1];
            m[s+j][1]=a[minCount-1-j][1];
        }
        s+=opNum;
    }
    cout<<(a[n-1][0]-a[0][0])<<' '<<s<<endl;
    for(int j=0;j<s;j++){
        cout<<m[j][0]<<' '<<m[j][1]<<endl;
    }
    return 0;
}


 * tower.cpp
 *
 *  Created on: 2019年1月4日
 *      Author: Administrator


#include<iostream>
using namespace std;
int main(int argc, char **argv) {
	int n, k;
	cin >> n >> k;
	int * a;
	a = new int[n];
	int *b;
	b = new int[2 * k];
	for (int i = 0; i < n; i++) {
		cin >> a[i];
	}

	int s, m = 0;

	int min = 0, max = 0;

	for (m = 0; m < k; ++m) {
		for (int i = 0; i < n; ++i) {
			if (a[i] < a[min])
				min = i;
			if (a[i] > a[max])
				max = i;
		}
		if (a[max] - a[min] > 1) {
			a[max] -= 1;
			a[min] += 1;
			b[2 * m] = max;
			b[2 * m + 1] = min;
			cout << max << min << endl;
		} else {
			s = max - min;
			break;
		}
	}
	cout << s << m;
	for (int i = 0; i < 2 * m; ++i) {
		cout << b[i];
	}
	return 0;
}

*/
