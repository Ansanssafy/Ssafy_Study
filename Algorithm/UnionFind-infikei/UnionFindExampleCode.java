import java.io.IOException;

public class UnionFindExampleCode {

    static int[] parents;

    static void initParents(int n) {
        parents = new int[n + 1];

        for (int u = 1; u <= n; u++) {
            parents[u] = -1;
        }
    }

    static int getParentOf(int u) {
        if (parents[u] < 0) return u;
        return parents[u] = getParentOf(parents[u]);
    }

    static void unionParents(int u1, int u2) {
        int pu1 = getParentOf(u1);
        int pu2 = getParentOf(u2);

        if (pu1 != pu2) {
            if (parents[pu1] <= parents[pu2]) {
                parents[pu1] += parents[pu2];
                parents[pu2] = pu1;
            } else {
                parents[pu2] += parents[pu1];
                parents[pu1] = pu2;
            }
        }
    }

    static void printParents() {
        System.out.print("[" + parents[1]);

        for (int u = 2; u < parents.length; u++) {
            System.out.print(", " + parents[u]);
        }

        System.out.println("]");
    }

    public static void main(String[] args) throws IOException {
        initParents(5);
        printParents();

        System.out.println("\n2와 3을 합치자.");
        unionParents(2, 3);
        printParents();

        System.out.println("\n4와 5를 합치자.");
        unionParents(4, 5);
        printParents();

        System.out.println("\n2와 4를 합치자.");
        unionParents(2, 4);
        printParents();

        System.out.println("getParentOf(5)를 호출해서 경로압축이 되는지 확인해보자.");
        getParentOf(5);
        printParents();
    }

}
