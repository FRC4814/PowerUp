package com.team4814.frc2018.utils;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class XboxControllerButton extends JoystickButton {

	public XboxControllerButton(XboxController controller, XboxButton buttonNumber) {
		super(controller, buttonNumber.getValue());
	}

}
