package fraley.austin.BookStore.Models;

import fraley.austin.BookStore.Constants.MembershipCosts;
import fraley.austin.BookStore.Enums.MembershipType;
import fraley.austin.BookStore.Enums.USState;

public class Membership {

    private static long nextMembershipNum = 1;

    private long membershipId;
    private MembershipType mType;
    private String shippingAddress;
    private String shippingCity;
    private int shippingZipcode;
    private USState shippingState;
    private boolean goodStanding;
    private double membershipFee;

    public Membership(MembershipType type) {
        this.membershipId = nextMembershipNum;
        incrementNextMembershipNum();
        this.mType = type;
        setMembershipFee();
    }

    private static void incrementNextMembershipNum() {
        nextMembershipNum += 1;
    }

    private static void decrementNextMembershipNum() {
        nextMembershipNum -= 1;
    }

    private void setMembershipFee() {
        this.membershipFee = this.mType.equals(MembershipType.BASIC) ? MembershipCosts.BASIC : MembershipCosts.PREMIUM;
    }

    public long getMembershipId() {
        return membershipId;
    }

    public MembershipType getMType() {
        return mType;
    }

    public void setMType(MembershipType mType) {
        this.mType = mType;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public String getShippingCity() {
        return shippingCity;
    }

    public void setShippingCity(String shippingCity) {
        this.shippingCity = shippingCity;
    }

    public int getShippingZipcode() {
        return shippingZipcode;
    }

    public void setShippingZipcode(int shippingZipcode) {
        this.shippingZipcode = shippingZipcode;
    }

    public USState getShippingState() {
        return shippingState;
    }

    public void setShippingState(USState shippingState) {
        this.shippingState = shippingState;
    }
}
