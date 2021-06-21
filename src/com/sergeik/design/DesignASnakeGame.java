package com.sergeik.design;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Design a Snake game that is played on a device with screen size height x width. Play the game online if you
 * are not familiar with the game.
 *
 * The snake is initially positioned at the top left corner (0, 0) with a length of 1 unit.
 *
 * You are given an array food where food[i] = (ri, ci) is the row and column position of a piece of food that
 * the snake can eat. When a snake eats a piece of food, its length and the game's score both increase by 1.
 *
 * Each piece of food appears one by one on the screen, meaning the second piece of food will not appear until
 * the snake eats the first piece of food.
 *
 * When a piece of food appears on the screen, it is guaranteed that it will not appear on a block occupied by
 * the snake.
 *
 * The game is over if the snake goes out of bounds (hits a wall) or if its head occupies a space that its body
 * occupies after moving (i.e. a snake of length 4 cannot run into itself).
 *
 * Implement the SnakeGame class:
 *
 * SnakeGame(int width, int height, int[][] food) Initializes the object with a screen of size height x width
 * and the positions of the food.
 * int move(String direction) Returns the score of the game after applying one direction move by the snake.
 * If the game is over, return -1.
 */
public class DesignASnakeGame {

    public static void main(String[] args) {
        SnakeGame snakeGame;

        snakeGame = new SnakeGame(3,3,new int[][] {{2,0}, {0,0}, {0,2}, {2,2}});
        assert 0 == snakeGame.move("D");
        assert 1 == snakeGame.move("D");
        assert 1 == snakeGame.move("R");
        assert 1 == snakeGame.move("U");
        assert 1 == snakeGame.move("U");
        assert 2 == snakeGame.move("L");
        assert 2 == snakeGame.move("D");
        assert 2 == snakeGame.move("R");
        assert 2 == snakeGame.move("R");
        assert 3 == snakeGame.move("U");
        assert 3 == snakeGame.move("L");
        assert 3 == snakeGame.move("D");

        snakeGame = new SnakeGame(2,2, new int[][] {{0,1}});
        assert 1 == snakeGame.move("R");
        assert 1 == snakeGame.move("D");


        snakeGame = new SnakeGame(3, 2, new int[][] {{1, 2}, {0, 1}});
        assert 0 == snakeGame.move("R"); // return 0
        assert 0 == snakeGame.move("D"); // return 0
        assert 1 == snakeGame.move("R"); // return 1, snake eats the first piece of food. The second piece of food appears
        // at (0, 1).
        assert 1 == snakeGame.move("U"); // return 1
        assert 2 == snakeGame.move("L"); // return 2, snake eats the second food. No more food appears.
        assert -1 == snakeGame.move("U"); // return -1, game over because snake collides with border
    }

    static class SnakeGame {

        boolean[][] field;
        int[][] food;
        int foodIdx = 0;
        Deque<int[]> snake;
        int snakeLength = 1;

        /** Initialize your data structure here.
         @param width - screen width
         @param height - screen height
         @param food - A list of food positions
         E.g food = [[1,1], [1,0]] means the first food is positioned at [1,1], the second is at [1,0]. */
        public SnakeGame(int width, int height, int[][] food) {
            field = new boolean[height][width];
            this.food = food;
            snake = new LinkedList<>();
            snake.offer(new int[]{0,0});
            field[0][0] = true;
        }

        /** Moves the snake.
         @param direction - 'U' = Up, 'L' = Left, 'R' = Right, 'D' = Down
         @return The game's score after the move. Return -1 if game over.
         Game over when snake crosses the screen boundary or bites its body. */
        public int move(String direction) {
            int[] dir = new int[]{0,0};
            switch (direction) {
                case "U": dir = new int[] {-1,0}; break;
                case "D": dir = new int[] {1,0}; break;
                case "L": dir = new int[] {0,-1}; break;
                case "R": dir = new int[] {0,1}; break;
            }
            int x = snake.peek()[0] + dir[0];
            int y = snake.peek()[1] + dir[1];
            //hit a wall
            if (x < 0 || x == field.length || y < 0 || y == field[0].length)
                return -1;
            //hit a food
            boolean hitFood = false;
            if (foodIdx < food.length && x == food[foodIdx][0] && y == food[foodIdx][1]) {
                foodIdx++;
                hitFood = true;
            }

            if (hitFood) {
                snakeLength++;
            } else {
                int[] tail = snake.pollLast();
                field[tail[0]][tail[1]] = false;
            }

            //hit self
            if (field[x][y])
                return -1;

            snake.addFirst(new int[]{x,y});
            field[x][y] = true;

            return snakeLength - 1;
        }
    }

}
