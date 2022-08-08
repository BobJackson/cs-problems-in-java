package csproblem.injava.chapter1;

import java.util.ArrayDeque;
import java.util.Deque;

public class Hanoi {
    private final int numDiscs;
    private final Deque<Integer> towerA = new ArrayDeque<>();
    private final Deque<Integer> towerB = new ArrayDeque<>();
    private final Deque<Integer> towerC = new ArrayDeque<>();


    public Hanoi(int discs) {
        this.numDiscs = discs;
        for (int i = 1; i <= discs; i++) {
            towerA.addFirst(i);
        }
    }

    public void solve() {
        move(towerA, towerC, towerB, numDiscs);
    }

    private static void move(Deque<Integer> begin, Deque<Integer> end, Deque<Integer> temp, int n) {
        if (n == 1) {
            end.addFirst(begin.removeFirst());
        } else {
            move(begin, temp, end, n - 1);
            move(begin, end, temp, 1);
            move(temp, end, begin, n - 1);
        }
    }

    public Deque<Integer> getTowerA() {
        return towerA;
    }

    public Deque<Integer> getTowerB() {
        return towerB;
    }

    public Deque<Integer> getTowerC() {
        return towerC;
    }
}
