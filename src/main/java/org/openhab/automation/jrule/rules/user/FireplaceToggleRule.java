package org.openhab.automation.jrule.rules.user;

import org.openhab.automation.jrule.generated.items.JRuleItems;
import org.openhab.automation.jrule.rules.*;
import org.openhab.automation.jrule.rules.event.JRuleItemEvent;
import org.openhab.automation.jrule.rules.value.JRuleOnOffValue;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import static org.openhab.automation.jrule.generated.items.JRuleItemNames.fireplace_switch_Action;

public class FireplaceToggleRule extends JRule {

    private Map<String, Function<JRuleItemEvent, String>> handlers = new HashMap<>();

    private Function<JRuleItemEvent, String> toggleHandler = (JRuleItemEvent event) -> {
        JRuleOnOffValue value = JRuleItems.fireplace_light_desk_PowerSwitch.getStateAsOnOff();
        if (value == JRuleOnOffValue.ON) {
            JRuleItems.fireplace_light_desk_PowerSwitch.sendCommand(JRuleOnOffValue.OFF);
            JRuleItems.fireplace_light_tv_PowerSwitch.sendCommand(JRuleOnOffValue.OFF);
            return "Lights OFF";
        } else {
            JRuleItems.fireplace_light_desk_PowerSwitch.sendCommand(JRuleOnOffValue.ON);
            JRuleItems.fireplace_light_tv_PowerSwitch.sendCommand(JRuleOnOffValue.ON);
            return "Lights ON";
        }
    };

    private Function<JRuleItemEvent, String> powerSwitchHandler = (JRuleItemEvent event) -> {
        JRuleOnOffValue value = JRuleItems.fireplace_power_muellerlicht_PowerSwitch.getStateAsOnOff();
        if (value == JRuleOnOffValue.ON) {
            JRuleItems.fireplace_power_muellerlicht_PowerSwitch.sendCommand(JRuleOnOffValue.OFF);
            return "Power Switch OFF";
        } else {
            JRuleItems.fireplace_power_muellerlicht_PowerSwitch.sendCommand(JRuleOnOffValue.ON);
            return "Power Switch ON";
        }
    };

    public FireplaceToggleRule() {
        handlers.put("toggle", toggleHandler);
        handlers.put("arrow_left_hold", powerSwitchHandler);
        handlers.put("arrow_right_hold", powerSwitchHandler);
    }

    @JRuleName("fireplace_desk_light_toggle")
    @JRuleWhenItemReceivedUpdate(item = fireplace_switch_Action)
    public boolean switchActionHandler(JRuleItemEvent event) {
        try {
            String result = this.handlers.get(event.getState().toString()).apply(event);
            logInfo(result);
        } catch (NullPointerException e) {
            logWarn("no handler defined for trigger {}", event.getState().toString());
        }
        return true;
    }

}
