package com.team4814.frc2018.auto;

public enum AutoGoalState
{
	kUnknown, kLeft, kRight;

	public static AutoGoalState fromChar(char c)
	{
		if (c == 'L')
			return kLeft;
		if (c == 'R')
			return kRight;
		return kUnknown;
	}
}
