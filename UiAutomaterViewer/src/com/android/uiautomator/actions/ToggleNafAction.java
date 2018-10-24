
package com.android.uiautomator.actions;
import com.android.uiautomator.UiAutomatorModel;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.resource.ImageDescriptor;
public class ToggleNafAction extends Action {
    public ToggleNafAction() {
        super("&Toggle NAF Nodes", IAction.AS_CHECK_BOX);
        setChecked(UiAutomatorModel.getModel().shouldShowNafNodes());
    }
    @Override
    public ImageDescriptor getImageDescriptor() {
        return ImageHelper.loadImageDescriptorFromResource("images/warning.png");
    }
    @Override
    public void run() {
        UiAutomatorModel.getModel().toggleShowNaf();
        setChecked(UiAutomatorModel.getModel().shouldShowNafNodes());
    }
}