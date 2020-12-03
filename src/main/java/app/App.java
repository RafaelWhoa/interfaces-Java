package app;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import model.entities.Contract;
import model.entities.Installment;
import model.services.ContractService;
import model.services.OnlinePaymentService;
import model.services.PaypalService;

/**
 * Hello world!
 *
 */
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
