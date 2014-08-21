package as.vista;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Iterator;
import java.util.List;

import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JComboBox;

import as.domini.CU_IngressarPacient;
import as.domini.TuplaMetge;
import as.domini.TupleHospHab;
import as.domini.controladors.ServiceLocator;
import as.main.Excepcions;


public class capaPresentacio {

	private JFrame frmSd;
	private JTextField txtEspecialitat;
	private JTextField txtTSPacient;
	private JPanel panelPacientHabitacio;
	private JPanel panelEspecialitat;
	private JPanel panelPregunta;
	private JPanel panelAssignaMetge;
	private JComboBox comboBoxHospital;
	private JComboBox comboBoxHabitacio;
	private JComboBox comboBoxMetge;
	private static CU_IngressarPacient cuip;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					cuip = CU_IngressarPacient.getInstance();
					capaPresentacio window = new capaPresentacio();
					window.frmSd.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public capaPresentacio() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSd = new JFrame();
		frmSd.setTitle("Sel·lecciona la especialitat");
		frmSd.setBounds(100, 100, 372, 193);
		frmSd.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSd.getContentPane().setLayout(null);
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(6, 6, 360, 159);
		frmSd.getContentPane().add(layeredPane);
		
		panelPacientHabitacio = new JPanel();
		panelPacientHabitacio.setVisible(false);
		panelPacientHabitacio.setBounds(0, 0, 360, 159);
		layeredPane.add(panelPacientHabitacio);
		panelPacientHabitacio.setEnabled(false);
		panelPacientHabitacio.setLayout(null);
		
		JButton btnContinuar_1 = new JButton("Continuar");
		btnContinuar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { //finestra Pacient habitacio (continuar listener)
				if (txtTSPacient.getText().length() == 0) { //pacient no introduit
					JOptionPane.showMessageDialog(panelPacientHabitacio,
						    "No s'ha introduït cap pacient.",
						    "Pacient buit",
						    JOptionPane.ERROR_MESSAGE);
				}
				else if (comboBoxHospital.getSelectedItem().toString().contains("Cap hospital")) { //(hospital no seleccionat)
					JOptionPane.showMessageDialog(panelPacientHabitacio,
						    "No s'ha sel·leccionat cap hospital.",
						    "Pacient buit",
						    JOptionPane.ERROR_MESSAGE);
				}
				else if (comboBoxHabitacio.getSelectedItem().toString().contains("Cap habitacio")) { //(habitacio no seleccionada)
					JOptionPane.showMessageDialog(panelPacientHabitacio,
						    "No s'ha sel·leccionat cap habitacio.",
						    "Pacient buit",
						    JOptionPane.ERROR_MESSAGE);
				}
				/*else if //(pacient no existeix)
				else if //(pacientActualmentIngressat)
				*/
				else {
					try {
						cuip.crearIngres(comboBoxHospital.getSelectedItem().toString(), 
										 Integer.valueOf(comboBoxHabitacio.getSelectedItem().toString()), 
										 txtTSPacient.getText());
						
						JOptionPane.showMessageDialog(panelPacientHabitacio,
							    "L'ingres s'ha creat correctament.");

					      
						panelPacientHabitacio.setEnabled(false);
						panelPacientHabitacio.setVisible(false);
						panelPregunta.setEnabled(true);
						panelPregunta.setVisible(true);
						frmSd.setTitle("Ingrés creat");
						comboBoxMetge.addItem("Cap metge sel.");

					} catch (NumberFormatException e1) {
						// És un desplegable amb números, no entrarà mai
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(panelPacientHabitacio,
								e1.getMessage(),
								"Error",
							    JOptionPane.ERROR_MESSAGE);
					}
					
									}
			}
		});
		btnContinuar_1.setBounds(233, 124, 121, 29);
		panelPacientHabitacio.add(btnContinuar_1);
		
		JButton btnCancel_1 = new JButton("Cancel·lar");
		btnCancel_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnCancel_1.setBounds(100, 124, 126, 29);
		panelPacientHabitacio.add(btnCancel_1);
		
		txtTSPacient = new JTextField();
		txtTSPacient.setBounds(155, 6, 150, 28);
		panelPacientHabitacio.add(txtTSPacient);
		txtTSPacient.setColumns(10);
		
		JLabel lblNTsPacient = new JLabel("n\u00BA TS Pacient");
		lblNTsPacient.setBounds(27, 12, 116, 16);
		panelPacientHabitacio.add(lblNTsPacient);
		
		comboBoxHospital = new JComboBox();
		comboBoxHospital.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comboBoxHospital.getSelectedIndex() > 0) comboBoxHabitacio.enable();
				else if (comboBoxHospital.getSelectedIndex() == 0) {
					comboBoxHabitacio.disable();
				}
				comboBoxHabitacio.updateUI();
			}
		});
		comboBoxHospital.setBounds(155, 46, 150, 27);
		panelPacientHabitacio.add(comboBoxHospital);
		
		comboBoxHabitacio = new JComboBox();
		comboBoxHabitacio.setEnabled(false);
		comboBoxHabitacio.setBounds(155, 85, 150, 27);
		panelPacientHabitacio.add(comboBoxHabitacio);
		
		JLabel lblNewLabel_1 = new JLabel("Habitaci\u00F3\n");
		lblNewLabel_1.setBounds(27, 89, 116, 16);
		panelPacientHabitacio.add(lblNewLabel_1);
		
		JLabel lblHospital = new JLabel("Hospital");
		lblHospital.setBounds(27, 50, 116, 16);
		panelPacientHabitacio.add(lblHospital);
		
		
		
		panelAssignaMetge = new JPanel();
		panelAssignaMetge.setOpaque(false);
		panelAssignaMetge.setVisible(false);
		panelAssignaMetge.setBounds(0, 0, 356, 159);
		layeredPane.add(panelAssignaMetge);
		panelAssignaMetge.setLayout(null);
		
		final JButton btnFinalitzar = new JButton("Finalitzar");
		btnFinalitzar.setBounds(236, 124, 102, 29);
		panelAssignaMetge.add(btnFinalitzar);
		btnFinalitzar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(comboBoxMetge.getSelectedItem().toString().contains("Cap metge") ) {
					JOptionPane.showMessageDialog(panelAssignaMetge,
						    "No s'ha sel·leccionat cap metge",
						    "Cap metge seleccionat",
						    JOptionPane.ERROR_MESSAGE);
				}
				else {
					int index = comboBoxMetge.getSelectedItem().toString().indexOf("-");
					String dni = comboBoxMetge.getSelectedItem().toString().substring(index+2);
					System.out.println("I el dni és... " + dni);
					try {
						cuip.assignarMetgeAIngres(dni);
						cuip.enviarInformeIngres();
						
						JOptionPane.showMessageDialog(panelAssignaMetge, "S'ha enviat el missatge correctament. Proces finalitzat");
						System.exit(0);
						
						
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(panelAssignaMetge,
							    e1.getMessage(),
							    "Error",
							    JOptionPane.ERROR_MESSAGE);
						
						//Si el servei no està disponible, s'acaba el cas d'ús
						if(e1.getMessage() == "serveiNoDisponible") {
							System.exit(0);
						}

					}
				}
				
			      
			}
		});
		
		comboBoxMetge = new JComboBox();
		comboBoxMetge.setBounds(151, 59, 187, 27);
		panelAssignaMetge.add(comboBoxMetge);
		
		JLabel lblNewLabel_2 = new JLabel("Metge");
		lblNewLabel_2.setBounds(23, 63, 104, 16);
		panelAssignaMetge.add(lblNewLabel_2);
		
		
		
		
		
		panelPregunta = new JPanel();
		panelPregunta.setOpaque(false);
		panelPregunta.setVisible(false);
		panelPregunta.setBounds(0, 0, 356, 159);
		layeredPane.add(panelPregunta);
		panelPregunta.setLayout(null);
		
		JLabel lblPregunta = new JLabel("Vols assignar un metge?");
		lblPregunta.setBounds(23, 63, 300, 16);
		panelPregunta.add(lblPregunta);
		
		JButton btnAssignarMetge= new JButton("Sí");
		btnAssignarMetge.setBounds(236, 124, 102, 29);
		panelPregunta.add(btnAssignarMetge);
		
		JButton btnNoAssignarMetge= new JButton("No");
		btnNoAssignarMetge.setBounds(108, 124, 117, 29);
		panelPregunta.add(btnNoAssignarMetge);
		
		btnAssignarMetge.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelPregunta.setEnabled(false);
				panelPregunta.setVisible(false);
				panelAssignaMetge.setEnabled(true);
				panelAssignaMetge.setVisible(true);
				comboBoxMetge.setEnabled(false);
				btnFinalitzar.setEnabled(false);
				frmSd.setTitle("Assignar metge");
				try {
					List<TuplaMetge> metges = cuip.mostraMetgesHospitalPerEspecialitat();
					Iterator<TuplaMetge> it = metges.iterator();
	                while(it.hasNext()) {
	                	TuplaMetge i = it.next();
	                	comboBoxMetge.addItem(i.nom + " - " + i.dni);
	                }
	                comboBoxMetge.setEnabled(true);
					btnFinalitzar.setEnabled(true);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(panelPregunta,
							Excepcions.NO_METGES + ". Es procedeix a enviar el missatge.",
						    "Error",
						    JOptionPane.ERROR_MESSAGE);
					
					//Si no hi ha metges, saltem directament a enviar el missatge
					try {
						cuip.enviarInformeIngres();
						JOptionPane.showMessageDialog(panelPregunta, "S'ha enviat el missatge correctament. Proces finalitzat.");
						
						
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(panelPregunta,
							    e2.getMessage(),
							    "Error",
							    JOptionPane.ERROR_MESSAGE);

					}
					System.exit(0);
				}
			}
		});
		
		btnNoAssignarMetge.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					cuip.enviarInformeIngres();
					JOptionPane.showMessageDialog(panelPregunta, "S'ha enviat el missatge correctament. Proces finalitzat.");
					
					
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(panelPregunta,
						    e1.getMessage(),
						    "Error",
						    JOptionPane.ERROR_MESSAGE);

				}
				System.exit(0);
			}
		});
		
		
		
		
		
		
		
		
		
		
		
		panelEspecialitat = new JPanel();
		panelEspecialitat.setOpaque(false);
		panelEspecialitat.setBounds(0, 0, 360, 159);
		layeredPane.add(panelEspecialitat);
		panelEspecialitat.setLayout(null);
		
		JButton btnCancel = new JButton("Cancel·lar");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnCancel.setBounds(108, 124, 117, 29);
		panelEspecialitat.add(btnCancel);
		
		JButton btnContinuar = new JButton("Continuar");
		btnContinuar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtEspecialitat.getText().length() == 0){
					JOptionPane.showMessageDialog(panelEspecialitat,
						    "No s'ha introduït cap especialitat.",
						    "Especialitat buida",
						    JOptionPane.ERROR_MESSAGE);
					
				}
				else {
					try {
						final List<TupleHospHab> hosps = cuip.obteHospitalsLliuresPerEspecialitat(txtEspecialitat.getText());
						System.out.println(hosps);
						panelPacientHabitacio.setEnabled(true);
						panelPacientHabitacio.setVisible(true);
						panelEspecialitat.setEnabled(false);
						panelEspecialitat.setVisible(false);
						frmSd.setTitle("Pacient i Habitacio");
						//Hospitals
						Iterator<TupleHospHab> it = hosps.iterator();
						comboBoxHospital.addItem("Cap hospital sel.");
						while(it.hasNext()) {
							comboBoxHospital.addItem(it.next().nom);
						}
						
						comboBoxHabitacio.addItem("Cap habitacio sel.");
						comboBoxHospital.addActionListener(new ActionListener() {
				            public void actionPerformed(ActionEvent event) {
				                JComboBox comboBox = (JComboBox) event.getSource();
				                comboBoxHabitacio.setEnabled(false);
				                if(comboBox.getSelectedIndex() != 0) {
				                	comboBoxHabitacio.removeAllItems();
				                	comboBoxHabitacio.addItem("Cap habitacio sel.");
					                Object selected = comboBox.getSelectedItem();
					                Iterator<TupleHospHab> it = hosps.iterator();
					                while(it.hasNext()) {
					                	TupleHospHab i = it.next();
					                	if(selected.toString().equals(i.nom)) {
					                		Iterator<Integer> it2 = i.habitacions.iterator();
											while(it2.hasNext()) {
												comboBoxHabitacio.addItem(it2.next());
											}
					                	}
					                }
					                comboBoxHabitacio.setEnabled(true);
				                }
				            }
				        });

						
						
					} catch(Exception exc) {
						JOptionPane.showMessageDialog(panelEspecialitat,
								exc.getMessage(),
								"Error",
							    JOptionPane.ERROR_MESSAGE);	
						txtEspecialitat.setText("");
						
					}
					
					
					
				}
				
			
			}
		});
		btnContinuar.setBounds(237, 124, 117, 29);
		panelEspecialitat.add(btnContinuar);
		
		txtEspecialitat = new JTextField();
		txtEspecialitat.setBounds(154, 52, 149, 28);
		panelEspecialitat.add(txtEspecialitat);
		txtEspecialitat.setColumns(10);
		txtEspecialitat.setText("");
		
		JLabel lblNewLabel = new JLabel("Especialitat");
		lblNewLabel.setBounds(41, 58, 117, 16);
		panelEspecialitat.add(lblNewLabel);
	}
}
