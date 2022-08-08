package csproblem.injava.chapter1;

import java.util.Stack;

public class Hanoi {
    private final int numDiscs;
    private final Stack<Integer> towerA = new Stack<>();
    private final Stack<Integer> towerB = new Stack<>();
    private final Stack<Integer> towerC = new Stack<>();


    public Hanoi(int discs) {
        this.numDiscs = discs;
        for (int i = 1; i <= discs; i++) {
            towerA.push(i);
        }
    }

    public void solve() {
        move(towerA, towerC, towerB, numDiscs);
    }

    private static void move(Stack<Integer> begin, Stack<Integer> end, Stack<Integer> temp, int n) {
        if (n == 1) {
            end.push(begin.pop());
        } else {
            move(begin, temp, end, n - 1);
            move(begin, end, temp, 1);
            move(temp, end, begin, n - 1);
        }
    }

    public Stack<Integer> getTowerA() {
        return towerA;
    }

    public Stack<Integer> getTowerB() {
        return towerB;
    }

    public Stack<Integer> getTowerC() {
        return towerC;
    }
}
