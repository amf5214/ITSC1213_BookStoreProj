package fraley.austin.BookStore.Security;

import fraley.austin.BookStore.Models.Member;

import org.apache.commons.codec.digest.Crypt;
import org.jetbrains.annotations.NotNull;

import java.util.Hashtable;

public class PasswordEncryptor {

    Hashtable<Long, String[]> currentPasswords;
    SaltGenerator saltGenerator;

    public PasswordEncryptor() {
        currentPasswords =  new Hashtable<>();
        saltGenerator = new SaltGenerator();
    }

    public void createPassword(@NotNull String password, Member member) {
        String salt = saltGenerator.nextSalt();
        String encryptedPassword = Crypt.crypt(password, salt);
        String[] rtnArray = {encryptedPassword, salt};
        currentPasswords.put(member.getMemberId(), rtnArray);
    }

    public boolean checkPassword(String password, Member member) {
        try {
            long memberId = member.getMemberId();
            String salt = currentPasswords.get(memberId)[1];
            return (currentPasswords.get(memberId)[0].equals(Crypt.crypt(password, salt)));
        } catch (NullPointerException e) {
            return false;
        }
    }

}
