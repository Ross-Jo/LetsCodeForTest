package com.letscodefortest.medium;

import java.util.*;
import java.util.stream.Collectors;

public class Leetcode_1366 {
    static Solution1 s1 = new Solution1();
    static Solution2 s2 = new Solution2();

    static class Solution1 { // wrong

        int[][] voteCount;

        public String rankTeams(String[] votes) {
            int numOfSeats = votes[0].length();
            voteCount = new int[26][numOfSeats];
            for (String e : votes) {
                for (int i = 0; i < e.length(); i++) {
                    voteCount[e.charAt(i) - 65][i]++;
                }
            }

            Set<Integer> candidates = Arrays.stream(votes[0].split("")).
                    map(e -> e.charAt(0) - 65).collect(Collectors.toSet());

            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < numOfSeats; j++) {
                sb.append(getWinner(candidates, j));
            }

            return sb.toString();
        }

        public char getWinner(Set<Integer> candidates, int seat) {

            int mostVotedCount = 0;
            int mostVotedValue = 0;
            Set<Integer> filteredCandidates = new HashSet<>();
            for (int row : candidates) {
                if (mostVotedValue < voteCount[row][seat]) {
                    mostVotedValue = voteCount[row][seat];
                    mostVotedCount = 1;
                    filteredCandidates = new HashSet<>() {{
                        add(row);
                    }};
                } else if (mostVotedValue == voteCount[row][seat]) {
                    mostVotedCount++;
                    filteredCandidates.add(row);
                }
            }

            if (mostVotedCount > 1) {
                if (seat + 1 == voteCount[0].length) {
                    return (char) (filteredCandidates.stream().mapToInt(v -> v).max().getAsInt() + 65);
                } else {
                    return getWinner(filteredCandidates, seat + 1);
                }
            }

            return (char) (filteredCandidates.iterator().next() + 65);
        }
    }

    /**
     * https://leetcode.com/problems/rank-teams-by-votes/
     * time complexity: O(NlogN)
     * space complexity: O(C^2), C: number of seats
     * Runtime: 39 ms, faster than 16.19% of Java online submissions for Rank Teams by Votes.
     * Memory Usage: 53.5 MB, less than 17.53% of Java online submissions for Rank Teams by Votes.
     * 각 팀이 seat 별로 받은 vote 수 만큼의 합계를 나타내는 배열을 만든 후 배열끼리 비교를 통한 sort를 실시하여 결과를 반환한다.
     */
    static class Solution2 {
        public String rankTeams(String[] votes) {
            Map<Character, int[]> map = new HashMap<>();
            int numOfSeats = votes[0].length();

            for (String vote : votes) {
                for (int i = 0; i < numOfSeats; i++) {
                    char c = vote.charAt(i);
                    map.putIfAbsent(c, new int[numOfSeats]);
                    map.get(c)[i]++;
                }
            }

            List<Character> teamList = new ArrayList<>(map.keySet());
            Collections.sort(teamList, (a, b) -> {
                for (int i = 0; i < numOfSeats; i++) {
                    if (map.get(a)[i] != map.get(b)[i]) {
                        return map.get(b)[i] - map.get(a)[i];
                    }
                }
                return a - b;
            });

            StringBuilder sb = new StringBuilder();
            for (char c : teamList) {
                sb.append(c);
            }
            return sb.toString();
        }
    }

    public static void main(String[] args) {
//        System.out.println(s2.rankTeams(new String[]{"ABC", "ACB", "ABC", "ACB", "ACB"}));
        System.out.println(s2.rankTeams(new String[]{"WXYZ", "XYZW"}));
//        System.out.println(s2.rankTeams(new String[]{"ZMNAGUEDSJYLBOPHRQICWFXTVK"}));
    }
}
