package com.letscodefortest.q2;

import java.util.*;

public class Q2 {

    public String reorganizeString(String S) {
        Map<Character, Integer> counter = new HashMap<>();
        for (char c: S.toCharArray()) {
            if (counter.containsKey(c)) {
                counter.put(c, counter.get(c) + 1);
            } else {
                counter.put(c, 1);
            }
        }

        int length = S.length();
        for (char c : counter.keySet()) {
            if (length - counter.get(c) + 2 <= counter.get(c)) {
                return "";
            }
        }

        LinkedHashMap<Character, Integer> reverseSortedMap = new LinkedHashMap<>();

        counter.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEachOrdered(x -> reverseSortedMap.put(x.getKey(), x.getValue()));

        char[] result = new char[length];
        int index = 0;
        for (char c : reverseSortedMap.keySet()) {
            for (int i=0; i<reverseSortedMap.get(c); i++) {
                if (index >= length) {
                    index = 1;
                }
                result[index] = c;
                index += 2;
            }
        }

        return new String(result);
    }

    public String reorganizeString_solution(String S) {
        int[] hash = new int[26];
        for (int i=0; i<S.length(); i++) hash[S.charAt(i) - 'a']++;

        int max = 0, letter = 0;
        for (int i=0; i<hash.length; i++) {
            if (hash[i] > max) {
                max = hash[i];
                letter = i;
            }
        }

        if (max > (S.length() + 1) / 2) return "";

        char[] res = new char[S.length()];
        int idx = 0;
        while(hash[letter] > 0) {
            res[idx] = (char) (letter + 'a');
            idx += 2;
            hash[letter]--;
        }
        for (int i=0; i< hash.length; i++) {
            while(hash[i] > 0) {
                if (idx >= res.length) {
                    idx = 1;
                }
                res[idx] = (char) (i + 'a');
                idx += 2;
                hash[i]--;
            }
        }
        return String.valueOf(res);
    }


    public static void main(String[] args) {
        Q2 q2 = new Q2();
        System.out.println(q2.reorganizeString("aabbcc"));
    }
}
