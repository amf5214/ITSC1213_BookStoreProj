package fraley.austin.BookStore.Models;

import fraley.austin.BookStore.Enums.MembershipType;

import java.time.LocalDate;

public class Member {

    private static long nextMemberNum = 1;

    private long memberId;
    private String fName;
    private String lName;
    private LocalDate birthDate;
    private Membership membership;
    private String userName;
    private PaymentMethod payMethod;

    public Member(String userName, MembershipType type) {
        this.memberId = nextMemberNum;
        incrementNextMemberNum();
        this.userName = userName;
        this.membership = new Membership(type);
        this.payMethod = new PaymentMethod();
    }

    public static void incrementNextMemberNum() {
        nextMemberNum += 1;
    }

    public static void decrementNextMemberNum() {
        nextMemberNum -= 1;
    }

    public static long getNextMemberNum() {
        return nextMemberNum;
    }

    public static void setNextMemberNum(long nextMemberNum) {
        Member.nextMemberNum = nextMemberNum;
    }

    public long getMemberId() {
        return memberId;
    }

    public void setMemberId(long memberId) {
        this.memberId = memberId;
    }

    public String getFName() {
        return fName;
    }

    public void setFName(String fName) {
        this.fName = fName;
    }

    public String getLName() {
        return lName;
    }

    public void setLName(String lName) {
        this.lName = lName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Membership getMembership() {
        return membership;
    }

    public void changeMembership(MembershipType type) {
        this.membership.setMType(type);
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public PaymentMethod getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(PaymentMethod payMethod) {
        this.payMethod = payMethod;
    }
}
