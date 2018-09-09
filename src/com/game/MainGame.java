package com.game;

import java.util.Arrays;
import java.util.Random;

public class MainGame {
	Random rand = new Random();

	// init to all 0
	int[][] bombs = new int[16][20];
	int[][] clickdata = new int[16][20];
	
	// 16x20 game board
	public MainGame() {
		initGame();
	}
	
	private void initGame() {
		int x, y;
		// set 35 bomb positions
		for(int i = 0; i < 35; i++) {
			x = rand.nextInt(20);
			y = rand.nextInt(16);
			System.out.println(x + " " +y + "i= " + i);
			if (bombs[y][x] == 0) {
				bombs[y][x] = -1;
			} else if (bombs[y][x] == 1) {
				i--;
			} else if (bombs[y][x] != -1){
				System.out.println("something wrong");
				break;
			}
		}
		
		// set bomb data around the bomb
		for (int i = 0; i < 20; i++) {
			for (int j = 0; j < 16; j++) {
				
				// if not a bomb
				if (bombs[j][i] != -1) {
					System.out.println(i + " " + j);
					// check top level
					if (j - 1 >= 0) {
						// left
						if (i - 1 >= 0) {
							if (bombs[j-1][i-1] == -1) {
								bombs[j][i] += 1;
							}
						}
						// middle
						if (bombs[j-1][i] == -1) {
							bombs[j][i] += 1;
						}
						// right
						if (i + 1 < 20) {
							if (bombs[j-1][i+1] == -1) {
								bombs[j][i] += 1;
							}
						}
					}
					
					// check middle level
					if (i - 1 >= 0) {
						if (bombs[j][i-1] == -1) {
							bombs[j][i] += 1;
						}
					}
					if (i + 1 < 20) {
						if (bombs[j][i+1] == -1) {
							bombs[j][i] += 1;
						}
					}
					
					// check lower level
					if (j + 1 < 16) {
						// left
						if (i - 1 >= 0) {
							if (bombs[j+1][i-1] == -1) {
								bombs[j][i] += 1;
							}
						}
						// middle
						if (bombs[j+1][i] == -1) {
							bombs[j][i] += 1;
						}
						// right
						if (i + 1 < 20) {
							if (bombs[j+1][i+1] == -1) {
								bombs[j][i] += 1;
							}
						}
					}
					
				}
			}
		}

		// testing: print bombdata
		for (int j = 0; j<16 ; j++) {
			System.out.println(Arrays.toString(bombs[j]));
		}
	}
	
	public int clicked(int x, int y) {
		// change x, y to corresponding array number
//		(100 -100)/50 = 0
//		(150-100)/50 = 1
		System.out.println("here: "+ x + " " + y);
		x = (x-100)/50;
		y = (y-100)/50;
		System.out.println("here2: " + clickdata[y][x] + "  "+bombs[y][x]);
		
		// testing: print clickdata
		for (int j = 0; j<16 ; j++) {
			System.out.println(Arrays.toString(bombs[j]));
		}
		
		// check if it is clicked
		if (clickdata[y][x] == 1) {
			return 999;
		}
		
		// state clicked
		clickdata[y][x] = 1;
		
		// check if it's a bomb
		// return bombs[x][y]; // simplify
		if (bombs[y][x] == -1) {
			return -1;
		} else if (bombs[y][x] == 0) {
			// open all surrounding 0 boxs
			openAllAround(x, y);
			return bombs[y][x];
		} else {
			return bombs[y][x];
		}
	}
	
	private void openAllAround(int x, int y) {
		// check top level
		if (y - 1 >= 0) {
			// check left
			if (x - 1 >= 0) {
				if (clickdata[y-1][x-1] == 0) {
					clickdata[y-1][x-1] = 1;

					if (bombs[y-1][x-1] == 0) {
						openAllAround(x-1, y-1);
					}
				}
			}
			// check middle
			if (clickdata[y-1][x] == 0) {
				clickdata[y-1][x] = 1;
				if (bombs[y-1][x] == 0) {
					openAllAround(x, y-1);
				}
			}
			// check right
			if (x + 1 < 20) {
				if (clickdata[y-1][x+1] == 0) {
					clickdata[y-1][x+1] = 1;
					if (bombs[y-1][x+1] == 0) {
						openAllAround(x+1, y-1);
					}
				}
			}
		}
		
		// check middle level
		if (x - 1 >= 0) {
			// check left
			if (clickdata[y][x-1] == 0) {
				clickdata[y][x-1] = 1;
				if (bombs[y][x-1] == 0) {
					openAllAround(x-1, y);
				}
			}
		}
		if (x + 1 < 20) {
			//check right
			if (clickdata[y][x+1] == 0) {
				clickdata[y][x+1] = 1;
				if (bombs[y][x+1] == 0) {
					openAllAround(x+1, y);
				}
			}
		}
		//check lower level
		if (y + 1 < 16) {
			// check left
			if (x - 1 >= 0) {
				if (clickdata[y+1][x-1] == 0) {
					clickdata[y+1][x-1] = 1;
					if (bombs[y+1][x-1] == 0) {
						openAllAround(x-1, y+1);
					}
				}
			}
			// check middle
			if (clickdata[y+1][x] == 0) {
				clickdata[y+1][x] = 1;
				if (bombs[y+1][x] == 0) {
					openAllAround(x, y+1);
				}
			}
			// check right
			if (x + 1 < 20) {
				if (clickdata[y+1][x+1] == 0) {
					clickdata[y+1][x+1] = 1;
					if (bombs[y+1][x+1] == 0) {
						openAllAround(x+1, y+1);
					}
				}
			}
		}

	}
	
//	public static void main(String[] args) {
//		MainGame newgame = new MainGame();
//	}
}
