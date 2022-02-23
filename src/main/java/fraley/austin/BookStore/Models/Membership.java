package fraley.austin.BookStore.Models;

import fraley.austin.BookStore.Constants.MembershipCosts;
import fraley.austin.BookStore.Enums.MembershipType;
import fraley.austin.BookStore.Enums.USState;

public class Membership {

    private static long nextMembershipNum = 1;

    private long membershipId;
    private MembershipType mType;
    private Address shippingAddress;
    private boolean goodStanding;
    private double membershipFee;

    public Membership(MembershipType type) {
        this.membershipId = nextMembershipNum;
        this.shippingAddress = new Address();
        this.mType = type;
        this.goodStanding = true;
        setMembershipFee();
        incrementNextMembershipNum();
    }

    private static void incrementNextMembershipNum() {
        nextMembershipNum += 1;
    }

    private static void decrementNextMembershipNum() {
        nextMembershipNum -= 1;
    }

    public void setMembershipFee() {
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

    public Address getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(Address shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public boolean isGoodStanding() {
        return goodStanding;
    }

    public void setGoodStanding(boolean goodStanding) {
        this.goodStanding = goodStanding;
    }

    public double getMembershipFee() {
        return membershipFee;
    }

    public void setMembershipFee(double membershipFee) {
        this.membershipFee = membershipFee;
    }
}
