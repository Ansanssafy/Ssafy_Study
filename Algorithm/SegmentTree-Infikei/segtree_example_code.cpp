#include <iostream>
#include <cmath>
#include <algorithm>
using namespace std;
using ll = long long;

int n;
ll arr[1000001];
ll seg[4000001];

ll seg_init(int node, int s, int e) {
    if (s == e) return seg[node] = arr[s];

    int mid = (s + e) >> 1;
    ll left_val = seg_init(node * 2, s, mid);
    ll right_val = seg_init(node * 2 + 1, mid + 1, e);
    return seg[node] = left_val + right_val;
}

void seg_update(int qidx, int node = 1, int s = 1, int e = n) {
    if (qidx < s || e < qidx) return;
    if (s == e) {
        seg[node] = arr[s];
        return;
    }

    int mid = (s + e) >> 1;
    seg_update(qidx, node * 2, s, mid);
    seg_update(qidx, node * 2 + 1, mid + 1, e);
    seg[node] = seg[node * 2] + seg[node * 2 + 1];
}

ll seg_query(int qs, int qe, int node = 1, int s = 1, int e = n) {
    if (qe < s || e < qs) return 0;
    if (qs <= s && e <= qe) return seg[node];

    int mid = (s + e) >> 1;
    ll left_val = seg_query(qs, qe, node * 2, s, mid);
    ll right_val = seg_query(qs, qe, node * 2 + 1, mid + 1, e);
    return left_val + right_val;
}

void seg_print() {
    int k = n - 1;
    int logn = 0;

    while (k > 0) {
        k >>= 1; // k /= 2 와 같다.
        logn++;
    }

    int powpow = (1 << (logn + 1)); // pow(2, (logn + 1)) 과 같다.

    cout << "[";
    for (int i = 0; i < powpow; i++) {
        cout << seg[i] << ", ";
    }
    cout << seg[powpow] << "]\n";
}

int main() {
    cin >> n;

    for (int i = 1; i <= n; i++) {
        cin >> arr[i];
    }

    seg_init(1, 1, n);

    seg_print();
    cout << "2부터 3까지 합 : " << seg_query(2, 3) << '\n';

    arr[3] = 8;
    seg_update(3);

    seg_print();
    cout << "2부터 3까지 합 : " << seg_query(2, 3) << '\n';

    return 0;
}
