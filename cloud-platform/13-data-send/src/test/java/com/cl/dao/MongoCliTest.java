package com.cl.dao;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.junit.Test;

/**
 * by cl at 2020/4/5 0005
 */
public class MongoCliTest {
    //连接到 MongoDB 服务。
    // 4 //两个参数分别为 IP 和端口，如果是远程连接请替换“localhost”为服务器 IP 5
    MongoClient mongoClient = new MongoClient("localhost", 27017);
    //连接到 test 数据库。
    MongoDatabase mongoDatabase = mongoClient.getDatabase("test");

    @Test
    public void createCollection() {
        mongoDatabase.createCollection("devicedata");

    }

    @Test
    public void insert() {
        MongoCollection<Document> documentMongoCollection = mongoDatabase.getCollection("devicedata");
        for( int i = 0; i < 20; i++) {
            Document document = new Document("title", "MongoDB java 操作测试");
            document.append("description", "使用 Java 测试 MongoDB 的基本操作").append("name", "cl"+i).append("age", i);
            documentMongoCollection.insertOne(document);
        }


    }
    @Test
    public void find() {
        MongoCollection<Document> documentMongoCollection = mongoDatabase.getCollection("devicedata");
        FindIterable<Document> documents = documentMongoCollection.find();
        MongoCursor<Document> iterator = documents.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        FindIterable<Document> documents1 = documentMongoCollection.find(Filters.eq("_id", new ObjectId("5e89844ba47c6d2058ff8ab9")));
        System.out.println(documents1.first());
        FindIterable<Document> documents2 = documentMongoCollection.find(Filters.and(Filters.eq("name", "cl"), Filters.eq("age", "11")));
        System.out.println(documents2.first());
    }
    @Test
    public void page() {
        MongoCollection<Document> documentMongoCollection = mongoDatabase.getCollection("devicedata");
        FindIterable<Document> documents = documentMongoCollection.find().skip(10).limit(10);
        MongoCursor<Document> iterator = documents.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }


    @Test
    public void update() {
        MongoCollection<Document> documentMongoCollection = mongoDatabase.getCollection("devicedata");
        documentMongoCollection.updateMany(Filters.eq("_id", new ObjectId("5e898150a47c6d2644f99d33"))
        , new Document("$set", new Document("description", "测试")));
    }
    @Test
    public void delete() {
        MongoCollection<Document> documentMongoCollection = mongoDatabase.getCollection("devicedata");
        documentMongoCollection.findOneAndDelete(Filters.eq("_id", new ObjectId("5e898150a47c6d2644f99d33")));
    }

    public static void main(String args[]) {


    }

}
