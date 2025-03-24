package com.shark.example.service.database.neo4j;


import org.neo4j.driver.*;

import java.util.List;

public class Neo4jExample {

    public void start() {
        try(Driver driver = GraphDatabase.driver("neo4j://localhost:7687", AuthTokens.basic("neo4j", "password"))) {
            storeSku(driver);

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void storeSku(Driver driver) {
        try (Session session = driver.session()) {
            session.writeTransaction(tx -> {
                tx.run("CREATE (s:SKU {name: $name, embedding: $embedding})",
                        Values.parameters("text", "Shark", "embedding", List.of(new double[]{0.9})));
                return null;
            });
        }
    }

    public static void main(String[] args) {
        new Neo4jExample().start();
    }

}
