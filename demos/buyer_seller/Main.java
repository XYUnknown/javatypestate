package demos.buyer_seller;


public class Main {
	
	public static void main(String[] args) {
		Socket socketSellerBuyer1 = new Socket();
		Socket socketSellerBuyer2 = new Socket();
		Socket socketBuyer1Buyer2 = new Socket();

		Seller seller = new Seller(socketSellerBuyer1, socketSellerBuyer2);
		Buyer1 buyer1 = new Buyer1(socketSellerBuyer1, socketBuyer1Buyer2);
		Buyer2 buyer2 = new Buyer2(socketSellerBuyer2, socketBuyer1Buyer2);

		socketSellerBuyer1.setEndpoints(seller, buyer1);
		socketSellerBuyer2.setEndpoints(seller, buyer2);
		socketBuyer1Buyer2.setEndpoints(buyer1, buyer2);

		System.out.println("Begin Protocol");
		////
		
		buyer1.sendTitleToSeller("''Alice's Adventures In Wonderland''");
		String title = seller.receiveTitleFromBuyer1();
		seller.sendPriceToBuyer1("£80");
		buyer1.receivePriceFromSeller();
		buyer1.sendQuoteToBuyer2("£42");
		String quote = buyer2.receiveQuoteFromBuyer1();
		
		switch (buyer2.sendToSellerBuyer1("AGREE")) {
			case AGREE:
				buyer2.transferMoneyToSeller("£42");
				break;
			case QUIT:
				break;
		}

		switch (buyer1.receiveLabelFromBuyer2()) {
			case AGREE:
				buyer1.transferMoneyToSeller("£38");
				break;
			case QUIT:
				break;
		}

		switch (seller.receiveLabelFromBuyer2()) {
			case AGREE:
				String money;
				money = seller.receiveMoneyFromBothBuyers();
				System.out.println("Seller: I received a total amount of " + money);
				break;
			case QUIT:
				System.out.println("Seller: We end the communication!");
				break;
		}

		////
		System.out.println("End Protocol");
	}
}
