package org.example;

import java.util.*;

public class VoteManager {
    public List<String> rankCandidates(List<String> votes) {
        Set<String> uniqueCandidates = new HashSet<>();

        for(String candidatesString : votes) {
            String[] candidates = candidatesString.split(",");
            for(String candidate : candidates)
                uniqueCandidates.add(candidate);
        }

        Map<String,int[]> voteMap = new HashMap<>();
        int candidateCount = uniqueCandidates.size();

        for(String vote : votes){
            int rank = 0;
            String [] voteArray = vote.split(",");
            for(String candidateVote : voteArray) {
                int[] voteCount;
                if(voteMap.containsKey(candidateVote)){
                    voteCount = voteMap.get(candidateVote);
                }
                else{
                    voteCount = new int[candidateCount];
                }
                voteCount[rank]++;
                voteMap.put(candidateVote,voteCount);
                rank++;
            }
        }

//        for(Map.Entry<String,int[]> entry : voteMap.entrySet()) {
//            System.out.println(entry.getKey()+" .. "+entry.getValue());
//            for(int val : entry.getValue()){
//                System.out.print(val);
//            }
//        }
        List<String> candidates = new ArrayList<>(uniqueCandidates);
        Collections.sort(candidates,(candidate1,candidate2)->{
            int[] candidateVote1 = voteMap.get(candidate1);
            int[] candidateVote2 = voteMap.get(candidate2);
            for(int rank=0; rank < candidateVote1.length; rank++) {
                if(candidateVote1[rank]<candidateVote2[rank]){
                    return 1;
                }
                else if(candidateVote1[rank]>candidateVote2[rank]){
                    return -1;
                }
            }
            return candidate1.compareTo(candidate2);
        });

        return candidates;
    }
}
