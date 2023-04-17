package rt_Kukla.raytracing.gui;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileFilter;

import rt_Kukla.raytracing.math.Vector3;
import rt_Kukla.raytracing.pixeldata.Color;
import rt_Kukla.raytracing.rendering.Scene;
import rt_Kukla.raytracing.rendering.Skybox;
import rt_Kukla.raytracing.solids.Plane;
import rt_Kukla.raytracing.solids.Sphere;
import rt_Kukla.raytracing.solids.Box;

import java.awt.*;
import java.awt.event.ItemEvent;
import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;


public class SettingsPanel extends JPanel {
    private int selectedSkyboxIndex;
    private JSpinner spImageWidth, spImageHeight;

    public SettingsPanel(Viewport viewport, JDialog animationDialog) {
        JSlider sdResolution;
        JLabel lbViewportRes;
        JLabel lbSensitivity;
        JSlider sdSensitivity;
        JLabel lbLightHA;
        JLabel lbLighVA;
        JSlider sdLightHA, sdLightVA;
        JLabel lbSpeed;
        JSlider sdSpeed;
        JLabel lbLightDist;
        JSlider sdLightDistance;
        JLabel lbFOV;
        JSlider sdFOV;
        JLabel lbSkybox, lbScene;
        JComboBox<String> cbScene, cbSkybox;
        JLabel lbOutRes;
        JButton btnRenderImage, btnShowAnimationDialog;

        GridBagLayout gbPanel0 = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        this.setLayout(gbPanel0);

        lbViewportRes = new JLabel("Viewport resolution");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.insets = new Insets(10,5,0,0);
        gbPanel0.setConstraints(lbViewportRes, gbc);
        this.add(lbViewportRes);

        sdResolution = new JSlider();
        sdResolution.setMinimum(1);
        sdResolution.setMaximum(100);
        sdResolution.setValue(25);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 0;
        gbc.anchor = GridBagConstraints.NORTH;
        gbPanel0.setConstraints(sdResolution, gbc);
        this.add(sdResolution);

        lbSensitivity = new JLabel("Mouse sensitivity");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.insets = new Insets(0,5,0,0);
        gbPanel0.setConstraints(lbSensitivity, gbc);
        this.add(lbSensitivity);

        sdSensitivity = new JSlider();
        sdSensitivity.setMinimum(1);
        sdSensitivity.setMaximum(100);
        sdSensitivity.setValue(10);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 0;
        gbc.anchor = GridBagConstraints.NORTH;
        gbPanel0.setConstraints(sdSensitivity, gbc);
        this.add(sdSensitivity);

        lbSpeed = new JLabel("Movement speed");
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.anchor = GridBagConstraints.NORTH;
        gbPanel0.setConstraints(lbSpeed, gbc);
        this.add(lbSpeed);

        sdSpeed = new JSlider();
        sdSpeed.setMinimum(1);
        sdSpeed.setMaximum(200);
        sdSpeed.setValue(100);
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 0;
        gbc.anchor = GridBagConstraints.NORTH;
        gbPanel0.setConstraints(sdSpeed, gbc);
        this.add(sdSpeed);

        lbFOV = new JLabel("Field of vision");
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.insets = new Insets(0,5,0,0);
        gbPanel0.setConstraints(lbFOV, gbc);
        this.add(lbFOV);

        sdFOV = new JSlider();
        sdFOV.setMinimum(10);
        sdFOV.setMaximum(120);
        sdFOV.setValue(60);
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 0;
        gbc.anchor = GridBagConstraints.NORTH;
        gbPanel0.setConstraints(sdFOV, gbc);
        this.add(sdFOV);

        lbLightHA = new JLabel("Light horizontal angle");
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.insets = new Insets(10,5,0,0);
        gbPanel0.setConstraints(lbLightHA, gbc);
        this.add(lbLightHA);

        sdLightHA = new JSlider();
        sdLightHA.setMinimum(0);
        sdLightHA.setMaximum((int) (Math.PI*200));
        sdLightHA.setValue(sdLightHA.getMinimum());
        gbc.gridx = 0;
        gbc.gridy = 9;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 0;
        gbc.anchor = GridBagConstraints.NORTH;
        gbPanel0.setConstraints(sdLightHA, gbc);
        this.add(sdLightHA);

        lbLighVA = new JLabel("Light vertical angle");
        gbc.gridx = 0;
        gbc.gridy = 10;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.insets = new Insets(0,5,0,0);
        gbPanel0.setConstraints(lbLighVA, gbc);
        this.add(lbLighVA);


        sdLightVA = new JSlider();
        sdLightVA.setMinimum(0);
        sdLightVA.setMaximum((int) (Math.PI*200));
        sdLightVA.setValue(sdLightVA.getMaximum());
        gbc.gridx = 0;
        gbc.gridy = 11;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 0;
        gbc.anchor = GridBagConstraints.NORTH;
        gbPanel0.setConstraints(sdLightVA, gbc);
        this.add(sdLightVA);

        lbLightDist = new JLabel("Light distance");
        gbc.gridx = 0;
        gbc.gridy = 12;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.insets = new Insets(0,5,0,0);
        gbPanel0.setConstraints(lbLightDist, gbc);
        this.add(lbLightDist);

        sdLightDistance = new JSlider();
        sdLightDistance.setMinimum(0);
        sdLightDistance.setMaximum(200);
        sdLightDistance.setValue(20);
        gbc.gridx = 0;
        gbc.gridy = 13;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 0;
        gbc.anchor = GridBagConstraints.NORTH;
        gbPanel0.setConstraints(sdLightDistance, gbc);
        this.add(sdLightDistance);

        lbScene = new JLabel("Scene");
        gbc.gridx = 0;
        gbc.gridy = 14;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.insets = new Insets(0,5,0,0);
        gbPanel0.setConstraints(lbScene, gbc);
        this.add(lbScene);

        lbSkybox = new JLabel("Skybox");
        gbc.gridx = 1;
        gbc.gridy = 14;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.insets = new Insets(0,5,0,0);
        gbPanel0.setConstraints(lbSkybox, gbc);
        this.add(lbSkybox);

        cbScene = new JComboBox<String>();
        cbScene.addItem("RGB Spheres");
        cbScene.addItem("RT Demo");
        cbScene.addItem("Mirror spheres");
        cbScene.addItem("Random spheres");
        cbScene.addItem("Boxes");
        cbScene.addItem("Custom");


        gbc.gridx = 0;
        gbc.gridy = 15;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 0;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.insets = new Insets(0,5,5,2);
        gbPanel0.setConstraints(cbScene, gbc);
        this.add(cbScene);

        cbSkybox = new JComboBox<String>();
        cbSkybox.addItem("Sky");
        cbSkybox.addItem("Studio");
        cbSkybox.addItem("Outdoor");
        cbSkybox.addItem("Factory");
        cbSkybox.addItem("Custom");
        gbc.gridx = 1;
        gbc.gridy = 15;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 0;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.insets = new Insets(0,2,5,5);
        gbPanel0.setConstraints(cbSkybox, gbc);
        this.add(cbSkybox);


        lbOutRes = new JLabel("Output resolution");
        gbc.gridx = 0;
        gbc.gridy = 21;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.insets = new Insets(10,5,0,0);
        gbPanel0.setConstraints(lbOutRes, gbc);
        this.add(lbOutRes);

        SpinnerModel spImageWidthModel = new SpinnerNumberModel(1920, 0, 100000, 1);
        spImageWidth = new JSpinner(spImageWidthModel);
        spImageWidth.setEditor(new JSpinner.NumberEditor(spImageWidth, "#"));
        gbc.gridx = 0;
        gbc.gridy = 22;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.insets = new Insets(0,5,0,0);
        gbPanel0.setConstraints(spImageWidth, gbc);
        this.add(spImageWidth);

        SpinnerModel spImageHeightModel = new SpinnerNumberModel(1080, 0, 100000, 1);
        spImageHeight = new JSpinner(spImageHeightModel);
        spImageHeight.setEditor(new JSpinner.NumberEditor(spImageHeight, "#"));
        gbc.gridx = 1;
        gbc.gridy = 22;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.insets = new Insets(0,5,0,5);
        gbPanel0.setConstraints(spImageHeight, gbc);
        this.add(spImageHeight);

        btnRenderImage = new JButton("Render image");
        gbc.gridx = 0;
        gbc.gridy = 23;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.insets = new Insets(5,4,0,4);
        gbPanel0.setConstraints(btnRenderImage, gbc);
        this.add(btnRenderImage);

        btnShowAnimationDialog = new JButton("Show animation dialog");
        gbc.gridx = 0;
        gbc.gridy = 24;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.insets = new Insets(5,4,4,4);
        gbPanel0.setConstraints(btnShowAnimationDialog, gbc);
        this.add(btnShowAnimationDialog);

        ChangeListener lightSettingsChangeListener =  e -> viewport.setLightAngle(sdLightHA.getValue()/100F, sdLightVA.getValue()/100F, sdLightDistance.getValue()/10F);

        lightSettingsChangeListener.stateChanged(null); // Update light angle

        sdLightHA.addChangeListener(lightSettingsChangeListener);
        sdLightVA.addChangeListener(lightSettingsChangeListener);
        sdLightDistance.addChangeListener(lightSettingsChangeListener);

        sdResolution.addChangeListener(e -> viewport.setResolution(sdResolution.getValue()/100F));

        sdSpeed.addChangeListener(e -> viewport.setMovementSpeed(sdSpeed.getValue()/100F));

        sdSensitivity.addChangeListener(e -> viewport.setMouseSensitivity(sdSensitivity.getValue()/100F));

        sdFOV.addChangeListener(e -> viewport.getScene().getCamera().setFOV(sdFOV.getValue()));

        cbScene.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                Scene scene = viewport.getScene();
                switch (cbScene.getSelectedIndex()) {
                    case 0: // RGB Spheres
                        scene.clearSolids();
                        scene.addSolid(new Sphere(new Vector3(-1, 0, 0), 0.4F, Color.RED, 0.4F, 0F));
                        scene.addSolid(new Sphere(new Vector3(0, 0, 0), 0.4F, Color.GREEN, 0.4F, 0F));
                        scene.addSolid(new Sphere(new Vector3(1, 0, 0), 0.4F, Color.BLUE, 0.4F, 0F));

                        scene.addSolid(new Plane(-1F, new Color(0, 0, 0), true,0.25F, 0F));
                        break;
                    case 1: // RT Demo
                        scene.clearSolids();
                        scene.addSolid(new Sphere(new Vector3(0F, -0.6F, 0F), 0.4F, Color.WHITE, 0.2F, 0F));
                        scene.addSolid(new Sphere(new Vector3(1F, -0.7F, -0.5F), 0.3F, new Color(0.5F, 0, 1F), 0.2F, 0F));
                        scene.addSolid(new Sphere(new Vector3(1.5F, 0F, 1F), 1F, Color.RED, 0.2F, 0F));
                        scene.addSolid(new Sphere(new Vector3(2F, -0.65F, -0.7F), 0.35F, Color.GREEN, 0.2F, 1F));
                        scene.addSolid(new Sphere(new Vector3(-2F, -0.35F, -0.7F), 0.7F, Color.BLUE, 0.2F, 0F));

                        scene.addSolid(new Plane(-1F, new Color(0, 0, 0), true,0.25F, 0F));
                        break;
                    case 2: // Mirror spheres
                        scene.clearSolids();
                        for (int i = -5; i<=5; i++) {
                            for (int j = -5; j<=5; j++) {
                                scene.addSolid(new Sphere(new Vector3(i, 0, j), 0.4F, Color.WHITE, 1F, 0F));
                            }
                        }

                        scene.addSolid(new Plane(-1F, new Color(0, 0, 0), true,0.25F, 0F));
                        break;
                    case 3: // Random spheres
                        scene.clearSolids();
                        Random rand = new Random();
                        for (int i = 0; i<100; i++) {
                            scene.addSolid(new Sphere(new Vector3(rand.nextFloat()*20-10,rand.nextFloat()*20-10, rand.nextFloat()*20-10), rand.nextFloat(), new Color(rand.nextFloat(), rand.nextFloat(), rand.nextFloat()), rand.nextFloat(), rand.nextFloat()));
                        }
                        break;

                    case 4: //Boxes
                        scene.clearSolids();
                        scene.addSolid(new Box(new Vector3(-1, 0, 1) , new Vector3(1, 1, 1), Color.RED, 0.4F, 0F));
                        scene.addSolid(new Box(new Vector3(0, 0, -1) , new Vector3(1, 1, 1), Color.WHITE, 1F, 0F));
                        scene.addSolid(new Box(new Vector3(2, 0, 2) , new Vector3(1, 1, 1), Color.BLUE, 0.4F, 0F));

                        scene.addSolid(new Plane(-1F, new Color(0, 0, 0), true,1F, 0F));
                        break;

                    case 5: //Boxes
                        scene.clearSolids();
                        sceneLoader("custom.txt", scene);
                        break;

                    default:
                        break;
                }
            }
        });

        cbSkybox.addItemListener(e -> {
            int selectedIndex = cbSkybox.getSelectedIndex();

            if (e.getStateChange() == ItemEvent.SELECTED && selectedIndex != selectedSkyboxIndex) {
                Skybox skybox = viewport.getScene().getSkybox();
                if (skybox.isLoaded()) {
                    if (selectedIndex < cbSkybox.getItemCount()-1) {
                        skybox.reload(cbSkybox.getSelectedItem() +".jpg");
                        selectedSkyboxIndex = selectedIndex;
                    } else {
                        JFileChooser fc = new JFileChooser("Choose a Skybox (HDRI)");
                        fc.setFileFilter(new FileFilter() {
                            @Override
                            public boolean accept(File f) {
                                return f.isDirectory() || f.getName().endsWith("jpg") || f.getName().endsWith("jpeg") || f.getName().endsWith("png") || f.getName().endsWith("bmp");
                            }

                            @Override
                            public String getDescription() {
                                return "Equirectangular images (*.jpg, *.png, *.bmp)";
                            }
                        });

                        fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
                        if (fc.showOpenDialog(viewport) == JFileChooser.APPROVE_OPTION) {
                            skybox.reloadFromFile(fc.getSelectedFile());
                            selectedSkyboxIndex = selectedIndex;
                        } else {
                            cbSkybox.setSelectedIndex(selectedSkyboxIndex);
                        }
                    }

                } else {
                    cbSkybox.setSelectedIndex(selectedSkyboxIndex);
                }
            }
        });

        btnRenderImage.addActionListener(e -> {
            try {
                viewport.renderToImage(getOutputWidth(), getOutputHeight());
            } catch (IOException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(viewport, ex.toString(), "Could not save image", JOptionPane.ERROR_MESSAGE);
            }
        });


        btnShowAnimationDialog.addActionListener(e ->  {
            animationDialog.setVisible(true);
            animationDialog.setLocationRelativeTo(viewport);
        });

    }

    private void sceneLoader(String filename, Scene scene) {

        File file = new File("src/res/" + filename);
        Scanner myReader = null;
        try {
            myReader = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        String[] dividedData;
        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            dividedData = data.split(" ");

            if(dividedData[0].equals("/")) continue;

            // S for sphere
            switch (dividedData[0]) {
                case "S" -> {
                    scene.addSolid(new Sphere(
                            new Vector3(Float.parseFloat(dividedData[1]), Float.parseFloat(dividedData[2]), Float.parseFloat(dividedData[3])),
                            Float.parseFloat(dividedData[4]),
                            new Color(Float.parseFloat(dividedData[5]), Float.parseFloat(dividedData[6]), Float.parseFloat(dividedData[7])),
                            Float.parseFloat(dividedData[8]),
                            Float.parseFloat(dividedData[9])

                    ));
                    }
                
                // B for box
                case "B" -> {
                    scene.addSolid(new Box(new Vector3(Float.parseFloat(dividedData[1]), Float.parseFloat(dividedData[2]), Float.parseFloat(dividedData[3]))
                    , new Vector3(Float.parseFloat(dividedData[4]), Float.parseFloat(dividedData[5]), Float.parseFloat(dividedData[6])), 
                    new Color(Float.parseFloat(dividedData[7]), Float.parseFloat(dividedData[8]), Float.parseFloat(dividedData[9])),
                            Float.parseFloat(dividedData[10]),
                            Float.parseFloat(dividedData[11])));

                }
                // Pl for plane
                case "Pl" -> {
                    scene.addSolid(new Plane(Float.parseFloat(dividedData[1]), new Color(Float.parseFloat(dividedData[2]), Float.parseFloat(dividedData[3]), Float.parseFloat(dividedData[4])),
                     false ,Float.parseFloat(dividedData[6]), Float.parseFloat(dividedData[7])));

                }
            }
        }
    }

    public int getOutputWidth() {
        return (int) spImageWidth.getValue();
    }

    public int getOutputHeight() {
        return (int) spImageHeight.getValue();
    }
}
