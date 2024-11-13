package org.openhab.automation.jrule.rules.user;

import static org.mockito.Mockito.verify;

import org.apache.commons.collections4.map.HashedMap;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.openhab.automation.jrule.internal.JRuleConfig;
import org.openhab.automation.jrule.internal.engine.JRuleEngine;
import org.openhab.automation.jrule.internal.module.JRuleRuleProvider;
import org.openhab.automation.jrule.internal.test.JRuleMockedEventBus;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;

@ExtendWith(MockitoExtension.class)
public class FireplaceToggleRuleTest {

    @Mock
    private FireplaceToggleRule fireplaceToggleRule;

    @Mock
    private JRuleRuleProvider ruleProvider;

    public void setUp() throws Exception {
        Logger rootLogger = (Logger) LoggerFactory.getLogger(org.slf4j.Logger.ROOT_LOGGER_NAME);
        rootLogger.setLevel(Level.ALL);
    }

    @Test
    @Disabled
    public void testMyRule() {
        JRuleEngine engine = JRuleEngine.get();
        JRuleConfig config = new JRuleConfig(new HashedMap<>());
        engine.setConfig(config);
        engine.setRuleProvider(ruleProvider);
        engine.add(fireplaceToggleRule, true);
        JRuleMockedEventBus eventBus = new JRuleMockedEventBus();
        eventBus.start();
        verify(fireplaceToggleRule.toggleSwitch()).equals(true);
    }
}
