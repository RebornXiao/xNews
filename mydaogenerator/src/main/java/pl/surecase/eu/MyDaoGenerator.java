package pl.surecase.eu;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class MyDaoGenerator {
    public static void main(String args[]) throws Exception {
        Schema schema = new Schema(1, "com.xnews.db");
        Entity person = schema.addEntity("Person");
        person.addIdProperty();
        person.addIntProperty("vipLevel");
        person.addIntProperty("equipmentId");
        person.addStringProperty("userName");
        person.addStringProperty("sex");
        person.addStringProperty("personID");
        person.addStringProperty("email");
        person.addStringProperty("userIntegral");
        person.addStringProperty("role");
        person.addStringProperty("deposit");
        person.addStringProperty("balance");
        person.addStringProperty("remark");
        person.addStringProperty("psd");
        person.addStringProperty("vitality");
        person.addIntProperty("isVIP");
        new DaoGenerator().generateAll(schema, args[0]);
    }
}
