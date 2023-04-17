package rt_Kukla.raytracing.gui;

import javax.swing.*;

import rt_Kukla.raytracing.rendering.AnimationRenderer;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class AnimationPanel extends JPanel {
    public AnimationPanel(Viewport viewport, SettingsPanel settingsPanel) {
        JButton btnFirstPos;
        JButton btnSecondPos;
        JLabel lbLabel0;
        JSpinner spnFrameCount;
        JButton btnStart;
        JLabel lbLabel1;
        JSpinner spnResolution;

        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints constraints = new GridBagConstraints();
        setLayout(layout);

        btnFirstPos = new JButton("Set first position");
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 1;
        constraints.weighty = 1;
        constraints.anchor = GridBagConstraints.NORTH;
        constraints.insets = new Insets(7,4,0,0);
        layout.setConstraints(btnFirstPos, constraints);
        add(btnFirstPos);

        btnSecondPos = new JButton("Set second position");
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 1;
        constraints.weighty = 1;
        constraints.anchor = GridBagConstraints.NORTH;
        constraints.insets = new Insets(7,0,0,4);
        layout.setConstraints(btnSecondPos, constraints);
        add(btnSecondPos);

        lbLabel0 = new JLabel("Frames");
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 1;
        constraints.weighty = 1;
        constraints.anchor = GridBagConstraints.NORTH;
        constraints.insets = new Insets(5,5,0,0);
        layout.setConstraints(lbLabel0, constraints);
        add(lbLabel0);

        spnFrameCount = new JSpinner();
        spnFrameCount.setModel(new SpinnerNumberModel(2, 2, 100000, 1));
        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 1;
        constraints.weighty = 1;
        constraints.anchor = GridBagConstraints.NORTH;
        constraints.insets = new Insets(5,0,0,0);
        layout.setConstraints(spnFrameCount, constraints);
        add(spnFrameCount);

        lbLabel1 = new JLabel("Output resolution (%)");
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 1;
        constraints.weighty = 1;
        constraints.anchor = GridBagConstraints.NORTH;
        constraints.insets = new Insets(5,5,0,0);
        layout.setConstraints(lbLabel1, constraints);
        add(lbLabel1);

        spnResolution = new JSpinner();
        spnResolution.setModel(new SpinnerNumberModel(100, 1, 100, 1));
        constraints.gridx = 1;
        constraints.gridy = 3;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 1;
        constraints.weighty = 1;
        constraints.anchor = GridBagConstraints.NORTH;
        constraints.insets = new Insets(5,0,0,0);
        layout.setConstraints(spnResolution, constraints);
        add(spnResolution);

        btnStart = new JButton("Render image sequence");
        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.gridwidth = 2;
        constraints.gridheight = 1;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 1;
        constraints.weighty = 1;
        constraints.anchor = GridBagConstraints.SOUTH;
        constraints.insets = new Insets(7,4,4,4);
        layout.setConstraints(btnStart, constraints);
        add(btnStart);

        btnFirstPos.addActionListener(e -> AnimationRenderer.captureFirstPosition(viewport.getScene().getCamera()));
        btnSecondPos.addActionListener(e -> AnimationRenderer.captureSecondPosition(viewport.getScene().getCamera()));

        btnStart.addActionListener(e -> {
            File outputDir = new File("image_sequence");
            if (!outputDir.exists())
                outputDir.mkdir();

            try {
                AnimationRenderer.renderImageSequence(viewport.getScene(), outputDir, settingsPanel.getOutputWidth (), settingsPanel.getOutputHeight(), (int)spnFrameCount.getValue(), (int)spnResolution.getValue()/100F);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(viewport, ex.toString(), "Failed to save image sequence.", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        });
    }
}
