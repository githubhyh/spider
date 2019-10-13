package com.hyh.leetcode;

import java.util.ArrayList;
import java.util.List;

public class ValidSudoku {
	public boolean isValidSudoku(char[][] board) {
		List<Character> temp = new ArrayList<>();
		List<Character> temp1 = new ArrayList<>();
		
		boolean flag = true;
		//先判断规则1,2
		for (int i = 0; i<9;i++) {
			for (int j = 0; j< 9; j++) {
				if (board[i][j]<='9' && board[i][j]>='1') {
					if (temp.contains(board[i][j])) {
						flag = false;
						break;
					}else {					
						temp.add(board[i][j]);
					}
				}
				if (board[j][i]<='9' && board[j][i]>='1') {
					if (temp1.contains(board[j][i])) {
						flag = false;
						break;
					}else {
						temp1.add(board[j][i]);
					}
				}
			}
			if (flag = false) {
				break;
			}
		}
		return flag;
	}
}
