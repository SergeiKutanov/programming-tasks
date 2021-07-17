package com.sergeik.dynamic;

import java.util.*;

/**
 * In a project, you have a list of required skills req_skills, and a list of people. The ith person people[i]
 * contains a list of skills that the person has.
 *
 * Consider a sufficient team: a set of people such that for every required skill in req_skills, there is at least
 * one person in the team who has that skill. We can represent these teams by the index of each person.
 *
 * For example, team = [0, 1, 3] represents the people with skills people[0], people[1], and people[3].
 * Return any sufficient team of the smallest possible size, represented by the index of each person. You may return
 * the answer in any order.
 */
public class SmallestSufficientTeam {

    private static int teamSize;
    private static HashSet<Integer> resTeam;

    public static void main(String[] args) {
        List<List<String>> people;

        people = new LinkedList<>();
        people.add(Arrays.asList("java"));
        people.add(Arrays.asList("nodejs"));
        people.add(Arrays.asList("nodejs", "reactjs"));
        assert Arrays.equals(
                new int[] {0,2},
                solution(new String[] {"java","nodejs","reactjs"}, people)
        );
    }

    private static int[] solution(String[] req_skills, List<List<String>> people) {
        teamSize = people.size();
        resTeam = new HashSet<>();
        HashMap<String, HashSet<Integer>> skillMap = new HashMap<>();
        for (int i = 0; i < people.size(); i++) {
            for (String skill: people.get(i)) {
                if (!skillMap.containsKey(skill))
                    skillMap.put(skill, new HashSet<>());
                skillMap.get(skill).add(i);
            }
        }

        backtrack(skillMap, req_skills, 0, new HashSet<>());

        int[] res = new int[resTeam.size()];
        int idx = 0;
        for (int p: resTeam)
            res[idx++] = p;

        return res;
    }

    private static void backtrack(HashMap<String, HashSet<Integer>> skillMap, String[] reqSkills, int idx, HashSet<Integer> team) {
        if (team.size() > teamSize)
            return;
        if (idx == reqSkills.length) {
            if (team.size() < teamSize) {
                teamSize = team.size();
                resTeam = new HashSet<>();
                resTeam.addAll(team);
            }
            return;
        }
        for (int person: skillMap.get(reqSkills[idx])) {
            boolean shouldRemove = !team.contains(person);
            team.add(person);
            backtrack(skillMap, reqSkills, idx + 1, team);
            if (shouldRemove)
                team.remove(person);
        }
    }

//    private static int[] solution(String[] req_skills, List<List<String>> people) {
//        teamSize = people.size();
//        resTeam = new HashSet<>();
//        HashMap<String, List<Integer>> skillMap = new HashMap<>();
//        for (int i = 0; i < people.size(); i++) {
//            for (String skill: people.get(i)) {
//                if (!skillMap.containsKey(skill))
//                    skillMap.put(skill, new ArrayList<>());
//                skillMap.get(skill).add(i);
//            }
//        }
//
//        backtrack(skillMap, req_skills, 0, new HashSet<>());
//        int[] res = new int[resTeam.size()];
//        int idx = 0;
//        for (int person: resTeam)
//            res[idx++] = person;
//        return res;
//    }

//    private static void backtrack(HashMap<String, List<Integer>> skillMap, String[] reqSkills, int cur, HashSet<Integer> team) {
//        if (team.size() > teamSize)
//            return;
//        if (cur == reqSkills.length) {
//            if (team.size() < teamSize) {
//                teamSize = team.size();
//                resTeam = new HashSet<>();
//                resTeam.addAll(team);
//            }
//            return;
//        }
//        for (int person: skillMap.get(reqSkills[cur])) {
//            boolean isRemove = !team.contains(person);
//            team.add(person);
//            backtrack(skillMap, reqSkills, cur + 1, team);
//            if (isRemove)
//                team.remove(person);
//        }
//    }

}
