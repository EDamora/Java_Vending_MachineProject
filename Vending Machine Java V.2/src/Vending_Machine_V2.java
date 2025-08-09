import controller.VendingMachineController;
import model.Cart;
import service.VendingMachineService;
import view.VendingMachineView;

import java.util.Scanner;

public class Vending_Machine_V2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        VendingMachineController controller = new VendingMachineController();
        VendingMachineView view = new VendingMachineView();
        VendingMachineService service = new VendingMachineService();
        Cart cart = new Cart();

        boolean running = true;

        while (running) {
            view.tampilkanMenuUtama();
            int pilihan = scanner.nextInt();

            switch (pilihan) {
                case 1:
                    view.tampilkanMenu(controller.getItems());
                    break;
                case 2:
                    view.tampilkanMenu(controller.getItems());
                    controller.pilihBarang(scanner, cart);
                    break;
                case 3:
                    controller.hapusBarang(scanner, cart);
                    break;
                case 4:
                    cart.tampilKeranjang();
                    break;
                case 5:
                    service.prosesPembayaran(scanner, cart);
                    break;
                case 6:
                    service.lihatHistory();
                    break;
                case 0:
                    running = false;
                    System.out.println("Terimakasih telah menggunakan Vending Machine. ");
                    break;
                default:
                    System.out.println("Pilihan Tidak Valid");
            }
        }
    }
}