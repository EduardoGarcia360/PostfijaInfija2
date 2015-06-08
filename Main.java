import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class Main extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField entrada;
	JLabel lblPost, lblResultado, lblSolu, lblNota1, lblNota2, label, label_1;
	JButton btnCalcular, btnLimpiar;
	Pila stack = new Pila();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Main() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 471, 300);
		this.setTitle("Eduardo Antonio Garcia Franco -> 2012-12961 -> IPC1 (B)");
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblNota1 = new JLabel("Convertir exprecion Infija a Postfija");
		lblNota1.setBounds(132, 11, 204, 14);
		contentPane.add(lblNota1);
		
		entrada = new JTextField();
		entrada.setBounds(127, 39, 204, 20);
		contentPane.add(entrada);
		
		lblNota2 = new JLabel("Postfija:");
		lblNota2.setBounds(25, 86, 64, 14);
		contentPane.add(lblNota2);
		
		lblPost = new JLabel();
		lblPost.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		lblPost.setBounds(129, 86, 202, 20);
		contentPane.add(lblPost);
		
		lblResultado = new JLabel("Resultado:");
		lblResultado.setBounds(25, 153, 64, 14);
		contentPane.add(lblResultado);
		
		lblSolu = new JLabel();
		lblSolu.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		lblSolu.setBounds(129, 150, 202, 20);
		contentPane.add(lblSolu);
		
		btnCalcular = new JButton("Calcular");
		btnCalcular.addActionListener(this);
		btnCalcular.setBounds(132, 207, 89, 23);
		contentPane.add(btnCalcular);
		
		label = new JLabel("Ingrese Infija:   (");
		label.setBounds(25, 42, 92, 14);
		contentPane.add(label);
		
		label_1 = new JLabel(")");
		label_1.setBounds(341, 42, 15, 14);
		contentPane.add(label_1);
		
		btnLimpiar = new JButton("Limpiar");
		btnLimpiar.addActionListener(this);
		btnLimpiar.setBounds(247, 207, 89, 23);
		contentPane.add(btnLimpiar);
	}
	
	/**
	 * METODO PARA VERIFICAR SI LA EXPRECION ES NUMERO O NO
	 * @param dato RECIBE CARACTER EN ESTE CASO TIPO 'CHAR' LO CONVERTIMOS A STRING PARA VERIFICAR SI SE PUEDE CONVERTIR A NUMERO
	 * @return RETORNARA 'TRUE' SI EL CARACTER ES NUMERO, RETORNARA 'FALSE' SI EL CARACTER NO ES NUMERO.
	 */
	boolean Es_Numero(char dato){
		String tmp = Character.toString(dato);
        try{
            Integer.parseInt(tmp);
        }catch(NumberFormatException nfe){
            return false;
        }
        return true;
    }
	
	/**
	 * METODO QUE CONVIERTE LA EXPRECION INGRESADA A POSTFIJA.
	 */
	void InfijaToPostfija(){
		String agregar = "(" + entrada.getText() + ")";
		char [] Exprecion = agregar.toCharArray();
		int Resultado=0, num1=0, num2=0;
		String postfija="";
		for(int i=0; i<Exprecion.length; i++){
			if( Es_Numero(Exprecion[i]) ){//SI LA POSICION 'i' ES UN NUMERO.
				
				String tmp = Character.toString(Exprecion[i]);
				
				if( Es_Numero(Exprecion[i+1]) ){ //SI EL SIGUIENTE CARACTER ES NUMERO, LO CONCATENA AL ANTERIOR.
					String tmp2 = Character.toString(Exprecion[i+1]);
					String r = tmp + tmp2;
					postfija = postfija + r + " ";
					int num = (int) Integer.parseInt(r);
					stack.push(num); //Y LO METE A LA PILA, COMO YA VERIFICAMOS EL SIGUIENTE NUMERO.
					i++; //AUMENTAMOS 'i' PARA QUE PASE AL SIGUIENTE CARACTER.
				}else{//SI EL SIGUIENTE CARACTER NO ES UN NUMERO.
					int num = (int) Integer.parseInt(tmp);
					stack.push(num);
					postfija = postfija + tmp + " ";
				}
				
			}else if(Exprecion[i] == ')'){
				try{
					int c=1;
					do{
						String operando = stack.pop_c();
						if( operando.equals("(") ){
							c--;
						}else{
							num2 = stack.pop();
							num1 = stack.pop();
							postfija = postfija + operando + " ";
							if(operando.equals("+")){
								Resultado = num1 + num2;
								stack.push(Resultado);
							}else if(operando.equals("-")){
								Resultado = num1 - num2;
								stack.push(Resultado);
							}else if(operando.equals("*")){
								Resultado = num1 * num2;
								stack.push(Resultado);
							}else if(operando.equals("/")){
								Resultado = num1 / num2;
								stack.push(Resultado);
							}
						}
					}while(c != 0);
				}catch(Exception e){
					JOptionPane.showMessageDialog(null, "Verifica que hayas ingresado bien la ecuacion");
					break;
				}
			}else if (Exprecion[i] == '(' || Exprecion[i] == '+' || Exprecion[i] == '-' || Exprecion[i] == '*' || Exprecion[i] == '/'){
				stack.push_c( Character.toString(Exprecion[i]) );
			}
		}//FIN FOR.
		lblSolu.setText( Integer.toString(stack.pop()) );
		lblPost.setText(postfija);
	}
	
	public void actionPerformed(ActionEvent e){
		
		if(e.getSource() == btnCalcular){
			if( entrada.getText().equals("") ){
				JOptionPane.showMessageDialog(null, "-Ingresa una exprecion Infija para empezar"
						+"\n-El programa agrega automaticamente parentesis"
						+"\n al inicio y al final de la exprecion."
						+"\nEjemplo: 2*((2+3) + (15/3))");
			}else{
				InfijaToPostfija();
			}
		}
		if(e.getSource() == btnLimpiar){
			entrada.setText("");
			lblPost.setText("");
			lblSolu.setText("");
		}
		
	}
	
}
