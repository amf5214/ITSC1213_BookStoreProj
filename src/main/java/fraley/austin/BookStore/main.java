package fraley.austin.BookStore;

import fraley.austin.BookStore.Enums.MembershipType;
import fraley.austin.BookStore.Enums.ProductType;
import fraley.austin.BookStore.Enums.USState;
import fraley.austin.BookStore.Models.BookStore;
import fraley.austin.BookStore.Models.Member;
import java.time.LocalDate;

public class main {

    public static void main(String[] args) {
        BookStore testStore = new BookStore();

        testStore.signUp("afraley", MembershipType.BASIC);
        testStore.setUserDetails("afraley", "Austin", "Fraley", LocalDate.of(2000, 9, 4));
        testStore.setMembershipDetails("afraley", "123 Unc ave", "Huntersville", USState.NC, 28223);

        Member currentMember = testStore.getMember("afraley");
        long item1 = testStore.addProduct("Of Mice and Men", 55, ProductType.BOOK, 11.99);
        long item2 = testStore.addProduct("Superman", 12, ProductType.DVD, 24.99);

        long orderNum = testStore.createSale(currentMember);

        testStore.addProductToSale(orderNum, item1, 3);
        testStore.addProductToSale(orderNum, item2, 4);

        testStore.print(orderNum);
        testStore.removeProductFromSale(orderNum, item1);
        testStore.print(orderNum);
    }
 }
