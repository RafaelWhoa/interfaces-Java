package app;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import model.entities.Contract;
import model.entities.Installment;
import model.services.ContractService;
import model.services.PaypalService;

/**
  * 
  *  Uma empresa deseja automatizar o processamento de seus contratos. O processamento de
  * um contrato consiste em gerar as parcelas a serem pagas para aquele contrato, com base no
  * número de meses desejado.
  *  A empresa utiliza um serviço de pagamento online para realizar o pagamento das parcelas.
  * Os serviços de pagamento online tipicamente cobram um juro mensal, bem como uma taxa
  * por pagamento. Por enquanto, o serviço contratado pela empresa é o do Paypal, que aplica
  * juros simples de 1% a cada parcela, mais uma taxa de pagamento de 2%.
  *  Fazer um programa para ler os dados de um contrato (número do contrato, data do contrato,
  * e valor total do contrato). Em seguida, o programa deve ler o número de meses para
  * parcelamento do contrato, e daí gerar os registros de parcelas a serem pagas (data e valor),
  * sendo a primeira parcela a ser paga um mês após a data do contrato, a segunda parcela dois
  * meses após o contrato e assim por diante. Mostrar os dados das parcelas na tela.
  *
  **/
public class App 
{
    public static void main( String[] args )
    {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        

        try {
        System.out.println("Enter contract data");
        System.out.print("Number: ");
        int number = sc.nextInt();
        System.out.print("Date (dd/MM/yyyy): ");
        sc.nextLine();
        Date date = sdf.parse(sc.next());
        System.out.print("Contract value: ");
        Double contractValue = sc.nextDouble();
        System.out.print("Enter number of installments: ");
        int n = sc.nextInt();

        Contract contract = new Contract(number, date, contractValue);

        ContractService cs = new ContractService(new PaypalService());

        cs.processContract(contract, n);

        System.out.println("Installments:");
		for (Installment x : contract.getInstallments()) {
			System.out.println(x);
		}
        
        }
        catch (ParseException e) {
            System.out.println("Invalid date format: " + e.getMessage());
        }


        sc.close();
    }
}
