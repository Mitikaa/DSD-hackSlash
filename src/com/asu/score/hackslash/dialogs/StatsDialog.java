package com.asu.score.hackslash.dialogs;

import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

import com.asu.score.hackslash.engine.SessionManager;
import com.asu.score.hackslash.properties.Constants;
import com.asu.score.hackslash.statistics.GitController;

public class StatsDialog extends TitleAreaDialog {

	private GitController gitCtrl = GitController.getInstance();
	private SessionManager session = SessionManager.getInstance();

	public StatsDialog(Shell parentShell) {
		super(parentShell);
	}

	@Override
	public void create() {
		super.create();
		setTitle("Git Commit Statistics");
		setMessage("Repo: - " + gitCtrl.getGitPath(), IMessageProvider.INFORMATION);
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite area = (Composite) super.createDialogArea(parent);
		Composite container = new Composite(area, SWT.NONE);
		container.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		GridLayout layout = new GridLayout(3, false);
		container.setLayout(layout);
		Composite container1 = new Composite(area, SWT.NONE);
		container1.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		container1.setLayout(layout);

		if (session.isAuthenticated()) {
			createButton(container, 101, "User Commit Statistics Graph", true);
			createButton(container, 102, "Monthly Commit Statistics Graph", true);
			createButton(container, 103, "LOC Statistics Graph", true);
			createButton(container1, 100, "Total Commit Meter", true);
			createButton(container1, Constants.GIT_COMMIT_LOGS_BUTTON_ID, "Show commit logs", true);

		} else {
			Label lblUser = new Label(container, SWT.NONE);
			lblUser.setText("Kindly login to view Git Statistics");
		}

		return area;
	}

	@Override
	protected boolean isResizable() {
		return true;
	}

	@Override
	protected void buttonPressed(int buttonId) {
		System.out.println("buttonpressed in StatsDialog id: "+buttonId);
		setReturnCode(buttonId);
		close();
	}

}