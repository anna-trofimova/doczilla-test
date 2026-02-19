package org.example;

import java.util.*;

public class Solver {

    private int maxDepth;

    public Solver(int maxDepth) {
        this.maxDepth = maxDepth;
    }

    public List<int[]> solve(GameState initialState) {
        return dfs(initialState, new ArrayList<>(), 0, new HashSet<>());
    }

    private List<int[]> dfs(GameState state, List<int[]> movesSoFar, int depth, Set<String> visited) {
        if (state.isSolved()) {
            return movesSoFar;
        }

        if (depth >= maxDepth) return null;

        String key = state.encodeState();
        if (visited.contains(key)) return null;
        visited.add(key);

        Tube[] tubes = state.getTubes();

        // Heuristics: empty test tubes first
        List<Integer> emptyTubes = new ArrayList<>();
        List<Integer> nonEmptyTubes = new ArrayList<>();
        for (int i = 0; i < tubes.length; i++) {
            if (tubes[i].isEmpty()) emptyTubes.add(i);
            else nonEmptyTubes.add(i);
        }

        for (int i : nonEmptyTubes) {
            for (int j : emptyTubes) {
                if (state.canPour(i, j)) {
                    GameState newState = state.cloneState();
                    newState.pour(i, j);
                    List<int[]> newMoves = new ArrayList<>(movesSoFar);
                    newMoves.add(new int[]{i, j});
                    List<int[]> result = dfs(newState, newMoves, depth + 1, visited);
                    if (result != null) return result;
                }
            }
        }

        // Transfusion into not empty tubes with the same color
        for (int i : nonEmptyTubes) {
            for (int j : nonEmptyTubes) {
                if (i == j) continue;
                if (state.canPour(i, j)) {
                    GameState newState = state.cloneState();
                    newState.pour(i, j);
                    List<int[]> newMoves = new ArrayList<>(movesSoFar);
                    newMoves.add(new int[]{i, j});
                    List<int[]> result = dfs(newState, newMoves, depth + 1, visited);
                    if (result != null) return result;
                }
            }
        }

        return null;
    }

    public List<int[]> solveIterative(GameState initialState) {

        Set<String> visited = new HashSet<>();
        Stack<GameStateWithMoves> stack = new Stack<>();
        stack.push(new GameStateWithMoves(initialState));

        int exploredStates = 0;

        while (!stack.isEmpty()) {

            GameStateWithMoves current = stack.pop();
            exploredStates++;

            if (exploredStates % 10000 == 0) {
                System.out.println("Explored states: " + exploredStates);
            }

            if (current.state.isSolved()) {
                System.out.println("Всего исследовано состояний: " + exploredStates);
                return current.movesSoFar;
            }

            String key = current.state.encodeState();
            if (visited.contains(key)) continue;
            visited.add(key);

            Tube[] tubes = current.state.getTubes();

            for (int i = 0; i < tubes.length; i++) {

                if (tubes[i].isFull() && tubes[i].isUniformOrEmpty()) continue;

                for (int j = 0; j < tubes.length; j++) {
                    if (i == j) continue;

                    if (current.state.canPour(i, j)) {

                        GameState newState = current.state.cloneState();
                        newState.pour(i, j);

                        List<int[]> newMoves = new ArrayList<>(current.movesSoFar);
                        newMoves.add(new int[]{i, j});

                        stack.push(new GameStateWithMoves(newState, newMoves));
                    }
                }
            }
        }

        System.out.println("Всего исследовано состояний: " + exploredStates);
        return Collections.emptyList();
    }


}
