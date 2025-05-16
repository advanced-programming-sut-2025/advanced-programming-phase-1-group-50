package models;

import models.stores.Sellable;
import models.userInfo.Player;

public class Trade {

        private final Player seller;
        private final Player buyer;
        private final int quantity;
        private final Sellable sellable;
        private final int price;

        public Trade(Player seller, Player buyer, int quantity, Sellable sellable, int price) {
                this.seller = seller;
                this.buyer = buyer;
                this.quantity = quantity;
                this.sellable = sellable;
                this.price = price;
        }

        public Player getSeller() {
                return seller;
        }

        public Player getBuyer() {
                return buyer;
        }

        public int getQuantity() {
                return quantity;
        }

        public Sellable getSellable() {
                return sellable;
        }

        public int getPrice() {
                return price;
        }
}
