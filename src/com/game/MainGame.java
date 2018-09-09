package com.game;

import java.util.Arrays;
import java.util.Random;

public class MainGame {
	Random rand = new Random();
	int[][] bombs = new int[16][20];
	int[][] clickdata = new int[16][20];
	
	// 16x20 game board
	public MainGame() {
		initGame();
	}
	
	private void initGame() {
		// init to all 0
//		int[][] bombs = new int[16][20];
//		int[][] clickdata = new int[16][20];
		int x, y;
		// set 20 bomb positions
		for(int i = 0; i < 20; i++) {
			x = rand.nextInt(16);
			y = rand.nextInt(20);
			System.out.println(x + " " +y + "i= " + i);
			if (bombs[x][y] == 0) {
				bombs[x][y] = -1;
			} else if (bombs[x][y] == 1) {
				i--;
			} else if (bombs[x][y] != -1){
				System.out.println("something wrong");
				break;
			}
		}
		
		// set bomb data around the bomb
		for (int i = 0; i < 16; i++) {
			for (int j = 0; j < 20; j++) {
				
				// if not a bomb
				if (bombs[i][j] != -1) {
					System.out.println(i + " " + j);
					// check top level
					if (i - 1 > 0) {
						// left
						if (j - 1 > 0) {
							if (bombs[i-1][j-1] == -1) {
								bombs[i][j] += 1;
							}
						}
						// middle
						if (bombs[i-1][j] == -1) {
							bombs[i][j] += 1;
						}
						// right
						if (j + 1 < 20) {
							if (bombs[i-1][j+1] == -1) {
								bombs[i][j] += 1;
							}
						}
					}
					
					// check middle level
					if (j - 1 > 0) {
						if (bombs[i][j-1] == -1) {
							bombs[i][j] += 1;
						}
					}
					if (j + 1 < 20) {
						if (bombs[i][j+1] == -1) {
							bombs[i][j] += 1;
						}
					}
					
					// check lower level
					if (i + 1 < 16) {
						// left
						if (j - 1 > 0) {
							if (bombs[i+1][j-1] == -1) {
								bombs[i][j] += 1;
							}
						}
						// middle
						if (bombs[i+1][j] == -1) {
							bombs[i][j] += 1;
						}
						// right
						if (j + 1 < 20) {
							if (bombs[i+1][j+1] == -1) {
								bombs[i][j] += 1;
							}
						}
					}
					
				}
			}
		}

		// testing: print bombdata
		for (int i = 0; i<16 ; i++) {
			System.out.println(Arrays.toString(bombs[i]));
		}
	}
	
	public int clicked(int x, int y) {
		// change x, y to corresponding array number
//		(100 -100)/50 = 0
//		(150-100)/50 = 1
		x = (x-100)/50;
		y = (y-100)/50;
		// check if it is clicked
		if (clickdata[x][y] == 1) {
			return 999;
		}
		
		// state clicked
		clickdata[x][y] = 1;
		
		// check if it's a bomb
		// return bombs[x][y]; // simplify
		if (bombs[x][y] == -1) {
			return -1;
		} else {
			return bombs[x][y];
		}
	}
	
//	public static void main(String[] args) {
//		MainGame newgame = new MainGame();
//	}
}
