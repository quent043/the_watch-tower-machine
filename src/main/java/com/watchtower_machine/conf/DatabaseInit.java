package com.watchtower_machine.conf;

public class DatabaseInit {

    public DatabaseInit() {
    }

    public void databaseConnectionInitializer(){

        Connmanagement.createConnection();
    }
}
