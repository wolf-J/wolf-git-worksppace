#include<iostream>
#include<vector>
using namespace std;
    void solve(int n, int m, long long k) {
        vector<char> x;//
        while (n && m) {
            //每次迭代问题规模缩减一个单位
            ////排列组合:假设当前序列首字符为a，剩下n-1个a放在剩下n - 1 +m 个位置共有的可能数
            long long count = 1;
            for (int i = 0; i < n - 1; i++) {//求组合数
                count *= n - 1 + m - i;
                count /= (i + 1);
                if (count > k)break;//防止越界。count>k就可以退出计算了
            }
            if (k <= count) {//如果k小于等于count，则表明首字符的确应为a
                x.push_back('a');
                n--;//问题缩减为 n-1个a和m个z 中找第k大
            }
            else {
                x.push_back('z');
                m--;//问题缩减为 n-1个a和m个z 中找第k-count大
                k -= count;
            }
        }
        //循环结束后，剩余子序列只存在"aa..aaa" 或 "zz..zzz"1种情况
        if (k != 1) {//
            cout << -1;
            return;
        }
        while (n--)x.push_back('a');
        while (m--)x.push_back('z');
        for (int i = 0; i < x.size(); i++) {
            cout << x[i];
        }
    }
int stringAZ() {
    int n, m;
    long long k;
    while (cin >> n >> m >> k) {
        solve(n, m, k);
    }
    return 0;
}
