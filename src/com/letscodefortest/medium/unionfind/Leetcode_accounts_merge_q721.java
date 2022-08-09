package com.letscodefortest.medium.unionfind;

import java.util.*;
import java.util.stream.Collectors;

// https://leetcode.com/problems/accounts-merge/

public class Leetcode_accounts_merge_q721 {

    static Solution1 s1 = new Solution1();

    // TLE
    static class Solution1 {

        int[] set;

        public List<List<String>> accountsMerge(List<List<String>> accounts) {
            Map<String, List<List<String>>> bucket = new HashMap<>();

            for (List<String> account : accounts) {
                List<List<String>> values = bucket.getOrDefault(account.get(0), new ArrayList<>());
                values.add(new ArrayList<>(account.subList(1, account.size())).stream().distinct().collect(Collectors.toList()));
                bucket.put(account.get(0), values);
            }

            for (Map.Entry<String, List<List<String>>> target : bucket.entrySet()) {
                if (target.getValue().size() > 1) {
                    target.setValue(unionFind(target.getValue()));
                }
            }

            List<List<String>> ret = new ArrayList<>();
            for (Map.Entry<String, List<List<String>>> entries : bucket.entrySet()) {
                for (List<String> emails : entries.getValue()) {
                    List<String> mergedAccount = new ArrayList<>();
                    mergedAccount.add(entries.getKey());
                    mergedAccount.addAll(emails.stream().sorted().collect(Collectors.toList()));
                    ret.add(mergedAccount);
                }
            }

            return ret;
        }

        private List<List<String>> unionFind(List<List<String>> values) {
            int n = values.size();
            set = new int[n];

            for (int i = 0; i < n; i++) {
                set[i] = i;
            }

            for (int i = 0; i < n - 1; i++) {
                for (int j = i + 1; j < n; j++) {
                    if (isSameUser(values.get(i), values.get(j))) {
                        union(i, j);
                    }
                }
            }

            for (int i = 0; i < n; i++) {
                find(i);
            }

            // set 이용 - 변환
            Map<Integer, Set<String>> mergedSet = new HashMap<>();
            for (int i = 0; i < n; i++) {
                Set<String> emails = mergedSet.getOrDefault(set[i], new HashSet<>());
                emails.addAll(values.get(i));
                mergedSet.put(set[i], emails);
            }

            List<List<String>> mergedList = new ArrayList<>();
            for (Set<String> emails : mergedSet.values()) {
                mergedList.add(new ArrayList<>(emails));
            }

            return mergedList;
        }

        private boolean isSameUser(List<String> userA, List<String> userB) {
            return userA.stream().distinct().filter(userB::contains).collect(Collectors.toSet()).size() > 0;
        }

        private void union(int i, int j) {
            int rootI = find(i);
            int rootJ = find(j);

            if (rootI != rootJ) {
                set[rootJ] = rootI;
            }
        }

        private int find(int i) {
            if (set[i] == i) {
                return i;
            } else {
                return set[i] = find(set[i]);
            }
        }
    }

    /**
     * time complexity: O(NKlogNK), N: number of accounts, K: maximum length of an account
     * space complexity: O(NK)
     * Runtime: 43 ms, faster than 80.24% of Java online submissions for Accounts Merge.
     * Memory Usage: 66.4 MB, less than 39.33% of Java online submissions for Accounts Merge.
     * 맵을 이용해 인접리스트를 만들면서 동시에 connected component를 구성한다(양방향 간선을 만들어 줌으로써) 이후 DFS를 실시해 같은 account를 찾아낸다.
     */
    static class Solution2 {
        // 포인트는 Set을 이용해 방문여부를 표시 했다는 점과 맵을 이용해 인접그래프를 나타낸다는 점
        HashSet<String> visited = new HashSet<>();
        Map<String, List<String>> adjacent = new HashMap<>();

        private void DFS(List<String> mergedAccount, String email) {
            visited.add(email);
            mergedAccount.add(email);

            if (!adjacent.containsKey(email)) {
                return;
            }

            for (String neighbor : adjacent.get(email)) {
                if (!visited.contains(neighbor)) {
                    DFS(mergedAccount, neighbor);
                }
            }
        }

        public List<List<String>> accountsMerge(List<List<String>> accountList) {
            int accountListSize = accountList.size();

            for (List<String> account : accountList) {
                int accountSize = account.size();

                String accountFirstEmail = account.get(1);
                for (int j = 2; j < accountSize; j++) {
                    String accountEmail = account.get(j);

                    // 첫번째 이메일을 중심으로 star(별모양) 꼴로 인접 그래프를 생성한다.
                    if (!adjacent.containsKey(accountFirstEmail)) {
                        adjacent.put(accountFirstEmail, new ArrayList<>());
                    }
                    adjacent.get(accountFirstEmail).add(accountEmail);

                    // 여기서의 포인트는 맵을 이용해 인접그래프를 나타낼 때, 첫번째 이메일과 나머지 이메일들의 간선을 '양방향'으로 이어준다는 것 -> 이를 이용해 자연스럽게 connected component를 생성함
                    // 만약에 메일이 겹치는 account의 경우 map의 특성으로 인해 자연스럽게 connected component가 된다.
                    if (!adjacent.containsKey(accountEmail)) {
                        adjacent.put(accountEmail, new ArrayList<>());
                    }
                    adjacent.get(accountEmail).add(accountFirstEmail);
                }
            }

            List<List<String>> mergedAccounts = new ArrayList<>();
            for (List<String> account : accountList) {
                String accountName = account.get(0);
                String accountFirstEmail = account.get(1);

                if (!visited.contains(accountFirstEmail)) {
                    List<String> mergedAccount = new ArrayList<>();
                    mergedAccount.add(accountName);

                    // DFS를 통해 연결된 간선을 따라 병합된 account를 구한다
                    DFS(mergedAccount, accountFirstEmail);
                    Collections.sort(mergedAccount.subList(1, mergedAccount.size()));
                    mergedAccounts.add(mergedAccount);
                }
            }

            return mergedAccounts;
        }
    }


    /**
     * time complexity: O(NKlogNK), N: number of accounts, K: maximum length of an account
     * space complexity: O(NK)
     * Runtime: 40 ms, faster than 85.51% of Java online submissions for Accounts Merge.
     * Memory Usage: 61.8 MB, less than 75.31% of Java online submissions for Accounts Merge.
     * 맵을 이용해 account Group을 만들면서 union find 시행, 이때 map을 이용해 같은 key(이미 본 이메일인 경우) union find를 시행하여 connected components를 만들어 준다는 점에 주목.
     */
    static class Solution3 {
        int[] representative;
        // union-find의 최적화를 위함 : 참고 : https://gmlwjd9405.github.io/2018/08/31/algorithm-union-find.html
        int[] size; // 이 사이즈는 이메일의 갯수 그런게 아니고 합쳐진 account의 사이즈를 의미 (account단위로 합쳐지니까)

        public List<List<String>> accountsMerge(List<List<String>> accountList) {
            int accountListSize = accountList.size();
            representative = new int[accountListSize];
            size = new int[accountListSize];

            for (int i = 0; i < accountListSize; i++) {
                representative[i] = i;
                // 모든 그룹의 사이즈를 처음에는 1로 맞춘다.
                size[i] = 1;
            }

            // key가 email이고 integer를 통해서 representative를 표현한 점에 주목
            Map<String, Integer> emailGroup = new HashMap<>();

            for (int i = 0; i < accountListSize; i++) {
                int accountSize = accountList.get(i).size();

                for (int j = 1; j < accountSize; j++) {
                    String email = accountList.get(i).get(j); // 이름은 제외해야 하니까

                    if (!emailGroup.containsKey(email)) {
                        emailGroup.put(email, i); // accountList의 인덱스가 그룹을 나타내는 지표로 쓰임
                    } else {
                        unionBySize(i, emailGroup.get(email)); // 이미 이메일 그룹에 존재하는 key인 경우 (한번 본 이메일일 경우) union find 시행
                    }
                }
            }

            Map<Integer, List<String>> components = new HashMap<>();
            for (String email : emailGroup.keySet()) {
                int group = emailGroup.get(email);
                int groupRep = findRepresentative(group); // 합쳐질때 다른 이메일 계정까지 root를 가리키고 있는게 아니끼 때문에 이와 같은 root를 indicate하기 위한 보정 작업 필요

                if (!components.containsKey(groupRep)) {
                    components.put(groupRep, new ArrayList<>());
                }

                components.get(groupRep).add(email);
            }

            List<List<String>> mergedAccounts = new ArrayList<>();
            for (int group : components.keySet()) {
                List<String> component = components.get(group);
                Collections.sort(component);
                component.add(0, accountList.get(group).get(0));
                mergedAccounts.add(component);
            }

            return mergedAccounts;
        }

        private void unionBySize(int a, int b) {
            int representativeA = findRepresentative(a);
            int representativeB = findRepresentative(b);

            if (representativeA == representativeB) {
                return;
            }

            // 크기에 따른 병합 : union - find 최적화
            if (size[representativeA] >= representativeB) {
                size[representativeA] += size[representativeB];
                representative[representativeB] = representativeA;
            } else {
                size[representativeB] += size[representativeA];
                representative[representativeA] = representativeB;
            }

        }

        private int findRepresentative(int x) {
            if (x == representative[x]) {
                return x;
            }

            // 경로 압축 : union - find 최적화
            return representative[x] = findRepresentative(representative[x]);
        }
    }

    public static void main(String[] args) {
        List<List<String>> input = new ArrayList<>();
        input.add(List.of("John", "johnsmith@mail.com", "john_newyork@mail.com"));
        input.add(List.of("John", "johnsmith@mail.com", "john00@mail.com"));
        input.add(List.of("Mary", "mary@mail.com"));
        input.add(List.of("John", "johnnybravo@mail.com"));

        System.out.println(s1.accountsMerge(input));

    }
}
