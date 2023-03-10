package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import com.ctre.phoenix.led.*;
import com.ctre.phoenix.led.CANdle.LEDStripType;

public class CANdleSystem extends SubsystemBase {
    private final CANdle m_candle = new CANdle(Constants.CANdleID, "rio");

    public LEDState ledstate;

    public CANdleSystem() {
        
        CANdleConfiguration configAll = new CANdleConfiguration();
        configAll.statusLedOffWhenActive = true;
        configAll.disableWhenLOS = false;
        configAll.stripType = LEDStripType.RGB;
        configAll.brightnessScalar = 0.1;
        m_candle.configAllSettings(configAll);
        ledstate = LEDState.CUBE;

        setLEDSTate(ledstate);
    }

    public void setLEDSTate(LEDState state) {

        ledstate = state;
        m_candle.setLEDs(state.r, state.g, state.b);

    }

    public LEDState getLEDState() {

        return ledstate;

    }

    public enum LEDState {

        CUBE(Constants.PURPLE_R, Constants.PURPLE_G, Constants.PURPLE_B),
        CONE(Constants.YELLOW_R, Constants.YELLOW_G, Constants.YELLOW_B);

        public final int r;
        public final int g;
        public final int b;

        private LEDState(int r, int g, int b) {
            this.r = r;
            this.g = g;
            this.b = b;
        }

    }

}