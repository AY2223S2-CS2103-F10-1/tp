package seedu.address.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class HelpWindowTest {
    @Test
    public void instructionMap_emptyInput_returnEmptyString() {
        String testStr = "";
        String resultStr = "";
        assertEquals(HelpWindow.instructionMap(testStr), resultStr);
    }
    @Test
    public void instructionMap_addMethods_returnEmptyString() {
        String testStr = "Add commands";
        String resultStr = HelpStrings.ADDMETHODS_HELP;
        assertEquals(HelpWindow.instructionMap(testStr), resultStr);
    }
}
