package com.cognizant.cognizantits.ide.main.mainui.components.testdesign.testcase.validation;

import com.cognizant.cognizantits.datalib.component.TestData;
import com.cognizant.cognizantits.datalib.component.TestStep;
import com.cognizant.cognizantits.datalib.testdata.model.TestDataModel;
import com.cognizant.cognizantits.engine.support.methodInf.Action;
import com.cognizant.cognizantits.engine.support.methodInf.MethodInfoManager;
import java.util.Objects;
import javax.swing.JComponent;

public class InputRenderer extends AbstractRenderer {
	String testDataNotPresent = "TestData/Column not avaliable in the Project";
	String inValidInput = "Syntax error. Input should be one of [@val ,%var% ,=Function ,Sheet:Column]";
	String shouldBeEmpty = "Syntax error. Input should be empty for the Action";

	public InputRenderer() {
		super("Input Shouldn't be empty.It should be one of [@val ,%var% ,=Function ,Sheet:Column]");
	}

	public void render(JComponent comp, TestStep step, Object value) {
		if (!step.isCommented().booleanValue()) {
			if (isEmpty(value).booleanValue()) {
				if (!isOptional(step).booleanValue()) {
					setEmpty(comp);
				} else {
					setDefault(comp);
				}
			} else if (isNotNeeded(step).booleanValue()) {
				setNotPresent(comp, this.shouldBeEmpty);
			} else if (step.isTestDataStep().booleanValue()) {
				if (isTestDataPresent(step).booleanValue()) {
					setDefault(comp);
				} else {
					setNotPresent(comp, this.testDataNotPresent);
				}
			} else if (isInputValid(value).booleanValue() || step.getInput().startsWith("<")
					|| step.getInput().startsWith("{")) {
				setDefault(comp);
			} else {
				setNotPresent(comp, this.inValidInput);
			}
		} else {
			setDefault(comp);
		}
	}

	private Boolean isOptional(TestStep step) {
		if (step.getObject().matches("Execute"))
			return Boolean.valueOf(true);
		if (MethodInfoManager.methodInfoMap.containsKey(step.getAction())) {
			return Boolean.valueOf(!((Action) MethodInfoManager.methodInfoMap.get(step.getAction())).input()
					.isMandatory().booleanValue());
		}
		return Boolean.valueOf(true);
	}

	private Boolean isNotNeeded(TestStep step) {
		if (step.getObject().matches("Execute"))
			return Boolean.valueOf(false);
		if (MethodInfoManager.methodInfoMap.containsKey(step.getAction())) {
			return ((Action) MethodInfoManager.methodInfoMap.get(step.getAction())).input().isNotNeeded();
		}
		return Boolean.valueOf(true);
	}

	private Boolean isTestDataPresent(TestStep step) {
		String[] data = step.getTestDataFromInput();
		return Boolean.valueOf(step.getProject().getTestData().getAllEnvironments().stream()
				.map(sTestData -> sTestData.getByName(data[0])).anyMatch(tdModelDef -> hasColumn(tdModelDef, data[1])));
	}

	private boolean hasColumn(TestDataModel tdModel, String column) {
		return (tdModel != null && tdModel.getColumnIndex(column) >= 0);
	}

	private Boolean isInputValid(Object value) {
		String val = Objects.toString(value, "").trim();
		return Boolean.valueOf(val.matches("(@.+)|(=.+)|(%.+%)|(<.+>)|([.+])"));
	}
}
