package org.openhab.automation.jrule.rules.user;

import org.openhab.automation.jrule.generated.items.JRuleItems;
import org.openhab.automation.jrule.rules.*;
import org.openhab.automation.jrule.rules.value.JRuleOnOffValue;

import static org.openhab.automation.jrule.generated.items.JRuleItemNames.fireplace_switch_Action;

public class FireplaceToggleRule extends JRule {

    @JRuleName("fireplace_desk_light_toggle")
    @JRuleWhenItemReceivedUpdate(item = fireplace_switch_Action)
    public boolean toggleSwitch() {
        logInfo("hooray it has been executed!!!");
        JRuleOnOffValue value = JRuleItems.fireplace_light_desk_PowerSwitch.getStateAsOnOff();
        if (value == JRuleOnOffValue.ON) {
            JRuleItems.fireplace_light_desk_PowerSwitch.sendCommand(JRuleOnOffValue.OFF);
        } else {
            JRuleItems.fireplace_light_desk_PowerSwitch.sendCommand(JRuleOnOffValue.ON);
        }
        return true;
    }

}
